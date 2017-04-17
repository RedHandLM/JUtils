/*
 * Copyright (C), 2013-2017, 猎上网
 * FileName: LockListener.java
 * Author:   Hunteron
 * Date:     2017年4月17日 上午10:35:33
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package zookeeper.lock;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author lsc
 * @Date 2017年4月17日
 * @Time 上午10:35:33
 */
public interface LockListener {

    void lockAcquired();

    void lockReleased();

}
