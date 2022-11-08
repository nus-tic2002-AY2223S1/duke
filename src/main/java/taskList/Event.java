package taskList;

import parser.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {
    protected String at;
    protected LocalDate localDate;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + convertDateFormat(getDate()) + ")";
    }

//    public LocalDate thisDate(){return this.localDate;}
    @Override
    public LocalDate getDate() {
        return Parser.StringToDate(this.at);
    }
    public String convertDateFormat(LocalDate at){
        return Parser.DateToString(at);
    }


}
