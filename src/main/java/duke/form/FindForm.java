package duke.form;

/**
 * Form stores the parsed parameters from console.
 *
 * @author Dex
 * @date 2022/10/26
 */
public class FindForm extends Form {

    /**
     * Keyword to search.
     */
    private String keyword;

    /**
     * Multi args constructor.
     *
     * @param metaData: Raw input from user.
     * @param command: Command name.
     * @param keyword: Keyword in command description.
     */
    public FindForm(String metaData, String command, String keyword) {
        super(metaData, command);
        this.keyword = keyword;
    }

    /**
     * Get keyword.
     *
     * @return Keyword used for search task description.
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Set keyword.
     *
     * @param keyword: Keyword used for search task description.
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
