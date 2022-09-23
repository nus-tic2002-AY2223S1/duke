import java.util.List;

public class DukeUtils {
    public static void printText(String text){
        String line = "---------------------------------------------";
        String space = "     ";
        System.out.println(space + line);
        System.out.println(space + text);
        System.out.println(space + line);
    }
    public static void printAction(Task t, int count){
        String line = "---------------------------------------------";
        String space = "     ";
        String taskName = "todo";
        String taskDate = " ";
        String Date1 = " ";
        String DateRest = " ";
        String rest = t.getDescription().split(" ", 2)[1];
        if(t.getType() != 'T') {
            taskName = rest.split("/", 2)[0];
            taskDate = rest.split("/", 2)[1];
            Date1 = taskDate.split(" ", 2)[0];
            DateRest = taskDate.split(" ", 2)[1];
        }
        System.out.println(space + line);
        System.out.println(space + "Got it. I've added this task:" );
        if(taskName.equals("todo")){
            System.out.println(space + "  [T]" + "[" + t.getStatusIcon() + "] " + rest);
        }
        else{
            System.out.println(space + "  [" + t.getType() + "] " + "[" + t.getStatusIcon() + "] " + taskName
                    + "(" + Date1 + ":" + DateRest + ")");
        }
        System.out.println(space + "Now you have " + count + " tasks in the list." );
        System.out.println(space + line);
    }
    public static void printTodo(String taskName, int count){
        String line = "---------------------------------------------";
        String space = "     ";
        System.out.println(space + line);
        System.out.println(space + "Got it. I've added this task:" );
        System.out.println(space + taskName);
        System.out.println(space + "Now you have" + count + "tasks in the list." );
        System.out.println(space + line);
    }

    public static void printList(List<Task> tasks) {
        String line = "---------------------------------------------";
        String space = "     ";
        System.out.println(space + line);
        System.out.println(space + "Here are the tasks in your list:");
        for (Task task : tasks) {
            int index = tasks.indexOf(task) + 1;
            System.out.println(space + index + ". " + task.getTypeDescription());
        }
        System.out.println(space + line);
    }
}
