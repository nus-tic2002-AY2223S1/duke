package duke.tasks;

import java.util.ArrayList;
import java.util.List;

import duke.ui.Ui;

public class TaskList {

    protected static List<Task> taskLists;
    protected int taskId = 0;

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
        taskId++;
        type.setTaskNo(taskId);
        taskLists.add(type);
    }

    public void markT(int id, String key) {
        if (taskLists.get(id).isDone) {
            System.out.println(Ui.trueMark(key));
        } else {
            assert taskLists.get(id).description.charAt(4) == ' ';
            taskLists.get(id).mark();
            taskLists.get(id).description.replace(4, 5, "X");
            Ui.markTask(id, taskLists);      
        } 
    }

    public void unmarkT(int id, String key) {
        if (!taskLists.get(id).isDone) {
            System.out.println(Ui.trueMark(key));
        } else {
            assert taskLists.get(id).description.charAt(4) == 'X';
            taskLists.get(id).unmark();
            taskLists.get(id).description.replace(4, 5, " ");
            Ui.unmarkTask(id, taskLists);
        }
    }
    
    public void deleteTask(int id) {
        assert getCount() != 0;
        StringBuilder selection = new StringBuilder(taskLists.get(id-1).description);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(selection.toString());
        taskLists.remove(id-1);
        taskId--;
        //resets the numbering for tasks
        for (int i = 0; i < getCount(); i++) {
            taskLists.get(i).setTaskNo(i+1);
        }
        System.out.println("Now you have " + getCount() + " tasks in the list.\n");
    }

    public boolean checkListNo(String keyValue) {
        if(keyValue.isEmpty() || keyValue.equals("0") || Integer.parseInt(keyValue) > getCount()) {
            return true;
        } else {
            return false;
        }
    }
}