package lsc.bean.resume;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈简历详情实体类〉
 *
 * @author Hunteron
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ContentLog implements Serializable {

    private static final long serialVersionUID = -8276826790772765615L;
    private String id;// 日志id
    private String talentId;// 人才手机id
    private String name;// 姓名
    private String resumeId;// 简历id MD5加密
    private String mobile;// 手机号
    private String email;// 邮箱
    private String createTime;// 创建时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTalentId() {
        return talentId;
    }

    public void setTalentId(String talentId) {
        this.talentId = talentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
