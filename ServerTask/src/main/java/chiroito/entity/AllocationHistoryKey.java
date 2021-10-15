package chiroito.entity;

import org.infinispan.distribution.group.Group;
import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

import java.util.Objects;

public class AllocationHistoryKey {

    private String itemId;
    private String historyId;

    @ProtoFactory
    public AllocationHistoryKey(String itemId, String historyId) {
        this.itemId = itemId;
        this.historyId = historyId;
    }

    @ProtoField(number = 1)
    public String getItemId() {
        return itemId;
    }

    @ProtoField(number = 2)
    public String getHistoryId() {
        return historyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllocationHistoryKey that = (AllocationHistoryKey) o;
        return itemId.equals(that.itemId) && historyId.equals(that.historyId);
    }

    @Group
    public String affinity(){
        return String.valueOf(itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, historyId);
    }

    @Override
    public String toString() {
        return "AllocationHistoryKey{" +
                "itemId=" + itemId +
                ", historyId='" + historyId + '\'' +
                '}';
    }
}
