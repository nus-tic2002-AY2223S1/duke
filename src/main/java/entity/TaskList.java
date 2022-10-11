package entity;
import Utils.DukeUtils;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public void addTask(Task task) {
        tasks.add(task);
        DukeUtils.echoText("Got it. I've added this task: \n      " + task +
                "\n     Now you have " + tasks.size() + " tasks in the list.");
    }
    public void markTask(int taskNo) {
        Task task = tasks.get(taskNo);
        task.markTask();
        DukeUtils.echoText("Nice! I've marked this task as done:\n      " + task);
    }
    public void unmarkTask(int taskNo) {
        Task task = tasks.get(taskNo);
        task.unmarkTask();
        DukeUtils.echoText("OK, I've marked this task as not done yet:\n      " + task);
    }
    public void deleteTask(int taskNo) {
        Task task = tasks.get(taskNo);
        tasks.remove(taskNo);
        DukeUtils.echoText("Noted. I've removed this task:\n      " + task +
                "\n     Now you have " + tasks.size() + " tasks in the list.");
    }

}
