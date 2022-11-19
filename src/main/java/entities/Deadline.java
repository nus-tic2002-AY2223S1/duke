package entities;


public class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
            this.type = 'D';
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }

        @Override
        public String toCommandString(){
            return "D" + super.toCommandString() + " | " + by;
        }

}

