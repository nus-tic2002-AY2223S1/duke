package parser;

import exceptions.DukeException;
import storage.Storage;
import tasklist.Tasklist;
import ui.Ui;

import javax.naming.SizeLimitExceededException;
import java.io.IOException;


public class Command {

    public String command;
    private String description;
    private String dateTime;
    private int index;
    private Character type;
    private boolean isExit = false;

    /**
     * Represents a command entered by the user.
     *
     * @param command Action required for the comment eg. List, delete, mark
     * @param description Description of the action/task eg. Read book
     * @param type Task type eg. D for deadline, E for event
     * @param datetime Datetime associated for time sensitive tasks eg. deadline due by 22Nov22
     * @param index Order of task to be actioned on among the tasklist
     * @throws SizeLimitExceededException If tasklist holds max number of tasks (Limit: 100 tasks)
     * @throws IllegalArgumentException When entered command action is not a recognised action
     * @throws IOException When there are missing arguments for the command
     */

    /** Create command with command */
    public Command (String command) {
        this.command = command;
    }

    /** Create command with command - des */
    public Command (String command, String description){
        this.command = command;
        this.description = description;
    }

    /** Create command with command - des - type */
    public Command (String command, String description, Character type){
        this.command = command;
        this.description = description;
        this.type = type;
    }

    /** Create command with command - des - datetime - type */
    public Command (String command, String description, String datetime, Character type){
        this.command = command;
        this.description = description;
        this.dateTime = datetime;
        this.type = type;
    }

    /** Create command with command - index */
    public Command (String command, int index){
        this.command = command;
        this.index = index;
    }

    /** Create command with command - des - datetime */
    public Command (String command, String description, String datetime){
        this.command = command;
        this.description = description;
        this.dateTime = datetime;
    }

    /** Create command with command - index - datetime */
    public Command (String command, int index, String datetime){
        this.command = command;
        this.index = index;
        this.dateTime = datetime;
    }

    public void execute (Tasklist tasklist, Ui ui) throws DukeException {

        try {
            if (Tasklist.getListcount() == 100) {
                throw new SizeLimitExceededException();
            }

            switch (this.command.trim().toLowerCase()) {
                case "bye" -> byeCommand(ui);
                case "list" -> listCommand(tasklist);
                case "todo" -> todoCommand(tasklist, this.description);
                case "event" -> eventCommand(tasklist, this.description, this.dateTime);
                case "deadline" -> deadlineCommand(tasklist, this.description, this.dateTime);
                case "mark" -> markCommand(tasklist, this.index - 1);
                case "unmark" -> unmarkCommand(tasklist, this.index - 1);
                case "delete" -> deleteCommand(tasklist, this.index);
                case "find" -> findCommand(tasklist, this.description);
                case "due" -> dueCommand(tasklist, this.description);
                case "postpone" -> postponeCommand(tasklist, this.index - 1, this.dateTime);
                default -> throw new IllegalArgumentException();
            }

        } catch (SizeLimitExceededException e) {
            System.out.println(Ui.ERROR_MAXTASKLISTSIZE);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(Ui.ERROR_INVALID_COMMAND + "\n" + Ui.UI_DIVIDER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void byeCommand(Ui ui) throws IOException {
        ui.showGoodbye();
        Storage.saveFile();
        this.isExit = true;
    }

    public boolean isExit(){
        return this.isExit;
    }
    public String getCommand(){
        return this.command;
    }
    public String getDescription(){
        return this.description;
    }
    public int getIndex(){
        return this.index;
    }
    public String getDateTime(){
        return this.dateTime;
    }
    public char getType(){
        return this.type;
    }

    public static void listCommand(Tasklist tasklist){
        tasklist.printTasklist();
    }

    public static void todoCommand(Tasklist tasklist, String description) throws IOException {
        tasklist.addTodo(description);
        Storage.saveFile();
    }

    public static void eventCommand(Tasklist tasklist, String description, String at) throws IOException {
        tasklist.addEvent(description, at);
        Storage.saveFile();
    }

    public static void deadlineCommand(Tasklist tasklist, String description, String by) throws IOException {
        tasklist.addDeadline(description, by);
        Storage.saveFile();
    }

    public static void markCommand(Tasklist tasklist, int index) throws IOException {
        tasklist.markDone(index);
        Storage.saveFile();
    }

    public static void unmarkCommand(Tasklist tasklist, int index) throws IOException {
        tasklist.unmarkDone(index);
        Storage.saveFile();
    }

    public static void deleteCommand(Tasklist tasklist, int index) throws IOException {
        tasklist.removeTask(index-1);
        Storage.saveFile();
    }

    public static void findCommand(Tasklist tasklist, String description){
        tasklist.findTasklist(description);
    }

    public static void dueCommand(Tasklist tasklist, String description){
        tasklist.dueTasklist(description);
    }

    public static void postponeCommand(Tasklist tasklist, int index, String to) throws IOException {
        tasklist.postponeTask(index, to);
        Storage.saveFile();
    }

}