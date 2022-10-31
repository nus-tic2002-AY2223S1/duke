package domain.aggregates.tracker;

import application.helpers.CommonHelper;
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

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    private Event event;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        try {
            System.setOut(new PrintStream(outContent));
            event = new Event("Task 1 /at Dec 15 2022 14:00");
        } catch (DukeValidationException ex){
            event = null;
        }
    }

    @AfterEach
    void tearDown() {
        event = null;
    }

    @Test
    void eventInit(){
        assertEquals(0, event.getId());
        assertEquals("Task 1", event.getName());
        assertEquals(LocalDateTime.parse("Dec 15 2022 14:00", DateTimeFormatter.ofPattern("[MMM dd yyyy HH:mm]")), event.getStartDateTime());
        assertEquals(false, event.getIsDone());
    }

    @Test
    void eventInit_date(){
        try {
            Event newEvent = new Event("Task 2 /at Dec 15 2022");
            assertEquals(0, newEvent.getId());
            assertEquals("Task 2", newEvent.getName());
            assertEquals(LocalDate.parse("Dec 15 2022", DateTimeFormatter.ofPattern("[MMM dd yyyy]")).atStartOfDay(), newEvent.getStartDateTime());
            assertEquals(false, newEvent.getIsDone());
        } catch (DukeValidationException ex){
            assert(false);
        }
    }

    @Test
    void eventInit_allValues(){
        Event newEvent = new Event(2,"Task 2", "2022-12-23T20:00",true);
        assertEquals(2, newEvent.getId());
        assertEquals("Task 2", newEvent.getName());
        assertEquals(LocalDateTime.parse("Dec 23 2022 20:00", DateTimeFormatter.ofPattern("[MMM dd yyyy HH:mm]")), newEvent.getStartDateTime());
        assertEquals(true, newEvent.getIsDone());
    }

    @Test
    void eventInit_null_throwsDukeValidationException(){
        try {
            Event newEvent = new Event(null);
            assertThrows(DukeValidationException.class, (Executable) newEvent);
        } catch (DukeValidationException ex){
        }
    }

    @Test
    void eventInit_nullName_throwsDukeValidationException(){
        try {
            Event newEvent = new Event(" /at 2022-11-05");
            assertThrows(DukeValidationException.class, (Executable) newEvent);
        } catch (DukeValidationException ex){
        }
    }

    @Test
    void eventInit_nullDate_throwsDukeValidationException(){
        try {
            Event newEvent = new Event("Task 2");
            assertThrows(DukeValidationException.class, (Executable) newEvent);
        } catch (DukeValidationException ex){
        }
    }

    @Test
    void printItem_dateTime() {
        event.printItem();
        assertEquals("\t\t0.[E][ ] Task 1 (at: 15 December 2022, 02:00 PM)\n", outContent.toString());
    }

    @Test
    void printItem_date() {
        try {
            Event event2 = new Event("Task 2 /at Dec 15 2022");
            event2.printItem();
            assertEquals("\t\t0.[E][ ] Task 2 (at: 15 December 2022)\n", outContent.toString());
        } catch (DukeValidationException ex){
            assert(false);
        }
    }

    @Test
    void testEquals_true() {
        try {
            Event event2 = new Event("Task 1 /at Dec 15 2022 14:00");
            assertEquals(true, event.equals(event2));
        } catch (DukeValidationException ex){
            assert(false);
        }
    }

    @Test
    void testEquals_false() {
        try {
            Event event2 = new Event("Task 2 /at Dec 23 2022 20:00");
            assertEquals(false, event.equals(event2));
        } catch (DukeValidationException ex){
            assert(false);
        }
    }

    @Test
    void testEquals_differentTask_todo_false() {
        try {
            Todo todo = new Todo("Task 2");
            assertEquals(false, event.equals(todo));
        } catch (DukeValidationException ex){
            assert(false);
        }
    }

    @Test
    void testEquals_differentTask_deadline_false() {
        try {
            Deadline deadline = new Deadline("Task 2/by 2022-12-13");
            assertEquals(false, event.equals(deadline));
        } catch (DukeValidationException ex){
            assert(false);
        }
    }

    @Test
    void testToString() {
        assertEquals("0 | E | 0 | Task 1 | 2022-12-15T14:00", event.toString());
    }

    @Test
    void compare_startDate_true() {
        assertEquals(true, event.compare(LocalDate.of(2022, 12, 15),null));
    }

    @Test
    void compare_startDate_false() {
        assertEquals(false, event.compare(LocalDate.of(2022, 12, 23),null));
    }

    @Test
    void compare_startDate_endDate_true() {
        assertEquals(true, event.compare(LocalDate.of(2022, 12, 15),LocalDate.of(2022, 12, 23)));
    }

    @Test
    void compare_startDate_endDate_false() {
        assertEquals(false, event.compare(LocalDate.of(2022, 11, 15),LocalDate.of(2022, 11, 23)));
    }

    @Test
    void getId() {
        assertEquals(0, event.getId());
    }

    @Test
    void setId() {
        event.setId(1);
        assertEquals(1, event.getId());
    }

    @Test
    void getName() {
        assertEquals("Task 1", event.getName());
    }

    @Test
    void setName() {
        event.setName("Updated Task 1");
        assertEquals("Updated Task 1", event.getName());
    }

    @Test
    void getIsDone() {
        assertEquals(false, event.getIsDone());
    }

    @Test
    void setIsDone_true() {
        event.setIsDone(true);
        assertEquals(true, event.getIsDone());
    }

    @Test
    void setIsDone_false() {
        event.setIsDone(false);
        assertEquals(false, event.getIsDone());
    }

    @Test
    void getStartDateTime() {
        assertEquals(LocalDateTime.parse("Dec 15 2022 14:00", DateTimeFormatter.ofPattern("[MMM dd yyyy HH:mm]")), event.getStartDateTime());
    }
}