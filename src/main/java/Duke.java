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

        //load file if exist

        //if dun exist create new file in a new folder

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
                } catch (DukeException e){
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                }
            }
            else if(line.startsWith("delete")){
                try{
                    taskID = filterTaskID(line);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("\t" + myList.myTaskList[taskID].toString());
                    myList.removeTasks(taskID);
                    System.out.println("Now you have " + myList.task_count + " tasks in the list.");
                } catch (DukeException e){
                    System.out.println("OOPS!!! There is no corresponding taskID to delete.");
                }
            }
            else {
                try{
                    throw new DukeException();
                } catch (DukeException e){
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }

            //save task
        }
    }
}
