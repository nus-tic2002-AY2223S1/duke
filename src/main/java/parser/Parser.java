package parser;

import command.*;
import entities.Storage;
import entities.Task;
import entities.TaskList;
import exception.DukeException;
import utils.DukeUtils;
import command.Command;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static command.Command.*;


public class Parser {

    public static void parser(String input) throws DukeException {
        String action, rest;

        DukeUtils.validateInput(input);

        action = input.split(" ", 2)[0].trim();

        switch (action) {
                case "bye":
                    System.out.println("    ---------------------------------------");
                    System.out.println("    Bye. Hope to see you again soon!");
                    Storage.save(TaskList.getMyTaskList());
                    System.exit(0);

                case "list":
                    DukeUtils.printList(TaskList.getMyTaskList());
                    break;

                case "mark":
                    rest = input.split(" ", 2)[1].trim();
                    int index = Integer.parseInt(rest) - 1;
                    Task t = TaskList.getTask(index);
                    t.updateMark(true);
                    DukeUtils.printText("Nice! I've marked this task as done:\n " + "    " + t.toString());
                    break;

                case "unmark":
                    rest = input.split(" ", 2)[1].trim();
                    int indexU = Integer.parseInt(rest) - 1;
                    Task unmark = TaskList.getTask(indexU);
                    unmark.updateMark(false);
                    DukeUtils.printText("OK, I've marked this task as not done yet:\n " + "    " + unmark.toString());
                    break;

                case "todo":
                    createTodo(input, TaskList.getMyTaskList());
                    break;

                case "deadline":
                    createDeadline(input, TaskList.getMyTaskList());
                    break;

                case "event":
                    createEvent(input, TaskList.getMyTaskList());
                    break;

                case "delete":
                    deleteTask(input, TaskList.getMyTaskList());
                    break;

                default:
                    DukeUtils.printText("Sorry, cannot understand input.");
            }
    }
}
