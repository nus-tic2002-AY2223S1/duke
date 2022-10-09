import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String EXIT_COMMAND  ="bye";
    public static final String LIST_COMMAND  ="list";
    public static final String MARK_COMMAND  ="mark";
    public static final String UNMARK_COMMAND  ="unmark";
    public static final String TODO_COMMAND ="todo";
    public static final String DEADLINE_COMMAND  ="deadline";
    public static final String EVENT_COMMAND  ="event";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printBanner();
        repLoop(scanner);
        exitMessage();
    }

    private static void repLoop(Scanner scanner){
        List<Task> inputList = new ArrayList<Task>(100);
        String input ="";
        input = scanner.nextLine();
        while(!input.equals(EXIT_COMMAND)){
            executeCommand(input,inputList);
            input = scanner.nextLine();
        }

    }
    private static void executeCommand(String command,List<Task> tasks){
        String[] inputs = command.split(" ");
        String[] commandArgs;
        switch(inputs[0]){
            case LIST_COMMAND :
                listTasks(tasks);
                break;
            case MARK_COMMAND:
                mark(tasks,inputs[1]);
                break;
            case UNMARK_COMMAND:
                unmark(tasks,inputs[1]);
                break;
            case EXIT_COMMAND:
                break;
            case TODO_COMMAND:
                commandArgs = splitCommandArgs(command);
                addTodo(tasks,commandArgs[0]);
                break;
            case DEADLINE_COMMAND:
                commandArgs = splitCommandArgs(command);
                addDeadline(tasks,commandArgs[0],commandArgs[1]);
                break;
            case EVENT_COMMAND:
                commandArgs = splitCommandArgs(command);
                addEvent(tasks,commandArgs[0],commandArgs[1]);
                break;
            default:
                //previously default just adds whatever string input.
                // Now it has to be Deadline ,Event or todoo:0.
                System.out.println("");
                break;

        }
    }

    //Splits the input from commandline for Deadlines and Events to the desc and time components
    private static String[] splitCommandArgs(String input){
        String commandArgs[];
        int indexOfFirstSpace= (input.split(" ")[0].length());
        // use this regex for simplicity as a deadline is going to use /by or /at
        commandArgs = input.substring(indexOfFirstSpace).split("/[a-z]{2}");
        return commandArgs;
    }

    private static void listTasks(List<Task> tasks){
        printDivider();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t"+ (i + 1)+". "+tasks.get(i).toString());
        }
        printDivider();
    }
    @Deprecated
    private static void addTask(List<Task> tasks,String desc){
        tasks.add(new Task(desc));
        printDivider();
        System.out.println("\tadded: "+desc);
        printDivider();
    }
    private static void addTodo(List<Task> tasks,String desc){
        Todo t = new Todo(desc);
        tasks.add(t);
        printTaskAddAcknowledge(t, tasks.size());
    }

    private static void addEvent(List<Task> tasks,String desc,String time){
        Event e = new Event(desc,time);
        tasks.add(e);
        printTaskAddAcknowledge(e, tasks.size());
    }

    private static void addDeadline(List<Task> tasks,String desc,String time){
        Deadline d = new Deadline(desc,time);
        tasks.add(d);
        printTaskAddAcknowledge(d, tasks.size());
    }

    private static void mark(List<Task> tasks,String taskIndex){
        Task task = tasks.get(Integer.parseInt(taskIndex) - 1);
        printDivider();
        System.out.println("\tNice! I've marked this task as done:");
        task.markAsDone();
        System.out.println("\t"+task.toString());
        printDivider();
    }
    private static void unmark(List<Task> tasks,String taskIndex){
        Task task = tasks.get(Integer.parseInt(taskIndex) - 1);
        printDivider();
        System.out.println("\tOK, I've marked this task as not done yet:");
        task.markAsNotDone();
        System.out.println("\t"+task.toString());
        printDivider();
    }
    private static void printTaskAddAcknowledge(Task t,int count){
        printDivider();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t"+t.toString());
        printTaskCount(count);
        printDivider();
    }
    private static void printTaskCount(int count){
        System.out.println("\tNow you have " + count+ " tasks in the list.");
    }
    private static void printDivider(){
        System.out.println("\t____________________________________________________________");
    }
    private static void printBanner(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printDivider();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printDivider();
        System.out.println("");
    }
    private static void exitMessage(){
        printDivider();
        System.out.println("\tBye. Hope to see you again soon!");
        printDivider();
    }
}
