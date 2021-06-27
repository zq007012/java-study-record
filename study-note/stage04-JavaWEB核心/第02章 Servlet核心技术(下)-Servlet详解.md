# 第02章 Servlet核心技术(下)-Servlet详解

[toc]

## 一. Servlet+JDBC应用

- 在Servlet中可以使用JDBC技术访问数据库, 常见功能如下
  - 查询JDBC数据, 然后生成显示界面, 例如:列表显示功能
  - 接收请求参数, 然后对DB操作, 例如: 注册、登录、修改密码等功能
- 为了方便重用和便于维护等目的， 经常会采用DAO(Data Access Object)模式对数据库操作进行独立封装
- ![](%E7%AC%AC02%E7%AB%A0%20Servlet%E6%A0%B8%E5%BF%83%E6%8A%80%E6%9C%AF(%E4%B8%8B).assets/SErvlet+JDBC%E5%BA%94%E7%94%A8.png)
- DAO工厂(工厂模式)
  - 工厂类: 封装了对象的创建细节, 为调用者提供符合要求的对象

## 二. 重定向和转发(重点)

### 2.1 重定向

#### 2.2.1 重定向的概念

- A找老王了解一些事情, 老王发现这事自己也不清楚, 但知道老赵清楚,, 于是告诉A去找老赵, A就去找老赵了, 老赵就告诉了A关于这件事的详细信息, 所以A是知道是谁告诉自己这件事的
- 首先客户浏览器发送http请求, 当web服务器接收后就将302状态码和对应的新`location`发送给客户端浏览器, 浏览器发现是302响应, 就会在地址栏自动再发送一个新的http请求, 请求的url是新的location地址, 服务器根据此请求寻找资源并相应给客户
  - **不管浏览器第一次发送的http请求的是超链接、地址栏、还是表单， 第二次重定向地址的http请求总是在浏览器的地址栏发出， 所以第一次http请求中url携带的数据或者表单携带的数据在第二次重定向地址的http请求中是没有的**

#### 2.2.2 重定向的实现

- 实现重定向需要借助javax.servlet.http.HttpServletResponse接口中的以下方法

| 方法                               | 说明                           |
| ---------------------------------- | ------------------------------ |
| void sendRedirect(String location) | 告诉浏览器重定向到location位置 |

#### 2.2.3 重定向的原理

![](%E7%AC%AC02%E7%AB%A0%20Servlet%E6%A0%B8%E5%BF%83%E6%8A%80%E6%9C%AF(%E4%B8%8B).assets/%E9%87%8D%E5%AE%9A%E5%90%91%E7%9A%84%E5%8E%9F%E7%90%86.png)

#### 2.2.4 重定向的特点

- 重定向后, 浏览器地址栏的url会发生改变(A知道这事是谁给自己办的)
- 浏览器在重定向前会发送一次请求(找老王办事), 重定向后又会发送一次请求(找老赵办事)
- 携带数据请求最好不要以重定向方式处理, 因为重定向会导致这些数据丢失
- 重定向中的`"/"`的表示根路径(根url)的意思, 所以`""`(相对路径)和`"../"`(相对路径的父目录)以及绝对路径都可以使用
- **重定向可以重定向到本部署环境之外, 甚至可以部署到本Tomcat服务器之外的其它web服务器**

### 2.2 转发

#### 2.2.1 转发的概念

- A带着一对资料去找老王办事, 老王办不了, 于是老王将资料和与A的联系通道交给了老赵处理, 老赵拿着这些资料办完事后, 在通过老王留下的与A的联系通道告诉了A办事的结果, 由于老赵是通过老王留下的联系通道联系A的所以A会以为这事儿是由老赵办的(浏览器的地址栏上始终显示的是老王的url)
- 一个Web组件(Servlet/JSP)将未完成的处理通过容器转交给两一个Web组件继续处理, 转发的各个Web组件会共享Request和Response对象

#### 2.2.2 转发的实现

- 首先需要获得指定了转发目标的请求调度器`RequestDispatcher`对象, 可以通过`ServletRequest`对象的`RequestDispatcher getRequestDispatcher(String path)`来获得
  - 注意: **path的格式跟`web.xml`中`url-pattern`的值的格式相同, `"/"`表示的是本模块的部署环境**, 所以==path只能是本模块部署环境下的资源==
- 然后调用请求调度器`RequestDispatcher`对象的`void forward(ServletRequest request, ServletResponse response)`方法进行转发

##### 2.2.2.1 `ServletRequest`的方法

| 方法                                                | 说明                                                         |
| --------------------------------------------------- | ------------------------------------------------------------ |
| RequestDispatcher getRequestDispatcher(String path) | 获得指定了转发目标的请求调度器`RequestDispatcher`对象, 该对象充当了目标资源的包装器 |

**在转发之前还可以向`ServletRequest`对象中存储一些参数, 以下是`ServletRequest`对象中的相关方法**

| 方法                                         | 说明                                                    |
| -------------------------------------------- | ------------------------------------------------------- |
| Object getAttribute(String name)             | 将指定属性值作为对象返回, 若给定名称`不存在, 则返回null |
| void setAttribute(String name, Object value) | 在此请求中存储属性值                                    |
| void removeAttribute(String name)            | 移除此请求中的指定属性                                  |

##### 2.2.2.2 `RequestDispatcher`对象中的相关方法

| 方法                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| void forward(ServletRequest request, ServletResponse response) | 将请求从一个servlet转发到本模块部署环境下的另一个资源(Servlet、JSP文件或HTML文件) |

#### 2.2.3 转发的特点

- 转发后, 浏览器地址栏的url不会发生改变(A还以为这事儿是是老王给自己办的)
- 在转发中, 浏览器只会发送一次请求
- 转发过程中不会丢失数据
- 转发中的`"/"`的表示本模块的部署环境, 所以`""`(相对路径)和`"../"`(相对路径的父目录)以及绝对路径**都不可以使用**, 
- 转发目标只能是本模块部署环境下的其他资源, **不能是**其它部署环境下的资源, **更不能是**其它web服务器中的资源

### 2.3 重定向和转发的比较

![](%E7%AC%AC02%E7%AB%A0%20Servlet%E6%A0%B8%E5%BF%83%E6%8A%80%E6%9C%AF(%E4%B8%8B).assets/%E9%87%8D%E5%AE%9A%E5%90%91%E5%92%8C%E8%BD%AC%E5%8F%91%E7%9A%84%E6%AF%94%E8%BE%83.png)

## 三.Servlet线程安全(重点)

- **服务器在收到请求后, 会启动一个线程来进行相应的请求处理, 在这个线程里调用的是 Servlet对象的service(request, response)方法**
- 默认情况下, 服务器只为每个Servlet创建一个对象实例, 当多个请求访问同一个Servlet时, 会有多个线程访问同一个Servlet对象的`service(request, response)`方法, 如果`service`方法里调用了Servlet对象的成员变量, 此时就可能发生线程安全问题
- **可以对发生线程安全问题的代码进行加锁处理, 从而避免并发安全问题, 但不建议使用,** 因为服务器上的资源是经常在同一时间被大量请求的, 而同步代码块会严重拖累服务器的执行效率的, 所以不如尽量避免在service方法中调用Servlet对象的成员变量

## 四. 状态管理

- Web程序基于HTTP协议通信, 而HTTP协议是**无状态**的协议, 一旦服务器响应完客户的请求之后, 就断开连接,而同一个客户的下一次请求又会重新建立网络连接
- 服务器程序有时是需要判断请求是否为同一个客户端发出的, 比如客户的多次选购商品, 因此, 有必要跟踪同一客户端发出的一系列请求
- **把一个浏览器与一个服务器之间的多次交互作为一个整体, 将多次交互所涉及的数据保存下来, 即状态管理**
- 多次交互的数据状态可以在客户端保存, 也可以在服务器端保存, 状态管理主要分为以下两类:
  - 客户端管理: 将状态保存在客户端, 基于Cookie实现
  - 服务器管理: 将状态保存在服务器端, 基于Session技术实现

### 4.1 Cookie技术

#### 4.1.1 基本概念

- Cookie本意为"饼干", 在这里表示客户端以"键-值"形式进行状态数据保存的一种技术
- 浏览器向服务器发送请求时, 浏览器会将保存在浏览器上的Cookie通过请求头上的`cookie`属性发送给服务器, 而服务器可以通过响应头上的`set-cookie`属性对浏览器上的cookie进行修改
  - 一个cookie中只能有一对键值对
  - 一个url可以绑定多个cookie
- 每次浏览器访问服务器时, 都会通过请求头上的`cookie`属性  将与url匹配的cookie发送给服务器

#### 4.1.2 相关方法

##### 4.1.2.1 服务器获取cookie

- 使用javax.servlet.http.HttpServletRequest接口的成员方法获取浏览器发送过来的cookie

- | 方法                  | 说明                                                         |
  | --------------------- | ------------------------------------------------------------ |
  | Cookie[] getCookies() | 获取此请求中包含的所有Cookie对象, 如果请求头中没有cookie则返回null(比如**浏览器时第一次访问该url父目录, 就会没有cookie**)<br />**该数组里的所有cookie的`getPath()`的值都是null, 但实际上浏览器上的对应cookie是有path的, 只是没有写到请求头中, 所以读取不到** |

##### 4.1.2.2 服务器修改浏览器上的cookie

- 使用javax.servlet.http.HttpServletResponse接口的成员方法实现Cookie的添加

- | 方法                          | 说明                                               |
  | ----------------------------- | -------------------------------------------------- |
  | void addCookie(Cookie cookie) | 把cookie对象添加到到响应头的`set-cookie`属性的值中 |

##### 4.1.2.3 Cookie对象的常用方法

- `javax.servlet.http.Cookie`, 一个Cookie对象只会保存一个键-值对, 并且该Cookie对象的名设置好后不可修改, 值是可以改的, Cookie对象可以绑定url, 可以设置最大的保存时间

###### 4.1.2.3.1 构造方法

| 构造方法                          | 说明                                |
| --------------------------------- | ----------------------------------- |
| Cookie(String name, String value) | 根据参数指定的键-值来构造cookie对象 |

###### 4.1.2.3.2 常用的构造方法

| 方法                           | 说明                                                         |
| ------------------------------ | ------------------------------------------------------------ |
| void setPath(String url)       | **设置cookie的路径信息(这样的话, 只有当浏览访问指定的url时, 该cookie才会添加到请求头中)** |
| void setValue(String newValue) | 设值该Cookie对象的值                                         |
| String getValue()              | 获取该Cookie对象的值                                         |
| String getName()               | 获取该Cookie对象的值                                         |
| void setMaxAge(int expiry)     | **设置该cookie在浏览器上的最大保存时间**                     |
| int getmaxAge()                | 获取该cookie在浏览器上的最大保存时间                         |

#### 4.1.3 Cookie的生命周期

- 默认情况下. 浏览器会将Cookie信息保存在内存中, 只要浏览器关闭, Cookie信息就会消失, 

- 如果希望关闭浏览器后Cookie信息人有效, 可以通过Cookie类相关方法实现

- | 方法                       | 说明                                     |
  | -------------------------- | ---------------------------------------- |
  | void setMaxAge(int expiry) | **设置该cookie在浏览器上的最大保存时间** |
  | int getmaxAge()            | 获取该cookie在浏览器上的最大保存时间     |

#### 4.1.4 Cookie的路径问题

- 浏览器在访问服务器时, 会比较Cookie的路径与请求路径是否匹配, 只有匹配的Cookie才会放到请求头中发送给服务器
- Cookie是有默认路径的, 该路径是添加这个Cookie对象的  respond对象所属servlet对象的url的父目录
  - Cookie的路径中`"/"`表示的是根url, 所以相对路径`""`和相对路径的父目录`"../"`以及绝对路径都可以使用
- 浏览器访问的url只有符合Cookie的路径或其子路径时, 浏览器才会将该Cookie添加到该url的请求头中
  - `"/father"`的子路径`"/father/son1"`、`"/father/son2"`、`"/father/son1/grandson"`等等
- 浏览器会记录Cookie的path, 但是浏览器访问对应的url时, 是不会把cookie的path写到请求头中的, 所以服务器是读取不到cookie的path的

#### 4.1.5 Cookie的特点

- Cookie技术不适合存储所有数据, 程序员只用于存储少量, 非敏感信息, 原因如下:
  - 将状态数据保存在浏览器端, 不安全
  - 保存数据量有限制, 大约4kb左右
  - 只能保存字符串信息
  - 可以通过浏览器设置禁止cookie的使用

### 4.2 Session技术

#### 4.2.1 基本概念

- Session本意为"会话"的含义, 是用来维护一个客户端和服务端关联的技术之一
- 浏览器访问服务器时, 服务器会**为每一个浏览器**在服务器端的内存中分配一个空间, 用于创建一个Session对象, 该对象有一个`sessionId`属性, 该属性的值是唯一的, 并且服务器会将这个`sessionId`以Cookie的方式发送给浏览器存储
  - 但浏览器第一次访问某个路径时, 请求头中是没有cookie的(因为真没有), 服务器接收到这个请求后, 会根据浏览器的信息生成一个值唯一的`sessionId`, 并在服务器的内存中创建一个专属于该浏览器的`Session`对象,  然后以`sessionId`为名, `sessionId`的值为值, 生成一个Cookie对象, 将该cookie响应给浏览器, 浏览器就会将cookie保存下来
  - 当浏览器第二次访问同一个路径时, 会将保存了`sessionId`的cookie写到请求头中发送给服务器, 服务器接收到后就会根据`sessionId`的值在内存中找到专属于该浏览器的`Session`对象

#### 4.2.2 相关方法

##### 4.2.2.1 获取`Session`对象

- 使用`javax.servlet.http.HttpServletRequest`接口的成员方法实现`Session`的获取

- | 方法                     | 说明                                                         |
  | ------------------------ | ------------------------------------------------------------ |
  | HttpSession getSession() | 获取此请求中的`sessionId`关联的`Session`对象, <br />若请求中没有`sesssionId`, 那就创建一个`Session`对象, 并在响应时, 自动将该`Session`对象的`sessionId`以cookie的添加到浏览器中 |

##### 4.2.2.2 `Session`对象的常用方法

`javax.servlet.http.HttpSession`接口中的常用方法

| 方法                                         | 声明                                                         |
| -------------------------------------------- | ------------------------------------------------------------ |
| boolean isNew()                              | 判断本`Session`对象是否时本次请求时创建的                    |
| String getId()                               | 获取`Session`对象的`sessionId`                               |
| Object getAttribute(String name)             | 获取存储在`Session`对象中的数据                              |
| void setAttribute(String name, Object value) | 向`Session`对象中存储数据                                    |
| void removeAttribute(String name)            | 移除`Session`对象中的某个数据                                |
| int getMaxInactiveInterval()                 | 获取`Session`对象的最大存活时长<br />当seconds的值为0或者负数时, 则`Session`对象永久存活 |
| void setMaxInactiveInterval(int seconds)     | 设值`Session`对象的最大存活时长, 单位是秒, <br />当seconds的值为0或者负数时, 则`Session`对象永久存活<br />默认是30分钟, 也就是说默认30分钟后销毁该`Session`对象, 浏览器再次访问时, 就得重新创建`Session`对象了 |

#### 4.2.3 Session的生命周期

- 为了节省服务器内存空间资源, 服务器会销毁超过了最大存活时长的`Session`对象, 服务其默认`Session`对象的最大存活时长为30分钟

- 可以通过`Session`对象的相关方法对`Session`对象的最大存活时间进行设置

  - 这种设置方式**只能对本**`Session`对象起作用

  - | 方法                                     | 说明                                                         |
    | ---------------------------------------- | ------------------------------------------------------------ |
    | int getMaxInactiveInterval()             | 获取`Session`对象的最大存活时长<br />当seconds的值为0或者负数时, 表示本`Session`对象永久存活 |
    | void setMaxInactiveInterval(int seconds) | 设值`Session`对象的最大存活时长, 单位是秒, <br />当seconds的值为0或者负数时, 则表示本`Session`对象永久存活<br />默认是30分钟, 也就是说默认30分钟后销毁该`Session`对象, 浏览器再次访问时, 就得重新创建`Session`对象了 |

- 还可以在`WEB-INFO/web.xml`文件中对本部署环境下创建的所有`Session`对象的最大存活时长进行设置

  - 这种设置方式会对**本部署环境下创建的所有**`Session`对象的最大存活时长起作用

  - ```xml
    <!-- 本节点是根节点的子节点 -->
    <session-config>
        <!-- 单位是'分钟' -->
    	<session-timeout>25</session-timeout>
    </session-config>
    ```



#### 4.2.4 Session特点

- 数据比较安全
- 能够保存的数据类型丰富(可以保存各种对象), 而cookie只能保存字符串
- 能够保存更多的数据, 而Cookie大约保存4kb
- 数据保存在服务端会占用服务器的内存空间, 如果存储信息过多, 用户量过大, 会严重影响服务器的性能

## 五. `ServletContext`、`ServletConfig`、`ServletRequest`、`ServletResponse`、`cookie`、`Session`的比较

- `ServletContext`对象会在模块部署时生成, 对于本模块下的`Servlet`对象来说`ServletContext`对象是公有且唯一的
- `ServletConfig`对象会在`Servlet`对象创建时生成, 相当于该`Servlet`对象的成员变量, 当不同的浏览器访问本`Servlet`对象时, `ServletConfig`对象是唯一的
- 本`Servlet`对象每次被访问时, 都会生成一个新的`ServletRequest`对象和新的`ServletResponse`对象
- `Session`对象针对的是同一浏览器对本`Servlet`对象的多次访问, 也就是说同一浏览器访问本`Servlet`对象时, 关联的`Session`对象是同一个; 而不同浏览器访问本`Servlet`对象时, 关联的`Session`对象是不同的
- `cookie`和`Session`都是用来进行状态管理的, 不同之处在于cookie保存在浏览器, 而session保存在服务器