import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void printBox(String words) {
        System.out.println("_____________________________________________");
        System.out.println(words);
        System.out.println("_____________________________________________");
    }

    public static void main(String[] args) {
        String logo = " _______ ______ ___  ___ ______\n"
                    + "|_______|  __  |   \\/   |  __  |\n"
                    + "   | |  | |  | |  |\\/|  | |  | |\n"
                    + "   | |  | |__| |  |  |  | |__| |\n"
                    + "   |_|  |______|__|  |__|______|\n";
        ArrayList<Task> list = new ArrayList<Task> ();
        int listCount = 0; //to count number of items added to checklist
        System.out.println(logo);
        printBox("Hello! Tomo here.\nWhat's up? ^_^");

        while(true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String[] arrInput = input.split(" ", 2);
            try {
                if (arrInput[0].equals("bye")) {
                    break;
                } else if (arrInput[0].equals("list")) {
                    String toPrint = "Checklist:";
                    for (int i = 0; i < listCount; i++) {
                        toPrint = toPrint + "\n" + (i + 1) + ") " + list.get(i).getTask();
                    }
                    printBox(toPrint);
                } else if (arrInput[0].equals("mark")) {
                    int i = Integer.parseInt(arrInput[1]);
                    list.get(i-1).setStatus(true);
                    printBox("Task completed: " + list.get(i - 1).getTask());
                } else if (arrInput[0].equals("unmark")) {
                    int i = Integer.parseInt(arrInput[1]);
                    list.get(i-1).setStatus(false);
                    printBox("Task not completed: " + list.get(i - 1).getTask());
                } else if (arrInput[0].equals("delete")) {
                    int i = Integer.parseInt(arrInput[1]);
                    printBox("Task " + list.get(i - 1).getTask() + " is deleted.");
                    list.remove(i-1);
                    listCount--;
                }
                else if (arrInput[0].equals("todo")) {
                    try {
                        list.add(listCount, new Todo(arrInput[1]));
                        listCount++;
                        printBox("Added '" + list.get(listCount - 1).getTask() + "' into the list.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        printBox("O_o Error. The description of Todo cannot be empty. Try again. ");
                    }
                } else if (arrInput[0].equals("deadline")) {
                    try {
                        String[] dInput = arrInput[1].split("/by ");
                        list.add(listCount, new Deadline(dInput[0], dInput[1]));
                        listCount++;
                        printBox("Added '" + list.get(listCount - 1).getTask() + "' into the list.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        printBox("O_o Error. The description of Deadline cannot be empty. Try again. ");
                    }
                } else if (arrInput[0].equals("event")) {
                    try {
                        String[] eInput = arrInput[1].split("/at ");
                        list.add(listCount, new Event(eInput[0], eInput[1]));
                        listCount++;
                        printBox("Added '" + list.get(listCount - 1).getTask() + "' into the list.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        printBox("O_o Error. The description of Event cannot be empty. Try again. ");
                    }
                } else {
                    throw new DukeException();
                }
            } catch(DukeException e) {
                printBox("O_o Error. I don't understand what that means. Try again.");
            }
        }
        printBox("See you ^_^");
    }
}
