# 启动IDEA时报错

[toc]

## 1. Address already in use: bind

### (1). 报错代码

```java
Internal error. Please refer to http://jb.gg/ide/critical-startup-errors

java.util.concurrent.CompletionException: java.net.BindException: Address already in use: bind
    at java.base/java.util.concurrent.CompletableFuture.encodeThrowable(CompletableFuture.java:314)
    at java.base/java.util.concurrent.CompletableFuture.completeThrowable(CompletableFuture.java:319)
    at java.base/java.util.concurrent.CompletableFuture$AsyncSupply.run(CompletableFuture.java:1702)
    at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
    at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
    at java.base/java.lang.Thread.run(Thread.java:834)
Caused by: java.net.BindException: Address already in use: bind
    at java.base/sun.nio.ch.Net.bind0(Native Method)
    at java.base/sun.nio.ch.Net.bind(Net.java:455)
    at java.base/sun.nio.ch.Net.bind(Net.java:447)
    at java.base/sun.nio.ch.ServerSocketChannelImpl.bind(ServerSocketChannelImpl.java:227)
    at io.netty.channel.socket.nio.NioServerSocketChannel.doBind(NioServerSocketChannel.java:132)
    at io.netty.channel.AbstractChannel$AbstractUnsafe.bind(AbstractChannel.java:551)
    at io.netty.channel.DefaultChannelPipeline$HeadContext.bind(DefaultChannelPipeline.java:1346)
    at io.netty.channel.AbstractChannelHandlerContext.invokeBind(AbstractChannelHandlerContext.java:503)
    at io.netty.channel.AbstractChannelHandlerContext.bind(AbstractChannelHandlerContext.java:488)
    at io.netty.channel.DefaultChannelPipeline.bind(DefaultChannelPipeline.java:985)
    at io.netty.channel.AbstractChannel.bind(AbstractChannel.java:247)
    at io.netty.bootstrap.AbstractBootstrap$2.run(AbstractBootstrap.java:344)
    at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:163)
    at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:510)
    at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:518)
    at io.netty.util.concurrent.SingleThreadEventExecutor$6.run(SingleThreadEventExecutor.java:1044)
    at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
    at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
    ... 1 more

-----
JRE 11.0.6+8-b520.66 amd64 by JetBrains s.r.o
C:\Program Files\JetBrains\IntelliJ IDEA 2019.3.5\jbr
```

### (2). 报错原因

```java
Address already in use: bind
```

**IDEA在启动时需要在==端口6942~6991==间找到一个可用端口并绑定(bind), 而Windows10自带的HyperVisor(Hyper-V虚拟机)把这些端口都保留了, 不允许其他程序使用这些端口, 就会导致该IDEA的启动错误出现**

### (3). 解决方案

在==CMD(管理员权限)==中通过代码将==端口6942-6991==中的任意一个端口从Windows10自带的HyperVisor(Hyper-V虚拟机)的保留状态中解放出来,并且不再允许被其保留,  这样IDEA就可以绑定(bind)这个端口,从而正常启动了

1. 关闭Hyper-V虚拟机(重启电脑后进行下一步操作)
   
   - ```java
     dism.exe /Online /Disable-Feature:Microsoft-Hyper-V
     //操作成功后重启计算机然后进行下一步操作
     ```
   
2. 将==端口6942-6991==中的任意一个端口从Windows10自带的HyperVisor(Hyper-V虚拟机)的保留状态中解放出来,并且不再允许被其保留

   -  ```
      netsh int ipv4 add excludedportrange protocol=tcp startport=端口号 numberofports=1
      ```

3. 重启Hyper-V虚拟机(重启电脑后操作才能成功)

   -  ```java
      dism.exe /Online /Enable-Feature:Microsoft-Hyper-V /All
      //完成后重启电脑
      ```



