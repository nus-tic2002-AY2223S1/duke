package processor.impl;

import domain.task.Task;
import exceptions.InvalidIntegerException;
import processor.TaskProcessor;

import java.util.List;

import static utils.Converter.getDeadlineFromUserInput;
import static utils.Converter.getEventFromUserInput;
import static utils.Converter.getTodoFromUserInput;
import static utils.ErrorMessages.INVALID_INTEGER_ERR_MSG;
import static utils.ErrorMessages.NUMBER_OUT_OF_RANGE_ERR_MSG;
import static utils.ErrorMessages.UNKNOWN_ERR_MSG;
import static utils.Messages.addedTaskMsg;
import static utils.Messages.alreadyDoneMsg;
import static utils.Messages.alreadyNotDoneMsg;
import static utils.Messages.markDoneMsg;
import static utils.Messages.markUndoneMsg;
import static utils.Messages.taskRemovedMsg;
import static utils.Mouth.speak;
import static utils.TaskUtil.getTaskFromTaskList;
import static utils.TaskUtil.getTaskIndexFromUserInput;

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

    public void deleteEvent(String userInput, List<Task> taskList) {
        try {
            int index = getTaskIndexFromUserInput(userInput);
            Task task = taskList.get(index);
            taskList.remove(index);
            speak(taskRemovedMsg(task));
        } catch (IndexOutOfBoundsException e) {
            speak(NUMBER_OUT_OF_RANGE_ERR_MSG);
        } catch (InvalidIntegerException e) {
            speak(INVALID_INTEGER_ERR_MSG);
        } catch (Exception e) {
            speak(UNKNOWN_ERR_MSG);
        }
    }

    private void addTask(Task task, List<Task> taskList) {
        taskList.add(task);
        speak(addedTaskMsg(task));
    }
}
