package processor.impl;

import domain.Task;
import processor.TaskProcessor;

import java.util.List;

import static domain.Mouth.speak;
import static utils.Messages.addedTaskMsg;
import static utils.Messages.alreadyDoneMsg;
import static utils.Messages.alreadyNotDoneMsg;
import static utils.Messages.markDoneMsg;
import static utils.Messages.markUndoneMsg;
import static utils.TaskUtil.getTaskFromUserInput;

public class TaskProcessorImpl implements TaskProcessor {

    public static void markTask(String userInput, List<Task> taskList) {
        getTaskFromUserInput(taskList, userInput).ifPresent(task -> {
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
        getTaskFromUserInput(taskList, userInput).ifPresent(task -> {
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
        taskList.add(new Task(userInput));
        speak(addedTaskMsg(userInput));
    }
}
