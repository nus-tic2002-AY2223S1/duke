package nus.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate localDate;

    public Deadline(String userInput) {
        super(userInput);
        String description = this.getDescription(userInput);
        super.setDescription(description);
        this.localDate = processDate(userInput);
    }

    public LocalDate getDate(){
        return this.localDate;
    }

    @Override
    public String getTaskType(){
        return "D";
    }

    @Override
    public String getTaskDetails() {
        String date = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        date = "(by: " + date + ")";
        return date;
    }

    @Override
    public String getDescription(String userInput) {
        int end = userInput.indexOf("/by");
        return userInput.substring(0, end);
    }

    @Override
    public String getDateInStr(String userInput) {
        int start = userInput.indexOf("/by") + 4;
        if (userInput.indexOf("[T]") == -1 || userInput.indexOf("[F]") == -1) {
            return userInput.substring(start, userInput.length()).trim();
        } else {
            return userInput.substring(start, userInput.length()-3).trim();
        }
    }
}