package domain.aggregates.tracker;

import application.helpers.CommonHelper;
import application.helpers.MessageConstants;
import domain.exceptions.*;
import domain.repositories.ITaskRepository;
import domain.repositories.TaskRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Tracker {
    public ArrayList<Task> tasks;
    private final ITaskRepository _taskRepository;

    /**
     * Tracker has Tasks
     * Tracker default constructor
     * Requires TaskRepository class
     * Populate tasks from .txt file
     */
    public Tracker() throws DukeFileException, DukeArgumentException {
        _taskRepository = new TaskRepository();
        this.tasks = _taskRepository.convertToTaskList();
    }

    /**
     * Iterate each task in tasks and call the printItem() method
     */
    private void printList(ArrayList<Task> t){
        for(int i = 0; i < t.size(); i++){
            t.get(i).printItem();
        }
    }

    /**
     * Call task's printItem() method
     * Display total number of tasks in tracker
     */
    private void printTask(Task task){
        task.printItem();
        CommonHelper.printMessage(String.format(MessageConstants.SUM_TASK, tasks.size()));
    }

    /**
     * Display all the tasks available in tracker
     * If no task, to show NO_TASK message
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
     * Add task to tracker - returns boolean (Success: True, Error: False)
     * Validate task against existing tasks to make sure no duplicate records are added
     * Set Task ID manually by incrementing from last available ID
     * Add task and print it
     * If encountered an error while adding, print GENERAL_ERROR message
     */
    public boolean addItem(Task task) throws DukeExistedException {
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
     * Update task in tracker - returns boolean (Success: True, Error: False)
     * Validate task against existing tasks to make sure it exists
     * Set the IsDone flag accordingly
     * Display respective message (MARK or UNMARK)
     */
    public boolean updateItem(int n, boolean isDone) throws DukeValidationException, DukeNotFoundException {
        Task task = _taskRepository.validateTask(tasks, n);
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
     * Delete item from tracker -return boolean (Success: True, Error: False)
     * Validate task against existing tasks to make sure it exists
     * Remove and print item
     * If encountered an error while removing, print GENERAL_ERROR message
     */
    public boolean deleteItem(int n) throws DukeValidationException, DukeNotFoundException {
        Task task = _taskRepository.validateTask(tasks, n);
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
     * Find tasks that is within the start and end datetime range
     * Start is mandatory
     * End is optional
     * Print result list
     * If no results, print NO_RESULTS_FOUND message
     */
    public void filterByDates(LocalDate start, LocalDate end){
        ArrayList<Task> filtered = this.tasks.stream().filter(x -> x.compare(start, end)).collect(Collectors.toCollection(ArrayList<Task>::new));
        if(filtered.size() > 0) {
            CommonHelper.printMessage(String.format(MessageConstants.FILTER_RESULTS_FOUND, filtered.size()));
            printList(filtered);
        }
        else
            CommonHelper.printMessage(MessageConstants.NO_RESULTS_FOUND);
    }
}
