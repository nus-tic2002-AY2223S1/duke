import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
public class Duke {

    Task task;

    public static void main(String[] args) throws Exception {
        Helper h = new Helper();
        TaskList t = new TaskList();
        Parser p = new Parser();

        h.separator();
        System.out.println("Hello! I'm Nala the Annoying Cat!\n" + "What can I do for you?");
        h.separator();
        while (true) {
            //tokenizer
            String incomingText = userInput();
            p.stringToToken(incomingText);

            if (p.front().equalsIgnoreCase("mark")) {
                //mark something as done. e.g. mark 5
                //delete the "mark"
                p.next();

                //parse the index to changeToMarkAsDone
                try{
                    String indexString = p.front();
                    int index = Integer.parseInt(indexString);
                    t.changeToMarkAsDone(index);
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Meow! " + p.front() + " is not a valid integer.");
                    t.showTodoList();
                }
                catch (IndexOutOfBoundsException e)
                {
                    System.out.println("Meow! " + p.front() + " is not in the list.");
                    t.showTodoList();
                }
            }

            else if (p.front().equalsIgnoreCase("unmark")){
                //mark something as not done. e.g. unmark 5
                p.next();
                //parse the index to changeToMarkNotDone
                try{
                    String indexString = p.front();
                    int index = Integer.parseInt(indexString);
                    t.changeToMarkNotDone(index);
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Meow! " + p.front() + " is not a valid integer.");
                    t.showTodoList();
                }
                catch (IndexOutOfBoundsException e)
                {
                    System.out.println("Meow! " + p.front() + " is not in the list.");
                    t.showTodoList();
                }
            }


            else if (p.front().equalsIgnoreCase("bye") || p.front().equalsIgnoreCase("end")) {
                h.separator();
                System.out.println("Goodbye! Hope to see you again soon!");
                h.separator();
                p.clear();
                break;
            }

            else if (incomingText.equalsIgnoreCase("list")) {
                p.next();
                t.showTodoList();
            }

            else if (p.front().equalsIgnoreCase("delete")){
                //delete a task. e.g. delete 5
                p.next();
                //parse the index to deleteTask
                try{
                    String indexString = p.front();
                    int index = Integer.parseInt(indexString);
                    t.changeToMarkNotDone(index);
                    t.deleteTask(index);
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Meow! " + p.front() + " is not a valid integer.");
                    t.showTodoList();
                }
                catch (IndexOutOfBoundsException e)
                {
                    System.out.println("Meow! " + p.front() + " is not in the list.");
                    t.showTodoList();
                }
            }



            else { //add new task
                String incomingTaskName="";
                String incomingType="";
                String incomingDate="";
                switch (p.front()){
                    case ("todo"):
                        if (p.remainingTokens.contains("/at")){
                            h.nalaSyntaxError("Event");
                            break;
                        }
                        else if (p.remainingTokens.contains("/by")){
                            h.nalaSyntaxError("Deadline");
                            break;
                        }
                        else{
                            incomingType = p.front();
                            p.next();
                            incomingTaskName = p.tokenToString();
                            if (incomingTaskName.isBlank()){
                                h.nalaSyntaxError("BlankTodo");
                                break;
                            }

                        }
                        t.addNewTask(incomingTaskName, incomingType);
                        p.clear();
                        break;

                    case ("deadline"):
                        if (p.remainingTokens.contains("/at")){
                            h.nalaSyntaxError("Event");
                            break;
                        }
                        else if (p.remainingTokens.contains("/by")){
                            incomingType = p.front();
                            p.next();
                            String TaskNameString="";
                            while (!p.front().equalsIgnoreCase("/by")){
                                TaskNameString = TaskNameString + " " + p.front();
                                p.next();
                            }
                            p.expect("/by");
                            incomingDate = p.tokenToString();
                            incomingTaskName = TaskNameString;
                            if (incomingTaskName.isBlank()){
                                h.nalaSyntaxError("BlankDeadline");
                                break;
                            }
                            if (incomingDate.isBlank()){
                                h.nalaSyntaxError("NoDate");
                                break;
                            }
                        }
                        else{ //no /by
                            h.nalaSyntaxError("DeadlineNoBy");
                            break;
                        }
                        t.addNewTask(incomingTaskName, incomingType, incomingDate);
                        p.clear();
                        break;

                    case  ("event"):
                        if (p.remainingTokens.contains("/by")){
                            h.nalaSyntaxError("Deadline");
                            break;
                        }
                        else if (p.remainingTokens.contains("/at")){
                            incomingType = p.front();
                            p.next();
                            String TaskNameString="";
                            while (!p.front().equalsIgnoreCase("/at")){
                                TaskNameString = TaskNameString + " " + p.front();
                                p.next();
                            }
                            p.expect("/at");
                            incomingDate = p.tokenToString();
                            incomingTaskName = TaskNameString;
                            if (incomingTaskName.isBlank()){
                                h.nalaSyntaxError("BlankEvent");
                                break;
                            }
                            if (incomingDate.isBlank()){
                                h.nalaSyntaxError("NoDate");
                                break;
                            }
                        }
                        else{ //no /at
                            h.nalaSyntaxError("EventNoAt");
                            break;
                        }
                        t.addNewTask(incomingTaskName, incomingType, incomingDate);
                        p.clear();
                        break;
                    default:
                        h.nalaConfused();

                }
            }
            p.clear();
        }
    }

    public static String userInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}


