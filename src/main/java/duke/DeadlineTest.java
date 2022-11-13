package duke;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {
    

    Deadline deadline = new Deadline("DeadlineTest","2022-02-02");
    @Test
    @DisplayName("Deadline Test toString")
    void testToString() {
        deadline.markAsDone();
        assertEquals("[D][X] DeadlineTest (by: Feb 2 2022)", deadline.toString());
        deadline.markAsNotDone();
        assertEquals("[D][ ] DeadlineTest (by: Feb 2 2022)", deadline.toString());
    }

    @Test
    @DisplayName("Deadline Test to String marked as Not Done")
    void toFile() {
        deadline.markAsNotDone();
        assertEquals("D | 0 | DeadlineTest | 2022-02-02\n", deadline.toFile());
        deadline.markAsDone();
        assertEquals("D | 1 | DeadlineTest | 2022-02-02\n", deadline.toFile());
    }

    @Test
    void dateToString() {
        assertEquals("Feb 2 2022", deadline.dateToString());
    }

    @Test
    void dateSaveFormat() {
        assertEquals("2022-02-02", deadline.dateSaveFormat());
    }
}