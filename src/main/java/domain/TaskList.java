package domain;

import domain.task.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
         taskList = new ArrayList<>();
     }

    public TaskList(Task task) {
        taskList = Collections.singletonList(task);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void addTask(Task task) {
         taskList.add(task);
    }

    public void removeTask(int index) {
        taskList.remove(index);
    }

    public Task getTask(int index) throws IndexOutOfBoundsException {
        return taskList.get(index);
    }

    public int getTaskCount() {
        return taskList.size();
    }
}
