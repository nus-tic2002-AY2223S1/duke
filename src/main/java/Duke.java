import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        public String getLabel(){
            return this.label;
        }
        private Command(String label) {
            this.label = label;
        }
    }

    public enum Action{
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete");

        public final String label;
        public String getLabel(){
            return this.label;
        }
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
            d.Warning("OOPS!!! The selection to " + a.getLabel() +" cannot be empty.");
            return;
        }

        int idx = getIndex(s[1]);
        if (idx != -1){
            switch (a){
                case MARK:
                    arrayList.get(idx).markTask();
                    printSingle(idx, true);
                    break;
                case UNMARK:
                    arrayList.get(idx).unmarkTask();
                    printSingle(idx, false);
                    break;
                case DELETE:
                    printTaskRemovedByIndex(idx);
                    arrayList.remove(idx);
                    break;
            }
        }
    }

    public static void saveFile() throws IOException {
        FileWriter writer = new FileWriter("output.txt");
        for(Task str: arrayList) {
//            String s = str.getType() + "|" +
//                    str.getStatusIcon() + "|" +
//                    str.getDescription() + "|" +
//                    str.getDue();
            writer.write(str.toString() + System.lineSeparator());
        }
        writer.close();
    }

    //processCommand Processes the commands of event / deadline / to-do
    public static void processCommand(Command c,String[] s) {
        if (s.length == 1){
            d.Warning("OOPS!!! The description of " + c.getLabel() +" cannot be empty.");
            return;
        }

        switch (c){
            case EVENT:
                String[] eventByInput = s[1].split("/at", 2);
                arrayList.add(new Event(eventByInput[0].trim(),eventByInput.length == 1 ? null: eventByInput[1].trim()));
                break;
            case DEADLINE:
                String[] deadlineByInput = s[1].split("/by", 2);
                arrayList.add(new Deadline(deadlineByInput[0].trim(),deadlineByInput.length == 1 ? null: deadlineByInput[1].trim()));
                break;
            case TODO:
                String[] todoByInput = s[1].split("/by", 2);
                arrayList.add(new Todo(todoByInput[0].trim(),todoByInput.length == 1 ? null: todoByInput[1].trim()));
                break;
        }
        printNewTaskAdded();

        try{
            saveFile();
        }catch(IOException e){
            d.Warning("IOException");
        }
    }
    public static int getIndex(String s) {
        if (!isInteger(s)){
            return -1;
        }
        int i = Integer.parseInt(s)-1;
        if (i < 0 || i >= arrayList.size()){
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
                case "delete":
                    processAction(Action.DELETE,processedInput);
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
        if (arrayList.size() == 0){
            s.append("You have no task.");
        }else{
            s.append("Here are the tasks in your list:\n    ");
            for( int i = 0; i < arrayList.size(); i++){
                String suffix = "";
                if (i != arrayList.size() -1){
                    suffix = "\n    ";
                }
                if (withIndex){
                    s.append(i+1);
                }
                s.append(".").append(arrayList.get(i).toString()).append(suffix);
            }
        }
        d.Output(s);
    }

    public static void printSingle(int index, Boolean isMark) {
        StringBuilder s = new StringBuilder();
        s.append(isMark ? "Nice! I've marked this task as done:\n    " :"OK, I've marked this task as not done yet:\\n    \"" ).
                append(arrayList.get(index).toString());
        d.Output(s);
    }

    public static void printNewTaskAdded() {
        StringBuilder s = new StringBuilder();
        s.append("Got it. I've added this task:\n\t").
                append(arrayList.get(arrayList.size() - 1).toString()).
                append("\n\tNow you have ").
                append(arrayList.size()).
                append(arrayList.size() > 1 ? " tasks " :" task ").
                append("in the list.");
        d.Output(s);
    }

    public static void printTaskRemovedByIndex(int index) {
        StringBuilder s = new StringBuilder();
        s.append("Noted. I've removed this task:\n\t").
                append(arrayList.get(index).toString()).
                append("\n\tNow you have ").
                append(arrayList.size() - 1).
                append(arrayList.size() > 1 ? " tasks " :" task ").
                append("in the list.");
        d.Output(s);
    }
    }
