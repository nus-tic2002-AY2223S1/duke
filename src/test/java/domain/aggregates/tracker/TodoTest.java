package domain.aggregates.tracker;

import domain.exceptions.DukeValidationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TodoTest {

    private Todo todo;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        try {
            System.setOut(new PrintStream(outContent));
            todo = new Todo("Task 1");
        } catch (DukeValidationException ex){
            todo = null;
        }
    }

    @AfterEach
    void tearDown() {
        todo = null;
    }

    @Test
    void todoInit(){
        assertEquals(0, todo.getId());
        assertEquals("Task 1", todo.getName());
        assertEquals(false, todo.getIsDone());
    }

    @Test
    void todoInit_allValues(){
        Todo newTodo = new Todo(2,"Task 2",true);
        assertEquals(2, newTodo.getId());
        assertEquals("Task 2", newTodo.getName());
        assertEquals(true, newTodo.getIsDone());
    }

    @Test
    void todoInit_null_throwsDukeValidationException(){
        try {
            Todo newTodo = new Todo(null);
            assertThrows(DukeValidationException.class, (Executable) newTodo);
        } catch (DukeValidationException ex){
        }
    }

    @Test
    void printItem() {
        todo.printItem();
        assertEquals("\t\t0.[T][ ] Task 1\n", outContent.toString());
    }

    @Test
    void testEquals_true() {
        try {
            Todo todo2 = new Todo("Task 1");
            assertEquals(true, todo.equals(todo2));
        } catch (DukeValidationException ex){
            assert(false);
        }
    }

    @Test
    void testEquals_false() {
        try {
            Todo todo2 = new Todo("Task 2");
            assertEquals(false, todo.equals(todo2));
        } catch (DukeValidationException ex){
            assert(false);
        }
    }

    @Test
    void testEquals_differentTask_event_false() {
        try {
            Event event = new Event("Task 2/at 2022-12-10 02:00");
            assertEquals(false, todo.equals(event));
        } catch (DukeValidationException ex){
            assert(false);
        }
    }

    @Test
    void testEquals_differentTask_deadline_false() {
        try {
            Deadline deadline = new Deadline("Task 2/by 2022-12-13");
            assertEquals(false, todo.equals(deadline));
        } catch (DukeValidationException ex){
            assert(false);
        }
    }

    @Test
    void testToString() {
        assertEquals("0 | T | 0 | Task 1", todo.toString());
    }

    @Test
    void compare_now_false() {
        assertEquals(false, todo.compare(LocalDate.now(),LocalDate.now()));
    }

    @Test
    void compare_null_false() {
        assertEquals(false, todo.compare(null,null));
    }

    @Test
    void getId() {
        assertEquals(0, todo.getId());
    }

    @Test
    void setId() {
        todo.setId(1);
        assertEquals(1, todo.getId());
    }

    @Test
    void getName() {
        assertEquals("Task 1", todo.getName());
    }

    @Test
    void setName() {
        todo.setName("Updated Task 1");
        assertEquals("Updated Task 1", todo.getName());
    }

    @Test
    void getIsDone() {
        assertEquals(false, todo.getIsDone());
    }

    @Test
    void setIsDone_true() {
        todo.setIsDone(true);
        assertEquals(true, todo.getIsDone());
    }

    @Test
    void setIsDone_false() {
        todo.setIsDone(false);
        assertEquals(false, todo.getIsDone());
    }
}