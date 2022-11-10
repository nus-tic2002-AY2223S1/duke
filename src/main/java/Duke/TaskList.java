package Duke;

import Duke.Tasks.Task;

import java.util.ArrayList;

/**
 * Represents the Task List, contains the data of Task.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /** Constructs a task list with task. */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /** Adds a task to the task list. */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /** Get size of task list. */
    public int getSize() {
        return taskList.size();
    }


    /** Remove a task from task list. */
    public void removeTask(Task task) {
        taskList.remove(task);
    }

    public Task getTask(int selection){
        return taskList.get(selection);
    }
    public ArrayList<Task> getListOfTask(){
        return taskList;
    }
}
