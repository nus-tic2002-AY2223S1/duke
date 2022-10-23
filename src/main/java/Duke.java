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

    private static String checkInput(String input, int taskListSize) throws TodoMissingDescriptionException, BasicInputException, DeadlineMissingKeywordException, EventMissingKeywordException, DeadlineWrongFormatException, EventWrongFormatException, InputNumberFormatException, InputWrongFormatException, InputIndexOutOfBoundsException, FindTaskMissingDatetimeException, InputWrongPriorityException, DoWithinPeriodMissingKeywordException, DoWithinPeriodWrongFormatException {

        String[] keywordList = {"todo", "bye", "mark", "unmark", "deadline", "todo", "list", "delete", "event", "findtask", "priority", "find", "dowithinperiod"};
        String keywordInput = input.split(" ")[0];
        boolean keywordExist = false;
        for (int keywordIndex = 0; keywordIndex < keywordList.length; keywordIndex++) {
            if (keywordInput.equals(keywordList[keywordIndex])) {
                keywordExist = true;
            }
        }
        if (!keywordExist) {
            throw new BasicInputException();
        } else {
            if (keywordInput.equals("todo")) {
                if (input.split(" ").length == 1) {
                    throw new TodoMissingDescriptionException();
                }
            } else if (keywordInput.equals("findtask")) {
                if (input.split(" ").length == 1) {
                    throw new FindTaskMissingDatetimeException();
                }
            } else if (keywordInput.equals("deadline")) {
                if (!input.contains("/by")) {
                    throw new DeadlineMissingKeywordException();
                } else if (input.split("/by ").length != 2 || input.indexOf("/by") == 9) { // when there is no task date or there is no task given
                    throw new DeadlineWrongFormatException();
                }
            } else if (keywordInput.equals("event")) {
                if (!input.contains("/at")) {
                    throw new EventMissingKeywordException();
                } else if (input.split("/at ").length != 2 || input.indexOf("/at") == 6) { // when there is no task date or there is no task given
                    throw new EventWrongFormatException();
                }
            } else if (keywordInput.equals("delete")) {
                if (input.split(" ").length != 2) {
                    throw new InputWrongFormatException(" ☹ OOPS!!! Input has wrong format. Delete command should be: delete {index to delete}");
                } else if (!input.split(" ")[1].matches("-?\\d+(\\.\\d+)?")) { // Check if numeric
                    throw new InputNumberFormatException(input);
                } else {
                    checkIndex(Integer.parseInt(input.split(" ")[1]) - 1, taskListSize);
                }
            } else if (keywordInput.equals("mark")) {
                if (input.split(" ").length != 2) {
                    throw new InputWrongFormatException(" ☹ OOPS!!! Input has wrong format. Mark command should be: mark {index to mark}");
                } else if (!input.split(" ")[1].matches("-?\\d+(\\.\\d+)?")) { // Check if numeric
                    throw new InputNumberFormatException(input);
                } else {
                    checkIndex(Integer.parseInt(input.split(" ")[1]) - 1, taskListSize);
                }
            } else if (keywordInput.equals("unmark")) {
                if (input.split(" ").length != 2) {
                    throw new InputWrongFormatException(" ☹ OOPS!!! Input has wrong format. Unmark command should be: unmark {index to unmark}");
                } else if (!input.split(" ")[1].matches("-?\\d+(\\.\\d+)?")) { // Check if numeric
                    throw new InputNumberFormatException(input);
                } else {
                    checkIndex(Integer.parseInt(input.split(" ")[1]) - 1, taskListSize);
                }
            } else if (keywordInput.equals("priority")) {
                if (input.split(" ").length != 3) {
                    throw new InputWrongFormatException(" ☹ OOPS!!! Input has wrong format. Priority command should be: priority {task index} {priority}");
                } else if (!input.split(" ")[1].matches("-?\\d+(\\.\\d+)?")) { // Check if numeric
                    throw new InputNumberFormatException(input);
                } else if (!(input.split(" ")[2].equals("low") || input.split(" ")[2].equals("medium") || input.split(" ")[2].equals("high"))) {
                    throw new InputWrongPriorityException();
                } else {
                    checkIndex(Integer.parseInt(input.split(" ")[1]) - 1, taskListSize);
                }
            } else if (keywordInput.equals("dowithinperiod")) {
                if (!input.contains("/between")) {
                    throw new DoWithinPeriodMissingKeywordException("/between");
                } else if (!input.contains("/and")) {
                    throw new DoWithinPeriodMissingKeywordException("/and");
                }
                // when there is no end period date or there is no task given or there is no start period date
                else if (input.split("/and ").length != 2 || input.indexOf("/between") == 15 || ((input.indexOf("/and") - 1) - (input.indexOf("/between") + 9) == -1)) {
                    throw new DoWithinPeriodWrongFormatException();
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
     * Chatbot process
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