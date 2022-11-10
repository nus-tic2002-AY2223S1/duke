package tasklist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void DeadlineTest() {
        Deadline deadline = new Deadline("Mail parcel for 11/11","19-11-2022");
        assertEquals("Mail parcel for 11/11 (by: Nov 19 2022)", deadline.getDescription() + " (by: Nov 19 2022)");
    }

    @Test
    public void DeadlineStringTest() {
        Deadline deadline = new Deadline("Mail parcel for 11/11","19-11-2022");
        assertEquals("[D][ ]   Mail parcel for 11/11 (by: Nov 19 2022)", deadline.toString());
    }

}

