import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> list;
    //private static int count;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        list = new ArrayList<Task> ();
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public String getTask(int i) {
        return list.get(i).getTask();
    }

    public int size() {
       return list.size();
    }

    public void addTodo(String description) {
        list.add(new Todo(description));
    }

    public void addDeadline(String description, String due) {
        list.add(new Deadline(description, due));
    }

    public void addEvent(String description, String due) {
        list.add(new Event(description, due));
    }

    public void delete(int i) {
        list.remove(i-1);
    }

    public void create(int i) {
        list.remove(i-1);
    }

    public void done(int i) {
        list.get(i).setStatus(true);
    }

    public void undone(int i) {
        list.get(i).setStatus(false);
    }
}
