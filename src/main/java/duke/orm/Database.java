package duke.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

/**
 * Interface of Database Orm
 */
public class Database {
    public Database() {
    }

    /**
     * Initialize Database Connection
     */
    public static Connection init() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:data/tmp/chat.db");
            c.setAutoCommit(false);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return null;
        }
        System.out.println("Opened database successfully");
        return c;
    }

    /**
     * Create new table
     */
    public void createTable() {
        Connection c = init();
        Statement stmt = null;

        try {
            String tb = "chat_tab";
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS " + tb
                    + " ('id' INTEGER NOT NULL,"
                    + "'sender' INTEGER NOT NULL, "
                    + "'message' TEXT NOT NULL, "
                    + "'timestamp' INTEGER NOT NULL,"
                    + "PRIMARY KEY('id' AUTOINCREMENT));";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Table created successfully");
    }

    /**
     * Insert into table
     */
    public static void insert(DatabaseObject o) {
        Connection c = init();
        Statement stmt = null;

        try {
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO chat_tab (sender,message,timestamp) VALUES (%s,'%s',%s);";
            String q = String.format(sql, o.getSender(), o.getMessage().replace("'", "''"), o.getTimestamp());
            System.out.println(q);
            stmt.executeUpdate(q);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    private static boolean isEmpty(String s) {
        return Objects.equals(s, "");
    }

    /**
     * Log user dialog into table
     */
    public static void logUser(String message) {
        if (isEmpty(message)) {
            return;
        }
        DatabaseObject o = new DatabaseObject(DatabaseObject.Sender.USER, message);
        o.write();
    }

    /**
     * Log duke dialog into table
     */
    public static void logDuke(String message) {
        if (isEmpty(message)) {
            return;
        }
        DatabaseObject o = new DatabaseObject(DatabaseObject.Sender.DUKE, message);
        o.write();
    }
}
