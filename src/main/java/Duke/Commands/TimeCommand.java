package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Tasks.Deadlines;
import Duke.Tasks.Events;
import Duke.Tasks.Task;
import Duke.Tasks.TaskList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * To find out how much time is left till due date for a selected task
 */
public class TimeCommand extends Command {
    private final int input;

    /**
     *  Constructor of TimeCommand
     *
     * @param input is the task # selected to find out time left till due date
     */
    public TimeCommand(int input) {
        this.input = input;
    }

    /**
     *  execute of TimeCommand
     *
     * @param storage will store task list data to hard disk
     * @param taskList to retrieve task to mark as complete
     * @return a string to show user of the time left till due date for the selected task
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        if (taskList.getSize() < input){
            return "Task list does not have #" + input + ", please select a number within the task list";
        }
        Task currTask = taskList.getTask(input-1);
        SimpleDateFormat format = new SimpleDateFormat("d MMM yyyy HH:mm");
        String dueDate;
        String output = currTask.getDescription();

        if(currTask.getTaskType().equals("Todo")){
            return output.concat(" - Does not have a due date");
        }

        if(currTask.getTaskType().equals("Events")){
            dueDate = ((Events) currTask).getDueDate();
        } else {
            dueDate = ((Deadlines) currTask).getDueDate();
        }

        if(dueDate.equals("no due date")){
            return output.concat(" - Does not have a due date");
        }

        Date date1;
        try {
            date1 = format.parse(dueDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date date2 = new Date();
        long difference =  date1.getTime() - date2.getTime();

        if (difference < 1){
            return output.concat(" - Task is overdue!") ;
        }

        long diffMinutes = difference / (60 * 1000) % 60;
        long diffHours = difference / (60 * 60 * 1000) % 24;
        long diffDays = difference / (24 * 60 * 60 * 1000);

        return output.concat(" - Time left till due date: " + diffDays + " day[s], " + diffHours +" hour[s] and "+ diffMinutes + " minute[s]");
    }
}
