import java.io.IOException;
import java.time.DayOfWeek;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class Duke {
    private static final List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String hello = "Hello! I'm Rick\n"
                + "What can I do for you?\n\n";
        System.out.print(hello);

        inputCommand();
    }

    /**
     * Main method that filters input by user and output required data
     */
    public static void inputCommand() {
        saveToFile();
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        try {
            if (command.replaceAll("\\s+", "").equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (command.toLowerCase().startsWith("list")) {
                if (command.replaceAll("\\s+", "").equalsIgnoreCase("list")) {
                    int listNo = 0;
                    System.out.println("Here are the tasks in your list:");

                    for (Task element : tasks) {
                        listNo++;
                        if (element == null) {
                            System.out.println();
                            break;
                        } else
                            System.out.println(listNo + "." + element);
                    }
                    System.out.println();
                    inputCommand();
                } else {
                    listCommandParser(command);
                    inputCommand();
                }
            } else if (command.toLowerCase().startsWith("mark")) {
                try {
                    // -5 index for "mark "
                    int taskNo = Integer.parseInt(command.substring(5)) - 1;
                    tasks.get(taskNo).markAsDone(true);

                    System.out.println("Nice! I've marked this task as done:" + System.lineSeparator()
                            + tasks.get(taskNo).toString() + System.lineSeparator());
                } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("☹ OOPS!!! Please indicate task to mark." + System.lineSeparator());
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    System.out.println("☹ OOPS!!! Task not found or invalid. Please try again." + System.lineSeparator());
                }
                inputCommand();
            } else if (command.toLowerCase().startsWith("find")) {
                try {
                    // -5 index for "find "
                    String findItem = command.substring(5);
                    int listNo = 0;

                    System.out.println("Here are the matching tasks in your list:");
                    for (Task element : tasks) {
                        if (element.toString().contains(findItem)) {
                            listNo++;
                            System.out.println(listNo + "." + element);
                        }
                    }
                    System.out.println();
                } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("☹ OOPS!!! Please indicate task to mark." + System.lineSeparator());
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    System.out.println("☹ OOPS!!! Task not found or invalid. Please try again." + System.lineSeparator());
                }
                inputCommand();
            } else if (command.toLowerCase().startsWith("unmark")) {
                try {
                    // -7 index for "unmark "
                    int taskNo = Integer.parseInt(command.substring(7)) - 1;
                    tasks.get(taskNo).markAsDone(false);

                    System.out.println("OK, I've marked this task as not done yet:" + System.lineSeparator()
                            + tasks.get(taskNo).toString() + System.lineSeparator());
                } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("☹ OOPS!!! Please indicate task to unmark." + System.lineSeparator());
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    System.out.println("☹ OOPS!!! Task not found or invalid. Please try again." + System.lineSeparator());
                }
                inputCommand();
            } else if (command.startsWith("delete")) {
                try {
                    // -7 index for "unmark"
                    int taskNo = Integer.parseInt(command.substring(7)) - 1;
                    removeTask(tasks.get(taskNo));
                } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("☹ OOPS!!! Please indicate task to delete." + System.lineSeparator());
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    System.out.println("☹ OOPS!!! Task not found or invalid. Please try again." + System.lineSeparator());
                }
                inputCommand();
            } else if (command.startsWith("todo")) {
                try {
                    String taskName = command.substring(5);
                    if (taskName.isBlank()) {
                        throw new DukeException();
                    } else {
                        addTask(new Todo(taskName));
                    }
                } catch (StringIndexOutOfBoundsException | DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty." + System.lineSeparator());
                }
                inputCommand();
            } else if (command.startsWith("deadline")) {
                try {
                    int split = command.indexOf('/');
                    String taskName = command.substring(9, split - 1);
                    String byWhen = command.substring(split + 4);

                    addTask(new Deadline(taskName, parseDate(byWhen)));
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty." + System.lineSeparator());
                }
                inputCommand();
            } else if (command.startsWith("event")) {
                try {
                    int split = command.indexOf('/');
                    String taskName = command.substring(6, split - 1);
                    String byWhen = command.substring(split + 4);

                    addTask(new Event(taskName, parseDate(byWhen)));
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty." + System.lineSeparator());
                }
                inputCommand();
            } else {
                throw new DukeException();
            }
        } catch (DukeException | IllegalArgumentException | StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + System.lineSeparator());
            inputCommand();
        }
    }

    /**
     * Add tasks to list.
     *
     * @param t indicates task to be added
     */
    public static void addTask(Task t) {
        tasks.add(t);

        System.out.println("Got it. I've added this task:" + System.lineSeparator() +
                t.toString() + System.lineSeparator() + "Now you have " + tasks.size() + " task(s) in the list." +
                System.lineSeparator());
    }

    /**
     * Remove tasks from list.
     *
     * @param t indicates task to be removed
     */
    public static void removeTask(Task t) {
        tasks.remove(t);

        System.out.println("Noted. I've removed this task:" + System.lineSeparator() +
                t.toString() + System.lineSeparator() + "Now you have " + tasks.size() + " task(s) in the list." +
                System.lineSeparator());
    }

    /**
     * A method to save all tasks to file on disk.
     */
    public static void saveToFile() {
        try {
            File dukeText = new File("data/duke.txt");
            dukeText.getParentFile().mkdirs();
            dukeText.createNewFile();

            FileWriter dukeTextWrite = new FileWriter("data/duke.txt");

            for (Task element : tasks) {
                char taskType = element.toString().charAt(1);
                char markType = element.toString().charAt(4);
                String taskDesc;

                int intMarkType = 0;

                if (markType == 'X') {
                    intMarkType = 1;
                }

                if (taskType == 'T') {
                    taskDesc = element.toString().substring(7);

                    dukeTextWrite.write(taskType + " | " + intMarkType + " | " + taskDesc + System.lineSeparator());
                } else {
                    int split = element.toString().indexOf(':');
                    int end = element.toString().indexOf(')');
                    taskDesc = element.toString().substring(7, split - 4);
                    String taskByWhen = element.toString().substring(split + 2, end);

                    dukeTextWrite.write(taskType + " | " + intMarkType + " | " + taskDesc + " | " + taskByWhen + System.lineSeparator());
                }
            }
            dukeTextWrite.close();
        } catch (IOException e) {
            System.out.println("Unable to save to file. Please check if directory and file exists.");
        }
    }

    /**
     * Take in date value from String and parse them to required Date format.
     *
     * @param byWhenText takes in the input from main command
     * @return date returned in required format [MMM DD YYYY]
     */
    public static String parseDate(String byWhenText) {
        LocalDate todayDate = LocalDate.now();
        LocalTime timeNow = LocalTime.now();

        byWhenText = byWhenText.replaceAll("\\s+", "");

        if (byWhenText.matches(".*\\d.*") && byWhenText.length() < 11) {
            try {
                LocalDate deadlineDate = LocalDate.parse(byWhenText);

                if (deadlineDate.isBefore(todayDate)) {
                    System.out.println("Date cannot be in the past.");
                    inputCommand();
                } else {
                    byWhenText = deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                }
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid date in this format: [YYYY-MM-DD]" + System.lineSeparator());
                inputCommand();
            }
        } else if (byWhenText.matches(".*\\d.*") && byWhenText.length() > 11) {
            try {
                String byWhenTextDate = byWhenText.substring(0, 10);
                String byWhenTextTime = byWhenText.substring(10);

                LocalDate deadlineDate = LocalDate.parse(byWhenTextDate);
                LocalTime deadlineTime = LocalTime.parse(byWhenTextTime.replaceAll("\\s+", ""));

                if (deadlineDate.isBefore(todayDate)) {
                    System.out.println("Date cannot be in the past." + System.lineSeparator());
                    inputCommand();
                } else if (deadlineDate.isEqual(todayDate) && deadlineTime.isBefore(timeNow)) {
                    System.out.println("Time cannot be in the past." + System.lineSeparator());
                    inputCommand();
                } else {
                    byWhenText = deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                            + deadlineTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                }
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid date and/or time in this format: [YYYY-MM-DD HH:mm]" + System.lineSeparator());
                inputCommand();
            }
        } else {
            try {
                String byWhenTextDay = byWhenText.substring(0, 3);
                String byWhenTextTime;

                DayOfWeek dow;
                DayOfWeek todayDay = todayDate.getDayOfWeek();
                LocalDate nextNearestDate;
                LocalTime byWhenTime;
                Locale locale = Locale.getDefault();

                switch (byWhenTextDay.toLowerCase()) {
                    case "mon":
                        dow = DayOfWeek.MONDAY;
                        byWhenTextDay = dow.getDisplayName(TextStyle.FULL, locale);

                        if (todayDay.equals(byWhenTextDay)) {
                            byWhenText = todayDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        } else {
                            TemporalAdjuster adj = TemporalAdjusters.next(dow);
                            nextNearestDate = todayDate.with(adj);
                            byWhenText = nextNearestDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        }
                        break;
                    case "tue":
                        dow = DayOfWeek.TUESDAY;
                        byWhenTextDay = dow.getDisplayName(TextStyle.FULL, locale);

                        if (todayDay.equals(byWhenTextDay)) {
                            byWhenText = todayDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        } else {
                            TemporalAdjuster adj = TemporalAdjusters.next(dow);
                            nextNearestDate = todayDate.with(adj);
                            byWhenText = nextNearestDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        }
                        break;
                    case "wed":
                        dow = DayOfWeek.WEDNESDAY;
                        byWhenTextDay = dow.getDisplayName(TextStyle.FULL, locale);

                        if (todayDay.equals(byWhenTextDay)) {
                            byWhenText = todayDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        } else {
                            TemporalAdjuster adj = TemporalAdjusters.next(dow);
                            nextNearestDate = todayDate.with(adj);
                            byWhenText = nextNearestDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        }
                        break;
                    case "thu":
                        dow = DayOfWeek.THURSDAY;
                        byWhenTextDay = dow.getDisplayName(TextStyle.FULL, locale);

                        if (todayDay.equals(byWhenTextDay)) {
                            byWhenText = todayDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        } else {
                            TemporalAdjuster adj = TemporalAdjusters.next(dow);
                            nextNearestDate = todayDate.with(adj);
                            byWhenText = nextNearestDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        }
                        break;
                    case "fri":
                        dow = DayOfWeek.FRIDAY;
                        byWhenTextDay = dow.getDisplayName(TextStyle.FULL, locale);

                        if (todayDay.equals(byWhenTextDay)) {
                            byWhenText = todayDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        } else {
                            TemporalAdjuster adj = TemporalAdjusters.next(dow);
                            nextNearestDate = todayDate.with(adj);
                            byWhenText = nextNearestDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        }
                        break;
                    case "sat":
                        dow = DayOfWeek.SATURDAY;
                        byWhenTextDay = dow.getDisplayName(TextStyle.FULL, locale);

                        if (todayDay.equals(byWhenTextDay)) {
                            byWhenText = todayDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        } else {
                            TemporalAdjuster adj = TemporalAdjusters.next(dow);
                            nextNearestDate = todayDate.with(adj);
                            byWhenText = nextNearestDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        }
                        break;
                    case "sun":
                        dow = DayOfWeek.SUNDAY;
                        byWhenTextDay = dow.getDisplayName(TextStyle.FULL, locale);

                        if (todayDay.equals(byWhenTextDay)) {
                            byWhenText = todayDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        } else {
                            TemporalAdjuster adj = TemporalAdjusters.next(dow);
                            nextNearestDate = todayDate.with(adj);
                            byWhenText = nextNearestDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        }
                        break;
                    default:
                        break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid date and/or time in this format: [YYYY-MM-DD HH:mm]" + System.lineSeparator());
                inputCommand();
            }
        }
        return byWhenText;
    }

    /**
     * Take in command String from list command and parse them to determine if input is deadline, event or todo.
     * Based on the task input, print out the results accordingly.
     * Input can also filter the tasks based on dates.
     *
     * @param wholeCommand takes in the input from main command
     */
    public static void listCommandParser(String wholeCommand) {
        String taskDescList, taskDateList;
        int listNo = 0;

        if (wholeCommand.matches(".*\\d.*")) {
            taskDescList = wholeCommand.replaceAll("\\s+", "").substring(4, wholeCommand.length() - 12);
            taskDateList = wholeCommand.replaceAll("\\s", "").substring(wholeCommand.length() - 12);
            taskDateList = parseDate(taskDateList);

            CommandType c = CommandType.valueOf(taskDescList);
            switch (c) {
                case event:
                    System.out.println("Event List For " + taskDateList);
                    for (Task element : tasks) {
                        if (element.toString().contains("[E]") && element.toString().contains(taskDateList)) {
                            listNo++;
                            System.out.println(listNo + "." + element);
                        }
                    }
                    System.out.println();
                    break;
                case deadline:
                    System.out.println("Deadline List For " + taskDateList);
                    for (Task element : tasks) {
                        if (element.toString().contains("[D]") && element.toString().contains(taskDateList)) {
                            listNo++;
                            System.out.println(listNo + "." + element);
                        }
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid input, please try again." + System.lineSeparator());
                    break;
            }

        } else {
            taskDescList = wholeCommand.replaceAll("\\s+", "").substring(4);
            CommandType c = CommandType.valueOf(taskDescList);
            switch (c) {
                case todo:
                    System.out.println("Todo List");
                    for (Task element : tasks) {
                        if (element.toString().contains("[T]")) {
                            listNo++;
                            System.out.println(listNo + "." + element);
                        }
                    }
                    System.out.println();
                    break;
                case event:
                    System.out.println("Event List");
                    for (Task element : tasks) {
                        if (element.toString().contains("[E]")) {
                            listNo++;
                            System.out.println(listNo + "." + element);
                        }
                    }
                    System.out.println();
                    break;
                case deadline:
                    System.out.println("Deadline List");
                    for (Task element : tasks) {
                        if (element.toString().contains("[D]")) {
                            listNo++;
                            System.out.println(listNo + "." + element);
                        }
                    }
                    System.out.println();
                    break;
                default:
                    System.out.println("Invalid input, please try again." + System.lineSeparator());
                    break;
            }
        }
    }
}