package processor.impl;

import domain.TaskList;
import domain.task.Deadline;
import domain.task.Task;
import exceptions.InvalidIntegerException;
import processor.TaskProcessor;

import java.util.Optional;

import static utils.Converter.getDeadlineFromUserInput;
import static utils.Converter.getEventFromUserInput;
import static utils.Converter.getPostponeDeadlineFromUserInput;
import static utils.Converter.getTodoFromUserInput;
import static utils.ErrorMessages.INVALID_INTEGER_ERR_MSG;
import static utils.ErrorMessages.NUMBER_OUT_OF_RANGE_ERR_MSG;
import static utils.ErrorMessages.UNKNOWN_ERR_MSG;
import static utils.ErrorMessages.UPDATE_DEADLINE_ERR_MSG;
import static utils.ErrorMessages.UPDATE_DEADLINE_IS_NOT_DEADLINE_ERR_MSG;
import static utils.Messages.addedTaskMsg;
import static utils.Messages.alreadyDoneMsg;
import static utils.Messages.alreadyNotDoneMsg;
import static utils.Messages.markDoneMsg;
import static utils.Messages.markUndoneMsg;
import static utils.Messages.searchQueryMsg;
import static utils.Messages.taskRemovedMsg;
import static utils.Messages.updatedDeadlineDateMsg;
import static utils.Mouth.speak;
import static utils.TaskUtil.getTaskFromTaskList;
import static utils.TaskUtil.getTaskIndexFromUserInput;
import static utils.TaskUtil.removeFirstWord;

public class TaskProcessorImpl implements TaskProcessor {
    @Override
    public void markTask(String userInput, TaskList taskList) {
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
    @Override
    public void unmarkTask(String userInput, TaskList taskList) {
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

    @Override
    public void addTodo(String userInput, TaskList taskList) {
        getTodoFromUserInput(userInput).ifPresent(todo -> addTask(todo, taskList));
    }

    @Override
    public void addDeadline(String userInput, TaskList taskList) {
        getDeadlineFromUserInput(userInput).ifPresent(deadline -> addTask(deadline, taskList));
    }

    @Override
    public void addEvent(String userInput, TaskList taskList) {
        getEventFromUserInput(userInput).ifPresent(event -> addTask(event, taskList));
    }

    @Override
    public void deleteTask(String userInput, TaskList taskList) {
        try {
            int index = getTaskIndexFromUserInput(userInput);
            Task task = taskList.getTask(index);
            taskList.removeTask(index);
            speak(taskRemovedMsg(task));
        } catch (IndexOutOfBoundsException e) {
            speak(NUMBER_OUT_OF_RANGE_ERR_MSG);
        } catch (InvalidIntegerException e) {
            speak(INVALID_INTEGER_ERR_MSG);
        } catch (Exception e) {
            speak(UNKNOWN_ERR_MSG);
        }
    }

    @Override
    public void postponeDeadline(String userInput, TaskList taskList) {
        getPostponeDeadlineFromUserInput(userInput).ifPresent(dto -> {
            Optional<Deadline> deadline = taskList.postponeDeadline(dto.getIndex(), dto.getPostponeToDate());
            if (deadline.isPresent()) {
                speak(updatedDeadlineDateMsg(deadline.get()));
            } else {
                speak(UPDATE_DEADLINE_IS_NOT_DEADLINE_ERR_MSG);
            }
        });
    }

    @Override
    public void findTask(String userInput, TaskList taskList) {
        String query = removeFirstWord(userInput);
        speak(searchQueryMsg(), taskList.findTasks(query));
    }

    private void addTask(Task task, TaskList taskList) {
        taskList.addTask(task);
        speak(addedTaskMsg(task, taskList.getTaskCount()));
    }
}
