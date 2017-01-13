package lsc.bean.resume;

import java.io.Serializable;

public class Certification implements Serializable {

    private static final long serialVersionUID = -2903859122391274840L;
    private String cer_name;// 证书名称
    private String issued;// 获得时间
    private String description;// 描述

    public String getCer_name() {
        return cer_name;
    }

    public void setCer_name(String cer_name) {
        this.cer_name = cer_name;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
