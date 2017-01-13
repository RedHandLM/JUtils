package lsc.bean.resume;

import java.io.Serializable;

public class TrainExperience implements Serializable {

    private static final long serialVersionUID = 6984838069729644507L;
    private String stime;// 开始时间
    private String etime;// 结束时间
    private String course;// 课程名称
    private String agency;// 培训机构名称
    private String address;// 地址
    private String certificate;// 获得证书
    private String description;// 描述

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
