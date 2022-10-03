package Entity;

import Entity.Task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void print(){
        System.out.println(getDescription());
    }

    @Override
    public String getDescription() {
        String description = String.format("\t [D][%s] %s (by: %s) ",this.getStatusIcon(),this.description,by);
        return description;
    }




}
