import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
public class Duke {

    Task task;

    public static void main(String[] args) {
        Todo t = new Todo();
        Helper h = new Helper();

        h.separator();
        System.out.println("Hello! I'm Nala the Annoying Cat!\n" + "What can I do for you?");
        h.separator();
        while (true) {
            //tokenizer
            String incomingText;
            incomingText = userInput();
            String[] strSplit = incomingText.split(" ");
            ArrayList<String> remainingTokens = new ArrayList<String>(Arrays.asList(strSplit));

            if (remainingTokens.get(0).equalsIgnoreCase("mark")) {
                //mark something as done. e.g. mark 5
                //delete the "mark"
                remainingTokens.remove(0);

                //parse the index to changeToMarkAsDone
                try{
                    String indexString = remainingTokens.get(0);
                    int index = Integer.parseInt(indexString);
                    t.changeToMarkAsDone(index);
                }
                catch (NumberFormatException e)
                {
                    System.out.println(remainingTokens.get(0) + " is not a valid integer");
                }

                //clear the arraylist
                remainingTokens.clear();
            }

            else if (remainingTokens.get(0).equalsIgnoreCase("unmark")){
                //mark something as not done. e.g. unmark 5
                remainingTokens.remove(0);
                //parse the index to changeToMarkNotDone
                try{
                    String indexString = remainingTokens.get(0);
                    int index = Integer.parseInt(indexString);
                    t.changeToMarkNotDone(index);
                }
                catch (NumberFormatException e)
                {
                    System.out.println(remainingTokens.get(0) + " is not a valid integer");
                }

                //clear the arraylist
                remainingTokens.clear();
            }


            //WIP
            else if (remainingTokens.get(0).equalsIgnoreCase("bye") || remainingTokens.get(0).equalsIgnoreCase("end")) {
                h.separator();
                System.out.println("Goodbye! Hope to see you again soon!");
                h.separator();
                remainingTokens.clear();
                break;
            }

            else if (incomingText.equalsIgnoreCase("list")) {
                h.separator();
                t.showTodoList();
                h.separator();
                h.newline();
                remainingTokens.clear();
            }

            else {
                t.addNewTodo(incomingText);
            }


        }
    }

    public static String userInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}


