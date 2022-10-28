package catbot.task;

import catbot.CatBotException;
import catbot.DuplicatedTaskException;
import catbot.TaskType;
import catbot.utils.Utils;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private Set<String> uniques = new HashSet<>();

    public TaskList(){

    }

    public TaskList(ArrayList<Task> tasks) throws DuplicatedTaskException {
        for(Task t : tasks){
            addTask(t);
        }
    }

    public Integer getSize(){
        return tasks.size();
    }

    public void addTask(Task t) throws DuplicatedTaskException {
        if(checkIfTaskExists(t)){
            throw new DuplicatedTaskException("Cat detected a duplicated task and did not add this task" + System.lineSeparator() + "pls try again!");
        }
        tasks.add(t);
        uniques.add(getTaskHash(t));
    }

    private boolean checkIfTaskExists(Task t) {
        String hash = getTaskHash(t);
        if (uniques.contains(hash)) {
            return true;
        }
        return false;
    }

    public void deleteTask(int taskNum) {
        tasks.remove(taskNum);
        Task t = getTask(taskNum);
        String hash = getTaskHash(t);
        uniques.remove(hash);
    }

    public TaskList searchTask(String query){
        TaskList output = new TaskList();

        for(Task t : tasks){
            if(t.getTaskName().contains(query)){
                try{
                    output.addTask(t);
                } catch (DuplicatedTaskException dte){
                }
            }
        }

        return output;
    }

    public void markDone(Integer taskNum) {
        tasks.get(taskNum).setToDone();
    }

    public void unmarkDone(Integer taskNum) {
        tasks.get(taskNum).setToUndone();
    }

    public Task getTask(Integer taskNum){
        return tasks.get(taskNum);
    }

    public ArrayList<Task> getTasksList(){
        return tasks;
    }


    public String getTaskHash(Task t){
        String hash;

        try {
            if(t.getTaskType().equals(TaskType.DEADLINE)){
                Deadline d = (Deadline)t;

                TaskType type = d.getTaskType();
                String name = d.getTaskName();
                LocalDateTime by = d.getBy();

                hash = Utils.hashString(type+name+by);

            } else if (t.getTaskType().equals(TaskType.EVENT)) {
                Event e = (Event)t;

                TaskType type = e.getTaskType();
                String name = e.getTaskName();
                LocalDateTime from = e.getFromDateTime();
                LocalDateTime to = e.getToDateTime();

                hash = Utils.hashString(type+name+from+to);
            } else {
                ToDo td = (ToDo)t;

                TaskType type = td.getTaskType();
                String name = td.getTaskName();

                hash = Utils.hashString(type+name);
            }
            return hash;
        } catch (NoSuchAlgorithmException nsae) {
            return null;
        }
    }

    @Override
    public String toString() {
        String s ="";
        for(int i = 0; i < tasks.size(); i++){
            s += i + "." + tasks.get(i) + "\n";
        }
        return s;
    }
}
