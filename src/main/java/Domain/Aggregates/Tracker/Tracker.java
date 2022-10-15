package Domain.Aggregates.Tracker;

import Application.Helpers.CommonHelper;
import Application.Helpers.MessageConstants;
import Domain.Exceptions.DukeExistedException;
import Domain.Exceptions.DukeNotFoundException;
import Domain.Exceptions.DukeValidationException;

import java.util.ArrayList;
import java.util.Optional;

public class Tracker {
    public ArrayList<Task> tasks;

    public Tracker(){
        tasks = new ArrayList<>(100);
    }

    private void printList(){
        for(int i = 0; i < tasks.size(); i++){
            tasks.get(i).printItem(i+1);
        }
    }

    private Task getItem(int n){
        return tasks.get(n-1);
    }

    private Optional<Task> getItem(Task task){
        return tasks.stream().filter(x -> x.equals(task)).findFirst();
    }

    private Task validateTask(int n) throws DukeNotFoundException, DukeValidationException {
        if(n < 0)
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Index"));
        else if(n == 0 || n > tasks.size())
            throw new DukeValidationException(MessageConstants.TASK_VALIDATION_SIZE_ERROR);
        Task task = getItem(n);
        if(task == null)
            throw new DukeNotFoundException(MessageConstants.TASK_NOT_FOUND_ERROR);
        return task;
    }

    private void validateTask(Task task) throws DukeExistedException {
        if(!getItem(task).isEmpty())
            throw new DukeExistedException(MessageConstants.TASK_EXISTED_ERROR);
    }

    private void printTask(Task task, int n){
        task.printItem(n);
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

    public void addItem(Task task) throws DukeExistedException {
        validateTask(task);
        if(tasks.add(task)) {
            CommonHelper.printMessage(MessageConstants.ADD_TASK);
            printTask(task, tasks.size());
        } else {
            CommonHelper.printMessage(MessageConstants.GENERAL_ERROR);
        }
    }


    public void updateItem(int n, boolean isDone) throws DukeValidationException, DukeNotFoundException {
        Task task = validateTask(n);
        task.setIsDone(isDone);
        if (isDone) {
            CommonHelper.printMessage(MessageConstants.MARK_TASK);
        } else {
            CommonHelper.printMessage(MessageConstants.UNMARK_TASK);
        }
        task.printItem(n);
    }

    public void deleteItem(int n) throws DukeValidationException, DukeNotFoundException {
        Task task = validateTask(n);
        if(tasks.remove(task)) {
            CommonHelper.printMessage(MessageConstants.DELETE_TASK);
            printTask(task, n);
        } else {
            CommonHelper.printMessage(MessageConstants.GENERAL_ERROR);
        }
    }
}
