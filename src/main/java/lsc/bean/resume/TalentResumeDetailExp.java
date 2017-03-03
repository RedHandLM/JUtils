package lsc.bean.resume;

import java.util.List;

public class TalentResumeDetailExp implements java.io.Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private Long talentId;
    private Long resumeId;
    private Integer integrity;
    private String sourceUrl;
    private List<TalentResumeWorkExp> workExps;
    private List<TalentResumeProjectExp> projectExps;
    private List<TalentResumeEduExp> eduExps;
    private List<TalentResumeCertification> certifications;
    private List<TalentResumeLanguageAbility> languageAbilitys;
    private List<TalentResumeTrainExp> trainExps;

    public Long getTalentId() {
        return talentId;
    }

    public void setTalentId(Long talentId) {
        this.talentId = talentId;
    }

    public List<TalentResumeWorkExp> getWorkExps() {
        return workExps;
    }

    public void setWorkExps(List<TalentResumeWorkExp> workExps) {
        this.workExps = workExps;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public List<TalentResumeProjectExp> getProjectExps() {
        return projectExps;
    }

    public void setProjectExps(List<TalentResumeProjectExp> projectExps) {
        this.projectExps = projectExps;
    }

    public List<TalentResumeEduExp> getEduExps() {
        return eduExps;
    }

    public void setEduExps(List<TalentResumeEduExp> eduExps) {
        this.eduExps = eduExps;
    }

    public Integer getIntegrity() {
        return integrity;
    }

    public void setIntegrity(Integer integrity) {
        this.integrity = integrity;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public List<TalentResumeCertification> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<TalentResumeCertification> certifications) {
        this.certifications = certifications;
    }

    public List<TalentResumeLanguageAbility> getLanguageAbilitys() {
        return languageAbilitys;
    }

    public void setLanguageAbilitys(List<TalentResumeLanguageAbility> languageAbilitys) {
        this.languageAbilitys = languageAbilitys;
    }

    public List<TalentResumeTrainExp> getTrainExps() {
        return trainExps;
    }

    public void setTrainExps(List<TalentResumeTrainExp> trainExps) {
        this.trainExps = trainExps;
    }
}
