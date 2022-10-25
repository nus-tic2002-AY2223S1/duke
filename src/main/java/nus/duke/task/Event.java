package nus.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at; // /at a specific place
    protected LocalDate localDate; // /on a specific date

    public Event(String userInput) {
        super(userInput);
        String description = this.getDescription(userInput);
        super.setDescription(description);
        this.at = getVenue(userInput);
        this.localDate = this.processDate(userInput);
    }

    @Override
    public String getTaskType(){
        return "E";
    }

    @Override
    public String getTaskDetails(){
        String venue = "(Venue: " + this.at + ")";
        String date = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        date = "(Time: " + date + ")";
        return venue + date;
    }

    @Override
    public String getDescription(String userInput){
        int end = userInput.indexOf("/at");
        return userInput.substring(0, end);
    }

    @Override
    public String getDateInStr(String userInput){
        int start = userInput.indexOf("/on") + 4;
        if (userInput.indexOf("[T]") == -1 || userInput.indexOf("[F]") == -1){
            return userInput.substring(start, userInput.length()).trim();
        } else {
            return userInput.substring(start, userInput.length()-3).trim();
        }
    }

    public String getVenue(String userInput){
        int start = userInput.indexOf("/at") + 4;
        int end = userInput.indexOf("/on");
        return userInput.substring(start, end).trim();
    }
}
