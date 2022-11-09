import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected static List<Task> tl;
    protected static int taskId = 1;

    public TaskList() {
        tl = new ArrayList<Task>();
    }

    public int getCount(){
        return tl.size();
    }

    public int getTaskId(){
        return taskId;
    }

    public List<Task> getList() {
        return tl;
    }

    public void addT(Task type) {
        tl.add(type);
        taskId++;
    }

    public Task findTask(int id){
        return tl.get(taskId);
        //add exception to handle outofrange
    }

    public void markT(int id, String key) {
        if (tl.get(id).isDone) {
            Ui.trueMark(key);
        } else {
            tl.get(id).mark();
            tl.get(id).description.replace(4, 5, "X");
            Ui.markTask(id, tl);      
        } 
    }

    public void unmarkT(int id, String key) {
        if (tl.get(id).isDone) {
            Ui.trueMark(key);
        } else {
            tl.get(id).unmark();
            tl.get(id).description.replace(4, 5, " ");
            Ui.unmarkTask(id, tl);
        }
    }

    public void deleteTask(int id) {
        StringBuilder selection = new StringBuilder(tl.get(id-1).description);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(selection.toString());
        tl.remove(id-1);
        System.out.println("Now you have " + getCount() + " tasks in the list.");
    }
}