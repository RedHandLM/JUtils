package lsc.bean.resume;

import java.io.Serializable;

public class Salary implements Serializable {

    private static final long serialVersionUID = -6508894121412532692L;
    private String to;
    private String from;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

}
