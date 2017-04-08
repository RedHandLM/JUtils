package lsc.jdk.concurrent.future;

import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import com.hunteron.scheduler.api.pool.ThreadPoolFactory;

public class Demo {
    ThreadPoolExecutor executor = ThreadPoolFactory.getInstance()
            .getThreadPool();
    
    public static void main(String[] args) {
        Future<?> future = executor.submit(new Runnable() {
            public void run() {
                if (DispatcherFrontController.logger.isDebugEnabled()) {
                    DispatcherFrontController.logger.debug("Task=" + sweetTask + " begin...");
                }

                CallbackPO po = new CallbackPO();

                if (Thread.currentThread().isInterrupted()) {
                    po.setSuccess(false);
                    po.setException("interrupted");
                } else {
                    try {
                        sweetTask.execute(context);
                        po.setSuccess(true);
                    } catch (WarnningException e) {
                        DispatcherFrontController.logger.warn(e.getMessage());

                        po.setSuccess(true);
                        po.setException(e);
                    } catch (FatalException e) {
                        DispatcherFrontController.logger.error(e.getMessage());
                        po.setSuccess(false);
                        po.setException(e);
                    } catch (Throwable t) {
                        DispatcherFrontController.logger.error(t.getMessage());

                        po.setSuccess(false);
                        po.setException(t);
                    }
                }

                po.setEndTime(new Date());
                po.setAppExecNo(context.getAppExecNo());
                po.setExecNo(context.getExecNo());

                if (DispatcherFrontController.logger.isDebugEnabled()) {
                    DispatcherFrontController.logger.debug("Task=" + sweetTask + " do callback. " + po);
                }

                CallBackService cs = new CallBackService();
                try {
                    cs.callback(context.getCallbackUrl(), po);
                } catch (CallbackException e) {
                    DispatcherFrontController.logger.error(e.getMessage());
                } finally {
                    // 回调结束，释放线程
                    FuturePool.getInstance().get(DispatcherFrontController.this.createKey(context));
                }

                if (DispatcherFrontController.logger.isDebugEnabled())
                    DispatcherFrontController.logger.debug("Task=" + sweetTask + " end...");
            }
        });
    }
}
