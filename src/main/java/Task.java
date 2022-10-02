import Helpers.CommonHelper;
import Helpers.MessageConstants;

import java.util.ArrayList;

public class Task {
    public ArrayList<Item> items;

    public Task(){
        items = new ArrayList<>(100);
    }

    private void printList(){
        for(int i = 0; i < items.size(); i++){
            printItem(i+1, items.get(i));
        }
    }

    private void printItem(Integer n, Item i){
        String t = i.getType().name();
        String displayText = String.format("\t\t%d.[%s][%s] %s %s", n, t, i.getIsDone() ? "X":" ", i.getName(), t.equals("T") ? "" : "(" + (t.equals("E") ? "at: " : "by: ") + i.getTime() + ")");
        CommonHelper.printMessage(displayText);
    }

    public void addItem(String itemName, TaskTypeEnumeration.TaskType type){
        CommonHelper.printMessage(MessageConstants.ADD_TASK);
        Item item = new Item(itemName, type);
        items.add(item);
        Integer n = items.size();
        printItem(n, item);
        CommonHelper.printMessage(String.format(MessageConstants.SUM_TASK, n));
    }

    public void showList(){
        if(items.size() > 0) {
            CommonHelper.printMessage(MessageConstants.LIST_TASK);
            printList();
        } else {
            CommonHelper.printMessage(MessageConstants.NO_TASK);
        }
    }

    public void updateDoneState(int n, boolean isDone){
        if(n > 0 && n <= items.size()) {
            Item item = items.get(n-1);
            item.setIsDone(isDone);
            if (isDone) {
                CommonHelper.printMessage(MessageConstants.MARK_TASK);
            } else {
                CommonHelper.printMessage(MessageConstants.UNMARK_TASK);
            }
            printItem(n, item);
        } else {
            CommonHelper.printMessage(MessageConstants.TASK_NOT_FOUND);
            printList();
        }
    }
}
