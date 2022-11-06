package logic.parsers;

import common.exceptions.EmptyTaskListException;
import common.exceptions.InvalidTaskDescriptionException;
import common.exceptions.NotExistTaskException;
import common.exceptions.MarkedTaskException;
import common.exceptions.UnmarkedTaskException;
import common.exceptions.DuplicatedTaskException;
import logic.commands.AddDeadlineCommand;
import logic.commands.Command;
import model.Chat;
import model.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.ConsoleUi;

import java.util.ArrayList;

import static common.enums.CommandEnum.deadline;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    @DisplayName("happy path - add deadline command")
    public void parseChatHappyPathTest() throws EmptyTaskListException, DuplicatedTaskException, UnmarkedTaskException, InvalidTaskDescriptionException, NotExistTaskException, MarkedTaskException {
        try {
            ConsoleUi ui = new ConsoleUi();
            ArrayList<Task> taskList = new ArrayList<>();
            Chat chat = new Chat(deadline, "deadline homework /by 2022-11-14", taskList);

            Command addDeadlineCom = new AddDeadlineCommand(ui, chat);
            addDeadlineCom.execute();
            assertEquals(chat.getTaskList().size(), 1);
         }catch (InvalidTaskDescriptionException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("unhappy path - add deadline command")
    public void parseChatUnhappyPathTest() throws EmptyTaskListException, DuplicatedTaskException, UnmarkedTaskException, InvalidTaskDescriptionException, NotExistTaskException, MarkedTaskException {
        try {
            ConsoleUi ui = new ConsoleUi();
            ArrayList<Task> taskList = new ArrayList<>();
            Chat chat = new Chat(deadline, "deadline homework", taskList);

            Command addDeadlineCom = new AddDeadlineCommand(ui, chat);
            addDeadlineCom.execute();
            assertEquals(chat.getTaskList().size(), 0);
        } catch (InvalidTaskDescriptionException e) {
            System.out.println(e);
        }
    }
}
