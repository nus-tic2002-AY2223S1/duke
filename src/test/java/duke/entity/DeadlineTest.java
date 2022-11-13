package duke.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class DeadlineTest {

    @Test
    void testToString() {
        Deadline deadline = new Deadline("description");
        deadline.setBy(LocalDateTime.of(2000, 1, 1, 0, 0));
        deadline.setDone(true);
        Assertions.assertEquals("[D][X] description (by: 01 Jan 2000, Sat 00:00)", deadline.toString());

        deadline.setDone(false);
        Assertions.assertEquals("[D][ ] description (by: 01 Jan 2000, Sat 00:00)", deadline.toString());

        deadline.setDescription("new description");
        Assertions.assertEquals("[D][ ] new description (by: 01 Jan 2000, Sat 00:00)", deadline.toString());
    }
}
