package lsc.bean.resume;

import java.io.Serializable;

public class WorkExperience implements Serializable {
    private static final long serialVersionUID = -1767624901821831397L;

    private String job_description;// 工作描述
    private String subordinate;// 下级
    private String etime;// 结束时间
    private String report_to;// 直接上级
    private String company_type;// 公司类型
    private String salary_from;// 最低薪资
    private String trade;// 行业
    private String stime;// 开始时间
    private String company;// 公司名称
    private String performance;// 绩效
    private String department;// 部门
    private String job;// 职位
    private String immediate_subordinate;// 直接下级
    private String salary_to;// 最高薪资
    private String company_size_cnt;// 公司人数
    private String achievements;// 业绩

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public String getSubordinate() {
        return subordinate;
    }

    public void setSubordinate(String subordinate) {
        this.subordinate = subordinate;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getReport_to() {
        return report_to;
    }

    public void setReport_to(String report_to) {
        this.report_to = report_to;
    }

    public String getCompany_type() {
        return company_type;
    }

    public void setCompany_type(String company_type) {
        this.company_type = company_type;
    }

    public String getSalary_from() {
        return salary_from;
    }

    public void setSalary_from(String salary_from) {
        this.salary_from = salary_from;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getImmediate_subordinate() {
        return immediate_subordinate;
    }

    public void setImmediate_subordinate(String immediate_subordinate) {
        this.immediate_subordinate = immediate_subordinate;
    }

    public String getSalary_to() {
        return salary_to;
    }

    public void setSalary_to(String salary_to) {
        this.salary_to = salary_to;
    }

    public String getCompany_size_cnt() {
        return company_size_cnt;
    }

    public void setCompany_size_cnt(String company_size_cnt) {
        this.company_size_cnt = company_size_cnt;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

}
