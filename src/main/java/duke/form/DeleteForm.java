package duke.form;

public class DeleteForm extends Form {

    private int index;

    public DeleteForm(String metaData, String command) {
        super(metaData, command);
    }

    public DeleteForm(String metaData, String command, int index) {
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
