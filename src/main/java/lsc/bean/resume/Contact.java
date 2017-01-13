package lsc.bean.resume;

import java.io.Serializable;

public class Contact implements Serializable {

    private static final long serialVersionUID = 292527769415309361L;

    private String mobile;// 手机号
    private String email;// 邮箱
    private String name;// 姓名
    private String tel;// 办公电话

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
