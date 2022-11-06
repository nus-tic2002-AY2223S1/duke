package Storage;

import Entity.Task;

import java.util.ArrayList;

public class TaskList {
//    private static ArrayList<Task> tasks;
//    private static int taskCount = 0;
//    private static TaskList theOne = null;
    private static ArrayList<Task> tasks;
//    private int taskCount = 0;

    public TaskList(ArrayList<Task> taskdetails) {
        tasks = taskdetails;
    }

    public TaskList(){
        tasks = new ArrayList<Task>();
    }
//    public static TaskList getInstance() {
//        if (theOne == null) {
//            theOne = new TaskList();
//        }
//        return theOne;
//    }

//    public static void init(ArrayList<Task> inputTask) {
//        if (tasks == null) {
//            tasks = new ArrayList<Task>();
//        } else {
//            tasks = inputTask;
//        }
//    }

    public void addTask(Task t) {
        tasks.add(t);
//        taskCount++;
        System.out.println("\tGot it. I have added this task:");
        t.print();
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    }

    public void deleteTask(int taskNumber) {
        if (1 <= taskNumber && taskNumber <= tasks.size()) {
            Task temp = tasks.get(taskNumber - 1);
            tasks.remove(taskNumber - 1);
//            taskCount--;
            System.out.println("\tNoted. I have removed this task:");
            temp.print();
            System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
        } else {
            System.out.println("\tPlease input a valid task number");
        }
    }

    public void printTasks() {
        System.out.println("\t Here are the tasks in your list:");
        ArrayList<Task> temp = tasks;
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print("\t " + (i + 1) + ".");
            getTasks(i).print();
//            tasks.get(i).print();
        }
    }

    public Task getTasks(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public int getTaskCount() {
        return tasks.size();
    }

}
