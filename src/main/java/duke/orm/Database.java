package duke.orm;

import java.sql.*;
import java.time.Instant;

public class Database {
    public Database() {
    }

    public static Connection init() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:data/test.db");
            c.setAutoCommit(false);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return null;
        }
        System.out.println("Opened database successfully");
        return c;
    }

    public void createTable() {
        Connection c = init();
        Statement stmt = null;

        try {
            String tb = "chat_tab";
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS " + tb +
                    " ('id' INTEGER NOT NULL," +
                    "'sender' INTEGER NOT NULL, " +
                    "'message' TEXT NOT NULL, " +
                    "'timestamp' INTEGER NOT NULL," +
                    "PRIMARY KEY('id' AUTOINCREMENT));";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            System.out.println(c.isClosed());
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return;
        }
        System.out.println("Table created successfully");
    }

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

    public static void logUser(String message) {
        DatabaseObject o = new DatabaseObject(DatabaseObject.Sender.USER, message);
        o.write();
    }

    public static void logDuke(String message) {
        DatabaseObject o = new DatabaseObject(DatabaseObject.Sender.DUKE, message);
        o.write();
    }
}