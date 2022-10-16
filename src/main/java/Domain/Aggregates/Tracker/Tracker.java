package Domain.Aggregates.Tracker;

import Application.Helpers.CommonHelper;
import Application.Helpers.MessageConstants;
import Domain.Exceptions.DukeExistedException;
import Domain.Exceptions.DukeFileException;
import Domain.Exceptions.DukeNotFoundException;
import Domain.Exceptions.DukeValidationException;
import Domain.Repositories.IStorageRepository;
import Domain.Repositories.ITaskRepository;
import Domain.Repositories.StorageRepository;
import Domain.Repositories.TaskRepository;

import java.io.File;
import java.util.ArrayList;

public class Tracker {
    public ArrayList<Task> tasks;
    private final ITaskRepository _taskRepository;

    public Tracker(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(100);
        _taskRepository = new TaskRepository();
        this.tasks = tasks;
    }

    private void printList(){
        for(int i = 0; i < tasks.size(); i++){
            tasks.get(i).printItem();
        }
    }

    private void printTask(Task task){
        task.printItem();
        CommonHelper.printMessage(String.format(MessageConstants.SUM_TASK, tasks.size()));
    }

    public void showList(){
        if(tasks.size() > 0) {
            CommonHelper.printMessage(MessageConstants.LIST_TASK);
            printList();
        } else {
            CommonHelper.printMessage(MessageConstants.NO_TASK);
        }
    }

    public void addItem(Task task) throws DukeExistedException, DukeFileException {
        _taskRepository.validateTask(task);
        task.setId(_taskRepository.getLastId(tasks) + 1);
        if(tasks.add(task)) {
            _storageRepository.write(file, task.toString());
            CommonHelper.printMessage(MessageConstants.ADD_TASK);
            printTask(task);
        } else {
            CommonHelper.printMessage(MessageConstants.GENERAL_ERROR);
        }
    }

    public void updateItem(int n, boolean isDone) throws DukeValidationException, DukeNotFoundException, DukeFileException {
        Task task = _taskRepository.validateTask(n);
        task.setIsDone(isDone);
        _storageRepository.override(tasks);
        if (isDone) {
            CommonHelper.printMessage(MessageConstants.MARK_TASK);
        } else {
            CommonHelper.printMessage(MessageConstants.UNMARK_TASK);
        }
        task.printItem();
    }

    public void deleteItem(int n) throws DukeValidationException, DukeNotFoundException, DukeFileException {
        Task task = _taskRepository.validateTask(n);
        if(tasks.remove(task)) {
            _storageRepository.override(tasks);
            CommonHelper.printMessage(MessageConstants.DELETE_TASK);
            printTask(task);
        } else {
            CommonHelper.printMessage(MessageConstants.GENERAL_ERROR);
        }
    }
}
