package domain.aggregates.tracker;

import application.helpers.CommonHelper;
import application.helpers.MessageConstants;
import domain.exceptions.DukeExistedException;
import domain.exceptions.DukeFileException;
import domain.exceptions.DukeNotFoundException;
import domain.exceptions.DukeValidationException;
import domain.exceptions.DukeArgumentException;
import domain.repositories.ITaskRepository;
import domain.repositories.TaskRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Tracker {
    /**
     * Properties
     */
    public ArrayList<Task> tasks;
    private final ITaskRepository _taskRepository;

    /**
     * Initialises Tracker with new Task Repository and reads tasks from local file to tasks.
     *
     * @throws DukeFileException if unable to retrieve or create file.
     * @throws DukeValidationException if required task properties are empty populating tasks.
     */
    public Tracker() throws DukeFileException, DukeArgumentException {
        _taskRepository = new TaskRepository();
        this.tasks = _taskRepository.convertToTaskList();
    }

    /**
     * Iterates each task in tasks and calls printItem().
     *
     * @param taskList ArrayList<Task>.
     */
    private void printList(ArrayList<Task> taskList){
        for(int i = 0; i < taskList.size(); i++){
            taskList.get(i).printItem();
        }
    }

    /**
     * Calls task's printItem() and displays total number of tasks in tracker.
     *
     * @param task Task.
     */
    private void printTask(Task task){
        task.printItem();
        CommonHelper.printMessage(String.format(MessageConstants.SUM_TASK, tasks.size()));
    }

    /**
     * Displays all the tasks available in tracker.
     * If no task, to show no task message instead.
     */
    public void showList(){
        if(tasks.size() > 0) {
            CommonHelper.printMessage(MessageConstants.LIST_TASK);
            printList(tasks);
        } else {
            CommonHelper.printMessage(MessageConstants.NO_TASK);
        }
    }

    /**
     * Prints searched tasks along with results count.
     * If no tasks, to show no results message instead.
     *
     * @param taskList ArrayList<Task>
     */
    private void printResults(ArrayList<Task> taskList){
        if(taskList.size() > 0) {
            CommonHelper.printMessage(String.format(MessageConstants.FILTER_RESULTS_FOUND, taskList.size()));
            printList(taskList);
        } else {
            CommonHelper.printMessage(MessageConstants.NO_RESULTS_FOUND);
        }
    }

    /**
     * Validates task against existing tasks to make sure no duplicate records are added. Sets Task ID manually by incrementing from last available ID. Adds task and prints task.
     *
     * @param task Task.
     * @return boolean.
     * @throws DukeExistedException if adds task that already exists.
     */
    public boolean hasItemAdded(Task task) throws DukeExistedException {
        _taskRepository.validateTask(tasks, task);
        task.setId(_taskRepository.getLastId(tasks) + 1);
        if(tasks.add(task)) {
            CommonHelper.printMessage(MessageConstants.ADD_TASK);
            printTask(task);
            return true;
        } else {
            CommonHelper.printMessage(MessageConstants.GENERAL_ERROR);
            return false;
        }
    }

    /**
     * Validates task against existing tasks to make sure it exists. Sets the IsDone flag accordingly, Displays respective message for mark/unmark and prints task.
     *
     * @param id Integer.
     * @param isDone boolean.
     * @return boolean.
     * @throws DukeNotFoundException if modifies a task that does not exist.
     * @throws DukeArgumentException if invalid arguments passed.
     */
    public boolean hasItemStateUpdated(int id, boolean isDone) throws DukeArgumentException, DukeNotFoundException {
        Task task = _taskRepository.validateTask(tasks, id);
        if(task != null) {
            task.setIsDone(isDone);
            if (isDone) {
                CommonHelper.printMessage(MessageConstants.MARK_TASK);
            } else {
                CommonHelper.printMessage(MessageConstants.UNMARK_TASK);
            }
            task.printItem();
            return true;
        }
        return false;
    }

    /**
     * Validates task against existing tasks to make sure it exists. Deletes task and prints it.
     * If encountered an error while removing, print general error message.
     *
     * @param id Integer.
     * @return boolean.
     * @throws DukeNotFoundException if modifies a task that does not exist.
     * @throws DukeArgumentException if invalid arguments passed.
     */
    public boolean hasItemDeleted(int id) throws DukeArgumentException, DukeNotFoundException {
        Task task = _taskRepository.validateTask(tasks, id);
        if(task != null) {
            if (tasks.remove(task)) {
                CommonHelper.printMessage(MessageConstants.DELETE_TASK);
                printTask(task);
                return true;
            } else {
                CommonHelper.printMessage(MessageConstants.GENERAL_ERROR);
                return false;
            }
        }
        return false;
    }

    /**
     * Finds tasks that is within mandatory start and optional end date range.
     * If no results, print no results message.
     *
     * @param start LocalDate.
     * @param end LocalDate.
     */
    public void filterByDates(LocalDate start, LocalDate end){
        ArrayList<Task> results = this.tasks.stream().filter(x -> x.compare(start, end)).collect(Collectors.toCollection(ArrayList<Task>::new));
        printResults(results);
    }

    /**
     * Validates task against existing tasks to make sure it exists. Snoozes task and prints it.
     * If encountered an error while removing, print general error message.
     *
     * @param id Integer.
     * @param newDateTime String.
     * @param isNewDateTimeSpecified boolean.
     * @return boolean.
     * @throws DukeNotFoundException if modifies a task that does not exist.
     * @throws DukeValidationException if new date time is empty.
     * @throws DukeArgumentException if invalid date string argument passed.
     */
    public boolean hasItemSnoozed(int id, String newDateTime, boolean isNewDateTimeSpecified) throws DukeValidationException, DukeArgumentException, DukeNotFoundException, DukeExistedException {
        Task task = _taskRepository.validateTask(tasks, id);
        if(task != null){
            if(CommonHelper.isEmptyOrNull(newDateTime) && !isNewDateTimeSpecified) {
                task.postpone(newDateTime, false);
                CommonHelper.printMessage(MessageConstants.DEFAULT_SNOOZE_TASK);
            } else {
                if(tasks.stream().filter(x -> x.find(newDateTime) && x.getName().equals(task.getName()) && x.getId() != id).count() > 0)
                    throw new DukeExistedException(MessageConstants.TASK_EXISTED_ERROR);
                else{
                    task.postpone(newDateTime, isNewDateTimeSpecified);
                    CommonHelper.printMessage(MessageConstants.SNOOZE_TASK);
                }
            }
            printTask(task);
            return true;
        }
        return false;
    }

    /**
     * Finds tasks that contains keyword.
     * If no results, print no results message.
     *
     * @param keyword String.
     * @throws DukeValidationException if keyword is empty.
     */
    public void find(String keyword) throws DukeValidationException {
        if(CommonHelper.isEmptyOrNull(keyword)) {
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "keyword"));
        }
        ArrayList<Task> results = this.tasks.stream().filter(x -> x.find(keyword)).collect(Collectors.toCollection(ArrayList<Task>::new));
        printResults(results);
    }
}
