package lsc.bean.resume;

import java.io.Serializable;
import java.util.Date;

public class TalentResumeTrainExp implements Serializable {
    private Long id;

    private Long resumeId;

    private String cerName;

    private String agencyName;

    private String description;

    private Integer startYear;

    private Integer endYear;

    private Integer startMonth;

    private Integer endMonth;

    private Date createTime;

    private Long createUserId;

    private Date updateTime;

    private Long updateUserId;

    private Boolean defunct;

    private String course;

    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_talent_resume_train_exp
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_talent_resume_train_exp.id
     *
     * @return the value of tb_talent_resume_train_exp.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_talent_resume_train_exp.id
     *
     * @param id the value for tb_talent_resume_train_exp.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_talent_resume_train_exp.resume_id
     *
     * @return the value of tb_talent_resume_train_exp.resume_id
     *
     * @mbggenerated
     */
    public Long getResumeId() {
        return resumeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_talent_resume_train_exp.resume_id
     *
     * @param resumeId the value for tb_talent_resume_train_exp.resume_id
     *
     * @mbggenerated
     */
    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_talent_resume_train_exp.cer_name
     *
     * @return the value of tb_talent_resume_train_exp.cer_name
     *
     * @mbggenerated
     */
    public String getCerName() {
        return cerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_talent_resume_train_exp.cer_name
     *
     * @param cerName the value for tb_talent_resume_train_exp.cer_name
     *
     * @mbggenerated
     */
    public void setCerName(String cerName) {
        this.cerName = cerName == null ? null : cerName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_talent_resume_train_exp.agency_name
     *
     * @return the value of tb_talent_resume_train_exp.agency_name
     *
     * @mbggenerated
     */
    public String getAgencyName() {
        return agencyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_talent_resume_train_exp.agency_name
     *
     * @param agencyName the value for tb_talent_resume_train_exp.agency_name
     *
     * @mbggenerated
     */
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName == null ? null : agencyName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_talent_resume_train_exp.description
     *
     * @return the value of tb_talent_resume_train_exp.description
     *
     * @mbggenerated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_talent_resume_train_exp.description
     *
     * @param description the value for tb_talent_resume_train_exp.description
     *
     * @mbggenerated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_talent_resume_train_exp.start_year
     *
     * @return the value of tb_talent_resume_train_exp.start_year
     *
     * @mbggenerated
     */
    public Integer getStartYear() {
        return startYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_talent_resume_train_exp.start_year
     *
     * @param startYear the value for tb_talent_resume_train_exp.start_year
     *
     * @mbggenerated
     */
    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_talent_resume_train_exp.end_year
     *
     * @return the value of tb_talent_resume_train_exp.end_year
     *
     * @mbggenerated
     */
    public Integer getEndYear() {
        return endYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_talent_resume_train_exp.end_year
     *
     * @param endYear the value for tb_talent_resume_train_exp.end_year
     *
     * @mbggenerated
     */
    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_talent_resume_train_exp.start_month
     *
     * @return the value of tb_talent_resume_train_exp.start_month
     *
     * @mbggenerated
     */
    public Integer getStartMonth() {
        return startMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_talent_resume_train_exp.start_month
     *
     * @param startMonth the value for tb_talent_resume_train_exp.start_month
     *
     * @mbggenerated
     */
    public void setStartMonth(Integer startMonth) {
        this.startMonth = startMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_talent_resume_train_exp.end_month
     *
     * @return the value of tb_talent_resume_train_exp.end_month
     *
     * @mbggenerated
     */
    public Integer getEndMonth() {
        return endMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_talent_resume_train_exp.end_month
     *
     * @param endMonth the value for tb_talent_resume_train_exp.end_month
     *
     * @mbggenerated
     */
    public void setEndMonth(Integer endMonth) {
        this.endMonth = endMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_talent_resume_train_exp.create_time
     *
     * @return the value of tb_talent_resume_train_exp.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_talent_resume_train_exp.create_time
     *
     * @param createTime the value for tb_talent_resume_train_exp.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_talent_resume_train_exp.create_user_id
     *
     * @return the value of tb_talent_resume_train_exp.create_user_id
     *
     * @mbggenerated
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_talent_resume_train_exp.create_user_id
     *
     * @param createUserId the value for tb_talent_resume_train_exp.create_user_id
     *
     * @mbggenerated
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_talent_resume_train_exp.update_time
     *
     * @return the value of tb_talent_resume_train_exp.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_talent_resume_train_exp.update_time
     *
     * @param updateTime the value for tb_talent_resume_train_exp.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_talent_resume_train_exp.update_user_id
     *
     * @return the value of tb_talent_resume_train_exp.update_user_id
     *
     * @mbggenerated
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_talent_resume_train_exp.update_user_id
     *
     * @param updateUserId the value for tb_talent_resume_train_exp.update_user_id
     *
     * @mbggenerated
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_talent_resume_train_exp.defunct
     *
     * @return the value of tb_talent_resume_train_exp.defunct
     *
     * @mbggenerated
     */
    public Boolean getDefunct() {
        return defunct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_talent_resume_train_exp.defunct
     *
     * @param defunct the value for tb_talent_resume_train_exp.defunct
     *
     * @mbggenerated
     */
    public void setDefunct(Boolean defunct) {
        this.defunct = defunct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_talent_resume_train_exp.course
     *
     * @return the value of tb_talent_resume_train_exp.course
     *
     * @mbggenerated
     */
    public String getCourse() {
        return course;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_talent_resume_train_exp.course
     *
     * @param course the value for tb_talent_resume_train_exp.course
     *
     * @mbggenerated
     */
    public void setCourse(String course) {
        this.course = course == null ? null : course.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_talent_resume_train_exp.address
     *
     * @return the value of tb_talent_resume_train_exp.address
     *
     * @mbggenerated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_talent_resume_train_exp.address
     *
     * @param address the value for tb_talent_resume_train_exp.address
     *
     * @mbggenerated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}