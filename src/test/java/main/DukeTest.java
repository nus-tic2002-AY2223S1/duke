package main;

import exception.DukeException;
import manager.CommandManager;
import org.junit.jupiter.api.Test;
import util.ErrorMessage;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yao Liang
 * @created 07/11/2022 - 3:49 pm
 * @projct Duke
 */
class DukeTest {
    
    @Test
    void executeInvalidInputShouldThrowException() {
        Duke duke = new Duke();
        assertThrows(DukeException.class, () -> {
            duke.validateUserInput("blah");
        });
    }
    
    @Test
    void executeEmptyInputShouldThrowException() {
        Duke duke = new Duke();
        assertThrows(DukeException.class, () -> {
            duke.validateUserInput("   ");
        });
    }
    
    @Test
    void executeListInputShouldThrowException() {
        Duke duke = new Duke();
        assertEquals(Arrays.toString(new String[]{"list"}), Arrays.toString(duke.validateUserInput("list")));
    }
    
    @Test
    void executeByeInputShouldThrowException() {
        Duke duke = new Duke();
        assertEquals(Arrays.toString(new String[]{"bye"}), Arrays.toString(duke.validateUserInput("bye")));
    }
    
    @Test
    void executeMarkInputShouldThrowException() {
        Duke duke = new Duke();
        assertEquals(Arrays.toString(new String[]{"mark", "3"}), Arrays.toString(duke.validateUserInput("mark 3")));
    }
    
    @Test
    void executeUnmarkInputShouldThrowException() {
        Duke duke = new Duke();
        assertEquals(Arrays.toString(new String[]{"unmark", "4"}),
                Arrays.toString(duke.validateUserInput("unmark 4")));
    }
    
    @Test
    void executeTodoInputShouldThrowException() {
        Duke duke = new Duke();
        assertEquals(Arrays.toString(new String[]{"todo", "do something"}),
                Arrays.toString(duke.validateUserInput("todo do something")));
    }
    
    @Test
    void executeDeadlineInputShouldThrowException() {
        Duke duke = new Duke();
        assertEquals(Arrays.toString(new String[]{"deadline", "return book /by 12-12-2022 2222"}),
                Arrays.toString(duke.validateUserInput("deadline return book /by 12-12-2022 2222")));
    }
    
    @Test
    void executeEventInputShouldThrowException() {
        Duke duke = new Duke();
        assertEquals(Arrays.toString(new String[]{"event", "project meeting /at 12/12/2022 2222"}),
                Arrays.toString(duke.validateUserInput("event project meeting /at 12/12/2022 2222")));
    }
    
    @Test
    void executeFindInputShouldThrowException() {
        Duke duke = new Duke();
        assertEquals(Arrays.toString(new String[]{"find", "book"}),
                Arrays.toString(duke.validateUserInput("find book")));
    }
    
    @Test
    void executeSortInputShouldThrowException() {
        Duke duke = new Duke();
        assertEquals(Arrays.toString(new String[]{"sort", "by name"}),
                Arrays.toString(duke.validateUserInput("sort by name")));
    }
}