# Sample code of Infinispan ServerTask
このソースコードはInfinispan/Red Hat Data GridでServerTaskを使うための検証およびサンプルコードです。

Infinispan 12.1.2のサーバを起動してMainクラスを実行すると以下のような結果が出力されます。

```
10月 11, 2021 5:21:03 午後 org.infinispan.client.hotrod.RemoteCacheManager actualStart
INFO: ISPN004021: Infinispan version: Infinispan 'Taedonggang' 12.1.2.Final
10月 11, 2021 5:21:03 午後 org.infinispan.client.hotrod.impl.protocol.Codec20 readNewTopologyAndHash
INFO: ISPN004006: Server sent new topology view (id=1, age=0) containing 1 addresses: [127.0.0.1:11222]
+++++++++++++++++++++++++++++++++++++++++++++
PidTask
Client PID = 70560
Server PID = 19128
+++++++++++++++++++++++++++++++++++++++++++++
ThreadInfoTask
ThraedGroup : main
Thraed : SINGLE_PORT-ServerIO-3-15
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
CacheComputeTask
java.lang.Exception
	at chiroito.task.CacheComputeTask$CacheTask.apply(CacheComputeTask.java:51)
	at chiroito.task.CacheComputeTask$CacheTask.apply(CacheComputeTask.java:41)
	at org.infinispan.cache.impl.BiFunctionMapper.apply(BiFunctionMapper.java:55)
	at org.infinispan.interceptors.impl.CallInterceptor.visitComputeCommand(CallInterceptor.java:392)
	at org.infinispan.commands.write.ComputeCommand.acceptVisitor(ComputeCommand.java:130)
	at org.infinispan.interceptors.impl.CallInterceptor.visitCommand(CallInterceptor.java:178)
	at org.infinispan.interceptors.BaseAsyncInterceptor.invokeNext(BaseAsyncInterceptor.java:61)
	at org.infinispan.interceptors.DDAsyncInterceptor.handleDefault(DDAsyncInterceptor.java:55)
	at org.infinispan.interceptors.DDAsyncInterceptor.visitComputeCommand(DDAsyncInterceptor.java:81)
	at org.infinispan.commands.write.ComputeCommand.acceptVisitor(ComputeCommand.java:130)
	at org.infinispan.interceptors.BaseAsyncInterceptor.invokeNextThenApply(BaseAsyncInterceptor.java:79)
	at org.infinispan.interceptors.distribution.BaseDistributionInterceptor.handleNonTxWriteCommand(BaseDistributionInterceptor.java:243)
	at org.infinispan.interceptors.distribution.NonTxDistributionInterceptor.visitComputeCommand(NonTxDistributionInterceptor.java:136)
	at org.infinispan.commands.write.ComputeCommand.acceptVisitor(ComputeCommand.java:130)
	at org.infinispan.interceptors.BaseAsyncInterceptor.invokeNext(BaseAsyncInterceptor.java:59)
	at org.infinispan.interceptors.BaseAsyncInterceptor.asyncInvokeNext(BaseAsyncInterceptor.java:232)
	at org.infinispan.interceptors.impl.EntryWrappingInterceptor.setSkipRemoteGetsAndInvokeNextForDataCommand(EntryWrappingInterceptor.java:758)
	at org.infinispan.interceptors.impl.EntryWrappingInterceptor.visitComputeCommand(EntryWrappingInterceptor.java:432)
	at org.infinispan.commands.write.ComputeCommand.acceptVisitor(ComputeCommand.java:130)
	at org.infinispan.interceptors.BaseAsyncInterceptor.invokeNextAndFinally(BaseAsyncInterceptor.java:155)
	at org.infinispan.interceptors.locking.AbstractLockingInterceptor.lambda$nonTxLockAndInvokeNext$3(AbstractLockingInterceptor.java:318)
	at org.infinispan.interceptors.SyncInvocationStage.andHandle(SyncInvocationStage.java:69)
	at org.infinispan.interceptors.locking.AbstractLockingInterceptor.nonTxLockAndInvokeNext(AbstractLockingInterceptor.java:313)
	at org.infinispan.interceptors.locking.AbstractLockingInterceptor.visitNonTxDataWriteCommand(AbstractLockingInterceptor.java:138)
	at org.infinispan.interceptors.locking.NonTransactionalLockingInterceptor.visitDataWriteCommand(NonTransactionalLockingInterceptor.java:41)
	at org.infinispan.interceptors.locking.AbstractLockingInterceptor.visitComputeCommand(AbstractLockingInterceptor.java:92)
	at org.infinispan.commands.write.ComputeCommand.acceptVisitor(ComputeCommand.java:130)
	at org.infinispan.interceptors.BaseAsyncInterceptor.invokeNextAndHandle(BaseAsyncInterceptor.java:188)
	at org.infinispan.statetransfer.StateTransferInterceptor.handleNonTxWriteCommand(StateTransferInterceptor.java:312)
	at org.infinispan.statetransfer.StateTransferInterceptor.handleWriteCommand(StateTransferInterceptor.java:256)
	at org.infinispan.statetransfer.StateTransferInterceptor.visitComputeCommand(StateTransferInterceptor.java:124)
	at org.infinispan.commands.write.ComputeCommand.acceptVisitor(ComputeCommand.java:130)
	at org.infinispan.interceptors.BaseAsyncInterceptor.invokeNextAndFinally(BaseAsyncInterceptor.java:155)
	at org.infinispan.interceptors.impl.CacheMgmtInterceptor.visitComputeCommand(CacheMgmtInterceptor.java:231)
	at org.infinispan.commands.write.ComputeCommand.acceptVisitor(ComputeCommand.java:130)
	at org.infinispan.interceptors.BaseAsyncInterceptor.invokeNext(BaseAsyncInterceptor.java:59)
	at org.infinispan.interceptors.DDAsyncInterceptor.handleDefault(DDAsyncInterceptor.java:55)
	at org.infinispan.interceptors.DDAsyncInterceptor.visitComputeCommand(DDAsyncInterceptor.java:81)
	at org.infinispan.commands.write.ComputeCommand.acceptVisitor(ComputeCommand.java:130)
	at org.infinispan.interceptors.BaseAsyncInterceptor.invokeNextAndExceptionally(BaseAsyncInterceptor.java:128)
	at org.infinispan.interceptors.impl.InvocationContextInterceptor.visitCommand(InvocationContextInterceptor.java:89)
	at org.infinispan.interceptors.BaseAsyncInterceptor.invokeNext(BaseAsyncInterceptor.java:61)
	at org.infinispan.interceptors.DDAsyncInterceptor.handleDefault(DDAsyncInterceptor.java:55)
	at org.infinispan.interceptors.DDAsyncInterceptor.visitComputeCommand(DDAsyncInterceptor.java:81)
	at org.infinispan.commands.write.ComputeCommand.acceptVisitor(ComputeCommand.java:130)
	at org.infinispan.interceptors.DDAsyncInterceptor.visitCommand(DDAsyncInterceptor.java:51)
	at org.infinispan.interceptors.impl.AsyncInterceptorChainImpl.invoke(AsyncInterceptorChainImpl.java:244)
	at org.infinispan.cache.impl.InvocationHelper.doInvoke(InvocationHelper.java:297)
	at org.infinispan.cache.impl.InvocationHelper.invoke(InvocationHelper.java:101)
	at org.infinispan.cache.impl.InvocationHelper.invoke(InvocationHelper.java:83)
	at org.infinispan.cache.impl.CacheImpl.computeInternal(CacheImpl.java:334)
	at org.infinispan.cache.impl.CacheImpl.computeInternal(CacheImpl.java:325)
	at org.infinispan.cache.impl.CacheImpl.compute(CacheImpl.java:321)
	at org.infinispan.cache.impl.CacheImpl.computeIfPresent(CacheImpl.java:297)
	at org.infinispan.cache.impl.AbstractDelegatingCache.computeIfPresent(AbstractDelegatingCache.java:409)
	at org.infinispan.cache.impl.EncoderCache.computeIfPresent(EncoderCache.java:635)
	at org.infinispan.Cache.computeIfPresent(Cache.java:478)
	at chiroito.task.CacheComputeTask.call(CacheComputeTask.java:30)
	at chiroito.task.CacheComputeTask.call(CacheComputeTask.java:11)
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
StockAllocationComputeTask
Stocked Item Num : 100000
After rushing, Stocked Item Num : 0
+++++++++++++++++++++++++++++++++++++++++++++
StockAllocationGetPutTask
Stocked Item Num : 100000
After rushing, Stocked Item Num : 25730
+++++++++++++++++++++++++++++++++++++++++++++
StockAllocationComputeWithLogicTask
Stocked Item Num : 100000
10月 11, 2021 5:21:16 午後 org.infinispan.client.hotrod.impl.protocol.Codec20 readNewTopologyAndHash
INFO: ISPN004006: Server sent new topology view (id=1, age=0) containing 1 addresses: [127.0.0.1:11222]
After rushing, Stocked Item Num : 0

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