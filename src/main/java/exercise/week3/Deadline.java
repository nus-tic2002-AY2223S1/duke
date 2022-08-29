package exercise.week3;

public class Deadline extends Todo {

    private String by;

    public Deadline(String description) {
        super(description);
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        String s1 = super.toString();
        return s1 + System.lineSeparator() + "do by: " + by;
    }
}

