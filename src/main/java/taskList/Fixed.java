package tasklist;

public class Fixed extends Task {
    protected String needs;

    public Fixed(String description, String needs) {
        super(description);
        this.needs = needs;
    }

    // event string to print
    @Override
    public String toString() {
        return "[F]" + super.toString() + " (Time Required: " + this.needs + ")";
    }

    @Override
    public String toOutput() {
        return "F" + super.toOutput() + ";" + this.needs;
    }
}
