package storage;

import exceptions.DukeException;
import org.junit.jupiter.api.Test;
import task.TaskList;
import task.ToDo;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void StorageSaveTest() {
        TaskList task = new TaskList();
        task.myTaskList.add(new ToDo("Go swimming"));
        Storage storage = new Storage("data/test.txt");
        Storage.save("data/test.txt", task.myTaskList);
        TaskList extractedTask;

        try {
            extractedTask = new TaskList(storage.load());
        } catch (DukeException e) {
            extractedTask = new TaskList();
        } catch (FileNotFoundException e) {
            extractedTask = new TaskList();
        }
        assertEquals(1, extractedTask.myTaskList.size());
        assertEquals("Go swimming", extractedTask.myTaskList.get(0).getDescription());
    }

    @Test
    public void StorageAnotherSaveTest() {
        TaskList task = new TaskList();
        task.myTaskList.add(new ToDo("Book a dentist appointment"));
        Storage storage = new Storage("data/test.txt");
        Storage.save("data/test.txt", task.myTaskList);
        TaskList extractedTask;

        try {
            extractedTask = new TaskList(storage.load());
        } catch (DukeException e) {
            extractedTask = new TaskList();
        } catch (FileNotFoundException e) {
            extractedTask = new TaskList();
        }
        assertEquals(1, extractedTask.myTaskList.size());
        assertEquals("Book a dentist appointment", extractedTask.myTaskList.get(0).getDescription());
    }

    @Test
    public void StorageLastSaveTest() {
        TaskList task = new TaskList();
        task.myTaskList.add(new ToDo("Book a dentist appointment"));
        task.myTaskList.add(new ToDo("Read a novel"));
        Storage storage = new Storage("data/test.txt");
        Storage.save("data/test.txt", task.myTaskList);
        TaskList extractedTask;

        try {
            extractedTask = new TaskList(storage.load());
        } catch (DukeException e) {
            extractedTask = new TaskList();
        } catch (FileNotFoundException e) {
            extractedTask = new TaskList();
        }
        assertEquals(2, extractedTask.myTaskList.size());
    }
}
