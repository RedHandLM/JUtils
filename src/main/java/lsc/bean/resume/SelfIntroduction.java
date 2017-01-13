package lsc.bean.resume;

import java.io.Serializable;

public class SelfIntroduction implements Serializable {
    private static final long serialVersionUID = -4893983821997807130L;

    private String content;// 个人简介描述
    private String type;// 类型

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
