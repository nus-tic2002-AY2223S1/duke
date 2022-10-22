package command;

import org.junit.jupiter.api.Test;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {

    @Test
    public void CommandConstructorTest() {
        Command c = new Command();
        assertEquals(false, c.exitValue);
    }

    @Test
    public void CommandAnotherConstructorTest() {
        Command c = new Command(true);
        assertEquals(true, c.exitValue);
    }

    @Test
    public void CommandExecuteTest() {
        TaskList task = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/test.txt");
        ToDoCommand toDoCommand = new ToDoCommand("Go skateboard class");
        toDoCommand.execute(task, ui, storage);
        assertEquals(1, task.myTaskList.size());
        assertEquals("Go skateboard class", task.myTaskList.get(0).getDescription());
    }
}
