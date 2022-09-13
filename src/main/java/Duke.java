import java.util.Scanner;

public class Duke {
    private static int count = 0;
    private static Task[] tasks = new Task[100];

    public static void addList(Task t){
        tasks[count]=t;
        count++;
    }
    public static void printList(){
        for(int i=1;i<=count;i++){
            System.out.println(i + "." + " " + tasks[i-1]);
        }
    }


    public static void main(String[] args) {
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
                    if(tasks[count-1] !=null) {

                        int indexMark = Integer.parseInt(userInput[1]);
                        Task markTask = tasks[indexMark-1];
                        System.out.println("Nice! I've marked this task as done:");
                        markTask.markAsDone();
                        System.out.println(markTask);
                    }else{
                        System.out.println("Task list is empty !");
                    }
                    continue;
                case"unmark":
                    if(tasks[count-1] !=null) {
                        int indexUnmark = Integer.parseInt(userInput[1]);
                        Task unmarkTask = tasks[indexUnmark-1];
                        System.out.println("OK, I've marked this task as not done yet:");
                        unmarkTask.markAsNotdone();
                        System.out.println(unmarkTask);
                    }else{
                        System.out.println("Task list is empty !");
                    }
                    continue;
                case"deadline":
                    if(tasks[count-1] !=null) {
                        int divPos = line.indexOf("/");
                        String deadlineTask = line.substring(9,divPos);
                        String deadlineDate = line.substring(divPos+4);
                        tasks[count] = new Deadline(deadlineTask,deadlineDate);
                        Deadline task =(Deadline) tasks[count];
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(task);
                        System.out.println("Now you have " + count + " in the list");
                    }else{
                        System.out.println("Task list is empty !");
                    }
                    continue;
                default:
                    addList(new Task(line));
                    continue;
            }
            break;
        }
    }
}


