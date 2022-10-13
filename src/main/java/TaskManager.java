import Tasks.*;
import Tasks.TaskInterface;

import java.util.ArrayList;

public class TaskManager implements TaskInterface {
    ArrayList<TaskInterface> tasks = new ArrayList<>();

    public void addNewTask(TaskType type, String work) {
        switch (type) {
            case TODO:
                Todo todo = new Todo(work);
                tasks.add(todo);
                break;
            case EVENT:
                Event event = new Event(work);
                tasks.add(event);
                break;
            case DEADLINE:
                Deadline deadline = new Deadline(work);
                tasks.add(deadline);
                break;
        }
    }
    @Override
    public String getString() {
        return tasks.get(tasks.size() - 1).getString();
    }

    public ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<>();
        int i = 1;
        for(TaskInterface task: tasks) {
            list.add(task.getString());
        }
        return list;
    }

    public boolean isDone() {
        return tasks.get(0).isDone();
    }


    public void markTask(boolean mark){
        tasks.get(0).markTask(mark);
    }

    public boolean markTask(int index, boolean mark) {
        if(tasks.get(index).isDone() == mark) {
            return false;
        } else {
            tasks.get(index).markTask(mark);
            return true;
        }
    }

    public String getTask(int index) {
        return tasks.get(index).getString();
    }

    public String remove(int index) {
        TaskInterface task = tasks.get(index);
        tasks.remove(index);
        return task.getString();
    }

    public enum TaskType {
        DEADLINE, EVENT, TODO;

        public static TaskType getType(String type) throws UnsupportedTaskType {
            if(type.equalsIgnoreCase("deadline")) {
                return DEADLINE;
            } else if (type.equalsIgnoreCase("event")) {
                return EVENT;
            } else if (type.equalsIgnoreCase("todo")) {
                return TODO;
            } else {
                throw new UnsupportedTaskType();
            }
        }
    }
}

