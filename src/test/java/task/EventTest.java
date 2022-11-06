package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void EventConstructorTest() {
        Event event = new Event("Attend design meeting", "03 June 2023 1130");
        assertEquals("Attend design meeting", event.getDescription());
        assertEquals("03 June 2023 1130", event.getAt());
    }

    @Test
    public void EventToStringTest() {
        Event event = new Event("Attend design meeting", "03 June 2023 1130");
        assertEquals("[E][ ] Attend design meeting (at: 03 June 2023 1130) [low]", event.toString());
    }

    @Test
    public void EventStringToOutput() {
        Event event = new Event("Attend design meeting", "03 June 2023 1130");
        assertEquals("E | 0 | Attend design meeting | 03 June 2023 1130 | low", event.stringToOutput());
    }


    @Test
    public void EventGetAtOutput() {
        Event event = new Event("Attend design meeting", "03 June 2023 1130");
        assertEquals("03 June 2023 1130", event.getAt());
    }
}
