package catbot.command;

import catbot.Storage;
import catbot.TaskType;
import catbot.task.Deadline;
import catbot.task.Event;
import catbot.task.Task;
import catbot.task.TaskList;
import catbot.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class StatisticsCommand extends Command {
    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        int numOfOverdueTasks = 0;
        int numOfTasksLeftForToday = 0;
        int numOfTasksLeftForThisWeek = 0;

        ArrayList<Task> arrOfTasks = t.getTasksList();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endOfToday = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(),23,59);
        LocalDateTime OneWeekFromNow = now.plus(1, ChronoUnit.WEEKS);
        LocalDateTime currentTaskldt;

        for(Task task: arrOfTasks){
            if(task.getTaskType() == TaskType.DEADLINE){
                Deadline d = (Deadline)task;
                currentTaskldt = d.getBy();
            } else if(task.getTaskType() == TaskType.EVENT){
                Event e = (Event)task;
                currentTaskldt = e.getFromDateTime();
            } else {
                continue;
            }

            if(task.getIsDone() == true){
                continue;
            }

            if(currentTaskldt.isBefore(now)){
                numOfOverdueTasks++;
            }

            if(currentTaskldt.isBefore(endOfToday) && currentTaskldt.isAfter(now)){
                numOfTasksLeftForToday++;
            }

            if(currentTaskldt.isBefore(OneWeekFromNow) && currentTaskldt.isAfter(now)){
                numOfTasksLeftForThisWeek++;
            }
        }
        ui.showMessage("You have " + numOfOverdueTasks + " overdue tasks");
        ui.showMessage("You have " + numOfTasksLeftForToday + " unfinished tasks left for today");
        ui.showMessage("You have " + numOfTasksLeftForThisWeek + " upcoming tasks in the next 7 days");
    }
}
