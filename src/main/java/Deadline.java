public class Deadline extends Todo {
    protected String by;

    public void setBy(String by) {
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public Deadline(String d) {
        super(d);
        int dateDiv = d.indexOf("/");
        int length = d.length();
        setBy(d.substring(dateDiv + 4));
        description.replace(dateDiv, length, "(by: " + by + ")");

    }
};
