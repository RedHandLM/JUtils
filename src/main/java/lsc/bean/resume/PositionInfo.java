package lsc.bean.resume;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈职位详情实体类〉
 *
 * @author Hunteron
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class PositionInfo implements Serializable {
    private static final long serialVersionUID = 2952777068650303177L;
    private String company;// 公司名称
    private String location;// 工作地点
    private String position;// 职位名称
    private String location_cnt;// 工作地点原文

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLocation_cnt() {
        return location_cnt;
    }

    public void setLocation_cnt(String location_cnt) {
        this.location_cnt = location_cnt;
    }

}
