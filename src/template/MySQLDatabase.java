package src.template;

// MySQL
public class MySQLDatabase extends Database {
    protected void connect() {
        System.out.println("Connecting to MySQL database...");
    }
    protected void executeQuery() {
        System.out.println("Executing MySQL query...");
    }
}
