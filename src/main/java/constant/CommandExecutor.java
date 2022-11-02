package constant;

import commands.*;
import entity.*;
import exceptions.DukeException;

import java.util.Date;

public enum CommandExecutor {
    BYE {
        /**
         * Bye command executor operation
         * @param inputs user input
         * @throws DukeException Duke exception
         */
        @Override
        public void execute(String inputs) throws DukeException {
            Command c = new ByeCommand();
            c.execute();
        }
    },
    LIST {
        /**
         * List command executor operation
         * @param inputs user input
         * @throws DukeException Duke exception
         */
        @Override
        public void execute(String inputs) throws DukeException {
            Command c = new ListCommand();
            c.execute();
        }
    },
    MARK {
        /**
         * Mark command executor operation
         * @param inputs user input
         * @throws DukeException Duke exception
         */
        @Override
        public void execute(String inputs) throws DukeException {
            Command c = new MarkCommand(inputs);
            c.execute();
        }
    },
    UNMARK {
        /**
         * Unmark command executor operation
         * @param inputs user input
         * @throws DukeException Duke exception
         */
        @Override
        public void execute(String inputs) throws DukeException {
            Command c = new UnmarkCommand(inputs);
            c.execute();
        }
    },
    DEADLINE {
        /**
         * Deadline command executor operation
         * @param inputs user input
         * @throws DukeException Duke exception
         */
        @Override
        public void execute(String inputs) throws DukeException {
            Command c = new DeadlineCommand(inputs);
            c.execute();
        }
    },
    TODO {
        /**
         * To-do command executor operation
         * @param inputs user input
         * @throws DukeException Duke exception
         */
        @Override
        public void execute(String inputs) throws DukeException {
            Command c = new TodoCommand(inputs);
            c.execute();
        }
    },
    EVENT {
        /**
         * Event command executor operation
         * @param inputs user input
         * @throws DukeException Duke exception
         */
        @Override
        public void execute(String inputs) throws DukeException {
            Command c = new EventCommand(inputs);
            c.execute();
        }
    },
    DELETE {
        /**
         * Delete command executor operation
         * @param inputs user input
         * @throws DukeException Duke exception
         */
        @Override
        public void execute(String inputs) throws DukeException {
            Command c = new DeleteCommand(inputs);
            c.execute();
        }
    },
    SAVE {
        /**
         * Save command executor operation
         * @param inputs user input
         * @throws DukeException Duke exception
         */
        @Override
        public void execute(String inputs) throws DukeException {
            Command c = new SaveCommand();
            c.execute();
        }
    },
    UNDEFINED {
        /**
         * Undefined command executor operation
         * @param inputs user input
         * @throws DukeException Duke exception
         */
        @Override
        public void execute(String inputs) throws DukeException {
            Command c = new UndefinedCommand();
            c.execute();
        }
    };

    public abstract void execute(String inputs) throws DukeException;
}
