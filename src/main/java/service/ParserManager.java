package service;

import constant.CommandEnum;
import entity.Form;
import exception.CommandArgsException;
import util.DataUtil;
import util.StringUtil;

public class ParserManager {

    private ParserManager() {}

    /**
     * default parser to parse the string input
     */
    private static final DefaultCommandParser defaultCommandParser = new DefaultCommandParser();

    /**
     * parser to parse the string input for `mark` operation
     */
    private static final MarkCommandParser markCommandParser = new MarkCommandParser();

    /**
     * parser to parse the string input for `unmark` operation
     */
    private static final UnmarkCommandParser unmarkCommandParser = new UnmarkCommandParser();

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
                return markCommandParser;
            case UNMARK_TASK:
                return unmarkCommandParser;
            default:
                return defaultCommandParser;
        }
    }

    private interface Parser {

        Form parseForm(String input);
    }

    private static class DefaultCommandParser implements Parser {

        @Override
        public Form parseForm(String input) {
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

            return new Form(input, CommandEnum.MARK_TASK.getName(), index);
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

            return new Form(input, CommandEnum.UNMARK_TASK.getName(), index);
        }
    }
}
