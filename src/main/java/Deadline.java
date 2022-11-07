//TODO: Add your code here
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
        // if (d.charAt(dateDiv - 2) == ' ') {
        //     setBy(d.substring(dateDiv - 1));
        //     description.replace(dateDiv-1, length, "(by: " + by + ")");
        // } else {
        //     setBy(d.substring(dateDiv - 2));
        //     description.replace(dateDiv-2, length, "(by: " + by + ")");
        // }
        //this.description.append(" (by: " + by + ")");
    }
};
