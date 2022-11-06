import java.io.IOException;
import java.util.List;

import exceptions.InvalidStorageFilePathException;
import storage.Storage;
import taskList.*;
import ui.UI;
import static taskList.TaskList.*;
import static ui.ErrorMessages.*;
import static ui.UI.printError;
import static ui.UI.printLine;


public class Duke {

    private Storage storage;
    private TaskList taskList;
    private UI ui;

    public Duke() throws InvalidStorageFilePathException, IOException {
        ui = new UI();
        storage = new Storage();
        taskList = new TaskList();
        Storage.mainCaller();
    }


    private void start(String[] launchArgs) throws InvalidStorageFilePathException, IOException {
        this.ui = new UI();
        this.taskList = new TaskList();
        ui.printIntro();

        while (true) {
                String line = UI.getUserCommand();
                String[] lineSpaceSplit = line.split(" ");

                // enter bye to end chat
                if (line.startsWith("bye"))
                    exit();

                // to list all items
                else if (line.startsWith("list"))
                    taskList.listTask();

                // mark items
                else if (line.startsWith("mark"))
                    taskList.markTask(line);

                // unmarked items
                else if (line.startsWith("unmark"))
                    taskList.unmarkTask(lineSpaceSplit);

                // to do task
                else if (line.startsWith("todo"))
                    taskList.todoTask(line);

                // deadline task
                else if (line.startsWith("deadline"))
                    taskList.deadlineTask(line);

                // event task
                else if (line.startsWith("event"))
                    taskList.eventTask(line);

                // delete task
                else if (line.startsWith("delete")) {
                    if (!taskList.checkEmptyTaskList()) {
                        try {
                            taskList.deleteTask(lineSpaceSplit);
                        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                            UI.printArrayOOB();
                        }
                    }
                }

                // prompt user to enter valid input
                else
                    UI.printStandardError();
                Storage.mainCaller();
            }
        }

    //    Prints the Goodbye message and exits.
    private void exit() {
        ui.printBye();
        System.exit(0);
    }

    public static void main(String... launchArgs) throws InvalidStorageFilePathException, IOException {
        new Duke().start(launchArgs);
    }
}


//public class Duke {
//    public static List<Task> taskList = new ArrayList<>();
//
//    public static void listTask() {
//        int taskCount = 0;
//        System.out.println("____________________________________________________________\n" +
//                "Here are the tasks in your list:");
//        for (Task task : taskList) {
//            System.out.println(taskCount + 1 + ". " + task);
//            taskCount++;
//        }
//        System.out.println("____________________________________________________________");
//    }
//
//    public static void markTask(String[] lineSpaceSplit) {
//        int markedIndex = Integer.parseInt(lineSpaceSplit[1]);
//        try {
//            Task markedTask = taskList.get(markedIndex - 1);
//            markedTask.markAsDone();
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("____________________________________________________________" +
//                    "Task Number out of range. Please enter a valid input (Task Number: 1 " + "-" + taskList.size() + ")" +
//                    "____________________________________________________________");
//        }
//    }
//
//    public static void unmarkTask(String[] lineSpaceSplit) {
//        int unmarkedIndex = Integer.parseInt(lineSpaceSplit[1]);
//        try {
//            Task unmarkedTask = taskList.get(unmarkedIndex - 1);
//            unmarkedTask.markAsUndone();
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("____________________________________________________________" +
//                    "Task Number out of range. Please enter a valid input (Current Range: 1 " + "to " + taskList.size() + ")" +
//                    "____________________________________________________________");
//        }
//    }
//
//    public static void todoTask(String line) {
//        System.out.println("____________________________________________________________");
//        try {
//            String todoTask = line.substring(5);
//            Todo newTodoTask = new Todo(todoTask);
//            taskList.add(newTodoTask);
//            System.out.println("Got it. I've added this task:");
//            System.out.println(newTodoTask);
//            System.out.println("\nNow you have " + taskList.size() + " tasks in the list.");
//        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
//            System.out.println("Todo description cannot be empty.\nPlease enter a valid input (E.g. todo borrow book).");
//        }
//        System.out.println("____________________________________________________________");
//    }
//
//    public static void deadlineTask(String line) {
//
//        System.out.println("____________________________________________________________");
//        try {
//            String[] deadlineItemSplit = (line.substring(9)).split(" /by ");
//            String deadlineTask = deadlineItemSplit[0];
//            String deadlineBy = deadlineItemSplit[1];
//            Deadline task = new Deadline(deadlineTask, deadlineBy);
//            taskList.add(task);
//            System.out.println("Got it. I've added this task:");
//            System.out.println(task);
//            System.out.println("\nNow you have " + taskList.size() + " tasks in the list.");
//        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
//            System.out.println("Invalid Deadline input.\nPlease enter a valid input (E.g. deadline return book /by Sunday).");
//        }
//        System.out.println("____________________________________________________________");
//    }
//
//    public static void eventTask(String line) {
//        System.out.println("____________________________________________________________");
//        try {
//            String[] eventItemSplit = (line.substring(6)).split(" /at ");
//            String eventTask = eventItemSplit[0];
//            String eventAt = eventItemSplit[1];
//            Event task = new Event(eventTask, eventAt);
//            taskList.add(task);
//            System.out.println("Got it. I've added this task:");
//            System.out.println(task);
//            System.out.println("\nNow you have " + taskList.size() + " tasks in the list.");
//        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
//            System.out.println("Invalid Event input.\nPlease enter a valid input (E.g. event project meeting /at Mon 2-4pm).");
//        }
//        System.out.println("____________________________________________________________");
//    }
//
//    public static void deleteTask(String[] lineSpaceSplit) {
//        System.out.println("____________________________________________________________");
//        int deleteIndex = Integer.parseInt(lineSpaceSplit[1]);
//        try {
//            Task task = taskList.get(deleteIndex - 1);
//            taskList.remove(task);
//            System.out.println("Noted. I've removed this task:\n");
//            System.out.println(task);
//            System.out.println("\nNow you have " + taskList.size() + " tasks in the list.");
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("____________________________________________________________" +
//                    "Task Number out of range. Please enter a valid input (Current Range: 1 " + "to " + taskList.size() + ")" +
//                    "____________________________________________________________");
//        }
//    }
//
//    public static void main(String[] args) throws DukeException, InvalidStorageFilePathException, IOException {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//        System.out.println("Hello there! I'm Duke\nWhat can I do for you?");
//
//
//        String line;
//        Scanner in = new Scanner(System.in);
//
//        while (true) {
//            line = in.nextLine();
//            String[] lineSpaceSplit = line.split(" ");
//            String taskTitle = lineSpaceSplit[0];
//
//            switch (taskTitle) {
//                // enter bye to end chat
//                case ("bye"):
//                    System.out.println("Bye. Hope to see you again soon!");
//                    break;
//
//                // to list all items
//                case ("list"):
//                    listTask();
//                    break;
//
//                // mark items
//                case ("mark"):
//                    if (taskList.isEmpty()) {
//                        System.out.println("____________________________________________________________\n" +
//                                "No Task is found. Please create a task." +
//                                "\n____________________________________________________________");
//                    } else {
//                        try {
//                            markTask(lineSpaceSplit);
//                        } catch (ArrayIndexOutOfBoundsException e) {
//                            System.out.println("____________________________________________________________\n" +
//                                    "Task number not detected. Please enter a valid input (E.g. mark 1)" +
//                                    "\n____________________________________________________________");
//                        }
//                    }
//                    break;
//
//                // unmarked items
//                case ("unmark"):
//                    if (taskList.isEmpty()) {
//                        System.out.println("____________________________________________________________\n" +
//                                "No Task is found. Please create a task." +
//                                "\n____________________________________________________________");
//                    } else {
//                        try {
//                            unmarkTask(lineSpaceSplit);
//                        } catch (ArrayIndexOutOfBoundsException e) {
//                            System.out.println("____________________________________________________________\n" +
//                                    "Task number not detected. Please enter a valid input (E.g. unmark 1)" +
//                                    "\n____________________________________________________________");
//                        }
//                    }
//                    break;
//
//                // to do task
//                case ("todo"):
//                    todoTask(line);
//                    break;
//
//                // deadline task
//                case ("deadline"):
//                    deadlineTask(line);
//                    break;
//
//                // event task
//                case ("event"):
//                    eventTask(line);
//                    break;
//
//                // delete task
//                case ("delete"):
//                    if (taskList.isEmpty()) {
//                        System.out.println("____________________________________________________________\n" +
//                                "No Task is found. Please create a task." +
//                                "\n____________________________________________________________");
//                    } else {
//                        try {
//                            deleteTask(lineSpaceSplit);
//                        } catch (ArrayIndexOutOfBoundsException e) {
//                            System.out.println("____________________________________________________________\n" +
//                                    "Task number not detected. Please enter a valid input (E.g. delete 1)" +
//                                    "\n____________________________________________________________");
//                        }
//                    }
//                    break;
//
//                // prompt user to enter valid input
//                default:
//                    System.out.println("____________________________________________________________\n" +
//                            "Please enter a valid input (E.g. todo Task 1)" + "" +
//                            "\n____________________________________________________________");
//                    break;
//            }
//            StorageFile.mainCaller();
//        }
//    }
//}