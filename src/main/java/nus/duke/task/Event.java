package nus.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate localDate;
    //protected String at; // i.e. at a specific date/time

    public Event(String userInput) {
        super(userInput);
        //this.at = userInput;

        String description = super.getDescription(userInput);
        super.setDescription(description);
        this.localDate = processDate(userInput);
    }

    @Override
    public String getTaskType(){
        return "E";
    }

    @Override
    public String getTaskDetails(){
        String date = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        date = "(Time: " + date + ")";
        return date;
    }

    @Override
    public String getDateInStr(String userInput){
        int start = userInput.indexOf("/at") + 4;
        if (userInput.indexOf("[T]") == -1 || userInput.indexOf("[F]") == -1){
            return userInput.substring(start, userInput.length()).trim();
        } else {
            return userInput.substring(start, userInput.length()-3).trim();
        }
    }
}
