import java.time.LocalDateTime;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
    private static final ArrayList<Task> tasklist = new ArrayList<>();
    protected static int indexTask = 0;

    public static class Ui {
        private static final String DIVIDER = "____________________________________________________________";
        private static final String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        public static void greet() {
            System.out.println("Hello from\n" + LOGO);
            System.out.println("Hello Siying!\n" + "How's your day today?\n" + DIVIDER + System.lineSeparator() + "Below are your tasks due today\n" + DIVIDER );
            System.out.println("What would you like to do now?");
        }

        public static void readInput(){
            String userInput;
            Scanner in;

            do {
                in = new Scanner(System.in);
                userInput = in.nextLine();

                Parser.identifyCommand(userInput);
            } while (!userInput.equals("bye"));
        }

        public static void printList(){
            for (int i = 0; i < indexTask; i++) {
                System.out.println(i + 1 + ".[" + tasklist.get(i).getTypetask() + "]" +
                        "[" + tasklist.get(i).getDone() + "] " +
                        tasklist.get(i).getDescription() + " " +
                        tasklist.get(i).getString());
            }
        }
        public static void printTask(int index){
            System.out.println("[" + tasklist.get(index).getTypetask() + "]" +
                            "[" + tasklist.get(index).getDone() + "] " +
                            tasklist.get(index).getDescription() + " " +
                            tasklist.get(index).getString());
        }
        public static void printAdded(int index){
            System.out.println("Got it. I've added this task:");
            Ui.printTask(index);
            System.out.println("Now you have " + (indexTask+1) + " tasks in the list");
        }
        public static void printDeleted(int index){
            System.out.println("Noted. I've removed this task:");
            Ui.printTask(index);
            System.out.println("Now you have " + (indexTask-1) + " tasks in the list");
        }

        public static void exit() {
            System.out.println("Bye Siying!" + System.lineSeparator() + "Enjoy your day ;)");
        }
    }

    public static class Parser {

        public static void identifyCommand(String input) {
            int desPosition = input.indexOf(" ");
            int timePosition = input.indexOf("/");
            String command;
            String desTask;
            String datetimeTask = null;

            if(input.isEmpty()){
                throw new EmptyStackException();
            } else {
                // command mark, unmark
                if (desPosition < 0) {
                    command = input;
                    desTask = "";
                } else if (timePosition < 0){
                    command = input.substring(0, desPosition);
                    desTask = input.substring(desPosition + 1);
                // command event, deadline, postpone
                } else {
                    command = input.substring(0, desPosition);
                    desTask = input.substring(desPosition + 1, timePosition - 1);
                    datetimeTask = input.substring(timePosition + 4);
                    if(datetimeTask.length() == 16){
                        datetimeTask = formatDateTime(datetimeTask);
                    } else if (datetimeTask.length() == 10){
                        datetimeTask = formatDate(datetimeTask);
                    }
                }

                switch (command) {
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        Ui.printList();
                        break;
                    case "todo":
                        addTodo(desTask, indexTask);
                        indexTask++;
                        break;
                    case "event":
                        addEvent(desTask, datetimeTask, indexTask);
                        indexTask++;
                        break;
                    case "deadline":
                        addDeadline(desTask, datetimeTask, indexTask);
                        indexTask++;
                        break;
                    case "mark":
                        markCommand(Integer.parseInt(desTask)-1);
                        break;
                    case "unmark":
                        unmarkCommand(Integer.parseInt(desTask)-1);
                        break;
                    case "delete":
                        deleteCommand(Integer.parseInt(desTask)-1);
                        indexTask--;
                        break;
                    case "find":
                        findCommand(desTask);
                    case "due":
                        duetaskCommand(desTask);
                    case "postpone":
                        postponeCommand(Integer.parseInt(desTask)-1, datetimeTask);
                    case "bye":
                        break;
                    default:
                        System.out.println("Invalid Action");
                        break;
                }
            }
        }
        public static void addTodo(String destask, int index){
            tasklist.add(new Todo(destask));
            Ui.printAdded(index);
        }
        public static void addEvent(String destask, String datetimetask, int index){
            tasklist.add(new Event(destask, datetimetask));
            Ui.printAdded(index);
        }
        public static void addDeadline(String destask, String datetimetask, int index){
            tasklist.add(new Deadline(destask, datetimetask));
            Ui.printAdded(index);
        }
        public static void markCommand(int index){
            tasklist.get(index).markDone();
            System.out.println("Nice! I've marked this task as done:");
            Ui.printTask(index);
        }
        public static void unmarkCommand(int index){
            tasklist.get(index).unmarkDone();
            System.out.println("OK, I've marked this task as not done yet:");
            Ui.printTask(index);
        }
        public static void deleteCommand(int index){
            Ui.printDeleted(index);
            tasklist.remove(index);
        }
        public static void findCommand(String find){
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < indexTask; i++){
                if(tasklist.get(i).matchFind(find)){
                    Ui.printTask(i);
                }
            }
        }
        public static void duetaskCommand(String date){
            System.out.println("Here are the tasks in your list matching the due date:");
            for (int i = 0; i < indexTask; i++){
                if(tasklist.get(i).matchDue(formatDate(date))){
                    Ui.printTask(i);
                }
            }
        }
        public static void postponeCommand(int index, String date){
            System.out.println("OK, I've postpone this task to new date below:");
            tasklist.get(index).setDate(date);
            Ui.printTask(index);
        }
        public static String formatDateTime(String datetime){
            LocalDateTime formattedDatetime = LocalDateTime.parse(datetime);
            DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("EEE MMM dd, yyyy HH:mm");
            return formattedDatetime.format(dateformatter);
        }
        public static String formatDate(String date){
            LocalDate formattedDate = LocalDate.parse(date);
            DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("EEE MMM dd, yyyy");
            return formattedDate.format(dateformatter);
        }

    }

    public static class Task {

        private String description;
        private Boolean isDone;
        protected String typeTask;

        public Task(String description){
            this.description = description;
            this.isDone = false;
            this.typeTask = " ";
        }

        public String getDescription(){
            return this.description;
        }
        public String getDone(){
            return (this.isDone ? "X" : " ");
        }
        public String getTypetask(){
            return this.typeTask;
        }
        public String getString(){
            return "";
        }

        public void markDone(){
            this.isDone = true;
        }
        public void unmarkDone(){
            this.isDone = false;
        }
        public boolean matchFind(String find){
            return this.description.contains(find);
        }
        public boolean matchDue(String duedate){
            return false;
        }
        public void setDate(String newdate){

        }
    }
    public static class Todo extends Task{
        public Todo(String description){
            super(description);
            super.typeTask = "T";
        }
    }
    public static class Event extends Task{
        private String at;
        public Event(String description, String at){
            super(description);
            super.typeTask = "E";
            this.at = at;
        }

        public String getString(){
            return super.getString() + "(at: " + this.at + ")";
        }
        public boolean matchDue(String duedate){
            return this.at.equals(duedate);
        }
        public void setDate(String newdate){
            this.at = newdate;
        }
    }
    public static class Deadline extends Task{
        private String by;
        public Deadline(String description, String by){
            super(description);
            super.typeTask = "D";
            this.by = by;
        }

        public String getString(){
            return super.getString() + "(by: " + this.by + ")";
        }
        public boolean matchDue(String duedate){
            return this.by.equals(duedate);
        }
        public void setDate(String newdate){
            this.by = newdate;
        }
    }

    public static void main(String[] args) {
        Ui.greet();
        Ui.readInput();
        Ui.exit();
    }

}