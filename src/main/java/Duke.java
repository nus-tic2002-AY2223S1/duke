import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (FileNotFoundException e) {
            ui.printBox("Existing file not found. New file created.");
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcome();
        //boolean isExit = false;
        // Duke
        while(true) {
            // UI
            String input = ui.userInput();

            // Parser
            String[] arrInput = input.split(" ", 2);

            // Commands + Exceptions + UI + Parser
            try {
                // exit
                if (arrInput[0].equals("bye")) {
                    break;
                }
                // list all tasks
                else if (arrInput[0].equals("list")) {
                    ui.printArray(tasks.getList(), tasks.size());
                }
                // mark completed tasks
                else if (arrInput[0].equals("mark")) {
                    int i = Integer.parseInt(arrInput[1]);
                    tasks.done(i-1);
                    ui.printBox("Task completed: " + tasks.getTask(i-1));
                }
                // unmark incomplete tasks
                else if (arrInput[0].equals("unmark")) {
                    int i = Integer.parseInt(arrInput[1]);
                    tasks.undone(i-1);
                    ui.printBox("Task not completed: " + tasks.getTask(i-1));
                }
                // delete task
                else if (arrInput[0].equals("delete")) {
                    int i = Integer.parseInt(arrInput[1]);
                    ui.printBox("Task " + tasks.getTask(i-1) + " is deleted.");
                    tasks.delete(i);
                }
                // add tasks (todo, deadline, event)
                else if (arrInput[0].equals("todo")) {
                    try {
                        tasks.addTodo(arrInput[1]);
                        ui.printBox("Added '" + tasks.getTask(tasks.size()-1) + "' into the list.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        ui.printBox("O_o Error. The description of Todo cannot be empty. Try again. ");
                    }
                } else if (arrInput[0].equals("deadline")) {
                    try {
                        String[] dInput = arrInput[1].split(" /");
                        tasks.addDeadline(dInput[0], dInput[1]);
                        ui.printBox("Added '" + tasks.getTask(tasks.size()-1) + "' into the list.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        ui.printBox("O_o Error. The description of Deadline cannot be empty. Try again. ");
                    }
                } else if (arrInput[0].equals("event")) {
                    try {
                        String[] eInput = arrInput[1].split(" /");
                        tasks.addEvent(eInput[0], eInput[1]);
                        ui.printBox("Added '" + tasks.getTask(tasks.size()-1) + "' into the list.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        ui.printBox("O_o Error. The description of Event cannot be empty. Try again. ");
                    }
                }
                // user input is invalid
                else {
                    throw new DukeException();
                }
            } catch(DukeException e) {
                ui.printBox("O_o Error. I don't understand what that means. Try again.");
            }
        }
        try {
            storage.saveFile(tasks.getList());
        } catch (IOException e) {
            ui.printBox("O_o Error. File could not be found. Try again.");
        }
        ui.printBox("See you ^_^");
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
