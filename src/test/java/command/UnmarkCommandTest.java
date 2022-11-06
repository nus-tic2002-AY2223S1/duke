package command;

import org.junit.jupiter.api.Test;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnmarkCommandTest {
    @Test
    public void UnmarkCommandConstructorTest() {
        UnmarkCommand unmarkCommand = new UnmarkCommand(1);
        assertEquals(1, unmarkCommand.unMarkIndex);
    }

    @Test
    public void UnmarkCommandAnotherConstructorTest() {
        UnmarkCommand unmarkCommand = new UnmarkCommand(2);
        assertEquals(2, unmarkCommand.unMarkIndex);
    }

    @Test
    public void UnmarkCommandExecuteTest() {

        TaskList task = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/test.txt");

        ToDoCommand toDoCommand = new ToDoCommand("Go bowling with friends");
        toDoCommand.execute(task, ui, storage);
        ToDoCommand toDoCommand2 = new ToDoCommand("Have lunch with family");
        toDoCommand2.execute(task, ui, storage);
        MarkCommand markCommand = new MarkCommand(1);
        markCommand.execute(task, ui, storage);
        UnmarkCommand unmarkCommand = new UnmarkCommand(1);
        unmarkCommand.execute(task, ui, storage);

        assertEquals(" ", task.myTaskList.get(1).getStatusIcon());
    }
}
