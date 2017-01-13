package lsc.bean.resume;

import java.io.Serializable;

public class LanguageAbility implements Serializable {

    private static final long serialVersionUID = 8841133633529060852L;
    private String verbal;// 口语
    private String language;// 语种
    private String literacy;// 读写

    public String getVerbal() {
        return verbal;
    }

    public void setVerbal(String verbal) {
        this.verbal = verbal;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLiteracy() {
        return literacy;
    }

    public void setLiteracy(String literacy) {
        this.literacy = literacy;
    }

}
