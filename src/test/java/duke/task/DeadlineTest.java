package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest extends TaskTest {

    private final String description = "deadline task description";
    private final LocalDateTime by = LocalDateTime.parse("2022-10-19T09:00:00");
    Deadline deadlineTask = new Deadline(description,by);

    @Test
    public void testDeadline() {
        assertEquals("[D][ ] deadline task description (by: Wed, 19 Oct 2022 09:00:00)", deadlineTask.toString());
        deadlineTask.setDone(true);
        assertEquals("[D][X] deadline task description (by: Wed, 19 Oct 2022 09:00:00)", deadlineTask.toString());
        deadlineTask.setDone(false);
        assertEquals("[D][ ] deadline task description (by: Wed, 19 Oct 2022 09:00:00)", deadlineTask.toString());
    }

}
