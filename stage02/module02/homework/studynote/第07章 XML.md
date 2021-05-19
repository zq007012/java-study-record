# 第07章 XML

[toc]

## 一. XML基本介绍

### 1.1 概述

XML全称是Extensible Markup Language, 即可扩展标记语言

- W3C在1998年2月发布1.0版本, 2004年2月有发布1.1版本, 但因为1.1版本不能向下兼容1.0版本, 所以1.1没有人用, 同时, 在2004年2月W3C又发布了1.0版本的第三版, 我们要学习的还是1.0版本
- 特点
  - 可扩展的, 体现在标签(markup)都是自定义的
  - 语法十分严格

### 1.2 XML的作用

| 功能         | 说明                                                         |
| ------------ | ------------------------------------------------------------ |
| 存储数据     | 通常, 我们在数据库中存储数据. 不过, 如果希望数据的可移植性更强, 我们可以把数据存储在xml文件中 |
| **配置文件** | 作为各种技术框架的配置文件时使用(**使用最多的情况**)         |
| 在网络中传输 | 客户端就可以使用xml格式向服务端发送数据, 服务器接收到xml格式数据, 进行解析 |

## 二. XML的语法

> xml格式的文件仅有两部分组成: 一个文档声明和一个根元素

### 2.1 xml的文档声明

- 文档声明必须写在第一行

#### 2.1.1 文档声明的格式

```xml
<?xml version="1.0" encoding="UTF-8"?>
```

#### 2.1.2 xml文档声明的属性说明

- version: 指定xml文档版本,必须写的属性, (值只会写1.0, 不会写1.1)
- encoding: 指定本xml文档的编码, 可选属性, 默认是utf-8

### 2.2 xml的元素

元素(即Element): 是XML文档中最重要的组成部分

#### 2.2.1 xml元素的语法规范

- **有且仅有一个根元素**
- 元素的标签名称不能使用空格, 不能使用冒号
- 元素的标签名称区分大小写

#### 2.2.2 元素示例

```xml
<user></user>
```

`<user></user>`整体叫做元素, user是标签名, `<user>`是开始标签, `</user>`时结束标签

#### 2.3 xml整体示例

```xml
<?xml version="1.0" encoding="utf-8"?>
<users>

</users>
```

- xml文件有且仅有一个文档声明, 文档声明必须在第一行
- xml文件有且仅有一个根元素, 它是所有其他元素的父元素, 比如以上实例中users所在的元素就是根元素
- 元素是由 开始标签  元素体  结束标签  三部分组成的
- 元素体的内容可以是子元素, 也可以是文本
- 空元素: 只是由一个表时开始和结束的标签组成, 例如: `<close/>`

### 2.4 xml元素的属性

```xml
<element id="idName" class="className"> </element>
```

- 属性是元素的一部分, 它只能出现在元素的开始标签中
- 属性的定义格式: 属性名=属性值, 其中**属性值**必须使用单引号或双引号包裹
- 一个元素可以有0~n个属性, 但是同一个元素中的不能出现同名的属性
- **属性名**不能使用空格和冒号, **属性名**必须以字母开头

### 2.5 xml的注释

xml的注释, 以`<!--`开始, 以`-->`结束.  注释内容会被xml解析器忽略

```xml
<!-- 这是一个xml的注释 -->
```

### 2.6 使用xml描述数据表中的数据

![](%E7%AC%AC07%E7%AB%A0%20XML.assets/One_2021-03-10_144256.png)

```xml
<?xml version="1.0" encoding="utf-8"?>
<pirates>
    <!-- 海贼: 弗兰奇 -->
    <pirate id="p008">
        <pid>8</pid>
        <pname>弗兰奇</pname>
        <age>36</age>
        <gender>男</gender>
        <birthday>1984.03.09</birthday>
        <reward>0.94</reward>
        <team_id>1</team_id>
    </pirate>
    <!-- 海贼: 小八 -->
    <pirate id="p012">
        <pid>12</pid>
        <pname>小八</pname>
        <age>38</age>
        <gender>男</gender>
        <birthday>1982.08.08</birthday>
        <reward>0.2</reward>
        <team_id>2</team_id>
    </pirate>
    <!-- 海贼: 汉库克 -->
    <pirate id="p013">
        <pid>13</pid>
        <pname>汉库克</pname>
        <age>31</age>
        <gender>女</gender>
        <birthday>1989.09.02</birthday>
        <reward>0.8</reward>
        <team_id>3</team_id>
    </pirate>
</pirates>
```

## 三. XML约束

- 在xml技术里, 可以编写一个文档来约束一个xml文档的书写规范, 这个文档被称为xml约束

- 常见的约束类型

  - DTD约束
  - Schema约束

- 作为程序员只要掌握两点

  - 会阅读
  - 会引入

### 3.1 DTD类型的xml约束


> DTD(Document Type Definition), 文档类型定义, 用来约束xml文档. 规定xml文档中元素的名称、元素的属性、子元素的名称及顺序、子元素保存的数据类型

#### 3.1.1 编写DTD

- 开发中， 我们不会自己编写DTD约束文档
- 通常我们都是通过框架提供的DTD约束文档编写对应的xml文档， 使用DTD约束的常见框架有: Struts、hibernate等
- DTD约束下的xml文档的根元素下有且仅有一种子元素

**student.dtd**文件

```dtd
<!ELEMENT students (student+) >
        <!ELEMENT student (name,age,sex)>
        <!ELEMENT name (#PCDATA)>
        <!ELEMENT age (#PCDATA)>
        <!ELEMENT sex (#PCDATA)>
        <!ATTLIST student number ID #REQUIRED>
<!--
    ELEMENT: 定义元素
    第一行只能用来定义根元素
    students表示根元素的标签名; (student+)中student表示根元素下的子元素的标签名, +表示
    该种 子元素可以有多个

    第二行用来定义子元素
    student表示子元素的标签名; (name, age, sex)中name、age、sex是二级子元素的标签名，
    表示student这个子元素下可以有三个标签名依次为name、age、sex的元素

    <!ELEMENT name (#PCDATA)> 表示name这个元素下只能保存普通的文本内容
    #PCDATA:普通的文本内容

    ATTLIST : 用来定义属性
    <!ATTLIST student number ID #REQUIRED>: student标签中 有一个ID属性 叫做 number
     #REQUIRED : number的属性值必须填写
     ID表示这个属性的值只能是:唯一的值、不能重复、值只能是字母或者下划线开头
-->
```

#### 3.1.2 xml文档引入DTD约束

- 引入dtd约束到xml文档中，两种方式

  - 内部dtd： 约束文档来自于本地文件

    - ```dtd
      <!DOCTYPE 根元素名 SYSTEM "dtd约束文档的文件路径">
      ```

  - 外部dtd, 约束文档来自于网络上的文件

    - ```dtd
      <!DOCTYPE 根元素名 SYSTEM "dtd约束文档的url"
      ```

- student.xml

  - ```xml
    <?xml version="1.0" encoding="utf-8" ?>
    <!DOCTYPE students SYSTEM "student.dtd">
    <students>
        <student number="s1">
            <name>路飞</name>
            <age>20</age>
            <sex>男</sex>
        </student>
        <student number="s2">
            <name>汉库克</name>
            <age>30</age>
            <sex>女</sex>
        </student>
    </students>
    ```

### 3.2 Schema约束

#### 3.2.1 什么是Schema约束

- Schema约束是新的xml文档约束, 比DTD约束要强大很多, 是DTD替代者
- Schema本身也是xml文档, 但Schema约束文档的扩展名为xsd, 而不是xml
- xsd的全称是Xml Schemas Definition
- Schema约束 功能强大, 内置多种简单和复杂的数据类型
- Schema支持命名空间(即一个xml中可以引入多个Schema约束文档)

#### 3.2.2 Schema约束示例

`pirate.xsd`

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<xsd:schema xmlns="http://www.lagou.com/xml"
            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
            targetNamespace="http://www.lagou.com/xml"
            elementFormDefault="qualified">
    <!-- 定义一个名为pirates的根元素, 这个根元素的type属性的值为pirateType-->
    <xsd:element name="pirates" type="piratesType"/>
    <!-- 对type属性的值为pirateType的元素进行规范-->
    <xsd:complexType name="piratesType">
        <!-- 这种type值的元素下依次有哪几种子元素 -->
        <xsd:sequence>
            <!-- minOccur: 最小出现次数, maxOccur: 最多出现次数, unbounded: 无穷大-->
            <xsd:element name="pirate" type="pirateType" minOccurs="0"
                         maxOccurs="unbounded"/>
        </xsd:sequence>
    </xsd:complexType>
    <!-- 对type属性的值为pirateType的元素进行规范-->
    <xsd:complexType name="pirateType">
        <!-- 这种type值的元素下依次有哪几种子元素 -->
        <xsd:sequence>
            <xsd:element name="name" type="xsd:string"/>
            <xsd:element name="age" type="ageType"/>
            <xsd:element name="gender" type="genderType"/>
            <xsd:element name="fruitPower" type="xsd:string"/>
            <xsd:element name="tag" type="tagType"/>
        </xsd:sequence>
        <!-- 这种type值的元素有哪些属性 -->
        <!-- use属性的值为required时, 表示元素必须设置名为number的属性-->
        <xsd:attribute name="index" type="indexType" use="required"/>
    </xsd:complexType>
    <xsd:simpleType name="genderType">
        <!-- xsd:restriction 约束这种type值的元素可以保存的数据类型, 属性base的值表示可以保存的类型-->
        <xsd:restriction base="xsd:string">
            <!-- xsd:enumeration  这种type值的元素可以保存的数据的值 -->
            <xsd:enumeration value="男"/>
            <xsd:enumeration value="女"/>
        </xsd:restriction>
    </xsd:simpleType>
    <xsd:simpleType name="ageType">
        <!-- xsd:restriction 约束这种type值的元素可以保存的数据类型, 属性base的值表示可以保存的类型-->
        <xsd:restriction base="xsd:integer">
            <!-- xsd:minInclusive 最小值 -->
            <xsd:minInclusive value="0"/>
            <!-- xsd:maxInclusive 最大值 -->
            <xsd:maxInclusive value="100"/>
        </xsd:restriction>
    </xsd:simpleType>
    <xsd:simpleType name="tagType">
        <xsd:restriction base="xsd:string">
            <xsd:pattern value=".{1,30}"/>
        </xsd:restriction>
    </xsd:simpleType>
    <xsd:simpleType name="indexType">
        <!-- xsd:restriction 约束这种type值的元素可以保存的数据类型, 属性base的值表示可以保存的类型 -->
        <xsd:restriction base="xsd:string">
            <!-- xsd:patternn 匹配正则表达式 -->
            <xsd:pattern value="p-[1-9]\d{0,3}"/>
        </xsd:restriction>
    </xsd:simpleType>
</xsd:schema>
```

###### 上述Schema约束文档的根元素各个属性的说明

```xml
<xsd:schema xmlns="http://www.lagou.com/xml"
            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
            targetNamespace="http://www.lagou.com/xml"
            elementFormDefault="qualified">
...
</xsd:schema>
```

> 命名空间指的是一个环境, 即所用的标签来自于那个环境定义的

| 属性                                           | 说明                                                         |
| ---------------------------------------------- | ------------------------------------------------------------ |
| `xmlns="http://www.lagou.com/xml"`             | 表示本文档的引用地址, 给引用本约束文档的xml文档使用          |
| `xmlns:xsd="http://www.w3.org/2001/XMLSchema"` | 表示本文档中使用到的数据类型等定义来自w3                     |
| `targetNamespace="http://www.lagou.com/xml"`   | 暂时不清楚                                                   |
| `elementFormDefault="qualified"`               | 表示要求引用本schema也输的xml文档的每一个元素都要由本文档的命名空间指定 |

#### 3.2.3 xml文档引入Schema约束3.2.3 xml文档引入Schema约束

xml文档的根元素必须设置三个根元素`xmlns`  `xmlns:xds`  `xsi:schemaLocation`

| 属性               | 说明                                                  |
| ------------------ | ----------------------------------------------------- |
| xmlns              | 跟Schema约束文档的根元素的xmlms属性的值相同           |
| xmlns:xis          | 值固定为`"http://www.w3.org/2001/XMLSchema-instance"` |
| xsi:schemaLocation | 值为`"xmlns的值 xsd文件的本地路径或url网络路径"`      |

#### 3.2.4 示例代码

`pirate.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<pirates xmlns="http://www.lagou.com/xml"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.lagou.com/xml pirate.xsd">
    <pirate index="p-1">
        <name>蒙奇·D·路飞</name>
        <age>20</age>
        <gender>男</gender>
        <fruitPower>橡胶果实</fruitPower>
        <tag>海贼王，我当定了</tag>
    </pirate>
    <pirate index="p-2">
        <name>波雅·汉库克</name>
        <age>30</age>
        <gender>女</gender>
        <fruitPower>天天果实</fruitPower>
        <tag>本宫做什么都会被原谅的，因为本宫太美了</tag>
    </pirate>
</pirates>

```

## 四. XML解析

### 4.1 XML解析概述

当将数据存储在xml后, 我们就希望通过程序获得xml的内容, 如果我们使用java基础所学的IO只是是可以完成的, 不过这样需要非常繁琐的操作才可以完成, 且开发中会遇到不同的问题(只读 读写). 人们为不同问题提供不同的解析方式, 并提交对应的解析器, 方便开发人员操作xml.

### 4.2 XML解析方式

开发中比较常见的解析方式有两种, 如下

- DOM: 全称是Document Object Model, 解析器会把整个XML文档加载到内存中, 并在内存中将整个xml文档解析成一个Document对象, Document对象的数据结构是树根结构. xml文档的根元素是树桩, 一个个子元素是一根根分叉, 子元素下又有自己的二级分叉, 同级且相邻的分叉间互有联系, 关系类似LinkedList集合中元素间的关系, 所以该解析方式可以对文件直接进行增查改删
  - 优点: 元素与元素之间保留结构关系, 故可以直接对文档进行增查改删操作
  - 缺点: XML文档过大时, 会导致内存溢出的现象
  - DOM图, ![](%E7%AC%AC07%E7%AB%A0%20XML.assets/DOM%E5%9B%BE.png)
- SAX: 全称是Simple APIs for XML, 对xml的简单程序接口, 是一种速度更快, 更有效的xml解析方式. SAX解析器逐行扫描文档, 一边扫描一遍解析, 并以事件驱动的方式进行具体解析, 每执行一行, 都ii昂出发对应的事件(了解即可)
  - 优点: 占用内存少, 处理速度快, 可以处理大文件
  - 缺点: 只能读, 逐行读取后将释放该行资源

### 4.3 XML常见的解析器

解析器: 就是根据不同的解析方式提供具体实现. 比如DOM解析器和SAX解析器, 由于解析器操作过于繁琐, 为了方便开发人员, 有提供易于操作的xml解析开发包

- JAXP: (Java API for xml processing), sun公司提供的解析器, 支持Dom和SAX两种解析方式
- **DOM4J**: (DOM for Java), 一款非常优秀的解析器, DOM4J是一个易用的、开源的库， 用于XML、xParth和XLT的解析， 他应用于Java平台， 采用了Java集合框架并完全支持DOM、SAX和JAXP
- Jsoup：Jsoup是一款Java的HTML解析器， 也可以解析XML
- PULL:  Android内置的XML解析方式， 类似SAX

### 4.4 DOM4J的使用

#### 4.4.1 下载Jar包

在github上搜索`dom4j`, 点击打开`dom4j/dom4j`, 在`Release`中下载所需的Jar包

#### 4.4.2 API介绍

可以通过核心类SaxReader加载xml文档获得Document对象, 通过Document对象获得文档的根元素, 然后就可以操作了

##### 常用API

- SaxReader对象

  - | 方法               | 说明                                       |
    | ------------------ | ------------------------------------------ |
    | Document read(...) | 将从参数中读取到的数据封装到Document对象中 |
  
- Document对象

  - | 方法                     | 说明       |
    | ------------------------ | ---------- |
    | Element getRootElement() | 获取根元素 |

- Element对象

  - | 方法                                                    | 说明                                             |
    | ------------------------------------------------------- | ------------------------------------------------ |
    | `List<Element> elements()`                              | 获取本元素下所有子元素                           |
    | `List<Element> elements(String name)`                   | 获取本元素下所有指定名称的子元素                 |
    | Element element(String name)                            | 获取本元素下指定名称的第一个子元素               |
    | String getName()                                        | 获取本元素的名字                                 |
    | String attributeValue(String name)                      | 获取本元素name属性的值                           |
    | String attributeValue(String name, String defaultValue) | 获取本元素name属性的值, 默认这个只是defaultValue |
    | String elementText(String name)                         | 获取本元素下指定名称的第一个子元素保存的文本数据 |
    | String getText()                                        | 获取本元素保存的文本数据                         |
    | String getTextTrim()                                    | 获取本元素保存的文本数据, 并去掉收尾的空格       |

#### 4.4.3 读取xml的示例代码

schema约束文档使用[3.2.2 Schema约束示例](#3.2.2 Schema约束示例)中的`pirate.xsd`, xml文档使用[3.2.4 示例代码](#3.2.4 示例代码)中的`pirate.xml`, 在本模块下创建一个`resources`文件夹, 把右键点击`mark directory as `,选择`resources root`将该文件夹标记为资源文件夹

```java

```

```java
SAXReader saxReader = new SAXReader();
Document doc = saxReader.read(new File("resources/pirate.xml"));
//获取根元素并获取根元素下的所有子元素
Element rootElement = doc.getRootElement();
List<Element> elements = rootElement.elements();
System.out.println("根元素的名称是: " + rootElement.getName() +
        ", 有" + elements.size() + "个子元素");

//解析每一个子元素
for (Element element :
        elements) {
    System.out.println("===========================================");
    System.out.println(element.getName() +
            " \tindex=\""+element.attributeValue("index") + "\"");
    List<Element> grandElements = element.elements();
    System.out.println("------------------------");
    for (Element grandElement :
            grandElements) {
        System.out.println(grandElement.getName() + " \t: \t" +
                grandElement.getText());
    }
    System.out.println("-------------------------");
    System.out.println("===========================================");
}
```

### 4.5 XPath方式读取xml

#### 4.5.1 XPath介绍

- XPath是一门在XML文档中查找信息的语言. 可以使用xpath查找xml中的内容(xpath类似文件路径, 可以称作是xml节点路径, 可以通过xml节点路径直接找到目标节点)
- XPath的好处: 由于DOM4J在解析XML是只能一层一层地解析, 所以当XML文件层数过多时使用会很不方便, 结合XPath就可以直接获取到目标元素
- 可以在[菜鸟教程](https://www.runoob.com/)上搜索XPath的教程
- **XPath中一个元素是一个节点, 一个属性也是一个节点**

#### 4.5.2 XPath基本语法介绍

DOM4J操作xpath的几种主要形式

| 语法                                 | 说明                                                         |
| ------------------------------------ | ------------------------------------------------------------ |
| /AAA/BBB/CCC                         | 根节点AAA下的第一个BBB节点下的第一个CCC节点<br />或者<br />根节点AAA下的所有BBB节点下的所有CCC节点 |
| //BBB                                | 获取所有名为BBB的节点                                        |
| //*                                  | 获取所有节点                                                 |
| /AAA/BBB[n]                          | 根节点AAA下第一个BBB节点(n是任意数字)<br />                  |
| /AAA/BBB/CCC[n]                      | 根节点AAA下第一个BBB节点下的第n个CCC节点(n是任意数字)<br />或者<br />根节点AAA下所有BBB节点下的第n个CCC节点(n是任意数字) |
| /AAA/BBB/CCC[n]                      | n是任意数字                                                  |
| /AAA/BBB[n]/CCC                      | n是任意数字                                                  |
| /AAA/BBB[m]/CCC                      | n是任意数字                                                  |
| /AAA/BBB[last()]                     | 跟节点AAA下的最后一个名为BBB的节点<br />                     |
| /AAA/BBB/CCC[last()]                 | 根节点AAA下的第一个BBB节点下的最后一个名为CCC的节点<br />或者<br />根节点AAA下的所有BBB节点下的最后一个名为CCC的节点 |
| /AAA/BBB/attribute::attributeName    | 根节点AAA下第一个BBB节点的attributeName属性节点(`attribute::`是不可变的)<br />或者<br />根节点AAA下所有BBB节点的attributeName属性节点(`attribute::`是不可变的) |
| /AAA/BBB[@attributeName='value']     | 根节点AAA下的atrributeName属性的值为value的BBB节点           |
| /AAA/BBB[@attributeName='value']/CCC |                                                              |



#### 4.5.3 使用Xpath语言读取XML

##### 4.5.3.1  `jaxen.jar`包

- java中使用XPath语言需要`Jaxen.jar`包的支持
- 可以到<https://mvnrepository.com/>(需要外网支持)搜索`jaxen`, 点击第一个结果, 在`Files:`这一行中, `bundle`就是所需jar包, `view all`可以显示所有`jaxen`相关的所有jar包
- Jaxen是一个Java编写的开源的XPath库。这是适应多种不同的对象模型，包括DOM，XOM，dom4j和JDOM。也可以作为适配器，转换Java字节代码或XML的Java bean为xml，从而使您可以使用XPath查询这些树了

##### 4.5.3.2 API介绍

###### 4.5.3.2.1 `Document`类中相关方法

| 方法                                   | 说明                        |
| -------------------------------------- | --------------------------- |
| Node selectSingleNode(String xpath)    | 查找和xpath匹配的第一个节点 |
| `List<Node> selectNodes(String xpath)` | 查找和xpath匹配的所有结点   |

###### 4.5.3.2.2 xpath语法

| 语法                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| /AAA/BBB/CCC                                                 | 根节点AAA下的第一个BBB节点下的第一个CCC节点<br />或者<br />根节点AAA下的所有BBB节点下的所有CCC节点 |
| //BBB                                                        | 获取所有名为BBB的节点                                        |
| //*                                                          | 获取所有节点                                                 |
| /AAA/BBB[n]                                                  | 根节点AAA下第n个BBB节点(n是任意数字)<br />                   |
| /AAA/BBB/CCC[n]                                              | 根节点AAA下第一个BBB节点下的第n个CCC节点(n是任意数字)<br />或者<br />根节点AAA下所有BBB节点下的第n个CCC节点(n是任意数字) |
| /AAA/BBB/CCC[n]                                              | n是任意数字                                                  |
| /AAA/BBB[n]/CCC                                              | n是任意数字                                                  |
| /AAA/BBB[m]/CCC[n]                                           | m和n是任意数字                                               |
| /AAA/BBB[last()]                                             | 跟节点AAA下的最后一个名为BBB的节点                           |
| /AAA/BBB/CCC[last()]                                         | 根节点AAA下的第一个BBB节点下的最后一个名为CCC的节点<br />或者<br />根节点AAA下的所有BBB节点下的最后一个名为CCC的节点 |
| /AAA/BBB/attribute::attributeName<br />或者<br />/AAA/BBB/@attributeName | 根节点AAA下第一个BBB节点的attributeName属性节点(`attribute::`是不可变的)<br />或者<br />根节点AAA下所有BBB节点的attributeName属性节点(`attribute::`是不可变的) |
| /AAA/BBB[@attributeName='value']                             | 根节点AAA下的atrributeName属性的值为value的BBB节点           |
| /AAA/BBB[@attributeName='value']/CCC                         | 根节点AAA下的atrributeName属性的值为value的BBB节点下的第一个CCC节点<br />或者<br />根节点AAA下的atrributeName属性的值为value的BBB节点下的所有CCC节点 |

###### 4.5.3.2.3 `Node`类中相关方法

`Node`对象分为**元素节点对象**和**属性节点对象**

| 方法                    | 说明                                                         |
| ----------------------- | ------------------------------------------------------------ |
| String gexText()        | 获取元素节点保存的文本数据<br />获取属性节点的值             |
| String getStringValue() | 获取该节点以及其所有子节点保存的文本数据<br />获取属性节点的值 |
| String getName()        | 获取节点名                                                   |
| String getPath()        | 获取xpath                                                    |
| String getNodeType()    | 获取本节点的类型, 元素节点的返回值是`Element`, 属性节点的返回值是`Attribute` |



##### 4.5.3.3 准备xml文件

`book.xml`, 将该文件保存在, [4.4.3 读取xml的示例代码](#4.4.3 读取xml的示例代码)中创建的resources文件夹下

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<bookstore>
    <book id="book1">
        <name>金瓶梅</name>
        <author>金圣叹</author>
        <price>99</price>
    </book>
    <book id="book2">
        <name>红楼梦</name>
        <author>曹雪芹</author>
        <price>69</price>
    </book>
    <book id="book3">
        <name>Java编程思想</name>
        <author>埃克尔</author>
        <price>59</price>
    </book>
</bookstore>
```



##### 4.5.3.4 示例代码

```java
 	@Test
    public void xpathTest1() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("resources/book.xml");

        String xpath1 = "/bookstore/book";
        Node singleNode = document.selectSingleNode(xpath1);
        System.out.println(singleNode.getName());
        System.out.println("----------------------------------");
        List<Node> nodes = document.selectNodes(xpath1);
        System.out.println("共有" + nodes.size() + "个节点");
        for (Node node :
                nodes) {
            System.out.println(node.getName());
        }

    }

    @Test
    public void xpathTest2() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("resources/book.xml");

        String xpath1 = "/bookstore/book[2]";
        Node singleNode = document.selectSingleNode(xpath1);
        System.out.println(singleNode.getName());
        System.out.println("----------------------------------");
        List<Node> nodes = document.selectNodes(xpath1);
        System.out.println("共有" + nodes.size() + "个节点");
        for (Node node :
                nodes) {
            System.out.println(node.getName());
        }
    }

    @Test
    public void xpathTest3() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("resources/book.xml");

        String xpath = "/bookstore/book[@index='book3']";
        Node singleNode = document.selectSingleNode(xpath);
        System.out.println(singleNode.getName());
        System.out.println("----------------------------------");
        List<Node> nodes = document.selectNodes(xpath);
        System.out.println("共有" + nodes.size() + "个节点");
        for (Node node :
                nodes) {
            System.out.println(node.getName());
        }
    }

    @Test
    public void xpathTest4() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("resources/book.xml");

        String xpath = "/bookstore/book/attribute::index";
        Node singleNode = document.selectSingleNode(xpath);
        System.out.println(singleNode.getName());
        System.out.println("----------------------------------");
        List<Node> nodes = document.selectNodes(xpath);
        System.out.println("共有" + nodes.size() + "个节点");
        for (Node node :
                nodes) {
            System.out.println(node.getName());
        }
    }
    @Test
    public void xpathTest5() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("resources/book.xml");

        String xpath = "/bookstore/book/name";
        Node singleNode = document.selectSingleNode(xpath);
        System.out.println(singleNode.getText());
        System.out.println(singleNode.getName());
        System.out.println(singleNode.getStringValue());
        System.out.println(singleNode.getPath());
        System.out.println(singleNode.getNodeTypeName());
    }

    @Test
    public void xpathTest6() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("resources/book.xml");

        String xpath = "/bookstore/book/@index";
        Node singleNode = document.selectSingleNode(xpath);
        System.out.println(singleNode.getText());
        System.out.println(singleNode.getName());
        System.out.println(singleNode.getStringValue());
        System.out.println(singleNode.getPath());
        System.out.println(singleNode.getNodeTypeName());
    }

    @Test
    public void xpathTest7() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("resources/book.xml");

        String xpath = "/bookstore";
        Node bookstore = document.selectSingleNode(xpath);
        System.out.println(bookstore.getPath());

        Node book = bookstore.selectSingleNode("/bookstore/book");
        System.out.println(book.getPath());
    }
}
```

