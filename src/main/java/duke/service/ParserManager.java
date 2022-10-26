package duke.service;

import duke.constant.CommandEnum;
import duke.constant.Constant;
import duke.form.DeleteForm;
import duke.form.DeadlineForm;
import duke.form.EventForm;
import duke.form.FindForm;
import duke.form.Form;
import duke.form.MarkingForm;
import duke.form.RescheduleForm;
import duke.form.TodoForm;
import duke.exception.CommandArgsException;
import duke.util.DataUtil;
import duke.util.DateUtil;
import duke.util.StringUtil;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class which used to parse the console input from user.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class ParserManager {

    private ParserManager() {}

    /**
     * Default parser to parse the string input.
     */
    private static final DefaultCommandParser DEFAULT_COMMAND_PARSER = new DefaultCommandParser();

    /**
     * Parser to parse the string input for `mark` operation.
     */
    private static final MarkCommandParser MARK_COMMAND_PARSER = new MarkCommandParser();

    /**
     * Parser to parse the string input for `unmark` operation.
     */
    private static final UnmarkCommandParser UNMARK_COMMAND_PARSER = new UnmarkCommandParser();

    /**
     * Parser to parse the string input for `todo` operation.
     */
    private static final TodoCommandParser TODO_COMMAND_PARSER = new TodoCommandParser();

    /**
     * Parser to parse the string input for `deadline` operation.
     */
    private static final DeadlineCommandParser DEADLINE_COMMAND_PARSER = new DeadlineCommandParser();

    /**
     * Parser to parse the string input for `event` operation.
     */
    private static final EventCommandParser EVENT_COMMAND_PARSER = new EventCommandParser();

    /**
     * Parser to parse the string input for `delete` operation.
     */
    private static final DeleteCommandParser DELETE_COMMAND_PARSER = new DeleteCommandParser();

    /**
     * Parser to parse the string input for `delete` operation.
     */
    private static final FindCommandParser FIND_COMMAND_PARSER = new FindCommandParser();

    /**
     * Parser to parse the string input for `reschedule` operation.
     */
    private static final RescheduleCommandParser RESCHEDULE_COMMAND_PARSER = new RescheduleCommandParser();

    /**
     * Parse the console input from user.
     *
     * @param input: Console input.
     * @return Form contain the parsed parameters.
     */
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
            case DELETE_TASK:
                return DELETE_COMMAND_PARSER;
            case FIND_TASK:
                return FIND_COMMAND_PARSER;
            case TODO:
                return TODO_COMMAND_PARSER;
            case DEADLINE:
                return DEADLINE_COMMAND_PARSER;
            case EVENT:
                return EVENT_COMMAND_PARSER;
            case RESCHEDULE:
                return RESCHEDULE_COMMAND_PARSER;
            default:
                return DEFAULT_COMMAND_PARSER;
        }
    }

    /**
     * Internal interface to be implemented by different parser
     */
    private interface Parser {

        Form parseForm(String input);

    }

    /**
     * Implementation class to parse unknown command.
     *
     * @author Dex
     * @date 2022/10/26
     */
    private static class DefaultCommandParser implements Parser {

        /**
         * Return what is given in the input.
         *
         * @param input: Input from console.
         * @return Parse form.
         */
        @Override
        public Form parseForm(String input) {
            // single string input, input could be command itself
            return new Form(input, input);
        }
    }

    /**
     * Implementation class to parse mark command.
     *
     * @author Dex
     * @date 2022/10/26
     */
    private static class MarkCommandParser implements Parser {

        /**
         * Extract the index parameter from input.
         *
         * @param input: Input from console.
         * @return Parsed form.
         */
        @Override
        public Form parseForm(String input) {
            String[] args = input.split(" ");
            if (args.length != 2) {
                throw new CommandArgsException("[mark] command has invalid arguments, it should be in `mark <index>` format");
            }

            // check index
            int index = DataUtil.toInteger(args[1]);
            if (index < 1) {
                throw new CommandArgsException("given index is invalid, it should be more than 0");
            }

            return new MarkingForm(input, CommandEnum.MARK_TASK.getName(), index);
        }
    }

    /**
     * Implementation class to parse unmark command.
     *
     * @author Dex
     * @date 2022/10/26
     */
    private static class UnmarkCommandParser implements Parser {

        /**
         * Extract the index parameter from input.
         *
         * @param input: Input from console.
         * @return Parsed form.
         */
        @Override
        public Form parseForm(String input) {
            String[] args = input.split(" ");
            if (args.length != 2) {
                throw new CommandArgsException("[unmark] command has invalid arguments, it should be in `unmark <index>` format");
            }

            // check index
            int index = DataUtil.toInteger(args[1]);
            if (index < 1) {
                throw new CommandArgsException("given index is invalid, it should be more than 0");
            }

            return new MarkingForm(input, CommandEnum.UNMARK_TASK.getName(), index);
        }
    }

    /**
     * Implementation class to parse delete command.
     *
     * @author Dex
     * @date 2022/10/26
     */
    private static class DeleteCommandParser implements Parser {

        /**
         * Extract the index parameter from input.
         *
         * @param input: Input from console.
         * @return Parsed form.
         */
        @Override
        public Form parseForm(String input) {
            String[] args = input.split(" ");
            if (args.length != 2) {
                throw new CommandArgsException("[delete] command has invalid arguments, it should be in `delete <index>` format");
            }

            // check index
            int index = DataUtil.toInteger(args[1]);
            if (index < 1) {
                throw new CommandArgsException("given index is invalid, it should be more than 0");
            }

            return new DeleteForm(input, CommandEnum.DELETE_TASK.getName(), index);
        }
    }

    /**
     * Implementation class to parse find command.
     *
     * @author Dex
     * @date 2022/10/26
     */
    private static class FindCommandParser implements Parser {

        /**
         * Regex expression which used to match the given input from user.
         */
        private static final Pattern findClausePattern = Pattern.compile("find (.*)");

        /**
         * Extract the keyword parameter from input.
         *
         * @param input: Input from console.
         * @return Parsed form.
         */
        @Override
        public Form parseForm(String input) {
            Matcher matcher = findClausePattern.matcher(input);
            if (!matcher.find()) {
                throw new CommandArgsException("[find] command has invalid arguments, it should be in `find <keyword>` format");
            }

            String keyword = StringUtil.trim(matcher.group(1));
            return new FindForm(input, CommandEnum.FIND_TASK.getName(), keyword);
        }
    }

    /**
     * Implementation class to parse reschedule command.
     *
     * @author Dex
     * @date 2022/10/26
     */
    private static class RescheduleCommandParser implements Parser {

        /**
         * Extract the index parameter from input.
         *
         * @param input: Input from console.
         * @return Parsed form.
         */
        @Override
        public Form parseForm(String input) {
            String[] args = input.split(" ");
            if (args.length != 2) {
                throw new CommandArgsException("[reschedule] command has invalid arguments, it should be in `reschedule <index>` format");
            }

            // check index
            int index = DataUtil.toInteger(args[1]);
            if (index < 1) {
                throw new CommandArgsException("given index is invalid, it should be more than 0");
            }

            return new RescheduleForm(input, CommandEnum.RESCHEDULE.getName(), index);
        }
    }

    /**
     * Implementation class to parse todo command.
     *
     * @author Dex
     * @date 2022/10/26
     */
    private static class TodoCommandParser implements Parser {

        /**
         * Regex expression which used to match the given input from user.
         */
        private static final Pattern todoClausePattern = Pattern.compile("todo (.*)");

        /**
         * Extract the description parameter from input.
         *
         * @param input: Input from console.
         * @return Parsed form.
         */
        @Override
        public Form parseForm(String input) {
            Matcher matcher = todoClausePattern.matcher(input);
            if (!matcher.find()) {
                throw new CommandArgsException("[todo] command has invalid arguments, it should be in `todo <description>` format");
            }

            String description = StringUtil.trim(matcher.group(1));
            return new TodoForm(input, CommandEnum.TODO.getName(), description);
        }
    }

    /**
     * Implementation class to parse deadline command.
     *
     * @author Dex
     * @date 2022/10/26
     */
    private static class DeadlineCommandParser implements Parser {

        /**
         * Regex expression which used to match the given input from user.
         */
        private static final Pattern deadlineClausePattern = Pattern.compile("deadline ([\\s\\w\\W]*)/\\s*by (.*)");

        /**
         * Extract the description, deadline time parameter from input.
         *
         * @param input: Input from console.
         * @return Parsed form.
         */
        @Override
        public Form parseForm(String input) {
            Matcher matcher = deadlineClausePattern.matcher(input);
            if (!matcher.find()) {
                throw new CommandArgsException("[deadline] command has invalid arguments, it should be in `deadline <description> / by <deadlineTime(yyyy-MM-dd HH:mm)>` format");
            }

            String description = StringUtil.trim(matcher.group(1));
            String param = StringUtil.trim(matcher.group(2));
            DeadlineForm deadlineForm = new DeadlineForm(input, CommandEnum.DEADLINE.getName(), description);
            deadlineForm.setBy(DateUtil.parse(param, Constant.Time.INPUT_FORMAT));
            return deadlineForm;
        }
    }

    /**
     * Implementation class to parse event command.
     *
     * @author Dex
     * @date 2022/10/26
     */
    private static class EventCommandParser implements Parser {

        /**
         * Regex expression which used to match the given input from user.
         */
        private static final Pattern eventClausePattern = Pattern.compile("event ([\\s\\w\\W]*)/\\s*at (.*)");

        /**
         * Extract the description, start time and end time parameter from input.
         *
         * @param input: Input from console.
         * @return Parsed form.
         */
        @Override
        public Form parseForm(String input) {
            Matcher matcher = eventClausePattern.matcher(input);
            if (!matcher.find()) {
                throw new CommandArgsException("[event] command has invalid arguments, it should be in `event <description> / at <startTime(yyyy-MM-dd HH:mm) & endTime(yyyy-MM-dd HH:mm)>` format");
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

            // get time within a specific period
            String startTimeInput = StringUtil.trim(args[0]);
            String endTineInput = StringUtil.trim(args[1]);
            LocalDateTime startTime = DateUtil.parse(startTimeInput, Constant.Time.INPUT_FORMAT);
            LocalDateTime endTime = DateUtil.parse(endTineInput, Constant.Time.INPUT_FORMAT);
            // check time validity
            if (startTime.isAfter(endTime)) {
                throw new CommandArgsException("input time value is incorrect, startTime cannot be greater than endTime");
            }
            eventForm.setStartTime(startTime);
            eventForm.setEndTime(endTime);
        }
    }
}
