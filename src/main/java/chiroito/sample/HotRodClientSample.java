package chiroito.sample;

import org.infinispan.Cache;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.commons.marshall.ProtoStreamMarshaller;

/*
docker run -p 11222:11222 -v C:\Users\cito\develop\infinispanSample\server:/user-config --env USER=chito --env PASS=pass --env DEBUG=true --env CONFIG_PATH=/user-config/config.yaml --name infinispan --net host quay.io/infinispan/server:10.1.3.Final-1
 */
public class HotRodClientSample {

    public static void main(String[] args) {

        String hostname = "127.0.1.1";
        int port = 11222;
        String user = "chito";
        String password = "pass";
        boolean isAuth = false;

        ConfigurationBuilder cb
                = new ConfigurationBuilder();
        cb.marshaller(new ProtoStreamMarshaller())
                .statistics()
                .enable()
                .jmxDomain("org.infinispan")
                .addServer()
                .host(hostname)
                .port(port);

        if (isAuth) {
            cb.security()
                    .authentication()
                    .realm("default")
                    .serverName("infinispan")
                    .saslMechanism("DIGEST-MD5")
                    .username(user)
                    .password(password);
        }

        RemoteCacheManager manager = new RemoteCacheManager(cb.build());

        RemoteCache<Object, Object> c = manager.getCache("custom-cache");
        c.put("key", "value");

        System.out.println(c.get("key"));
    }
}
