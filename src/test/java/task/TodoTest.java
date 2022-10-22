package task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    Todo task = new Todo("read book");
    @Test
    public void dummyTest(){
        assertEquals("[T][ ]read book", task.toString());
    }

    @Test
    public void anotherDummyTest(){
        task.markAsDone();
        assertEquals("[T][x]read book", task.toString());
    }
}
