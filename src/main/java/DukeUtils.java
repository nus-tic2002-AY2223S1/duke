import java.util.List;

public class DukeUtils {
    public static void printText(String text){
        String line = "---------------------------------------------";
        String space = "     ";
        System.out.println(space + line);
        System.out.println(space + text);
        System.out.println(space + line);
    }

    public static void printList(List<Task> tasks) {
        String line = "---------------------------------------------";
        String space = "     ";
        System.out.println(space + line);
        System.out.println(space + "Here are the tasks in your list:");
        for (Task task : tasks) {
            int index = tasks.indexOf(task) + 1;
            System.out.println(space + index + ". " + task.getStatusDescription());
        }
        System.out.println(space + line);
    }
}
