import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    protected static String[] Tasks = new String[100];
    protected static String userReply = "";
    protected static Scanner in;
    protected static int countTask = 0;

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = " ____________";
        System.out.println("Hello Siying!\n" + "How's your day today?\n" + line + System.lineSeparator() + "Below are your tasks due today\n" + line );
        System.out.println("What would you like to do now?");
    }

    public static void exit() {
        System.out.println("Bye Siying!" + System.lineSeparator() + "Enjoy your day ;)");
    }

    public static void getAction() {

        while (!userReply.equals("bye")) {
            in = new Scanner(System.in);
            userReply = in.nextLine();

            if (userReply.equals("bye")) {
                ;
            } else if (userReply.equals("list")) {
                printTasks(Tasks);
            } else {
                Tasks[countTask] = userReply;
                countTask++;

                System.out.println(userReply);
            }
        }
    }

    public static void printTasks(String[] items) {
        for (int i = 0; i < countTask; i++){
            System.out.println(i+1 + ". " + items[i]);
        }
    }

//    public static void listDuetoday(){
//        return Tasks;
//    }

    public static void main(String[] args) {

        greet();
        getAction();
        exit();

    }
}
