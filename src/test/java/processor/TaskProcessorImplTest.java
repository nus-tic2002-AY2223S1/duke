package processor;


import domain.TaskList;
import domain.task.Todo;
import exceptions.EmptyDescriptionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processor.impl.TaskProcessorImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskProcessorImplTest {

    private final TaskProcessor taskProcessor = new TaskProcessorImpl();

    @BeforeEach
    public void before() {
    }

    @Test
    public void markTaskHappyPathTest() throws EmptyDescriptionException {
        TaskList taskList = new TaskList(new Todo("take out the trash"));
        assertFalse(taskList.getTask(0).isDone());
        taskProcessor.markTask("1", taskList);
        assertTrue(taskList.getTask(0).isDone());
        taskProcessor.unmarkTask("1", taskList);
        assertFalse(taskList.getTask(0).isDone());
    }

    @Test
    public void markTaskUnhappyPathTest() throws EmptyDescriptionException {
        TaskList taskList = new TaskList(new Todo("take out the trash"));
        assertFalse(taskList.getTask(0).isDone());
        taskProcessor.unmarkTask("1", taskList);
        assertFalse(taskList.getTask(0).isDone());
        taskProcessor.unmarkTask("1", taskList);
        assertFalse(taskList.getTask(0).isDone());
        taskProcessor.markTask("1", taskList);
        assertTrue(taskList.getTask(0).isDone());
        taskProcessor.markTask("1", taskList);
        assertTrue(taskList.getTask(0).isDone());
    }

}