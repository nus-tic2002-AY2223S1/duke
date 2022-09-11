import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Todo t = new Todo();

        t.separator();
        System.out.println("Hello! I'm Nala the Annoying Cat!\n" + "What can I do for you?");
        t.separator();
        while (true) {
            String incomingText;
            incomingText = userInput();

            try {
                if (incomingText.substring(0, 4).equalsIgnoreCase("mark")) {
                    //mark something as done
                    String indexString = incomingText.substring(5);
                    int index = Integer.parseInt(indexString);
                    t.changeToMarkAsDone(index);
                    continue;
                }

                else if (incomingText.substring(0,6).equalsIgnoreCase("unmark")){
                    //mark something as not done
                    String indexString = incomingText.substring(7);
                    int index = Integer.parseInt(indexString);
                    t.changeToMarkNotDone(index);
                    continue;
                }
            } catch (NumberFormatException e) {

            } catch (StringIndexOutOfBoundsException e) {

            }

            if (incomingText.equalsIgnoreCase("bye") || incomingText.equalsIgnoreCase("end")) {
                t.separator();
                System.out.println("Goodbye! Hope to see you again soon!");
                t.separator();
                break;
            }

            else if (incomingText.equalsIgnoreCase("list")) {
                t.separator();
                t.showTodoList();
                t.separator();
                t.newline();
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


