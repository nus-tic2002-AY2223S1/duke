package command;

import org.junit.jupiter.api.Test;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineCommandTest {

    @Test
    public void DeadlineCommandConstructorTest() {
        String deadLineTask = "Attend python class";
        LocalDateTime deadlineDatetime = LocalDateTime.parse("02/04/2023 1230", DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
        String deadlineString = deadlineDatetime.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        DeadlineCommand deadline = new DeadlineCommand(deadLineTask, deadlineString);
        assertEquals("Attend python class", deadline.description);
        assertEquals("2 Apr 2023 1230", deadline.byDate);
    }

    @Test
    public void DeadlineCommandAnotherConstructorTest() {
        String deadLineTask = "Go shopping with my sister";
        LocalDateTime deadlineDatetime = LocalDateTime.parse("04/12/2022 1230", DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
        String deadlineString = deadlineDatetime.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        DeadlineCommand deadline = new DeadlineCommand(deadLineTask, deadlineString);
        assertEquals("Go shopping with my sister", deadline.description);
        assertEquals("4 Dec 2022 1230", deadline.byDate);
    }

    @Test
    public void DeadlineCommandExecuteTest() {
        TaskList task = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/test.txt");
        String deadLineTask = "task1";
        LocalDateTime deadlineDatetime = LocalDateTime.parse("02/04/2020 1230", DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
        String deadlineString = deadlineDatetime.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        DeadlineCommand deadline = new DeadlineCommand(deadLineTask, deadlineString);
        deadline.execute(task, ui, storage);
        assertEquals(1, task.myTaskList.size());
        assertEquals("[D][ ] task1 (by: 2 Apr 2020 1230)", task.myTaskList.get(0).toString());
    }
}
