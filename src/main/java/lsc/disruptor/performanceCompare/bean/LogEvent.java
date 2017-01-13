package lsc.disruptor.performanceCompare.bean;

import java.io.Serializable;

public class LogEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    private long logId;
    private String content;

    public LogEvent() {

    }

    public LogEvent(long logId, String content) {
        this.logId = logId;
        this.content = content;
    }

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}