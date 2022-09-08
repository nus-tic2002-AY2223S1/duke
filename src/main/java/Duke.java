import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ---------------------------------------");
        System.out.println("     Hello! I'm Duke\n" +
                "     What can I do for you?");

        List<String> tasks = new ArrayList<>();
        while (true){
            String text;
            Scanner s = new Scanner(System.in);
            text = s.nextLine();
            System.out.println("    ---------------------------------------");

            if(text.equals("list")){
                for (String task : tasks) {
                    int index = tasks.indexOf(task) + 1;
                    System.out.println("    " + index + ". " +task);
                }
            }
            if(!text.equals("list") && !text.equals("bye")){
                System.out.println("    " + "added: " + text);
                tasks.add(text);
            }
            if (text.equals("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
                break;
            }
        }
    }
}
