package service;

import entity.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private static final TaskManager taskManager = new TaskManager();

    public static TaskManager getInstance() {
        return taskManager;
    }

    private TaskManager() {}

    /**
     * store task data
     */
    private static final List<Task> taskList = new ArrayList<>();

    /**
     * @description init task list from hard disk data
     * @author Dex
     * @date 2022/08/29
     */
    public void initTask() {
        System.out.println("init task list ...");
    }

    /**
     * @description write task list data into hard disk
     * @author Dex
     * @date 2022/08/29
     */
    public void persistTask() {
        // System.out.println("persist all task data ...");
    }

    /**
     * @description append new task to task list
     * @author Dex
     * @date 2022/08/29
     * @param task: task entity
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * @description get task instance by given index
     * @author Dex
     * @date 2022/08/31
     * @param index: index of task in the list
     */
    public Task getTaskByIndex(int index) {
        return taskList.get(index);
    }

    /**
     * @description remove task from list by given index
     * @author Dex
     * @date 2022/08/29
     * @param index: index of task in the list
     */
    public void removeTask(int index) {
        // may have performance issue
        taskList.remove(index);
    }

    /**
     * @description get task size
     * @author Dex
     * @date 2022/08/31
     */
    public int getTaskSize() {
        return taskList.size();
    }

    /**
     * @description get task list instance, direct access of task list is not recommended.
     * for iteration purpose, include getIterator method (TODO)
     * @author Dex
     * @date 2022/08/31
     */
    public List<Task> getTaskList() {
        return taskList;
    }
}
