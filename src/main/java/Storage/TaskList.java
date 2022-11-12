package Storage;

import Entity.Task;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> taskdetails) {
        tasks = taskdetails;
    }

    public TaskList(){
        tasks = new ArrayList<Task>();
    }

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

    public void findTasks(String keyword){
        ArrayList<Task> result = new ArrayList<Task>();

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                result.add(task);}
        }
        if(result.size()>0){
            for (int i = 0; i < result.size(); i++) {
                System.out.print("\t " + (i + 1) + ".");
                result.get(i).print();
            }
        }else{
            System.out.println("\tSorry, we didn't find any tasks contains keyword:"+keyword);
        }

    }


    public void printTasks() {
        System.out.println("\t Here are the tasks in your list:");
        ArrayList<Task> temp = tasks;
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print("\t " + (i + 1) + ".");
            getTasks(i).print();
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
