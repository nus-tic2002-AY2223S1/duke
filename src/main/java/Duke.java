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
            int index = line.indexOf(" ")+1;
            int indexNum =Integer.parseInt(line.substring(index));

            System.out.print(indexNum);
            switch(line){
                case"bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case"list":
                    printList();
                    continue;
                case"mark":
                    if(tasks[count-1] !=null) {
                        Task markTask = tasks[indexNum];
                        System.out.println("Nice! I've marked this task as done:");
                        markTask.markAsDone();
                        System.out.println(markTask);
                    }else{
                        System.out.println("Task list is empty !");
                    }
                    continue;
                case"unmark":
                    if(tasks[count-1] !=null) {
                        Task unmarkTask = tasks[indexNum];
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(unmarkTask);
                    }else{
                        System.out.println("Task list is empty !");
                    }
                    continue;
                case"deadline":
                    if(tasks[count-1] !=null) {
                        int divPos = line.indexOf("/");
                        Deadline deadline = (Deadline) tasks[divPos];
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(deadline);
                        System.out.println("Now you have " + count + " in the list");
                    }else{
                        System.out.println("Task list is empty !");
                    }
                default:
                    addList(new Task(line));
            }
        }
    }
}


