import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

public class Duke {
    // UI
    public static void printBox(String words) {
        System.out.println("_____________________________________________");
        System.out.println(words);
        System.out.println("_____________________________________________");
    }

    // Storage
    public static void createFile(File f) {
        if(!f.exists()) {
            File dir = new File("data");
            dir.mkdir();
        }
    }

    // Storage
    public static void saveFile(String filePath, ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String toPrint = "";
        for (int i = 0; i < list.size(); i++) {
            if(i!=0) {
                toPrint = toPrint + "\n";
            }
            toPrint = toPrint + list.get(i).getTask();
        }
        fw.write(toPrint);
        fw.close();
    }

    // Storage
    public static ArrayList<Task> loadFile(ArrayList<Task> list) {
        File f = new File("data/duke.txt");
        int count = 0;
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                String[] arr = s.split("\\Q|\\E");
                if(arr[0].equals("T")) {
                    list.add(count, new Todo(arr[2]));
                    list.get(count).setStatus(Boolean.parseBoolean(arr[1]));
                    count++;
                } else if (arr[0].equals("D")) {
                    list.add(count, new Deadline(arr[2], arr[3]));
                    list.get(count).setStatus(Boolean.parseBoolean(arr[1]));
                    count++;
                } else if (arr[0].equals("E")) {
                    list.add(count, new Event(arr[2], arr[3]));
                    list.get(count).setStatus(Boolean.parseBoolean(arr[1]));
                    count++;
                }
                System.out.println(s);
            }
        } catch(FileNotFoundException e) {
            printBox("O_o Error. The file could not be found.");
        }
        return list;
    }

    // UI + Storage
    public static void printArray(ArrayList<Task> list, int length) {
        String toPrint = "";
        for (int i = 0; i < length; i++) {
            if(i!=0) {
                toPrint = toPrint + "\n";
            }
            toPrint = toPrint + list.get(i).getTask();
        }
        printBox(toPrint);
    }

    // Duke
    public static void main(String[] args) {
        // UI
        String logo = " _______ ______ ___  ___ ______\n"
                    + "|_______|  __  |   \\/   |  __  |\n"
                    + "   | |  | |  | |  |\\/|  | |  | |\n"
                    + "   | |  | |__| |  |  |  | |__| |\n"
                    + "   |_|  |______|__|  |__|______|\n";
        // Task
        ArrayList<Task> list = new ArrayList<Task> ();
        int listCount = list.size(); //to count number of items added to checklist

        // Storage
        String filePath = "data/duke.txt";
        File f = new File(filePath);
        if(!f.exists()) {
            createFile(f);
        } else {
            list = loadFile(list);
            listCount = list.size();
        }

        // UI
        System.out.println(logo);
        printBox("Hello! Tomo here.\nWhat's up? ^_^");

        // Duke
        while(true) {
            // UI?
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String[] arrInput = input.split(" ", 2);

            // Commands + Exceptions + UI + Parser
            try {
                // exit
                if (arrInput[0].equals("bye")) {
                    break;
                }
                // list all tasks
                else if (arrInput[0].equals("list")) {
                    printArray(list, listCount);
                }
                // mark completed tasks
                else if (arrInput[0].equals("mark")) {
                    int i = Integer.parseInt(arrInput[1]);
                    list.get(i-1).setStatus(true);
                    printBox("Task completed: " + list.get(i - 1).getTask());
                }
                // unmark incomplete tasks
                else if (arrInput[0].equals("unmark")) {
                    int i = Integer.parseInt(arrInput[1]);
                    list.get(i-1).setStatus(false);
                    printBox("Task not completed: " + list.get(i - 1).getTask());
                }
                // delete task
                else if (arrInput[0].equals("delete")) {
                    int i = Integer.parseInt(arrInput[1]);
                    printBox("Task " + list.get(i - 1).getTask() + " is deleted.");
                    list.remove(i-1);
                    listCount--;
                }
                // add tasks (todo, deadline, event)
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
                        String[] dInput = arrInput[1].split(" /by ");
                        list.add(listCount, new Deadline(dInput[0], dInput[1]));
                        listCount++;
                        printBox("Added '" + list.get(listCount - 1).getTask() + "' into the list.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        printBox("O_o Error. The description of Deadline cannot be empty. Try again. ");
                    }
                } else if (arrInput[0].equals("event")) {
                    try {
                        String[] eInput = arrInput[1].split(" /at ");
                        list.add(listCount, new Event(eInput[0], eInput[1]));
                        listCount++;
                        printBox("Added '" + list.get(listCount - 1).getTask() + "' into the list.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        printBox("O_o Error. The description of Event cannot be empty. Try again. ");
                    }
                }
                // user input is invalid
                else {
                    throw new DukeException();
                }
            } catch(DukeException e) {
                printBox("O_o Error. I don't understand what that means. Try again.");
            }
        }
        try {
            saveFile(filePath, list);
        } catch (IOException e) {
            printBox("O_o Error. File could not be found. Try again.");
        }
        printBox("See you ^_^");
    }
}
