package chiroito.domain;

import chiroito.entity.AllocationRequest;

public class Stock {

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

    public int getStockedNum() {
        return stockedNum;
    }
}
