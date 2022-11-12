# nglihui
###### /src/main/java/Duke.java
``` java
    private void start(String[] launchArgs) throws InvalidStorageFilePathException, IOException {
        this.ui = new UI();
        this.taskList = new TaskList();
        ui.printIntro();

        while (true) {
                String input = UI.getUserCommand();
                assert input != null : "User should not input null.";
                String[] inputSplit = input.split(" ");

                // enter bye to end chat
                if (input.equals("bye"))
                    exit();

                // to list all items
                else if (input.equals("list"))
                    taskList.listTask(inputSplit, input);

                // mark items
                else if (input.startsWith("mark"))
                    taskList.markTask(inputSplit, input);

                // unmarked items
                else if (input.startsWith("unmark"))
                    taskList.unmarkTask(inputSplit, input);

                // to do task
                else if (input.startsWith("todo"))
                    taskList.todoTask(input, inputSplit);

                // deadline task
                else if (input.startsWith("deadline"))
                    taskList.deadlineTask(input, inputSplit);

                // event task
                else if (input.startsWith("event"))
                    taskList.eventTask(input, inputSplit);

                // delete task
                else if (input.startsWith("delete"))
                    taskList.deleteTask(input, inputSplit);

                // view scheduled task for the date
                else if (input.startsWith("schedule for"))
                    taskList.scheduleTask(input, inputSplit);

                else if (input.startsWith("find"))
                    taskList.findTask(input, inputSplit);

                else if (input.startsWith("fixed"))
                    taskList.fixedDurationTasks(input,inputSplit);

                // prompt user to enter valid input
                else
                    UI.printStandardError();

                // to actively store task list
                Storage.mainCaller();
            }
        }

    //    Prints the Goodbye message and exits.
    private void exit() {
        ui.printBye();
        System.exit(0);
    }

    public static void main(String... launchArgs) throws InvalidStorageFilePathException, IOException {
        new Duke().start(launchArgs);
    }
}
```
