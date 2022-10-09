import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String EXIT_COMMAND  ="bye";
    public static final String LIST_COMMAND  ="list";
    public static final String MARK_COMMAND  ="mark";
    public static final String UNMARK_COMMAND  ="unmark";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printBanner();
        repLoop(scanner);
        exitMessage();
    }

    private static void repLoop(Scanner scanner){
        String existCue;
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
            default:
                addTask(tasks,command);
                break;

        }
    }

    private static void listTasks(List<Task> tasks){
        printDivider();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t"+ (i + 1)+". "+tasks.get(i).toString());
        }
        printDivider();
    }

    private static void addTask(List<Task> tasks,String command){
        tasks.add(new Task(command));
        printDivider();
        System.out.println("\tadded: "+command);
        printDivider();
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
