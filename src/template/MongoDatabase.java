package src.template;

// MongoDB
class MongoDBDatabase extends Database {
    protected void connect() {
        System.out.println("Connecting to MongoDB...");
    }
    protected void executeQuery() {
        System.out.println("Executing MongoDB query...");
    }
}
