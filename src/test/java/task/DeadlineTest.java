package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void DeadlineConstructorTest() {
        Deadline deadline = new Deadline("Finish math homework", "03 June 2023 1130");
        assertEquals("Finish math homework", deadline.getDescription());
    }

    @Test
    public void DeadlineToStringTest() {
        Deadline deadline = new Deadline("Finish math homework", "03 June 2023 1130");
        assertEquals("[D][ ] Finish math homework (by: 03 June 2023 1130) [low]", deadline.toString());
    }

    @Test
    public void DeadlineStringToOutput() {
        Deadline deadline = new Deadline("Finish math homework", "03 June 2023 1130");
        assertEquals("D | 0 | Finish math homework | 03 June 2023 1130 | low", deadline.stringToOutput());
    }


    @Test
    public void DeadlineGetByOutput() {
        Deadline deadline = new Deadline("Finish math homework", "03 June 2023 1130");
        assertEquals("03 June 2023 1130", deadline.getBy());
    }
}
