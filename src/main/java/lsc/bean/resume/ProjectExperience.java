package lsc.bean.resume;

import java.io.Serializable;

public class ProjectExperience implements Serializable {

    private static final long serialVersionUID = 7640301342177397935L;
    private String duty;// 职责
    private String project_name;// 项目名称
    private String etime;// 结束时间
    private String description;// 描述
    private String stime;// 开始时间
    private String env_hardware;// 硬件设备
    private String dev_tools;// 所用工具
    private String env_software;// 所用软件
    private String position;// 职位

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEnv_hardware() {
        return env_hardware;
    }

    public void setEnv_hardware(String env_hardware) {
        this.env_hardware = env_hardware;
    }

    public String getDev_tools() {
        return dev_tools;
    }

    public void setDev_tools(String dev_tools) {
        this.dev_tools = dev_tools;
    }

    public String getEnv_software() {
        return env_software;
    }

    public void setEnv_software(String env_software) {
        this.env_software = env_software;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
