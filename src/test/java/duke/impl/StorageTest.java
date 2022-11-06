package duke.impl;


import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

public class StorageTest {
    @Test
    public void storageConstructorTest() {
        String path = "src/test/save_test/output_test.txt";
        Storage s = new Storage(path);
        assertEquals(path, s.getPath());
    }

    @Test
    public void storageLoadFileTest() throws FileNotFoundException {
        //expected
        TaskList expected = new TaskList();
        expected.addTask(new Todo("TestTask1", 0));
        expected.addTask(new Event("TestTask2", new long[]{0}));
        expected.addTask(new Deadline("TestTask3", 0));
        Task expectedTask1 = expected.getList().get(0);
        expectedTask1.markTask();
        Task expectedTask2 = expected.getList().get(1);
        Task expectedTask3 = expected.getList().get(2);

        //actual
        TaskList t = new TaskList(new Storage("src/test/save_test/output_test.txt").load());
        Task actualTask1 = t.getList().get(0);
        Task actualTask2 = t.getList().get(1);
        Task actualTask3 = t.getList().get(2);

        assertEquals(expectedTask1.getDescription(), actualTask1.getDescription());
        assertEquals(expectedTask1.getDue(), actualTask1.getDue());
        assertEquals(expectedTask1.getIsDone(), actualTask1.getIsDone());
        assertEquals(expectedTask2.getDescription(), actualTask2.getDescription());
        assertEquals(expectedTask2.getDue(), actualTask2.getDue());
        assertEquals(expectedTask2.getIsDone(), actualTask2.getIsDone());
        assertEquals(expectedTask3.getDescription(), actualTask3.getDescription());
        assertEquals(expectedTask3.getDue(), actualTask3.getDue());
        assertEquals(expectedTask3.getIsDone(), actualTask3.getIsDone());
    }

    @Test
    public void storageFileNotFoundTest() {
        try {
            TaskList t = new TaskList(new Storage("src/test/save_test/123.txt").load());
        } catch (FileNotFoundException e) {
            assertEquals("src/test/save_test/123.txt (No such file or directory)", e.getMessage());
        }
    }

    @Test
    public void storageFileEmptyTest() throws FileNotFoundException {
        //expected
        TaskList expected = new TaskList();
        expected.addTask(new Todo("TestTask", 0));
        Task expectedTask = expected.getList().get(0);

        //actual
        TaskList t = new TaskList(
                new Storage("src/test/save_test/output_test_empty.txt").load());
        ArrayList<Task> actualTask = t.getList();
        ArrayList<Task> expectedList = new ArrayList<>(List.of());
        assertEquals(expectedList, actualTask);
    }

    @Test
    public void storageFileOnlyHasFirstLineTest() throws FileNotFoundException {
        //expected
        TaskList expected = new TaskList();
        expected.addTask(new Todo("TestTask", 0));
        Task expectedTask = expected.getList().get(0);

        //actual
        TaskList t = new TaskList(
                new Storage("src/test/save_test/output_test_only_first_line.txt").load());
        ArrayList<Task> actualTask = t.getList();
        ArrayList<Task> expectedList = new ArrayList<>(List.of());
        assertEquals(expectedList, actualTask);
    }

    @Test
    public void storageFileOnlyHasFirstTwoLinesTest() throws FileNotFoundException {
        //expected
        TaskList expected = new TaskList();
        expected.addTask(new Todo("TestTask", 0));
        Task expectedTask = expected.getList().get(0);

        //actual
        TaskList t = new TaskList(
                new Storage("src/test/save_test/output_test_only_first_two_lines.txt").load());
        ArrayList<Task> actualTask = t.getList();
        ArrayList<Task> expectedList = new ArrayList<>(List.of());
        assertEquals(expectedList, actualTask);
    }
}
