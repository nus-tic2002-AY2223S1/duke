package duke;

import constant.CommandEnum;
import entity.Deadline;
import entity.Event;
import entity.Task;
import entity.Todo;
import exception.DukeException;
import form.DeadlineForm;
import form.DeleteForm;
import form.EventForm;
import form.Form;
import form.MarkingForm;
import form.TodoForm;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.CommandManager;
import service.TaskManager;
import service.command.Command;
import util.FileUtil;

import java.io.File;
import java.time.LocalDateTime;

public class TestCommand {

    private final TaskManager taskManager = TaskManager.getInstance();

    private static final String TEST_META_DATA_DESCRIPTION = "meta data";

    private static final String TEST_TASK_DESCRIPTION = "test task description";

    private static final String TEST_DATA_SET_PATH = "data/test-set.txt";

    private static final LocalDateTime TEST_DEADLINE_BY_TIME = LocalDateTime.of(2030, 1, 1, 0, 0, 0);

    private static final LocalDateTime TEST_EVENT_START_TIME = LocalDateTime.of(2022, 1, 1, 0, 0, 0);

    private static final LocalDateTime TEST_EVENT_END_TIME = LocalDateTime.of(2022, 12, 31, 23, 59, 59);

    @BeforeAll
    public static void setUp() {
        // init test data file location
        TaskManager.init(TEST_DATA_SET_PATH);
    }

    @AfterAll
    public static void cleanUp() {
        // remove test data file
        FileUtil.deleteFile(new File(TEST_DATA_SET_PATH));
    }

    @Test
    public void testListCommand() {
        // no examinable data set to verify, simply test execution function
        Command command = CommandManager.getCommand(CommandEnum.LIST.getName());
        Form form = new Form(TEST_META_DATA_DESCRIPTION);
        command.execute(form);
    }

    @Test
    public void testAddTodoTaskCommand() {
        String commandName = CommandEnum.TODO.getName();
        TodoForm todoForm = new TodoForm(TEST_META_DATA_DESCRIPTION, commandName, TEST_TASK_DESCRIPTION);

        Command command = CommandManager.getCommand(commandName);
        command.execute(todoForm);
        Todo todo = getNewlyAddedTask(Todo.class);

        Todo mock = new Todo(TEST_TASK_DESCRIPTION);

        Assertions.assertEquals(mock.getType(), todo.getType());
        Assertions.assertEquals(mock.getDescription(), todo.getDescription());
        Assertions.assertEquals(mock.isDone(), todo.isDone());
    }

    @Test
    public void testAddDeadlineTaskCommand() {
        String commandName = CommandEnum.DEADLINE.getName();
        DeadlineForm deadlineForm = new DeadlineForm(TEST_META_DATA_DESCRIPTION, commandName, TEST_TASK_DESCRIPTION);
        deadlineForm.setBy(TEST_DEADLINE_BY_TIME);

        Command command = CommandManager.getCommand(commandName);
        command.execute(deadlineForm);
        Deadline deadline = getNewlyAddedTask(Deadline.class);

        Deadline mock = new Deadline(TEST_TASK_DESCRIPTION);
        mock.setBy(TEST_DEADLINE_BY_TIME);

        Assertions.assertEquals(mock.getType(), deadline.getType());
        Assertions.assertEquals(mock.getDescription(), deadline.getDescription());
        Assertions.assertEquals(mock.getBy(), deadline.getBy());
        Assertions.assertEquals(mock.isDone(), deadline.isDone());
    }

    @Test
    public void testAddEventTaskCommand() {
        String commandName = CommandEnum.EVENT.getName();
        EventForm eventForm = new EventForm(TEST_META_DATA_DESCRIPTION, commandName, TEST_TASK_DESCRIPTION);
        eventForm.setStartTime(TEST_EVENT_START_TIME);
        eventForm.setEndTime(TEST_EVENT_END_TIME);

        Command command = CommandManager.getCommand(commandName);
        command.execute(eventForm);
        Event event = getNewlyAddedTask(Event.class);

        Event mock = new Event(TEST_TASK_DESCRIPTION);
        mock.setStartTime(TEST_EVENT_START_TIME);
        mock.setEndTime(TEST_EVENT_END_TIME);

        Assertions.assertEquals(mock.getType(), event.getType());
        Assertions.assertEquals(mock.getDescription(), event.getDescription());
        Assertions.assertEquals(mock.getStartTime(), event.getStartTime());
        Assertions.assertEquals(mock.getEndTime(), event.getEndTime());
        Assertions.assertEquals(mock.isDone(), event.isDone());
    }

    @Test
    public void testMarkTaskCommand() {
        // prefill the data set
        Todo e = new Todo(TEST_TASK_DESCRIPTION);
        taskManager.addTask(e);

        String commandName = CommandEnum.MARK_TASK.getName();
        MarkingForm markingForm = new MarkingForm(TEST_META_DATA_DESCRIPTION, commandName, getUserInputLastIndex());

        Command command = CommandManager.getCommand(commandName);
        command.execute(markingForm);
        Todo todo = getNewlyAddedTask(Todo.class);

        Assertions.assertTrue(todo.isDone());
    }

    @Test
    public void testUnmarkTaskCommand() {
        // prefill the data set
        Todo e = new Todo(TEST_TASK_DESCRIPTION);
        e.setDone(true);
        taskManager.addTask(e);

        String commandName = CommandEnum.UNMARK_TASK.getName();
        MarkingForm markingForm = new MarkingForm(TEST_META_DATA_DESCRIPTION, commandName, getUserInputLastIndex());

        Command command = CommandManager.getCommand(commandName);
        command.execute(markingForm);
        Todo todo = getNewlyAddedTask(Todo.class);

        Assertions.assertFalse(todo.isDone());
    }

    @Test
    public void testDeleteTaskCommand() {
        // prefill the data set
        Todo e = new Todo(TEST_TASK_DESCRIPTION);
        taskManager.addTask(e);
        int previousSize = taskManager.getTaskSize();

        String commandName = CommandEnum.DELETE_TASK.getName();
        DeleteForm deleteForm = new DeleteForm(TEST_META_DATA_DESCRIPTION, commandName, getUserInputLastIndex());

        Command command = CommandManager.getCommand(commandName);
        command.execute(deleteForm);

        Assertions.assertEquals(previousSize - 1, taskManager.getTaskSize());
    }

    /**
     * @description get newly added task from list
     * @author Dex
     * @date 2022/09/23
     * @param clazz: class type inherit from Task
     */
    @SuppressWarnings("unchecked")
    private <T extends Task> T getNewlyAddedTask(Class<T> clazz) {
        return (T) taskManager.getTaskByIndex(getLastIndex());
    }

    private int getLastIndex() {
        if (taskManager.getTaskSize() < 1) {
            throw new DukeException("current task list is empty, no last index found");
        }
        return taskManager.getTaskSize() - 1;
    }

    private int getUserInputLastIndex() {
        return getLastIndex() + 1;
    }
}
