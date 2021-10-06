# Sample code of Infinispan ServerTask
このソースコードはInfinispan/Red Hat Data GridでServerTaskを使うための検証およびサンプルコードです。

Infinispan 12.1.2のサーバを起動してMainクラスを実行すると以下のような結果が出力されます。

```
C:\opt\AdoptOpenJDK\jdk-11.0.10.9-hotspot\bin\java.exe -javaagent:C:\Users\cito\AppData\Local\JetBrains\Toolbox\apps\IDEA-U\ch-0\212.5284.40\lib\idea_rt.jar=56066:C:\Users\cito\AppData\Local\JetBrains\Toolbox\apps\IDEA-U\ch-0\212.5284.40\bin -Dfile.encoding=UTF-8 -classpath C:\Users\cito\develop\infinispanSample\Stream\target\classes;C:\Users\cito\.m2\repository\org\infinispan\infinispan-client-hotrod\12.1.2.Final\infinispan-client-hotrod-12.1.2.Final.jar;C:\Users\cito\.m2\repository\org\infinispan\infinispan-commons\12.1.2.Final\infinispan-commons-12.1.2.Final.jar;C:\Users\cito\.m2\repository\org\infinispan\protostream\protostream\4.4.0.Final\protostream-4.4.0.Final.jar;C:\Users\cito\.m2\repository\org\infinispan\protostream\protostream-types\4.4.0.Final\protostream-types-4.4.0.Final.jar;C:\Users\cito\.m2\repository\com\github\ben-manes\caffeine\caffeine\2.8.4\caffeine-2.8.4.jar;C:\Users\cito\.m2\repository\org\jboss\logging\jboss-logging\3.4.1.Final\jboss-logging-3.4.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-sasl-digest\1.15.1.Final\wildfly-elytron-sasl-digest-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-auth-server\1.15.1.Final\wildfly-elytron-auth-server-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-auth\1.15.1.Final\wildfly-elytron-auth-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-base\1.15.1.Final\wildfly-elytron-base-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-permission\1.15.1.Final\wildfly-elytron-permission-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-x500\1.15.1.Final\wildfly-elytron-x500-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-credential\1.15.1.Final\wildfly-elytron-credential-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-keystore\1.15.1.Final\wildfly-elytron-keystore-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-provider-util\1.15.1.Final\wildfly-elytron-provider-util-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-mechanism\1.15.1.Final\wildfly-elytron-mechanism-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-http\1.15.1.Final\wildfly-elytron-http-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-mechanism-digest\1.15.1.Final\wildfly-elytron-mechanism-digest-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-sasl\1.15.1.Final\wildfly-elytron-sasl-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-ssl\1.15.1.Final\wildfly-elytron-ssl-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-util\1.15.1.Final\wildfly-elytron-util-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\common\wildfly-common\1.5.4.Final\wildfly-common-1.5.4.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-sasl-external\1.15.1.Final\wildfly-elytron-sasl-external-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-sasl-gs2\1.15.1.Final\wildfly-elytron-sasl-gs2-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-asn1\1.15.1.Final\wildfly-elytron-asn1-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-mechanism-gssapi\1.15.1.Final\wildfly-elytron-mechanism-gssapi-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-security-manager-action\1.15.1.Final\wildfly-elytron-security-manager-action-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-sasl-gssapi\1.15.1.Final\wildfly-elytron-sasl-gssapi-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-sasl-oauth2\1.15.1.Final\wildfly-elytron-sasl-oauth2-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-mechanism-oauth2\1.15.1.Final\wildfly-elytron-mechanism-oauth2-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-sasl-plain\1.15.1.Final\wildfly-elytron-sasl-plain-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-sasl-scram\1.15.1.Final\wildfly-elytron-sasl-scram-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-mechanism-scram\1.15.1.Final\wildfly-elytron-mechanism-scram-1.15.1.Final.jar;C:\Users\cito\.m2\repository\org\wildfly\security\wildfly-elytron-password-impl\1.15.1.Final\wildfly-elytron-password-impl-1.15.1.Final.jar;C:\Users\cito\.m2\repository\io\netty\netty-handler\4.1.63.Final\netty-handler-4.1.63.Final.jar;C:\Users\cito\.m2\repository\io\netty\netty-common\4.1.63.Final\netty-common-4.1.63.Final.jar;C:\Users\cito\.m2\repository\io\netty\netty-resolver\4.1.63.Final\netty-resolver-4.1.63.Final.jar;C:\Users\cito\.m2\repository\io\netty\netty-buffer\4.1.63.Final\netty-buffer-4.1.63.Final.jar;C:\Users\cito\.m2\repository\io\netty\netty-transport\4.1.63.Final\netty-transport-4.1.63.Final.jar;C:\Users\cito\.m2\repository\io\netty\netty-codec\4.1.63.Final\netty-codec-4.1.63.Final.jar;C:\Users\cito\.m2\repository\io\reactivex\rxjava3\rxjava\3.0.4\rxjava-3.0.4.jar;C:\Users\cito\.m2\repository\org\reactivestreams\reactive-streams\1.0.3\reactive-streams-1.0.3.jar;C:\Users\cito\.m2\repository\io\netty\netty-transport-native-epoll\4.1.63.Final\netty-transport-native-epoll-4.1.63.Final-linux-x86_64.jar;C:\Users\cito\.m2\repository\io\netty\netty-transport-native-unix-common\4.1.63.Final\netty-transport-native-unix-common-4.1.63.Final.jar;C:\Users\cito\.m2\repository\org\jboss\spec\javax\transaction\jboss-transaction-api_1.2_spec\1.1.1.Final\jboss-transaction-api_1.2_spec-1.1.1.Final.jar;C:\Users\cito\.m2\repository\org\infinispan\infinispan-tasks\12.1.2.Final\infinispan-tasks-12.1.2.Final.jar;C:\Users\cito\.m2\repository\org\infinispan\infinispan-core\12.1.2.Final\infinispan-core-12.1.2.Final.jar;C:\Users\cito\.m2\repository\org\jgroups\jgroups\4.2.12.Final\jgroups-4.2.12.Final.jar;C:\Users\cito\.m2\repository\org\jboss\threads\jboss-threads\2.3.3.Final\jboss-threads-2.3.3.Final.jar;C:\Users\cito\.m2\repository\org\infinispan\infinispan-tasks-api\12.1.2.Final\infinispan-tasks-api-12.1.2.Final.jar chiroito.Main
10月 06, 2021 12:54:06 午後 org.infinispan.client.hotrod.RemoteCacheManager actualStart
INFO: ISPN004021: Infinispan version: Infinispan 'Taedonggang' 12.1.2.Final
10月 06, 2021 12:54:06 午後 org.infinispan.client.hotrod.impl.protocol.Codec20 readNewTopologyAndHash
INFO: ISPN004006: Server sent new topology view (id=1, age=0) containing 1 addresses: [127.0.0.1:11222]
+++++++++++++++++++++++++++++++++++++++++++++
PidTask
Client PID = 24548
Server PID = 10340
+++++++++++++++++++++++++++++++++++++++++++++
ThreadInfoTask
ThraedGroup : main
Thraed : SINGLE_PORT-ServerIO-3-1
StackTrace :
java.lang.Exception
	at chiroito.task.ThreadInfoTask.call(ThreadInfoTask.java:30)
	at chiroito.task.ThreadInfoTask.call(ThreadInfoTask.java:9)
	at org.infinispan.server.tasks.ServerTaskWrapper.run(ServerTaskWrapper.java:27)
	at org.infinispan.server.tasks.LocalServerTaskRunner.execute(LocalServerTaskRunner.java:23)
	at org.infinispan.server.tasks.ServerTaskEngine.invokeTask(ServerTaskEngine.java:81)
	at org.infinispan.server.tasks.ServerTaskEngine.runTask(ServerTaskEngine.java:65)
	at org.infinispan.server.tasks.ServerTaskEngine.runTask(ServerTaskEngine.java:27)
	at org.infinispan.tasks.impl.TaskManagerImpl.runTask(TaskManagerImpl.java:92)
	at org.infinispan.server.hotrod.TaskRequestProcessor.exec(TaskRequestProcessor.java:39)
	at org.infinispan.server.hotrod.HotRodDecoder.switch3(HotRodDecoder.java:1809)
	at org.infinispan.server.hotrod.HotRodDecoder.switch1_0(HotRodDecoder.java:158)
	at org.infinispan.server.hotrod.HotRodDecoder.decode(HotRodDecoder.java:145)
	at io.netty.handler.codec.ByteToMessageDecoder.decodeRemovalReentryProtection(ByteToMessageDecoder.java:508)
	at io.netty.handler.codec.ByteToMessageDecoder.callDecode(ByteToMessageDecoder.java:447)
	at io.netty.handler.codec.ByteToMessageDecoder.channelRead(ByteToMessageDecoder.java:276)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:379)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:365)
	at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:357)
	at io.netty.channel.ChannelInboundHandlerAdapter.channelRead(ChannelInboundHandlerAdapter.java:93)
	at org.infinispan.server.core.transport.StatsChannelHandler.channelRead(StatsChannelHandler.java:28)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:379)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:365)
	at io.netty.channel.AbstractChannelHandlerContext.fireChannelRead(AbstractChannelHandlerContext.java:357)
	at io.netty.channel.DefaultChannelPipeline$HeadContext.channelRead(DefaultChannelPipeline.java:1410)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:379)
	at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:365)
	at io.netty.channel.DefaultChannelPipeline.fireChannelRead(DefaultChannelPipeline.java:919)
	at io.netty.channel.nio.AbstractNioByteChannel$NioByteUnsafe.read(AbstractNioByteChannel.java:166)
	at io.netty.channel.nio.NioEventLoop.processSelectedKey(NioEventLoop.java:719)
	at io.netty.channel.nio.NioEventLoop.processSelectedKeysOptimized(NioEventLoop.java:655)
	at io.netty.channel.nio.NioEventLoop.processSelectedKeys(NioEventLoop.java:581)
	at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:493)
	at io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:989)
	at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
	at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.base/java.lang.Thread.run(Thread.java:834)

+++++++++++++++++++++++++++++++++++++++++++++
StockAllocationConputeTask
Stocked Item Num : 100000
After rushing, Stocked Item Num : 0
+++++++++++++++++++++++++++++++++++++++++++++
StockAllocationGetPutTask
Stocked Item Num : 100000
After rushing, Stocked Item Num : 24654

Process finished with exit code 0
```

## 検証する方法

### パッケージとサーバへの配置
```
git clone <URL>
cd ServerTaskSample
mvn clean package
cp target/ServerTaskSample-1.0-SNAPSHOT.jar ${INFINISPAN_HOME}/server/lib
```

### 認証情報を更新
`server/conf/users.properties`に`admin=password`という行を追加

`server/conf/groups.properties`に`admin=PowerUser,BillingAdmin,`という行を追加

### サーバを起動

```
${INFINISPAN_HOME}/bin/server.sh
```

### 検証用コードを実行

```
java chiroito.Main
```