import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke
{
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private Parser parser;

    public static int filterTaskID(String line)
    {
        String[] words = line.split(" ");
        int ID_num = Integer.parseInt(words[1]);
        return ID_num - 1;
    }

    //constructor for Duke
    public Duke(String filePath) throws IOException
    {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException | DukeException e)
        {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws IOException
    {
        ui.showWelcome();

        String fullCommand = "";//fullCommand
        int taskID;

        while(!fullCommand.equals("bye") )
        {
            fullCommand = ui.readCommand();

            if(fullCommand.equals("list"))
            {//listCommand
                tasks.listTasks();
            }
            else if (fullCommand.equals("bye"))
            {//ExitCommand
                System.out.println("Bye. Hope to see you again soon!");
            }
            else if (fullCommand.startsWith("mark"))
            {//MarkCommand
                taskID = filterTaskID(fullCommand);
                tasks.myTaskList[taskID].markAsDone();
            }
            else if (fullCommand.startsWith("unmark"))
            {//Unmark Command
                taskID = filterTaskID(fullCommand);
                tasks.myTaskList[taskID].markAsNotDone();
            }
            else if (fullCommand.startsWith("todo") || fullCommand.startsWith("deadline") || fullCommand.startsWith("event") )
            { //AddCommand
                try
                {
                    tasks.addTasks(fullCommand);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + tasks.myTaskList[tasks.task_count-1].toString());
                    System.out.println("Now you have " + tasks.task_count + " tasks in the list.");
                } catch (DukeException | IOException e)
                {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                }
            }
            else if(fullCommand.startsWith("delete"))
            { //DeleteCommand
                try{
                    taskID = filterTaskID(fullCommand);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("\t" + tasks.myTaskList[taskID].toString());
                    tasks.removeTasks(taskID);
                    System.out.println("Now you have " + tasks.task_count + " tasks in the list.");
                } catch (DukeException e)
                {
                    System.out.println("OOPS!!! There is no corresponding taskID to delete.");
                } catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
            }
            else if(fullCommand.startsWith("find"))
            { //FindCommand
                System.out.println("Here are the matching tasks in your list:");
                //print a list of item contain the key
                tasks.findTasks(fullCommand);
            }
            else if(fullCommand.startsWith("viewSchedule"))
            { //viewScheduleCommand
                System.out.println("Here are the matching tasks in your list:");
                //print a list of item contain the key
                tasks.viewSchedule();
            }

            else
            {
                try
                {
                    throw new DukeException();
                } catch (DukeException e){
                    ui.showError(e.getMessage());
                }
            }
            ui.showLine();
        }
    }
    public static void main(String[] args) throws IOException
    {
        new Duke("data/tasks.txt").run();
    }
}
