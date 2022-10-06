import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public static int filterTaskID(String line) {
        String[] words = line.split(" ");
        int ID_num = Integer.parseInt(words[1]);
        return ID_num - 1;
    }

    //load file if exist, if dun exist create new file in a new folder
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        //...
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

        while(!line.equals("bye") ){
            line = in.nextLine();

            if(line.equals("list")){
                tasks.listTasks();
            }
            else if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            }
            else if (line.startsWith("mark")) {
                taskID = filterTaskID(line);
                tasks.myTaskList[taskID].markAsDone();
            }
            else if (line.startsWith("unmark")) {
                taskID = filterTaskID(line);
                tasks.myTaskList[taskID].markAsNotDone();
            }
            else if (line.startsWith("todo") || line.startsWith("deadline") || line.startsWith("event") ) {
                try{
                    tasks.addTasks(line);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + tasks.myTaskList[tasks.task_count-1].toString());
                    System.out.println("Now you have " + tasks.task_count + " tasks in the list.");
                } catch (DukeException e){
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                }
            }
            else if(line.startsWith("delete")){
                try{
                    taskID = filterTaskID(line);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("\t" + tasks.myTaskList[taskID].toString());
                    tasks.removeTasks(taskID);
                    System.out.println("Now you have " + tasks.task_count + " tasks in the list.");
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
    public static void main(String[] args) {
        new Duke("../TIC2002/tasks.txt").run();
    }
}
