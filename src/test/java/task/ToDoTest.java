package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void ToDoConstructorTest() {
        ToDo todo = new ToDo("Prepare for math exam");
        assertEquals("Prepare for math exam", todo.getDescription());
    }

    @Test
    public void ToDoToStringTest() {
        ToDo todo = new ToDo("Prepare for math exam");
        assertEquals("[T][ ] Prepare for math exam [low]", todo.toString());
    }

    @Test
    public void ToDoStringToOutput() {
        ToDo todo = new ToDo("Prepare for math exam");
        assertEquals("T | 0 | Prepare for math exam | low", todo.stringToOutput());
    }
}
