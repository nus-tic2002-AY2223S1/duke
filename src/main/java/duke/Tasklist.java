package duke;
import java.util.List;
import java.util.ArrayList;
public class Tasklist {
    public static List<Task> tasks  = new ArrayList<>(100);

    public Tasklist(){
    }

    /**
     *  Initializes the Tasklist
     *  by either importing from an existing save
     *  Or Creating a new list
     */
    public static void initialize(){
        Storage.importTasks();
    }

    /**
     * Return a list of task whose description ,
     * partially or full matched.
     *
     * @param candidate the search term used
     * @return a List of Tasks which description matched.
     */
    protected static List<Task> search(String candidate){
       List<Task>tasks =  Tasklist.tasks;
        List<Task>result = new ArrayList<>(100);
       int size = Tasklist.size();
       for (int i = 0 ; i < size;i++){
           Task task = tasks.get(i);
           String description = task.description;
            if (description.contains(candidate)){
                result.add(task);
            }
       }
       return result;
    }

    /**
     *  Using an input prints the tasks which description,
     *  match fully or partially
     * @param input for search
     */
    public static void find(String input){
        List<Task>results = search(input);
        Ui.printDivider();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < results.size(); i++) {
            System.out.println("\t"+ (i + 1)+". "+results.get(i).toString());
        }
        Ui.printDivider();
    }

    /**
     * Marks a task as Done using TaskIndex
     * @param taskIndex task index
     */
    public static void mark(String taskIndex){
        Task task = Tasklist.get(Integer.parseInt(taskIndex) - 1);
        Ui.printDivider();
        System.out.println("\tNice! I've marked this task as done:");
        task.markAsDone();
        System.out.println("\t"+ task);
        Ui.printDivider();
    }

    /**
     * Marks a task as Not Done using TaskIndex
     * @param taskIndex task index
     */
    public static void unmark(String taskIndex){

        Task task = tasks.get(Integer.parseInt(taskIndex) - 1);
        Ui.printDivider();
        System.out.println("\tOK, I've marked this task as not done yet:");
        task.markAsNotDone();
        System.out.println("\t"+task);
        Ui.printDivider();
    }

    /**
     * Adds a todo to the Tasklist and returns it.
     * @param desc description of event
     * @return todo
     */
    public static Task addTodo(String desc){
        Todo t = new Todo(desc);
        tasks.add(t);
        return t;
    }
    /**
     * Adds a Event to the Tasklist and returns it.
     * @param desc description of event
     * @return event
     */
    public static Task addEvent(String desc,String time){
        Event e = new Event(desc,time);
        tasks.add(e);
        return e;
    }
    /**
     * Adds a Deadline to the Tasklist and returns it.
     * @param desc description of event
     * @return event
     */
    public static Task addDeadline(String desc,String time){
        Deadline d = new Deadline(desc,time);
        tasks.add(d);
        return d;
    }

    /**
     * From a list of task passed in, deletes the index
     * @param tasks list of tasks
     * @param index index of task to be deleted
     * @throws InvalidDeleteOptionException Execption for index greater that list size
     */
    public static void deleteTask(List<Task> tasks,int index) throws InvalidDeleteOptionException{
        int size =tasks.size();
        if (index > size){
            throw new InvalidDeleteOptionException(index,size);
        }
        Task t = tasks.get(index);
        tasks.remove(index);
        Ui.printDeleteAcknowledge(t, tasks.size());
    }

    /**
     * Returns the size of the task list
     * @return size of tasklist
     */
    public static int size(){
        return tasks.size();
    }
    /**
     * Add task to task list
     */
    public static void add(Task t){
        Tasklist.tasks.add(t);
    }
    /**
     * Returns task that is at the index of tasklist
     *  @param i index of the task
     */
    public  static Task get(int i){
       return Tasklist.tasks.get(i);
    }
}



