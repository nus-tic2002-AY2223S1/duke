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
    public RequestController(Tracker tracker, Storage storage) {
        this.tracker = tracker;
        this.storage = storage;
    }

    public void list() throws DukeValidationException, DukeFileException, DukeNotFoundException {
        viewTaskCommandHandler();
    }

    public void todo(String input) throws DukeValidationException, DukeExistedException, DukeFileException {
        addTaskCommandHandler(new Todo(input));
    }

    public void event(String input) throws DukeValidationException, DukeExistedException, DukeFileException {
        addTaskCommandHandler(new Event(input));
    }

    public void deadline(String input) throws DukeValidationException, DukeExistedException, DukeFileException, ParseException {
        addTaskCommandHandler(new Deadline(input));
    }

    public void mark(int input) throws DukeValidationException, DukeFileException, DukeNotFoundException {
        markTaskCommandHandler(input);
    }

    public void unmark(int input) throws DukeValidationException, DukeFileException, DukeNotFoundException {
        unmarkTaskCommandHandler(input);
    }

    public void delete(int input) throws DukeValidationException, DukeFileException, DukeNotFoundException {
        deleteTaskCommandHandler(input);
    }

    public void filter(String date) throws DukeValidationException {
        filterTaskByDatesCommandHandler(date);
    }

    public void hello(){
        CommonHelper.printMessage(MessageConstants.WELCOME);
    }

    public void bye(){
        CommonHelper.printMessage(MessageConstants.END);
        System.exit(1);
    }

    private void viewTaskCommandHandler() throws DukeFileException, DukeValidationException, DukeNotFoundException {
        ViewTaskCommand command = new ViewTaskCommand(tracker, storage);
        command.execute();
    }

    private void addTaskCommandHandler(Task task) throws DukeExistedException, DukeFileException {
        AddTaskCommand command = new AddTaskCommand(tracker, storage, task);
        command.execute();
    }

    private void markTaskCommandHandler(int id) throws DukeValidationException, DukeNotFoundException, DukeFileException {
        MarkTaskCommand command = new MarkTaskCommand(tracker, storage, id);
        command.execute();
    }

    private void unmarkTaskCommandHandler(int id) throws DukeValidationException, DukeNotFoundException, DukeFileException {
        UnmarkTaskCommand command = new UnmarkTaskCommand(tracker, storage, id);
        command.execute();
    }

    private void deleteTaskCommandHandler(int id) throws DukeValidationException, DukeNotFoundException, DukeFileException {
        DeleteTaskCommand command = new DeleteTaskCommand(tracker, storage, id);
        command.execute();
    }
    
    private void filterTaskByDatesCommandHandler(String date) throws DukeValidationException {
        FilterTaskByDatesCommand command = new FilterTaskByDatesCommand(tracker, storage, date);
        command.execute();
    }
}
