package chiroito.infinispan;

import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder(includeClasses = {ProtoBufParentKey.class, ProtoBufChildKey.class, ProtoBufEntity.class}, schemaPackageName = "chiroito.infinispan", schemaFileName = "test.proto", schemaFilePath = "proto/")
public interface ProtoBufInitializer extends SerializationContextInitializer {
}
