package duke.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void testToString() {
        Task task = new Task("description");
        task.setDone(true);
        Assertions.assertEquals("[ ][X] description", task.toString());

        task.setDone(false);
        Assertions.assertEquals("[ ][ ] description", task.toString());

        task.setDescription("new description");
        Assertions.assertEquals("[ ][ ] new description", task.toString());
    }
}
