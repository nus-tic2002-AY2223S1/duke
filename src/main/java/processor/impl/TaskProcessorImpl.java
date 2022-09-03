package processor.impl;

import domain.task.Task;
import processor.TaskProcessor;

import java.util.List;

import static domain.Mouth.speak;
import static utils.Converter.getDeadlineFromUserInput;
import static utils.Converter.getEventFromUserInput;
import static utils.Converter.getTaskFromUserInput;
import static utils.Converter.getTodoFromUserInput;
import static utils.Messages.addedTaskMsg;
import static utils.Messages.alreadyDoneMsg;
import static utils.Messages.alreadyNotDoneMsg;
import static utils.Messages.markDoneMsg;
import static utils.Messages.markUndoneMsg;
import static utils.TaskUtil.getTaskFromTaskList;

public class TaskProcessorImpl implements TaskProcessor {

    public static void markTask(String userInput, List<Task> taskList) {
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

    public static void unmarkTask(String userInput, List<Task> taskList) {
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

    public static void addTask(String userInput, List<Task> taskList) {
        getTaskFromUserInput(userInput).ifPresent(task -> {
            taskList.add(task);
            speak(addedTaskMsg(task));
        });
    }

    public static void addTodo(String userInput, List<Task> taskList) {
        getTodoFromUserInput(userInput).ifPresent(todo -> {
            taskList.add(todo);
            speak(addedTaskMsg(todo));
        });
    }

    public static void addDeadline(String userInput, List<Task> taskList) {
        getDeadlineFromUserInput(userInput).ifPresent(deadline -> {
            taskList.add(deadline);
            speak(addedTaskMsg(deadline));
        });
    }

    public static void addEvent(String userInput, List<Task> taskList) {
        getEventFromUserInput(userInput).ifPresent(event -> {
            taskList.add(event);
            speak(addedTaskMsg(event));
        });
    }
}
