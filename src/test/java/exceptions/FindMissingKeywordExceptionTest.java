package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FindMissingKeywordExceptionTest {
    @Test
    public void FindMissingKeywordExceptionConstructorTest() {
        FindMissingKeywordException findMissingKeywordException = new FindMissingKeywordException();
        assertEquals("OOPS!!! Keyword is missing. The find command should be: find {keyword}", findMissingKeywordException.errorMsg);
    }

    @Test
    public void FindMissingKeywordExceptionAnotherConstructorTest() {
        FindMissingKeywordException findMissingKeywordException = new FindMissingKeywordException();
        assertNotEquals("This is a wrong message", findMissingKeywordException.errorMsg);
    }

    @Test
    public void FindMissingKeywordExceptionLastConstructorTest() {
        FindMissingKeywordException findMissingKeywordException = new FindMissingKeywordException();
        assertNotEquals("This is another wrong message", findMissingKeywordException.errorMsg);
    }
}
