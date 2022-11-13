package processor;


import domain.TaskList;
import domain.task.Deadline;
import domain.task.Event;
import domain.task.Task;
import domain.task.Todo;
import exceptions.EmptyDescriptionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processor.impl.MemoryProcessorImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.CommonStrings.PATH_FROM_DISK;

public class MemoryProcessorImplTest {
    private final MemoryProcessor memoryProcessor = new MemoryProcessorImpl();
    private final File file = new File(PATH_FROM_DISK);
    @BeforeEach
    public void before() {
        // delete file
        if (file.delete()){
            System.out.println("file deleted successfully");
        } else {
            System.out.println("failed to delete the file");
        }
    }
    @Test
    public void happyPathTest() throws EmptyDescriptionException, FileNotFoundException {
        Task task1 = new Todo("test todo");
        task1.markDone();
        Task task2 = new Deadline("test deadline", LocalDate.parse("2022-12-26"));
        Task task3 = new Event("test event", "test at");
        TaskList taskList = new TaskList(Arrays.asList(task1, task2, task3));

        memoryProcessor.save(taskList);
        assertTrue(file.exists());
        String expectedFileContents = "T | 1 | test todo\n" +
                "D | 0 | test deadline | 2022-12-26\n" +
                "E | 0 | test event | test at";
        String actualFileContents = getFileContentsAsString(file);
        assertEquals(expectedFileContents, actualFileContents);

        Task task4 = new Todo("test todo 2");
        taskList = new TaskList(Arrays.asList(task1, task2, task3, task4));
        memoryProcessor.save(taskList);
        assertTrue(file.exists());
        expectedFileContents = "T | 1 | test todo\n" +
                "D | 0 | test deadline | 2022-12-26\n" +
                "E | 0 | test event | test at\n" +
                "T | 0 | test todo 2";
        actualFileContents = getFileContentsAsString(file);
        assertEquals(expectedFileContents, actualFileContents);

        // test load
        TaskList actualTaskList = new TaskList();
        memoryProcessor.load(actualTaskList);
        assertEquals(taskList.getTaskCount(), actualTaskList.getTaskCount());
        for (int i = 0; i < actualTaskList.getTaskCount(); i++) {
            assertEquals(taskList.getTask(i).getDescription(), actualTaskList.getTask(i).getDescription());
            assertEquals(taskList.getTask(i).getStatusIcon(), actualTaskList.getTask(i).getStatusIcon());
        }
    }

    private String getFileContentsAsString(File file) throws FileNotFoundException {
        Scanner sc;
        sc = new Scanner(file);
        StringBuilder builder = new StringBuilder();

        // edge case
        if (!sc.hasNextLine()) {
            return "";
        }

        builder.append(sc.nextLine());
        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            builder.append("\n").append(nextLine);
        }
        return builder.toString();
    }
}