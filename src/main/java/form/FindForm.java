package form;

public class FindForm extends Form {

    private String keyword;

    public FindForm(String metaData, String command, String keyword) {
        super(metaData, command);
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
