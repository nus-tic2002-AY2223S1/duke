package Duke;

import Duke.Commands.Command;
import Duke.Tasks.Deadlines;
import Duke.Tasks.Events;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void testTodo(){
        Task todo = new Todo("Test Todo");
        assertEquals("[T][ ] Test Todo",todo.getDescription());
    }

    @Test
    public void testEvent(){
        Task event = new Events("Test Event","2 Dec 2019 18:00");
        assertEquals("[E][ ] Test Event (2 Dec 2019 18:00)",event.getDescription());
    }

    @Test
    public void testDeadline(){
        Task deadline = new Deadlines("Test Deadline","2 Mar 2020 10:00");
        assertEquals("[D][ ] Test Deadline (2 Mar 2020 10:00)",deadline.getDescription());
    }

}
