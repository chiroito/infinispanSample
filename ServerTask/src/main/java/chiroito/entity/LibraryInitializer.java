package chiroito.entity;

import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder(includeClasses = {StockEntity.class, AllocationHistoryKey.class, AllocationHistoryValue.class, AllocationResponse.class}, schemaFileName = "library.proto", schemaFilePath = "proto/", schemaPackageName = "stock_sample")
public interface LibraryInitializer extends SerializationContextInitializer {
}
