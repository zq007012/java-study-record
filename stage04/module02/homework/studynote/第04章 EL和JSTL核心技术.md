# 第04章 EL和JSTL核心技术

[toc]

## 一. EL表达式(熟悉)

### 1.1 基本概念

- 格式: `${value}`
- **EL(Expression Language)**表达式提供了在JSP中简化表达式的方法, 可以方便地访问各种数据并输出
- 可以在body子元素或其后辈元素下, 直接通过EL表达式输入文本内容, 其实就是`<%=value%>`的另一种写法
  - **也就是说EL表达式内可以像表达式区那样调用各种对象的各种方法, 只要最后的结果是个可输出文本就行了, 也就是八大基本数据类型和字符串类型**
  - ==但是IDEA中EL表达式中的对象无法自动导包, 导包的话需要通过page指令的import属性手动导入==
- EL表达式**访问不到**在程序代码区中声明的变量
- EL表达式访问不到数据时, 结果会是空字符`""`, 而非`null`
- EL表达式的值最终会写在html文件中, 成文html文件的一部分
- EL表达式内可以调用对象的方法
- **EL表达式内不可以进行字符串的拼接**
- **EL表达式内获取的变量的类型会自动转换成其本来的类型**

### 1.2 主要功能

- 依次访问pageContext、request、session、和application作用域对象存储的**attribute**数据
- 获取请求(**request**)中的参数值(**param**)
- 访问JavaBean对象的属性
- 访问集合或数组中的数据
- 输出简单的运算结果(**加减乘除取余**、**比较大小的运算**、**逻辑运算**)

### 1.3 EL表达式中常用的内置对象

| 类别       | 标识符           | 描述                                                         |
| ---------- | ---------------- | ------------------------------------------------------------ |
| JSP        | pageContext      |                                                              |
| 作用域     | pageScope        | 是一个Map集合, 封装了所有通过`pageContext.setAttribute(String name, Object value)`方法设置的键-值对 |
|            | requestScope     | 是一个Map集合, 封装了所有通过`request.setAttribute(String name, Object value)`方法设置的键-值对 |
|            | sessionScope     | 是一个Map集合, 封装了所有通过``session.setAttribute(String name, Object value)`方法设置的键-值对 |
|            | applicationScope | 是一个Map集合, 封装了所有通过`session.setAttribute(String name, Object value)`方法设置的键-值对 |
| 请求参数   | param            | 是一个Map集合, 封装了请求参数中的所有键-值对,<br /> 键是`String`类型, 值也是`String`类型 |
|            | paramValues      | 是一个Map集合, 封装了请求参数中的所有键-值对, <br />键是`String`类型, 值也是`String[]`类型(**字符串数组**) |
| 请求头     | header           | 是一个Map集合, 封装了请求头中的所有键-值对, <br />键是`String`类型, 值也是`String`类型 |
|            | headerValues     | 是一个Map集合, 封装了请求头中的所有键-值对, <br />键是`String`类型, 值也是`String[]`类型(**字符串数组**) |
| Cookie     | cookie           | 是一个Map集合, 封装了cookie中的所有键-值对,<br /> 键是String类型, 值也是String类型 |
| 初始化参数 | initParam        | 是一个Map集合, 封装了`web.xml`文件中所有`<context-param>`节点下设置的键-值对和通过`application.setInitParam(String name, String value)`方法设置的所有键值对, <br />键是String类型, 值也是String类型 |

- 既然这些内置对象是Map集合, 那么所有Map集合具有的方法, 这些内置对象都是可以使用的
- 注意, 从内置对象中获取某个键对应的值时,如果该键是不存在的, 那么**返回值是个**空字符串`""`, **而非**`null`

### 1.4 访问内置对象的数据

- 访问内置对象的数据时可以简写
- EL表达式中的作用域内置对象可以不写, 这样的话就会就依次访问pageContext、request、session、和application作用域对象存储的**attribute**数据, 从哪个对象中先获得了就用哪个的, 剩下的就不用管了.

```jsp
<!-- 网址的后面加上?name="黑百合"-->
<% pageContext.setAttribute("name", "黑百合");%>
<p>pageScope: ${pageScope.get("name")}</p>
<p>pageScope: ${pageScope.name}</p>
<p style="background:red">
    pageScope: ${pageScope.age}
</p>

<% request.setAttribute("name", "吉斯·帝·拉伊");%>
<p>requestScope: ${requestScope.get("name")}</p>
<p>requestScope: ${requestScope.name}</p>

<% session.setAttribute("name", "波雅·汉库克");%>
<p>sessionScope: ${sessionScope.get("name")}</p>
<p>sessionScope: ${sessionScope.name}</p>

<% application.setAttribute("name", "妮可·罗宾");%>
<p>applicationScope: ${applicationScope.get("name")}</p>
<p>applicationScope: ${applicationScope.name}</p>

<!-- 不写作用域内置对象 -->
<p>
    不写作用域内置对象的属性的值: ${name}
</p>
<!-- 键值对的类型是 String - String -->
<p>param: ${param.get("name")}</p>
<p>param: ${param.name}</p>
<!-- 键值对的类型是 String - String[] -->
<p>paramValues: ${paramValues.get("name")}</p>
<p>paramValues: ${paramValues.name}</p>

<p>header: ${header.get("Accept")}</p>
<p>header: ${header.accept}</p>
<p>headerValues: ${headerValues.get("Accept")}</p>
<p>headerValues: ${headerValues.Accept}</p>

<p>cookie: ${cookie.get("JSESSIONID")}</p>
<p>cookie: ${cookie.JSESSIONID}</p>

<p>initParam: ${initParam.get("name")}</p>
<p>initParam: ${initParam.name}</p>
```

### 1.5 访问JavaBean对象的属性

- 访问JavaBean对象**之前必须先导入**这个JavaBean类

#### 1.5.1 访问方式

- 方式一: `${对象名.属性名}​`, 例如:`${student.name}`
- 方式二: `${对象名["属性名"]}`, 例如: `${student["name"]}`

#### 1.5.2 主要区别

- 当要存取的属性名中包含一些特殊字符, 如: `.`或`,`等Java标识符之外的符号, 就一定要使用`[]`而非`.`的方式

- 使用`[]`的方式可以动态取值, 具体方式如下

  - ```jsp
    <% request.setAttribute("prop", "name"); 
       pageContext.setAttribute("student", student);
    %>
    <!-- 相当于在表达式中写一个变量 -->
    姓名: ${student[prop]}
    
    <% request.setAttribute("prop", "age"); %>
    年龄: ${student[prop]}
    ```

### 1.6 访问集合或数组中的数据

- ==注意: 这种方式只能访问保存在内置对象键值对的值中的集合或数组==

#### 1.6.1 访问方式

```jsp
<%
    ArrayList<String> list=new ArrayList<>();
    list.add("不知火舞");
    list.add("红叶");
    list.add("黑百合");

    pageContext.setAttribute("list", list);
%>
<p>${pageScope.list[0]}</p>
<p>${pageScope.list[1]}</p>
<p>${pageScope.list[2]}</p>

<%@page import="java.util.List" %>
<p>${list.indexOf("红叶")}</p>
```

### 1.7 EL表达式中的运算

- 在EL表达式中可以进行算术运算、关系运算、逻辑运算、条件运算、验证运算

#### 1.7.1 常用的算术运算符

- **EL表达式中的算术运算符可以将运算符两侧的变量类型自动转换为数字类型**
- **EL表达式的算术运算结果跟java语言中的原酸结果是不同的**
  - 比如: 在java语言中, `3 / 2 = 1`, 而在EL表达式中, `3 / 2 = 1.5`

| 算术运算符 | 说明                                                         |
| ---------- | ------------------------------------------------------------ |
| +          | 加, EL表达式中的`+`只有算术运算的功能, 没有字符串凭借的功能, 如果要凭借字符串, 可以调用String对象的`concat(String str)`方法 |
| -          | 减                                                           |
| *          | 乘                                                           |
| /或div     | 除                                                           |
| %或mod     | 取余                                                         |

#### 1.7.2 关系运算符

| 关系运算符   | 说明     |
| ------------ | -------- |
| `==`或者`eq` | 等于     |
| `!=`或者`ne` | 不等于   |
| `>`或者`gt`  | 大于     |
| `>=`或者`ge` | 大于等于 |
| `<`或`lt`    | 小于     |
| `<=`或`le`   | 小于等于 |

#### 1.7.3 逻辑运算符

| 逻辑运算符   | 说明 |
| ------------ | ---- |
| `&&`或 `and` | 与   |
| `|| `或 `or` | 或   |
| `!` 或 `not` | 非   |

#### 1.7.4 条件运算符

```jsp
${条件表达式? 值1 : 值2}
```

#### 1.7.5 验证运算符

```jsp
<!-- 值为布尔类型, 判断表达式是否为null值、空字符串、长度为0的集合或者数组-->
${empty 表达式}
${empty pageScope.name}
${empty pageScope.studentList}
```

## 二. JSTL标签

### 2.1 基本概念

- JSTL(JSP Standard Tag Library) 被称为JSP标准标签库
- 开发人员可以利用这些标签取代JSP页面上的Java代码, 从而提高程序的可读性, 降低程序的维护难度

### 2.2 使用方式

- 下载JSTL的jar包并添加为项目的依赖, 下载地址为: <https://tomcat.apache.org/download-taglibs.cgi>, 四个jar包都下载下来

- 在JSP页面中使用`tablib`指令引入jstl标签库, 方式为

  - ```jsp
    <!-- prefix属性用于指定库前缀 -->
    <!-- uri用于执行库的标识 -->
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jst1/core" %>
    ```

### 2.3 常用核心标签

#### 2.3.1 输出标签

用来将指定文本内容输出的标签, **一般不怎么用, 直接使用el表达式就好了**

```jsp
<c:out value="黑百合"></c:out><br/>

<% pageContext.setAttribute("name", "红叶"); %>
<c:out value="${pageScope.name}"></c:out>
```



#### 2.3.2 设置标签

相当于`pageContext.setAttribute(name, value)`

##### 2.3.2.1 设置作用域对象中的属性

- `<c:set var="保存在作用域对象中的属性名" value="属性值" scope="作用域对象"/>`

```jsp
<!--<%
    pageContext.setAttribute("name","黑百合");
%>-->
<c:set var="name" value="黑百合" scope="page"/><br/>
```

##### 2.3.2.2 作用域对象的属性值是个JavaBean对象

- `<jsp:useBean id="对象在作用域对象中的属性名" class="对象的名字" scope="对象属于哪个作用域的属性"/>`
- `<c:set target="能够调用到JavaBean对象的El表达式" property="JavaBean对象某个字段的名字" value="字段的值"/>`

```jsp
<%-- 在作用域对象中设置这个JavaBean对象的属性, id的值为属性名, 同时也是JavaBean对象引用的名字, class是JavaBean对象的类型 --%>
<jsp:useBean id="widowmaker" class="com.zq.servlet.Student" scope="page"/>

<%-- 对这个JavaBean对象的各个属性进行设置--%>
<c:set target="${widowmaker}" property="name" value="黑百合"/>
<c:set target="${widowmaker}" property="age" value="30"/>
<c:set target="${widowmaker}" property="gender" value="女"/>

<%-- 输出JavaBean对象的各个属性的值 --%>
<p>${widowmaker.name} , ${widowmaker.age} , ${widowmaker.gender}</p>
```



#### 2.3.3 删除标签

- 标签`<c:remove var="属性名"/>`, 属性`scope`, 属性名所在的作用域
- 相当于`pageContext.removeAttribute(name)`

```jsp
${empty widowmaker}<br/>
<c:remove var="widowmaker"/>
${empty widowmaker}<br/>

${empty name}<br/>
<c:remove var="name"/>
${empty name}<br/>
```

#### 2.3.4 单条件判断标签

```jsp
<c:if test="EL条件表达式">
	满足这个条件时要执行的html代码
</c:if>
```

#### 2.3.5 多条件判断标签

- 标签`<c:choose>`和子标签`<c:when>`、`<c:otherwise>`
  - 子标签`<c:when>`有一个属性`test`

```jsp
<c:choose>
	<c:when test="EL条件表达式1">
    	满足这个条件时要执行的html代码
    </c:when>
    <c:when test="EL条件表达式2">
    	满足这个条件时要执行的html代码
    </c:when>
    ...
    <c:otherwise>
    	上面的条件都不满足时要执行的html代码
    </c:otherwise>
</c:choose>
```

#### 2.3.6 循环标签

- 标签: `<c:forEach>`

- 属性`var`是循环到的元素在作用域对象中的属性名, 会自动保存到作用域对象中, 所以在循环体中可以通过el表达式式调用

- 属性`items`就是保存在作用域对象中的集合或数组, 需要用el表达式调用

- 属性`step`、`begin`、`end`可以忽略不写, 值都是数字

  - `step`的默认值是1, `begin`的默认值是0, `end`的默认值是数组的长度-1或集合的大小-1

  - 这几个值类似在这个语句中的作用

    - ```java
      for(int i=begin; i <= end; i += step){
          var = item[i];
      }
      ```

- 属性`varStatus`, 有四个常用的状态值, `index`  `count`  `first`  `last` 

  - `index`    获取的是当前循环中属性`var`**指向的变量**  **的索引**, 返回值是个整数
  - `count`     获取的是当前循环是第几次循环, 返回值是个整数
  - `first`     获取的是当前循环是否是第一次循环, 返回值是boolean类型
  - `last`        获取的是当前循环是否是最后一次循环, 返回值是boolean类型

##### 2.3.6.1 指定循环次数的示例代码

```jsp
<c:forEach var="i" begin="1" end="8" step="2" varStatus="status">
    <p>
        ----------这是第${status.count}次循环----------<br/>
        i = ${i}<br/>
        status.index = ${status.index}<br/>
        status.count = ${status.count}<br/>
        status.last = ${status.last}<br/>
        status.first = ${status.first}<br/>
    </p>
</c:forEach>
```

###### 2.3.6.2 循环一个数组的示例代码

- 示例代码

  - ```jsp
    <%
        String[] nameArr = {"黑百合", "穗乃果", "汉库克", "贝优妮塔", "娜美","Mercy"};
        pageContext.setAttribute("nameArr",nameArr);
    %>
    <c:forEach var="name" items="${nameArr}" step="2" varStatus="status">
        <p>
            ----------这是第${status.count}次循环----------<br/>
            姓名: ${name}<br/>
            status.index = ${status.index}<br/>
            status.count = ${status.count}<br/>
            status.first = ${status.first}<br/>
            status.last = ${status.last}<br/>
        </p>
    </c:forEach>
    ```



#### 2.3.7 可以在EL表达式中调用的方法`fn`

- 需要先导入标签库`<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>`

- 然后就可以以`${fn:方法名(var)}`的形式使用方法了

- 示例代码

  - ```jsp
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <body>
        <c:set var="name" value="Mercy"/>
        ${fn:toUpperCase(name)}
    </body>
    ```

#### 2.3.8 常用格式化标签`<fmt:formatDate/>`

- 需要先导入标签库`<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>`

- ```jsp
  <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%
      Date date = new Date();
      pageContext.setAttribute("date", date);
  %>
  <fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss"/>
  ```

#### 2.3.9 自定义标签(模拟输出标签)

- 如果上面几个标签不能满足需求, 程序员也可以自定义标签

##### 2.3.9.1 编写标签类

- 有两种方式
  - 继承`SimpleTagSupport`类并重写`doTag`方法
  - 继承`TagSupport`类并重写`doStartTag`方法
- 这里使用第一种方式

```java
public class OutTag extends SimpleTagSupport{
    // 标签的属性, 必须是私有的, 并且具有get和set方法
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void doTag() throws JspException, IOException {
        //JspContext对象, Jsp页面的上下文对象, 有多种方法可用
        JspContext jspContext = this.getJspContext();
        JspWriter out = jspContext.getOut();
        out.println(value);
    }
}
```

###### 2.3.9.1.1 JspContext对象

| 方法                                                    | 说明                                                         |
| ------------------------------------------------------- | ------------------------------------------------------------ |
| JspWriter getOut()                                      | jps页面的输写器, 可用来在Jsp页面书写文本                     |
| Object fintAttribute(String name)                       | 依次在作用域对象中查找name对应的属性值, 第一个找到的就是, 剩下的就不用找了 |
| Objecty getAttribute(String name, int scope)            | 在指定的作用域对象中获取name所对应的属性值                   |
| void setAttribute(String name, Object value, int scope) | 在指定的作用域对象中设置name所对应的属性值                   |
| void removeAttribute(String name, int scope)            | 移除指定的作用域对象中的名为name的属性                       |

##### 2.3.9.2 配置标签说明文件到`WEB-INFO`文件夹下

- **一个==标签说明文件==可以对多个自定义标签类进行说明**

- 在`WEB-INFO`文件夹上点击右键, `New`→`XML configuratin File`→`Jsp Tag Library Descriptor`, 输入标签说明文件的名字

- 将第一行的`encoding`的值改为`"utf-8"`

- 设置自定义标签库的前缀, 这些节点必须有, 且只能有一个, 是根节点的子节点

  - ```xml
        <!-- 本自定义标签库的版本, 可以按需更改 -->
    	<tlib-version>1.0</tlib-version>
        <!-- 本自定义标签库的前缀, 可以按需更改 -->
        <short-name>zq</short-name>
    	<!-- 本自定义标签库的uri, 不要改 -->
        <uri>http://mycompany.com</uri>
    ```

- 为自定义的标签配置说明节点, 是根节点的子节点, 可以有多个

  - ```XML
    <tag>
           <!-- 自定义标签的名字 -->
            <name>out</name>
        	<!-- 自定义标签类的全称 -->
            <tag-class>com.zq.tag.OutTag</tag-class>
            <!-- 自定义标签是否有内容 -->
            <body-content>empty</body-content>
        	<!-- 自定义标签的属性 -->
            <attribute>
                <!-- 属性名, 必须与类中的属性名相同 -->
                <name>value</name>
                <!-- 该属性是否是必需的 -->
                <required>true</required>
            </attribute>
        </tag>
    ```

##### 2.3.9.3 在JSP中使用自定义标签

```jsp
<%@ taglib prefix="zq" uri="http://mycompany.com" %>
<zq:out value="黑百合"/>
```

