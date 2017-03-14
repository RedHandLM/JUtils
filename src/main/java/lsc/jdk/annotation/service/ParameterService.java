package lsc.jdk.annotation.service;

import java.util.List;

import lsc.core.MethodBean;
import lsc.core.MgrParameterQuery;
import lsc.core.MgrParametersResult;

public interface ParameterService {
    public abstract MgrParametersResult queryParameters(MgrParameterQuery paramMgrParameterQuery);

    public abstract int insertParameter(String paramString1, Object paramObject, String paramString2);

    public abstract void updateParamMethod(List<MethodBean> paramList);

    
}
