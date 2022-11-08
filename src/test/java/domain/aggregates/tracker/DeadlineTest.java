package domain.aggregates.tracker;

import domain.exceptions.DukeValidationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeadlineTest {

    private Deadline deadline;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        try {
            System.setOut(new PrintStream(outContent));
            deadline = new Deadline("Task 1 /by Dec 15 2022 14:00");
        } catch (DukeValidationException ex) {
            deadline = null;
        }
    }

    @AfterEach
    void tearDown() {
        deadline = null;
    }

    @Test
    void deadlineInit_dateTime() {
        assertEquals(0, deadline.getId());
        assertEquals("Task 1", deadline.getName());
        assertEquals(LocalDateTime.parse("Dec 15 2022 14:00", DateTimeFormatter.ofPattern("[MMM dd yyyy HH:mm]")), deadline.getDueDateTime());
        assertEquals(false, deadline.getIsDone());
    }

    @Test
    void deadlineInit_date() {
        try {
            Deadline newDeadline = new Deadline("Task 2 /by Dec 15 2022");
            assertEquals(0, newDeadline.getId());
            assertEquals("Task 2", newDeadline.getName());
            assertEquals(LocalDate.parse("Dec 15 2022", DateTimeFormatter.ofPattern("[MMM dd yyyy]")).atStartOfDay(), newDeadline.getDueDateTime());
            assertEquals(false, newDeadline.getIsDone());
        } catch (DukeValidationException ex) {
            assert (false);
        }
    }

    @Test
    void deadlineInit_allValues() {
        Deadline newDeadline = new Deadline(2, "Task 2", "2022-12-23T20:00", true);
        assertEquals(2, newDeadline.getId());
        assertEquals("Task 2", newDeadline.getName());
        assertEquals(LocalDateTime.parse("Dec 23 2022 20:00", DateTimeFormatter.ofPattern("[MMM dd yyyy HH:mm]")), newDeadline.getDueDateTime());
        assertEquals(true, newDeadline.getIsDone());
    }

    @Test
    void deadlineInit_null_throwsDukeValidationException() {
        try {
            Deadline newDeadline = new Deadline(null);
            assertThrows(DukeValidationException.class, (Executable) newDeadline);
        } catch (DukeValidationException ex) {
        }
    }

    @Test
    void deadlineInit_nullName_throwsDukeValidationException() {
        try {
            Deadline newDeadline = new Deadline(" /by 2022-11-05");
            assertThrows(DukeValidationException.class, (Executable) newDeadline);
        } catch (DukeValidationException ex) {
        }
    }

    @Test
    void deadlineInit_nullDate_throwsDukeValidationException() {
        try {
            Deadline newDeadline = new Deadline("Task 2");
            assertThrows(DukeValidationException.class, (Executable) newDeadline);
        } catch (DukeValidationException ex) {
        }
    }

    @Test
    void printItem_dateTime() {
        deadline.printItem();
        assertEquals("\t\t0.[D][ ] Task 1 (by: 15 December 2022, 02:00 PM)\n", outContent.toString());
    }

    @Test
    void printItem_date() {
        try {
            Deadline deadline2 = new Deadline("Task 2 /by Dec 15 2022");
            deadline2.printItem();
            assertEquals("\t\t0.[D][ ] Task 2 (by: 15 December 2022)\n", outContent.toString());
        } catch (DukeValidationException ex) {
            assert (false);
        }
    }

    @Test
    void testEquals_true() {
        try {
            Deadline deadline2 = new Deadline("Task 1 /by Dec 15 2022 14:00");
            assertEquals(true, deadline.equals(deadline2));
        } catch (DukeValidationException ex) {
            assert (false);
        }
    }

    @Test
    void testEquals_false() {
        try {
            Deadline deadline2 = new Deadline("Task 2 /by Dec 23 2022 20:00");
            assertEquals(false, deadline.equals(deadline2));
        } catch (DukeValidationException ex) {
            assert (false);
        }
    }

    @Test
    void testEquals_againstTodo_false() {
        try {
            Todo todo = new Todo("Task 2");
            assertEquals(false, deadline.equals(todo));
        } catch (DukeValidationException ex) {
            assert (false);
        }
    }

    @Test
    void testEquals_againstEvent_false() {
        try {
            Event event = new Event("Task 2/at 2022-12-13");
            assertEquals(false, deadline.equals(event));
        } catch (DukeValidationException ex) {
            assert (false);
        }
    }

    @Test
    void testToString() {
        assertEquals("0 | D | 0 | Task 1 | 2022-12-15T14:00", deadline.toString());
    }

    @Test
    void compare_startDate_true() {
        assertEquals(true, deadline.compare(LocalDate.of(2022, 12, 15), null));
    }

    @Test
    void compare_startDate_false() {
        assertEquals(false, deadline.compare(LocalDate.of(2022, 12, 23), null));
    }

    @Test
    void compare_startDateEndDate_true() {
        assertEquals(true, deadline.compare(LocalDate.of(2022, 12, 15), LocalDate.of(2022, 12, 23)));
    }

    @Test
    void compare_startDateEndDate_false() {
        assertEquals(false, deadline.compare(LocalDate.of(2022, 11, 15), LocalDate.of(2022, 11, 23)));
    }

    @Test
    void getId() {
        assertEquals(0, deadline.getId());
    }

    @Test
    void setId() {
        deadline.setId(1);
        assertEquals(1, deadline.getId());
    }

    @Test
    void getName() {
        assertEquals("Task 1", deadline.getName());
    }

    @Test
    void setName() {
        deadline.setName("Updated Task 1");
        assertEquals("Updated Task 1", deadline.getName());
    }

    @Test
    void getIsDone() {
        assertEquals(false, deadline.getIsDone());
    }

    @Test
    void setIsDone_true() {
        deadline.setIsDone(true);
        assertEquals(true, deadline.getIsDone());
    }

    @Test
    void setIsDone_false() {
        deadline.setIsDone(false);
        assertEquals(false, deadline.getIsDone());
    }

    @Test
    void getDueDateTime() {
        assertEquals(LocalDateTime.parse("Dec 15 2022 14:00", DateTimeFormatter.ofPattern("[MMM dd yyyy HH:mm]")), deadline.getDueDateTime());
    }
}
