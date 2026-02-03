package bridge;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;

public class BadBridge {

    public static void main(String[] args) {

    }
}

interface Logger {

    void log(LogRecord record);
}

class SimpleConsoleLogger implements Logger {

    @Override
    public void log(LogRecord record) {
        System.out.println(record.getLevel() + ", " + record.getMsg());
    }

}

class DetailedConsoleLogger implements Logger {

    @Override
    public void log(LogRecord record) {
        System.out.println(record.getTime() + ":"
                + record.getLevel() + ", "
                + record.getMsg() + "("
                + record.getAdditionalInfo() + ")");
    }
}

class SimpleFileLogger implements Logger {

    private File file;

    public SimpleFileLogger(File file) {
        this.file = file;
    }

    @Override
    public void log(LogRecord record) {
        //try with resources
        try (PrintWriter pr = new PrintWriter(new FileWriter(file))) {
            pr.println(record.getLevel() + ", " + record.getMsg());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SimpleFileLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(record.getLevel() + ", " + record.getMsg());
    }

}

class DetailedFileLogger implements Logger {

    private File file;

    public DetailedFileLogger(File file) {
        this.file = file;
    }
    
    @Override
    public void log(LogRecord record) {
        //try with resources
        try (PrintWriter pr = new PrintWriter(new FileWriter(file))) {
            pr.println(record.getTime() + ":"
                + record.getLevel() + ", "
                + record.getMsg() + "("
                + record.getAdditionalInfo() + ")");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SimpleFileLogger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
