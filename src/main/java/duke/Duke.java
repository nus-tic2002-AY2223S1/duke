package duke;
import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tasklist.initialize();
        String input;

        Ui.printBanner();
        input = scanner.nextLine();
        while(!(input.equals(Parser.EXIT_COMMAND)||input.equals(Parser.BYE_COMMAND))){
            try{
                Parser.executeCommand(input);
            }
            catch (UnknownCommandException | EmptyDescriptionException| InvalidDeleteOptionException| MissingTimeException e) {
                    Ui.printDivider();
                    System.out.println("\t"+e.getMessage());
                    Ui.printDivider();
            }
            input = scanner.nextLine();
        }
        Ui.exitMessage();
    }














}
