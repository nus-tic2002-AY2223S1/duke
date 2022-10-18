package Application.Controllers;

import Application.Commands.*;
import Application.Helpers.CommonHelper;
import Application.Helpers.MessageConstants;
import Domain.Aggregates.Storage.Storage;
import Domain.Aggregates.Tracker.*;
import Domain.Exceptions.DukeExistedException;
import Domain.Exceptions.DukeFileException;
import Domain.Exceptions.DukeNotFoundException;
import Domain.Exceptions.DukeValidationException;

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

    public void deadline(String input) throws DukeValidationException, DukeExistedException, DukeFileException {
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
}
