import java.util.ArrayList;
import java.util.Scanner;
import java.lang.*;
import storeage.StoreFile;
import task.Events;
import task.Task;
import task.Deadline;
import task.Todo;
import exception.DukeException;
import static utility.Method.*;

public class Duke {
    public static void main(String[] args){
        String line;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> listItems = new ArrayList<>();

        UI.Greeting();

        while(true){
            try{
                line = in.nextLine();
                if (UserInput(listItems, line)) continue;
                break;
            }catch(NullPointerException e){
                System.out.println("☹ OOPS!!! task is not exist");
            }catch(DukeException e){
              System.out.println(e.getMessage());
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("☹ OOPS!!! The input task is not valid");
            }catch(NumberFormatException e){
                System.out.println("☹ OOPS!!! The input is String,Pls enter number");
            }catch(StringIndexOutOfBoundsException e){
                System.out.println("☹ OOPS!!! The input is out of bounds,Pls enter valid String");
            }catch(IndexOutOfBoundsException e){
                System.out.println("☹ OOPS!!! The input is out of index,Pls re-enter");
            }
        }
    }

    public static boolean UserInput(ArrayList<Task> listItems,String Input) throws DukeException{
            String[] userInput = Input.split(" ");
        switch(userInput[0]){
            case"bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case"list":
                if(listItems.isEmpty()){
                    throw new DukeException("☹ OOPS!!! The task list is empty");
                }
                printList(listItems);
                return true;
            case"mark":
                markFunction(listItems,Input);
                return true;
            case"unmark":
                unmarkFunction(listItems,Input);
                return true;
            case"deadline":
                if(userInput.length<2){
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                if(isNumeric(userInput[1])){
                    throw new DukeException("☹ OOPS!!! The input of a deadline cannot be number.");
                }
                deadlineFunction(listItems,Input);
               return true;
            case"todo":
                if(userInput.length<2){
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                if(isNumeric(userInput[1])){
                    throw new DukeException("☹ OOPS!!! The input of a todo cannot be number.");
                }
                todoFunction(listItems,Input);
                return true;
            case"event":
                if(userInput.length<2){
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                if(isNumeric(userInput[1])){
                    throw new DukeException("☹ OOPS!!! The input of a event cannot be number.");
                }
                eventFunction(listItems,Input);
                return true;
            case"delete":
                deleteFunction(listItems,Input);
                return true;
            case"save":
                save(listItems);
                return true;
            case"find":
                searchWord(listItems,Input);
                return true;
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                return true;
        }
        return false;
    }
}


