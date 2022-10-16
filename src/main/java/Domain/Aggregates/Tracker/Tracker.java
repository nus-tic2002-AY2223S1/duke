package Domain.Aggregates.Tracker;

import Application.Helpers.CommonHelper;
import Application.Helpers.MessageConstants;
import Domain.Exceptions.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Tracker {
    public ArrayList<Task> tasks;
    public File file;
    private static String FILE_PATH = "data/duke.txt";
    private static String HEADER = "ID | Type | Is Done | Name | Remarks\n";

    public Tracker() {

        tasks = new ArrayList<>(100);

            file = new File(FILE_PATH);
            file.setWritable(true);
            file.setReadable(true);
            if(!file.exists()) {
                try {
                    file.createNewFile();
                    writeToFile(HEADER);
                } catch (IOException ex){
                    new DukeFileException(MessageConstants.TASK_GET_ERROR);
                } catch (DukeFileException ex){

                }
            }
            else
                populateTasks();

    }

    private void printList(){
        for(int i = 0; i < tasks.size(); i++){
            tasks.get(i).printItem();
        }
    }

    private Optional<Task> getItem(int n){
        return tasks.stream().filter(x -> x.getId() == n).findFirst();
    }

    private Optional<Task> getItem(Task task){
        return tasks.stream().filter(x -> x.equals(task)).findFirst();
    }

    private Task validateTask(int n) throws DukeNotFoundException, DukeValidationException {
        if(n < 0)
            throw new DukeValidationException(String.format(MessageConstants.TASK_VALIDATION_EMPTY_ERROR, "Id"));
        else if(n == 0 || n > getLastId())
            throw new DukeValidationException(MessageConstants.TASK_VALIDATION_SIZE_ERROR);
        Optional<Task> task = getItem(n);
        if(task.isEmpty())
            throw new DukeNotFoundException(MessageConstants.TASK_NOT_FOUND_ERROR);
        return task.get();
    }

    private void validateTask(Task task) throws DukeExistedException {
        if(!getItem(task).isEmpty())
            throw new DukeExistedException(MessageConstants.TASK_EXISTED_ERROR);
    }

    private void printTask(Task task){
        task.printItem();
        CommonHelper.printMessage(String.format(MessageConstants.SUM_TASK, tasks.size()));
    }

    private void populateTasks() {
        try {
            Scanner scnr = new Scanner(new FileReader(FILE_PATH));
            String[] str;
            scnr.nextLine();
            while (scnr.hasNext()) {
                str = scnr.nextLine().split(" \\| ");
                Task task = null;
                switch (TaskType.valueOf(str[1])) {
                    case T:
                        task = new Todo(Integer.valueOf(str[0]), str[3], Boolean.valueOf(str[2]));
                        break;
                    case E:
                        task = new Event(Integer.valueOf(str[0]), str[3], str[4], Boolean.valueOf(str[2]));
                        break;
                    case D:
                        task = new Deadline(Integer.valueOf(str[0]), str[3], str[4], Boolean.valueOf(str[2]));
                        break;
                }
                tasks.add(task);
            }
            scnr.close();
        } catch (IOException ex){
            new DukeFileException(MessageConstants.TASK_GET_ERROR);
        }
    }

    private void writeToFile(String row) throws DukeFileException {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(row + "\n");
            writer.close();
        } catch (IOException ex){
            throw new DukeFileException(MessageConstants.TASK_SAVE_ERROR);
        }
    }

    private void overrideFile() throws DukeFileException {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            writer.append(HEADER);
            for (Task task : tasks) {
                writer.append(task.toString() + "\n");
            }
            writer.flush();
        } catch (IOException ex){
            throw new DukeFileException(MessageConstants.TASK_SAVE_ERROR);
        }
    }

    private int getLastId(){
        if(tasks.size() == 0)
            return 0;
        return tasks.get(tasks.size() - 1).getId();
    }

    public void showList(){
        if(tasks.size() > 0) {
            CommonHelper.printMessage(MessageConstants.LIST_TASK);
            printList();
        } else {
            CommonHelper.printMessage(MessageConstants.NO_TASK);
        }
    }

    public void addItem(Task task) throws DukeExistedException, DukeFileException {
        validateTask(task);
        if(tasks.add(task)) {
            writeToFile(task.toString());
            CommonHelper.printMessage(MessageConstants.ADD_TASK);
            printTask(task);
        } else {
            CommonHelper.printMessage(MessageConstants.GENERAL_ERROR);
        }
    }


    public void updateItem(int n, boolean isDone) throws DukeValidationException, DukeNotFoundException, DukeFileException {
        Task task = validateTask(n);
        task.setIsDone(isDone);
        overrideFile();
        if (isDone) {
            CommonHelper.printMessage(MessageConstants.MARK_TASK);
        } else {
            CommonHelper.printMessage(MessageConstants.UNMARK_TASK);
        }
        task.printItem();
    }

    public void deleteItem(int n) throws DukeValidationException, DukeNotFoundException, DukeFileException {
        Task task = validateTask(n);
        if(tasks.remove(task)) {
            overrideFile();
            CommonHelper.printMessage(MessageConstants.DELETE_TASK);
            printTask(task);
        } else {
            CommonHelper.printMessage(MessageConstants.GENERAL_ERROR);
        }
    }


    public int getNewId(){
        return getLastId() + 1;
    }
}
