package duke.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TodoTest {

    @Test
    void testToString() {
        Todo todo = new Todo("description");
        todo.setDone(true);
        Assertions.assertEquals("[T][X] description", todo.toString());

        todo.setDone(false);
        Assertions.assertEquals("[T][ ] description", todo.toString());

        todo.setDescription("new description");
        Assertions.assertEquals("[T][ ] new description", todo.toString());
    }
}
