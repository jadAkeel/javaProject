
//Separate What to do from How to do it
//Log to Console or to File, Format logging simple or Detailed
package bridge;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GoodOne {
    
}

interface Formatter{
    String format(LogRecord record);
}

class SimpleFormatter implements Formatter{
    public String format(LogRecord record){
        return record.getLevel() + ", " + record.getMsg();
    }
}

class DetailedFormatter implements Formatter{
    public String format(LogRecord record){
        return record.getTime() + ":"
                + record.getLevel() + ", "
                + record.getMsg() + "("
                + record.getAdditionalInfo() + ")";
    }
}

interface Logger {

    void log(LogRecord record);
}

class ConsoleLogger implements Logger {
    
    //Bridge
    private Formatter formatter;

    public ConsoleLogger(Formatter formatter) {
        this.formatter = formatter;
    }
    
    @Override
    public void log(LogRecord record) {
        System.out.println(formatter.format(record));
    }

}

class FileLogger implements Logger {
    //Bridge
    private Formatter formatter;
    private File file;

    public FileLogger(File file, Formatter formatter) {
        this.file = file;
        this.formatter = formatter;
    }

    @Override
    public void log(LogRecord record) {
        //try with resources
        try (PrintWriter pr = new PrintWriter(new FileWriter(file))) {
            pr.println(formatter.format(record));
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SimpleFileLogger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}