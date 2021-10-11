package chiroito.task;

import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder(includeClasses = {StockEntity.class},  schemaFileName = "library.proto", schemaFilePath = "proto/", schemaPackageName = "stock_sample")
public interface LibraryInitializer  extends SerializationContextInitializer {
}
