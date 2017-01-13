package lsc.bean.resume;

import java.io.Serializable;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈巧达简历实体类〉
 *
 * @author Hunteron
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ResumeView implements Serializable {
    private static final long serialVersionUID = 7037271528606532951L;
    private String id;// 简历id

    private BasicInfo basicInfo;// 基本信息

    private JobhunterView jobhunterView;// jobhunter信息

    private String talentId;// 人才id

    private String outKey;// 外部简历id

    private String orderId;// 订单id

    private String jobid;// 职位id

    private String mobilemd5;// 手机号MD5加密

    private List<JobhunterWorkExp> workExperience;// 工作经历

    private List<JobhunterProjectExp> projectExperience;// 项目经历

    private List<JobhunterEduExp> educationExperience;// 教育经历

    private List<LanguageAbility> languageAbility;// 掌握语言

    private List<TrainExperience> trainExperience;// 培训经历

    private List<Certification> certification;// 等级证书

    private UpdateStates updateStates; // 简历更新状态

    private Integer resourceType;// 简历来源

    private String createTime;// 创建时间

    private String updateDate;// 简历更新时间

    public String getTalentId() {
        return talentId;
    }

    public void setTalentId(String talentId) {
        this.talentId = talentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public JobhunterView getJobhunterView() {
        return jobhunterView;
    }

    public void setJobhunterView(JobhunterView jobhunterView) {
        this.jobhunterView = jobhunterView;
    }

    public String getOutKey() {
        return outKey;
    }

    public void setOutKey(String outKey) {
        this.outKey = outKey;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getMobilemd5() {
        return mobilemd5;
    }

    public void setMobilemd5(String mobilemd5) {
        this.mobilemd5 = mobilemd5;
    }

    public List<JobhunterWorkExp> getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(List<JobhunterWorkExp> workExperience) {
        this.workExperience = workExperience;
    }

    public List<JobhunterProjectExp> getProjectExperience() {
        return projectExperience;
    }

    public void setProjectExperience(List<JobhunterProjectExp> projectExperience) {
        this.projectExperience = projectExperience;
    }

    public List<JobhunterEduExp> getEducationExperience() {
        return educationExperience;
    }

    public void setEducationExperience(List<JobhunterEduExp> educationExperience) {
        this.educationExperience = educationExperience;
    }

    public List<LanguageAbility> getLanguageAbility() {
        return languageAbility;
    }

    public void setLanguageAbility(List<LanguageAbility> languageAbility) {
        this.languageAbility = languageAbility;
    }

    public List<TrainExperience> getTrainExperience() {
        return trainExperience;
    }

    public void setTrainExperience(List<TrainExperience> trainExperience) {
        this.trainExperience = trainExperience;
    }

    public List<Certification> getCertification() {
        return certification;
    }

    public void setCertification(List<Certification> certification) {
        this.certification = certification;
    }

    public UpdateStates getUpdateStates() {
        return updateStates;
    }

    public void setUpdateStates(UpdateStates updateStates) {
        this.updateStates = updateStates;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
