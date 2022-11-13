package commands;

import entity.Event;
import entity.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class EventCommandTest {
    @Test
    void testExecute_success(){
        String inputs = "deadline go home /at 2022-10-10 00:00";
        Command c = new EventCommand(inputs);
        c.execute();
        Storage s = Storage.getInstance();
        Event res = (Event) s.tasks.get(0);
        Assertions.assertEquals("go home", res.getDescription());
        Assertions.assertFalse(res.isDone());
        Assertions.assertEquals(LocalDateTime.of(2022, 10,
                10, 0, 0), res.getAt());
    }

}
