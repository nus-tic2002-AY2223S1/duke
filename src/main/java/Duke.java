import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String EXIT_COMMAND  ="bye";
    public static final String LIST_COMMAND  ="list";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printBanner();
        repLoop(scanner);
        exitMessage();
    }

    private static void repLoop(Scanner scanner){
        String existCue;
        List<String> inputList = new ArrayList<String>(100);

        String input ="";
        input = scanner.nextLine();
        while(!input.equals(EXIT_COMMAND)){
            executeCommand(input,inputList);
            input = scanner.nextLine();
        }

    }
    private static void executeCommand(String command,List<String> inputs){
        if (command.equals(LIST_COMMAND)){
            printDivider();
            for (int i = 0; i < inputs.size(); i++) {
                System.out.println("\t"+ (i + 1)+". "+inputs.get(i));
            }
            printDivider();
        }else{
            inputs.add(command);
            printDivider();
            System.out.println("\tadded: "+command);
            printDivider();
        }
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
