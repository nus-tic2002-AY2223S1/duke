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
        System.out.println("persist all task data ...");
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
     * @description remove task from list by given index
     * @author Dex
     * @date 2022/08/29
     * @param index: index of task in the list
     */
    public void removeTask(int index) {

    }

    /**
     * @description remove task from list by given search criteria
     * @author Dex
     * @date 2022/08/29
     */
    public void removeTask() {

    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
