import java.util.Scanner;
public class Duke {
    static private Task[] list = new Task[100];
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

    public static void printList() {
        print("Here are the tasks in your list:\n");
        for(int i = 0; i < listCount; i++) {
            String count = (i + 1) + ". ";
            print(count);
            list[i].printTask();
        }
    }
    public static void echo(String toEcho) {
        print("Got it. I've added this task:\n");
        print("  " + toEcho + "\n");
        print("Now you have " + listCount + " tasks in the list.\n");
    }
    public static void identifyInput(String command) {
        boolean exist = false;
        if (command.equals("bye")) {
            exit();
        } else if (command.isEmpty()) {
            print("Empty input detected, please re-enter\n");
        } else if (command.equals("list")) {
            printList();
        } else if (command.contains("mark") || command.contains("unmark")) {
            String[] split = command.split(" ");
            try {
                identifyMark(split);
            } catch (NullPointerException e) {
                print("Task number not exist!\n");
            } catch (ArrayIndexOutOfBoundsException e) {
                print("Task number out of list range!\n");
            }
        } else {
            for (int i = 0; i < listCount; i++) {
                if (list[i].description.equals(command)) {
                    exist = true;
                    break;
                }
            }
            if (exist) {
                command = command + " exist";
            } else {
                try {
                    addTask(command);
                } catch (notATask e){
                    print("Not a correct task types, please use keywords: todo, deadlines or events.\n");
                }
            }
        }
    }
    public static void addTask(String command) throws notATask {
        String[] split = command.split("/");
        String dateAndTime;
        String[] description = split[0].split(" ", 2);
        switch (description[0]) {
            case "todo":
                list[listCount] = new Todo (description[1]);
                break;
            case "deadline":
                try {
                    dateAndTime = split[1];
                } catch (IndexOutOfBoundsException e){
                    print("Please enter with a specific date/time\n");
                    return;
                }
                list[listCount] = new Deadline(description[1], dateAndTime);
                break;
            case "event":
                try {
                    dateAndTime = split[1];
                    checkTimeSlot(dateAndTime);
                } catch (IndexOutOfBoundsException e){
                    print("Please enter with a time slot for the event\n");
                    return;
                } catch (notTimeSlotException e){
                    return;
                }
                list[listCount] = new Event(description[1], dateAndTime);
                break;
            default:
                throw new notATask();
        }
        listCount ++;
        echo(list[listCount - 1].toString());
    }

    public static void identifyMark(String[] split) {
        if (isNumeric(split[1])) {
            int taskNumber = Integer.parseInt(split[1]) - 1;
            if (split[0].equals("mark")) {
                    list[taskNumber].markAsDone();
            }
            if (split[0].equals("unmark")) {
                    list[taskNumber].markAsUndone();
            }
        }
    }
    public static void exit() {
        print("Bye. Remember!\n");
        print("In times of crisis, the wise build bridges while the foolish build barriers.\n");

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
    public static void print(String toPrint){
        String indentFive = "     ";
        System.out.print(indentFive + toPrint);
    }

    public static void checkTimeSlot(String dateAndTime) throws notTimeSlotException{
        if (dateAndTime.contains("-")){
            return;
        } else {
            throw new notTimeSlotException();
        }
    }
    public static class notTimeSlotException extends Exception{}

    public static class notATask extends Exception{}
}


