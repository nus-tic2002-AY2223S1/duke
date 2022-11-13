package commands;

import entity.CommandParser;
import entity.Event;
import exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command{

    private final String inputs;

    public EventCommand(String inputs) {
        this.inputs = inputs;
    }

    public void execute() {
        if (CommandParser.countCommandParts(inputs) < 2)
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");

        String inputBody = CommandParser.getCommandBody(inputs);
        String[] eventDesc = CommandParser.getEventDetails(inputBody);
        try {
            Event event = new Event(eventDesc[0], LocalDateTime.parse(eventDesc[1].trim(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            instance.addTask(event);
        } catch (DukeException | DateTimeParseException err) {
            throw new DukeException("☹ OOPS!!! Invalid date time format, please use yyyy-MM-dd hh:mm.");
        }
    }
}
