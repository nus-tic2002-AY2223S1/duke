package duke;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    Event event = new Event("EventTest","2022-02-02");
    @Test
    @DisplayName("Event Test toString")
    void testToString() {
        event.markAsDone();
        assertEquals("[E][X] EventTest (at: Feb 2 2022)", event.toString());
        event.markAsNotDone();
        assertEquals("[E][ ] EventTest (at: Feb 2 2022)", event.toString());
    }

    @Test
    @DisplayName("Event Test to String marked as Not Done")
    void toFile() {
        event.markAsNotDone();
        assertEquals("E | 0 | EventTest | 2022-02-02\n", event.toFile());
        event.markAsDone();
        assertEquals("E | 1 | EventTest | 2022-02-02\n", event.toFile());
    }

    @Test
    void dateToString() {
        assertEquals("Feb 2 2022", event.dateToString());
    }

    @Test
    void dateSaveFormat() {
        assertEquals("2022-02-02", event.dateSaveFormat());
    }
}