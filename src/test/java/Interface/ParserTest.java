package Interface;

import Duke.Interface.Cmd;
import Duke.Interface.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parseListTest() {
        String[] s = {"list"};
        Parser p = new Parser();
        Cmd actual = p.injectIn(s).parse();
        Cmd expected = new Cmd(Cmd.CmdTypes.PRINT_LIST, null);
        assertEquals(expected.t, actual.t);
        assertEquals(expected.input, actual.input);
    }

    @Test
    public void parseMarkValidTest() {
        String[] s = {"mark", "1"};
        Parser p = new Parser();
        Cmd actual = p.injectIn(s).parse();
        Cmd expected = new Cmd(Cmd.CmdTypes.MARK_TASK, s);
        assertEquals(expected.t, actual.t);
        assertEquals(expected.input, actual.input);
    }

    @Test
    public void parseMarkInvalidTest() {
        String[] s = {"mark"};
        Parser p = new Parser();
        Cmd actual = p.injectIn(s).parse();
        Cmd expected = new Cmd(Cmd.CmdTypes.MARK_TASK, s);
        assertEquals(expected.t, actual.t);
        assertEquals(expected.input, actual.input);
    }

    @Test
    public void parseUnmarkValidTest() {
        String[] s = {"unmark", "1"};
        Parser p = new Parser();
        Cmd actual = p.injectIn(s).parse();
        Cmd expected = new Cmd(Cmd.CmdTypes.UNMARK_TASK, s);
        assertEquals(expected.t, actual.t);
        assertEquals(expected.input, actual.input);
    }

    @Test
    public void parseUnmarkInvalidTest() {
        String[] s = {"unmark"};
        Parser p = new Parser();
        Cmd actual = p.injectIn(s).parse();
        Cmd expected = new Cmd(Cmd.CmdTypes.UNMARK_TASK, s);
        assertEquals(expected.t, actual.t);
        assertEquals(expected.input, actual.input);
    }

    @Test
    public void parseTodoValidTest() {
        String[] s = {"todo", "abc"};
        Parser p = new Parser();
        Cmd actual = p.injectIn(s).parse();
        Cmd expected = new Cmd(Cmd.CmdTypes.ADD_TODO, s);
        assertEquals(expected.t, actual.t);
        assertEquals(expected.input, actual.input);
    }

    @Test
    public void parseTodoInvalidTest() {
        String[] s = {"todo"};
        Parser p = new Parser();
        Cmd actual = p.injectIn(s).parse();
        Cmd expected = new Cmd(Cmd.CmdTypes.ADD_TODO, s);
        assertEquals(expected.t, actual.t);
        assertEquals(expected.input, actual.input);
    }

    @Test
    public void parseDeadlineValidTest() {
        String[] s = {"deadline", "abc"};
        Parser p = new Parser();
        Cmd actual = p.injectIn(s).parse();
        Cmd expected = new Cmd(Cmd.CmdTypes.ADD_DEADLINE, s);
        assertEquals(expected.t, actual.t);
        assertEquals(expected.input, actual.input);
    }

    @Test
    public void parseTDeadlineInvalidTest() {
        String[] s = {"deadline"};
        Parser p = new Parser();
        Cmd actual = p.injectIn(s).parse();
        Cmd expected = new Cmd(Cmd.CmdTypes.ADD_DEADLINE, s);
        assertEquals(expected.t, actual.t);
        assertEquals(expected.input, actual.input);
    }

    @Test
    public void parseEventValidTest() {
        String[] s = {"event", "abc"};
        Parser p = new Parser();
        Cmd actual = p.injectIn(s).parse();
        Cmd expected = new Cmd(Cmd.CmdTypes.ADD_EVENT, s);
        assertEquals(expected.t, actual.t);
        assertEquals(expected.input, actual.input);
    }

    @Test
    public void parseTEventInvalidTest() {
        String[] s = {"event"};
        Parser p = new Parser();
        Cmd actual = p.injectIn(s).parse();
        Cmd expected = new Cmd(Cmd.CmdTypes.ADD_EVENT, s);
        assertEquals(expected.t, actual.t);
        assertEquals(expected.input, actual.input);
    }

    @Test
    public void parseDeleteValidTest() {
        String[] s = {"delete", "1"};
        Parser p = new Parser();
        Cmd actual = p.injectIn(s).parse();
        Cmd expected = new Cmd(Cmd.CmdTypes.DELETE_TASK, s);
        assertEquals(expected.t, actual.t);
        assertEquals(expected.input, actual.input);
    }

    @Test
    public void parseTDeleteInvalidTest() {
        String[] s = {"delete"};
        Parser p = new Parser();
        Cmd actual = p.injectIn(s).parse();
        Cmd expected = new Cmd(Cmd.CmdTypes.DELETE_TASK, s);
        assertEquals(expected.t, actual.t);
        assertEquals(expected.input, actual.input);
    }

    @Test
    public void parseByeTest() {
        String[] s = {"bye"};
        Parser p = new Parser();
        Cmd actual = p.injectIn(s).parse();
        Cmd expected = new Cmd(Cmd.CmdTypes.EXIT, null);
        assertEquals(expected.t, actual.t);
        assertEquals(expected.input, actual.input);
    }

    @Test
    public void parseDayValidTest() {
        String[] s = {"day", "1/1/1999"};
        Parser p = new Parser();
        Cmd actual = p.injectIn(s).parse();
        Cmd expected = new Cmd(Cmd.CmdTypes.FIND_DATE, s);
        assertEquals(expected.t, actual.t);
        assertEquals(expected.input, actual.input);
    }

    @Test
    public void parseTDayInvalidTest() {
        String[] s = {"day"};
        Parser p = new Parser();
        Cmd actual = p.injectIn(s).parse();
        Cmd expected = new Cmd(Cmd.CmdTypes.FIND_DATE, s);
        assertEquals(expected.t, actual.t);
        assertEquals(expected.input, actual.input);
    }

    @Test
    public void parseFindValidTest() {
        String[] s = {"find", "keyword"};
        Parser p = new Parser();
        Cmd actual = p.injectIn(s).parse();
        Cmd expected = new Cmd(Cmd.CmdTypes.FIND_TASK, s);
        assertEquals(expected.t, actual.t);
        assertEquals(expected.input, actual.input);
    }

    @Test
    public void parseTFindInvalidTest() {
        String[] s = {"find"};
        Parser p = new Parser();
        Cmd actual = p.injectIn(s).parse();
        Cmd expected = new Cmd(Cmd.CmdTypes.FIND_TASK, s);
        assertEquals(expected.t, actual.t);
        assertEquals(expected.input, actual.input);
    }

    @Test
    public void parseBlankTest() {
        String[] s = {""};
        Parser p = new Parser();
        Cmd actual = p.injectIn(s).parse();
        Cmd expected = new Cmd(Cmd.CmdTypes.RETURN, null);
        assertEquals(expected.t, actual.t);
        assertEquals(expected.input, actual.input);
    }

    @Test
    public void parseRandomTextTest() {
        String[] s = {"loremIpsum"};
        Parser p = new Parser();
        Cmd actual = p.injectIn(s).parse();
        Cmd expected = new Cmd(Cmd.CmdTypes.UNKNOWN_CMD, null);
        assertEquals(expected.t, actual.t);
        assertEquals(expected.input, actual.input);
    }
}