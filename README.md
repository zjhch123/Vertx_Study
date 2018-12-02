# Vert.x
Vert.x是一个异步无阻塞的网络框架，其参照物是node.js。基本上node.js能干的事情，Vert.x都能干。Vert.x利用Netty4的EventLoop来做单线程的事件循环，所以跑在Vert.x上的业务不能做CPU密集型的运算，这样会导致整个线程被阻塞。

## 特性
1. 支持语言多：Java/Scale/Javascript/Ruby/Python.......
2. 异步无锁编程
3. 多IO支持，分布式支持UpUp
4. 生态环境日趋成熟

----
Vert.x是基于事件的，提供一个事件驱动编程模型。使用Vert.x时只需要编写EventHandler。

Vert.x的执行单元叫verticle。即程序的入口，每个语言可能实现的方式不一样，比如Java需要继承一个AbstractVerticle抽象类。

在Vert.x里所有的事件包括IO都是依赖于Netty的EventLoop接口,而这个接口在Netty里会一定的频率调用.即当发生IO事件时,Netty会按时间比率分配CPU资源去响应这个事件。

在Vert.x里你可以简单的理解为IO相关的事件就可以了,用了一个特定的线程池来响应这类请求.而Worker在Vert.x里默认是一套按顺序执行的Handler,即按照先来先到的顺序依次执行,此类的请求是另一个线程池执行。

<b>所有业务逻辑其实都会跑在Netty里的EventLoop上，而EventLoop通过循环事件队列来执行所有的业务逻辑，这样可以把一些I/O操作频繁的事件及时从CPU上剥离开来，最后通过注册一个回调Handler来处理所有的事件回调。</b>

----
Vert.x3支持很多常用工具：metrics、热部署、consul、kafka、mongo、redis等。
