package chiroito.task;

import org.infinispan.Cache;
import org.infinispan.tasks.ServerTask;
import org.infinispan.tasks.TaskContext;
import org.infinispan.util.function.SerializableBiFunction;

import java.util.Map;

public class StockAllocationComputeWithLogicTask implements ServerTask<Boolean> {

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
    public Boolean call() throws Exception {

        try {
            // 実行時のパラメータを取得
            Map<String, ?> param = this.context.getParameters().get();
            int orderItemNo = (Integer) param.get("ItemNo");
            int orderNum = (Integer) param.get("Num");

            AllocationRequest request = new AllocationRequest(orderNum);

            // キャッシュを取得
            Cache<Integer, StockEntity> userCache = (Cache<Integer, StockEntity>) this.context.getCache().get();

            //業務処理を作成
            AllocateTask allocateTask = new AllocateTask(request);

            // 業務処理を実行
            StockEntity newCachedData = userCache.computeIfPresent(orderItemNo, allocateTask);

            return newCachedData != null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getName() {
        return "StockAllocationComputeWithLogicTask";
    }

    /**
     * キャッシュに対して行う処理
     */
    class AllocateTask implements SerializableBiFunction<Integer, StockEntity, StockEntity> {

        private final AllocationRequest request;

        public AllocateTask(AllocationRequest request) {
            this.request = request;
        }

        @Override
        public StockEntity apply(Integer userId, StockEntity cachedStockData) {

            // キャッシュされた情報からドメインオブジェクトを作成
            Stock stock = new Stock(userId, cachedStockData.getNum());

            Stock newStock = stock.allocate(request);

            // 新たにキャッシュに格納されるデータ
            return new StockEntity(newStock.stockedNum);
        }
    }


    /**
     * ドメイン処理
     */
    class Stock {

        private final int itemId;
        private int stockedNum;

        public Stock(int itemId, int stockedNum) {
            this.itemId = itemId;
            this.stockedNum = stockedNum;
        }

        public Stock allocate(AllocationRequest req) {

            if (stockedNum < req.num) {
                throw new RuntimeException("在庫が足りません");
            }

            return new Stock(itemId, stockedNum - req.num);
        }
    }

    /**
     * ドメインのバリューオブジェクト
     */
    class AllocationRequest {

        public final int num;

        public AllocationRequest(int num) {
            this.num = num;
        }
    }
}
