import java.util.Scanner;

public class Duke {
    public static String line = "\t____________________________________________________________\n";

    public static void main(String[] args) {

        System.out.println();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//        System.out.println("yuanyuan testing");

        String greeting = line + "\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n"
                +line;

        System.out.println(greeting);

        Scanner in = new Scanner(System.in);
        String input;
        input = in.nextLine();

        while(input!=null){
            if(input.equals("bye")){
                System.out.println(line + "\t Bye. Hope to see you again soon!\n"+line);
                break;
            }
            System.out.println(line + "\t"+input+"\n"+line);
            input = in.nextLine();
        }

    }
}
