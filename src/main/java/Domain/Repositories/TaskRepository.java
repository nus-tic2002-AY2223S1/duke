package Domain.Repositories;

import Application.Helpers.MessageConstants;
import Application.Helpers.StorageConstants;
import Domain.Aggregates.Tracker.*;
import Domain.Exceptions.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

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


    public ArrayList<Task> convertToTaskList() throws DukeFileException {
        ArrayList<Task> tasks = new ArrayList<>(100);
        try {
            Scanner scnr = new Scanner(new FileReader(StorageConstants.FILE_PATH));
            String[] str;
            scnr.nextLine();
            while (scnr.hasNext()) {
                str = scnr.nextLine().split(" \\| ");
                Task task = null;
                switch (TaskType.valueOf(str[1])) {
                    case T:
                        task = new Todo(Integer.valueOf(str[0]), str[3], Boolean.valueOf(str[2]));
                        break;
                    case E:
                        task = new Event(Integer.valueOf(str[0]), str[3], str[4], Boolean.valueOf(str[2]));
                        break;
                    case D:
                        task = new Deadline(Integer.valueOf(str[0]), str[3], str[4], Boolean.valueOf(str[2]));
                        break;
                }
                tasks.add(task);
            }
            scnr.close();
        } catch (IOException ex){
            throw new DukeFileException(MessageConstants.TASK_GET_ERROR);
        }
        return tasks;
    }
}
