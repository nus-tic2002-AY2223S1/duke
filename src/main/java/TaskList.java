import java.util.ArrayList;
import java.util.List;

public class TaskList {
    
    // properties
    List<Task> tasks = new ArrayList<>();

    // methods
    public void listTask() {
        System.out.println("\t-----------------------------------------------------------------");
        // if list is empty, prints empty list message
        if (tasks.size() == 0) {
            System.out.println("\t The list is empty");
        }
        else
        {
            // prints out the entire list
            for (int i = 0; i < tasks.size(); i++) {
                int count = i + 1;
                System.out.println("\t " + count + ". " + tasks.get(i));
            }
        }
        System.out.println("\t-----------------------------------------------------------------");
    }

    public void addToDo(String[] lineArray) {
        // piece the task description back together
        String descriptionLine = "";
        for (int i = 1; i < lineArray.length; i++) {
            descriptionLine += " " + lineArray[i];
        }
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t " + "Got it. I've added this task: ");
        tasks.add(new Todo(descriptionLine));
        System.out.println("\t\t " + tasks.get(tasks.size()-1));
        System.out.println("\t " + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("\t-----------------------------------------------------------------");
    }

    public void addDeadLine(String[] lineArray) {
        // piece the task description back together
        String descriptionLine = "";
        int indexOfDelimiter = 0;
        for (int i = 1; i < lineArray.length; i++) {
            if (lineArray[i].equalsIgnoreCase("/by")) {
                indexOfDelimiter = i;
                break;
            }
            descriptionLine += " " + lineArray[i];
        }
        String date = "";
        for (int i = indexOfDelimiter+1; i < lineArray.length; i++) {
            date += " " + lineArray[i];
        }
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t " + "Got it. I've added this task: ");
        tasks.add(new Deadline(descriptionLine, date));
        System.out.println("\t\t " + tasks.get(tasks.size()-1));
        System.out.println("\t " + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("\t-----------------------------------------------------------------");
    }

    public void addEvent(String[] lineArray) {
        // piece the task description back together
        String descriptionLine = "";
        int indexOfDelimiter = 0;
        for (int i = 1; i < lineArray.length; i++) {
            if (lineArray[i].equalsIgnoreCase("/at")) {
                indexOfDelimiter = i;
                break;
            }
            descriptionLine += " " + lineArray[i];
        }
        String date = "";
        for (int i = indexOfDelimiter+1; i < lineArray.length; i++) {
            date += " " + lineArray[i];
        }
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t " + "Got it. I've added this task: ");
        tasks.add(new Event(descriptionLine, date));
        System.out.println("\t\t " + tasks.get(tasks.size()-1));
        System.out.println("\t " + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("\t-----------------------------------------------------------------");
    }

    public void markTask(String[] lineArray) {
        // get the index of the to do list to mark
        int inputIndex = Integer.parseInt(lineArray[1]);
        int arrayIndex = inputIndex - 1;
        tasks.get(arrayIndex).setDone(true);
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t\t " + tasks.get(arrayIndex));
        System.out.println("\t-----------------------------------------------------------------");
    }

    public void unmarkTask(String[] lineArray) {
        // get the index of the to do list to unmark
        int inputIndex = Integer.parseInt(lineArray[1]);
        int arrayIndex = inputIndex - 1;
        tasks.get(arrayIndex).setDone(false);
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t\t " + tasks.get(arrayIndex));
        System.out.println("\t-----------------------------------------------------------------");
    }

    public void deleteTask(String[] lineArray) {
        int inputIndex = Integer.parseInt(lineArray[1]);
        int arrayIndex = inputIndex - 1;
        System.out.println("\t-----------------------------------------------------------------");
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t\t " + tasks.get(arrayIndex));
        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("\t-----------------------------------------------------------------");
        tasks.remove(arrayIndex);
    }

}
