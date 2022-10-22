package command;

import org.junit.jupiter.api.Test;
import storage.Storage;
import task.TaskList;
import task.ToDo;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkCommandTest {

    @Test
    public void MarkCommandConstructorTest() {
        MarkCommand markCommand = new MarkCommand(1);
        assertEquals(1, markCommand.markIndex);
    }

    @Test
    public void MarkCommandAnotherConstructorTest() {
        MarkCommand markCommand = new MarkCommand(2);
        assertEquals(2, markCommand.markIndex);
    }

    @Test
    public void MarkCommandExecuteTest() {
        TaskList task = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/test.txt");
        task.myTaskList.add(new ToDo("Do math homework"));
        task.myTaskList.add(new ToDo("Piano practice for 1 hour"));
        MarkCommand markCommand = new MarkCommand(1);
        markCommand.execute(task, ui, storage);
        assertEquals("X", task.myTaskList.get(1).getStatusIcon());
    }
}
