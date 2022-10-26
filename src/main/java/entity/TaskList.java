package entity;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public void addTask(Task task) {
        tasks.add(task);
        Ui.echoText("Got it. I've added this task: \n      " + task +
                "\n     Now you have " + tasks.size() + " tasks in the list.");
    }
    public void markTask(int taskNo) {
        Task task = tasks.get(taskNo);
        task.markTask();
        Ui.echoText("Nice! I've marked this task as done:\n      " + task);
    }
    public void unmarkTask(int taskNo) {
        Task task = tasks.get(taskNo);
        task.unmarkTask();
        Ui.echoText("OK, I've marked this task as not done yet:\n      " + task);
    }
    public void deleteTask(int taskNo) {
        Task task = tasks.get(taskNo);
        tasks.remove(taskNo);
        Ui.echoText("Noted. I've removed this task:\n      " + task +
                "\n     Now you have " + tasks.size() + " tasks in the list.");
    }

    public void printTasks() {
        System.out.println(Ui.tab + Ui.breakLine);
        int counter = 0;
        for (Task task : tasks)
            System.out.println(Ui.tab + ++counter + ". " + task);
        System.out.println(Ui.tab + Ui.breakLine);
    }
    public String writeTasksToFile() {
        StringBuilder contexts = new StringBuilder();
        for (Task task : tasks) {
            contexts.append(task.toFile());
        }
        return contexts.toString();
    }



}
