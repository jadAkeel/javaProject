
//We want to implement a Logging framework with the following requirements
//Log to the console or to a file
//Provide Detailed information or minimal information

package bridge;

import java.time.LocalDateTime;

enum Level  {INFO,WARN,ERROR}

public class LogRecord {
    private LocalDateTime time;
    private Level level;
    private String msg;
    private String additionalInfo;

    public LogRecord(LocalDateTime time, Level level, String msg, String additionalInfo) {
        this.time = time;
        this.level = level;
        this.msg = msg;
        this.additionalInfo = additionalInfo;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Level getLevel() {
        return level;
    }

    public String getMsg() {
        return msg;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
    
    
}
