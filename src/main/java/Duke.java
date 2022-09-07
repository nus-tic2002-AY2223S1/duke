import java.util.Scanner;

public class Duke {
    private static int count = 0;
    private static Task[] tasks = new Task[100];

    public static void addList(Task t){
        tasks[count]=t;
        count++;
    }
    public static void print(String t){
        if(t.equals("list")){
                System.out.println("Here are the tasks in your list:");
            for(int i=1;i<=count;i++){
                System.out.println(i + "." + " " + tasks[i-1]);
            }
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

        print("list");

    }
}


