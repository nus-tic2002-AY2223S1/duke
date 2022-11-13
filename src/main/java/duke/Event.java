package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected String at;
    protected LocalDate atDate;
    public Event(String description,String at) {
        super(description);
        assert !this.description.equals("");
        LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(at.trim());
            this.atDate = parsedDate;
        }catch( Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        assert !at.equals("");
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateToString() + ")";
    }
    @Override
    public String toFile() { return("E | "+(super.isDone? 1:0)+" | "+super.description+" | "+dateSaveFormat()+"\n");}
    public String dateToString(){
        if (atDate == null){return at;}
        return atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String dateSaveFormat(){
        if (atDate == null){return at;}
        return atDate.toString();
    }
}
