package lsc.core;

import java.io.Serializable;
import java.util.Date;

import lsc.jdk.annotation.MySqlParameter;

public class MgrParametersWord implements MySqlParameter, Serializable {
    private static final long serialVersionUID = -1351071016615689521L;
    private long id;
    private String paramKey;
    private String info;
    private Integer type;
    private String value;
    private Date addTime;
    private Long lastUpdate;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getAddTime() {
        return this.addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getParamKey() {
        return this.paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getKey() {
        return this.paramKey;
    }

    public void setKey(String key) {
    }

    public void setValue(Object value) {
    }

    @Override
    public String getParameterName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setParameterName(String name) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getProjectName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setProjectName(String name) {
        // TODO Auto-generated method stub

    }
}