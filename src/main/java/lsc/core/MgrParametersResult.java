package lsc.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MgrParametersResult implements Serializable {
    private static final long serialVersionUID = -2054733663746480487L;
    private List<MgrParametersWord> list = new ArrayList<MgrParametersWord>(2);

    private Map<String, MgrParametersWord> map = new HashMap<String, MgrParametersWord>(2);

    public List<MgrParametersWord> getList() {
        return this.list;
    }

    public void setList(List<MgrParametersWord> list) {
        this.list = list;
        if (list != null)
            for (MgrParametersWord p : list)
                this.map.put(p.getParamKey(), p);
    }

    public Map<String, MgrParametersWord> getMap() {
        return this.map;
    }

    public void setMap(Map<String, MgrParametersWord> map) {
        this.map = map;
    }
}