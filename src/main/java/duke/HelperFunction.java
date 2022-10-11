package duke;

import java.util.Vector;

import duke.task.Task;

public class HelperFunction {
    // Print a line with 1 tab as indentation
    protected static void printlnWithIndent(String line) {
        System.out.println("\t" + line);
    }

    // Print a message for a successful insertion of task
    protected static void printNewTask(Vector<Task> tasks) {
        int size = tasks.size();
        printlnWithIndent("Great. We added a new task:");
        printlnWithIndent("\t" + tasks.get(size - 1));
        printlnWithIndent(String.format("You have in total %d tasks", size));
    }

    // Find the index of a string in a string array
    // Return the index if found and -1 if not found
    protected static int findIndex(String[] haystack, String needle) {
        for (int i = 0; i < haystack.length; i += 1) {
            if (haystack[i].equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}