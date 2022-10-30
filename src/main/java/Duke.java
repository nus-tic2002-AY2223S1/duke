import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
public class Duke {
    public static Task[] arr = {};
    public static ArrayList<Task> arrayList = new ArrayList<>(Arrays.asList(arr));
    public static String format = "    ─────────────────────────────────────────\n    %s\n    ─────────────────────────────────────────\n";

    public static int getIndex(String s) {
        if (!isInteger(s)){
            return -1;
        }
        int i = Integer.parseInt(s)-1;
        if (i >= arr.length){
            System.out.format(format, "No such index.");
            return -1;
        }
        return i;
    }
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            System.out.format(format, "Enter the item index.");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.printf("Hello from Duke. What can I do for you?%n");
        Scanner inputRaw = new Scanner(System.in).useDelimiter("\n");

        while(true){
            //whole line of input
            String inputLine = inputRaw.nextLine();
            //splitting input into <command> + <task name + at/by>
            String[] processedInput = inputLine.split(" ", 2);

            //verify command
            switch (processedInput[0]){
                case "list":
                    printList(true);
                    continue;
                case "mark":
                    int idxMark = getIndex(processedInput[1]);
                    if (idxMark != -1){
                        arr[idxMark].markTask();
                        printSingle(idxMark,true);
                    }
                    continue;
                case "unmark":
                    int idxUnmark = getIndex(processedInput[1]);
                    if (idxUnmark != -1){
                        arr[idxUnmark].unmarkTask();
                        printSingle(idxUnmark,false);
                    }
                    continue;
                case "todo":
                    //split <task name + /by>
                    String[] todoByInput = processedInput[1].split("/by", 2);
                    arrayList.add(new Todo(todoByInput[0],todoByInput.length == 1 ? null: todoByInput[1]));
                    arr = arrayList.toArray(arr);
                    printNewTaskAdded();
                    continue;
                case "deadline":
                    String[] deadlineByInput = processedInput[1].split("/by", 2);
                    arrayList.add(new Deadline(deadlineByInput[0],deadlineByInput.length == 1 ? null: deadlineByInput[1]));
                    arr = arrayList.toArray(arr);
                    printNewTaskAdded();
                    continue;
                case "event":
                    String[] eventByInput = processedInput[1].split("/at", 2);
                    arrayList.add(new Event(eventByInput[0],eventByInput.length == 1 ? null: eventByInput[1]));
                    arr = arrayList.toArray(arr);
                    printNewTaskAdded();
                    continue;
                case "bye":
                    System.out.format(format, "Bye. Hope to see you again soon!");
                    return;
                default:
                    System.out.format(format, "Specify Todo / Deadline / Event. \n\tE.g. Todo <Task Name>");
            }
        }
    }

    public static void printList(Boolean withIndex) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n    ");
        for( int i = 0; i < arr.length; i++){
            String suffix = "";
            if (i != arr.length -1){
                suffix = "\n    ";
            }
            if (withIndex){
                output.append(i+1);
            }
            output.append(".").append(arr[i].toString()).append(suffix);
        }
        System.out.format(format, output);
    }

    public static void printSingle(int index, Boolean isMark) {
        StringBuilder output = new StringBuilder();
        output.append(isMark ? "Nice! I've marked this task as done:\n    " :"OK, I've marked this task as not done yet:\\n    \"" ).
                append(arr[index].toString());
        System.out.format(format, output);
    }

    public static void printNewTaskAdded() {
        StringBuilder output = new StringBuilder();
        output.append("Got it. I've added this task:\n\t").
                append(arr[arr.length - 1].toString()).
                append("\n\tNow you have ").
                append(arr.length).
                append(arr.length > 1 ? " tasks " :" task ").
                append("in the list.");
        System.out.format(format, output);
    }
    }
