package duke.service;

import duke.entity.Task;
import duke.util.FileUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

class TaskManagerTest {

    private static final String TEST_FILE_PATH = "data/init.txt";

    private static final String TEST_TASK_DESCRIPTION = "task description";

    @BeforeAll
    static void init() {
        TaskManager.init(TEST_FILE_PATH);
    }

    @AfterAll
    static void cleanup() {
        FileUtil.deleteFile(new File(TEST_FILE_PATH));
    }

    @Test
    void testInit() {
        TaskManager.init(TEST_FILE_PATH);
        File file = new File(TEST_FILE_PATH);
        Assertions.assertTrue(file.exists());
    }

    @Test
    void testPersistTask() {
        TaskManager taskManager = TaskManager.getInstance();

        // prefill the task
        Task task = new Task(TEST_TASK_DESCRIPTION);
        taskManager.addTask(task);
        taskManager.persistTask();

        String content = FileUtil.readFileContent(new File(TEST_FILE_PATH));
        String mockContent = "{\"description\":\"task description\",\"done\":false}";
        Assertions.assertEquals(mockContent, content);
    }

    @Test
    void testFindTask() {
        TaskManager taskManager = TaskManager.getInstance();

        // prefill the task
        Task task = new Task(TEST_TASK_DESCRIPTION);
        taskManager.addTask(task);

        List<Task> taskList1 = taskManager.findTask("description");
        Assertions.assertFalse(taskList1.isEmpty());

        List<Task> taskList2 = taskManager.findTask("not exist");
        Assertions.assertTrue(taskList2.isEmpty());
    }

    @Test
    void testAddTask() {
        TaskManager taskManager = TaskManager.getInstance();
        Task task = new Task(TEST_TASK_DESCRIPTION);
        taskManager.addTask(task);

        Task addedTask = taskManager.getTaskByIndex(0);
        Assertions.assertEquals(task.getDescription(), addedTask.getDescription());
        Assertions.assertEquals(task.getType(), addedTask.getType());
        Assertions.assertEquals(task.isDone(), addedTask.isDone());
    }

    @Test
    void testRemoveTask() {
        TaskManager taskManager = TaskManager.getInstance();
        Task task = new Task(TEST_TASK_DESCRIPTION);
        taskManager.addTask(task);

        Assertions.assertFalse(taskManager.getTaskList().isEmpty());
        taskManager.removeTask(0);
        Assertions.assertTrue(taskManager.getTaskList().isEmpty());
    }
}
