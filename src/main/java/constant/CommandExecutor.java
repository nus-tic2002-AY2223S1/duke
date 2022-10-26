package constant;

import entity.*;

import exceptions.DukeException;

public enum CommandExecutor {
    BYE {
        @Override
        public void execute(TaskList taskList, String inputs) {
            Ui.echoText("Bye. Hope to see you again soon!");
        }
    },
    LIST {
        @Override
        public void execute(TaskList taskList, String inputs) {
            taskList.printTasks();
        }
    },
    MARK {
        @Override
        public void execute(TaskList taskList, String inputs) throws DukeException {
            if (CommandParser.countCommandParts(inputs) < 2)
                throw new DukeException("☹ OOPS!!! Mark is not specified.");

            String inputBody = CommandParser.getCommandBody(inputs);
            int taskNo = Integer.parseInt(inputBody) - 1;

            if (taskNo >= taskList.tasks.size())
                throw new DukeException("☹ OOPS!!! The task for marking is not in list.");
            taskList.markTask(taskNo);

        }
    },
    UNMARK {
        @Override
        public void execute(TaskList taskList, String inputs) throws DukeException {
            if (CommandParser.countCommandParts(inputs) < 2)
                throw new DukeException("☹ OOPS!!! Unmark is not specified");

            String inputBody = CommandParser.getCommandBody(inputs);
            int taskNo = Integer.parseInt(inputBody) - 1;

            if (taskNo >= taskList.tasks.size())
                throw new DukeException("☹ OOPS!!! The task for unmarking is not in list.");
            taskList.unmarkTask(taskNo);
        }
    },
    DEADLINE {
        @Override
        public void execute(TaskList taskList, String inputs) throws DukeException {
            if (CommandParser.countCommandParts(inputs) < 2)
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");

            String inputBody = CommandParser.getCommandBody(inputs);
            String[] deadlineDesc = CommandParser.getDeadlineDetails(inputBody);
            Deadline deadline = deadlineDesc.length == 2 ?
                    new Deadline(deadlineDesc[0], deadlineDesc[1]) :
                    new Deadline(deadlineDesc[0], "");
            taskList.addTask(deadline);
        }
    },
    TODO {
        @Override
        public void execute(TaskList taskList, String inputs) throws DukeException {
            if (CommandParser.countCommandParts(inputs) < 2)
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");

            String inputBody = CommandParser.getCommandBody(inputs);
            Todo todo = new Todo(inputBody);
            taskList.addTask(todo);
        }
    },
    EVENT {
        @Override
        public void execute(TaskList taskList, String inputs) throws DukeException {
            if (CommandParser.countCommandParts(inputs) < 2)
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");

            String inputBody = CommandParser.getCommandBody(inputs);
            String[] eventDesc = CommandParser.getEventDetails(inputBody);
            Event event = eventDesc.length == 2 ?
                    new Event(eventDesc[0], eventDesc[1]) :
                    new Event(eventDesc[0], "");
            taskList.addTask(event);
        }
    },
    DELETE {
        @Override
        public void execute(TaskList taskList, String inputs) throws DukeException {
            if (CommandParser.countCommandParts(inputs) < 2)
                throw new DukeException("☹ OOPS!!! Delete is not specified");

            String inputBody = CommandParser.getCommandBody(inputs);
            int taskNo = Integer.parseInt(inputBody) - 1;

            if (taskNo >= taskList.tasks.size())
                throw new DukeException("☹ OOPS!!! The task for deleting is not in list.");

            taskList.deleteTask(taskNo);
        }
    },
    SAVE {
        @Override
        public void execute(TaskList taskList, String inputs) throws DukeException {
            Storage file = new Storage();
            file.write(taskList.writeTasksToFile());
            Ui.echoText("The tasks have been saved");
        }
    },
    UNDEFINED {
        @Override
        public void execute(TaskList taskList, String inputs) throws DukeException {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    };


//    public abstract void execute();

    public abstract void execute(TaskList taskList, String inputs) throws DukeException;
}
