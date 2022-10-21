package nus.duke.storage;

import nus.duke.tasklist.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.ArrayList;
import nus.duke.task.Task;

class StorageTest {
    String filePath = "/Users/rebecca/Desktop/Duke/data/DukeTasks.txt";
    Storage storage = new Storage(filePath);

    @Test
    void hardDiskDataIsLoaded() throws IOException {
        TaskList tl = new TaskList();
        tl.addTask("TODO send dogs to the groomers");
        tl.addTask("DEADLINE finish assignment /by 21-10-2022");
        ArrayList<Task> loadedTaskList = storage.load();
        assertEquals(tl, storage.load());
    }

}