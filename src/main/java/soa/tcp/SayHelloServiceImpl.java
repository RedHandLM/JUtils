package soa.tcp;

/**
 * 
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉服务实现类
 *
 * @author shichang.liu
 * @date 2017年3月14日上午11:25:52
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SayHelloServiceImpl implements SayHelloService {

    @Override
    public String sayHello(String arg) {
        if ("Hello".equals(arg)) {
            return "你好";
        }else{
            return "再见";
        }
    }

}
