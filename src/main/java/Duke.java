import java.util.Scanner;

public class Duke {
    private static int count = 0;
    private static Task[] tasks = new Task[100];     // the tasks was random allocated 100 elements with value null ?


    public static void addList(Task t){
        tasks[count]=t;
        count++;
    }
    public static void printList(){
        System.out.println("Here are the tasks in your list:");
            for(int i=1;i<=count;i++){
                System.out.println(i + "." + " " + tasks[i-1]);
            }
        }


    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
<<<<<<< HEAD
        
        while(true){
            line = in.nextLine();
           int index = line.indexOf(" ")+1;
          int indexNum =Integer.parseInt(line.substring(index));
            System.out.print(indexNum);
            switch(line){
                case"bye":
=======


        while(true){
            line = in.nextLine();
            String[] userInput =  line.split(" ");
            //int indexNum = Integer.parseInt(userInput[1]);  // can not set variable indexNum for both mark and unmark ?
           // System.out.println(indexNum);
            switch(userInput[0]){
                case "bye":
>>>>>>> 8b41a97275937e716722dc8e56e52a259fd418dc
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    printList();
                    continue;
<<<<<<< HEAD
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
=======
                case "mark":
                     if(tasks[count-1]!=null){
                         int indexMark = Integer.parseInt(userInput[1]);
                         Task markTask = tasks[indexMark-1];
                         System.out.println("Nice! I've marked this task as done:");
                         markTask.markAsDone();
                         System.out.println(markTask);
                     }else{
                         System.out.println("Task list is empty");
                     }
                     continue;
                case "unmark":
                    if(tasks[count-1]!=null){
                        int indexUnmark = Integer.parseInt(userInput[1]);
                        Task unmarkTask = tasks[indexUnmark-1];
                        System.out.println("OK, I've marked this task as not done yet:");
                        unmarkTask.markAsNotdone();
                        System.out.println(unmarkTask);
                    }
>>>>>>> 8b41a97275937e716722dc8e56e52a259fd418dc
                    continue;
                default:
                    addList(new Task(line));
                    continue;
            }
            break;
        }
    }

}

