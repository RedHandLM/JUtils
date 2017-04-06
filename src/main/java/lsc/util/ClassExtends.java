package lsc.util;

import java.util.List;

@SuppressWarnings("unused")
public class ClassExtends {

    private List<person> list;

    @SuppressWarnings("unchecked")
    private void getName(List<? extends person> l) {
        this.list = (List<person>) l;
    }

}

class person {
}

class stu extends person {
}