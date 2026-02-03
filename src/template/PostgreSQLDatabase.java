package src.template;



// PostgreSQL
public class PostgreSQLDatabase extends Database {
    protected void connect() {
        System.out.println("Connecting to PostgreSQL database...");
    }
    protected void executeQuery() {
        System.out.println("Executing PostgreSQL query...");
    }
}
