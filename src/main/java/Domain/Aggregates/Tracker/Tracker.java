package Domain.Aggregates.Tracker;

import Application.Helpers.CommonHelper;
import Application.Helpers.MessageConstants;
import Domain.Exceptions.DukeExistedException;
import Domain.Exceptions.DukeFileException;
import Domain.Exceptions.DukeNotFoundException;
import Domain.Exceptions.DukeValidationException;
import Domain.Repositories.ITaskRepository;
import Domain.Repositories.TaskRepository;

import java.util.ArrayList;

public class Tracker {
    public ArrayList<Task> tasks;
    private final ITaskRepository _taskRepository;

    public Tracker() throws DukeFileException {
        _taskRepository = new TaskRepository();
        this.tasks = _taskRepository.convertToTaskList();
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

    public boolean addItem(Task task) throws DukeExistedException {
        _taskRepository.validateTask(tasks, task);
        task.setId(_taskRepository.getLastId(tasks) + 1);
        if(tasks.add(task)) {
            CommonHelper.printMessage(MessageConstants.ADD_TASK);
            printTask(task);
            return true;
        } else {
            CommonHelper.printMessage(MessageConstants.GENERAL_ERROR);
            return false;
        }
    }

    public boolean updateItem(int n, boolean isDone) throws DukeValidationException, DukeNotFoundException {
        Task task = _taskRepository.validateTask(tasks, n);
        if(task != null) {
            task.setIsDone(isDone);
            if (isDone) {
                CommonHelper.printMessage(MessageConstants.MARK_TASK);
            } else {
                CommonHelper.printMessage(MessageConstants.UNMARK_TASK);
            }
            task.printItem();
            return true;
        }
        return false;
    }

    public boolean deleteItem(int n) throws DukeValidationException, DukeNotFoundException {
        Task task = _taskRepository.validateTask(tasks, n);
        if(task != null) {
            if (tasks.remove(task)) {
                CommonHelper.printMessage(MessageConstants.DELETE_TASK);
                printTask(task);
                return true;
            } else {
                CommonHelper.printMessage(MessageConstants.GENERAL_ERROR);
                return false;
            }
        }
        return false;
    }
}
