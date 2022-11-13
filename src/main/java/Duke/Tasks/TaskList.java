package Duke.Tasks;

import java.util.ArrayList;

/**
 * Represents the Task List, contains the data of Task.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     *  Constructor of TaskList
     *
     * @param taskList is an ArrayList of task to store task
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }


    /**
     *  To add a task to the TaskList
     *
     * @param task to be added to the TaskList
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * To get size of TaskList
     *
     * @return an Int which contains the size of TaskList
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     *  To remove a task from TaskList
     *
     * @param task to be removed from the TaskList
     */
    public void removeTask(Task task) {
        taskList.remove(task);
    }

    /**
     *  To retrieve a task from TaskList base on position
     *
     * @param selection is the position of task to be retrieved from the TaskList
     * @return the Task which is retrieved from the TaskList
     */
    public Task getTask(int selection){
        return taskList.get(selection);
    }

    /**
     *  To retrieve all task from TaskList
     *
     * @return an ArrayList which contains all Tasks from the TaskList
     */
    public ArrayList<Task> getListOfTask(){
        return taskList;
    }
}
