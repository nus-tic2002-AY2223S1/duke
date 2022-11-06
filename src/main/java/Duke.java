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
    public static final String DELETE_COMMAND  ="delete";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printBanner();
        repLoop(scanner);
        exitMessage();
    }

    private static void repLoop(Scanner scanner){
        List<Task> inputList = new ArrayList<>(100);
        String input;
        input = scanner.nextLine();
        while(!input.equals(EXIT_COMMAND)){
            try{
                executeCommand(input,inputList);
            } catch (UnknownCommandException | EmptyDescriptionException| InvalidDeleteOptionException| MissingTimeException e) {
                printDivider();
                System.out.println("\t"+e.getMessage());
                printDivider();
            }

            input = scanner.nextLine();
        }

    }
    private static void executeCommand(String command,List<Task> tasks)
            throws UnknownCommandException, EmptyDescriptionException,MissingTimeException,InvalidDeleteOptionException{
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
                validateDescritionNotEmpty(TODO_COMMAND,commandArgs[0]);
                addTodo(tasks,commandArgs[0]);
                break;
            case DEADLINE_COMMAND:
                commandArgs = splitCommandArgs(command);
                validateDescritionNotEmpty(DEADLINE_COMMAND,commandArgs[0]);
                validateTimeFormatisPresent(commandArgs);
                addDeadline(tasks,commandArgs[0],commandArgs[1]);
                break;
            case EVENT_COMMAND:
                commandArgs = splitCommandArgs(command);
                validateDescritionNotEmpty(EVENT_COMMAND,commandArgs[0]);
                validateTimeFormatisPresent(commandArgs);
                addEvent(tasks,commandArgs[0],commandArgs[1]);
                break;
            case DELETE_COMMAND:
                commandArgs = splitCommandArgs(command);
                validateDescritionNotEmpty(DELETE_COMMAND,commandArgs[0]);
                deleteTask(tasks,Integer.parseInt(commandArgs[0].trim()) - 1);
                break;
            default:
                throw new UnknownCommandException();
        }
    }

    //Splits the input from commandline for Deadlines and Events to the desc and time components
    private static String[] splitCommandArgs(String input){
        String[] commandArgs;
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

    private static void deleteTask(List<Task> tasks,int index) throws InvalidDeleteOptionException{
        int size =tasks.size();
        if (index > size){
            throw new InvalidDeleteOptionException(index,size);
        }
        Task t = tasks.get(index);
        tasks.remove(index);
        printDeleteAcknowledge(t, tasks.size());
    }
    private static void validateTimeFormatisPresent(String[] command)throws MissingTimeException{
        if (command.length != 2){
            throw new MissingTimeException();
        }
    }
    private static void validateDescritionNotEmpty(String name,String desc)throws EmptyDescriptionException{
        if (desc.isEmpty()){
            throw new EmptyDescriptionException(name);
        }
    }

    private static void mark(List<Task> tasks,String taskIndex){
        Task task = tasks.get(Integer.parseInt(taskIndex) - 1);
        printDivider();
        System.out.println("\tNice! I've marked this task as done:");
        task.markAsDone();
        System.out.println("\t"+ task);
        printDivider();
    }
    private static void unmark(List<Task> tasks,String taskIndex){
        Task task = tasks.get(Integer.parseInt(taskIndex) - 1);
        printDivider();
        System.out.println("\tOK, I've marked this task as not done yet:");
        task.markAsNotDone();
        System.out.println("\t"+task);
        printDivider();
    }
    private static void printTaskAddAcknowledge(Task task,int count){
        printDivider();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t"+task);
        printTaskCount(count);
        printDivider();
    }

    private static void printDeleteAcknowledge(Task task,int count){
        printDivider();
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t"+task);
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
        System.out.println(logo);
        printDivider();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printDivider();
    }
    private static void exitMessage(){
        printDivider();
        System.out.println("\tBye. Hope to see you again soon!");
        printDivider();
    }
}
