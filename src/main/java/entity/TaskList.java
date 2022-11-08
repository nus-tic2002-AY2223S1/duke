package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {
    public List<Task> tasks;

    /**
     * TaskList object constructor
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * To load tasks in file to Tasks
     *
     * @param task a Task object
     */
    public void loadTask(Task task) {
        tasks.add(task);
    }

    public void tagTask(int taskNo, String[] tags) {
        Task task = tasks.get(taskNo);
        task.addTags(Arrays.asList(tags));
        Ui.echoText("Task is tagged:\n      " + task);
    }
    /**
     * To add task to Tasks by user's command
     *
     * @param task a Task object
     */
    public void addTask(Task task) {
        tasks.add(task);
        Ui.echoText("Got it. I've added this task: \n      " + task +
                "\n     Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * To mark task in Tasks by task number by user's command
     *
     * @param taskNo a task number
     */
    public void markTask(int taskNo) {
        Task task = tasks.get(taskNo);
        task.markTask();
        Ui.echoText("Nice! I've marked this task as done:\n      " + task);
    }

    /**
     * To unmark task in Tasks by task number by user's command
     *
     * @param taskNo a task number
     */
    public void unmarkTask(int taskNo) {
        Task task = tasks.get(taskNo);
        task.unmarkTask();
        Ui.echoText("OK, I've marked this task as not done yet:\n      " + task);
    }

    /**
     * To delete task in Tasks by task number by user's command
     *
     * @param taskNo a task number
     */
    public void deleteTask(int taskNo) {
        Task task = tasks.get(taskNo);
        tasks.remove(taskNo);
        Ui.echoText("Noted. I've removed this task:\n      " + task +
                "\n     Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * To print all Tasks by user's command
     */
    public void printTasks() {
        System.out.println(Ui.tab + Ui.breakLine);
        int counter = 0;
        for (Task task : tasks)
            System.out.println(Ui.tab + ++counter + ". " + task);
        System.out.println(Ui.tab + Ui.breakLine);
    }

    /**
     * To write tasks to file
     *
     * @return text to save to file
     */
    public String writeTasksToFile() {
        StringBuilder contexts = new StringBuilder();
        for (Task task : tasks) {
            contexts.append(task.toFile());
        }
        return contexts.toString();
    }


}
