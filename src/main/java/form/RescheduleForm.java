package form;

public class RescheduleForm extends Form {

    private int index;

    public RescheduleForm(String metaData, String command) {
        super(metaData, command);
    }

    public RescheduleForm(String metaData, String command, int index) {
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
