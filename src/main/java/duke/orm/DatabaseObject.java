package duke.orm;

import java.time.Instant;


/**
 * Object of Database Orm
 */
public class DatabaseObject {
    private int sender;
    private String message;
    private long timestamp;

    /**
     * Types of Sender
     */
    public enum Sender {
        DUKE(0),
        USER(1);
        public final int label;

        Sender(int label) {
            this.label = label;
        }

        public int getLabel() {
            return this.label;
        }
    }

    /**
     * Initialize Object based on schema
     */
    public DatabaseObject(Sender s, String msg) {
        this.sender = s.getLabel();
        this.message = msg;
        this.timestamp = Instant.now().getEpochSecond();
    }

    public void write() {
        Database.insert(this);
    }

    public int getSender() {
        return this.sender;
    }

    public String getMessage() {
        return this.message;
    }

    public long getTimestamp() {
        return this.timestamp;
    }


}
