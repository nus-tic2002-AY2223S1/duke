package duke.form;

public class MarkingForm extends Form {

    private int index;

    public MarkingForm(String metaData, String command) {
        super(metaData, command);
    }

    public MarkingForm(String metaData, String command, int index) {
        super(metaData, command);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
