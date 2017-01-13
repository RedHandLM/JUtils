package lsc.bean.resume;

import java.io.Serializable;

public class EducationExperience implements Serializable {

    private static final long serialVersionUID = 6877932621689668606L;

    private String school;// 学校
    private String etime;// 结束时间
    private String speciality;// 专业
    private String degree;// 学历
    private String stime;// 开始时间
    private String description;// 描述

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
