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
public class Resume implements Serializable {
    private static final long serialVersionUID = 6341741491630570520L;
    private String id;// 简历id
    private String resumeId;// 简历id
    private String resumeidmd5;// 简历id MD5加密
    private String name;// 简历名称
    private String spreadInfoId;// 订单id
    private String updateDate;// 简历更新时间
    private String jobid;// 职位id
    private String sex;// 性别
    private String birthday;// 生日
    private String workYear;// 工作年数
    private String degree;// 学历
    private String isMarried;// 婚否
    private String currentArea;// 现居地
    private String homePlace;// 户籍所在地
    private String nature;// 政治面貌
    private Contact contact;// 个人信息
    private List<SelfIntroduction> selfIntroduction;// 自我评价
    private List<WorkExperience> workExperience;// 工作经历
    private List<ProjectExperience> projectExperience;// 项目经历
    private List<EducationExperience> educationExperience;// 教育经历
    private List<LanguageAbility> languageAbility;// 掌握语言
    private List<SkillAbility> skillAbility;// 技能
    private List<TrainExperience> trainExperience;// 培训经历
    private JobIntension jobIntension;// 期望职位
    private List<Certification> certification;// 等级证书
    private String mobilemd5;// 联系电话
    private UpdateStates updateStates; // 简历更新状态

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public String getResumeidmd5() {
        return resumeidmd5;
    }

    public void setResumeidmd5(String resumeidmd5) {
        this.resumeidmd5 = resumeidmd5;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public String getSpreadInfoId() {
        return spreadInfoId;
    }

    public void setSpreadInfoId(String spreadInfoId) {
        this.spreadInfoId = spreadInfoId;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getIsMarried() {
        return isMarried;
    }

    public void setIsMarried(String isMarried) {
        this.isMarried = isMarried;
    }

    public String getCurrentArea() {
        return currentArea;
    }

    public void setCurrentArea(String currentArea) {
        this.currentArea = currentArea;
    }

    public String getHomePlace() {
        return homePlace;
    }

    public void setHomePlace(String homePlace) {
        this.homePlace = homePlace;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public List<SelfIntroduction> getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(List<SelfIntroduction> selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    public List<WorkExperience> getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(List<WorkExperience> workExperience) {
        this.workExperience = workExperience;
    }

    public List<ProjectExperience> getProjectExperience() {
        return projectExperience;
    }

    public void setProjectExperience(List<ProjectExperience> projectExperience) {
        this.projectExperience = projectExperience;
    }

    public List<EducationExperience> getEducationExperience() {
        return educationExperience;
    }

    public void setEducationExperience(List<EducationExperience> educationExperience) {
        this.educationExperience = educationExperience;
    }

    public List<LanguageAbility> getLanguageAbility() {
        return languageAbility;
    }

    public void setLanguageAbility(List<LanguageAbility> languageAbility) {
        this.languageAbility = languageAbility;
    }

    public List<SkillAbility> getSkillAbility() {
        return skillAbility;
    }

    public void setSkillAbility(List<SkillAbility> skillAbility) {
        this.skillAbility = skillAbility;
    }

    public List<TrainExperience> getTrainExperience() {
        return trainExperience;
    }

    public void setTrainExperience(List<TrainExperience> trainExperience) {
        this.trainExperience = trainExperience;
    }

    public JobIntension getJobIntension() {
        return jobIntension;
    }

    public void setJobIntension(JobIntension jobIntension) {
        this.jobIntension = jobIntension;
    }

    public List<Certification> getCertification() {
        return certification;
    }

    public void setCertification(List<Certification> certification) {
        this.certification = certification;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getMobilemd5() {
        return mobilemd5;
    }

    public void setMobilemd5(String mobilemd5) {
        this.mobilemd5 = mobilemd5;
    }

    public UpdateStates getUpdateStates() {
        return updateStates;
    }

    public void setUpdateStates(UpdateStates updateStates) {
        this.updateStates = updateStates;
    }
}
