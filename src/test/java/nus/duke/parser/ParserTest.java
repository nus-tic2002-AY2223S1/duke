package nus.duke.parser;

import nus.duke.data.DukeException;
import nus.duke.ui.Messages;
import nus.duke.tasklist.TaskList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    static private Command userInput;
    static void initEach(String command) {
        try {
            userInput = Parser.parse(command);
        } catch (NullPointerException e){
        } catch (DukeException e){
        }
    }
    @Test
    void succeedingTest() {
        //Correct parsing of a "bye" command.
        initEach("bye");
        assertEquals("bye", userInput.getCommand());
        assertTrue(userInput.isExit());

        //Correct parsing of a "list" command.
        initEach("list");
        assertEquals("list", userInput.getCommand());

        //Correct parsing of a "mark" command, index = entered number - 1.
        initEach("mark 1");
        assertEquals("mark", userInput.getCommand());
        assertEquals(0, userInput.getIndex());

        //Correct parsing of a "unmark" command, index = entered number - 1.
        initEach("unmark 1");
        assertEquals("unmark", userInput.getCommand());
        assertEquals(0, userInput.getIndex());

        //Correct parsing of a "delete" command, index = entered number - 1.
        initEach("delete 1");
        assertEquals("delete", userInput.getCommand());
        assertEquals(0, userInput.getIndex());

        //Correct parsing of a "todo" command.
        initEach("todo plan for travel");
        assertEquals("todo", userInput.getCommand());
        assertEquals("plan for travel", userInput.getDescription());

        //Correct parsing of a "deadline" command with date/time format DD/MM/YYYY hhmm.
        initEach("deadline return book /by 11/11/2022 1800");
        assertEquals("deadline", userInput.getCommand());
        assertEquals("return book", userInput.getDescription());
        assertEquals("by", userInput.getPreposition());
        assertEquals(LocalDateTime.parse("11-11-2022" + " " + "18:00:00", TaskList.STORAGE_FORMATTER), userInput.getDateAndTime1());
            //Same command with date/time format DD/MM/YYYY.
        initEach("deadline return book /by 11/11/2022");
        assertEquals(LocalDateTime.parse("11-11-2022" + " " + Parser.DEFAULT_TIME, TaskList.STORAGE_FORMATTER), userInput.getDateAndTime1());

        //Correct parsing of an "event" command with date/time format DD/MM/YYYY hhmm - DD/MM/YYYY hhmm.
        initEach("event trip to Japan /at 11/11/2022 1800-20/11/2022 0030");
        assertEquals("event", userInput.getCommand());
        assertEquals("trip to Japan", userInput.getDescription());
        assertEquals("at", userInput.getPreposition());
        assertEquals(LocalDateTime.parse("11-11-2022" + " " + "18:00:00", TaskList.STORAGE_FORMATTER), userInput.getDateAndTime1());
        assertEquals(LocalDateTime.parse("20-11-2022" + " " + "00:30:00", TaskList.STORAGE_FORMATTER), userInput.getDateAndTime2());
            //Same command with date/time format DD/MM/YYYY hhmm-hhmm.
        initEach("event trip to Japan /at 11/11/2022 1800-2330");
        assertEquals(LocalDateTime.parse("11-11-2022" + " " + "18:00:00", TaskList.STORAGE_FORMATTER), userInput.getDateAndTime1());
        assertEquals(LocalDateTime.parse("11-11-2022" + " " + "23:30:00", TaskList.STORAGE_FORMATTER), userInput.getDateAndTime2());
            //Same command with date/time format DD/MM/YYYY-DD/MM/YYYY.
        initEach("event trip to Japan /at 11/11/2022-12/11/2022");
        assertEquals(LocalDateTime.parse("11-11-2022" + " " + Parser.DEFAULT_START_TIME, TaskList.STORAGE_FORMATTER), userInput.getDateAndTime1());
        assertEquals(LocalDateTime.parse("12-11-2022" + " " + Parser.DEFAULT_TIME, TaskList.STORAGE_FORMATTER), userInput.getDateAndTime2());
    }

    @DisplayName("Exceptional test for Parse class.")
    @Test
    void exceptionTesting(){
        //Test for empty command exception.
        DukeException exception = assertThrows(DukeException.class, ()->Parser.parse(""));
        assertEquals(Messages.MESSAGE_EMPTY_INPUT, exception.getMessage());

        //Test for incorrect format exception for index input on mark, unmark and delete command.
        exception = assertThrows(DukeException.class, ()->Parser.parse("mark a"));
        assertEquals(Messages.MESSAGE_INDEX_NOT_NUMBER, exception.getMessage());
        exception = assertThrows(DukeException.class, ()->Parser.parse("unmark /"));
        assertEquals(Messages.MESSAGE_INDEX_NOT_NUMBER, exception.getMessage());
        exception = assertThrows(DukeException.class, ()->Parser.parse("delete ten"));
        assertEquals(Messages.MESSAGE_INDEX_NOT_NUMBER, exception.getMessage());

        //Test for wrong deadline date/time format exception.
        exception = assertThrows(DukeException.class, ()->Parser.parse("deadline return book /by 20 12 2022 1800"));
        assertEquals(Messages.MESSAGE_WRONG_DEADLINE_DATE_TIME_FORMAT, exception.getMessage());
        //Test for wrong event date/time format exception.
        exception = assertThrows(DukeException.class, ()->Parser.parse("event meeting /at 20 12 2022 1800-2200"));
        assertEquals(Messages.MESSAGE_WRONG_EVENT_DATE_TIME_FORMAT, exception.getMessage());

        //Test for unexpected command keyword exception.
        exception = assertThrows(DukeException.class, ()->Parser.parse("must return book /by 20 12 2022 1800"));
        assertEquals(Messages.MESSAGE_NOT_A_TASK, exception.getMessage());

        //Test for adding tasks without description exception for todo, deadline, event.
        exception = assertThrows(DukeException.class, ()->Parser.parse("todo"));
        assertEquals(Messages.MESSAGE_NO_TASK_DESCRIPTION, exception.getMessage());
        exception = assertThrows(DukeException.class, ()->Parser.parse("deadline"));
        assertEquals(Messages.MESSAGE_NO_TASK_DESCRIPTION, exception.getMessage());
        exception = assertThrows(DukeException.class, ()->Parser.parse("event"));
        assertEquals(Messages.MESSAGE_NO_TASK_DESCRIPTION, exception.getMessage());

        //Test for missing keyword "/" exception for deadline and event.
        exception = assertThrows(DukeException.class, ()->Parser.parse("deadline return book by 20/12/2022 1800"));
        assertEquals(Messages.MESSAGE_NO_KEYWORD_FOR_EVENT_DEADLINE, exception.getMessage());
        exception = assertThrows(DukeException.class, ()->Parser.parse("event meeting at 20/12/2022 1800-2200"));
        assertEquals(Messages.MESSAGE_NO_KEYWORD_FOR_EVENT_DEADLINE, exception.getMessage());
    }

}
