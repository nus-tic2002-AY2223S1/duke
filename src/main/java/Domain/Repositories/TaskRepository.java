package Domain.Repositories;

import Application.Helpers.MessageConstants;
import Domain.Aggregates.Tracker.Task;
import Domain.Aggregates.Tracker.Tracker;
import Domain.Exceptions.DukeArgumentException;
import Domain.Exceptions.DukeExistedException;
import Domain.Exceptions.DukeNotFoundException;
import Domain.Exceptions.DukeValidationException;

import java.util.ArrayList;
import java.util.Optional;

public class TaskRepository implements ITaskRepository {

    public TaskRepository() {
    }

    public Optional<Task> getItem(ArrayList<Task> tasks, int n){
        return tasks.stream().filter(x -> x.getId() == n).findFirst();
    }

    public Optional<Task> getItem(ArrayList<Task> tasks, Task task){
        return tasks.stream().filter(x -> x.equals(task)).findFirst();
    }

    public int getLastId(ArrayList<Task> tasks){
        if(tasks.size() == 0)
            return 0;
        return tasks.get(tasks.size() - 1).getId();
    }

    public Task validateTask(ArrayList<Task> tasks, int n) throws DukeNotFoundException, DukeValidationException {
        if(n < 0)
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Id"));
        else if(n == 0 || n > getLastId(tasks))
            throw new DukeValidationException(MessageConstants.TASK_VALIDATION_SIZE_ERROR);
        Optional<Task> task = getItem(tasks, n);
        if(task.isEmpty())
            throw new DukeNotFoundException(MessageConstants.TASK_NOT_FOUND_ERROR);
        return task.get();
    }

    public void validateTask(ArrayList<Task> tasks, Task task) throws DukeExistedException {
        if(!getItem(tasks, task).isEmpty())
            throw new DukeExistedException(MessageConstants.TASK_EXISTED_ERROR);
    }
}
