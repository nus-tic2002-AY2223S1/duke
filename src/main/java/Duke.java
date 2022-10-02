import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.*;

public class Duke {
   // private static int count = 0;
   // private static Task[] tasks = new Task[100];

    public static ArrayList<Task> AddList (ArrayList<Task> listItems,String input){
            listItems.add(new Task(input));
            System.out.println("\tadded: " + input);
        return listItems;
    }

    public static void printList(ArrayList<Task>listItems){
        for(int i=1;i<=listItems.size();i++){
            System.out.println(i + "." + listItems.get(i-1));
        }
    }


    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static void main(String[] args){
        String line;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> listItems = new ArrayList<Task>();

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

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
                    int indexMark = Integer.parseInt(userInput[1]);
                    //Task markTask = tasks[indexUnmark-1];
                    Task markTask = listItems.get(indexMark-1);
                    markTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(markTask);


                return true;
            case"unmark":
                    int indexUnmark = Integer.parseInt(userInput[1]);
                   // Task unmarkTask = tasks[indexUnmark-1];
                    Task unmarkTask = listItems.get(indexUnmark-1);
                    unmarkTask.markAsNotdone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(unmarkTask);
                return true;
            case"deadline":
                if(userInput.length<2){
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                if(isNumeric(userInput[1])){
                    throw new DukeException("☹ OOPS!!! The input of a deadline cannot be number.");
                }
                    int divPos = Input.indexOf("/");
                    String deadlineTask = Input.substring(9,divPos);
                    String deadlineDate = Input.substring(divPos+4);
                    Deadline taskDeadline = new Deadline(deadlineTask,deadlineDate);
                    listItems.add(taskDeadline);

                    System.out.println("Got it. I've added this task: ");
                    System.out.println(taskDeadline);
                    System.out.println("Now you have " + listItems.size() + " tasks in the list.");
                return true;
            case"todo":
                if(userInput.length<2){
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                if(isNumeric(userInput[1])){
                    throw new DukeException("☹ OOPS!!! The input of a todo cannot be number.");
                }
                    String todoTask = Input.substring(5);
                    Todo taskTodo = new Todo(todoTask);
                    listItems.add(taskTodo);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(taskTodo);
                    System.out.println("Now you have " + listItems.size() + " tasks in the list.");
                return true;
            case"event":
                if(userInput.length<2){
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                if(isNumeric(userInput[1])){
                    throw new DukeException("☹ OOPS!!! The input of a event cannot be number.");
                }
                    int Pos = Input.indexOf("/");
                    String eventsTask = Input.substring(6,Pos);
                    String eventsDate = Input.substring(Pos+4);
                    Events taskEvent = new Events(eventsTask,eventsDate);
                    listItems.add(taskEvent);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(taskEvent);
                    System.out.println("Now you have " + listItems.size() + " tasks in the list.");
                return true;
            case"delete":
                try{
                    int DeleteNum = Integer.parseInt(userInput[1]);
                    Task t = listItems.get(DeleteNum-1);
                    listItems.remove(DeleteNum-1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + listItems.size() + " tasks in the list.");
                }catch(IndexOutOfBoundsException e){
                    System.out.println("☹ OOPS!!! The index out of arraylist length");
                }
                return true;
            default:
                if(userInput[0].equals(("blah"))){
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                if(isNumeric(userInput[0])){
                    throw new DukeException("☹ OOPS!!! You have enter a number,Pls re-enter task !");
                }
                AddList(listItems,Input);

                return true;
        }
        return false;
    }
}


