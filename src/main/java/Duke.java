import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
                
        System.out.println("Hello from\n" + logo);
        System.out.println("\nHello! I'm Duke\nWhat can I do for you?\n");
       
        String[] list = new String[100];
        int next = 0;
        Scanner in = new Scanner(System.in);

        while(true) {
            String input = in.nextLine();
            switch (input) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    in.close();
                    return;
                case ("list"):
                    for (int i = 0; i < next; i++) {
                        System.out.println(i + ". " + list[i]);
                    }
                    break;
                default:
                    list[next] = input;
                    next++;
                    System.out.println("added: " + input);
            }                
        }  
    }
}
