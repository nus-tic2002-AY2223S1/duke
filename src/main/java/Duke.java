import common.enums.CommandEnum;
import common.exceptions.EmptyTaskListException;
import common.exceptions.InvalidTaskDescriptionException;
import common.exceptions.NotExistTaskException;
import common.exceptions.MarkedTaskException;
import common.exceptions.UnmarkedTaskException;
import common.exceptions.DuplicatedTaskException;
import model.Chat;
import model.Task;

import java.util.ArrayList;
import java.util.Scanner;

import static common.constants.CommonConstant.PROMPT;
import static common.constants.ErrorMessage.INVALID_TASK_COMMAND_MSG;
import static common.constants.ErrorMessage.EMPTY_TASK_LIST_ERROR_MSG;
import static common.constants.ErrorMessage.INVALID_TASK_DESCRIPTION_ERROR_MSG;
import static common.constants.ErrorMessage.NOT_EXIST_TASK_ERROR_MSG;
import static common.constants.ErrorMessage.MARKED_TASK_ERROR_MSG;
import static common.constants.ErrorMessage.UNMARKED_TASK_ERROR_MSG;
import static common.constants.ErrorMessage.DUPLICATED_TASK_ERROR_MSG;
import static common.constants.ErrorMessage.EXCEPTION_ERROR_MSG;
import static common.utils.PrintUtil.printGreet;
import static common.utils.PrintUtil.printLine;
import static common.utils.StringUtil.getFirstWord;
import static logic.parsers.Parser.parseChat;

public class Duke {
    public static void main(String[] args) {
        printGreet();
        Scanner userInput = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        while (userInput.hasNext()) {
            printLine();

            try {
                String input = (userInput.nextLine()).toLowerCase();
                String command = getFirstWord(input);
                Chat chat = new Chat(CommandEnum.valueOf(command), input, taskList);

                parseChat(chat);
            } catch (IllegalArgumentException e) {
                System.out.println(INVALID_TASK_COMMAND_MSG);
            } catch (EmptyTaskListException e) {
                System.out.println(EMPTY_TASK_LIST_ERROR_MSG);
            } catch (InvalidTaskDescriptionException e) {
                System.out.println(INVALID_TASK_DESCRIPTION_ERROR_MSG);
            } catch (NotExistTaskException e) {
                System.out.println(NOT_EXIST_TASK_ERROR_MSG);
            } catch (MarkedTaskException e) {
                System.out.println(MARKED_TASK_ERROR_MSG);
            } catch (UnmarkedTaskException e) {
                System.out.println(UNMARKED_TASK_ERROR_MSG);
            } catch (DuplicatedTaskException e) {
                System.out.println(DUPLICATED_TASK_ERROR_MSG);
            } catch (Exception e) {
                System.out.println(String.format(EXCEPTION_ERROR_MSG, e));
            }

            printLine();
            System.out.print(PROMPT);
        }
    }
}