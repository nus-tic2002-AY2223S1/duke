package command;

import entities.*;
import ui.Ui;
import utils.DukeUtils;
import java.util.ArrayList;
import java.util.List;

public class Command {
    public static void createDeadline(String text, List<Task> tasks) {
        String dateD = DukeUtils.getDate(text);
        Deadline deadline = new Deadline(DukeUtils.getBody(text), dateD);
        tasks.add(deadline);
        int countD = tasks.size();
        DukeUtils.printAction(deadline, countD);
        Storage.save(tasks);
    }
    public static void createTodo(String text, List<Task> tasks){
        Todo todo = new Todo(text);
        tasks.add(todo);
        int countT = tasks.size();
        DukeUtils.printAction(todo, countT);
        Storage.save(tasks);
    }
    public static void createEvent(String text, List<Task> tasks) {
        String dateE = DukeUtils.getDate(text);
        Event event = new Event(DukeUtils.getBody(text), dateE);
        tasks.add(event);
        int countE = tasks.size();
        DukeUtils.printAction(event, countE);
        Storage.save(tasks);
    }
    public static void deleteTask(String text, List<Task> tasks){
        int taskno = Integer.parseInt(text.split(" ", 2)[1]) - 1;
        int countDel = tasks.size() - 1;
        DukeUtils.printDelete(tasks.get(taskno), countDel);
        tasks.remove(taskno);
        Storage.save(tasks);
    }


}
