package chiroito.infinispan;

import org.infinispan.distribution.group.Group;
import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

import java.util.Objects;

public class ProtoBufParentKey implements Comparable<ProtoBufParentKey> {

    private final int parentId;

    @ProtoFactory
    public ProtoBufParentKey(int parentId) {
        this.parentId = parentId;
    }

    @ProtoField(number = 1, defaultValue = "0")
    public int getParentId() {
        return parentId;
    }

    @Group
    public String affinity() {
        return String.valueOf(parentId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProtoBufParentKey that = (ProtoBufParentKey) o;
        return parentId == that.parentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentId);
    }

    @Override
    public String toString() {
        return "ProtoBufParentKey{ " +
                "parentId=" + parentId +
                " }";
    }

    @Override
    public int compareTo(ProtoBufParentKey o) {
        return this.parentId - o.parentId;
    }
}
