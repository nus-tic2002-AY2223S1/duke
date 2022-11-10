package task;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDateTime;

import static formatting.Helper.FORMATTER;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void eventTestMethod() throws ParseException {
        LocalDateTime startDate = LocalDateTime.parse("10/11/2022 0000", FORMATTER);
        LocalDateTime endDate = LocalDateTime.parse("10/11/2022 0100", FORMATTER);

        Event event1 = new Event("Junit event test1",startDate, endDate);
        assertEquals("[E][ ] Junit event test1 (at: 10/11/2022 0000 to 10/11/2022 0100)", event1.toString());
    }
}
