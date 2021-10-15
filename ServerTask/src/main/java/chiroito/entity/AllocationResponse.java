package chiroito.entity;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

public class AllocationResponse {

    @ProtoField(number = 1, defaultValue = "true")
    public final boolean isSuccess;

    @ProtoFactory
    public AllocationResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
