package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest extends TaskTest {

    @Test
    public void testTodo() {
        Todo todoTask = new Todo("todo task description");
        assertEquals("[T][ ] todo task description", todoTask.toString());
        todoTask.setDone(true);
        assertEquals("[T][X] todo task description", todoTask.toString());
        todoTask.setDone(false);
        assertEquals("[T][ ] todo task description", todoTask.toString());
    }

}
