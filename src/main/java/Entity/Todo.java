package Entity;

import Entity.Task;

public class Todo extends Task {

    public Todo(String description){
        super(description);
    }

    @Override
    public void print(){
        String icon = String.format("\t [T][%s] ",this.getStatusIcon());
        System.out.println(icon + description );
    }

    @Override
    public String getDescription() {
        String description = String.format("\t [T][%s] %s ",this.getStatusIcon(),this.description);
        return description;
    }
}
