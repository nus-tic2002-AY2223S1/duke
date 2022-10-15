package Domain.Aggregates.Tracker;

import Application.Helpers.CommonHelper;
import Application.Helpers.MessageConstants;
import Domain.Exceptions.DukeExistedException;
import Domain.Exceptions.DukeNotFoundException;
import Domain.Exceptions.DukeValidationException;

import java.util.ArrayList;

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

    public void addItem(String itemName, Task task) throws DukeExistedException {
        if(!tasks.stream().filter(x -> x.getName() == task.getName()).findFirst().isEmpty())
            throw new DukeExistedException(MessageConstants.TASK_EXISTED_ERROR);
        CommonHelper.printMessage(MessageConstants.ADD_TASK);
        tasks.add(task);
        Integer n = tasks.size();
        task.printItem(n);
        CommonHelper.printMessage(String.format(MessageConstants.SUM_TASK, n));
    }

    public void showList(){
        if(tasks.size() > 0) {
            CommonHelper.printMessage(MessageConstants.LIST_TASK);
            printList();
        } else {
            CommonHelper.printMessage(MessageConstants.NO_TASK);
        }
    }

    public void updateDoneState(int n, boolean isDone) throws DukeValidationException, DukeNotFoundException {
        if(n < 0)
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Index"));
        else if(n == 0 || n > tasks.size())
            throw new DukeValidationException(MessageConstants.TASK_VALIDATION_SIZE_ERROR);
        Task task = tasks.get(n-1);
        if(task == null)
            throw new DukeNotFoundException(MessageConstants.TASK_NOT_FOUND_ERROR);
        task.setIsDone(isDone);
        if (isDone) {
            CommonHelper.printMessage(MessageConstants.MARK_TASK);
        } else {
            CommonHelper.printMessage(MessageConstants.UNMARK_TASK);
        }
        task.printItem(n);
    }
}
