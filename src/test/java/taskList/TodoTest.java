package taskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoTest {
    @Test
    public void TodoTest() {
        Todo todo = new Todo("Purchase stamps for mailing");
        assertEquals("Purchase stamps for mailing", todo.getDescription());
    }

    @Test
    public void ToDoStringTest() {
        ToDo todo = new ToDo("Purchase stamps for mailing");
        assertEquals("[T][ ] Purchase stamps for mailing", todo.toString());
    }

}
