package lsc.jdk.annotation;

import lsc.jdk.annotation.MySqlParameterField.Type;

public class CparameterFromMySql {
    
    @MySqlParameterField(key = "CREDIT_GBO_MAX_USER_CREDIT", info = "用户最大信誉值", type = Type.INTEGER)
    public static int CREDIT_GBO_MAX_USER_CREDIT = 100;

    @MySqlParameterField(key = "CREDIT_GBO_COMPLETE_CREDIT", info = "完成任务获取的信誉值", type = Type.INTEGER)
    public static int CREDIT_GBO_COMPLETE_TASK_CREDIT = 5;

}
