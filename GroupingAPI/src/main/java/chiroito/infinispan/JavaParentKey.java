package chiroito.infinispan;

import org.infinispan.distribution.group.Group;

import java.io.Serializable;
import java.util.Objects;

public class JavaParentKey implements Serializable, Comparable<JavaParentKey> {

    private static final long serialVersionUID = 1;

    private final int parentId;

    public JavaParentKey(int parentId) {
        this.parentId = parentId;
    }

    public int getParentId() {
        return parentId;
    }

    @Group
    public String affinity() {
        return "" + parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JavaParentKey that = (JavaParentKey) o;
        return parentId == that.parentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentId);
    }

    @Override
    public String toString() {
        return "JavaParentKey{ " +
                "parentId=" + parentId +
                " }";
    }

    @Override
    public int compareTo(JavaParentKey o) {
        return this.parentId - o.parentId;
    }
}
