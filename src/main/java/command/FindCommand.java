package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{
    String search;

    /**
     * Constructor for FindCommand
     */
    public FindCommand(String search){
        this.search=search;

    }

    /**
     * Find item stated
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String s;
        ArrayList <Integer> t=new ArrayList<>();
        for(int i=0;i< tasks.tasks.size();i++){

            s=tasks.tasks.get(i).toString();

            if(s.contains(search)){
                t.add(i);
            }

        }

        for(int j=0;j<t.size();j++){
            System.out.print(Integer.toString(j+1)+".");
            System.out.println(tasks.tasks.get(t.get(j)));

        }



    }


}
