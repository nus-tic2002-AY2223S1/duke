package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void TaskListConstructorTest() {
        TaskList task = new TaskList();
        assertEquals(0, task.myTaskList.size());
    }

    @Test
    public void TaskListLoadTaskTest() {
        TaskList task = new TaskList();
        task.loadTasks("T | 1 | read book | low");
        assertEquals("read book", task.myTaskList.get(0).getDescription());
        assertEquals("X", task.myTaskList.get(0).getStatusIcon());
    }

    @Test
    public void TaskListAddTaskTest() {
        TaskList task = new TaskList();
        task.addTask(new ToDo("read book"));
        assertEquals("read book", task.myTaskList.get(0).getDescription());
    }
}
