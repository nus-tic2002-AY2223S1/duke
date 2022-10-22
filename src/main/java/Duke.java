import command.Command;
import storage.Storage;
import exceptions.*;
import task.TaskList;
import ui.Ui;
import parser.Parser;

import java.io.FileNotFoundException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static void checkIndex(int inputIndex, int taskListSize) throws InputIndexOutOfBoundsException {
        if (!(inputIndex < taskListSize) || !(inputIndex > -1)) {
            throw new InputIndexOutOfBoundsException();
        }
    }

    private static String checkInput(String input, int taskListSize) throws TodoMissingDescriptionException, BasicInputException, DeadlineMissingKeywordException, EventMissingKeywordException, DeadlineWrongFormatException, EventWrongFormatException, InputNumberFormatException, InputWrongFormatException, InputIndexOutOfBoundsException, FindTaskMissingDatetimeException {

        String[] keywordList = {"todo", "bye", "mark", "unmark", "deadline", "todo", "list", "delete", "event", "findtask"};
        boolean keywordExist = false;
        for (int keywordIndex = 0; keywordIndex < keywordList.length; keywordIndex++) {
            if (input.contains(keywordList[keywordIndex])) {
                keywordExist = true;
            }
        }
        if (!keywordExist) {
            throw new BasicInputException();
        } else {
            if (input.indexOf("todo") != -1) {
                if (input.split(" ").length == 1) {
                    throw new TodoMissingDescriptionException();
                }
            } else if (input.indexOf("findtask") != -1) {
                if (input.split(" ").length == 1) {
                    throw new FindTaskMissingDatetimeException();
                }
            } else if (input.indexOf("deadline") != -1) {
                if (!input.contains("/by")) {
                    throw new DeadlineMissingKeywordException();
                } else if (input.split("/by ").length != 2 || input.indexOf("/by") == 9) { // when there is no task date or there is no task given
                    throw new DeadlineWrongFormatException();
                }
            } else if (input.indexOf("event") != -1) {
                if (!input.contains("/at")) {
                    throw new EventMissingKeywordException();
                } else if (input.split("/at ").length != 2 || input.indexOf("/at") == 6) { // when there is no task date or there is no task given
                    throw new EventWrongFormatException();
                }
            } else if (input.indexOf("delete") != -1) {
                if (input.split(" ").length != 2) {
                    throw new InputWrongFormatException(" ☹ OOPS!!! Input has wrong format. Delete command should be: delete {index to delete}");
                } else if (!input.split(" ")[1].matches("-?\\d+(\\.\\d+)?")) { // Check if numeric
                    throw new InputNumberFormatException(input);
                } else {
                    checkIndex(Integer.parseInt(input.split(" ")[1]) - 1, taskListSize);
                }
            } else if (input.indexOf("mark") != -1) {
                if (input.split(" ").length != 2) {
                    throw new InputWrongFormatException(" ☹ OOPS!!! Input has wrong format. Mark command should be: mark {index to mark}");
                } else if (!input.split(" ")[1].matches("-?\\d+(\\.\\d+)?")) { // Check if numeric
                    throw new InputNumberFormatException(input);
                } else {
                    checkIndex(Integer.parseInt(input.split(" ")[1]) - 1, taskListSize);
                }
            } else if (input.indexOf("unmark") != -1) {
                if (input.split(" ").length != 2) {
                    throw new InputWrongFormatException(" ☹ OOPS!!! Input has wrong format. Unmark command should be: unmark {index to unmark}");
                } else if (!input.split(" ")[1].matches("-?\\d+(\\.\\d+)?")) { // Check if numeric
                    throw new InputNumberFormatException(input);
                } else {
                    checkIndex(Integer.parseInt(input.split(" ")[1]) - 1, taskListSize);
                }
            }
            return input;
        }
    }

    /**
     * Set up initial taskList based on given taskList file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Chat bot process
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                checkInput(fullCommand, tasks.myTaskList.size());
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                Storage.save("data/tasks.txt", tasks.myTaskList);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main() method
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}