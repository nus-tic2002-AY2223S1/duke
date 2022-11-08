package domain.repositories;

import domain.aggregates.tracker.Task;
import domain.exceptions.DukeExistedException;
import domain.exceptions.DukeFileException;
import domain.exceptions.DukeNotFoundException;
import domain.exceptions.DukeValidationException;
import domain.exceptions.DukeArgumentException;

import java.util.ArrayList;
import java.util.Optional;

public interface ITaskRepository {
    public Optional<Task> getItem(ArrayList<Task> tasks, int n);
    public Optional<Task> getItem(ArrayList<Task> tasks, Task task);
    public int getLastId(ArrayList<Task> tasks);
    public Task validateTask(ArrayList<Task> tasks, int n) throws DukeNotFoundException, DukeValidationException;
    public void validateTask(ArrayList<Task> tasks, Task task) throws DukeExistedException;

    public ArrayList<Task> convertToTaskList() throws DukeFileException, DukeArgumentException;
}
