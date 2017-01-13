package lsc.bean.resume;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈投递记录实体类〉
 *
 * @author Hunteron
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Deliver implements Serializable {

    private static final long serialVersionUID = 1123311356721438385L;
    // private String id;// 投递id
    private String talentId;// 人才id
    private String disp_time;// 简历投递时间，时间戳（外部简历投递时间）
    private String update_time;// 更新时间（外部简历更新时间）
    private PositionInfo positionInfo;// 职位详情
    private String createTime;// 创建时间

    // public String getId() {
    // return id;
    // }
    //
    // public void setId(String id) {
    // this.id = id;
    // }

    public String getTalentId() {
        return talentId;
    }

    public void setTalentId(String talentId) {
        this.talentId = talentId;
    }

    public String getDisp_time() {
        return disp_time;
    }

    public void setDisp_time(String disp_time) {
        this.disp_time = disp_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public PositionInfo getPositionInfo() {
        return positionInfo;
    }

    public void setPositionInfo(PositionInfo positionInfo) {
        this.positionInfo = positionInfo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
