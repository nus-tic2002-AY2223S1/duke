import java.util.Scanner;


public class Duke {
    public static int listSize = 0;
    public static void main(String[] args) {

        String logo = "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀     ⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⠿⠿⣷⣦⣀⠀⢀⣀⣀⣤⣤⣤⣤⣤⣄⣀⡀⢀⣤⣶⠿⠿⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⡟⢸⠀⠀⠉⠻⠿⠛⠛⠋⠉⠉⠉⠉⠉⠙⠛⠻⠿⠋⠁⠀⠀⢻⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⡇⠘⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡀⠀⢸⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⡿⠛⠀⠀⠐⢠⣦⣦⣦⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣄⠀⠀⠙⣿⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⡟⠀⠀⠀⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿⣿⠀⠸⣿⣿⣿⣿⣿⣿⣿⡇⡄⠀⠀⠈⢿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠇⠀⠀⠀⠀⠀⠀⢻⣿⣿⣿⣿⣿⡿⠁⣼⣄⠙⠿⣿⣿⣿⡿⠟⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡆⠀⠀⠀⠀⠀⣴⡿⠛⢿⡏⠉⠈⠰⡿⠛⠻⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⡀⢀⠀⠀⣸⣿⠁⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢙⣿⣶⡄⡾⠟⠃⠀⠀⣾⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠿⣯⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⠃⠀⠀⠀⠀⠀⠀⣴⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⠀⠀⠀⠘⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⢀⡀⡀⣀⣀⣀⠀⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠊⠀⠀⠀⠀⠀⢀⣀⠀⠀⠀⠀⠀⠀⣤⣾⠿⠛⠃⠀⠀⠀⣸⣷⠀⠀⠀⠀⣀⣀⡀⡀⠀\n" +
                "⠺⠿⠛⠛⠛⠛⠿⠿⠿⠿⠿⠿⠿⠿⠿⠷⣶⢶⡶⡶⠶⠿⠿⠿⠿⠟⠛⠛⠻⠿⠿⠿⢿⣿⣀⣠⣤⣤⣤⣤⣶⠿⠛⠛⠛⠛⠛⠛⠛⠛⠻⠃\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠉⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";


        System.out.println(logo);
        System.out.println("Hello! I'm Milo");
        System.out.println("What can I do for you?");

        Task[] list = new Task[100];

        while(true){
            String input;

            Scanner in = new Scanner(System.in);
            input = in.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (input.equals("list")){
                printList(list);
                continue;
            }

            if (input.contains("mark")){
                changeStatus(list,input);
                continue;
            }

            addToList(list,input);
        }

    }

    public static void printList(Task[] list){
        for (int i = 0; i < listSize; i++) {
            System.out.print(i+1 + ". ");
            System.out.println(list[i].getDescription());
        }
    }

    public static void addToList(Task[] list, String input){
        try {
            String[] inputArr = input.split(" ", 2); //To get the first word from input
            String taskType = inputArr[0];

            String[] taskValidator = {"todo", "deadline", "event"};
            boolean taskValid = false;
            for (String s : taskValidator){
                if (taskType.equals(s)) {taskValid = true; break;}
            }
            if (!taskValid){throw new InvalidTaskCreation();}
            if (inputArr.length == 1){throw new InvalidTaskCommand();}

            String taskDescription = inputArr[1];
            String dueDate = "No due date"; //Due date default value

            if (taskDescription.contains("/")){
                inputArr = taskDescription.split("/", 2); //To get the due date
                taskDescription = inputArr[0].trim();
                dueDate = inputArr[1].replaceFirst(" ", ": "); //add ":" into due date
            }

            switch (taskType.toLowerCase()){
                case "todo":
                    list[listSize] = new Todo(taskDescription);
                    break;
                case "deadline":
                    list[listSize] = new Deadlines(taskDescription, dueDate);
                    break;
                case "event":
                    list[listSize] = new Events(taskDescription, dueDate);
                    break;
                default:
                    throw new InvalidTaskCreation();
            }

            listSize++;
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + list[listSize-1].getDescription());
            System.out.println("Now you have " + listSize + " tasks in the list.");
        } catch (InvalidTaskCreation e){
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (InvalidTaskCommand e){
            System.out.println("☹ OOPS!!! The description of a "+ input +" cannot be empty.");
        }
    }

    public static void changeStatus(Task[] list, String input){
        int start = input.indexOf(' '); //Detect position of white space
        String num =  input.substring(start+1); //Get the number from the end of the input
        int selection = Integer.parseInt(num) - 1;

        if(input.contains("un")){
            System.out.println("OK, I've marked this task as not done yet:");
            list[selection].setStatus(false);
        } else {
            System.out.println("Nice! I've marked this task as done:");
            list[selection].setStatus(true);
        }
        System.out.println(list[selection].getDescription());
    }
}
