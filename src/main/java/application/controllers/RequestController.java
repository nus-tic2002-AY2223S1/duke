package application.controllers;

import application.commands.AddTaskCommand;
import application.commands.DeleteTaskCommand;
import application.commands.FilterTaskByDatesCommand;
import application.commands.FindTaskCommand;
import application.commands.MarkTaskCommand;
import application.commands.UnmarkTaskCommand;
import application.commands.SnoozeTaskCommand;
import application.commands.ViewTaskCommand;
import application.helpers.CommonHelper;
import application.helpers.MessageConstants;
import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.Tracker;
import domain.aggregates.tracker.Task;
import domain.aggregates.tracker.Todo;
import domain.aggregates.tracker.Event;
import domain.aggregates.tracker.Deadline;
import domain.exceptions.DukeExistedException;
import domain.exceptions.DukeFileException;
import domain.exceptions.DukeNotFoundException;
import domain.exceptions.DukeValidationException;
import domain.exceptions.DukeArgumentException;

public class RequestController {

    private Tracker tracker;
    private Storage storage;

    /**
     * Initialises RequestController which is bridge between UI and Domain communication.
     *
     * @param tracker Tracker.
     * @param storage Storage.
     */
    public RequestController(Tracker tracker, Storage storage) {
        this.tracker = tracker;
        this.storage = storage;
    }

    /**
     * Calls ViewTaskCommand to display tasks.
     */
    public void list() {
        ViewTaskCommand command = new ViewTaskCommand(tracker, storage);
        command.execute();
    }

    /**
     * Creates new Todo based on the input specified and calls addTaskCommand private method.
     *
     * @param input String.
     * @throws DukeExistedException if adds task that already exists.
     * @throws DukeValidationException if required task properties are empty.
     * @throws DukeFileException if unable to save changes to local file.
     */
    public void todo(String input) throws DukeValidationException, DukeExistedException, DukeFileException {
        addTaskCommand(new Todo(input));
    }

    /**
     * Creates new Event based on the input specified and calls addTaskCommand private method.
     *
     * @param input String.
     * @throws DukeExistedException if adds task that already exists.
     * @throws DukeValidationException if required task properties are empty.
     * @throws DukeFileException if unable to save changes to local file.
     * @throws DukeArgumentException if invalid arguments passed.
     */
    public void event(String input) throws DukeValidationException, DukeExistedException, DukeFileException, DukeArgumentException {
        addTaskCommand(new Event(input));
    }

    /**
     * Creates new Deadline based on the input specified and calls addTaskCommand private method.
     *
     * @param input String.
     * @throws DukeExistedException if adds task that already exists.
     * @throws DukeValidationException if required task properties are empty.
     * @throws DukeFileException if unable to save changes to local file.
     * @throws DukeArgumentException if invalid arguments passed.
     */
    public void deadline(String input) throws DukeValidationException, DukeExistedException, DukeFileException, DukeArgumentException {
        addTaskCommand(new Deadline(input));
    }

    /**
     * Calls Mark Task Command to set done to true.
     *
     * @param id Integer.
     * @throws DukeNotFoundException if modifies a task that does not exist.
     * @throws DukeArgumentException if invalid arguments passed.
     * @throws DukeFileException if unable to save changes to local file.
     */
    public void mark(int id) throws DukeArgumentException, DukeFileException, DukeNotFoundException {
        MarkTaskCommand command = new MarkTaskCommand(tracker, storage, id);
        command.execute();
    }

    /**
     * Calls Unmark Task Command to set done to false.
     *
     * @param id Integer.
     * @throws DukeNotFoundException if modifies a task that does not exist.
     * @throws DukeArgumentException if invalid arguments passed.
     * @throws DukeFileException if unable to save changes to local file.
     */
    public void unmark(int id) throws DukeArgumentException, DukeFileException, DukeNotFoundException {
        UnmarkTaskCommand command = new UnmarkTaskCommand(tracker, storage, id);
        command.execute();
    }

    /**
     * Calls Delete Task Command to delete task.
     *
     * @param id Integer.
     * @throws DukeNotFoundException if modifies a task that does not exist.
     * @throws DukeArgumentException if invalid arguments passed.
     * @throws DukeFileException if unable to save changes to local file.
     */
    public void delete(int id) throws DukeArgumentException, DukeFileException, DukeNotFoundException {
        DeleteTaskCommand command = new DeleteTaskCommand(tracker, storage, id);
        command.execute();
    }

    /**
     * Calls Filter Task Command with the date range that is passed.
     *
     * @param date String.
     * @throws DukeArgumentException if invalid arguments passed.
     */
    public void filter(String date) throws DukeArgumentException {
        FilterTaskByDatesCommand command = new FilterTaskByDatesCommand(tracker, storage, date);
        command.execute();
    }

    /**
     * Calls Snooze Task Command with new date.
     * If date is specified, it will be passed as a property to assist in validation and condition to apply default snooze.
     *
     * @param id Integer.
     * @param date String.
     * @param isDateSpecified Boolean.
     * @throws DukeNotFoundException if modifies a task that does not exist.
     * @throws DukeValidationException if date is empty.
     * @throws DukeFileException if unable to save changes to local file.
     * @throws DukeArgumentException if invalid arguments passed.
     */
    public void snooze(int id, String date, boolean isDateSpecified) throws DukeValidationException, DukeNotFoundException, DukeFileException, DukeArgumentException {
        SnoozeTaskCommand command = new SnoozeTaskCommand(tracker, storage, id, date, isDateSpecified);
        command.execute();
    }

    /**
     * Calls Find Task Command with keyword.
     * Checks if keyword matches either the name or date or dateTime.
     *
     * @param keyword String.
     * @throws DukeValidationException if keyword is empty.
     */
    public void find(String keyword) throws DukeValidationException {
        FindTaskCommand command = new FindTaskCommand(tracker, storage, keyword);
        command.execute();
    }

    /**
     * Displays welcome message.
     */
    public void hello(){
        CommonHelper.printMessage(MessageConstants.WELCOME);
    }

    /**
     * Displays goodbye message.
     */
    public void bye(){
        CommonHelper.printMessage(MessageConstants.END);
    }

    /**
     * Calls AddTaskCommand to add new task.
     *
     * @throws DukeExistedException if adds task that already exists.
     * @throws DukeFileException if unable to save changes to local file.
     */
    private void addTaskCommand(Task task) throws DukeExistedException, DukeFileException {
        AddTaskCommand command = new AddTaskCommand(tracker, storage, task);
        command.execute();
    }
}
