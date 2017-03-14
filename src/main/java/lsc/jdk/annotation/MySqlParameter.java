package lsc.jdk.annotation;

public interface MySqlParameter {

    public String getKey();

    public void setKey(String key);

    public Object getValue();

    public void setValue(Object value);

    public String getInfo();

    public void setInfo(String info);

    // 增加参数分组、项目概念
    // 参数命名规范，过渡方案，最终命名规范：name1.name2.name3.name4
    public String getParameterName();

    public void setParameterName(String name);

    public String getProjectName();

    public void setProjectName(String name);

}
