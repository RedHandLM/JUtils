package lsc.jdk.concurrent.countdownlatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBatchOperation<T> {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 一批执行的数量
     */
    private static final int NUM_BATCH = 2000;// 一批次执行数量

    /**
     * 功能描述: 获取需要处理的数据总数<br>
     * 
     * @return int
     */
    protected abstract int getDataTotal();

    /**
     * 功能描述: 批量获取需要处理的数据<br>
     *
     * @param start
     * @param maxRows
     * @return List<T>
     */
    protected abstract List<T> getBatchDatas(int start, int maxRows);

    /**
     * 功能描述: 批量处理数据<br>
     *
     * @param data
     */
    protected abstract void dealBatchData(List<T> data);

    protected long execute(Executor exec, List<T> data) {
        long taskBeginTime = System.currentTimeMillis();

        if (CollectionUtils.isNotEmpty(data)) {
            dealBatchData(data);
            return System.currentTimeMillis() - taskBeginTime;
        }

        int taskThreadTotal = getTaskThreadTotal();
        if (taskThreadTotal <= 0) {
            return System.currentTimeMillis() - taskBeginTime;
        }

        CountDownLatch latch = new CountDownLatch(taskThreadTotal);
        for (int i = 0; i < taskThreadTotal; i++) {
            List<T> datas = getBatchDatas(i * NUM_BATCH, NUM_BATCH);
            asynDealDatas(exec, latch, datas);
        }
        try {
            latch.await(1, TimeUnit.HOURS);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        long spendTime = System.currentTimeMillis() - taskBeginTime;
        log.debug("task success,spend time :" + spendTime + "ms");
        return 0;

    }

    private int getTaskThreadTotal() {
        return (getDataTotal() / NUM_BATCH) + 1;
    }

    private void asynDealDatas(Executor exec, final CountDownLatch latch, final List<T> datas) {
        exec.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    dealBatchData(datas);
                } catch (Throwable e) {
                    log.error(e.getMessage(), e);
                } finally {
                    latch.countDown();
                }
            }
        });
    }

}
