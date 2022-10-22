package command;

import org.junit.jupiter.api.Test;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoCommandTest {

    @Test
    public void ToDoCommandConstructorTest() {
        ToDoCommand toDoCommand = new ToDoCommand("Write an essay");
        assertEquals("Write an essay", toDoCommand.description);
    }

    @Test
    public void ToDoCommandAnotherConstructorTest() {
        ToDoCommand toDoCommand = new ToDoCommand("Go bowling with friends");
        assertEquals("Go bowling with friends", toDoCommand.description);
    }

    @Test
    public void ToDoCommandExecuteTest() {

        TaskList task = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/test.txt");

        ToDoCommand toDoCommand = new ToDoCommand("Go bowling with friends");
        toDoCommand.execute(task, ui, storage);

        assertEquals(1, task.myTaskList.size());
        assertEquals("Go bowling with friends", task.myTaskList.get(0).getDescription());
    }
}
