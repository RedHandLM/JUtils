package design_pattern.factory.abstract_factory.engine.impl;

import design_pattern.factory.abstract_factory.engine.Engine;

/**
 * 
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉具体产品类
 *
 * @author shichang.liu
 * @date 2017年4月20日下午6:23:45
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Engine_Domestic implements Engine {

    @Override
    public void work() {
        System.out.println("国产发动机");
    }

}
