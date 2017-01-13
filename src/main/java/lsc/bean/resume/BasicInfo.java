package lsc.bean.resume;

import java.io.Serializable;

public class BasicInfo implements Serializable {
    private static final long serialVersionUID = 1502313491644619106L;

    // private String talentId;// 人才id
    //
    // private String companyName;// 公司
    //
    // private String jobTitle;// 职位
    //
    // private String applyJobStatus;// 求职状态
    // private String degree;// 学历

    private String userId;// 用户id

    private String realName;// 姓名

    private String firstName;

    private String lastName;

    private String userType;// '1 : hr; 2 : hh; 3: admin; 4: agent'

    private String displayName;

    private String defunct;// '是否无效 -> 1 : true 已删除；0 : false 有效'

    private String birthday;// 生日

    private String gender;// 性别'0.女 1.男'

    private String title;

    private String mobilePhone;// 手机号

    private String account;// 账号

    private String email;// 邮箱

    private String avatar;// 头像

    private String provinceId;// 省id

    private String cityId;// 城市id

    private String address;// 所在地

    private String officePhone;// 办公电话

    private String qq;// qq号

    private String createUserId;

    private String updateUserId;

    private String createTime;

    private String updateTime;

    private String loginName;// 用户名

    private String experience;

    private String subscriptionEmail;

    private String subscriptionMobilePhone;

    private String industry;// 所属行业

    private String industryFunc;// 所属行业职能

    private String isMarried;// 婚否

    private String nature;// 政治面貌

    private String workYear;// 工作年数

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDefunct() {
        return defunct;
    }

    public void setDefunct(String defunct) {
        this.defunct = defunct;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSubscriptionEmail() {
        return subscriptionEmail;
    }

    public void setSubscriptionEmail(String subscriptionEmail) {
        this.subscriptionEmail = subscriptionEmail;
    }

    public String getSubscriptionMobilePhone() {
        return subscriptionMobilePhone;
    }

    public void setSubscriptionMobilePhone(String subscriptionMobilePhone) {
        this.subscriptionMobilePhone = subscriptionMobilePhone;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIndustryFunc() {
        return industryFunc;
    }

    public void setIndustryFunc(String industryFunc) {
        this.industryFunc = industryFunc;
    }

    public String getIsMarried() {
        return isMarried;
    }

    public void setIsMarried(String isMarried) {
        this.isMarried = isMarried;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

}
