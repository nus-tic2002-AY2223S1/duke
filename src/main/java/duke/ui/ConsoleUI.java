package duke.ui;

import com.blinkfox.minitable.MiniTable;
import duke.constant.CommandEnum;
import duke.constant.Constant;
import duke.dto.CommandTableDto;
import duke.dto.ResponseDto;
import duke.entity.Task;
import duke.util.CollectionUtil;
import duke.util.ExceptionUtil;
import duke.util.InputUtil;
import duke.util.StringUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsoleUI implements UI {

    private interface RenderHandler {
        void render(ResponseDto<?> responseDto);
    }

    private final Map<String, RenderHandler> renderHandlerMap = new HashMap<>();

    /**
     * store the maximum length, used to adjust the line break
     */
    private int maxLineLength = 0;

    /**
     * default render handler which used to render response entity after executing command
     */
    RenderHandler defaultRenderHandler = responseDto -> {
        printResponseMessage(responseDto);
        printLineBreak();
    };

    /**
     * render handler which used to render response entity after executing `show_command` command
     */
    RenderHandler showCommandRenderHandler = responseDto -> {
        // render table data
        CommandTableDto commandTableDto = (CommandTableDto) responseDto.getData();
        MiniTable miniTable = new MiniTable();
        miniTable.addHeaders(commandTableDto.getHeaders());
        commandTableDto.getRows().forEach(miniTable::addDatas);
        System.out.println(miniTable.render());
    };

    /**
     * render handler which used to render response entity after executing `list` command
     */
    @SuppressWarnings("unchecked")
    RenderHandler listTaskRenderHandler = responseDto -> {
        // render task data
        List<Task> taskList = (List<Task>) responseDto.getData();
        int index = 1;
        for (Task task : taskList) {
            System.out.printf("%d. %s%n", index++, task.toString());
        }

        printResponseMessage(responseDto);
        printLineBreak();
    };

    /**
     * render handler which used to render response entity after executing `find` command
     */
    @SuppressWarnings("unchecked")
    RenderHandler findTaskCommandHandler = responseDto -> {
        // render task data
        List<Task> taskList = (List<Task>) responseDto.getData();
        if (!CollectionUtil.isEmpty(taskList)) {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                String taskInfo = String.format("%d: %s", i + 1, taskList.get(i).toString());
                System.out.println(taskInfo);
            }
        }

        printResponseMessage(responseDto);
        printLineBreak();
    };

    /**
     * render handler which used to render response entity after executing `bye` command
     * program will terminate after render complete
     */
    RenderHandler existCommandHandler = responseDto -> {
        printResponseMessage(responseDto);
        System.exit(0);
    };

    public ConsoleUI() {
        // compute the length line break
        // included bar length of 2 + last space
        this.maxLineLength = getGuidance().stream().map(String::length).max(Comparator.naturalOrder()).orElse(0) + 3;

        // init render map
        renderHandlerMap.put(CommandEnum.SHOW_COMMAND.getName(), showCommandRenderHandler);
        renderHandlerMap.put(CommandEnum.LIST.getName(), listTaskRenderHandler);
        renderHandlerMap.put(CommandEnum.FIND_TASK.getName(), findTaskCommandHandler);
        renderHandlerMap.put(CommandEnum.EXIT.getName(), existCommandHandler);
    }

    @Override
    public void renderGreetingMessage() {
        // print logo
        System.out.println(Constant.STARTING_LOGO);
        System.out.println();

        // get guidance info in list
        List<String> guidance = getGuidance();

        // print guidance in neat format
        System.out.println("Greeting from Duke! ;>");
        printGuidanceLineBreak("~", maxLineLength);
        guidance.forEach(o -> {
            String message = String.format("|%s%s|", o, getPaddingSpace(maxLineLength - o.length() - 2));
            System.out.println(message);
        });
        printGuidanceLineBreak("~", maxLineLength);
    }

    private List<String> getGuidance() {
        String guidance =
                "A quick guidance for you.\n" +
                "Enter `show_command` to check current supported command.\n" +
                "Any input which is not included in supported commands will be treated as\n" +
                "invalid command and will not be processed by the program.";
        return StringUtil.stringToList(guidance, "\n");
    }

    private void printGuidanceLineBreak(String label, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(label);
        }
        System.out.println();
    }

    private String getPaddingSpace(int length) {
        return Stream.generate(() -> " ").limit(length).collect(Collectors.joining());
    }

    private void printLineBreak() {
        System.out.print("+");
        for (int i = 0; i < maxLineLength - 2; i++) {
            System.out.print("-");
        }
        System.out.print("+");
        System.out.println();
    }

    @Override
    public void renderDukeErrorMsg(String errorMsg) {
        System.out.println(":(");
        System.out.println("cannot execute command due to error raised");
        System.out.println(errorMsg);
        printLineBreak();
    }

    @Override
    public void renderUnknownErrorMsg(Throwable throwable) {
        System.out.println(":(");
        System.out.println("unknown error raised, please contact developer for assistance");
        // actual error message should keep internally, prevent exposure to user. (for dev purpose, do not comment out the given code)
        System.out.println(ExceptionUtil.getStackTraceAsString(throwable));
        printLineBreak();
    }

    public String getDukeCommandInput() {
        System.out.print("~@duke >>> ");
        return InputUtil.getInputString();
    }

    @Override
    public void renderResponse(ResponseDto<?> responseDto) {
        String commandName = responseDto.getCommandName();
        RenderHandler renderHandler = renderHandlerMap.getOrDefault(commandName, defaultRenderHandler);
        renderHandler.render(responseDto);
    }

    private void printResponseMessage(ResponseDto<?> responseDto) {
        if (!StringUtil.isBlank(responseDto.getMessage())) {
            System.out.println(responseDto.getMessage());
        }
    }
}
