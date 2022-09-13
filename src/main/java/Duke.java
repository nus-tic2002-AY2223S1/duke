import java.util.Scanner;

public class Duke {
    public static String line = "\t____________________________________________________________\n";
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void addTask(Task t){
        tasks[taskCount] = t;
        taskCount++;
        String output = String.format("added: %s",t.description);
        System.out.println(line + "\t"+output+"\n"+line);
    }

    public static void printTasks(){
        for (int i = 0; i < taskCount; i++){
            System.out.print("\t"+(i+1)+".");
            tasks[i].print();
        }
    }

    public static void main(String[] args) {

        String greeting = line + "\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n"
                +line;

        System.out.println(greeting);

        Scanner in = new Scanner(System.in);
        String input;
        input = in.nextLine();


        String[] list = new String[100];

        while(input!=null){
            String output = "";
            if(input.equalsIgnoreCase("bye")){
                output = String.format("%s\t%s\n%s",line,"Bye. Hope to see you again soon!",line);
                System.out.println(output);
                break;
            }

            if(input.equalsIgnoreCase("list")){
                printTasks();
            }
//            todo : 正则判断 mark && unmark
            if(input.equalsIgnoreCase("mark")){
//            todo: handle function
//            handletask();
            }
            else{
                Task t = new Task(input);
                addTask(t);
            }
            input = in.nextLine();
        }

    }
}


