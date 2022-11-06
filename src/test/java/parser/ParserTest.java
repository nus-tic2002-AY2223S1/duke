package parser;

import command.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ParserTest {

    @Test
    public void ToDoCommandTest() {
        Command c = Parser.parse("todo go swimming");
        assertSame(ToDoCommand.class, c.getClass());
    }

    @Test
    public void ListCommandTest() {
        Command c = Parser.parse("list");
        assertSame(ListCommand.class, c.getClass());
    }

    @Test
    public void ByeCommandTest() {
        Command c = Parser.parse("bye");
        assertSame(ByeCommand.class, c.getClass());
    }

    @Test
    public void MarkCommandTest() {
        Command c = Parser.parse("mark 1");
        assertSame(MarkCommand.class, c.getClass());
    }

    @Test
    public void UnmarkCommandTest() {
        Command c = Parser.parse("unmark 2");
        assertSame(UnmarkCommand.class, c.getClass());
    }

    @Test
    public void EventCommandTest() {
        Command c = Parser.parse("event attend swimming class /at 2/02/2023 1230");
        assertSame(EventCommand.class, c.getClass());
    }

    @Test
    public void DeadlineCommandTest() {
        Command c = Parser.parse("deadline finish math homework /by 2/02/2023 1330");
        assertSame(DeadlineCommand.class, c.getClass());
    }

    @Test
    public void DeleteCommandTest() {
        Command c = Parser.parse("delete 3");
        assertSame(DeleteCommand.class, c.getClass());
    }

    @Test
    public void FindTaskCommandTest() {
        Command c = Parser.parse("findtask 2/02/2023 1330");
        assertSame(FindtaskCommand.class, c.getClass());
    }

    @Test
    public void ErrorCommandTest() {
        Command c = Parser.parse("test");
        assertSame(ErrorCommand.class, c.getClass());
    }

    @Test
    public void WrongInputCommandTest() {
        Command c = Parser.parse("deadline finish math homework /by  2/02/2023");
        assertSame(ErrorCommand.class, c.getClass());
    }

    @Test
    public void WrongInputCommandAnotherTest() {
        Command c = Parser.parse("deadline finish math homework /by 2-02-2023 1330");
        assertSame(ErrorCommand.class, c.getClass());
    }

    @Test
    public void PriorityCommandTest() {
        Command c = Parser.parse("priority 1 high");
        assertSame(PriorityCommand.class, c.getClass());
    }

    @Test
    public void FindCommandTest() {
        Command c = Parser.parse("find this is test");
        assertSame(FindCommand.class, c.getClass());
    }

    @Test
    public void DoWithinPeriodCommandTest() {
        Command c = Parser.parse("dowithinperiod have dinner with friends /between 02/03/2022 /and 05/06/2023");
        assertSame(DoWithinPeriodCommand.class, c.getClass());
    }
}
