package lsc.core;

import java.io.Serializable;
import java.util.List;

public class MethodBean implements Serializable {

    /**
     */
    private static final long serialVersionUID = -3449575740905823716L;

    private long id;

    private String projectName;

    private String beanName;

    private String methodName;

    private Long version = 0L;

    private String methodDescribtion;

    private List<ParamBean> params;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<ParamBean> getParams() {
        return params;
    }

    public void setParams(List<ParamBean> params) {
        this.params = params;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMethodDescribtion() {
        return methodDescribtion;
    }

    public void setMethodDescribtion(String methodDescribtion) {
        this.methodDescribtion = methodDescribtion;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}