package lsc.bean.resume;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈人才实体类〉
 *
 * @author Hunteron
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Talent implements Serializable {

    private static final long serialVersionUID = 107602429871535345L;

    private String id;// 人才id

    private String mobileMd5Name;// 手机号MD5+姓名

    private String outerKey;// 外部简历id

    private String resumeId;// 最新简历id mobilemd5+日期

    private String mobilemd5;// 联系电话

    private String createTime;// 创建时间

    private String updateTime; // 更新时间

    private String expectIndustryIds;// 期望行业

    private String expectFunctionIds;// 期望职能

    private String expectCityIds;// 期望城市

    private String keywords;// 关键字

    private Double expectAnnualsalaryMin;// 最小期望年薪

    private Double expectAnnualsalaryMax;// 最大期望年薪

    private Integer degree;// 学历

    private Integer check;

    public String getMobileMd5Name() {
        return mobileMd5Name;
    }

    public void setMobileMd5Name(String mobileMd5Name) {
        this.mobileMd5Name = mobileMd5Name;
    }

    public String getOuterKey() {
        return outerKey;
    }

    public void setOuterKey(String outerKey) {
        this.outerKey = outerKey;
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public String getId() {
        return id;
    }

    public String getExpectIndustryIds() {
        return expectIndustryIds;
    }

    public void setExpectIndustryIds(String expectIndustryIds) {
        this.expectIndustryIds = expectIndustryIds;
    }

    public String getExpectFunctionIds() {
        return expectFunctionIds;
    }

    public void setExpectFunctionIds(String expectFunctionIds) {
        this.expectFunctionIds = expectFunctionIds;
    }

    public String getExpectCityIds() {
        return expectCityIds;
    }

    public void setExpectCityIds(String expectCityIds) {
        this.expectCityIds = expectCityIds;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobilemd5() {
        return mobilemd5;
    }

    public void setMobilemd5(String mobilemd5) {
        this.mobilemd5 = mobilemd5;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Double getExpectAnnualsalaryMin() {
        return expectAnnualsalaryMin;
    }

    public void setExpectAnnualsalaryMin(Double expectAnnualsalaryMin) {
        this.expectAnnualsalaryMin = expectAnnualsalaryMin;
    }

    public Double getExpectAnnualsalaryMax() {
        return expectAnnualsalaryMax;
    }

    public void setExpectAnnualsalaryMax(Double expectAnnualsalaryMax) {
        this.expectAnnualsalaryMax = expectAnnualsalaryMax;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

}
