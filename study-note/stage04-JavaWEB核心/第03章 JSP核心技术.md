# 第03章 JSP核心技术

[toc]

## 一. JSP概述

### 1.1 JSP概念

- JSP是Java Server Page的简称, 跟Servlet一样可以动态生成HTML响应, JSP文件命名为xxx.jsp
- 与Servlet不同, JSP文件以HTML元素为主, 然后内嵌java代码段, 用于处理动态内容.

### 1.2 JPS示例

```jsp
<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2021/5/8
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Mercy</title>
  </head>
  <body>
  <h1><%="Mercy said: \"" + "Hero's never die.\""%></h1>
  </body>
</html>

```

### 1.3 JSP与Servlet关系

![](%E7%AC%AC03%E7%AB%A0%20JSP%E6%A0%B8%E5%BF%83%E6%8A%80%E6%9C%AF.assets/jsp%E4%B8%8Eservlet%E5%85%B3%E7%B3%BB.png)

## 二. JSP的语法

### 2.1 JSP语法结构

- 声明区
- 程序代码区
- 表达式
- 注释
- 指令和动作
- 内置对象

### 2.2 声明区

- 基本语法: 

  - ```jsp
    <%! %>
    ```

- 作用: 可以定义全局变量、方法、类

  - 这里的全局变量相当于`Servlet`类的成员变量, 方法相当于`Servlet`类的成员方法

- ```jsp
  <%--
    Created by IntelliJ IDEA.
    User: zq007
    Date: 2021/5/8
    Time: 15:27
    To change this template use File | Settings | File Templates.
  --%>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
    <head>
      <title>被访问的次数</title>
    </head>
    <body>
    <%! int count = 0 %>
    <h1>本页面被访问的次数<%=count + 1%></h1>
    </body>
  </html>
  
  ```



### 2.3 程序代码区

- 基本语法:

  - ```jsp
    <%程序代码区%>
    ```

- 说明: 可以定义局部变量以及放入任何的java程序代码, 比如流程控制语句、调用方法

- ```jsp
  <%@ page import="java.io.File" %>
  <%@ page import="java.awt.font.ImageGraphicAttribute" %><%--
    Created by IntelliJ IDEA.
    User: zq007
    Date: 2021/5/8
    Time: 16:04
    To change this template use File | Settings | File Templates.
  --%>
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
  <head>
      <title>程序代码区</title>
  </head>
  <body>
  <% 
      String relativePath = "images/"
      File dir = new File(application.getRealPath("images"));
      System.out.println(dir.getAbsoluteFile());
      File[] images = dir.listFiles();
      for (File image : images) {
  %>
  <img src="<%=relativePath + image.getName()%>" style="height: 300px" alt="<%=image.getPath()%>不存在"/><br/>
  <%
      }
  %>
  </body>
  </html>
  
  ```

### 2.4 表达式区

- 基本语法

  - ```jsp
    <%=... ...%>
    ```

- 说明: 可以输出一个变量或者一个具体内容, 但`=`后面必须是字符串变量或者可以被转换成字符串的表达式

- 注意: 表达式不需要以`;`结束, 因为表达式区只有一行

- 注意: ==所有的表达式区和程序代码区相当于写在service中的代码, 所以不同的表达式区和程序代码区间的局部变量是可以跨区调用的==

- ```jsp
  <% int count=0 %>
  <h1>
      被访问次数: <%=count%> 次
  </h1>
  ```

### 2.5 注释

| 方法                                                         | 说明                           |
| ------------------------------------------------------------ | ------------------------------ |
| `<!-- ... -->`                                               | HTML文件的注释, 浏览器可以看到 |
| `<%-- ... --%>`                                              | JSP文件的注释, 浏览器看不到    |
| **声明区内**和**程序代码区内**可以使用java语言中的单行注释和多行注释 | java语言中的注释, 浏览器看不到 |

### 2.6 指令和动作

#### 2.6.1 指令

- 指令格式:

  - ```jsp
    <%@指令 属性="属性值"%>
    ```

- 指令的属性可以设定多个

- JSP常用的指令有: `page`、`taglib`、`include`

##### 2.6.1.1 page指令

- page指令用于导包和设置一些页面属性, 常用属性如下

- | page指令常用属性 | 说明                                                         |
  | ---------------- | ------------------------------------------------------------ |
  | import           | 导入相应的java包, 唯一允许在同一jsp文件中多次出现的属性      |
  | contentType      | 设置Content-Type响应头, 表名即将发送到浏览器的文档类型, 一般是`"text/html;charset=utf-8"` |
  | pageEncoding     | 设置页面的编码, 一般是`"utf-8"`                              |
  | laguage          | 指定页面使用的编程语言, 一般是`"java"`                       |
  | session          | 控制页面是否参与HTTP会话, `"true"`为参与, `"false"`为不参与  |
  | errorPage        | 当本页面出现异常时, 把异常抛给errorPage指向的页面去处理, 同时跳转到errorPage指向的页面 |
  | isErrorPage      | 当前页面是否可以作为其他页面的错误处理页面                   |

##### 2.6.1.2 taglib指令

- taglib指令用来扩展JSP程序的标签元素, 引入其他功能的标签库文件

- ```jsp
  <!-- prefix属性用于指定库前缀 -->
  <!-- uri属性用于指定库的位置 -->
  <%@taglib uri="tagLibrary" prefix="prefix"%>
  ```

##### 2.6.1.3 include指令

- include指令用于引入另一个JSP程序或HTML文件等, 格式如下:

  - ```jsp
    <%@include file="jsp或html文件地址"%>
    ```

- JSP引擎会在JSP文件的转换时期先把file属性设定的文件包含进来, 然后开始执行转换及编译的工作

#### 2.6.2 动作

##### 2.6.2.1 引入页面的动作

- `<jsp:include>`标签和其子标签`<jsp:parame>`

- `<jsp:include>`动作用于引入另一个JSP程序或servlet

  - 属性`page`   用来引入另一个jsp文件或html文件
  - 属性`flush` 是否开启页面缓存，在include另一个jsp文件时，默认值为`"false"`，服务器会等待该文件被读到底端，然后才输出到客户端，并且销毁该次访问的request和response，而当把flush属性赋为`"true"` 时，在缓存累积了一定数据时，服务器会先提供一部分数据给浏览器，并等待后续内容 

- 子标签`<jsp:param>`的作用其实就是`request.setParameter(name, value)`, 在被引入的页面中可以通过`request.getParameter(name)`获取到`value`的值

- 执行到`<jsp:include>`标签时, 被引入的文件才会被编译

- 示例

  - ```jsp
    <jsp:include page="content.jsp" flush="true">
    	<jsp:parameter name="name" value="value"/>
        <!-- 在'content.jsp'页面中可以通过request.getParameter("name")获取到"name"对应的"value" -->
    </jsp:include>
    ```

###### 2.6.2.1.0 include指令和include动作的区别

- include指令是在jsp程序的转换时期就将file属性所指定的程序内容嵌入然后再编译执行(**静态包含**)
- include动作在jsp程序转换时期是不会被编译的, 只有在客户端请求时期被执行到才会被动态地编译载入(**动态包含, 推荐**)
- 也就是说include指令引入的子页面会嵌入到父页面中, 会跟父页面同时被编译成Servlet, 然后响应给浏览器, 而include动作引入的子页面在父页面编译成Servlet时, 仍旧是一段include动作标签代码, 只有浏览器访问父页面到了该include动作区域时, 才会向服务器请求这个子页面, 服务器才会对子页面进行编译, 然后将子页面响应给浏览器

##### 2.6.2.2 转发的动作

- `<jsp:forward>`标签和其子标签`<jsp:param>`

- forward动作用于在jsp中实现转发, 将请求转发到另一个指定的JSP程序或者Servlet中执行

  - 属性`page`   转发的目的地, 是个jsp文件的路径或者Servlet的路径

- 子标签`<jsp:param>`的作用其实就是`request.setParameter(name, value)`, 在转发目的地的页面中可以通过`request.getParameter(name)`获取到`value`的值

- 示例代码:

  - ```jsp
    <jsp:forward page="target.jsp">
    	<jsp:parameter name="name" value="value"/>
        <!-- 在'target.jsp'页面中可以通过request.getParameter("name")获取到"name"对应的value值 -->
    </jsp:forward>
    ```

##### 2.6.2.3 使用JavaBean的动作

- 使用JavaBean的动作有三种

###### 2.6.2.3.1 创建JavaBean对象

- ```jsp
  <jsp:useBean name="对象名" scope="保存范围" class="JavaBean类名的全称"/>
  ```

- scope可取的值有四个: `page`  `request`  `sesseion`  `application`, scope表示该对象保存的范围, 本质上就是这些内置对象通过其setAttribute(name, obj)方法保存了JavaBean对象

###### 2.6.2.3.2 获取指定JavaBean对象的指定属性的值

注意: 只能在该对象保存的范围下获取

- ```jsp
  <jsp:getProperty name="对象名" property="属性名"/>
  ```

###### 2.6.2.3.3 设置指定JavaBean对象的指定属性的值

注意: 只能在该对象保存的范围下设置

- 第一种设置方式
  - ```jsp
    <jsp:setProperty name="对象名" property="属性名" value="属性值"/>
    ```

- 第二种设置方法

  - ```jsp
    <jsp:setProperty name="对象名" property="属性名" param="parameterName"/>
    ```

注意: 

- **`value`属性和`param`属性不能同时出现,** 
- `value`属性的值就是这个JavaBean对象对应的属性的值, 
- 而`param`属性的值是request对象的`getParameter(String name)`方法中的`name`, 这个JavaBean对象对应属性的值其实是`request.getParameter("parameterName")`获取到的值

## 三. JSP内置对象

### 3.1 基本概念

- 在JSP程序中有9个内置对象, 由Tomcat的Servlet容器为用户进行实例化, 程序员可以不用定义就直接使用这些对象
- 在JSP转换成Server后, 会自动追加这些对象的定义, 使用内置对象可以简化JSP的开发

### 3.2 对象名称和对象类型

| 对象名称    | 对象类型            | 说明                       |
| ----------- | ------------------- | -------------------------- |
| page        | Object              | 浏览器本次访问的页面       |
| pageContext | PageContext         | 浏览器本次访问页面的上下文 |
| request     | HttpServletRequest  |                            |
| response    | HttpServletResponse |                            |
| out         | JSPWriter           |                            |
| session     | HttpSession         |                            |
| config      | ServletConfig       |                            |
| application | ServletContext      |                            |
| exception   | Throwable           |                            |

#### 3.2.1 page内置对象

- 表示的是浏览器本次访问的页面
- 同一个浏览器多次访问同一个jsp, 虽然每次显示的页面是相同的, 但本质上这些页面是每访问一次就会创建一个新的, 浏览器上看到的实际上是个新的页面, 所以page内置对象也是新的
- 因此可以断定page内置对象的作用范围比request还要小, 因为request可以跟随转发作用到另一个页面上, 而page内置对象只能作用在本页面上

#### 3.2.2 pageContext内置对象

- pageContext对象是PageContext类型的对象, 其实表示是浏览器本次访问页面的上下文

- | 常用方法                                                | 说明                                                         |
  | ------------------------------------------------------- | ------------------------------------------------------------ |
  | void setAttribute(String name, Object value, int scope) | [3.2.2.0 `setAttribute`和`getAttribute`方法详解](#3.2.2.0 和方法详解) |
  | Object getAttribute(String name, int scope)             | [3.2.2.0 `setAttribute`和`getAttribute`方法详解](#3.2.2.0 和方法详解) |
  | void removeAttribute(String name, int scope)            | [3.2.2.0 `setAttribute`和`getAttribute`方法详解](#3.2.2.0 和方法详解) |
  | Object getPage()                                        |                                                              |
  | ServletRequest getRequest()                             |                                                              |
  | ServletResponse getResponse()                           |                                                              |
  | JspWriter getOut()                                      |                                                              |
  | HttpSession getSession()                                |                                                              |
  | ServletConfig getServletConfig()                        |                                                              |
  | ServletContext getServletContext()                      |                                                              |
  | Exception getException()                                |                                                              |

##### 3.2.2.0 `setAttribute`和`getAttribute`方法详解

- scope有四个值可以取, 这个四个值分别封装成了PageContext类中的常量, 并且属性也不是被保存到了pageContext对象中, 而是被保存到了对应的scope对象中了
  - PageContext.PAGE_SCOPE    属性被保存到了page内置对象中了
  - PageContext.REQUEST_SCOPE  属性被保存到了request内置对象中了
  - PageContext.SESSION_SCOPE  属性被保存到了seesion内置对象中了
  - PageContext.APPLICATION_SCOPE  属性被保存到了application内置对象中了
- scope的默认值是PageContext.PAGE_SCOPE
- Object getAttribute(String name, int scope)其实就是在scope所表的的内置对象中查找属性name的值

#### 3.2.3 request内置对象

| 常用方法                                     | 说明 |
| -------------------------------------------- | ---- |
| void setAttribute(String name, Object value) |      |
| Object getAttribute(String name)             |      |
| void removeAtrribute(String name)            |      |



#### 3.2.4 response内置对象

#### 3.2.5 out内置对象

- out内置对象是一个缓冲的输出流, 用来给客户端输出文本信息

- | 常用方法               | 说明                                                         |
  | ---------------------- | ------------------------------------------------------------ |
  | void print(String x)   | 向客户端输出各种类型的数据                                   |
  | void newLine()         | 输出一个换行符                                               |
  | void println(String x) | 向客户端输出各种类型的数据并换行                             |
  | void flush()           | 清空缓冲区里的数据, 并把这些数据输出到客户端                 |
  | void close()           | 关闭输出流                                                   |
  | int getBurfferSize()   | 获取缓冲区的大小                                             |
  | int getRemaining()     | 获取缓冲区未使用部分的大小                                   |
  | void clearBuffer()     | 清空缓冲区里的数据, 并且这些数据**不会**输出到客户端         |
  | void clear()           | 清空缓冲区里的数据, 并且这些数据**不会**输出到客户端, 如果执行到笨方法时, 缓冲区已经被flush过了, 就抛出一个异常 |

- ==fluse  clear  clearBuffer三个方法间的异同==

  - 相同之处是都会清空缓冲区中的数据
  - 不同之处在于`flush`会将这些数据输出到客户端, 而`clear`和`clearBuffer`不会
  - `clear`和`clearBuffer`间的不同之处在在于, 当执行到clear方法时, 如果缓冲区已经被flush过了, 就抛出一个异常, 而clearBuffer则不会

#### 3.2.6 session内置对象

| 常用方法                                     | 说明 |
| -------------------------------------------- | ---- |
| void setAttribute(String name, Object value) |      |
| Object getAttribute(String name)             |      |
| void removeAtrribute(String name)            |      |



#### 3.2.7 config内置对象

#### 3.2.8 application内置对象

| 常用方法                                     | 说明 |
| -------------------------------------------- | ---- |
| void setAttribute(String name, Object value) |      |
| Object getAttribute(String name)             |      |
| void removeAtrribute(String name)            |      |



#### 3.2.9 exception内置对象

- exception对象是`Trowable`的实例, 表示的是JSP的异常信息
- **只有异常处理页面才可以使用该内置对象**, 可以将一个jsp页面设置为专门处理异常的界面

##### 3.2.9.1 设置异常处理页面的两种方式

==不建议开发阶段使用自定义异常处理页, 因为Tomcat默认的异常处理页面显示的信息更加全面, 更加美观==

###### 3.2.9.1.1 通过`page`指令的`isErrorPage`属性设置

- 在jsp页面中添加以下page指令, 可以将一个jsp页面设置为处理异常信息的页面, 可以把这个页面起名为`error.jsp`

  - ```jsp
    <%@page isErrorPage="true"%>
    ```

- 对另一个页面中的`page`指令中的`errorPage`属性进行以下设置, 当该页面产生异常时, 就会将异常抛给`error.jsp`页面去处理, 并且会跳转到`error.jsp`页面

  - ```jsp
    <%@page errorPage="error.jsp"%>
    ```



###### 3.2.9.1.2 在`WEB-INFO/web.xml`中为所有页面配置统一的异常处理页面

- 在jsp页面中添加以下page指令, 可以将一个jsp页面设置为处理异常信息的页面, 可以把这个页面起名为`error.jsp`

  - ```jsp
    <%@page isErrorPage="true"%>
    ```

- 在`WEB-INFO/web.xml`的根标签下添加一下子标签, 当**该模块下的没有自定义异常处理页面的任意页面**产生异常时, 都会将异常抛给`error.jsp`页面处理, 并且会跳转到`error.jsp`页面

  - ```xml
    <error-page>
    	<exception-type>java.lang.Throwable</exception-type>
        <location>/error.jsp</location>
    </error-page>
    ```

##### 3.2.9.2 打印错误信息

```jsp
<%=exception.getMessage()%>
```



## 四. MVC设计模式

### 4.1 基本概念

- MVC是模型(Model)和视图(View)以及控制器(Controller)的简写, 是一种将数据、界面显示和业务逻辑进行分离的组织方式, 这样**在改进界面及用户交互时, 不需要重写编写业务逻辑**, 从而提高了代码的可维护性
- M: 主要用于封装业务数据的JavaBean(Bean)和业务逻辑的Service以及访问数据库的DAO对象
- V: 主要负责数据收集和数据展现, 通常由JSP完成
- C: 主要负责流程控制和页面跳转, 通常由Servlet完成

### 4.2 基本模型

![](%E7%AC%AC03%E7%AB%A0%20JSP%E6%A0%B8%E5%BF%83%E6%8A%80%E6%9C%AF.assets/jsp%E4%B8%8Eservlet%E5%85%B3%E7%B3%BB-1620543512059.png)