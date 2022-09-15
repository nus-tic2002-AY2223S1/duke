import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static String line = "\t____________________________________________________________";
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;
    static String markUnmarkPattern = "(mark|unmark)\\s*(\\d+)";


    public static void addTask(Task t){
        tasks[taskCount] = t;
        taskCount++;
        String output = String.format("added: %s",t.description);
        System.out.println("\t"+output);
    }

    public static void printTasks(){
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++){
            System.out.print("\t "+(i+1)+".");
            tasks[i].print();
        }
    }

    public static String inputCleaner(String input){
        return input.trim();
    }

    public static void main(String[] args) {

        String greeting = line + "\n\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n"
                +line;
        System.out.println(greeting);

        Scanner in = new Scanner(System.in);
        String input = inputCleaner(in.nextLine());

        while(input!=null){
            System.out.println(line);
            String output = "";
            if(input.equalsIgnoreCase("bye")){
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            }
            else if(input.equalsIgnoreCase("list")){
                printTasks();
            }
            else if(input.startsWith("mark")|input.startsWith("unmark")){
                Pattern r = Pattern.compile(markUnmarkPattern);
                Matcher m = r.matcher(input);
                if (m.find()){
                    int taskNumber = Integer.parseInt(m.group(2));
                    if(m.group(1).equalsIgnoreCase("mark")){
                        tasks[taskNumber-1].updateStatus(true);
                    }
                    if(m.group(1).equalsIgnoreCase("unmark")) {
                        tasks[taskNumber - 1].updateStatus(false);
                    }
                }else {
                    System.out.println("NO MATCH");
                }
            }
            else {
                Task t = new Task(input);
                addTask(t);
            }
            System.out.println(line);
            input = inputCleaner(in.nextLine());
        }

    }
}


