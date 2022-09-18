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


    public static void main(String[] args) throws DukeException {
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while(true){
            line = in.nextLine();
            String[] userInput = line.split(" ");
            switch(userInput[0]){
                case"bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case"list":
                    printList();
                    continue;
                case"mark":
                    if(tasks[count] !=null){
                        int indexMark = Integer.parseInt(userInput[1]);
                        Task markTask = tasks[indexMark-1];
                        markTask.markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(markTask);
                    }else{
                        throw new DukeException("The task list is empty");
                    }
                    continue;
                case"unmark":
                    if(tasks[count] !=null) {
                        int indexUnmark = Integer.parseInt(userInput[1]);
                        Task unmarkTask = tasks[indexUnmark-1];
                        unmarkTask.markAsNotdone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(unmarkTask);
                    }else{
                        System.out.println("Task list is empty !");
                    }
                    continue;
                case"deadline":
                        int divPos = line.indexOf("/");
                        String deadlineTask = line.substring(9,divPos);
                        String deadlineDate = line.substring(divPos+4);
                        Deadline taskDeadline = new Deadline(deadlineTask,deadlineDate);
                        addList(taskDeadline);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(taskDeadline);
                        System.out.println("Now you have " + count + " tasks in the list.");
                    continue;
                case"todo":
                    if(userInput.length<2){
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                        String todoTask = line.substring(5);
                        Todo taskTodo = new Todo(todoTask);
                        addList(taskTodo);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(taskTodo);
                        System.out.println("Now you have " + count + " tasks in the list.");

                    continue;
                case"event":
                        int Pos = line.indexOf("/");
                        String eventsTask = line.substring(6,Pos);
                        String eventsDate = line.substring(Pos+4);
                        Events taskEvent = new Events(eventsTask,eventsDate);
                        addList(taskEvent);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(taskEvent);
                        System.out.println("Now you have " + count + " tasks in the list.");

                    continue;
                default:
                    if(userInput[0].equals(("blah"))){
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    addList(new Task(line));

                    continue;
            }
            break;
        }
    }
}


