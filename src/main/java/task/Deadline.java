package task;



public class Deadline extends Task {

    protected String by;

    /**
     * Deadline task constructor
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    /**
     * simplify to store in text file
     */
    @Override
    public String toOutput() {
        String statusIndex="0";
        if (getStatusIcon().equals("X")){
            statusIndex="1";
        }
        return "D | "+statusIndex+" | "+super.toOutput()+" | "+by;
    }

    /**
     * For output when list is called
     */
    public String toString() {
        return "[D]"+super.toString()+" (by: "+by+")";
    }
}