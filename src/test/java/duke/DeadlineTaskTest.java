package duke;

import duke.exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import duke.task.Deadline;
class DeadlineTaskTest {

    @Test
    void testToString() throws InvalidInputException {
        assertEquals("[D][×][#] borrow book(by: Feb-02-2022 00:00)" , new Deadline("borrow book", new DateTime("2022-02-02")).toString());
    }
}