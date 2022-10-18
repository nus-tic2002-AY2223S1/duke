package nus.duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import nus.duke.exceptions.*;
import nus.duke.frontend.*;
import nus.duke.parser.*;
import nus.duke.storage.*;
import nus.duke.tasklist.*;
import nus.duke.task.*;
import java.io.FileNotFoundException;  // Import this class to handle errors


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    Scanner s;

    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            ArrayList<Task> hardDiskTaskList = storage.load();
            tasks = new TaskList(hardDiskTaskList);
        } catch (IOException ioe){
            System.out.println("io exception");
        }
    }

    public void run() {
        ui = new Ui();
        parser = new Parser();
        s = new Scanner(System.in);
        ui.awakeDobby();
        boolean terminateDobby = false;
        String command;
        do{
            String userInput = ui.getUserInput(s);
            command = parser.parse(userInput);
            terminateDobby = tasks.processTasks(command, userInput);
        }while(terminateDobby != true);
        storage.saveTasks(tasks);
        ui.exit();
    }

    public static void main(String[] args) {
        new Duke("/Users/rebecca/Desktop/Duke/data/DukeTasks.txt").run();
    }

}
