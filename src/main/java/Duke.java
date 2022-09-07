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


//        line = in.nextLine();
//       // addList(new Task("read book"));
//        Task t = new Task("read book");
//        addList(t);
//        t.markAsDone();
//        addList(new Task("return book"));
//        addList(new Task("buy bread"));

        //printList("list");


        while(true){
            line = in.nextLine();
            int indexNum = Integer.parseInt(line.substring(line.indexOf(" ")+1));
            switch(line){
                case"bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case"list":
                    printList();
                    continue;
                case"mark":
                    Task markTask = tasks[indexNum];
                    System.out.println("Nice! I've marked this task as done:");
                    markTask.markAsDone();
                    System.out.println(markTask);
                    continue;
                case"unmark":
                    Task unmarkTask = tasks[indexNum];
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(unmarkTask);
                    continue;
                default:
                    addList(new Task(line));
            }
        }

    }
}


