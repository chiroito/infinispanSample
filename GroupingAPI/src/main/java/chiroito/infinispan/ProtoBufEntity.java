package chiroito.infinispan;

import org.infinispan.protostream.annotations.ProtoFactory;

public class ProtoBufEntity {

    @ProtoFactory
    public ProtoBufEntity() {
    }

    @Override
    public String toString() {
        return "ProtoBufChildEntity{" +
                '}';
    }
}
