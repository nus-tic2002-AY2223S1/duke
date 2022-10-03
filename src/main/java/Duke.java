import Entity.Deadline;
import Entity.Event;
import Entity.Task;
import Entity.Todo;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static String line = "\t____________________________________________________________";
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;
    static String markUnmarkPattern = "(mark|unmark)\\s*(\\d+)";
    static String todoPattern = "(todo) ([\\S\\s]*)";
    static String deadlinePattern = "(deadline) ([\\S\\s]*)(\\/by) ([\\S\\s]*)";
    static String eventPattern = "(event) ([\\S\\s]*)(\\/at )([\\S\\s]*)";

    public static void addTask(Task t){
        tasks[taskCount] = t;
        taskCount++;
        System.out.println("\tGot it. I have added this task:");
        t.print();
        System.out.println("\tNow you have " + taskCount + " tasks in the list.");
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

    public static String getFirstInputWord(String input){
        String[] arrayOfInput = input.split(" ",2);
//        System.out.println(arrayOfInput[0]);
        return arrayOfInput[0];
    }


    public static void testing(){
//        tasks[0] = new Entity.Deadline("return book", "Monday");
//        tasks[0].print();
//        addTask(new Deadline("return book", "Monday"));
//        addTask(new Event("event project", "Monday","2-4pm"));
//        getFirstInputWord("retyrb viij");

    }

    public static void main(String[] args) {

//        testing();
        String greeting = line + "\n\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n"
                +line;
        System.out.println(greeting);

        Scanner in = new Scanner(System.in);
        String input = inputCleaner(in.nextLine());

        while(input!=null){

            System.out.println(line);
            String firstWord = getFirstInputWord(input);

            if(input.equalsIgnoreCase("bye")){
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            }
            
            switch(firstWord) {
                case "list":
                    printTasks();
                    break;

                case "mark":
                    Pattern r = Pattern.compile(markUnmarkPattern);
                    Matcher m = r.matcher(input);
                    if (m.find()) {
                        int taskNumber = Integer.parseInt(m.group(2));
                        if (m.group(1).equalsIgnoreCase("mark")) {
                            tasks[taskNumber - 1].updateStatus(true);
                        }
                    } else {
                        System.out.println("NO MATCH");
                    }
                    break;

                case "unmark":
                    r = Pattern.compile(markUnmarkPattern);
                    m = r.matcher(input);
                    if (m.find()) {
                        int taskNumber = Integer.parseInt(m.group(2));
                        if (m.group(1).equalsIgnoreCase("unmark")) {
                            tasks[taskNumber - 1].updateStatus(false);
                        }
                    } else {
                        System.out.println("NO MATCH");
                    }
                    break;

                case"todo":
                    r = Pattern.compile(todoPattern);
                    m = r.matcher(input);
                    if(m.find()){
                        addTask(new Todo(m.group(2)));
                    }
                   break;

                case "deadline":
                    r = Pattern.compile(deadlinePattern);
                    m = r.matcher(input);
                    if(m.find()){
                        String descrp = m.group(2);
                        String by = m.group(4);
                        addTask(new Deadline(descrp,by));
                    }
                    break;

                case "event":
                    r = Pattern.compile(eventPattern);
                    m = r.matcher(input);
                    if(m.find()){
                        String descrp = m.group(2);
                        String at = m.group(4);
                        addTask(new Event(descrp,at));
                    }
                    break;

                default:
                    Entity.Task t = new Entity.Task(input);
                    addTask(t);
            }
            System.out.println(line);
            input = inputCleaner(in.nextLine());

        }

    }
}


