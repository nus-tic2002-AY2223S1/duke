import common.exceptions.EmptyTaskListException;
import common.exceptions.InvalidTaskCommandException;
import common.exceptions.InvalidTaskDescriptionException;
import common.exceptions.NotExistTaskException;
import common.exceptions.MarkedTaskException;
import common.exceptions.UnmarkedTaskException;
import model.Chat;
import model.Task;

import java.util.ArrayList;
import java.util.Scanner;

import static common.constants.CommonConstant.PROMPT;
import static common.constants.ErrorMessage.*;
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
            String input = (userInput.nextLine()).toLowerCase();
            String command = getFirstWord(input);
            Chat chat = new Chat(command, input, taskList);

            printLine();

            try {
                parseChat(chat);
            } catch (InvalidTaskCommandException e) {
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
            } catch (Exception e) {
                System.out.println(String.format(EXCEPTION_ERROR_MSG, e));
            }

            printLine();
            System.out.print(PROMPT);
        }
    }
}