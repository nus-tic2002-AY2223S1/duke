package application.controllers;

import application.commands.*;
import application.helpers.CommonHelper;
import application.helpers.MessageConstants;
import domain.aggregates.storage.Storage;
import domain.aggregates.tracker.*;
import domain.exceptions.DukeExistedException;
import domain.exceptions.DukeFileException;
import domain.exceptions.DukeNotFoundException;
import domain.exceptions.DukeValidationException;

import java.text.ParseException;

public class RequestController {

    private Tracker tracker;
    private Storage storage;

    /**
     * Request controller default constructor
     * Requires Tracker and Storage
     */
    public RequestController(Tracker tracker, Storage storage) {
        this.tracker = tracker;
        this.storage = storage;
    }

    /**
     * List keyword behaviour: Calls View Task Command Handler
     */
    public void list() throws DukeValidationException, DukeFileException, DukeNotFoundException {
        viewTaskCommandHandler();
    }

    /**
     * Todo keyword behaviour: Calls Add Task Command Handler with new Task - Todo
     */
    public void todo(String input) throws DukeValidationException, DukeExistedException, DukeFileException {
        addTaskCommandHandler(new Todo(input));
    }

    /**
     * Event keyword behaviour: Calls Add Task Command Handler with new Task - Event
     */
    public void event(String input) throws DukeValidationException, DukeExistedException, DukeFileException {
        addTaskCommandHandler(new Event(input));
    }

    /**
     * Deadline keyword behaviour: Calls Add Task Command Handler with new Task - Deadline
     */
    public void deadline(String input) throws DukeValidationException, DukeExistedException, DukeFileException, ParseException {
        addTaskCommandHandler(new Deadline(input));
    }

    /**
     * Mark keyword behaviour: Calls Mark Task Command Handler with is done flag
     */
    public void mark(int input) throws DukeValidationException, DukeFileException, DukeNotFoundException {
        markTaskCommandHandler(input);
    }

    /**
     * Unmark keyword behaviour: Calls Unmark Task Command Handler with inverse is done flag
     */
    public void unmark(int input) throws DukeValidationException, DukeFileException, DukeNotFoundException {
        unmarkTaskCommandHandler(input);
    }

    /**
     * Delete keyword behaviour: Calls Delete Task Command Handler with Task ID
     */
    public void delete(int input) throws DukeValidationException, DukeFileException, DukeNotFoundException {
        deleteTaskCommandHandler(input);
    }

    /**
     * Filter keyword behaviour: Calls Filter Task By Dates Command Handler with date range in string
     */
    public void filter(String date) throws DukeValidationException {
        filterTaskByDatesCommandHandler(date);
    }

    /**
     * Snooze keyword behaviour: Calls Snooze Task with new date in string
     */
    public void snooze(int id, String date) throws DukeValidationException, DukeNotFoundException, DukeFileException {
        snoozeTaskCommandHandler(id, date);
    }

    /**
     * Hello keyword behaviour: Display Welcome message
     */
    public void hello(){
        CommonHelper.printMessage(MessageConstants.WELCOME);
    }

    /**
     * Bye keyword behaviour: Display End message
     */
    public void bye(){
        CommonHelper.printMessage(MessageConstants.END);
    }

    /**
     * Executes View Task Command Handler
     */
    private void viewTaskCommandHandler() throws DukeFileException, DukeValidationException, DukeNotFoundException {
        ViewTaskCommand command = new ViewTaskCommand(tracker, storage);
        command.execute();
    }

    /**
     * Executes Add Task Command Handler
     */
    private void addTaskCommandHandler(Task task) throws DukeExistedException, DukeFileException {
        AddTaskCommand command = new AddTaskCommand(tracker, storage, task);
        command.execute();
    }

    /**
     * Executes Mark Task Command Handler
     */
    private void markTaskCommandHandler(int id) throws DukeValidationException, DukeNotFoundException, DukeFileException {
        MarkTaskCommand command = new MarkTaskCommand(tracker, storage, id);
        command.execute();
    }

    /**
     * Executes Unmark Task Command Handler
     */
    private void unmarkTaskCommandHandler(int id) throws DukeValidationException, DukeNotFoundException, DukeFileException {
        UnmarkTaskCommand command = new UnmarkTaskCommand(tracker, storage, id);
        command.execute();
    }

    /**
     * Executes Delete Task Command Handler
     */
    private void deleteTaskCommandHandler(int id) throws DukeValidationException, DukeNotFoundException, DukeFileException {
        DeleteTaskCommand command = new DeleteTaskCommand(tracker, storage, id);
        command.execute();
    }

    /**
     * Executes Filter Task By Dates Command Handler
     */
    private void filterTaskByDatesCommandHandler(String date) throws DukeValidationException {
        FilterTaskByDatesCommand command = new FilterTaskByDatesCommand(tracker, storage, date);
        command.execute();
    }

    /**
     * Executes Snooze Task Command Handler
     */
    private void snoozeTaskCommandHandler(int id, String date) throws DukeValidationException, DukeNotFoundException, DukeFileException {
        SnoozeTaskCommand command = new SnoozeTaskCommand(tracker, storage, id, date);
        command.execute();
    }
}
