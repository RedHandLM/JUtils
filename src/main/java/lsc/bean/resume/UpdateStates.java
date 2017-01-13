package lsc.bean.resume;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈简历更新状态实体类〉
 *
 * @author Hunteron
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class UpdateStates implements Serializable {

    private static final long serialVersionUID = -1206635134170544065L;

    private String resumeidmd5;// 简历id MD5加密
    private String userid;
    private String timeUpdate;// 是否时间有更新：1是；0否
    private String contentUpdate;// 内容是否有更新：1是；0否
    private String hasDeliverInfo;// 是否含有投递信息：1是；0否，如果是1，需要包含deliverInfo下的positionInfo不为空，至少是公司company不为空
    private Deliver deliverInfo;// 简历投递详情

    public String getResumeidmd5() {
        return resumeidmd5;
    }

    public void setResumeidmd5(String resumeidmd5) {
        this.resumeidmd5 = resumeidmd5;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(String timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    public String getContentUpdate() {
        return contentUpdate;
    }

    public void setContentUpdate(String contentUpdate) {
        this.contentUpdate = contentUpdate;
    }

    public String getHasDeliverInfo() {
        return hasDeliverInfo;
    }

    public void setHasDeliverInfo(String hasDeliverInfo) {
        this.hasDeliverInfo = hasDeliverInfo;
    }

    public Deliver getDeliverInfo() {
        return deliverInfo;
    }

    public void setDeliverInfo(Deliver deliverInfo) {
        this.deliverInfo = deliverInfo;
    }

}
