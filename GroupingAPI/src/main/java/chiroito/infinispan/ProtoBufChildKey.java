package chiroito.infinispan;

import org.infinispan.distribution.group.Group;
import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

import java.util.Objects;

public class ProtoBufChildKey implements Comparable<ProtoBufChildKey> {

    private final int parentId;
    private final int branchNumber;

    @ProtoFactory
    public ProtoBufChildKey(int parentId, int branchNumber) {
        this.parentId = parentId;
        this.branchNumber = branchNumber;
    }

    @ProtoField(number = 1, defaultValue = "0")
    public int getParentId() {
        return parentId;
    }

    @ProtoField(number = 2, defaultValue = "0")
    public int getBranchNumber() {
        return branchNumber;
    }

    @Group
    public String affinity() {
        return String.valueOf(parentId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProtoBufChildKey that = (ProtoBufChildKey) o;
        return parentId == that.parentId && branchNumber == that.branchNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentId, branchNumber);
    }

    @Override
    public String toString() {
        return "ProtoBufChildKey{ " +
                "parentId=" + parentId +
                ", branchNumber=" + branchNumber +
                " }";
    }

    @Override
    public int compareTo(ProtoBufChildKey o) {
        if (this.parentId == o.parentId) {
            return this.branchNumber - o.branchNumber;
        } else {
            return this.parentId - o.parentId;
        }
    }
}
