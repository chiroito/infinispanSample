package chiroito.infinispan;

import org.infinispan.distribution.group.Group;

import java.io.Serializable;
import java.util.Objects;

public class JavaChildKey implements Serializable, Comparable<JavaChildKey> {

    private final int parentId;
    private final int branchNumber;

    public JavaChildKey(int parentId, int branchNumber) {
        this.parentId = parentId;
        this.branchNumber = branchNumber;
    }

    public int getParentId() {
        return parentId;
    }

    public int getBranchNumber() {
        return branchNumber;
    }

//    @Group
//    public String affinity() {
//        return "" + parentId;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JavaChildKey that = (JavaChildKey) o;
        return parentId == that.parentId && branchNumber == that.branchNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentId, branchNumber);
    }



    @Override
    public String toString() {
        return "JavaChildKey{ " +
                "parentId=" + parentId +
                ", branchNumber=" + branchNumber +
                " }";
    }

    @Override
    public int compareTo(JavaChildKey o) {
        if(this.parentId == o.parentId){
            return this.branchNumber - o.branchNumber;
        } else {
            return this.parentId - o.parentId;
        }
    }
}
