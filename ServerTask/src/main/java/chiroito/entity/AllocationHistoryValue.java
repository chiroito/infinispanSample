package chiroito.entity;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

public class AllocationHistoryValue {

    private final int num;

    @ProtoFactory
    public AllocationHistoryValue(int num) {
        this.num = num;
    }

    @ProtoField(number = 1, defaultValue = "0")
    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "AllocationHistoryValue{" +
                "num=" + num +
                '}';
    }
}
