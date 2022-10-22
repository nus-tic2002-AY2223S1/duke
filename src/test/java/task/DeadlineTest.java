package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    Deadline task = new Deadline("return book","2015/5/6 1800");
    @Test
    public void dummyTest(){
        assertEquals("[D][ ]return book (by: May 06 2015 0600PM)", task.toString(), "works");
    }

    @Test
    public void anotherDummyTest(){
        task.markAsDone();
        assertEquals("[D][x]return book (by: May 06 2015 0600PM)", task.toString(), " works");
    }
}