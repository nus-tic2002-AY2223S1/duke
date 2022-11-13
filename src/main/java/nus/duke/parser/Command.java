package nus.duke.parser;

import nus.duke.data.DukeException;
import nus.duke.storage.Storage;
import nus.duke.ui.Messages;
import nus.duke.ui.Ui;
import nus.duke.tasklist.*;
import nus.duke.tasklist.TaskList;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents an executable command.
 */
public class Command {
    static private String command;
    static private String description;
    static private String taskBefore = null;
    static private LocalDateTime dateAndTime1;
    static private LocalDateTime dateAndTime2;
    static private String preposition;
    static private int index;
    static private int compareCount = 0;
    private boolean isEXit = false;

    /**
     * Command constructor for the "list" command.
     */
    public Command(String command){
        this.command = command;
    }
    /**
     * Command constructor for the "bye" command.
     */
    public Command(String command, boolean isExit){
        this.command = command;
        this.isEXit = isExit;
    }
    /**
     * Command constructor for the commands with index, E.g. "mark", "unmark" and "delete".
     */
    public Command(String command, int index) {
        this.command = command;
        this.index = index;
    }
    /**
     * Command constructor for the "todo" command.
     */
    public Command(String command, String description) {
        this.command = command;
        this.description = description;
    }

    /**
     * Command constructor for the "after" command after a specific task.
     */
    public Command(String command, String description, String taskBefore) {
        this.command = command;
        this.description = description;
        this.taskBefore = taskBefore;
    }
    /**
     * Command constructor for the "after" command after a specific date/time.
     */
    public Command(String command, String description, String date1, String time1) {
        this.command = command;
        this.description = description;
        this.dateAndTime1 = LocalDateTime.parse(date1 + " " + time1, TaskList.STORAGE_FORMATTER);
    }
    /**
     * Command constructor for the "deadline" command.
     */
    public Command(String command, String description, String preposition, String date1, String time1) {
        this.command = command;
        this.description = description;
        this.preposition = preposition;
        this.dateAndTime1 = LocalDateTime.parse(date1 + " " + time1, TaskList.STORAGE_FORMATTER);
    }
    /**
     * Command constructor for the "event" command.
     */
    public Command(String command, String description, String preposition, String date1, String time1, String date2, String time2) {
        this.command = command;
        this.description = description;
        this.preposition = preposition;
        this.dateAndTime1 = LocalDateTime.parse(date1 + " " + time1, TaskList.STORAGE_FORMATTER);
        this.dateAndTime2 = LocalDateTime.parse(date2 + " " + time2, TaskList.STORAGE_FORMATTER);
    }
    /**
     * Getter of the command.
     */
    public String getCommand() {
        return this.command;
    }
    /**
     * Getter of the description.
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * Getter of the description.
     */
    public String getPreposition() {
        return this.preposition;
    }
    /**
     * Getter of the index.
     */
    public Integer getIndex() {
        return this.index;
    }
    /**
     * Getter of the dateAndTime2.
     */
    public String getTaskBefore() {
        return this.taskBefore;
    }
    /**
     * Getter of the dateAndTime1.
     */
    public LocalDateTime getDateAndTime1() {
        return this.dateAndTime1;
    }
    /**
     * Getter of the dateAndTime2.
     */
    public LocalDateTime getDateAndTime2() {
        return this.dateAndTime2;
    }
    /**
     * Extract the isExit of the current command.
     */
    public boolean isExit() {
        return this.isEXit;
    }
    /**
     * Executes the command.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> list = tasks.getList();
        switch (this.command) {
            case "bye":
                byeCommand(ui);
                break;
            case "mark":
                markCommand(list);
                storage.save(tasks);
                break;
            case "unmark":
                unMarkCommand(list);
                storage.save(tasks);
                break;
            case "delete":
                deleteCommand(tasks, ui);
                storage.save(tasks);
                break;
            case "list":
                tasks.printList();
                break;
            case "find":
                findCommand(list);
                break;
            case "todo":
            case "deadline":
            case "event":
            case "DoAfter":
                try {
                    addCommand(tasks, ui, storage);
                } catch (DukeException e){
                    throw new DukeException(e.getMessage());
                }
                break;
            default:
                //command variable will only contain the above values.
                assert false : command;
        }
    }
    /**
     * Mark the task as done with reference to the index provided in the command.
     *
     * @throws DukeException is the index in this command is out of the task list range.
     */
    public void markCommand(ArrayList<Task> list) throws DukeException {
        try {
            list.get(this.index).markAsDone(0);
        } catch (IndexOutOfBoundsException e){
            throw new DukeException(Messages.MESSAGE_TASK_NUMBER_OUT_OF_RANGE);
        }
    }
    /**
     * Mark the task as undone with reference to the index provided in the command.
     *
     * @throws DukeException is the index in this command is out of the task list range.
     */
    public void unMarkCommand(ArrayList<Task> list) throws DukeException {
        try {
            list.get(this.index).markAsUndone(0);
        } catch (IndexOutOfBoundsException e){
            throw new DukeException(Messages.MESSAGE_TASK_NUMBER_OUT_OF_RANGE);
        }
    }
    /**
     * Delete the task with reference to the index provided in the command.
     *
     * @throws DukeException is the index in this command is out of the task list range.
     */
    public void deleteCommand(TaskList tasks, Ui ui) throws DukeException {
        ArrayList<Task> list = tasks.getList();
        String removedTask;
        try {
            removedTask = list.get(this.index).toString(1);
            list.remove(this.index);
        }catch (IndexOutOfBoundsException e){
            throw new DukeException(Messages.MESSAGE_TASK_NUMBER_OUT_OF_RANGE);
        }
        tasks.changeListCount("-");
        ui.echo(removedTask, "removed");
    }
    /**
     * Search existing task list and find tasks matching to the keywords provided in the command.
     *
     */
    public void findCommand(ArrayList<Task> list) {
        Ui.print("Here are the matching tasks in your list:\n");
        list.forEach(Task ->compare(Task));
        Ui.print(compareCount + " task(s) found.\n");
        compareCount = 0;
    }
    /**
     * Add three types of tasks "todo", "deadline" & "event" to the task list, save to a .txt file and echo off.
     */
    public void addCommand (TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> list = tasks.getList();
        //check if the task to be added is existed
        if (tasks.getListCount() > 0) {
            try {
                isDuplicate(tasks);
            } catch (DukeException e) {
                throw new DukeException(e.getMessage());
            }
        }
        switch (command) {
            case "todo":
                list.add(new Todo(description));
                break;
            case "deadline":
                list.add(new Deadline(description, preposition, dateAndTime1));
                break;
            case "event":
                list.add(new Event(description, preposition, dateAndTime1, dateAndTime2));
                break;
            case "DoAfter":
                if (taskBefore == null) {
                    list.add(new DoAfter(description, dateAndTime1));
                } else {
                    list.add(new DoAfter(description, taskBefore));
                    taskBefore = null;
                }
                break;
            default:
                //Only command with case "todo" ,"deadline", "event" and "DoAfter" should invoke this method.
                assert false : command;
        }
        tasks.changeListCount("+");
        storage.save(tasks);
        ui.echo(list.get(tasks.getListCount() - 1).toString(1), "added");
    }
    /**
     * Execute the "bye" command by displaying the bye message.
     */
    public void byeCommand (Ui ui){
        ui.showByeMessage();
    }
    /**
     * Compare given task's description with the description in the find command. Print if there is a match.
     *
     */
    public void compare(Task task) {
        if (task.getDescription().contains(description)){
            compareCount ++;
            Ui.echoMatch(compareCount + ": " + task.toString(1) );
        }
    }
    /**
     * Checker before adding command, scan through the task list for any existing duplicated task.
     *
     * @throws DukeException if any of the existing task is identical to the task to be added.
     */
    public static void isDuplicate (TaskList tasks) throws DukeException{
        ArrayList<Task> list = tasks.getList();
        for (int i = 0; i < tasks.getListCount(); i++) {
            switch (command) {
                case "todo":
                    if(list.get(i).getDescription().equals(description)){
                        throw new DukeException(Messages.MESSAGE_DUPLICATE_TASK);
                    }
                    break;
                case "deadline":
                    if(list.get(i).getDescription().equals(description)){
                        if (list.get(i).getDateAndTime(1).equals(dateAndTime1.format(TaskList.DISPLAY_FORMATTER))) {
                            throw new DukeException(Messages.MESSAGE_DUPLICATE_TASK);
                        }
                    }
                    break;
                case "event":
                    if(list.get(i).getDescription().equals(description)){
                        if (list.get(i).getDateAndTime(1).equals(dateAndTime1.format(TaskList.DISPLAY_FORMATTER)
                                + "-" + dateAndTime2.format(TaskList.DISPLAY_FORMATTER))) {
                            throw new DukeException(Messages.MESSAGE_DUPLICATE_TASK);
                        }
                    }
                    break;
                default:
                    //Only command with case "todo" ,"deadline" and "event" should invoke this method.
                    assert false : command;
                    return;
            }
        }
    }
}
