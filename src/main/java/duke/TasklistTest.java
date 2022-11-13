package duke;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TasklistTest {

    @Test
    void search() {
        Todo todo = new Todo("borrow book");
        Event event = new Event("read book","2022-03-01");
        Deadline deadlineBook = new Deadline("return book","2022-05-05");
        Deadline deadlineNewspaper = new Deadline("read newspaper","2022-03-01");
        Tasklist.tasks.add(todo);
        Tasklist.tasks.add(event);
        Tasklist.tasks.add(deadlineBook);
        Tasklist.tasks.add(deadlineNewspaper);
        assertEquals(Arrays.asList(todo,event,deadlineBook), Tasklist.search("book"));
        assertEquals(Arrays.asList(todo), Tasklist.search("borrow"));
        assertEquals(Arrays.asList(event,deadlineNewspaper), Tasklist.search("read"));
        assertEquals(Arrays.asList(deadlineNewspaper), Tasklist.search("newspaper"));
    }
}