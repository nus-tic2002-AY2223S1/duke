import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
public class Duke {

    public enum Command{
        DEADLINE("deadline"),
        EVENT("event"),
        TODO("todo");

        public final String label;
        private Command(String label) {
            this.label = label;
        }
    }

    public enum Action{
        MARK("mark"),
        UNMARK("unmark");

        public final String label;
        private Action(String label) {
            this.label = label;
        }
    }
    public static Task[] arr = {};
    public static ArrayList<Task> arrayList = new ArrayList<>(Arrays.asList(arr));
    public static String format = "    ─────────────────────────────────────────\n    %s\n    ─────────────────────────────────────────\n";
    static DukeOutput d = new DukeOutput(format);

    //processAction Processes the actions of mark / unmark
    public static void processAction(Action a,String[] s) {
        if (s.length == 1){
            d.Warning("OOPS!!! The selection to " + a.label +" cannot be empty.");
            return;
        }

        int idxMark = getIndex(s[1]);
        boolean isMark = false;
        if (idxMark != -1){
            switch (a){
                case MARK:
                    arr[idxMark].markTask();
                    isMark = true;
                    break;
                case UNMARK:
                    arr[idxMark].unmarkTask();
            }
            printSingle(idxMark, isMark);
        }
    }

    //processCommand Processes the commands of event / deadline / to-do
    public static void processCommand(Command c,String[] s){
        if (s.length == 1){
            d.Warning("OOPS!!! The description of " + c.label +" cannot be empty.");
            return;
        }

        switch (c){
            case EVENT:
                String[] eventByInput = s[1].split("/at", 2);
                arrayList.add(new Event(eventByInput[0],eventByInput.length == 1 ? null: eventByInput[1]));
                break;
            case DEADLINE:
                String[] deadlineByInput = s[1].split("/by", 2);
                arrayList.add(new Deadline(deadlineByInput[0],deadlineByInput.length == 1 ? null: deadlineByInput[1]));
                break;
            case TODO:
                String[] todoByInput = s[1].split("/by", 2);
                arrayList.add(new Todo(todoByInput[0],todoByInput.length == 1 ? null: todoByInput[1]));
                break;
        }
        arr = arrayList.toArray(arr);
        printNewTaskAdded();
    }
    public static int getIndex(String s) {
        if (!isInteger(s)){
            return -1;
        }
        int i = Integer.parseInt(s)-1;
        if (i <= 0 || i >= arr.length){
            d.Warning("No such index.");
            return -1;
        }
        return i;
    }
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            d.Warning("Enter the numeric item index.");
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        d.Output("Hello from Duke. What can I do for you?");
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
                    processAction(Action.MARK,processedInput);
                    continue;
                case "unmark":
                    processAction(Action.UNMARK,processedInput);
                    continue;
                case "todo":
                    processCommand(Command.TODO,processedInput);
                    continue;
                case "deadline":
                    processCommand(Command.DEADLINE,processedInput);
                    continue;
                case "event":
                    processCommand(Command.EVENT,processedInput);
                    continue;
                case "bye":
                    d.Output("Bye. Hope to see you again soon!");
                    return;
                case "":
                    continue;
                default:
                    d.Warning("OOPS!!! I'm sorry, but I don't know what that means :( \n\tSpecify Todo / Deadline / Event. \n\tE.g. Todo <Task Name>");
            }
        }
    }

    public static void printList(Boolean withIndex){
        StringBuilder s = new StringBuilder();
        if (arr.length == 0){
            s.append("You have no task.");
        }else{
            s.append("Here are the tasks in your list:\n    ");
            for( int i = 0; i < arr.length; i++){
                String suffix = "";
                if (i != arr.length -1){
                    suffix = "\n    ";
                }
                if (withIndex){
                    s.append(i+1);
                }
                s.append(".").append(arr[i].toString()).append(suffix);
            }
        }
        d.Output(s);
    }

    public static void printSingle(int index, Boolean isMark) {
        StringBuilder s = new StringBuilder();
        s.append(isMark ? "Nice! I've marked this task as done:\n    " :"OK, I've marked this task as not done yet:\\n    \"" ).
                append(arr[index].toString());
        d.Output(s);
    }

    public static void printNewTaskAdded() {
        StringBuilder s = new StringBuilder();
        s.append("Got it. I've added this task:\n\t").
                append(arr[arr.length - 1].toString()).
                append("\n\tNow you have ").
                append(arr.length).
                append(arr.length > 1 ? " tasks " :" task ").
                append("in the list.");
        d.Output(s);
    }
    }
