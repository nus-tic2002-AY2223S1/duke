import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
public class Duke {
    public static Task[] arr = {};
    public static ArrayList<Task> arrayList = new ArrayList<>(Arrays.asList(arr));
    public static String format = "    ─────────────────────────────────────────\n    %s\n    ─────────────────────────────────────────\n";

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
        Scanner myObj = new Scanner(System.in).useDelimiter("\n");

        while(true){
            String input = myObj.nextLine();
            String[] arrInput = input.split(" ", 2);

            switch (arrInput[0]){
                case "list":
                    printList(true);
                    continue;
                case "mark":
                    if (!isInteger(arrInput[1])){
                        continue;
                    }
                    int idxMark = Integer.parseInt(arrInput[1])-1;
                    if (idxMark < arrInput.length){
                        continue;
                    }
                    arr[idxMark].markTask();
                    printSingle(idxMark,true);
                    continue;
                case "unmark":
                    if (!isInteger(arrInput[1])){
                        continue;
                    }
                    int idxUnmark = Integer.parseInt(arrInput[1])-1;
                    if (idxUnmark < arrInput.length){
                        continue;
                    }
                    arr[idxUnmark].unmarkTask();
                    printSingle(idxUnmark,false);
                    continue;
                case "bye":
                    System.out.format(format, "Bye. Hope to see you again soon!");
                    return;
                case "todo":
                    continue;
                case "":
                    continue;
                default:
                    arrayList.add(new Task(input));
                    arr = arrayList.toArray(arr);
                    System.out.format(format, "added: "+input);
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
            output.append(".[").append(arr[i].getStatusIcon()).append("] ").append(arr[i].description).append(suffix);
        }
        System.out.format(format, output);
    }

    public static void printSingle(int index, Boolean isMark) {
        StringBuilder output = new StringBuilder();
        if (isMark){
            output.append("Nice! I've marked this task as done:\n    ");
        }else{
            output.append("OK, I've marked this task as not done yet:\n    ");
        }
        output.append("  [").append(arr[index].getStatusIcon()).append("] ").append(arr[index].description);
        System.out.format(format, output);
    }
    }
