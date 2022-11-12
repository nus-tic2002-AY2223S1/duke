package duke;
import java.util.List;
import java.util.ArrayList;
public class Tasklist {
    public static List<Task> tasks  = new ArrayList<>(100);
    public Tasklist(){
    }
    public static void initialize(){
        Storage.importTasks();
    }

    public static void mark(String taskIndex){
        Task task = Tasklist.get(Integer.parseInt(taskIndex) - 1);
        Ui.printDivider();
        System.out.println("\tNice! I've marked this task as done:");
        task.markAsDone();
        System.out.println("\t"+ task);
        Ui.printDivider();
    }
    public static void unmark(String taskIndex){

        Task task = tasks.get(Integer.parseInt(taskIndex) - 1);
        Ui.printDivider();
        System.out.println("\tOK, I've marked this task as not done yet:");
        task.markAsNotDone();
        System.out.println("\t"+task);
        Ui.printDivider();
    }

    public static Task addTodo(String desc){
        Todo t = new Todo(desc);
        tasks.add(t);
        return t;

    }

    public static Task addEvent(String desc,String time){
        Event e = new Event(desc,time);
        tasks.add(e);
        return e;
    }

    public static Task addDeadline(String desc,String time){
        Deadline d = new Deadline(desc,time);
        tasks.add(d);
        return d;
    }

    public static void deleteTask(List<Task> tasks,int index) throws InvalidDeleteOptionException{
        int size =tasks.size();
        if (index > size){
            throw new InvalidDeleteOptionException(index,size);
        }
        Task t = tasks.get(index);
        tasks.remove(index);
        Ui.printDeleteAcknowledge(t, tasks.size());
    }

    public static int size(){
        return tasks.size();
    }

    public static void add(Task t){
        Tasklist.tasks.add(t);
    }

    public  static Task get(int i){
       return Tasklist.tasks.get(i);
    }
}



