package Logic;

import Common.TaskType;
import CustomException.UnsupportedTaskType;
import Tasks.*;
import Tasks.TaskInterface;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.ArrayList;
/**
 * The logic of the bot
 */
public class TaskManager implements BotCallback {
    ArrayList<TaskInterface> tasks = new ArrayList<>();
    BotUseCase router;

    public TaskManager(BotUseCase router) {
        this.router = router;
        this.router.delegate = this;
        router.loadActiveFile();
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
                        String work = events[0];
                        String[] dates = events[1].split("-");
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                            LocalDateTime startdate = LocalDateTime.parse(dates[0], formatter);
                            String enddatestring = startdate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd ")) + dates[1];
                            LocalDateTime enddate = LocalDateTime.parse(enddatestring, formatter);
                            Event event = new Event(work, startdate, enddate);
                            tasks.add(event);
                            router.addSuccess(event);
                        } catch (Exception e) {
                            router.customError("please use YYYY/MM/dd HH:mm-HH:mm format. e.g 2022/08/01 20:00-21:00");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        router.customError("Please add when and until after /at e.g /at 2022/08/01 20:00-21:00");
                    }
                    break;
                case DEADLINE:
                    try {
                        String[] deadlines = task.split("/by ");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                        Deadline deadline = new Deadline(deadlines[0], LocalDateTime.parse(deadlines[1], formatter));
                        tasks.add(deadline);
                        router.addSuccess(deadline);
                    } catch (IndexOutOfBoundsException e) {
                        router.customError("Please add /by by yyyy/MM/dd HH:mm format. e.g deadline return book /by 2022/08/01 20:00");
                    }
                    break;
            }
        } catch (UnsupportedTaskType e) {
            router.unsupportedTaskType();
        } catch (IndexOutOfBoundsException e) {
            router.unsupportedFormat(text);
        } catch (DateTimeParseException e) {
            router.customError("please use YYYY/MM/dd HH:mm format. e.g 2022/08/01 20:00");
        } catch (UnsupportedTemporalTypeException e) {
            router.customError("please use YYYY/MM/dd HH:mm format. e.g 2022/08/01 20:00");

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

    public void showFiles() {
        router.showAllFiles();
    }

    public void setActiveFile(String alias) {
        router.setActiveFile(alias);
    }

    public void createNewFile(String alias) {
        router.addNewFile(alias);
    }

    public void getActiveFile() {
        router.getActiveFile();
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

