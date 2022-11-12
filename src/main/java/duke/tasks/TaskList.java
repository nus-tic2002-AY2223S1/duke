package duke.tasks;

import java.util.ArrayList;
import java.util.List;

import duke.ui.Ui;

public class TaskList {
    protected static List<Task> taskLists;
    protected static int taskId = 1;

    public TaskList() {
        taskLists = new ArrayList<Task>();
    }

    public int getCount(){
        return taskLists.size();
    }

    public int getTaskId(){
        return taskId;
    }

    public List<Task> getList() {
        return taskLists;
    }

    public void addT(Task type) {
        taskLists.add(type);
        taskId++;
    }

    public void markT(int id, String key) {
        if (taskLists.get(id).isDone) {
            System.out.println(Ui.trueMark(key));
        } else {
            taskLists.get(id).mark();
            taskLists.get(id).description.replace(4, 5, "X");
            Ui.markTask(id, taskLists);      
        } 
    }

    public void unmarkT(int id, String key) {
        if (!taskLists.get(id).isDone) {
            System.out.println(Ui.trueMark(key));
        } else {
            taskLists.get(id).unmark();
            taskLists.get(id).description.replace(4, 5, " ");
            Ui.unmarkTask(id, taskLists);
        }
    }

    public void deleteTask(int id) {
        StringBuilder selection = new StringBuilder(taskLists.get(id-1).description);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(selection.toString());
        taskLists.remove(id-1);
        System.out.println("Now you have " + getCount() + " tasks in the list.");
    }

    public boolean checkListNo(String keyValue) {
        if(keyValue.isEmpty() || keyValue.equals("0") || Integer.parseInt(keyValue) > getCount()) {
            return true;
        } else {
            return false;
        }
    }
}