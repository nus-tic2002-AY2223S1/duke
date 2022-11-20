package duke;
public class Ui {

    public static void printTaskAddAcknowledge(Task task,int count){
        printDivider();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t"+task);
        printTaskCount(count);
        printDivider();
    }

    public static void printDeleteAcknowledge(Task task,int count){
        printDivider();
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t"+task);
        printTaskCount(count);
        printDivider();
    }
    public static void printTaskCount(int count){
        System.out.println("\tNow you have " + count+ " tasks in the list.");
    }
    public static void printDivider(){
        System.out.println("\t____________________________________________________________");
    }
    public static void printBanner(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        printDivider();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printDivider();
    }

    public static void listTasks(){
        printDivider();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < Tasklist.size(); i++) {
            System.out.println("\t"+ (i + 1)+". "+Tasklist.get(i).toString());
        }
        printDivider();
    }

    public static void exitMessage(){
        printDivider();
        System.out.println("\tBye. Hope to see you again soon!");
        printDivider();
    }
}
