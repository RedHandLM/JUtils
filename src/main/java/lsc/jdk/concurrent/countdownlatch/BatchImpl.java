package lsc.jdk.concurrent.countdownlatch;

import java.util.ArrayList;
import java.util.List;

public class BatchImpl extends AbstractBatchOperation<Long> {

    @Override
    protected int getDataTotal() {
        //获取需要处理数据的总数量
        return 0;
    }

    @Override
    protected List<Long> getBatchDatas(int start, int maxRows) {
        //批量获取数据的ID
        return new ArrayList<Long>();
    }

    @Override
    protected void dealBatchData(List<Long> data) {
        //批量处理数据  实际处理逻辑
    }

}
