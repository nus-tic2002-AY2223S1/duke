package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected LocalDate byDate;
    public Deadline(String description, String by) {
        super(description);
        LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(by.trim());
            this.byDate = parsedDate;
        }catch( Exception e){
        }

        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    @Override
    public String toFile() { return("D | "+(super.isDone? 1:0)+" | "+super.description+" | "+dateToString()+"\n");}

    public String dateToString(){
        if (byDate == null){return by;}
        return byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}