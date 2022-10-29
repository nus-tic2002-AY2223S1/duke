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
import static common.utils.PrintUtil.printGreet;
import static common.utils.PrintUtil.printLine;
import static common.utils.StringUtil.getFirstWord;
import static logic.file.FileManager.loadFile;
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
                loadFile(chat);
            } catch (IllegalArgumentException e) {
                System.out.println(e);
            } catch (EmptyTaskListException e) {
                System.out.println(e);
            } catch (InvalidTaskDescriptionException e) {
                System.out.println(e);
            } catch (NotExistTaskException e) {
                System.out.println(e);
            } catch (MarkedTaskException e) {
                System.out.println(e);
            } catch (UnmarkedTaskException e) {
                System.out.println(e);
            } catch (DuplicatedTaskException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.out.println(e);
            }

            printLine();
            System.out.print(PROMPT);
        }
    }
}