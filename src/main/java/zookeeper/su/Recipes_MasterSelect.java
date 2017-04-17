package zookeeper.su;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉使用Curator简单实现master选举
 *-
 * @author shichang.liu
 * @date 2017年4月17日下午4:42:31
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Recipes_MasterSelect {

    static String master_path = "/curator_recipes_master_path";

    static CuratorFramework client = LockMain.CreateSession();

    public static void main(String[] args) {
        try {
            client.start();
            // LeaderSelector 封装了所有和leader选举的相关逻辑
            LeaderSelector selector = new LeaderSelector(client, master_path, new LeaderSelectorListenerAdapter() {
                // takeLeadership 一旦执行完此方法 curator就会释放master权利 重新开始下一轮master选举
                @Override
                public void takeLeadership(CuratorFramework client) throws Exception {
                    System.out.println("成为Master角色");
                    Thread.sleep(3000);
                    System.out.println("完成Master操作，释放Master权利");
                }
            });
            selector.autoRequeue();
            selector.start();
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
