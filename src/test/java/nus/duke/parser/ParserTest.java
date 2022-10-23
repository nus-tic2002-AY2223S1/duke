package nus.duke.parser;

import nus.duke.exceptions.*;
import org.junit.jupiter.api.Test;
import static nus.duke.parser.Parser.*;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parserCorrectlyThrowsEmptyTaskException() {
        assertThrows(EmptyTaskException.class, () -> hasInputErrors("TODO"));
        assertThrows(EmptyTaskException.class, () -> hasInputErrors("TODO "));
        assertThrows(EmptyTaskException.class, () -> hasInputErrors("TODO     "));
        assertThrows(EmptyTaskException.class, () -> hasInputErrors("DEADLINE"));
        assertThrows(EmptyTaskException.class, () -> hasInputErrors("DEADLINE "));
        assertThrows(EmptyTaskException.class, () -> hasInputErrors("DEADLINE     "));
        assertThrows(EmptyTaskException.class, () -> hasInputErrors("EVENT"));
        assertThrows(EmptyTaskException.class, () -> hasInputErrors("EVENT "));
        assertThrows(EmptyTaskException.class, () -> hasInputErrors("EVENT     "));
        assertThrows(EmptyTaskException.class, () -> hasInputErrors("MARK"));
        assertThrows(EmptyTaskException.class, () -> hasInputErrors("MARK "));
        assertThrows(EmptyTaskException.class, () -> hasInputErrors("MARK     "));
        assertThrows(EmptyTaskException.class, () -> hasInputErrors("UNMARK"));
        assertThrows(EmptyTaskException.class, () -> hasInputErrors("UNMARK "));
        assertThrows(EmptyTaskException.class, () -> hasInputErrors("UNMARK     "));
    }

    @Test
    void parserCorrectlyThrowsMissingKeywordException() {
        assertThrows(MissingKeywordException.class, () -> hasInputErrors("DEADLINE ethics essay"));
        assertThrows(MissingKeywordException.class, () -> hasInputErrors("EVENT school bake sale"));
    }

    @Test
    void parserCorrectlyThrowsMissingDateException() {
        assertThrows(MissingDateException.class, () -> hasInputErrors("DEADLINE ethics essay /by"));
        assertThrows(MissingDateException.class, () -> hasInputErrors("DEADLINE ethics essay /by "));
        assertThrows(MissingDateException.class, () -> hasInputErrors("DEADLINE ethics essay /by tomorrow"));
        assertThrows(MissingDateException.class, () -> hasInputErrors("DEADLINE ethics essay /by 01-01-2022"));
        assertThrows(MissingDateException.class, () -> hasInputErrors("DEADLINE ethics essay /by 01012022"));
    }

    @Test
    void parserChecksThatCommandIsValid() {
        assertThrows(InvalidCommandException.class, () -> hasInputErrors("hi"));
        assertThrows(InvalidCommandException.class, () -> hasInputErrors("hi "));
        assertThrows(InvalidCommandException.class, () -> hasInputErrors("randomW0rd"));
        assertThrows(InvalidCommandException.class, () -> hasInputErrors("197298372"));
        assertThrows(InvalidCommandException.class, () -> hasInputErrors("19282 "));
        assertThrows(InvalidCommandException.class, () -> hasInputErrors(" "));
    }
}