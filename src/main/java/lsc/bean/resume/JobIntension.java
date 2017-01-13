package lsc.bean.resume;

import java.io.Serializable;

public class JobIntension implements Serializable {

    private static final long serialVersionUID = -3260689840390705472L;

    private Salary salary;// 薪水
    private String[] city;// 城市
    private String job_nature;// 职位类型
    private String state;// 求职状态
    private String[] trade;// 求职意向
    private String arrival_time;// 到岗时间
    private String[] job;// 期望职位

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public String getJob_nature() {
        return job_nature;
    }

    public void setJob_nature(String job_nature) {
        this.job_nature = job_nature;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String[] getCity() {
        return city;
    }

    public void setCity(String[] city) {
        this.city = city;
    }

    public String[] getTrade() {
        return trade;
    }

    public void setTrade(String[] trade) {
        this.trade = trade;
    }

    public String[] getJob() {
        return job;
    }

    public void setJob(String[] job) {
        this.job = job;
    }
}
