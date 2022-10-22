package seedu.nusduke.parser;

import seedu.nusduke.storage.Storage;
import seedu.nusduke.tasklist.*;
import seedu.nusduke.ui.Ui;
import seedu.nusduke.ui.Messages;
import seedu.nusduke.data.DukeException;

import java.util.ArrayList;

public class Command {
    static private String command;
    static private String description;
    static private String dateAndTime;
    static private int index;
    private boolean isEXit = false;

    public Command(String command){
        this.command = command;
    }

    public Command(boolean isExit){
        this.isEXit = isExit;
    }

    public Command(String command, int index) {
        this.command = command;
        this.index = index;
    }

    public Command(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public Command(String command, String description, String dateAndTime) {
        this.command = command;
        this.description = description;
        this.dateAndTime = dateAndTime;
    }

    public boolean isExit() {
        return this.isEXit;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> list = tasks.getList();
        switch (this.command) {
            case "bye":
                break;
            case "mark":
                markCommand(list);
                break;
            case "unmark":
                unMarkCommand(list);
                break;
            case "delete":
                deleteCommand(tasks, ui);
                break;
            case "todo":
            case "deadline":
            case "event":
                try {
                    addCommand(tasks, ui, storage);
                } catch (DukeException e){
                    throw new DukeException(e.getMessage());
                }
                break;
            default:
                ui.print(Messages.MESSAGE_NOT_A_TASK);

        }
    }

    public void markCommand(ArrayList<Task> list) {
        list.get(this.index).markAsDone();
    }

    public void unMarkCommand(ArrayList<Task> list) {
        list.get(this.index).markAsUndone();
    }

    public void deleteCommand(TaskList tasks, Ui ui) {
        ArrayList<Task> list = tasks.getList();
        String removedTask = list.get(this.index).toString();
        list.remove(this.index);
        tasks.changeListCount("-");
        ui.echo(removedTask, "removed");
    }

    public void addCommand (TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> list = tasks.getList();
        //check if the task to be added is existed
        if (tasks.getListCount() > 0) {
            try {
                isDuplicate(tasks, ui);
            } catch (DukeException e) {
                throw new DukeException(e.getMessage());
            }
        }
        switch (command) {
            case "todo":
                list.add(new Todo(description));
                break;
            case "deadline":
                list.add(new Deadline(description, dateAndTime));
                break;
            case "event":
                list.add(new Event(description, dateAndTime));
                break;
            default:
        }
        tasks.changeListCount("+");
        ui.echo(list.get(tasks.getListCount() - 1).toString(), "added");

    }

    public static void isDuplicate (TaskList tasks, Ui ui) throws DukeException{
        ArrayList<Task> list = tasks.getList();
        for (int i = 0; i < tasks.getListCount(); i++) {
            switch (command) {
                case "todo":
                    if(list.get(i).getDescription().equals(description)){
                        throw new DukeException(Messages.MESSAGE_DUPLICATE_TASK);
                    }
                    break;
                case "deadline":
                case "event":
                    if(list.get(i).getDescription().equals(description)){
                        if (list.get(i).getDateAndTime().equals(dateAndTime)) {
                            throw new DukeException(Messages.MESSAGE_DUPLICATE_TASK);
                        }
                    }
                    break;
                default:
                    return;
            }
        }
    }
}
