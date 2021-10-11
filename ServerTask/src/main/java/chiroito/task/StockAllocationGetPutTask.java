package chiroito.task;

import org.infinispan.Cache;
import org.infinispan.tasks.ServerTask;
import org.infinispan.tasks.TaskContext;

import java.util.Map;

/**
 * 指定された商品の在庫を減らす処理
 * 在庫数が正しく減っていかないため、この様な実装は避けるべきです。
 * 正しい実装方法はStockAllocationConputeTaskを参照して下さい
 *
 * @see StockAllocationComputeTask
 */
public class StockAllocationGetPutTask implements ServerTask<Boolean> {

    private TaskContext context;

    @Override
    public void setTaskContext(TaskContext taskContext) {
        this.context = taskContext;
    }

    @Override
    public Boolean call() throws Exception {

        try {
            //起動時のパラメータを取得
            Map<String, ?> param = this.context.getParameters().get();
            int orderItemNo = (Integer) param.get("ItemNo");
            int orderNum = (Integer) param.get("Num");

            //キャッシュを取得
            Cache<Integer, Integer> cache = (Cache<Integer, Integer>) this.context.getCache().get();

            //業務処理
            Integer stockedNum = cache.get(orderItemNo);
            Integer remainStokedNum = cache.put(orderItemNo, stockedNum - orderNum);

            return remainStokedNum >= 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getName() {
        return "StockAllocationGetPutTask";
    }
}
