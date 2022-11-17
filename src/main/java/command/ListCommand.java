package command;

import parser.Parser;
import task.Task;
import ui.Ui;
import exceptions.DukeException;
import storage.Storage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import task.TaskList;

import java.util.Collections;


public class ListCommand extends Command{
    /**
     * Add display task
     */
    public void execute(TaskList tasks, Ui ui, Storage storage)throws DukeException {


        sorting(tasks);
        sortByDate(tasks);
        for(int i=0;i<tasks.tasks.size();i++){
            System.out.println((i+1)+"."+tasks.tasks.get(i).toString());

        }

    }
    /**
     * Sort task according to Event and deadline, follow by todo
     */
    public void sorting(TaskList tasks){
        String sLeft,sRight;
        int right=tasks.tasks.size()-1;
        Task temp;
        for(int left=0;left<tasks.tasks.size();left++){
            //System.out.println((i+1)+"."+tasks.tasks.get(i).toString());
            sLeft=tasks.tasks.get(left).toString();
            sRight=tasks.tasks.get(right).toString();
            if(sLeft.contains("[T]") && !sRight.contains("[T]")){
                Collections.swap(tasks.tasks,right,left);
                right--;

            }
            if(sRight.contains("[T]")){
                right--;

            }
            if(left>=right){
                break;
            }
        }


    }
    /**
     * Sort task according to date
     */

    public void sortByDate(TaskList tasks){
        int count=0;
        String s;
        for(int i=0;i<tasks.tasks.size();i++){
            s=tasks.tasks.get(i).toString();
            if(s.contains("[D]")|| s.contains("E")){
                count++;
            }

        }
        for(int i=1;i<count;i++){
            LocalDateTime key= Parser.dataConvert(tasks.tasks.get(i).getDateTime());
            int j=i-1;
            while(j>=0 && Parser.dataConvert(tasks.tasks.get(j).getDateTime()).isAfter(key)){
                Collections.swap(tasks.tasks,j+1,j);
                j--;
            }
        }



    }
}
