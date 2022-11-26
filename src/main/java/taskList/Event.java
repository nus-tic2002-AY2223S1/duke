package tasklist;

import parser.Parser;

import java.time.LocalDate;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    // event string to print
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (At: " + convertDateFormat(getDate()) + ")";
    }

    public String toOutput() {
        return "E" + super.toOutput() + ";" + convertDateFormat(getDate());
    }

    // convert String date to LocalDate format
    @Override
    public LocalDate getDate() {
        return Parser.stringToDate(this.at);
    }

    // convert LocalDate to ideal date format
    public String convertDateFormat(LocalDate at) {
        return Parser.dateToString(at);
    }


}
