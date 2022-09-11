import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input ="";
        String divider = " \t____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(divider);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(divider+"\n");
        input = scanner.nextLine();
        while(!input.equals("bye")){
            System.out.println(divider);
            System.out.println("\t"+input);
            System.out.println(divider);
            input = scanner.nextLine();
        }
        System.out.println(divider);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(divider);
    }
}
