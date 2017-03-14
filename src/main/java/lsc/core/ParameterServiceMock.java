package lsc.core;

import java.util.List;

import lsc.jdk.annotation.service.ParameterService;

public class ParameterServiceMock implements ParameterService {
    public MgrParametersResult queryParameters(MgrParameterQuery query) {
        return null;
    }

    public int insertParameter(String key, Object value, String info) {
        return 0;
    }

    @Override
    public void updateParamMethod(List<MethodBean> list) {
    }

}