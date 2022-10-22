package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void TaskConstructorTest() {
        Task task = new Task("Have lunch with family");
        assertEquals("Have lunch with family", task.getDescription());
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void TaskAnotherConstructorTest() {
        Task task = new Task("Attend painting class");
        assertEquals("Attend painting class", task.getDescription());
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void TaskMarkDoneTest() {
        Task task = new Task("Attend painting class");
        task.markDone();
        assertEquals("Attend painting class", task.getDescription());
        assertEquals(true, task.isDone);
    }

    @Test
    public void TaskUnMarkDoneTest() {
        Task task = new Task("Attend painting class");
        task.markDone();
        task.unMarkDone();
        assertEquals("Attend painting class", task.getDescription());
        assertEquals(false, task.isDone);
    }

    @Test
    public void TaskToStringTest() {
        Task task = new Task("Attend painting class");
        task.markDone();
        assertEquals("[X] Attend painting class", task.toString());
    }

    @Test
    public void TaskToStringOutputTest() {
        Task task = new Task("Attend painting class");
        task.markDone();
        assertEquals(" | 1 | Attend painting class", task.stringToOutput());
    }
}
