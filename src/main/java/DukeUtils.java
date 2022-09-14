import java.util.List;

public class DukeUtils {
    public static void dukeInit() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String breakLine = "-------------------------------------------------";
        String tab = "     ";
        System.out.println("Hello from bowen's\n" + logo + breakLine);
        System.out.println(tab + breakLine);
        System.out.println(tab + "Hello! I'm Duke\n" + tab +"What can I do for you?");
        System.out.println(tab + breakLine);
    }

    public static void echoText(String text){
        String breakLine = "-------------------------------------------------";
        String tab = "     ";
        System.out.println(tab + breakLine);
        System.out.println(tab + text);
        System.out.println(tab + breakLine);
    }

    public static void printList(List<Task> tasks) {
        String breakLine = "-------------------------------------------------";
        String tab = "     ";
        System.out.println(tab + breakLine);
        int counter = 0;
        for (Task task : tasks)
            System.out.println(tab + ++counter + ". " + task);
        System.out.println(tab + breakLine);
    }
}
