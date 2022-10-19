package Domain.Aggregates.Tracker;

import Application.Helpers.CommonHelper;
import Application.Helpers.MessageConstants;
import Domain.Exceptions.*;
import Domain.Repositories.ITaskRepository;
import Domain.Repositories.TaskRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Tracker {
    public ArrayList<Task> tasks;
    private final ITaskRepository _taskRepository;

    public Tracker() throws DukeFileException, DukeArgumentException {
        _taskRepository = new TaskRepository();
        this.tasks = _taskRepository.convertToTaskList();
    }

    private void printList(ArrayList<Task> t){
        for(int i = 0; i < t.size(); i++){
            t.get(i).printItem();
        }
    }

    private void printTask(Task task){
        task.printItem();
        CommonHelper.printMessage(String.format(MessageConstants.SUM_TASK, tasks.size()));
    }

    public void showList(){
        if(tasks.size() > 0) {
            CommonHelper.printMessage(MessageConstants.LIST_TASK);
            printList(tasks);
        } else {
            CommonHelper.printMessage(MessageConstants.NO_TASK);
        }
    }

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
