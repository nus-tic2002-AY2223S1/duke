package Duke.TaskList;

import Duke.Duke;
import Duke.Exception.DukeException;
import Duke.Parser.Parser;
import Duke.Ui.Ui;
import Duke.TaskList.*;

import java.util.ArrayList;
import java.util.List;

public class Tasklist {
    private static ArrayList<Task> tl;
    private static ArrayList<Task> findTask;
    private static Ui ui = new Ui();

    public Tasklist(){
        tl = new ArrayList<>();
    }

    public Tasklist(List<Task> list) {
        tl = new ArrayList<>();
        tl.addAll(list);
    }

    /**
     * display method prints out the current list or find a specific task depending on the user's command
     * @param tL is the TaskList to be displayed
     * @param command is the user input command
     */
    public static void display (ArrayList<Task> tL, String command) {
        if (command == "") {
            Ui.line();
            System.out.println("Please enter a command. Command cannot be empty.");
            Ui.line();
        }

        switch(command) {
            case "list":
                if (tl.size() == 0) {
                    System.out.println("Your list is empty");
                } else {
                    System.out.println("Here are the tasks in your list:\n");
                }
                for (int index = 1; index <= tL.size(); index++){
                    System.out.println (index + ". " + tL.get(index-1).toString());
                }
                break;
            case "find":
                if(findTask.size() == 0) {
                    System.out.println("Sorry, there are is no such task in your list.");
                } else {
                    System.out.println("These are the tasks that match: ");
                }
                break;
        }
    }

    /**
     * addTodo method adds a new ToDo in the list
     * @param todo is the task to be added
     */
    public static void addTodo(ToDo todo) {
        tl.add(todo);
        Ui.addedTask(tl);
    }

    /**
     * addDeadline adds a new Deadline
     * @param deadline is the task to be added
     */
    public static void addDeadline(Deadline deadline) {
        tl.add(deadline);
        Ui.addedTask(tl);
    }

    /**
     * addEvent method adds a new Deadline
     * @param event is the task to be added
     */
    public static void addEvent(Event event) {
        tl.add(event);
        Ui.addedTask(tl);
    }

    /**
     * markTask method marks a task as done/undone based on whether or not it is already marked as done/undone in the list
     * @param userInput is the userInput to mark which task is done/undone
     * @param status is the status of the task to be marked done/undone
     * @throws DukeException
     */
    public static void markTask(String userInput, boolean status) throws DukeException {
        try {
            String idx[] = userInput.split(" ",2);
            int markIdx = Integer.parseInt(idx[1]);
            tl.get(markIdx-1).markAsDone(status);
            if(status) {
                System.out.println("Nice! I've marked this task as done:");
            } else {
                System.out.println("Ok! I've marked this task as not done yet: ");
            }
            System.out.println(tl.get(markIdx-1).getTask());
        } catch (NumberFormatException e){
            throw new DukeException("Please indicate task number");
        } catch (IndexOutOfBoundsException e){
            if (tl.size() == 0){
                throw new DukeException("Sorry. A task cannot be marked in an empty list.");
            } else {
                throw new DukeException("Sorry. Please indicate a task number in the list.");
            }
        }
    }

    /**
     * deleteTask method deletes a task from the list
     * @param userInput is the task to be deleted
     * @throws DukeException
     */
    public static void deleteTask(String userInput) throws DukeException {
        try {
            int idx = Integer.parseInt(userInput)-1;
            String description = tl.get(idx).getTask();
            tl.remove(idx);
            System.out.println("Noted. I've remove this task: \n" + description);
            System.out.println("Now you have " + tl.size() + " tasks in your list.");
        } catch (NumberFormatException e){
            throw new DukeException("Please indicate task number");
        } catch (IndexOutOfBoundsException e){
            if (tl.size() == 0){
                throw new DukeException("Sorry. A task cannot be deleted from an empty list.");
            } else {
                throw new DukeException("Sorry. Please indicate a task number in the list.");
            }
        }
    }

    /**
     * getList method returns the TaskList
     * @return
     */
    public static ArrayList<Task> getList() {
        return tl;
    }

    public static ArrayList<Task> findTask (String userInput) {
        findTask = new ArrayList<Task>();
        for(Task task:tl) {
            if(task.description.toLowerCase().contains(userInput)) {
                findTask.add(task);
            }
        }
        if(!findTask.isEmpty()) {
            System.out.println(findTask);
        }
        return findTask;
    }
}
