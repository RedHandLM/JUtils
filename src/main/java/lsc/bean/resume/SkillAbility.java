package lsc.bean.resume;

import java.io.Serializable;

public class SkillAbility implements Serializable {

    private static final long serialVersionUID = -697843668126800847L;
    private String level;// 熟练程度
    private String skill;// 技能名称
    private String how_long;// 时间

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getHow_long() {
        return how_long;
    }

    public void setHow_long(String how_long) {
        this.how_long = how_long;
    }

}
