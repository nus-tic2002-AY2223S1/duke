package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest extends TaskTest {

    private final String description = "event task description";
    private final LocalDateTime at = LocalDateTime.parse("2022-10-20T13:50:59");
    Event eventTask = new Event(description,at);

    @Test
    public void testEvent() {
        assertEquals("[E][ ] event task description (at: Thu, 20 Oct 2022 13:50:59)", eventTask.toString());
        eventTask.setDone(true);
        assertEquals("[E][X] event task description (at: Thu, 20 Oct 2022 13:50:59)", eventTask.toString());
        eventTask.setDone(false);
        assertEquals("[E][ ] event task description (at: Thu, 20 Oct 2022 13:50:59)", eventTask.toString());
    }

}
