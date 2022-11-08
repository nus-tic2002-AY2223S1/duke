package common.utils;

import common.enums.CommandEnum;
import common.enums.PeriodicalEnum;
import common.enums.UpdateTypeEnum;
import common.exceptions.InvalidTaskDescriptionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.constants.CommonConstant.INIT_INT_VAL;
import static common.constants.CommonConstant.SPACE;
import static common.constants.CommonConstant.ZERO_VAL;
import static common.constants.CommonConstant.DELIMITER;
import static common.constants.ErrorMessage.EXCEPTION_ERROR_MSG;
import static common.enums.CommandEnum.deadline;
import static common.enums.CommandEnum.event;
import static common.enums.CommandEnum.todo;
import static common.enums.UpdateTypeEnum.desc;
import static logic.validators.Validator.dateTimeRegex;

;

public class StringUtil {
    /**
     * Return the first word of the sentence
     *
     * @param   sentence    sentence from user input
     */
    public static String getFirstWord(String sentence) {
        int index = sentence.contains(SPACE) ? sentence.indexOf(SPACE) : ZERO_VAL;
        String firstWord = sentence.substring(0, index).trim();

        try {
            if (index > 0) {
                return firstWord;
            }
        } catch (Exception e) {
            System.out.println(String.format(EXCEPTION_ERROR_MSG, e));
        }

        return sentence;
    }

    /**
     * Return the substring `description` in a string
     *
     * @param   command
     * @param   sentence    sentence from user input
     */
    public static String getDescriptionFromString(CommandEnum command, String sentence) {
        String commandString = command.toString();
        String substring = sentence;
        int index = sentence.indexOf(commandString);

        try {
            if (substring.contains(DELIMITER)) { // filter string with DELIMITER if it has
                substring = substring.substring(0, substring.indexOf(DELIMITER) - 1);
            }

            if (index > -1 && commandString.length() != 0) { // filter string with command
                substring = substring.substring(index + INIT_INT_VAL + commandString.length());
            }
        } catch (Exception e) {}

        return substring;
    }

    /**
     * Return the substring `time` in a string
     *
     * @param   sentence    sentence from user input
     */
    public static String getTimeFromString(String sentence) {
        String output = "";

        try {
            Pattern pattern = Pattern.compile(dateTimeRegex);
            Matcher matcher = pattern.matcher(sentence);
            if (matcher.find()) {
                output = matcher.group(1);
            }
        } catch (Exception e) {
            System.out.println(String.format(EXCEPTION_ERROR_MSG, e));
        }

        return output;
    }

    /**
     * Return the substring `periodical` in a string
     *
     * @param   sentence    sentence from user input
     */
    public static String getPeriodicalFromString(String sentence) {
        String substring = String.valueOf(PeriodicalEnum.undefined);
        int firstIndex = sentence.indexOf(DELIMITER);
        int lastIndex = sentence.lastIndexOf(DELIMITER);

        try {
            if ((sentence.contains(String.valueOf(todo)) && sentence.contains(DELIMITER)) ||
                    ((sentence.contains(String.valueOf(deadline)) || sentence.contains(String.valueOf(event)))
                            && firstIndex != lastIndex)) {
                substring = sentence.substring(lastIndex + INIT_INT_VAL);
            }
        } catch (Exception e) {
            System.out.println(String.format(EXCEPTION_ERROR_MSG, e));
        }

        return substring;
    }

    /**
     * Return update id in a string
     *
     * @param   sentence    sentence from user input
     * @throws  InvalidTaskDescriptionException
     */
    public static String getUpdateIdFromString(String sentence) throws InvalidTaskDescriptionException {
        try {
            List<String> words = new ArrayList<>(Arrays.asList(sentence.split("\\s+")));
            return words.get(0); // update id is position 1 - (1 from command)
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskDescriptionException();
        }
    }

    /**
     * Return updateEnumType in a string
     *
     * @param   sentence    sentence from user input
     * @throws  InvalidTaskDescriptionException
     */
    public static UpdateTypeEnum getUpdateEnumTypeFromString(String sentence) throws InvalidTaskDescriptionException {
        try {
            List<String> words = new ArrayList<>(Arrays.asList(sentence.split("\\s+")));
            return UpdateTypeEnum.valueOf(words.get(1)); // updateTypeEnum is position 2 - (1 from command)
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskDescriptionException();
        }
    }

    /**
     * Return update description in a string
     *
     * @param   sentence    sentence from user input
     * @throws  InvalidTaskDescriptionException
     */
    public static String getUpdateDescriptionFromString(String sentence) throws InvalidTaskDescriptionException {
        try {
            List<String> words = new ArrayList<>(Arrays.asList(sentence.split("\\s+")));
            String updateDesc = "";
            if (getUpdateEnumTypeFromString(sentence) == desc) {
                updateDesc = words.get(2); // updateTypeEnum is position 2 - (1 from command)
            } else {
                updateDesc = words.get(2) + " " + words.get(3); // updateTypeEnum is position 3 and 5 - (1 from command)
            }
            return updateDesc;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskDescriptionException();
        }
    }
}
