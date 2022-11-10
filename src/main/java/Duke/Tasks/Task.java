package Duke.Tasks;

import java.util.ArrayList;

/**
 * Represents a Task. Contains the data of all Duke.Tasks.Task such as Todo, Events and Deadlines.
 */

public class Task {

    public ArrayList<String> tagging = new ArrayList();
    protected String description;
    protected boolean isDone = false;


    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        String mark = this.getStatus() ? "[X]" : "[ ]";
        mark = mark.concat(" " + description + " ");
        mark = mark.concat(this.getTagging());
        return mark;
    }

    public String getDescriptionOnly() {
        return description;
    }

    public boolean getStatus(){
        return isDone;
    }

    private void setStatus(boolean b){
        isDone = b;
    }


    public void markTask(){
        this.setStatus(true);
    }
    public void unmarkTask(){
        this.setStatus(false);
    }

    public void addTagging(String tag){
        tagging.add(tag);
    }
    public String getTagging(){
        if(tagging.isEmpty()){ return ""; }

        String output = "";
        for (String t: tagging) {
            output = output.concat(t + " ");
        }
        return output;
    }
}