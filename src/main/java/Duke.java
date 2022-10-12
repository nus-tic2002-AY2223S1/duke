import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    //static private Task[] list = new Task[100];
    static private ArrayList<Task> list = new ArrayList<Task>();
    static private int listCount = 0;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Wakanda forever! I'm Winston Duke");
        System.out.println("What can I do for you?");
        readInput();
    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void readInput() {
        String command;
        do {
            Scanner userInput = new Scanner(System.in);
            command = userInput.nextLine();
            identifyInput(command);
        } while (!(command.equals("bye")));
    }

    public static void identifyInput(String command) {
        boolean exist = false;
        if (command.equals("bye")) {
            exit();
        } else if (command.isEmpty()) {
            print("Empty input detected, please re-enter\n");
        } else if (command.equals("list")) {
            printList();
        } else if (command.contains("mark") || command.contains("unmark") || command.contains("delete") ) {
            String[] split = command.split(" ");
            try {
                identifyTaskNumber(split);
            } catch (NullPointerException e) {
                print("Task number not exist!\n");
            } catch (IndexOutOfBoundsException e) {
                print("Task number out of list range! Please enter number from 1 -  " + listCount + ".\n");
            }
        } else {
            try {
                addTask(command);
            } catch (DukeException.notATask e) {
                print("OOPS!!! I'm sorry, but I dont't know what that means :-( \n" );
                print("Please use keywords: todo, deadlines or events.\n");
            }
        }
    }
    public static void addTask(String command) throws DukeException.notATask {
        String[] split = command.split("/");
        String dateAndTime = "";
        String[] description;
        // Check event description is not empty.
        try{
            description = checkAndSplitDescription(split[0]);
        } catch (DukeException.noTaskDescription e) {
            print("Task description cannot be empty!\n");
            return;
        }
        //check if Deadline and Event has a valid date/time.
        try {
            dateAndTime = split[1];
            checkDateAndTime(description, dateAndTime);
        } catch (IndexOutOfBoundsException e){
            switch (description[0]){
                case "deadline":
                    print("Invalid command! Date and time for deadline cannot be empty.\n");
                    return;
                case "event":
                    print("Invalid command! Date and time for event cannot be empty.\n");
                    return;
                default:
                    break;
            }
        } catch (DukeException.notTimeSlotException e) {
            print("Please enter a timeslot for the event! Or use deadline instead. \n");
            return;
        }
        //check if the task to be added is existed
        if (listCount > 0) {
            try {
                isDuplicate(description, dateAndTime);
            } catch (DukeException.isDuplicateException e) {
                print("Task exist!\n");
                return;
            }
        }
        switch (description[0]) {
            case "todo":
                    list.add(new Todo(description[1]));
                break;
            case "deadline":
                list.add(new Deadline(description[1], dateAndTime));
                break;
            case "event":
                list.add(new Event(description[1], dateAndTime));
                break;
            default:
                throw new DukeException.notATask();
        }
        listCount ++;
        echo(list.get(listCount - 1).toString(), "added");
    }

    public static void deleteTask(int taskNumber) {
        String removedTask = list.get(taskNumber).toString();
        list.remove(taskNumber);
        listCount--;
        echo(removedTask, "removed");
    }
    public static void identifyTaskNumber(String[] split) {
        if (isNumeric(split[1])) {
            int taskNumber = Integer.parseInt(split[1]) - 1;
            switch (split[0]) {
                case "mark":
                    list.get(taskNumber).markAsDone();
                    break;
                case "unmark":
                    list.get(taskNumber).markAsUndone();
                    break;
                case "delete":
                    deleteTask(taskNumber);
                    break;
                default:
                    return;
            }
        }
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void checkDateAndTime(String[] description, String dateAndTime) throws DukeException.notTimeSlotException{
        switch (description[0]){
            case "todo":
                return;
            case "event":
                try{
                    checkTimeSlot(dateAndTime);
                } catch (DukeException.notTimeSlotException e) {
                    throw new DukeException.notTimeSlotException();
                }
                break;
            default:
                break;
        }
    }
    public static void isDuplicate (String[] description, String dateAndTime) throws DukeException.isDuplicateException{
        for (int i = 0; i < listCount; i++) {
            switch (description[0]) {
                case "todo":
                    if(list.get(i).getDescription().equals(description[1])){
                        throw new DukeException.isDuplicateException();
                    }
                    break;
                case "deadline":
                case "event":
                    if(list.get(i).getDescription().equals(description[1])){
                        if (list.get(i).getDateAndTime().equals(dateAndTime)) {
                            throw new DukeException.isDuplicateException();
                        }
                    }
                    break;
                default:
                    return;
            }
        }
    }

    public static void checkTimeSlot(String dateAndTime) throws DukeException.notTimeSlotException {
        if (dateAndTime.contains("-")) {
        } else {
            throw new DukeException.notTimeSlotException();
        }
    }

    public static String[] checkAndSplitDescription(String description) throws DukeException.noTaskDescription, DukeException.notATask{
        String[] split;

        split = description.split(" ", 2);
        if (split.length < 2) {
            if (split[0].equals("todo") || split[0].equals("deadline") || split[0].equals("event")) {
                throw new DukeException.noTaskDescription();
            } else {
                throw new DukeException.notATask();
            }
        }
        return split;
    }

    public static void print(String toPrint){
        String indentFive = "     ";
        System.out.print(indentFive + toPrint);
    }

    public static void printList() {
        print("Here are the tasks in your list:\n");
        for(int i = 0; i < listCount; i++) {
            String count = (i + 1) + ". ";
            print(count);
            list.get(i).printTask();
        }
    }
    public static void echo(String toEcho, String mode) {
        print("Got it. I've " + mode + " this task:\n");
        print("  " + toEcho + "\n");
        print("Now you have " + listCount + " tasks in the list.\n");
    }
    public static void exit() {
        print("Bye. Remember!\n");
        print("In times of crisis, the wise build bridges while the foolish build barriers.\n");

    }
}


