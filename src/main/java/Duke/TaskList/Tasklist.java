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

    public static void addTodo(ToDo todo) {
        tl.add(todo);
        Ui.addedTask(tl);
    }

    public static void addDeadline(Deadline deadline) {
        tl.add(deadline);
        Ui.addedTask(tl);
    }

    public static void addEvent(Event event) {
        tl.add(event);
        Ui.addedTask(tl);
    }

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
        return findTask;
    }
}
