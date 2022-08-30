import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static Scanner scanner;
    public static String input;
    public static String[] inputWords;
    public static String action;
    public static ArrayList<Task> taskList;

    public static void getUserInput() {
        input = scanner.nextLine();
        inputWords = input.toLowerCase().split(" ");
        if (inputWords.length > 0){
            action = inputWords[0];
        }
    }

    public static void printList() {
        String printStr = "\t Here are the tasks in your list: \n";
        for (int i = 0; i < taskList.size(); i++) {
            printStr += String.format("\t %d.[%s] %s\n", i + 1, taskList.get(i).getStatusIcon(), taskList.get(i).getDescription());
        }

        PrintUtil.printWithIndentation("%s%s%s", printStr);
    }

    public static void addTask(){
        taskList.add(new Task(input.substring(4)));
        PrintUtil.printWithIndentation("%s\t added: %s\n%s", input.substring(4));
    }

    public static void updateTaskStatus(boolean isDone){
        if (inputWords.length > 1){
            int index = Integer.parseInt(inputWords[1]) - 1;
            taskList.get(index).updateStatus(isDone);
        }
    }

    public static void main(String[] args) {

        //greetings
        PrintUtil.printWithIndentation("%s%s%s", "\t Hello! I'm Duke\n \t What can I do for you?\n");

        //init
        scanner = new Scanner(System.in);
        taskList = new ArrayList<>();

        taskList.add(new Task("read book"));
        taskList.add(new Task("return book"));
        taskList.add(new Task("buy bread"));


        //start
        getUserInput();

        //cases
        while (!action.equals("bye")) {

            switch (action) {
                case "list":
                    printList();
                    break;
                case "mark":
                    updateTaskStatus(true);
                    break;
                case "unmark":
                    updateTaskStatus(false);
                    break;
                case "add":
                    addTask();
                    break;
            }
            getUserInput();
        }

        //bye
        PrintUtil.printWithIndentation("%s\t %s\n%s", "Bye. Hope to see you again soon!");
    }
}
