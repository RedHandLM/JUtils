/*
 * Copyright (C), 2013-2017, 猎上网
 * FileName: ZNodeName.java
 * Author:   Hunteron
 * Date:     2017年4月17日 上午10:09:17
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package zookeeper.lock;

import lsc.util.StringTools;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author lsc
 * @Date 2017年4月17日
 * @Time 上午10:09:17
 */
public class ZNodeName implements Comparable<ZNodeName> {
    private final String name;
    private String prefix;
    private int sequence = -1;

    public int getZNodeName() {
        return sequence;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public ZNodeName(String name) {
        if (StringTools.isBlank(name)) {
            throw new NullPointerException("id cannot be null");
        }
        this.name = name;
        this.prefix = name;
        int idx = name.lastIndexOf('_');
        if (idx >= 0) {
            this.prefix = name.substring(0, idx);
            this.sequence = StringTools.getInt(name.substring(idx + 1), 0);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ZNodeName sequence = (ZNodeName) o;
        if (!name.equals(sequence.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        if (StringTools.isBlank(name)) {
            return 0;
        }
        return name.hashCode() + 37;

    }

    @Override
    public int compareTo(ZNodeName that) {
        int s1 = this.sequence;
        int s2 = that.sequence;
        if (s1 == -1 && s2 == -1) {
            return this.name.compareTo(that.name);
        }
        if (s1 == -1) {
            return -1;
        } else if (s2 == -1) {
            return 1;
        } else {
            return s1 - s2;
        }
    }

}
