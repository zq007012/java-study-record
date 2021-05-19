# 第01章 Servlet核心技术(上)

[toc]

## 一. 基本概念(常识)

### 1.1 C/S 架构的概念

- C/S 架构(Client/Server, 客户端/服务器模式), 是一种比较早的软件体系结构, 也是生活中很常见的结构. 这种结构将需要处理的业务合理的分配到客户端和服务器端, 客户端通常负责完成于用户的交互任务, 服务器通常负责数据的管理.
- C/S架构的主要优点如下:
  - 客户端的界面和功能可以很丰富
  - 应用服务器的负荷较轻
  - 响应速度很快
- C/S架构的主要缺点如下
  - 适用面窄, 用户群固定
  - 维护和升级的成本高, 所有的客户端都需要更新版本

### 1.2 B/S架构的概念

- B/S架构(Brower/Server, 浏览器/服务器模式), 是互联网兴起后的软件体系结构, 该结构将系统功能实现的主要业务逻辑集中到服务器端, 极少数业务逻辑在浏览器实现, 浏览器通常负责完成与客户的交互任务, 服务器通常负责数据的管理
- B/S架构的主要优点如下:
  - 无需安装客户端, 只要有浏览器即可
  - 适用面广, 用户群不固定
  - 通过权限控制实现多客户访问的目的, 交互性较强
  - 维护和升级的成本低, 无需更新所有客户端版本
- B/S架构的主要缺点如下
  - 应用服务器的负荷较重
  - 浏览器的界面和功能想要达到客户端的丰富成都需要花费大量的成本
  - 在跨浏览器上不尽如人意, 适配比较麻烦

### 1.3 JavaWeb的概念

- Web本来的含义是**网页**, **在JavaWeb中Web则表示可以在互联网上访问的==资源==**
- 可以在互联网上访问的资源主要分为以下两种:
  - 静态资源: 主要是指该资源在服务器端已经准备好了, 当客户端发出对该资源的访问请求时, 服务端会直接把该资源交给客户端
  - 动态资源: 主要是指该资源在服务器端没有准备, 当客户端发出对该资源的访问请求时, 服务端会根据客户端的请求信息现场生成客户端所需的资源, 然后交给客户端
- 可以看到静态资源与动态资源最大的区别是, 访问同一个静态资源时, 是千人一面, 大家看到的都是相同的东西; 访问同一个动态资源时, 是千人千面, 可以看碟下菜, 可以给不同的访问者看不同的东西
- JavaWeb是使用Java语言进行动态Web资源开发技术的统称, 是解决相关Web互联网领域技术总和

### 1.4 B/S架构早期与后来的区别

- ![](%E7%AC%AC01%E7%AB%A0%20Servlet%E6%A0%B8%E5%BF%83%E6%8A%80%E6%9C%AF(%E4%B8%8A).assets/%E6%97%A9%E6%9C%9Fbs%E6%9E%B6%E6%9E%84.png)
- ![](%E7%AC%AC01%E7%AB%A0%20Servlet%E6%A0%B8%E5%BF%83%E6%8A%80%E6%9C%AF(%E4%B8%8A).assets/%E5%90%8E%E6%9D%A5%E7%9A%84BS%E6%9E%B6%E6%9E%84.png)

## 二. HTTP协议(熟悉)

### 2.1 HTTP协议的概念

- HTTP协议(Hyper Transfer Protocol, 超文本传输协议) 是由W3C(万维网联盟)组织制定的一种应用层协议, 是用来规范浏览器与Web服务器之间如何通讯的数据格式,主要涉及浏览器发送请求的格式和服务器的响应格式
- HTTP协议通常承载于TCP协议之上, 而承载于TLS或SSL协议层之上的协议就是常说的HTTPS协议. (TLS或SSL协议承载于TCP协议之上)
- HTTP默认的端口号为80, HTTPS默认的端口号为443
- ![](%E7%AC%AC01%E7%AB%A0%20Servlet%E6%A0%B8%E5%BF%83%E6%8A%80%E6%9C%AF(%E4%B8%8A).assets/%E7%BD%91%E7%BB%9C%E9%80%9A%E4%BF%A1%E5%88%86%E5%B1%82%E7%A4%BA%E6%84%8F%E5%9B%BE.png)

### 2.2 HTTP请求格式

- 客户端发送到服务器的请求消息主要包括:请求行、请求头、空白行和请求体

- 请求行    用来说明请求类型和要访问的资源的路径以及所使用的HTTP版本， 格式如下

  - ```
    请求类型(Request Method)  请求的路径  协议的版本
    ```

  - ```
    POST /task01-demo01/demo1.html HTTP/1.1
    ```

- 请求头    是紧接着请求行的(及第一行)之后的部分, 用来说明服务器要是用的附加新西, 格式(key: value), 有发起请求的主机的ip地址和端口, 访问的主机的地址和端口, 请求信息的长度, 发起请求的浏览器的附加信息, 如下所示:

  - ```
    Host: localhost:8080//要访问的主机的IP地址和端口
    Content-length; 21
    Cache-Control: max-age=0
    User-Agent: Mozilla/5.0 (Windwo NT 6.1; WOW64)
    ```

- 空白行    即请求头和请求体之间必须有的一行空白行

- 请求体    也叫请求数据, 可以添加任意的其他数据, 主要是表单数据, 如下所示:

  - ```
    name=tifa&password=123456
    ```

### 2.3 HTTP响应格式

- 通常情况下服务器接收并处理客户端发过来的请求后会返回一个HTTP的响应消息, 主要包括: 响应行  响应头  空白行和响应体

- 响应行    用来说明HTTP协议版本号和状态码以及状态消息, 格式如下:

  - ```
    协议的版本  状态码  状态信息
    ```

    - 协议的版本有`HTTP/1.1`和`HTTP/1.0`两种
    - 状态码    200表示成功, 404表示路径错误, 500表示服务错误
    - 状态信息    状态码200对应的状态信息是`OK`

  - ```
    HTTP/1.1 200 OK
    ```

- 响应头    用来说明客户端要是用的一些附加信息, 格式(key: value)

  - ```
    Content-Type: text/html
    Content-length: 588
    Date: Thu,08 Sep 2021 12:59:54 GMT
    ```

- 空白行    响应头与响应体之间必须有一行空白行

- 响应体    服务器返回给客户端的数据, 一般是html的文本信息

  - ```
    <html><head><title>Demo</title></head><body><h1>Hello World</h1></body></html>
    ```



## 三. Tombat服务器(重点)

### 3.1 基本概念

- Tomcat本意为公猫, 最初是由Sun公司的软件架构师詹姆斯·邓肯·戴维森开发的，后来他将其变为开源项目, 并由Sun公司贡献给Apache软件基金会
- Tocat服务器是一个开源的轻量级Web应用服务器, 在中大型系统和并发量小的场合下被普遍使用, 是开发和调试Servlet、JSP程序的首选

### 3.2 安装方式

- 下载地址：<http://tomcat.apache.org/>
- 选择所需的版本, 然后选择安装版或绿色版下载

### 3.3 目录结构

| 目录    | 说明                                                         |
| ------- | ------------------------------------------------------------ |
| bin     | 主要存放二进制可执行文件和脚本                               |
| conf    | 主要存放各种配置文件                                         |
| lib     | 主要用来存放运行Tomcat所需要的jar包                          |
| logs    | 主要存放Tomcat在运行过程中产生的日志文件                     |
| temp    | 主要存放Tomcat在运行过程中产生的临时文件                     |
| webapps | 主要存放Web应用程序, 当Tomcat启动时会去加载该目录下的Web应用程序 |
| work    | 主要存放Tomcat在运行时的编译后文件, 例如JSP编译后的文件      |

### 3.4 启动和关闭

- 启动之前首先安装JDK并配置**JDK的环境变量**`JAVA_HOME	`以及环境变量`path`, 若希望Tomcat服务器可以在任意路径启动, 则需要配置**Tomcat环境变量**`CATALINA_HOME`以及环境变量`path`

- 启动方式 
  - 使用bin目录下的批处理文件`startup.bat`来启动Tomcat服务器, 若在cmd终端的最后出现一个毫秒数就说明启动成功
- 关闭方式
  - 使用bin目录下的批处理文件`shutdown.bat`来关闭Tomcat服务器
  - 也可以通过点击`ctrl+c`来关闭
  - 还可以通过关闭终端来关闭

### 3.5 CMD控制台和IDEA控制台中的Tomcat日志信息乱码

这是因为Tomcat的日志文件都是以`utf-8`编码的, 而CMD控制台和IDEA的控制台使用的是`gbk`编码, 所以产生了乱码, 解决思路就是, 让专门用来显示Tomcat日志文件的Cmd控制台和IDEA的控制台使用`utf-8`编码, 

#### 3.5.1 解决CMD控制台的日志信息乱码

- 打开cmd运行以下代码, 关闭cmd; 在此之后, 当启动专门用来显示tomcat显示日志的cmd时就会使用utf-8编码, 从而避免乱码

- ```dos
  set rr="HKCU\Console\Tomcat"
  reg add %rr% /v "CodePage" /t REG_DWORD /d 0x0000fde9 /f>nul
  ```

#### 3.5.2 解决IDEA控制台的日志信息乱码

- 必须以将配置了名为`JAVA_TOOL_OPTIONS`, 值为`-Dfile.encoding=UTF-8`的环境变量

- `Help`→`Edit Custom VM Options...`, 输入以下代码, 然后重启IDEA, 这样IDEA的控制台就会默认使用utf-8编码了

- ```properties
  -Dfile.encoding=UTF-8
  ```

### 3.6 配置文件

- `conf`文件夹下的`server.xml`文件是Tomcat服务器的主配置文件, 可以设置服务器的端口号、设置域名或IP、默认加载的项目， 请求的编码等

  - ```xml
    <Connector port="8080" protocal="HTTP/1.1" connectionTimeout="20000" redirectPort="8843"/>
    
    ```

- `conf`文件夹下的`tomcat-user.xml`文件**用来配置管理Tomcat服务器的用户与权限**

  - ```xml
    <role ralename="manager-gui"/>
    <user username="zq" password="11111111" roles="manager-gui"/>
    ```

### 3.7 `webapps`文件夹

- 当`Tomcat`开启后, 可以通过`http://服务器IP地址:端口号/`+**文件相对路径**访问`webapps`下的所有文件

- `http://localhost:8080/`    访问的是 `webapps/ROOT`目录下的`index.html`文件
- `http://localhost:8080/root`    访问的是 `webapps/ROOT`目录下的`index.html`文件
- `http://localhost:8080/root/index.html`    访问的是 `webapps/ROOT`目录下的`index.html`文件
- `http://localhost:8080/root/hello world.html`    访问的是 `webapps/ROOT`目录下的`hello word.html`文件
- `http://localhost:8080/zq`    访问的是 `webapps/zq`目录下的`index.html`文件
- `http://localhost:8080/zq/hello world.html`    访问的是 `webapps/zq`目录下的`hello word.html`**文件**

## 四. Servlet(重点)

### 4.1 Servlet的基本概念

- Servlet( Server Applet)是Java Servlet的简称, applet是小应用程序的意思, servlet是小服务程序或服务连接器的意思, 是Java语言编写的服务端程序, 换句话说, Servlet就是运行在Tomcat服务器上的Java类
- Servlet用来完成B/S架构下客户端请求的响应处理, 也就是交互式地浏览和生成数据, 生成动态Web内容

### 4.2 Servlet的编程步骤

####  4.2.1 建立`Java Web Application`模块并配置Tomcat服务器

1. 创建一个空项目: `Create New Project`→`Empty Project`→`Empty Project`, 点击`Next`, 输入项目名`stage04-module01`以及项目的保存路径
2. 初始化该空项目:
   1.  `File`→`Project Structure...`
   2. `Project Setting`→`Project`
      1. `Project name`不变
      2. `Project SDK`设置为本机安装的JDK版本
      3. `Project language level`要小于或等于`Project SDK`的版本
      4. `Project compiler output`不变
      5. 点击`OK`
3. 创建一个`Web`应用模块: `File`→`Project Structure...`
   1. `Project Setting`→`Modules`
   2. 点击`+`, 点击`New Module`
   3. 左侧栏点击`Java Enterprise`, 右侧栏选择`Web Applicaton`
      1. 设置`Application Server`: 点击后面的`New`之后选择`Tomcat Server`, 其他的设置不要动
      2. ![](%E7%AC%AC01%E7%AB%A0%20Servlet%E6%A0%B8%E5%BF%83%E6%8A%80%E6%9C%AF(%E4%B8%8A).assets/One_2021-04-29_201312.png)
      3. 点击`Next`, 输入模块名``task01-demo01`和模块的存储位置, 点击`Finish`, 就会在该项目下生成该模块
4. 点击`Run`→`Edit Configuration`, 在`Run/Debug Configuration`中可以设置: 当Tomcat启动时把本模块部署到`Servlet`中, 并且为本模块设置部署环境
   1. 将Name设置为与本模块名同名, 以便与其他的设置相区分
   2. 点击`Sever`, `Application server`选择`Tomcat`
      1. 在`Open brower`里可以设置:  当用此`Run/Debug Configuration`启动Tomcat之后, 自动使用指定的浏览器打开`URL`中指定的网址
      2. ![](%E7%AC%AC01%E7%AB%A0%20Servlet%E6%A0%B8%E5%BF%83%E6%8A%80%E6%9C%AF(%E4%B8%8A).assets/One_2021-04-30_080119.png)
   3. 点击`Deployment`, 设置本模块的部署环境, 在`Deploy at the server startup`里面找到本模块(如果没有的话就将本模块的路径添加进来), 点击后在`Application context`中设置本模块的部署环境: `/task01_demo01`
      1. 此处的`/`表示的是`http://服务器的IP地址:端口号/`
      2. 还可以在此添加其它模块的路径或者资源文件夹的路径, 每个添加进来的路径都要单独设置部署环境
      3. 当我们以该`Run/Debug Configuration`配置来运行Tomcat时, 所有在此添加的路径都会部署到Tomcat的`Servlet`中
   4. 当我们以该`Run/Debug Configuration`配置来运行Tomcat时, 本模块`web`文件夹下所有内容( 除了`WEB-INF`下的文件 ) 和本模块编辑的Servlet类( 在`web/WEB-INF/web.xml`文件中设置了url映射 ), 都类似于被放到了`Tomcat`软件下的`webapps`文件中的`task01_demo01`文件夹下了(`task01_demo01`是本模块的部署环境),  也就是说可以根据`url`来访问这些文件, **之所以说是类似, 是因为当不是以该`Run/Debug Configuration`配置来运行Tomcat时, 就不会产生这样的效果**

#### 4.2.2 创建`Sevlet`类

##### 4.2.2.1 第一种方式

创建一个`Servlet`类, 比如`DemoServlet extends HttpServlet`, 需要在`web/WEB-INF/web.xml`文件中设置该`Servlet`的`url映射`, 格式如下

1. ```xml
       <servlet>
           <!--servlet类的别名-->
           <servlet-name>DemoServlet</servlet-name>
           <!--servlet类的全名,即包含包名-->
           <servlet-class>com.zq.DemoServlet</servlet-class>
       </servlet>
       <servlet-mapping>
           <!--必须跟上面的servlet类的别名相同-->
           <servlet-name>DemoServlet</servlet-name>
           <!--该servlet类的url映射-->
           <url-pattern>/demo</url-pattern>
       </servlet-mapping>
   ```

- ==这样该servlet就相当于以`demo`为名字, 放到了`webapps/task01_demo01`下了, 也就是说以`http://服务器的IP地址:端口号/task01_demo01/demo`就能访问该`Servlet`类了==
- `<url-pattern>`的内容中的`/`相当于`http://服务器的ip地址:端口号/模块部署环境/`, 并且只能是`/路径`的格式

##### 4.2.2.2 第二种方式

- 或者使用注解的方式设置该`Servlet`类的`url映射`, 这样就不需要到`web.xml`文件中设置该`Servlet`的`url映射`了
- `urlPatterns`的内容里的`/`相当于`http://服务器的ip地址:端口号/模块部署环境/`, 并且只能是`/路径`的格式

```java
@WebServlet(name = "Demo5Servlet", urlPatterns = "/demo5")
   public class Demo5Servlet extends HttpServlet {
```

#### 4.2.3 为web应用模块添加依赖或依赖库

**分两步**

1. 像java应用模块那样为web应用模块添加依赖或依赖库
2. `Project Stucture`→`Project Setting`→`Artifacts`, 点击需要添加依赖的模块, 然后点击`Output Layout`, 在右侧的`Avaliable Elements`中会显示本项目下的所有模块, 点开之后就是能在执行时的`WEB-INFO`文件夹中添加依赖或者依赖库了, 直接双击就可以把jar包或者模块添加到执行时的`WEB-INFO/classes`文件中, 可以把库添加到执行时的`WEB-INFO/lib`中

#### 4.2.4 `jsp`、``html`文件中的`"/"`  `""`  `"../"`所表示的url

- `"/"`表示的是根路径或者根url, 即`http://服务器的ip地址:端口号/`， 所以`""`和`"../"`是可以使用的
- `""`表示的是相对路径和相对url, 相对路径指的是本文件的父目录, 相对url指的是该目录的url
- `"../"`表示的是相对路径的父目录和相对url的父url

#### 4.2.5 `web.xml`中的`<url-pattern>`和注解中的`urlPatterns`中的`"/"`

- ==这些情况下的`"/"`表示的是当前模块的部署环境, 所以`""`和`"../"`不能使用==

- `web.xml`中的`<url-pattern>`和注解中的`urlPatterns`的值只能是`/资源路径`的格式, 如果不遵循此格式写的话, 就会发生以下异常

  - ```java
    无法启动组件[StandardEngine[Catalina].StandardHost[localhost]
    ```

  

### 4.3 Servlet接口

#### 4.3.1 基本概念

- javax.servlet.Servelt接口用于定义所有servlet必须实现的方法

#### 4.3.2 常用的方法

| 方法                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| void init(ServletConfig config)                              | 由Tomcat的servlet容器调用, 以向servlet指示该servlet正在被放入服务中 |
| void service(ServletRequest requset, ServletResponse response) | 由Tomcat的servlet容器调用, 以允许servlet响应请求             |
| ServletConfig getServletConfig()                             | 返回ServletConfig对象, 该对啊ing包含此servlet的初始化和启动擦函数 |
| String getServletInfo()                                      | 返回有关servlet的信息, 如作者  版本和版权                    |
| void destroy()                                               | 由Tomcat的servlet调用, 以向servlet指示该servlet正在退出服务  |

#### 4.3.3 `GenericServlet`

##### 4.3.3.1 基本概念

- javax.servlet.GenericServlet抽象类实现了[Servlet接口](#4.3 Servlet接口)
- 该类主要用于定义一个通用的的 与协议无关的servlet
- 若通过继承该抽象类来编写通用servlet, 则只需重写抽象方法`void service(ServletRequest request, ServletResponse response)`即可

##### 4.3.3.2 常用方法

| 方法                                                         | 说明                                             |
| ------------------------------------------------------------ | ------------------------------------------------ |
| abstract void service( ServletRequest request, ServletResponse response) | 由Tomcat的servlet容器调用, 以晕血servlet响应请求 |

#### 4.3.4 `HttpServlet`

##### 4.3.4.1 基本概念

- javax.servlet.http.HttpServlet抽象类继承了[GenericServlet](#4.3.3 )抽象类
- 用于创建适用于网站的HTTP Servlet, 该类的子类必须至少重写一个方法

##### 4.3.4.2 常用的方法

| 方法                                                     | 说明                                                |
| -------------------------------------------------------- | --------------------------------------------------- |
| void init()                                              | 进行初始化操作, 只在本servlet被第一次访问时执行一次 |
| void service(HttpRequest request, HttpResponse response) | 根据请求的方式决定是调用doGet还是doPost             |
| void doGet(HttpRequest request, HttpResponse response)   | 处理客户端的GET请求                                 |
| void doPost(HttpRequest request, HttpResponse response)  | 处理客户端的POST请求                                |
| void destroy()                                           | 删除本Servlet实例时会执行一次                       |

### 4.4 Servlet的生命周期

![](%E7%AC%AC01%E7%AB%A0%20Servlet%E6%A0%B8%E5%BF%83%E6%8A%80%E6%9C%AF(%E4%B8%8A).assets/%E7%AC%AC%E4%B8%80%E7%AB%A0%20Servlet%E6%A0%B8%E5%BF%83%E6%8A%80%E6%9C%AF%EF%BC%88%E4%B8%8A%EF%BC%89.png)

- 构造方法只被调用一次, 当第一次请求Servlet时调用构造方法来创建Servlet的实例
- init方法只被调用一次, 当创建号Servlet实例后立即调用该方法实现Servlet的初始化
- service方法被多次调用, 每当有请求时都会调用service方法来进行响应
- destroy方法只被调用一次, 当该Servlet实力所在的Web应用被卸载前嗲用该方法来释放当前占用的资源

### 4.5 POST和GET请求(重点)

#### 4.5.1 GET请求

##### 4.5.1.1 客户端发出GET请求的主要方式

1. 在浏览器输入url, 然后按回车
2. 点击`<a>`超链接
3. 点击表单的提交按钮, 该表单的的属性`method="get"`

##### 4.5.1.2 GET请求特点

会将请求数据添加到请求url地址的后面, 只能提交少量的文本数据, 不安全

#### 4.5.2 POST请求

##### 4.5.2.1 客户端发出POST请求的主要方式

点击表单的提交按钮, 该表单的的属性`method="post"`

##### 4.5.2.2 POST请求特点

请求数据添加到了请求体中, 可提交大量文本数据和二进制数据, 安全性好

### 4.6 ServletRequest接口

#### 4.6.1 基本概念

- javax.servlet.ServletRequest接口主要用于向servlet提供客户端请求信息, , 可以从中获取到任何请求信息
- Tomcat的Sevlet容器创建一个ServletRequest对象, 并将器作为参数传递给Servlet对象的service方法

#### 4.6.2 常用方法

| 方法                                      | 说明                                                         |
| ----------------------------------------- | ------------------------------------------------------------ |
| String getRemoteAddr()                    | 获取发送请求的客户端的ip地址                                 |
| int getRemotePort()                       | 获取发送请求的客户端的端口                                   |
| Map<String, String[]> getParameterMap()   | 返回请求参数name-value键值对集合                             |
| Enumeration getParameterNames()           | 获取包含所有请求参数name的字符串对象的枚举, 如果请求没有参数, 则会获取到一个空枚举 |
| String [] getParameterValues(String name) | 获取请求参数name对应的所有value, 并将这些value放到一个字符串数组中, 返回这个数组, 如果不存在这个参数name, 则返回null |
| String getParameter(String name)          | 获取请求参数name对应的所有value, 并将这些value放到一个字符串中, 不同的value间以`,`分隔, 返回这个字符串, 如果不存在这个参数name, 则返回null |
| void setCharacterEncoding(String charset) | 要求浏览器的以charset的方式编码请求体的文本信息              |

### 4.7 HttpServletRequest接口

#### 4.7.1 基本概念

- javax.servlet.http.HttpServletRequst接口时[ServletRequest](#4.6 ServletRequest接口)接口的子接口, 主要用于提供HTTP请求信息的功能
- 在浏览器中, 不同于需要用户输入的表单数据, HTTP请求头数据是由浏览器定义的
- 可直接通过HttpServletRequest对象提供的一系列get方法获取请求头数据

#### 4.7.2 常用方法

| 方法                                      | 说明                                                         |
| ----------------------------------------- | ------------------------------------------------------------ |
| String getServletPath()                   | 获取此请求中调用的servlet的urlPatterns值<br />/demo01        |
| String getRequestURI()                    | 获取请求的资源的路径信息<br />/task01-demo01/demo01          |
| StringBuffet getRequestURL()              | 获取请求的完整url路径信息<br />http://localhost:8080/task01-demo01/demo01 |
| String getQueryString()                   | 获取HTTP请求的url路径后面附带的请求参数<br />name=tifa&password=11111111 |
| String getMethod()                        | 获取发送此HTTP请求的方式, 例如GET  POST                      |
| void setCharacterEncoding(String charset) | 要求浏览器以charset方式编码请求体中的文本内容                |

### 4.8 ServletResponse接口

#### 4.8.1 基本概念

- javax.servlet.ServletResponse接口用于定义一个对象来帮助Servlet向客户发送响应
- Tomcat的Servlet容器会创建ServletResponse对象, 并将其作为参数传递给servlet的service方法

#### 4.8.2 常用方法

| 方法                             | 说明                                             |
| -------------------------------- | ------------------------------------------------ |
| String getCharacterEncoding()    | 获取响应内容的编码方式                           |
| void setContentType(String type) | 设置响应内容的类型, 剋以避免客户端接受的页面乱码 |
| PrintWriter getWriter()          | 获取可向客户端发送字符文本的PrintWriter对象      |

### 4.9 HttpServletResponse接口

#### 4.9.1 基本概念

- javax.servlet.http.HttpServletREsponse接口继承ServletResponse接口, 以便在发送响应式提供特定的http功能

#### 4.9.2 常用方法

| 方法                               | 说明                                              |
| ---------------------------------- | ------------------------------------------------- |
| void sendRedirect(String location) | 使用指定的重定向位置url像客户端发送临时重定向相应 |

### 4.10 接收请求和发送响应的示例

#### 4.10.1 index.jsp准备

```html
<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2021/5/1
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <form action="demo01" method="method">
      <label>
        姓名:<input name="name" type="text" />
      </label><br/>
      <label>
        密码:<input name="password" type="text"/>
      </label><br/>
      <input type="submit" value="注册"/>
    </form>
  </body>
</html>

```

#### 4.10.2 接收请求和发送响应的示例

```java
   //设置请求的编码为'utf-8', 让浏览器以此编码来发送请求体, 避免乱码
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        //设置像议题内容的类型, 然浏览器清楚服务器发送的什么东西, 以便浏览器正确解码
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("<h1>姓名: " + name + "</h1>" + "<h1>密码: " + password
                + "</h1>");
```

### 4.11 ServletConfig接口(熟悉)

#### 4.11.1 基本概念

- javax.servlet.ServletConfig接口用于描述Servlet本身的相关配置信息, 在初始化期间用于将信息传递给Tomcat的Servlet容器来配置Servlet对象
- 在Servlet类中通过`this.getServletConfig()`方法获取
- ==每个servlet的ServletConfig对象都是独一无二不可共享的==

#### 4.11.2 配置方式

```xml
<!-- 在web.xml中配置ServletConfig初始化参数 -->
<servlet>
	<servlet-name>ConfigDemoServlet</servlet-name>
    <servlet-class>com.zq.ConfigDemoServlet</servlet-class>
    <!-- 配置Servlet的初始化参数 -->
    <init-param>
    	<!-- 参数名 -->
        <param-name>name</param-name>
        <param-value>value</param-value>
    </init-param>
</servlet>
```

#### 4.11.3 常用的方法

| 方法                                 | 功能                                                         |
| ------------------------------------ | ------------------------------------------------------------ |
| String getServletName()              | 获取Servlet的别名                                            |
| String getInitParameter(String name) | 获取初始化参数名name对应的参数值字符串, 如果该参数不存在, 则返回null |
| Enumeration getInitParameterNames()  | 将Servlet的所有初始化参数的名称作为字符串对象的枚举返回, 如果servlet没有初始化参数, 则返回空枚举 |
| ServletContext getServletContext()   | 获取ServletContext的引用                                     |

### 4.12 ServletContext接口

#### 4.12.1 基本概念

- javax.servlet.ServletContext接口主要用于定义一组方法, Servlet使用这些方法可以与Tomcat的Servlet容器通信
- Tomcat的Servlet容器在启动时会为同一部署环境下的Servlet创建一个唯一且共享的ServletContext对象, 用于实现在这些Servlet之间的信息共享和通信
- 在Servlet类中通过`this.getServletContext()`方法可以获得ServletContext对象

#### 4.12.2 配置方式

```xml
<!-- 在web.xml的根节点下配置ServletContext初始化参数-->
<context-param>
	<param-name>username</param-name>
    <param-value>Mercy</param-value>
</context-param>
<context-param>
	<param-name>password</param-name>
    <param-value>Hero's never die.</param-value>
</context-param>
```

#### 4.12.3 常用的方法

| 方法                                         | 声明                                                         |
| -------------------------------------------- | ------------------------------------------------------------ |
| String getInitParameter(String name)         | 获取初始话参数name的值的字符串, 如果该参数不存在, 则返回null |
| Enumeration getInitParameterNames()          | 将本部署环境下的所有context-param中的初始化参数名称都作为字符串对象的美剧返回, 如果没有, 则返回空枚举 |
| void setAttribute(String name, Object value) | 将指定的属性名和属性值绑定到本对象                           |
| Object getAttribute(String name)             | 根据指定的属性名获取属性值(一般只用来获取自定义的属性值)     |
| void removeAttribute(String name)            | 删除指定的属性                                               |
| String getRealPath(String path)              | 获取本部署环境路径,;例如:`/task01-demo01`                    |
| InputStream getResourceAsStream(String path) | 将位于指定路径的资源作为InputStream对象返回                  |



## 五. Servlet请求的中文乱码问题

### 5.1 Servlet请求的中文乱码原因

- 浏览器在提交表单时, 会对中文参数值进行编码, Tomcat服务器接收到浏览器请求后会对请求参数解码, 当浏览器的编码与Tomcat服务器的解码方式不一致时, 就会导致乱码

### 5.2 Servlet请求的中文乱码解决思路

- Tomcat服务器是以`UTF-8`的方式解码请求中的字符串参数的
- 所以我们需要做的就是要求浏览器对请求的参数以`UTF-8`的方式编码

#### 5.2.1 POST请求中的中文乱码

要求浏览器以`utf-8`的方式编码请求体, 通过`ServletRequest`对象的`void setCharacterEncoding(String charset)`来实现

```java
//设置请求的编码为'utf-8', 让浏览器以此编码来发送请求体, 避免乱码
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
```

#### 5.2.2 GET请求中的中文乱码

- 从Tomcat8.0开始, 浏览器的url会以`utf-8`的方式时编码后发送给Tomcat服务器, 而Tomcat服务器也是以`utf-8`解码的, 所以不会出现中文乱码

- 但在Tomcat8.0之前, 浏览器的url是以`iso-8859-1`的方式时编码后发送给Tomcat服务器, 而Tomcat服务器也是以`utf-8`解码的, 所以肯定会出现中文乱码, 这种情况有两种解决方法

  - 第一种: 对Tomcat软件进行设置, 要求url的编码方式跟请求体的编码方式一致, 打开对Tomcat软件下的`conf/server.xml`文件, 在`<connector>`标签中添加属性`userBodyEncodingForURI="true"`, 这样当通过`request.setCharacterEncoding("utf-8")`设置请求体的编码方式时, 也会同时设置url的编码方式

  - 第二种: 在后端先将参数值使用ISO-8859-1编码方式转为字节数组，再将其使用UTF-8来进行解码，示例代码如下：

    - ```java
      name = new String(name.getBytes("ISO_8859-1"),"UTF-8");
      ```

    - ==但我觉得这种方法逻辑上很不靠谱, 字节数组在不同的编码和解码方式间转换时会导致字节的丢失, 最终得到的也是有偏差的结果, 建议还是使用第一种方式, 或者干脆将Tomcat服务器升级到8.0版本之后==

## 六. Sevlet的工作过程

1. Web应用在Tomcat运行时进行环境部署, Tomcat上的Servlet容器就知道有这么个Web应用部署上来了, 并且获得了这个Web应用的`web/WEB-INFO/web.xml`文件, 初始化了一个ServletContext对象, 这个对象有且只有一个, 可以被该Web应用下的所有Servlet对象获取到
2. 当该Web应用下的某个`url-pattern`被访问到时, Tomcat上的Servlet容器就会根据与该`url-pattern`绑定的`servlet-name`找到`servlet-class`, 然后将该`servlet-class`实例化,  `servlet-class`实例化时, 会运行`init()`方法并生成一个专属于该对象的`ServletConfig`对象, 然后调用该`Servlet`对象的`service`方法
3. 当第二次访问该`url-pattern`时, Servlet容器会创建一个子线程, 在这个子线程里复用上次对应类的实例对象, 调用该对象的service方法





