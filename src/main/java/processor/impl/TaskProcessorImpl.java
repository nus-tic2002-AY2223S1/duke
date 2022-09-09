package processor.impl;

import domain.task.Task;
import processor.TaskProcessor;

import java.util.List;

import static utils.Converter.getDeadlineFromUserInput;
import static utils.Converter.getEventFromUserInput;
import static utils.Converter.getTodoFromUserInput;
import static utils.Messages.addedTaskMsg;
import static utils.Messages.alreadyDoneMsg;
import static utils.Messages.alreadyNotDoneMsg;
import static utils.Messages.markDoneMsg;
import static utils.Messages.markUndoneMsg;
import static utils.Mouth.speak;
import static utils.TaskUtil.getTaskFromTaskList;

public class TaskProcessorImpl implements TaskProcessor {

    public void markTask(String userInput, List<Task> taskList) {
        getTaskFromTaskList(taskList, userInput).ifPresent(task -> {
            if (task.isDone()) {
                // if task is already done, scold the user
                speak(alreadyDoneMsg(task));
            } else {
                task.markDone();
                speak(markDoneMsg(task));
            }
        });
    }

    public void unmarkTask(String userInput, List<Task> taskList) {
        getTaskFromTaskList(taskList, userInput).ifPresent(task -> {
            if (!task.isDone()) {
                // if task is already not done, scold the user
                speak(alreadyNotDoneMsg(task));
            } else {
                task.markNotDone();
                speak(markUndoneMsg(task));
            }
        });
    }


    public void addTodo(String userInput, List<Task> taskList) {
        getTodoFromUserInput(userInput).ifPresent(todo -> addTask(todo, taskList));
    }

    public void addDeadline(String userInput, List<Task> taskList) {
        getDeadlineFromUserInput(userInput).ifPresent(deadline -> addTask(deadline, taskList));
    }

    public void addEvent(String userInput, List<Task> taskList) {
        getEventFromUserInput(userInput).ifPresent(event -> addTask(event, taskList));
    }

    private void addTask(Task task, List<Task> taskList) {
        taskList.add(task);
        speak(addedTaskMsg(task));
    }
}
