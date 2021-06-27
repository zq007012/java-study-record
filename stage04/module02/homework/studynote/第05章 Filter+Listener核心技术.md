# 第05章 Filter+Listener核心技术

[toc]

## 一. Filter过滤器

### 1.1 基本概念

- Filter本意为"过滤"的含义, 是JavaWeb的三大组件之一, JavaWeb的三大组件为: `Servlet`、`Filter`、`Listener`
- 过滤器相当于浏览器与Web资源间的一道过滤网，在访问资源之前通过一系列的过滤器对请求进行修改、判断以及拦截等，也可以对相应进行修改、判断以及拦截等。

### 1.2 工作方式

![](%E7%AC%AC05%E7%AB%A0%20Filter+Listener%E6%A0%B8%E5%BF%83%E6%8A%80%E6%9C%AF.assets/Filter%E7%9A%84%E5%B7%A5%E4%BD%9C%E6%96%B9%E5%BC%8F.png)

### 1.3 使用方式

#### 1.3.1 自定义类实现`Filter`接口并重写`doFilter`方法

- `chain.doFilter(request,response)`的作用: 如果还有下一个过滤器, 就将请求放行给下一个过滤器去处理, 如果没有下一个过滤器, 那就可以访问资源了

```java
public class LoginFilter implements Filter {
  
    public void doFilter(ServletRequest request, ServletResponse response,
        FilterChain chain) throws IOException,ServletException {
        //TODO 处理逻辑，如果不拦截, 就必须调用下面的方法来放行
        chain.doFilter(request,response);
    }
    
    @Override
    public void init(FilterConfig config) throws ServletException {

    }
    
    @Override
    public void destroy() {
    }
}
```

#### 1.3.2 在web.xml中注册过滤器

- `<filter>`节点和`<filter-mapping>`节点, 
  - 这两类节点是根节点的子节点
  - 每个Filter类只能对应一个`<filter>`节点
  - 而一个`<filter>`节点可以对应多个`<filter-mapping>`节点

##### 1.3.2.1 `<filter>`节点

- `<filter>`节点主要是为Filter类起一个名字, 斌且可以设置初始化参数

  - `<filter>`节点下还可以设置`<init-parm>`节点, 用来设置一些初始化参数, 这些参数可以在`Filter`实现类下的`init`方法中获取

    - ```xml
      <init-param>
                  <param-name>excludePath</param-name>
                  <param-value>/login;/register</param-value>
      </init-param>
      ```

##### 1.3.2.2 `<filter-mapping>`节点

- `<filter-mapping>`节点规定了指定名字的Filter类需要过滤哪些url

- `<filter-mapping>`节点有4个常用的子节点
- `<filter-name>`    必须有且只能有一个
  - `<url-pattern>`    需要被该过滤器过滤的url, 可以有多个
  - `<servlet-name>`    需要被该过滤器过滤的servlet的name, 可以有多个, `<url-pattern>`节点和`<servlet-name>`节点必须至少有一个
  - `<dispatcher>`      是否过滤转发的网址, 默认是不过滤转发的网址, 有FORWARD, REQUEST, INCLUDE, ASYNC, 和 ERROR 5个值
    - REQUEST    过滤转发的请求
    - FORWARD    过滤转发的网址
    - INCLUDE    过滤jsp文件中通过`<jsp:include>`动作转发的网址

###### 1.3.2.2.1 示例代码

```xml
    <filter>
        <filter-name>loginFilter</filter-name>
        <filter-class>com.zq.LogInFilter</filter-class>
        <init-param>
            <param-name>excludePath</param-name>
            <param-value>/register</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>loginFilter</filter-name>
        <url-pattern>/login</url-pattern>
        <servlet-name>exampleServlet</servlet-name>
        <dispatcher>FORWARD</dispatcher>
        <dispatcher>INCLUDE</dispatcher>
        <dispatcher>REQUEST</dispatcher>
    </filter-mapping>
```

- `/*`表示该过滤器要过滤所有访问本部署环境下资源的url

```xml
    <filter>
        <filter-name>logInFilter</filter-name>
        <filter-class>com.zq.LogInFilter</filter-class>
        
    </filter>
    <filter-mapping>
        <filter-name>logInFilter</filter-name>
        <!-- /* 表示本环境下的所有资源都要经过本过滤器过滤-->
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```

#### 1.3.3 也可以使用注解`@WebFilter`代替在web.xml中注册过滤器

**@WebFilter常用的的5个成员变量**

- **filterName**    是String类型, 对应`<filter-name>`节点
- **urlPatterns**    是String[]类型, 对应多个`<url-pattern>`节点
- **servletNames**    是String[]类型, 对应多个`<servlet-name>`节点
- **dispatcherTypes **   是DispatcherType[]类型(枚举数组), 默认值是{DispatcherType.REQUEST}, 对应多个`<dispathecr>`节点
- **initParams**    是WebInitParam[]类型(注解数组), 对应多个`<init-param>`节点, 值可以这么写`{@WebInitParam(name="account", value="admin"), @WebInitParam(name="password", value="123456")}`

#### 1.3.4 Filter接口

##### 1.3.4.1 基本概念

- `javax.servlet.Filter`接口主要用于描述过滤器对象, 可以对**资源的请求(request)**和**资源的响应(response)**进行筛选操作

##### 1.3.4.2 常用方法

| 方法                                                         | 功能                   |
| ------------------------------------------------------------ | ---------------------- |
| void init(FilterConfig filterConfig)                         | 实现过滤器的初始化操作 |
| void doFilter(ServletRequest request, ServlterResponse response, FilterChain chain) | 执行过滤操作的功能     |
| void destroy()                                               | 实现过滤器的销毁       |

#### 1.3.5 FilterConfig接口

##### 1.3.5.1 基本概念

- `javax.servlet.FilterConfig`接口主要用于描述过滤器的配置信息
- 可以获取到在`web.xml`中为该过滤器设值的名称、在`web.xml`中为该过滤器设置的所有初始化参数、本部署环境的对象

##### 1.3.5.2 常用方法

| 方法                                 | 功能                     |
| ------------------------------------ | ------------------------ |
| String getFilterName()               | 获取过滤器的名字         |
| String getInitParameter(String name) | 获取指定的初始化参数信息 |
| Enumeration getInitParameterNames()  | 获取所有的初始化参数名称 |
| ServletContext getServletContext()   | 获取ServletContext对象   |

#### 1.3.6 多个过滤器的使用

- 如果多个过滤器要过滤同一个资源的请求和响应, 那么Tomcat的Servlet容器会依据`web.xml`中的`fiterName-urlPatter`映射的顺序, 也就是`<filter-mapping>`节点的顺序来依次调用对应的过滤器
- 这些操作是通过`Filter`实现类中的`doFilter`方法中的``chain.doFilter(request,response);`这个语句实现的
- 而且这些操作都是在同一个线程里进行的, 所以过滤器链中的过滤器的`doFilter`方法的执行结束的顺序就像递归方法的完成顺序似的, 只有最内层的过滤器的`doFilter`方法执行结束了, 外层过滤器才会由内向外一层一层地结束
- ![](%E7%AC%AC05%E7%AB%A0%20Filter+Listener%E6%A0%B8%E5%BF%83%E6%8A%80%E6%9C%AF.assets/%E8%BF%87%E6%BB%A4%E5%99%A8%E9%93%BE%E4%B8%AD%20requet%E5%92%8Cresponse%20%E7%9A%84%E4%BC%A0%E9%80%92%E9%A1%BA%E5%BA%8F.svg)

#### 1.3.7 过滤器的优点

- 实现代码的"可拔插性", 及增加或减少某个功能模块, 不会影响程序的正常执行
- 可以将多个相同处理逻辑的模块集中写在过滤器里面, 可实现重复利用, 也方便代码的维护

## 二. Listener监听器

### 2.1 基本概念

- Servlet规范中定义的一种特殊的组件, 用来监听Servlet容器产生的事件并进行相应的处理
- 容器产生的事件分类如下:
  - 生命周期相关的事件
  - 属性状态关的事件
  - 存值状态相关的事件
- 底层原理是采用接口回调的方式实现

### 2.2 基本分类

| 监听器类型                                       | 功能                                |
| ------------------------------------------------ | ----------------------------------- |
| javax.servlet.ServletRequestListener             | 监听request作用域的创建和销毁       |
| javax.servlet.http.HttpSessionListener           | 监听session作用域的创建和销毁       |
| javax.servlet.ServletContextListener             | 监听application作用域的创建和销毁   |
| javax.servlet.ServletRequestAttributeListener    | 监听request作用域属性的状态变化     |
| javax.servlet.http.HttpSessionAttributeListener  | 监听session作用域属性的状态变化     |
| javax.servlet.ServletContextAttributeListener    | 监听application作用域属性的状态变化 |
| javax.servlet.http.HttpSessionBindingListener    |                                     |
| javax.servlet.http.HttpSessionActivationListener |                                     |

### 2.3 监听器详解

#### 2.3.1 ServletRequestListener监听器

- 在每一个ServletRequest对象创建和关闭时都会通知ServletRequestListener监听器

##### 2.3.1.1 常用方法

| 方法                                                  | 功能                                                         |
| ----------------------------------------------------- | ------------------------------------------------------------ |
| void requestInitialized( **ServletRequestEvent** sre) | 当Tomcat的Servlet容器每创建一个ServletRequest对象后该方法就会被调用一次 |
| void requestDestroyed( **ServletRequestEvent **sre)   | 当Tomcat的Servlet容器每销毁一个ServletRequest对象前该方法就会被调用一次 |

###### 2.3.1.1.1 `ServletRequestEvent`对象的常用方法

| 方法                               | 功能                                                 |
| ---------------------------------- | ---------------------------------------------------- |
| ServletContext getServletContext() | 获取引发了该事件的 **请求** 所在的ServletContext对象 |
| ServletRequest getServletRequest() | 获取引发可该事件的ServletRequest对象                 |

##### 2.3.1.2 示例代码

###### 2.3.1.2.1 创建ServletContextListener接口的实现类

```java
public class MyRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println("请求销毁了...");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        System.out.println("创建请求...");
    }
}
```

###### 2.3.1.2.2 在web.xml文件中配置这个监听器

**在根节点下配置以下节点**

```xml
<listener>
        <listener-class>com.zq.MyRequestListener</listener-class>
</listener>
```

###### 2.3.1.2.3 使用注解`@WebListener`代替在web.xml文件中注册监听器

**只要使用注解`@WebListener`标记监听器类就行了**

```java
@WebListener
public class MyRequestListener implements ServletRequestListener{
    
}
```



#### 2.3.2 HttpSessionListener监听器

- 在每一个HttpSession对象创建和失效时都会通知HttpSessionListener监听器

##### 2.3.2.1 常用方法如下

- | 方法                                             | 功能                                                         |
  | ------------------------------------------------ | ------------------------------------------------------------ |
  | void sessionCreated( **HttpSessionEvent** hse)   | 当Tomcat的Servlet容器每创建一个HttpSession对象后该方法就会被调用一次 |
  | void sessionDestroyed( **HttpSessionEvent **hse) | 当每一个HttpSession对象因超时被销毁或调用HttpSession的invalidate()方法让它销毁前该方法就会被调用一次 |

###### 2.3.2.1.1 `HttpSessionEvent`对象的常用方法

- | 方法                     | 功能                              |
  | ------------------------ | --------------------------------- |
  | HttpSession getSession() | 获取引发了该事件的HttpSession对象 |

##### 2.3.2.2 示例代码

###### 2.3.2.2.1 创建HttpSessionListener接口的实现类

```java
public class MySessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("创建了session...");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("session销毁！");
    }
}
```

###### 2.3.2.2.2 在web.xml中配置这个监听器

```xml
<listener>
        <listener-class>com.zq.MyHttpSessionListener</listener-class>
</listener>
```

###### 3.3.2.2.3 使用注解`@WebListener`代替在web.xml文件中注册监听器

**只要使用注解`@WebListener`标记监听器类就行了**

```java
@WebListener
public class MysessionListener implements HttpSessionListener{
    
}
```



#### 2.3.3 ServleContextListener监听器

- 在ServletContext对象创建和关闭时都会通知ServleContextListener监听器

##### 2.3.3.1 常用方法如

- | 方法                                                  | 功能                                                         |
  | ----------------------------------------------------- | ------------------------------------------------------------ |
  | void contextInitialized( **ServletContextEvent **sce) | 当SevletContext对象创建的时候, 该方法就会被调用一次          |
  | void contextDestroyed( **ServletContextEvent** sce)   | 当ServletContext对象销毁的时候(例如关闭Web应用服务器或者重新加载该Web应用), 该方法就会被调用一次 |

###### 2.3.3.1.1 `ServletContextEvent`对象的常用方法

- | 方法                               | 功能                                 |
  | ---------------------------------- | ------------------------------------ |
  | ServletContext getServletContext() | 获取引发了该事件的ServletContext对象 |

##### 2.3.3.2 示例代码

###### 2.3.3.2.1 创建ServletContext接口的实现类

```java
public class MyContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContext对象创建了...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("销毁ServletContext对象...");
    }
}
```

###### 2.3.3.2.2 在web.xml中配置这个监听器或

```xml
<listener>
        <listener-class>com.zq.MyContextListener</listener-class>
    </listener>
```

###### 2.3.3.2.3 使用注解`@WebListener`代替在web.xml文件中注册监听器

**只要使用注解`@WebListener`标记监听器类就行了**

```java
@WebListener
public class MyContextListener implements ServletContextListener{
    
}
```



#### 2.3.4 ServletRequestAttributeListener监听器

- 向ServletRequest对象中添加、删除或修改一个属性的值的时候， 将会通知ServletRequestAttributeListener监听器

##### 2.3.4.1常用方法如下

- | 方法                                                      | 功能                                                         |
  | --------------------------------------------------------- | ------------------------------------------------------------ |
  | void attributeAdd(ServletRequestAttributeEvent srae)      | 当任意一个ServletRequest对象每增加一个属性该方法就会被调用   |
  | void attributeReplaced(ServletRequestAttributeEvent srae) | 当任意一个ServletRequest对象每修改一个属性的值该方法就会被调用 |
  | void attributeRemoved(ServletRequestAttributeEvent srae)  | 当任意一个ServletRequest对象每移除一个属性该方法就会被调用   |

###### 2.3.4.1.1 ServletRequestAttributeEvent对象常用的方法

- | 方法                               | 功能                         |
  | ---------------------------------- | ---------------------------- |
  | String getName()                   | 获取引发了该事件的属性的名称 |
  | Object getValue()                  | 获取引发了该事件的属性的值   |
  | ServletRequest getServletRequest() |                              |
  | ServletContext getServletContext() |                              |



##### 2.3.4.2 示例代码

###### 2.3.4.2.1 创建监听器的实现类

```java
public class MyReqeustAttributeListener implements ServletRequestAttributeListener{
    @Override
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.println("增加了属性" + servletRequestAttributeEvent.getName());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.println("属性" + servletRequestAttributeEvent.getName() + "被删除了" );
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.println("修改属性" + servletRequestAttributeEvent.getName());
    }
}
```

###### 2.3.3.2.2 在web.xml中配置这个监听器或

```xml
<listener>
        <listener-class>com.zq.MyRequestAttributeListener</listener-class>
</listener>
```

###### 2.3.3.2.3 使用注解`@WebListener`代替在web.xml文件中注册监听器

**只要使用注解`@WebListener`标记监听器类就行了**

```java
@WebListener
public class MyRequestListener implements ServletRequestListener{
    
}
```

#### 2.3.5 HttpSessionAttributeListener监听器

- 向HttpSession对象中添加、删除或修改一个属性的值的时候， 将会通知HttpSessionAttributeListener监听器

##### 2.3.5.1 常用方法如下

- | 方法                                                  | 功能                                                        |
  | ----------------------------------------------------- | ----------------------------------------------------------- |
  | void attributeAdd(HttpSessionBingdingEvent srae)      | 当任意一个HttpSession对象每增加一个属性该方法就会被调用     |
  | void attributeReplaced(HttpSessionBingdingEvent srae) | 当任意一个HttpSession对象每修改一个属性的值该方法就会被调用 |
  | void attributeRemoved(HttpSessionBingdingEvent srae)  | 当任意一个HttpSession对象每移除一个属性该方法就会被调用     |

###### 2.3.5.1.1 ServletRequestAttributeEvent对象常用的方法

- | 方法                     | 功能                              |
  | ------------------------ | --------------------------------- |
  | HttpSession getSession() | 获取发生了该事件的HttpSession对象 |
  | String getName()         | 获取引发了该事件的属性的名称      |
  | Object getValue()        | 获取引发了该事件的属性的值        |

##### 2.3.5.2 示例代码

###### 2.3.5.2.1 创建监听器的实现类

```java
public class MySessionAttributeListener implements HttpSessionAttributeListener{
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("增加了属性" + httpSessionBindingEvent.getName());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("属性" + httpSessionBindingEvent.getName() + "被删除！");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("修改属性" + httpSessionBindingEvent.getName());
    }
}
```

###### 2.3.3.2.2 在web.xml中配置这个监听器或

```xml
<listener>
        <listener-class>com.zq.MySessionAttributeListener</listener-class>
</listener>
```

###### 2.3.3.2.3 使用注解`@WebListener`代替在web.xml文件中注册监听器

**只要使用注解`@WebListener`标记监听器类就行了**

```java
@WebListener
public class MySessionListener implements HttpSessionAttributeListener{
    
}
```

#### 2.3.6 ServletContextAttributeListener监听器

- 向ServletContext对象中添加、删除或修改一个属性的值的时候， 将会通知ServletContextAttributeListener监听器

##### 2.3.6.1 常用方法如下

- | 方法                                                      | 功能                                                         |
  | --------------------------------------------------------- | ------------------------------------------------------------ |
  | void attributeAdd(ServletContextAttributeEvent srae)      | 当任意一个ServletContext对象每增加一个属性该方法就会被调用   |
  | void attributeReplaced(ServletContextAttributeEvent srae) | 当任意一个ServletContext对象每修改一个属性的值该方法就会被调用 |
  | void attributeRemoved(ServletContextAttributeEvent srae)  | 当任意一个ServletContext对象每移除一个属性该方法就会被调用   |


###### 2.3.6.1.1 ServletContextAttributeEvent对象常用的方法

- | 方法                               | 功能                                 |
  | ---------------------------------- | ------------------------------------ |
  | String getName()                   | 获取引发了该事件的属性的名称         |
  | Object getValue()                  | 获取引发了该事件的属性的值           |
  | ServletContext getServletContext() | 获取发生了该事件的ServletContext对象 |

##### 2.3.6.2 示例代码

###### 2.3.6.2.1 创建监听器的实现类

```java
public class MyContextbuteListener implements ServletContxtAttributeListener{
    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("增加了属性" + servletContextAttributeEvent.getName());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("属性" + servletContextAttributeEvent.getName() + "被删除！");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("修改属性" + servletContextAttributeEvent.getName());
    }
}
```

###### 2.3.3.2.2 在web.xml中配置这个监听器或

```xml
<listener>
        <listener-class>com.zq.MyContextAttributeListener</listener-class>
</listener>
```

###### 2.3.3.2.3 使用注解`@WebListener`代替在web.xml文件中注册监听器

**只要使用注解`@WebListener`标记监听器类就行了**

```java
@WebListener
public class MyContextListener implements ServletContextAttributeListener{
    
}
```

#### 2.3.7 HttpSessionBindingListener监听器

- HttpSeesion绑定监听器, 这个监听器接口是由seesion.setAttribute(name, obj)中的对象--`obj`**的类**来实现的, 当对象`obj`与session进行了绑定**(即通过session.setAtttribute(name, obj)方法, 对象obj保存到了session的属性中)**或者对象`obj`与session进行了解绑**(即通过session.removeAttribute(name)方法, 对象obj被从session的属性中移除了)**时, 将会通知对象`obj`作为监听器的对应的监听功能
- ==这个监听器不需要在web.xml文件中注册==

##### 2.3.7.1 常用方法如下

| 方法                                                         | 功能                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) | 对象--`obj`**的类**来实现了这种监听器, 当对象`obj`被添加或替换进session的属性中时就会调用这个方法 |
| void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) | 对象--`obj`**的类**来实现了这种监听器, 当对象`obj`被从session的属性中移除时就会调用这个方法 |

###### 2.3.7.1.1 HttpSessionBindingEvent常用的方法

| 方法                     | 功能                          |
| ------------------------ | ----------------------------- |
| String getName()         | 获取引发了该事件的属性的名称  |
| Object getValue()        | 获取引发了该事件的属性的值    |
| HttpSession getSession() | 获取发生了该事件的session对象 |

##### 2.3.7.2 示例代码

###### 2.3.7.2.1 创建监听器的实现类

```java
public class Person implements HttpSessionBindingListener {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("对象" + ((Person)httpSessionBindingEvent.getValue()).toString() +  "绑定到session中了");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("对象" + ((Person)httpSessionBindingEvent.getValue()).toString() +  "与session解绑了");
    }
}
```

###### 2.3.7.2.2 触发绑定与解绑事件

```jsp
<%@ page import="com.zq.Person" %><%--
  Created by IntelliJ IDEA.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>实现session中对象的绑定和解除</title>
</head>
<body>
<%
    // 准备一个Person类型的对象
    Person nami = new Person();
    nami.setName("娜美");
    nami.setAge(20);
    Person robin = new Person();
    robin.setName("罗宾");
    robin.setAge(30);
    // 将对象nami与session对象进行绑定
    session.setAttribute("person", nami);
    
    // 将对象robin与session绑定, 并将对象nami与seesion解绑
    session.setAttribute("person", robin);
    
    // 将对象robin与session解绑
    session.removeAttribute("person");
%>
</body>
</html>

```

###### 2.3.7.2.3 运行结果

```dos
对象Person{name='娜美', age=20}绑定到session中了
对象Person{name='罗宾', age=30}绑定到session中了
对象Person{name='罗宾', age=30}与session解绑了
```

#### 2.3.8 HttpSessionActivationListener监听器

##### 2.3.8.1 session的钝化与活化

###### 2.3.8.1.1 session的钝化

> 当服务器正常关闭时,还存活着的session(在设置时间内没有销毁) 会随着服务器的关闭被以文件`SESSIONS.ser`的形式存储在tomcat 的work 目录下,这个过程叫做Session 的钝化。

- `SESSIONS.ser`文件默认保存在tomcat 的work 目录下

- 可以在tomcat的conf/context.xml文件的根节点下配置以下节点来修改`SESSIONS.SER`文件的保存位置

  - ```xml
    <Manager className="org.apache.catalina.session.PersistentManager" saveOnRestart="true">
    		<!-- 配置`SESSIONS.SER`文件的保存位置-->
    		<store className="org.apache.catalina.session.FileStore" directory="SESSIONS.SER文件的父目录"/>
    	</Manager>
    ```

  

###### 2.3.8.1.2 session的活化

> 当服务器再次正常开启时,服务器会找到之前的“SESSIONS.ser” 文件，从中恢复之前保存起来的Session 对象，这个过程叫做Session的活化。

###### 2.3.8.1.3 session钝化与活化中的注意事项

- 只有在服务器正常关闭的条件下，还未超时的Session 才会被钝化成文件。当Session 超时、session调用了invalidate 方法或者服务器在非正常情况下关闭时，Session 都不会被钝化，因此也就不存在活化。
- **通过session.setAttribute(name, obj)方法保存在session的属性中的对象, 如果该对象的类实现了`Serializable `接口，那么该对象就可以跟随session一起钝化和活化**
- 在被钝化成“SESSIONS.ser” 文件时，不会因为超过Session 过期时间而消失，这个文件会一直存在，等到下一次服务器开启session活化后就会消失。
- 当多个Session 被钝化时，这些被钝化的Session 都被保存在一个文件中，并不会为每个Session 都建立一个文件
- ![](%E7%AC%AC05%E7%AB%A0%20Filter+Listener%E6%A0%B8%E5%BF%83%E6%8A%80%E6%9C%AF.assets/20180112124409204.png)

##### 2.3.8.2 HttpSessionActivationListener监听器

> 通过session.setAttribute(name, obj)方法保存在session的属性中的对象, 如果该对象的类实现了`Serializable `接口，那么该对象就可以跟随session一起钝化和活化

- 对象`obj`的类在实现HttpSessionActivationListener监听器后, 当该类对象在跟随seesion一起钝化与活化时, 就可以监听到session的钝化与活化事件, 注意: **对象`obj`的类必须同时实现`Serializable`接口**
- **这个监听器是不需要在web.xml文件中注册的**
- ==HttpSessionActivationListener监听器不能决定session是否钝化与活化, 这个监听器只能监听session是否发生了钝化与活化==

###### 2.3.8.2.1 常用方法

| 方法                                                         | 功能                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| void sessionWillPassivate(HttpSessionEvent httpSessionEvent) | 对象`obj`的类实现了这种监听器并且通过session.settribute(name, obj)方法被保存到了session的属性中, 当这个session对象被钝化时, 这个方法就会被调用 |
| void sessionDidActivate(HttpSessionEvent httpSessionEvent)   | 对象`obj`的类实现了这种监听器并且通过session.settribute(name, obj)方法被保存到了session的属性中, 当这个session对象被活化时, 这个方法就会被调用 |

###### 2.3.8.2.2 HttpSessionEvent的常用方法

| 方法                     | 功能                            |
| ------------------------ | ------------------------------- |
| HttpSession getSession() | 发生了钝化或者活化的session对象 |

### 2.4 监听器的应用

- 需求: 统计本Web应用的被访问次数、当前在线人数、
- 思路: 使用int requestCount来记录被访问次数, 使用int onlineCount来记录在线人数, 并且将这两个数字保存到ServletContext对象中,以便全局都可以访问到这连个数字

```java
package com.zq;

import javax.servlet.*;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @ClassName: RequestCountAndOnline
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/6/13 14:37
 * @Version: V1.0
 */
@WebListener
public class RequestCountAndOnlineCount implements ServletRequestListener,
        HttpSessionListener, ServletContextListener {
    private ServletContext context;
    private int requestCount;
    private int onlineCount;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.context = servletContextEvent.getServletContext();
        requestCount = 0;
        onlineCount = 0;
        setRequestCountAndOnlineCount();
    }

    private void setRequestCountAndOnlineCount() {
        setRequestCount();
        setOnlineCount();
    }

    /**
     * 设置在线人数
     */
    private void setOnlineCount() {
        context.setAttribute("onlineCount", onlineCount);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        
    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        // 新的请求产生, 访问次数加1
        requestCount += 1;
        setRequestCount();
    }

    /**
     * 设置Web应用的被访问次数
     */
    private void setRequestCount() {
        context.setAttribute("requestCount", requestCount);
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        //新的session产生, 在线人数加1
        onlineCount += 1;
        setOnlineCount();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        //有session销毁了, 说明有人下线了, 在线人数-1
        onlineCount -=1;
        setOnlineCount();
    }
}

```

