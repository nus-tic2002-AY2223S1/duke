package Logic;

import Common.TaskType;
import CustomException.UnsupportedTaskType;
import Tasks.*;
import Tasks.TaskInterface;
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
            String task = text.substring(0, text.indexOf(' '));
            String work = text.substring(text.indexOf(" "));
            System.out.println(work);
            if(work.isBlank() || work.isEmpty()) {
                router.unsupportedFormat(text);
                return;
            }
            TaskType type= TaskType.getType(task);
            switch (type) {
                case TODO:
                    Todo todo = new Todo(work);
                    tasks.add(todo);
                    router.addSuccess(todo);
                    break;
                case EVENT:
                    Event event = new Event(work);
                    tasks.add(event);
                    router.addSuccess(event);
                    break;
                case DEADLINE:
                    Deadline deadline = new Deadline(work);
                    tasks.add(deadline);
                    router.addSuccess(deadline);
                    break;
            }
        } catch (UnsupportedTaskType e) {
            router.unsupportedTaskType();
        } catch (IndexOutOfBoundsException e) {
            router.unsupportedFormat(text);
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

