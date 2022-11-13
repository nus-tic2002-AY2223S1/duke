package duke.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Data transfer object used for list command.
 */
public class CommandTableDto {

    /**
     * Store header data in the table.
     */
    private List<String> headers = new ArrayList<>();

    /**
     * Store body data in the table.
     */
    private List<List<String>> rows = new ArrayList<>();

    /**
     * Returns headers.
     *
     * @return Header data of table.
     */
    public List<String> getHeaders() {
        return headers;
    }

    /**
     * Sets headers.
     *
     * @param headers: Target headers.
     */
    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    /**
     * Returns body in rows.
     *
     * @return Body data of table.
     */
    public List<List<String>> getRows() {
        return rows;
    }

    /**
     * Sets body data.
     *
     * @param rows: Target body data.
     */
    public void setRows(List<List<String>> rows) {
        this.rows = rows;
    }
}
