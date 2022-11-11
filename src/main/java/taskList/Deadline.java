package tasklist;

import parser.Parser;

import java.time.LocalDate;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    // deadline string to print
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By: " + convertDateFormat(getDate()) + ")";
    }

    // convert String date to LocalDate format
    @Override
    public LocalDate getDate() {
        return Parser.StringToDate(this.by);
    }

    // convert LocalDate to ideal date format
    public String convertDateFormat(LocalDate by){
        return Parser.DateToString(by);
    }
}

