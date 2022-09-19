import java.util.Scanner;



public class Duke {
    private static int count = 0;
    private static Task[] tasks = new Task[100];

    public static void addList(Task t) {

        tasks[count]=t;
        count++;
    }
    public static void printList(){
        for(int i=1;i<=count;i++){
            System.out.println(i + "." + " " + tasks[i-1]);
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

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while(true){
            try {
                line = in.nextLine();
                String[] userInput = line.split(" ");
                if (extracted(line, userInput)) continue;
                break;
            } catch(NullPointerException e){
                System.out.println("☹ OOPS!!! task number is not valid");
            } catch(DukeException e){
              System.out.println(e.getMessage());
            } catch(NumberFormatException e){

            } catch (StringIndexOutOfBoundsException e){

            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("☹ OOPS!!! task is empty");
            }
        }
    }

    private static boolean extracted(String line, String[] userInput) throws DukeException,NullPointerException {
        switch(userInput[0]){
            case"bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case"list":
                if(tasks[count]==null){
                    throw new DukeException("The task list is empty");
                }
                printList();
                return true;
            case"mark":
                if(userInput[1]==null){
                    throw new DukeException("The mark task is not available,Pls re-input mark task!");
                }
                    int indexMark = Integer.parseInt(userInput[1]);
                    Task markTask = tasks[indexMark-1];
                    markTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(markTask);


                return true;
            case"unmark":
                    int indexUnmark = Integer.parseInt(userInput[1]);
                    Task unmarkTask = tasks[indexUnmark-1];
                    unmarkTask.markAsNotdone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(unmarkTask);
                if(unmarkTask ==null) {
                    throw new DukeException("The unmark task is not available,Pls re-input unmark task!");
                }
                return true;
            case"deadline":
                if(userInput.length<2){
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                if(isNumeric(userInput[1])){
                    throw new DukeException("☹ OOPS!!! The input of a deadline cannot be number.");
                }
                    int divPos = line.indexOf("/");
                    String deadlineTask = line.substring(9,divPos);
                    String deadlineDate = line.substring(divPos+4);
                    Deadline taskDeadline = new Deadline(deadlineTask,deadlineDate);
                    addList(taskDeadline);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(taskDeadline);
                    System.out.println("Now you have " + count + " tasks in the list.");
                return true;
            case"todo":
                if(userInput.length<2){
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                if(isNumeric(userInput[1])){
                    throw new DukeException("☹ OOPS!!! The input of a todo cannot be number.");
                }
                    String todoTask = line.substring(5);
                    Todo taskTodo = new Todo(todoTask);
                    addList(taskTodo);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(taskTodo);
                    System.out.println("Now you have " + count + " tasks in the list.");

                return true;
            case"event":
                if(userInput.length<2){
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                if(isNumeric(userInput[1])){
                    throw new DukeException("☹ OOPS!!! The input of a event cannot be number.");
                }
                    int Pos = line.indexOf("/");
                    String eventsTask = line.substring(6,Pos);
                    String eventsDate = line.substring(Pos+4);
                    Events taskEvent = new Events(eventsTask,eventsDate);
                    addList(taskEvent);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(taskEvent);
                    System.out.println("Now you have " + count + " tasks in the list.");

                return true;
            default:
                if(userInput[0].equals(("blah"))){
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                addList(new Task(line));

                return true;
        }
        return false;
    }
}


