package nus.duke.storage;

import nus.duke.tasklist.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

class StorageTest {
    String filePath = "/Users/rebecca/Desktop/Duke/data/DukeTasks.txt";
    Storage storage = new Storage(filePath);

    @Test
    void hardDiskDataIsLoaded() throws IOException {
        TaskList tl = new TaskList();
        assertEquals(tl.getTaskList(), storage.load());
    }
}