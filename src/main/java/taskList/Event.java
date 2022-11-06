package taskList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at;
    protected LocalDate localDate;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public LocalDate thisDate(){return this.localDate;}

    public String formatDate(){
        String date = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return date;
    }


}
