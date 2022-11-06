package command;

import org.junit.jupiter.api.Test;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventCommandTest {

    @Test
    public void EventCommandConstructorTest() {
        LocalDateTime eventDatetime = LocalDateTime.parse("02/04/2023 1230", DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
        String eventDatetimeString = eventDatetime.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        EventCommand eventCommand = new EventCommand("Teatime at marina bay with family", eventDatetimeString);

        assertEquals("2 Apr 2023 1230", eventCommand.atDate);
        assertEquals("Teatime at marina bay with family", eventCommand.description);
    }

    @Test
    public void EventCommandAnotherConstructorTest() {
        LocalDateTime eventDatetime = LocalDateTime.parse("12/12/2023 2130", DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
        String eventDatetimeString = eventDatetime.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        EventCommand eventCommand = new EventCommand("Teatime at vivo with family", eventDatetimeString);

        assertEquals("12 Dec 2023 2130", eventCommand.atDate);
        assertEquals("Teatime at vivo with family", eventCommand.description);
    }

    @Test
    public void EventCommandExecuteTest() {
        TaskList task = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/test.txt");

        LocalDateTime eventDatetime = LocalDateTime.parse("02/04/2023 1230", DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
        String eventDatetimeString = eventDatetime.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        EventCommand eventCommand = new EventCommand("Teatime at marina bay with family", eventDatetimeString);
        eventCommand.execute(task, ui, storage);

        assertEquals(1, task.myTaskList.size());
        assertEquals("Teatime at marina bay with family", task.myTaskList.get(0).getDescription());
    }
}
