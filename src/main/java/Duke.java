import java.io.FileNotFoundException;
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());

        } catch (DukeException e) {
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        System.out.println(tasks.myTaskList.size());
//        if (tasks.task_count == 0) {
//            System.out.println("There is no task, please add some task!");
//        }
//        for (int count = 0; count < tasks.myTaskList.size(); count++) {
//            System.out.println((count + 1) + "." + tasks.myTaskList.get(count).toString());
//        }

    }

    public static void main(String[] args) {
        new Duke("src/data/tasks.txt").run();
    }
}
//public class Duke {
//    private static void checkIndex(int inputIndex, int taskListSize) throws InputIndexOutOfBoundsException {
//        if (!(inputIndex < taskListSize) || !(inputIndex > -1)) {
//            throw new InputIndexOutOfBoundsException();
//        }
//    }
//
//    private static String checkInput(String input) throws TodoMissingDescriptionException, BasicInputException, DeadlineMissingKeywordException, EventMissingKeywordException, DeadlineWrongFormatException, EventWrongFormatException, InputNumberFormatException, InputWrongFormatException {
//
//        String[] keywordList = {"todo", "bye", "mark", "unmark", "deadline", "todo", "list", "delete", "event"};
//        boolean keywordExist = false;
//        for (int keywordIndex = 0; keywordIndex < keywordList.length; keywordIndex++) {
//            if (input.contains(keywordList[keywordIndex])) {
//                keywordExist = true;
//            }
//        }
//        if (!keywordExist) {
//            throw new BasicInputException();
//        } else {
//            if (input.indexOf("todo") != -1) {
//                if (input.split(" ").length == 1) {
//                    throw new TodoMissingDescriptionException();
//                }
//            } else if (input.indexOf("deadline") != -1) {
//                if (!input.contains("/by")) {
//                    throw new DeadlineMissingKeywordException();
//                } else if (input.split(" ").length != 4) {
//                    throw new DeadlineWrongFormatException();
//                }
//            } else if (input.indexOf("event") != -1) {
//                if (!input.contains("/at")) {
//                    throw new EventMissingKeywordException();
//                } else if (input.split(" ").length != 4) {
//                    throw new EventWrongFormatException();
//                }
//            } else if (input.indexOf("delete") != -1) {
//                if (input.split(" ").length != 2) {
//                    throw new InputWrongFormatException();
//                } else if (!input.split(" ")[1].matches("-?\\d+(\\.\\d+)?")) { // Check if numeric
//                    throw new InputNumberFormatException();
//                }
//            } else if (input.indexOf("mark") != -1) {
//                if (input.split(" ").length != 2) {
//                    throw new InputWrongFormatException();
//                } else if (!input.split(" ")[1].matches("-?\\d+(\\.\\d+)?")) { // Check if numeric
//                    throw new InputNumberFormatException();
//                }
//            } else if (input.indexOf("unmark") != -1) {
//                if (input.split(" ").length != 2) {
//                    throw new InputWrongFormatException();
//                } else if (!input.split(" ")[1].matches("-?\\d+(\\.\\d+)?")) { // Check if numeric
//                    throw new InputNumberFormatException();
//                }
//            }
//            return input;
//        }
//    }
//
//    public static void main(String[] args) {
//        // Create a taskList containing task object
//        ArrayList<Task> taskList = new ArrayList<Task>(); // Create an ArrayList object
//        Scanner input = new Scanner(System.in);
//        System.out.print("Hello! I'm Duke\n" + "What can I do for you?:\n");
//
//        while (true) {
//            String line = input.nextLine();
//            try {
//                checkInput(line);
//                String[] lineList = line.split(" ");
//                String firstWord = lineList[0].toLowerCase();
//
//                switch (firstWord) {
//                    case "bye":
//                        System.out.println("Bye. Hope to see you again soon!");
//                        break;
//                    case "list":
//                        if (taskList.size() == 0) {
//                            System.out.println("There is no task, please add some task!");
//                        }
//                        for (int count = 0; count < taskList.size(); count++) {
//                            System.out.println((count + 1) + "." + taskList.get(count).toString());
//                        }
//                        continue;
//                    case "mark":
//                        checkIndex(Integer.parseInt(lineList[1]) - 1, taskList.size());
//                        int markIndex = Integer.parseInt(lineList[1]) - 1;
//                        Task selectedTask = taskList.get(markIndex);
//                        selectedTask.markDone();
//                        System.out.println("Nice! I've marked this task not done:");
//                        System.out.println(selectedTask.toString());
//                        continue;
//                    case "unmark":
//                        checkIndex(Integer.parseInt(lineList[1]) - 1, taskList.size());
//                        int unMarkIndex = Integer.parseInt(lineList[1]) - 1;
//                        Task unSelectedTask = taskList.get(unMarkIndex);
//                        unSelectedTask.unMarkDone();
//                        System.out.println("OK, I've marked this task as done yet:");
//                        System.out.println(unSelectedTask.toString());
//                        continue;
//                    case "deadline":
//                        String[] bySplitList = line.split("/by ");
//                        String deadLine = bySplitList[1];
//                        String deadLineTask = line.substring(9, line.indexOf("/by"));
//                        System.out.println("Got it. I've added this task:");
//                        taskList.add(new Deadline(deadLineTask, deadLine));
//                        System.out.println(taskList.get(taskList.size() - 1).toString());
//                        System.out.println("Now you have " + (taskList.size()) + " tasks in the list.");
//                        continue;
//                    case "todo":
//                        System.out.println("Got it. I've added this task:");
//                        String toDoTask = line.split("todo ")[1];
//                        taskList.add(new ToDo(toDoTask));
//                        System.out.println(taskList.get(taskList.size() - 1).toString());
//                        System.out.println("Now you have " + (taskList.size()) + " tasks in the list.");
//                        continue;
//                    case "event":
//                        String[] atSplitList = line.split("/at ");
//                        String eventDate = atSplitList[1];
//                        String eventTask = line.substring(6, line.indexOf("/at"));
//                        System.out.println("Got it. I've added this task:");
//                        taskList.add(new Event(eventTask, eventDate));
//                        System.out.println(taskList.get(taskList.size() - 1).toString());
//                        System.out.println("Now you have " + (taskList.size()) + " tasks in the list.");
//                        continue;
//                    case "delete":
//                        checkIndex(Integer.parseInt(lineList[1]) - 1, taskList.size());
//                        int deleteIndex = Integer.parseInt(lineList[1]) - 1;
//                        System.out.println("Noted. I've removed this task:");
//                        System.out.println(taskList.get(deleteIndex).toString());
//                        taskList.remove(deleteIndex);
//                        System.out.println("Now you have " + (taskList.size()) + " tasks in the list.");
//                        continue;
//                    default:
//                        System.out.println("Try another command, your input does not work: " + line + "\n");
//                }
//            } catch (TodoMissingDescriptionException e) {
//                System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
//            } catch (BasicInputException e) {
//                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//            } catch (DeadlineMissingKeywordException e) {
//                System.out.println(" ☹ OOPS!!! The key word /by must exists in the deadline command.");
//            } catch (DeadlineWrongFormatException e) {
//                System.out.println(" ☹ OOPS!!! Input has wrong format. Deadline command should be: deadline {description} /by {deadline}");
//            } catch (EventMissingKeywordException e) {
//                System.out.println(" ☹ OOPS!!! The key word /at must exists in the event command.");
//            } catch (EventWrongFormatException e) {
//                System.out.println(" ☹ OOPS!!! Input has wrong format. Event command should be: event {description} /at {date}");
//            } catch (InputNumberFormatException e) {
//                System.out.println(" ☹ OOPS!!! The index after " + line.split(" ")[0] + " is not numeric, please enter an numeric number.");
//            } catch (InputWrongFormatException e) {
//                String keyword = line.split(" ")[0];
//                if (keyword.equals("delete")) {
//                    System.out.println(" ☹ OOPS!!! Input has wrong format. Delete command should be: delete {index to delete}");
//                } else if (keyword.equals("unmark")) {
//                    System.out.println(" ☹ OOPS!!! Input has wrong format. Unmark command should be: unmark {index to unmark}");
//                } else if (keyword.equals("mark")) {
//                    System.out.println(" ☹ OOPS!!! Input has wrong format. Mark command should be: mark {index to mark}");
//                }
//            } catch (InputIndexOutOfBoundsException e) {
//                System.out.println(" ☹ OOPS!!! Index in the input is out of bound. Please choose an index that is within than the size of our tasklist");
//            }
//        }
//    }
//}