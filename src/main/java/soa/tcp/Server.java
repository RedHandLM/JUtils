package soa.tcp;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉服务中心
 *
 * @author shichang.liu
 * @date 2017年3月14日下午3:24:01
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface Server {
    
    void stop();

    void start();

    void register(Class<?> serviceInterface, Class<?> impl);

    boolean isRunning();

    int getPort();
}
