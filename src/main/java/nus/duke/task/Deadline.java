package nus.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate localDate;
    public Deadline(String userInput) {
        super(userInput);
        String description = super.getDescription(userInput);
        super.setDescription(description);
        this.localDate = processDate(userInput);
    }
    @Override
    public String getTaskType(){
        return "D";
    }

    @Override
    public String getTaskDetails(){
        String date = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        date = "(by: " + date + ")";
        return date;
    }
}