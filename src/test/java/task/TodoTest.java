package task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest extends TaskTest{

    @Test
    public void todoTestMethod(){
        Todo Todo1 = new Todo("Junit todo test1");
        assertEquals("[T][ ] Junit todo test1", Todo1.toString());

        String testGetTaskName = Todo1.getTaskName();
        assertEquals("Junit todo test1",testGetTaskName);

        Todo1.setMarkAsDone();
        assertEquals("[T][X] Junit todo test1", Todo1.toString());

        Todo1.setMarkNotDone();
        assertEquals("[T][ ] Junit todo test1", Todo1.toString());

    }
}
