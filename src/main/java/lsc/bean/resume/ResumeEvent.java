package lsc.bean.resume;

import java.io.Serializable;
import java.util.List;

public class ResumeEvent implements Serializable {

    /**
     */
    private static final long serialVersionUID = 2934318702619103388L;
    private List<ResumeView> resumeViews;
    private List<Deliver> delivers;
    
    private List<ContentLog> contentLogs;
    private List<Talent> talents;
    private List<ResumeLog> resumeLogs;
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<ResumeView> getResumeViews() {
        return resumeViews;
    }

    public void setResumeViews(List<ResumeView> resumeViews) {
        this.resumeViews = resumeViews;
    }

    public List<ResumeLog> getResumeLogs() {
        return resumeLogs;
    }

    public void setResumeLogs(List<ResumeLog> resumeLogs) {
        this.resumeLogs = resumeLogs;
    }

    public List<Deliver> getDelivers() {
        return delivers;
    }

    public void setDelivers(List<Deliver> delivers) {
        this.delivers = delivers;
    }

    public List<ContentLog> getContentLogs() {
        return contentLogs;
    }

    public void setContentLogs(List<ContentLog> contentLogs) {
        this.contentLogs = contentLogs;
    }

    public List<Talent> getTalents() {
        return talents;
    }

    public void setTalents(List<Talent> talents) {
        this.talents = talents;
    }

}