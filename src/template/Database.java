package src.template;

// Abstract Class
abstract class Database {
    public final void connectAndQuery() {
        connect();
        executeQuery();
        disconnect();
    }

    protected abstract void connect();
    protected abstract void executeQuery();

    protected void disconnect() {
        System.out.println("Disconnecting from database...");
    }
}
