package service;

import constant.CommandEnum;
import constant.Constant;
import form.DeadlineForm;
import form.EventForm;
import form.Form;
import form.MarkingForm;
import form.TodoForm;
import exception.CommandArgsException;
import util.DataUtil;
import util.StringUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserManager {

    private ParserManager() {}

    /**
     * default parser to parse the string input
     */
    private static final DefaultCommandParser DEFAULT_COMMAND_PARSER = new DefaultCommandParser();

    /**
     * parser to parse the string input for `mark` operation
     */
    private static final MarkCommandParser MARK_COMMAND_PARSER = new MarkCommandParser();

    /**
     * parser to parse the string input for `unmark` operation
     */
    private static final UnmarkCommandParser UNMARK_COMMAND_PARSER = new UnmarkCommandParser();

    /**
     * parser to parse the string input for `todo` operation
     */
    private static final TodoCommandParser TODO_COMMAND_PARSER = new TodoCommandParser();

    /**
     * parser to parse the string input for `deadline` operation
     */
    private static final DeadlineCommandParser DEADLINE_COMMAND_PARSER = new DeadlineCommandParser();

    /**
     * parser to parse the string input for `event` operation
     */
    private static final EventCommandParser EVENT_COMMAND_PARSER = new EventCommandParser();

    public static Form parseForm(String input) {
        input = StringUtil.trim(input);
        String[] args = input.split(" ");
        Parser parser = getParserByCommand(args[0]);
        return parser.parseForm(input);
    }

    private static Parser getParserByCommand(String command) {
        CommandEnum commandEnum = CommandEnum.getCommandByName(command);
        switch (commandEnum) {
            case MARK_TASK:
                return MARK_COMMAND_PARSER;
            case UNMARK_TASK:
                return UNMARK_COMMAND_PARSER;
            case TODO:
                return TODO_COMMAND_PARSER;
            case DEADLINE:
                return DEADLINE_COMMAND_PARSER;
            case EVENT:
                return EVENT_COMMAND_PARSER;
            default:
                return DEFAULT_COMMAND_PARSER;
        }
    }

    private interface Parser {

        Form parseForm(String input);
    }

    private static class DefaultCommandParser implements Parser {

        @Override
        public Form parseForm(String input) {
            // single string input, input could command itself
            return new Form(input, input);
        }
    }

    private static class MarkCommandParser implements Parser {

        @Override
        public Form parseForm(String input) {
            String[] args = input.split(" ");
            if (args.length != 2) {
                throw new CommandArgsException("[mark] command has invalid arguments, it should be in `mark index` format");
            }

            // check index
            int index = DataUtil.toInteger(args[1]);
            if (index < 1) {
                throw new CommandArgsException("given index is invalid, it should be more than 0");
            }

            return new MarkingForm(input, CommandEnum.MARK_TASK.getName(), index);
        }
    }

    private static class UnmarkCommandParser implements Parser {

        @Override
        public Form parseForm(String input) {
            String[] args = input.split(" ");
            if (args.length != 2) {
                throw new CommandArgsException("[unmark] command has invalid arguments, it should be in `unmark index` format");
            }

            // check index
            int index = DataUtil.toInteger(args[1]);
            if (index < 1) {
                throw new CommandArgsException("given index is invalid, it should be more than 0");
            }

            return new MarkingForm(input, CommandEnum.UNMARK_TASK.getName(), index);
        }
    }

    private static class TodoCommandParser implements Parser {

        private static final Pattern todoClausePattern = Pattern.compile("todo (.*)");

        @Override
        public Form parseForm(String input) {
            Matcher matcher = todoClausePattern.matcher(input);
            if (!matcher.find()) {
                throw new CommandArgsException("[todo] command has invalid arguments, it should be in `todo description` format");
            }

            String description = StringUtil.trim(matcher.group(1));
            return new TodoForm(input, CommandEnum.TODO.getName(), description);
        }
    }

    private static class DeadlineCommandParser implements Parser {

        private static final Pattern deadlineClausePattern = Pattern.compile("deadline ([\\s\\w]*)/\\s*by (.*)");

        @Override
        public Form parseForm(String input) {
            Matcher matcher = deadlineClausePattern.matcher(input);
            if (!matcher.find()) {
                throw new CommandArgsException("[deadline] command has invalid arguments, it should be in `deadline description / by deadlineTime(yyyy-MM-dd HH:mm)` format");
            }

            String description = StringUtil.trim(matcher.group(1));
            String param = StringUtil.trim(matcher.group(2));
            DeadlineForm deadlineForm = new DeadlineForm(input, CommandEnum.DEADLINE.getName(), description);
            deadlineForm.setBy(parseDate(param));
            return deadlineForm;
        }

        private LocalDateTime parseDate(String param) {
            try {
                return LocalDateTime.parse(param, DateTimeFormatter.ofPattern(Constant.Time.INPUT_FORMAT));
            } catch (DateTimeParseException e) {
                throw new CommandArgsException("deadlineTime format invalid, it should be in (yyyy-MM-dd HH:mm) format");
            }
        }
    }

    private static class EventCommandParser implements Parser {

        private static final Pattern eventClausePattern = Pattern.compile("event ([\\s\\w]*)/\\s*at (.*)");

        @Override
        public Form parseForm(String input) {
            Matcher matcher = eventClausePattern.matcher(input);
            if (!matcher.find()) {
                throw new CommandArgsException("[event] command has invalid arguments, it should be in `event description / at startTime(yyyy-MM-dd HH:mm & endTime(yyyy-MM-dd HH:mm)` format");
            }

            String description = StringUtil.trim(matcher.group(1));
            String param = StringUtil.trim(matcher.group(2));
            EventForm eventForm = new EventForm(input, CommandEnum.EVENT.getName(), description);
            setEventDate(eventForm, param);
            return eventForm;
        }

        private void setEventDate(EventForm eventForm, String param) {
            String[] args = param.split("&");
            if (args.length != 2) {
                throw new CommandArgsException("event must specify the startTime and endTime");
            }

            String startTimeInput = StringUtil.trim(args[0]);
            String endTineInput = StringUtil.trim(args[1]);
            try {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constant.Time.INPUT_FORMAT);
                LocalDateTime startTime = LocalDateTime.parse(startTimeInput, dateTimeFormatter);
                LocalDateTime endTime = LocalDateTime.parse(endTineInput, dateTimeFormatter);
                // check time validity
                if (startTime.isAfter(endTime)) {
                    throw new CommandArgsException("input time value is incorrect, startTime cannot be greater than endTime");
                }
                eventForm.setStartTime(startTime);
                eventForm.setEndTime(endTime);
            } catch (DateTimeParseException exception) {
                throw new CommandArgsException("startTime or endTime format invalid, it should be in (yyyy-MM-dd HH:mm) format");
            }
        }
    }
}
