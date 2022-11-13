package domain;

import domain.task.Deadline;
import domain.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
         taskList = new ArrayList<>();
     }

    public TaskList(Task task) {
        taskList = Collections.singletonList(task);
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void addTask(Task task) {
         this.taskList.add(task);
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

    public Optional<Deadline> postponeDeadline(int index, LocalDate postponeToDate) {
        Task task = taskList.get(index);
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            deadline.setBy(postponeToDate);
            return Optional.of(deadline);
        }
        return Optional.empty();
    }
}
