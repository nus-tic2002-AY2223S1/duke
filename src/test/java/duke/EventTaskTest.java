package duke;

import duke.exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import duke.task.Event;
import java.time.format.DateTimeParseException;
class EventTaskTest {

    @Test
    void testToString() throws InvalidInputException {
        assertEquals("[E][×][#] borrow book(at: Feb-02-2022 00:00 - Feb-02-2022 00:00)" , new Event("borrow book", new DateTime("2022-02-02"),new DateTime("2022-02-02")).toString());
    }
}