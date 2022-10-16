package Domain.Repositories;

import Domain.Aggregates.Tracker.Task;
import Domain.Exceptions.DukeExistedException;
import Domain.Exceptions.DukeNotFoundException;
import Domain.Exceptions.DukeValidationException;

import java.util.ArrayList;
import java.util.Optional;

public interface ITaskRepository {
    public Optional<Task> getItem(ArrayList<Task> tasks, int n);
    public Optional<Task> getItem(ArrayList<Task> tasks, Task task);
    public int getLastId(ArrayList<Task> tasks);
    public Task validateTask(ArrayList<Task> tasks, int n) throws DukeNotFoundException, DukeValidationException;
    public void validateTask(ArrayList<Task> tasks, Task task) throws DukeExistedException;
}
