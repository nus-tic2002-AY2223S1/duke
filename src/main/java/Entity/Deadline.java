package Entity;

import Entity.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    @Override
    public void print(){
        System.out.println(getDescription());
    }

    @Override
    public String toDisk() {
//        D | 0 | return book | June 6th
        return type + " | " + ((isDone) ? "1" : "0") + " | " + description + " | "+ this.by.format(DateTimeFormatter.ofPattern("E,MMM d"));
    }

    @Override
    public String getDescription() {
        String description = String.format("\t [D][%s] %s (by: %s) ",this.getStatusIcon(),this.description,this.by.format(DateTimeFormatter.ofPattern("E,MMM d")));
        return description;
    }




}
