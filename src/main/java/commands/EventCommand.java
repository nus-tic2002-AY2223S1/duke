package commands;

import entity.CommandParser;
import entity.Event;
import exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        Event event = new Event(eventDesc[0], LocalDateTime.parse(eventDesc[1].trim(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        instance.addTask(event);
    }
}
