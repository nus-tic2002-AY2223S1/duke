package Interface;

import Duke.Interface.Storage;
import Duke.Tasks.Task;
import Duke.Tasks.TaskList;
import Duke.Tasks.Todo;
import org.junit.jupiter.api.Test;

import javax.naming.event.NamingExceptionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
        expected.addTask(new Todo("sample_todo", 0));
        Task expectedTask = expected.getList().get(0);

        //actual
        TaskList t = new TaskList(new Storage("src/test/save_test/output_test.txt").load());
        Task actualTask = t.getList().get(0);

        assertEquals(expectedTask.getDescription(), actualTask.getDescription());
        assertEquals(expectedTask.getDue(), actualTask.getDue());
        assertEquals(expectedTask.getIsDone(), actualTask.getIsDone());
    }

    @Test
    public void storageFileNotFoundTest() {
        try {
            TaskList t = new TaskList(new Storage("src/test/save_test/123.txt").load());
        } catch (FileNotFoundException e) {
            assertEquals("src/test/save_test/123.txt (No such file or directory)", e.getMessage());
        }
    }
}