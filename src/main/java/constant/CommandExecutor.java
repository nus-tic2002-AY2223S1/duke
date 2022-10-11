package constant;

import entity.TaskList;
import entity.Deadline;
import entity.Event;
import entity.Todo;

import Utils.DukeUtils;
import exceptions.DukeException;

public enum CommandExecutor {
    BYE {
        @Override
        public void execute(TaskList taskList, String inputs) {
            System.out.println("bye bye");
        }
    },
    LIST {
        @Override
        public void execute(TaskList taskList, String inputs) {
            DukeUtils.printList(taskList.tasks);
        }
    },
    MARK {
        @Override
        public void execute(TaskList taskList, String inputs) throws DukeException {
            if (DukeUtils.countCommandParts(inputs) < 2)
                throw new DukeException("☹ OOPS!!! Mark is not specified.");

            String commandBody = DukeUtils.getCommandBody(inputs);
            int taskNo = Integer.parseInt(commandBody) - 1;

            if (taskNo >= taskList.tasks.size())
                throw new DukeException("☹ OOPS!!! The task for marking is not in list.");
            taskList.markTask(taskNo);

        }
    },
    UNMARK {
        @Override
        public void execute(TaskList taskList, String inputs) throws DukeException {
            if (DukeUtils.countCommandParts(inputs) < 2)
                throw new DukeException("☹ OOPS!!! Unmark is not specified");

            String commandBody = DukeUtils.getCommandBody(inputs);
            int taskNo = Integer.parseInt(commandBody) - 1;

            if (taskNo >= taskList.tasks.size())
                throw new DukeException("☹ OOPS!!! The task for unmarking is not in list.");
            taskList.unmarkTask(taskNo);
        }
    },
    DEADLINE {
        @Override
        public void execute(TaskList taskList, String inputs) throws DukeException {
            if (DukeUtils.countCommandParts(inputs) < 2)
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");

            String commandBody = DukeUtils.getCommandBody(inputs);
            String[] deadlineDesc = commandBody.split("/by", 2);
            Deadline deadline = deadlineDesc.length == 2 ?
                    new Deadline(deadlineDesc[0], deadlineDesc[1]) :
                    new Deadline(deadlineDesc[0], "");
            taskList.addTask(deadline);
        }
    },
    TODO {
        @Override
        public void execute(TaskList taskList, String inputs) throws DukeException {
            if (DukeUtils.countCommandParts(inputs) < 2)
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");

            String commandBody = DukeUtils.getCommandBody(inputs);
            Todo todo = new Todo(commandBody);
            taskList.addTask(todo);
        }
    },
    EVENT {
        @Override
        public void execute(TaskList taskList, String inputs) throws DukeException {
            if (DukeUtils.countCommandParts(inputs) < 2)
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");

            String commandBody = DukeUtils.getCommandBody(inputs);
            String[] eventDesc = commandBody.split("/at", 2);
            Event event = eventDesc.length == 2 ?
                    new Event(eventDesc[0], eventDesc[1]) :
                    new Event(eventDesc[0], "");
            taskList.addTask(event);
        }
    },
    DELETE {
        @Override
        public void execute(TaskList taskList, String inputs) throws DukeException {
            if (DukeUtils.countCommandParts(inputs) < 2)
                throw new DukeException("☹ OOPS!!! Delete is not specified");

            String commandBody = DukeUtils.getCommandBody(inputs);
            int taskNo = Integer.parseInt(commandBody) - 1;

            if (taskNo >= taskList.tasks.size())
                throw new DukeException("☹ OOPS!!! The task for deleting is not in list.");

            taskList.deleteTask(taskNo);
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
