package duke;

import duke.exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import duke.task.ToDo;
class TodoTaskTest {

    @Test
    void testToString() throws InvalidInputException {
        assertEquals("[T][×][#] borrow mbook", new ToDo("borrow mbook").toString());
    }
}