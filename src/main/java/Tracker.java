import Helpers.CommonHelper;
import Helpers.MessageConstants;

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

    public void addItem(String itemName, Task task){
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

    public void updateDoneState(int n, boolean isDone){
        if(n > 0 && n <= tasks.size()) {
            Task task = tasks.get(n-1);
            task.setIsDone(isDone);
            if (isDone) {
                CommonHelper.printMessage(MessageConstants.MARK_TASK);
            } else {
                CommonHelper.printMessage(MessageConstants.UNMARK_TASK);
            }
            task.printItem(n);
        } else {
            CommonHelper.printMessage(MessageConstants.TASK_NOT_FOUND);
            printList();
        }
    }
}
