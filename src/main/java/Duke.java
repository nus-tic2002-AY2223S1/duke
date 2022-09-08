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
                    for (int count = 0; count < taskList.length; count++) {
                        if (taskList[count] != null) {
                            System.out.println((count + 1) + "." + taskList[count].toString());
                        }
                    }
                    continue;
                case "mark":
                    int markIndex = Integer.parseInt(lineList[1]) - 1;
                    Task selectedTask = taskList[markIndex];
                    selectedTask.markDone();
                    System.out.println("Nice! I've marked this task not done:");
                    System.out.println(selectedTask.toString());
                    continue;
                case "unmark":
                    int unMarkIndex = Integer.parseInt(lineList[1]) - 1;
                    Task unSelectedTask = taskList[unMarkIndex];
                    unSelectedTask.unMarkDone();
                    System.out.println("OK, I've marked this task as done yet:");
                    System.out.println(unSelectedTask.toString());
                    continue;
                case "deadline":
                    String[] bySplitList = line.split("/by ");
                    String deadLine = bySplitList[1];
                    String deadLineTask = line.substring(9, line.indexOf("/by"));
                    System.out.println("Got it. I've added this task:");
                    taskList[counter] = new Deadline(deadLineTask, deadLine);
                    System.out.println(taskList[counter].toString());
                    counter++;
                    System.out.println("Now you have "+ (counter) + " tasks in the list.");
                    continue;
                case "todo":
                    System.out.println("Got it. I've added this task:");
                    String toDoTask = line.split("todo ")[1];
                    taskList[counter] = new ToDo(toDoTask);
                    System.out.println(taskList[counter].toString());
                    counter++;
                    System.out.println("Now you have "+ (counter) + " tasks in the list.");
                    continue;
                case "event":
                    String[] atSplitList = line.split("/at ");
                    String eventDate = atSplitList[1];
                    String eventTask = line.substring(6, line.indexOf("/at"));
                    System.out.println("Got it. I've added this task:");
                    taskList[counter] = new Event(eventTask, eventDate);
                    System.out.println(taskList[counter].toString());
                    counter++;
                    System.out.println("Now you have "+ (counter) + " tasks in the list.");
                    continue;
                default:
                    taskList[counter] = new Task(line);
                    counter++;
                    System.out.println("added: "+ line + "\n");
            }
        }
    }
}