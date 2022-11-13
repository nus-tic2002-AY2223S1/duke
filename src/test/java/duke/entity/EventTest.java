package duke.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class EventTest {

    @Test
    void testToString() {
        Event event = new Event("description");
        event.setStartTime(LocalDateTime.of(2000, 1, 1, 0, 0));
        event.setEndTime(LocalDateTime.of(2022, 1, 1, 0, 0));
        event.setDone(true);
        Assertions.assertEquals("[E][X] description (at: 01 Jan 2000, Sat 00:00 - 01 Jan 2022, Sat 00:00)", event.toString());

        event.setDone(false);
        Assertions.assertEquals("[E][ ] description (at: 01 Jan 2000, Sat 00:00 - 01 Jan 2022, Sat 00:00)", event.toString());

        event.setDescription("new description");
        Assertions.assertEquals("[E][ ] new description (at: 01 Jan 2000, Sat 00:00 - 01 Jan 2022, Sat 00:00)", event.toString());

        event.setEndTime(LocalDateTime.of(2022, 1, 2, 0, 0));
        Assertions.assertEquals("[E][ ] new description (at: 01 Jan 2000, Sat 00:00 - 02 Jan 2022, Sun 00:00)", event.toString());
    }
}
