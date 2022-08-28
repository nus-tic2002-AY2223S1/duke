import java.util.Scanner;

public class Duke {
    public static void printTaskList(Task[] inputList) {
        for (int count = 0; count < inputList.length; count++) {
            if (inputList[count] != null) {
                System.out.println((count + 1) + "." + "[" + inputList[count].getStatusIcon() + "] " + inputList[count].getDescription());
            }
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {

        // Create a taskList containing task object
        Task[] taskList = new Task[100];
        Scanner input = new Scanner(System.in);
        System.out.print("Hello! I'm Duke\n" + "What can I do for you?:\n");
        int counter = 0;
        while (true) {
            String line = input.nextLine();
            String[] lineList = line.split(" ");
            String firstWord = lineList[0].toLowerCase();
            switch (firstWord) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    printTaskList(taskList);
                    continue;
                case "mark":
                    int markIndex = Integer.parseInt(lineList[1]) - 1;
                    Task selectedTask = taskList[markIndex];
                    selectedTask.markDone();
                    System.out.println("Nice! I've marked this task not done:");
                    System.out.println("[" + selectedTask.getStatusIcon() + "] " + selectedTask.getDescription() + "\n");
                    continue;
                case "unmark":
                    int unMarkIndex = Integer.parseInt(lineList[1]) - 1;
                    Task unSelectedTask = taskList[unMarkIndex];
                    unSelectedTask.unMarkDone();
                    System.out.println("OK, I've marked this task as done yet:");
                    System.out.println("[" + unSelectedTask.getStatusIcon() + "] " + unSelectedTask.getDescription() + "\n");
                    continue;
                default:
                    taskList[counter] = new Task(line);
                    counter++;
                    System.out.println(line + "\n");
            }
        }
    }
}