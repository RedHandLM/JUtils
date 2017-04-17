/*
 * Copyright (C), 2013-2017, 猎上网
 * FileName: ZooKeeperOperation.java
 * Author:   Hunteron
 * Date:     2017年4月17日 上午9:45:28
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package zookeeper.lock;

import org.apache.zookeeper.KeeperException;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author lsc
 * @Date 2017年4月17日
 * @Time 上午9:45:28
 */
public interface ZooKeeperOperation {

    boolean execute() throws KeeperException, InterruptedException;
}
