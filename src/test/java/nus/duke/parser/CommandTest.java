package nus.duke.parser;

import nus.duke.data.DukeException;
import nus.duke.storage.Storage;
import nus.duke.ui.Messages;
import nus.duke.ui.Ui;
import nus.duke.tasklist.TaskList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test cases for Command class.")
public class CommandTest {
    private final Storage storage = new Storage("tasks.txt");
    private final TaskList taskList = new TaskList(storage.load());
    private final Ui ui = new Ui();
    private static Command userInput;

    public CommandTest() throws DukeException {
    }

    static void initEach(String command) {
        try {
            userInput = Parser.parse(command);
        } catch (NullPointerException e){
        } catch (DukeException e){
        }
    }

    @DisplayName("Exceptional test for Command class.")
    @Test
    void exceptionTesting(){
        //Exception test case for out of list range case for mark, unmark, delete.
        //List count = 2;
        initEach("mark 3");
        DukeException exception = assertThrows(DukeException.class, ()->userInput.execute(taskList, ui, storage));
        assertEquals(Messages.MESSAGE_TASK_NUMBER_OUT_OF_RANGE, exception.getMessage());
        initEach("unmark 3");
        exception = assertThrows(DukeException.class, ()->userInput.execute(taskList, ui, storage));
        assertEquals(Messages.MESSAGE_TASK_NUMBER_OUT_OF_RANGE, exception.getMessage());
        initEach("delete 4");
        exception = assertThrows(DukeException.class, ()->userInput.execute(taskList, ui, storage));
        assertEquals(Messages.MESSAGE_TASK_NUMBER_OUT_OF_RANGE, exception.getMessage());
        //Exceptional test case for duplicated task.
        initEach("todo read book");
        exception = assertThrows(DukeException.class, ()->userInput.execute(taskList, ui, storage));
        assertEquals(Messages.MESSAGE_DUPLICATE_TASK, exception.getMessage());
    }
}
