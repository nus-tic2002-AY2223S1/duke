package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.impl.Ui;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

public class DukeTest {
    @Test
    public void dukeInitWithFileTest() {
        String path = "src/test/save_test/output_test.txt";
        Ui.LocaleRegion l = Ui.LocaleRegion.EN;
        Duke d = new Duke(path, l);

        TaskList expected = new TaskList();
        expected.addTask(new Todo("TestTask1", 0));
        expected.addTask(new Event("TestTask2", new long[]{0}));
        expected.addTask(new Deadline("TestTask3", 0));
        Task expectedTask1 = expected.getList().get(0);
        expectedTask1.markTask();
        Task expectedTask2 = expected.getList().get(1);
        Task expectedTask3 = expected.getList().get(2);

        TaskList t = d.getTaskList();
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
    public void dukeInitWithNoFileTest() {
        String path = "src/test/save_test/file.txt";
        Ui.LocaleRegion l = Ui.LocaleRegion.EN;
        Duke d = new Duke(path, l);
        ArrayList<Task> t = d.getTaskList().getList();
        assertEquals(0, t.size());
    }

    @Test
    public void dukeGetResponseTest() {
        String path = "src/test/save_test/output_test.txt";
        Ui.LocaleRegion l = Ui.LocaleRegion.EN;
        Duke d = new Duke(path, l);
        String expected = "✔ Okay, I have added the following task:\n\t\t[Todo][   ] Task1\n\tYou now have 4 tasks.";
        String actual = d.getResponse("todo Task1");
        assertEquals(expected, actual);
    }

    @Test
    public void dukeGetWelcomeNewUserListTaskTest() {
        String path = "src/test/save_test/file.txt";
        Ui.LocaleRegion l = Ui.LocaleRegion.EN;
        Duke d = new Duke(path, l);
        String expected = "» Hello from Duke. What can I do for you?";
        String[] actual = d.getWelcome(true);

        assertEquals(expected, actual[0]);
    }

    @Test
    public void dukeGetWelcomeNewUserNoListTaskTest() {
        String path = "src/test/save_test/file.txt";
        Ui.LocaleRegion l = Ui.LocaleRegion.EN;
        Duke d = new Duke(path, l);
        String expected = "» Hello from Duke. What can I do for you?";
        String[] actual = d.getWelcome(true);

        assertEquals(expected, actual[0]);
    }

    @Test
    public void dukeGetWelcomeReturningUserListTaskTest() {
        String path = "src/test/save_test/output_test.txt";
        Ui.LocaleRegion l = Ui.LocaleRegion.EN;
        Duke d = new Duke(path, l);
        String expected1 = "» Hello again, bytedance! Welcome back. What can I do for you?";
        String expected2 = "✔ Here are your task(s):\n"
                + "1.\t[Todo][⦿] TestTask1\n"
                + "2.\t[Event][   ] TestTask2\n"
                + "3.\t[Deadline][   ] TestTask3";
        String expected3 = "[Last Modified on Sun 06 Nov 2022, 2:53 PM]\n";
        String[] actual = d.getWelcome(true);

        assertEquals(expected1, actual[0]);
        assertEquals(expected2, actual[1]);
        assertEquals(expected3, actual[2]);
    }

    @Test
    public void dukeGetWelcomeReturningUserNoListTaskTest() {
        String path = "src/test/save_test/output_test.txt";
        Ui.LocaleRegion l = Ui.LocaleRegion.EN;
        Duke d = new Duke(path, l);
        String expected1 = "» Hello again, bytedance! Welcome back. What can I do for you?";
        String[] actual = d.getWelcome(false);

        assertEquals(expected1, actual[0]);
    }

    @Test
    public void dukeSetLocaleTest() {
        String path = "src/test/save_test/output_test.txt";
        Ui.LocaleRegion l = Ui.LocaleRegion.EN;
        Duke d = new Duke(path, l);
        Ui.LocaleRegion expected = Ui.LocaleRegion.CN;
        d.setLocale(expected);
        Ui.LocaleRegion actual = d.getLocale();

        assertEquals(expected, actual);
    }
}
