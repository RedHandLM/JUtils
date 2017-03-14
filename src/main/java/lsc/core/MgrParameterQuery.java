package lsc.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MgrParameterQuery implements Serializable {
    private static final long serialVersionUID = -7799195093065688094L;
    private List<String> parameterKeys = new ArrayList<String>();

    public List<String> getParameterKeys() {
        return this.parameterKeys;
    }

    public void setParameterKeys(List<String> parameterKeys) {
        this.parameterKeys = parameterKeys;
    }

    public void addParameterKey(String key) {
        if (!(this.parameterKeys.contains(key)))
            this.parameterKeys.add(key);
    }
}