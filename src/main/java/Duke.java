import java.util.ArrayList;
import java.util.Scanner;

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
                System.out.println(i + 1 + ".[" + tasklist.get(i).getDone() + "] " + tasklist.get(i).getDescription());
            }
        }
        public static void printTask(int index){
            System.out.println(" [" + tasklist.get(index).getDone() + "] " + tasklist.get(index).getDescription());
        }


        public static void exit() {
            System.out.println("Bye Siying!" + System.lineSeparator() + "Enjoy your day ;)");
        }
    }

    public static class Parser {

        public static void identifyCommand(String input) {
            int descriptionPosition = input.indexOf(" ");
            String command;
            String taskDes;

            if (descriptionPosition < 0) {
                command = input;
                taskDes = "";
            } else {
                command = input.substring(0, descriptionPosition);
                taskDes = input.substring(descriptionPosition + 1);
            }

            switch (command) {
                case "list":
                    Ui.printList();
                    break;
                case "mark":
                    markCommand(Integer.parseInt(taskDes)-1);
                    break;
                case "unmark":
                    unmarkCommand(Integer.parseInt(taskDes)-1);
                    break;
                case "bye":
                    break;
                default:
                    tasklist.add(new Task(input));
                    indexTask++;
                    addCommand(input);
                    break;
            }
        }
        public static void addCommand(String input){
            System.out.println("added: " + input);
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

    }

    public static class Task {
        private String description;
        private Boolean isDone;

        public Task(String description){
            this.description = description;
            this.isDone = false;
        }

        public String getDescription(){
            return this.description;
        }
        public String getDone(){
            return (this.isDone ? "X" : " ");

        }
        public void markDone(){
            this.isDone = true;
        }
        public void unmarkDone(){
            this.isDone = false;
        }


    }

    public static void main(String[] args) {
        Ui.greet();
        Ui.readInput();
        Ui.exit();
    }

}