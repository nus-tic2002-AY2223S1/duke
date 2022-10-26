package Logic;

import Common.TaskType;
import CustomException.UnsupportedTaskType;
import Tasks.*;
import Tasks.TaskInterface;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.ArrayList;

public class TaskManager implements BotCallback {
    ArrayList<TaskInterface> tasks = new ArrayList<>();
    BotUseCase router;

    public TaskManager(BotUseCase router) {
        this.router = router;
        this.router.delegate = this;
        router.loadData();
    }

    public void addNewTask(String text) {
        try {
            if(text.indexOf(" ") == -1) {
                router.invalidFormat(text);
                return;
            }
            String type = text.substring(0, text.indexOf(' '));
            String task = text.substring(text.indexOf(" "));
            if(task.isBlank() || task.isEmpty()) {
                router.unsupportedFormat(text);
                return;
            }
            TaskType taskType = TaskType.getType(type);
            switch (taskType) {
                case TODO:
                    Todo todo = new Todo(task);
                    tasks.add(todo);
                    router.addSuccess(todo);
                    break;
                case EVENT:
                    try {
                        String[] events = task.split("/at ");
                        Event event = new Event(events[0], events[1]);
                        tasks.add(event);
                        router.addSuccess(event);
                    } catch (IndexOutOfBoundsException e) {
                        router.customError("Please add when will it finished after /at");

                    }
                    break;
                case DEADLINE:
                    try {
                        String[] deadlines = task.split("/by ");
                        Deadline deadline = new Deadline(deadlines[0], LocalDateTime.parse(deadlines[1]));
                        tasks.add(deadline);
                        router.addSuccess(deadline);
                    } catch (IndexOutOfBoundsException e) {
                        router.customError("Please add yyyy-mm-ddThh:mm format after /by. e.g correct command is deadline return book /by 2022-08-01T20:00");
                    }
                    break;
            }
        } catch (UnsupportedTaskType e) {
            router.unsupportedTaskType();
        } catch (IndexOutOfBoundsException e) {
            router.unsupportedFormat(text);
        } catch (DateTimeParseException e) {
            router.customError("please use yyyy-mm-ddThh:mm format. e.g 2022-08-01T20:00");
        } catch (UnsupportedTemporalTypeException e) {
            router.customError("please use yyyy-mm-ddThh:mm format. e.g 2022-08-01T20:00");

        }
    }

    public void showList() {
        ArrayList<String> list = new ArrayList<>();
        int i = 1;
        for(TaskInterface task: tasks) {
            list.add(task.getString());
        }
        router.showList(tasks);
    }

    public void markTask(int index, boolean mark) {
        try {
            if(tasks.get(index).isDone() == mark) {
                router.markFailed(tasks.get(index));
            } else {
                tasks.get(index).markTask(mark);
                router.markSuccess(tasks.get(index));
            }
        } catch (IndexOutOfBoundsException e) {
            router.indexOutOFBound();
        }

    }

    public void remove(int index) {
        try {
            TaskInterface task = tasks.get(index);
            tasks.remove(index);
            router.deleteSuccess(task);
        } catch (IndexOutOfBoundsException e)  {
            router.indexOutOFBound();
        }
    }

    public void goodbye() {
        router.goodbye();
    }

    @Override
    public void loadData(ArrayList<TaskInterface> tasks) {
        this.tasks = tasks;
    }

    @Override
    public int getSize() {
        return tasks.size();
    }


}

