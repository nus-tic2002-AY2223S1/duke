import java.util.ArrayList;
import java.util.List;

public class Collections {
    protected static List<Task> taskslist;
    protected static int taskId = 1;

    public Collections(){
        taskslist = new ArrayList<Task>();
    }

    public int getCount(){
        return taskslist.size();
    }

    public int getTaskId(){
        return taskId;
    }

    public List<Task> getList() {
        return taskslist;
    }

    public void addTask(Task type) {
        System.out.println("Got it. I've added this task:");
        taskslist.add(type);
        taskId++;
        System.out.println("Now you have " + getCount() + " in the list.");
    }

    public Task findTask(int id){
        return taskslist.get(taskId);
        //add exception to handle outofrange
    }

    public void markTask(int id) {
        taskslist.get(id).mark();
        taskslist.get(id).description.replace(4, 5, "X");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskslist.get(id).description.toString());        
    }

    public void unmarkTask(int id) {
        taskslist.get(id).unmark();
        taskslist.get(id).description.replace(4, 5, " ");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskslist.get(id).description.toString());
    }

    public void deleteTask(int id) {
        StringBuilder selection = new StringBuilder(taskslist.get(id-1).description);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(selection.toString());
        taskslist.remove(id-1);
        System.out.println("Now you have " + getCount() + " tasks in the list.");
    }
}