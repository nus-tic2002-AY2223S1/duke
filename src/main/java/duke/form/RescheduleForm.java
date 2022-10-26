package duke.form;

/**
 * Form stores the parsed parameters from console.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class RescheduleForm extends Form {

    /**
     * Index of task.
     */
    private int index;


    /**
     * Multi args constructor.
     *
     * @param metaData: Raw input from user.
     * @param command: Command name.
     * @param index: Index of task in the list.
     */
    public RescheduleForm(String metaData, String command, int index) {
        super(metaData, command);
        this.index = index;
    }

    /**
     * Get index.
     *
     * @return Index of task in the list.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Set index.
     *
     * @param index: Index of task in the list.
     */
    public void setIndex(int index) {
        this.index = index;
    }
}
