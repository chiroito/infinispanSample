package chiroito.task;

import chiroito.domain.Stock;
import chiroito.entity.AllocationRequest;
import chiroito.entity.AllocationResponse;
import chiroito.entity.StockEntity;
import org.infinispan.Cache;
import org.infinispan.tasks.ServerTask;
import org.infinispan.tasks.TaskContext;
import org.infinispan.util.function.SerializableBiFunction;

import java.util.Map;

public class StockAllocationComputeWithLogicTask implements ServerTask<chiroito.entity.AllocationResponse> {

    private TaskContext context;

    @Override
    public void setTaskContext(TaskContext taskContext) {
        this.context = taskContext;
    }

    /**
     * ServerSideTask本体
     *
     * @return
     * @throws Exception
     */
    @Override
    public AllocationResponse call() throws Exception {

        try {
            // 実行時のパラメータを取得
            Map<String, ?> param = this.context.getParameters().get();
            int orderItemNo = (Integer) param.get("ItemNo");
            int orderNum = (Integer) param.get("Num");

            AllocationRequest request = new chiroito.entity.AllocationRequest(orderNum);

            // キャッシュを取得
            Cache<Integer, chiroito.entity.StockEntity> userCache = (Cache<Integer, chiroito.entity.StockEntity>) this.context.getCache().get();

            //業務処理を作成
            AllocateTask allocateTask = new AllocateTask(request);

            // 業務処理を実行
            chiroito.entity.StockEntity newCachedData = userCache.computeIfPresent(orderItemNo, allocateTask);

            return new chiroito.entity.AllocationResponse(newCachedData != null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new chiroito.entity.AllocationResponse(false);
    }

    @Override
    public String getName() {
        return "StockAllocationComputeWithLogicTask";
    }

    /**
     * キャッシュに対して行う処理
     */
    class AllocateTask implements SerializableBiFunction<Integer, chiroito.entity.StockEntity, StockEntity> {

        private final chiroito.entity.AllocationRequest request;

        public AllocateTask(chiroito.entity.AllocationRequest request) {
            this.request = request;
        }

        @Override
        public chiroito.entity.StockEntity apply(Integer userId, chiroito.entity.StockEntity cachedStockData) {

            // キャッシュされた情報からドメインオブジェクトを作成
            chiroito.domain.Stock stock = new chiroito.domain.Stock(userId, cachedStockData.getNum());

            Stock newStock = stock.allocate(request);

            // 新たにキャッシュに格納されるデータ
            return new chiroito.entity.StockEntity(newStock.getStockedNum());
        }
    }
}
