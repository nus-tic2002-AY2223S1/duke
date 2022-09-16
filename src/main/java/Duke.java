import java.util.Scanner;

public class Duke {
    public static int filterTaskID(String line) {
        String[] words = line.split(" ");
        int ID_num = Integer.parseInt(words[1]);
        return ID_num - 1;
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke. \nWhat can I do for you?");

        String line = "";
        int taskID;
        Scanner in = new Scanner(System.in);

        Tasks myList = new Tasks();

        while(!line.equals("bye") ){
            line = in.nextLine();

            if(line.equals("list")){
                myList.listTasks();
            }
            else if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            }
            else if (line.startsWith("mark")) {
                taskID = filterTaskID(line);
                myList.myTaskList[taskID].markAsDone();
            }
            else if (line.startsWith("unmark")) {
                taskID = filterTaskID(line);
                myList.myTaskList[taskID].markAsNotDone();
            }
            else if (line.startsWith("todo") || line.startsWith("deadline") || line.startsWith("event") ) {
                try{
                    myList.addTasks(line);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + myList.myTaskList[myList.task_count-1].toString());
                    System.out.println("Now you have " + myList.task_count + " tasks in the list.");
                } catch (NoDescriptionException e){
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                } catch (UnknownCommandException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                try{
                    throw new UnknownCommandException();
                } catch (UnknownCommandException e){
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        }
    }
}
