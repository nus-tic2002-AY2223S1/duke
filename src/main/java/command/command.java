package command;

import entities.Deadline;
import entities.Event;
import entities.Task;
import entities.Todo;
import utils.DukeUtils;

import java.util.List;

public class command {
    public static void createDeadline(String text, List<Task> tasks) {
        String dateD = DukeUtils.getDate(text);
        Deadline deadline = new Deadline(DukeUtils.getBody(text), dateD);
        tasks.add(deadline);
        int countD = tasks.size();
        DukeUtils.printAction(deadline, countD);
    }
    public static void createTodo(String text, List<Task> tasks) {
        Todo todo = new Todo(text);
        tasks.add(todo);
        int countT = tasks.size();
        DukeUtils.printAction(todo, countT);
    }
    public static void createEvent(String text, List<Task> tasks) {
        String dateE = DukeUtils.getDate(text);
        Event event = new Event(DukeUtils.getBody(text), dateE);
        tasks.add(event);
        int countE = tasks.size();
        DukeUtils.printAction(event, countE);
    }
    public static void deleteTask(String text, List<Task> tasks){
        int taskno = Integer.parseInt(text.split(" ", 2)[1]) - 1;
        int countDel = tasks.size() - 1;
        DukeUtils.printDelete(tasks.get(taskno), countDel);
        tasks.remove(taskno);
    }

}
