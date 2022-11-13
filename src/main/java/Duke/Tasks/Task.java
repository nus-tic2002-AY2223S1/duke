package Duke.Tasks;

import java.util.ArrayList;

/**
 * Represents a Task which inherits Todo, Events and Deadlines.
 */

public class Task {
    public ArrayList<String> tagging = new ArrayList();
    protected String description;
    protected String taskType;
    protected boolean isDone = false;

    /**
     *  Constructor of Task
     *
     * @param description to describe what needs to be done for the Task
     * @param taskType is the type of task like Todo, Events and Deadlines
     */
    public Task(String description, String taskType) {
        this.description = description;
        this.taskType = taskType;
    }

    /**
     * To retrieve the full description of a Task
     *
     * @return a String with the description of the Task with marking and tagging
     */
    public String getDescription() {
        String mark = this.getStatus() ? "[X]" : "[ ]";
        mark = mark.concat(" " + description + " ");
        mark = mark.concat(this.getTagging());
        return mark;
    }

    /**
     * To retrieve the description of a Task
     *
     * @return a String with the description of the Task
     */
    public String getDescriptionOnly() {
        return description;
    }

    /**
     * To retrieve the completion status of a Task
     *
     * @return a boolean which indicates the completion status of the task
     */
    public boolean getStatus(){
        return isDone;
    }

    /**
     * To set the completion status of a Task
     *
     * @param b is a boolean to indicate if the task is completed or not
     */
    private void setStatus(boolean b){
        isDone = b;
    }

    /**
     * To mark a task as completed
     */
    public void markTask(){
        this.setStatus(true);
    }

    /**
     * To mark a task as not completed
     */
    public void unmarkTask(){
        this.setStatus(false);
    }

    /**
     * To add a tagging to a task
     *
     * @param tag is the tagging to be added to a task
     */
    public void addTagging(String tag){
        tagging.add(tag);
    }

    /**
     * To get all taggings from a task
     *
     * @return a String which contains all tagging of a task
     */
    public String getTagging(){
        if(tagging.isEmpty()){ return ""; }

        String output = "";
        for (String t: tagging) {
            output = output.concat(t + " ");
        }
        return output;
    }

    /**
     * To get task type of a task
     *
     * @return a String which the task type of a task
     */
    public String getTaskType(){
        return taskType;
    }

}