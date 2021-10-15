package chiroito.entity;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

/**
 * キャッシュに格納するデータ
 */
public class StockEntity {

    private final int num;

    @ProtoFactory
    public StockEntity(int num) {
        this.num = num;
    }

    @ProtoField(number = 1, defaultValue = "0")
    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "StockEntity{" +
                "num=" + num +
                '}';
    }
}