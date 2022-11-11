package task;



import exceptions.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


public class TaskList {



    public ArrayList<Task> tasks=new ArrayList<>();

    public TaskList() {
        this.tasks = new ArrayList<>();

    }

    public TaskList(File f) throws FileNotFoundException  {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            read(s.nextLine());


        }
    }

    public ArrayList<Task> read(String s){
        String [] inputArray=s.split(" \\| ");

//        if (inputArray[1].equals("1")) {
//            status = "X";
//        }

        if (inputArray[0].equals("T")) {

              tasks.add(new Todo(inputArray[2]));
              if(inputArray[1].equals("1")) {
                  tasks.get(tasks.size() - 1).mark();

              }
        } else if (inputArray[0].equals("E")) {
            tasks.add(new Event(inputArray[2],inputArray[3]));
            if(inputArray[1].equals("1")) {
                tasks.get(tasks.size() - 1).mark();
            }
        }else{
            tasks.add(new Deadline(inputArray[2],inputArray[3]));
            if(inputArray[1].equals("1")) {
                tasks.get(tasks.size() - 1).mark();
            }
        }





        return tasks;


    }
    public void addTask(Task task){
        tasks.add(task);

    }
    public void removeTask(int task){
        tasks.remove(task);

    }

}
