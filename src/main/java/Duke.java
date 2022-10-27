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

    private static void checkToDoInput(String input) throws TodoMissingDescriptionException {
        if (input.split(" ").length == 1) {
            throw new TodoMissingDescriptionException();
        }
    }

    private static void checkFindInput(String input) throws FindMissingKeywordException {
        if (input.split(" ").length == 1) {
            throw new FindMissingKeywordException();
        }
    }

    private static void checkFindTaskInput(String input) throws FindTaskMissingDatetimeException {
        if (input.split(" ").length == 1) {
            throw new FindTaskMissingDatetimeException();
        }
    }

    private static void checkDeadlineInput(String input) throws DeadlineMissingKeywordException, DeadlineWrongFormatException {
        if (!input.contains("/by")) {
            throw new DeadlineMissingKeywordException();
        } else if (input.split("/by ").length != 2 || input.indexOf("/by") == 9) { // when there is no task date or there is no task given
            throw new DeadlineWrongFormatException();
        }
    }

    private static void checkEventInput(String input) throws EventMissingKeywordException, EventWrongFormatException {
        if (!input.contains("/at")) {
            throw new EventMissingKeywordException();
        } else if (input.split("/at ").length != 2 || input.indexOf("/at") == 6) { // when there is no task date or there is no task given
            throw new EventWrongFormatException();
        }
    }

    private static void checkDeleteInput(String input, int taskListSize) throws InputNumberFormatException, InputWrongFormatException, InputIndexOutOfBoundsException {
        if (input.split(" ").length != 2) {
            throw new InputWrongFormatException(" ☹ OOPS!!! Input has wrong format. Delete command should be: delete {index to delete}");
        } else if (!input.split(" ")[1].matches("-?\\d+(\\.\\d+)?")) { // Check if numeric
            throw new InputNumberFormatException(input);
        } else {
            checkIndex(Integer.parseInt(input.split(" ")[1]) - 1, taskListSize);
        }
    }

    private static void checkMarkInput(String input, int taskListSize) throws InputWrongFormatException, InputNumberFormatException, InputIndexOutOfBoundsException {
        if (input.split(" ").length != 2) {
            throw new InputWrongFormatException(" ☹ OOPS!!! Input has wrong format. Mark command should be: mark {index to mark}");
        } else if (!input.split(" ")[1].matches("-?\\d+(\\.\\d+)?")) { // Check if numeric
            throw new InputNumberFormatException(input);
        } else {
            checkIndex(Integer.parseInt(input.split(" ")[1]) - 1, taskListSize);
        }
    }

    private static void checkUnmarkInput(String input, int taskListSize) throws InputWrongFormatException, InputNumberFormatException, InputIndexOutOfBoundsException {
        if (input.split(" ").length != 2) {
            throw new InputWrongFormatException(" ☹ OOPS!!! Input has wrong format. Unmark command should be: unmark {index to unmark}");
        } else if (!input.split(" ")[1].matches("-?\\d+(\\.\\d+)?")) { // Check if numeric
            throw new InputNumberFormatException(input);
        } else {
            checkIndex(Integer.parseInt(input.split(" ")[1]) - 1, taskListSize);
        }
    }

    private static void checkPriorityInput(String input, int taskListSize) throws InputWrongFormatException, InputNumberFormatException, InputWrongPriorityException, InputIndexOutOfBoundsException {
        if (input.split(" ").length != 3) {
            throw new InputWrongFormatException(" ☹ OOPS!!! Input has wrong format. Priority command should be: priority {task index} {priority}");
        } else if (!input.split(" ")[1].matches("-?\\d+(\\.\\d+)?")) { // Check if numeric
            throw new InputNumberFormatException(input);
        } else if (!(input.split(" ")[2].equals("low") || input.split(" ")[2].equals("medium") || input.split(" ")[2].equals("high"))) {
            throw new InputWrongPriorityException();
        } else {
            checkIndex(Integer.parseInt(input.split(" ")[1]) - 1, taskListSize);
        }
    }

    private static void checkDoWithinPeriodInput(String input) throws DoWithinPeriodMissingKeywordException, DoWithinPeriodWrongFormatException {
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

    private static boolean checkKeywordExist(String keywordInput, String[] keywordList) {
        boolean isKeywordExist = false;
        for (int keywordIndex = 0; keywordIndex < keywordList.length; keywordIndex++) {
            if (keywordInput.equals(keywordList[keywordIndex])) {
                isKeywordExist = true;
            }
        }
        return isKeywordExist;
    }

    private static String checkInput(String input, int taskListSize) throws TodoMissingDescriptionException, BasicInputException, DeadlineMissingKeywordException, EventMissingKeywordException, DeadlineWrongFormatException, EventWrongFormatException, InputNumberFormatException, InputWrongFormatException, InputIndexOutOfBoundsException, FindTaskMissingDatetimeException, InputWrongPriorityException, DoWithinPeriodMissingKeywordException, DoWithinPeriodWrongFormatException, FindMissingKeywordException {
        String[] keywordList = {"bye", "mark", "unmark", "deadline", "todo", "list", "delete", "event", "findtask", "priority", "find", "dowithinperiod"};
        String keywordInput = input.split(" ")[0];
        boolean isKeywordExist = checkKeywordExist(keywordInput, keywordList);
        if (!isKeywordExist) {
            throw new BasicInputException();
        } else {
            if (keywordInput.equals("todo")) {
                checkToDoInput(input);
            } else if (keywordInput.equals("find")) {
                checkFindInput(input);
            } else if (keywordInput.equals("findtask")) {
                checkFindTaskInput(input);
            } else if (keywordInput.equals("deadline")) {
                checkDeadlineInput(input);
            } else if (keywordInput.equals("event")) {
                checkEventInput(input);
            } else if (keywordInput.equals("delete")) {
                checkDeleteInput(input, taskListSize);
            } else if (keywordInput.equals("mark")) {
                checkMarkInput(input, taskListSize);
            } else if (keywordInput.equals("unmark")) {
                checkUnmarkInput(input, taskListSize);
            } else if (keywordInput.equals("priority")) {
                checkPriorityInput(input, taskListSize);
            } else if (keywordInput.equals("dowithinperiod")) {
                checkDoWithinPeriodInput(input);
            }
            return input;
        }
    }

    /**
     * Set up initial taskList based on given taskList file
     *
     * @param filePath file directory which stores the list of task
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
                isExit = c.getExitValue();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main() method to start the chatbot process
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}