package chiroito.infinispan;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.Configuration;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.configuration.ServerConfigurationBuilder;
import org.infinispan.commons.configuration.XMLStringConfiguration;
import org.infinispan.commons.marshall.JavaSerializationMarshaller;
import org.infinispan.commons.marshall.ProtoStreamMarshaller;

import java.util.Map;

public class Main {

    private static final String hostname = "127.0.0.1";
    private static final int port = 11222;
    private static final boolean isAuth = false;
    private static final String user = "admin";
    private static final String password = "password";

    private static final String PROTOBUF_CACHE_CONFIG = "<distributed-cache name=\"%s\">"
            + " <encoding media-type=\"application/x-protostream\"/><groups enabled=\"true\"/>"
            + "</distributed-cache>";

    private static final String JAVA_CACHE_CONFIG = "<distributed-cache name=\"%s\">"
            + " <encoding media-type=\"application/x-java-serialized-object\"/><groups enabled=\"true\"/>"
            + "</distributed-cache>";

    private static final int MAX_DATA_NUM = 3;
    private static final int MAX_BRANCH_NUM = 9;


    public static void main(String[] args) {
        outputJavaSerialization();
        outputProtoSerialization();
    }

    private static void outputProtoSerialization() {

        Configuration configuration = createProtoBufConfiguration();

        RemoteCacheManager manager = new RemoteCacheManager(configuration);

        RemoteCache<ProtoBufParentKey, ProtoBufEntity> protoBufParentCache = manager.administration().getOrCreateCache("protoBufParentCache",
                new XMLStringConfiguration(String.format(PROTOBUF_CACHE_CONFIG, "protoBufParentCache")));
        RemoteCache<ProtoBufChildKey, ProtoBufEntity> protoBufChildCache = manager.administration().getOrCreateCache("protoBufChildCache",
                new XMLStringConfiguration(String.format(PROTOBUF_CACHE_CONFIG, "protoBufChildCache")));

        // Put sample data
        for (int i = 0; i < MAX_DATA_NUM; i++) {
            protoBufParentCache.put(new ProtoBufParentKey(i), new ProtoBufEntity());

            for (int j = 0; j < MAX_BRANCH_NUM; j++) {
                protoBufChildCache.put(new ProtoBufChildKey(i, j), new ProtoBufEntity());
            }
        }

        // Output cache information on each server node
        protoBufParentCache.execute(new CacheFetchTask().getName(), Map.of("childCacheName", "protoBufChildCache"));

        manager.stop();
        manager.close();
    }

    private static void outputJavaSerialization() {

        Configuration configuration = createJavaConfiguration();

        RemoteCacheManager manager = new RemoteCacheManager(configuration);

        RemoteCache<JavaParentKey, JavaEntity> javaParentCache = manager.administration().getOrCreateCache("javaParentCache",
                new XMLStringConfiguration(String.format(JAVA_CACHE_CONFIG, "javaParentCache")));
        RemoteCache<JavaChildKey, JavaEntity> javaChildCache = manager.administration().getOrCreateCache("javaChildCache",
                new XMLStringConfiguration(String.format(JAVA_CACHE_CONFIG, "javaChildCache")));

        // Put sample data
        for (int i = 0; i < MAX_DATA_NUM; i++) {
            javaParentCache.put(new JavaParentKey(i), new JavaEntity());

            for (int j = 0; j < MAX_BRANCH_NUM; j++) {
                javaChildCache.put(new JavaChildKey(i, j), new JavaEntity());
            }
        }

        // Output cache information on each server node
        javaParentCache.execute(new CacheFetchTask().getName(), Map.of("childCacheName", "javaChildCache"));

        manager.stop();
        manager.close();
    }

    private static Configuration createProtoBufConfiguration() {

        ConfigurationBuilder cb = new ConfigurationBuilder();

        ProtoBufInitializerImpl initializer = new ProtoBufInitializerImpl();
        cb = cb.uri("hotrod://127.0.0.1:11222,127.0.0.1:11232,127.0.0.1:11242"
                        + "?context-initializers=chiroito.infinispan.ProtoBufInitializerImpl")
                .marshaller(new ProtoStreamMarshaller())
                .statistics()
                .enable()
                .jmxDomain("org.infinispan")
                .addContextInitializer(initializer);

        addAuthConfiguration(cb);

        return cb.build();
    }

    private static Configuration createJavaConfiguration() {

        ConfigurationBuilder cb = new ConfigurationBuilder();

        ServerConfigurationBuilder serverConfig = cb
                .addServer()
                .host(hostname)
                .port(Main.port);

        cb = serverConfig.marshaller(new JavaSerializationMarshaller());
        serverConfig.addJavaSerialAllowList("chiroito.infinispan.Java.+");

        addAuthConfiguration(cb);

        return cb.build();
    }

    private static ConfigurationBuilder addAuthConfiguration(ConfigurationBuilder cb) {
        if (isAuth) {
            cb.security()
                    .authentication()
                    .realm("default")
                    .saslMechanism("DIGEST-MD5")
                    .username(user)
                    .password(password);
        }
        return cb;
    }
}
