package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testToStringPrint() {
        assertEquals("[T][ ] todo task description", new Todo("todo task description").toString());
        assertEquals("[D][ ] deadline task description /by 2022-10-19 13:00:00", new Deadline("deadline task description /by 2022-10-19 13:00:00").toString());
        assertEquals("[E][ ] event task description /at 2022-10-20 19:00:00", new Event("event task description /at 2022-10-20 19:00:00").toString());
    }

}
