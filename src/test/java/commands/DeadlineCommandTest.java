package commands;

import entity.Deadline;
import entity.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class DeadlineCommandTest {
    @Test
    void testExecute_success() {
        String inputs = "deadline go home /by 2022-10-10 00:00";
        Command c = new DeadlineCommand(inputs);
        c.execute();
        Storage s = Storage.getInstance();
        Deadline res = (Deadline) s.tasks.get(0);
        Assertions.assertEquals("go home", res.getDescription());
        Assertions.assertFalse(res.isDone());
        Assertions.assertEquals(LocalDateTime.of(2022, 10,
                10, 0, 0), res.getBy());
    }


}
