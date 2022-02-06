# 第05章 JDBC&批处理&元数据

[toc]

## 一. JDBC概述

### 1.1  数据库服务器驱动

- 数据库保存在数据库服务器里, 只有数据库服务器才能对数据库进行**直接的**增查改删`crud`操作
- 数据库服务器通过SQL语言对数据库进行`crud`的操作, 并获取相应的反馈信息
  
  - 服务器需要先将SQL语言编译成对应的二进制指令, 然后使用这些指令对数据库进行操作, 并获得相应的反馈信息

> 如果想要在数据库服务器外(也就是客户端)对数据库服务器中的数据库进行操作, 就需要满足以下几个条件
>
> 1. 客户端要跟数据库服务器建立连接
>    1. 客户端要知道数据库服务器在哪里, 也就是服务器的网址和网络端口
>    2. 客户端有可以指使服务器干活的权力，也就是客户端有能够登录数据库服务器的账号和密码，并且这个账号有操作数据库的权限
> 2. 将客户端编写的SQL语句发送给数据库服务器的传输通道(客户端➡数据库服务器)，数据库服务器会对从客户端接收的SQL语句进行编译，然后用编译后的指令操作数据库，并获取到对应的反馈信息
> 3. 将数据库服务器获取到的反馈信息发送给客户端的传输通道(数据库服务器➡客户端)

这就是客户端访问  数据库服务端的  数据库  的大概过程, 不同的编程语言对这个过程设立了相应的标准规范, 而数据库服务器软件的制作方会实现这些规范, 这些规范被实现后叫做驱动(Drivers), 客户端的制作者可以根据需要到数据库软件的制作者那里下载对应编程语言的数据库服务器驱动, 然后使用这些驱动访问数据库服务器上的数据库.

### 1.2 JDBC

- Java语言访问数据库服务器上的数据库的标准规范叫做JDBC(Java Database Conectivity) , 是一种用于在客户端操作数据库服务器上的数据库的Java API, 可以为多种关系型数据库提供统一访问, 它由一组用Java语言编写的类和接口组成, 是Java语言访问数据库的标准规范.
- JDBC是接口, 驱动时接口的实现, 没有驱动将无法完成客户端与数据库服务器的连接, 从而不能操作数据库! 每个数据库服务器软件的厂商都会提供自己的驱动.
- JDBC的好处是开发者不需要知道各个数据库服务器厂商的驱动是如何实现的、如何使用, 只需要知道JDBC如何使用就行了. 极大地降低了开发者的学习成本.
  - ![ ](%E7%AC%AC05%E7%AB%A0%20JDBC.assets/%E4%BB%BB%E5%8A%A1%E4%B8%80_JDBC.png)

### 1.3 JDBC开发

#### 1.3.1 JDBC开发步骤

**所有的步骤都是在客户端进行的**

1. 注册数据库驱动
   1. 使Jdk的JDBC与数据库驱动建立联系, 程序就能知道要找哪个驱动做事了
   2. 可以注册多种数据库驱动
   3. Java1.6开始注册驱动就自动化了, 可以不写这个步骤了
2. 连接数据库服务器
   1. url, 经常使用的有一下格式
      1. 主协议:子协议://服务器的IP地址:端口?字符集, 例如`jdbc:mysql://localhost:3306?characterEncoding=UTF-8`
         1. 这种格式连接的是数据库服务器
         2. 这里的字符集表示客户端与服务端通讯所使用的字符集
         3. 可以将`?字符集`去掉, 但写上的话可以有效地避免通讯时产生乱码
         4. 其中协议`jdbc:mysql`中的主协议`jdbc`是固定不变的, 子协议`mysql`是数据库服务器的名称
      2. 主协议:子协议://服务器的IP地址:端口/数据库?字符集, 例如`jdbc:mysql://localhost:3306/db?characterEncoding=UTF-8`
         1. 这种格式连接的是数据库服务器里的指定数据库
         2. 这里的字符集表示客户端与服务端通讯所使用的字符集
         3. 可以将`?字符集`去掉, 但写上的话可以有效地避免通讯时产生乱码
         4. 其中协议`jdbc:mysql`中的主协议`jdbc`是固定不变的, 子协议`mysql`是数据库服务器的名称
   2. 登录数据库服务器的用户名和密码
3. 开启客户端向数据库服务器发送数据的网络传输通道, 向服务器传送SQL语句
4. 开启客户端接收数据库服务器的反馈信息的传输通道, 接收服务器的反馈信息
5. 操作数据库结束, 关闭相关资源, 断开连接

#### 1.3.2 下载MySQL驱动包--支持Java语言的版本

1. 进入MySQL的[官网](https://www.mysql.com/), 点击`Downloads`, 然后点击[MySQL Community (GPL) Downloads](https://dev.mysql.com/downloads/)
2. 点击[Connector/J](https://dev.mysql.com/downloads/connector/j/)(表示针对Java语言的连接器), 进入下一页,`Select Operating System`选择`Platform Independent`(意思是跨平台), Windows系统选择后缀名为`.zip`的版本, Linux系统选另一个版本

## 二. 编程

### 2.0 准备

#### 2.0.1 准备数据

使用SQLYog创建数据库、表并插入数据

```sql
/*
从表:
	表名pirate
	列 pid:int    pname:vachar(10)    age:int    gender:char(1)    birthday:date    reward[赏金]:double(12,8)    team_id:int

主表: 
	表名team
	列team_id:int    tname:varchar(10)
*/
-- 先创建主表, 在创建从表
CREATE DATABASE one_piece;
USE one_piece;
CREATE TABLE team(
	team_id INT PRIMARY KEY AUTO_INCREMENT, 
	tname VARCHAR(10)
);

CREATE TABLE pirate(
	pid INT PRIMARY KEY AUTO_INCREMENT, 
	pname VARCHAR(10),
	age INT, 
	gender CHAR(1), 
	birthday DATE,
	reward DOUBLE(12,8), 
	team_id INT, 
	FOREIGN KEY(team_id) REFERENCES team(team_id)
);

-- 先完善主表的数据
INSERT INTO team(tname) VALUES('草帽海贼团'),
				('太阳海贼团'),
				('九蛇海贼团'),
				('白胡子海贼团'),
				('红心海贼团');
-- 再完善从表的数据
INSERT INTO 
	pirate(pname, age, gender, birthday, reward, team_id) 
	VALUES('路飞', 19, '男', '2001.5.5',5.00,1),
		('索隆',21,'男','1999.11.11',3.2,1),
		('娜美',20,'女','2000.7.3', 0.66,1),
		('乌索普',19,'男','2001.4.1', 2, 1),
		('香吉士',21,'男','1999.3.2',3.3 ,1),
		('乔巴', 17, '男', '2003.12.24', 50e-8,1),
		('罗宾', 30, '女', '1990.2.6',1.3,1),
		('弗兰奇',36,'男','1984.3.9',0.94,1),
		('布鲁克',90,'男','1930.4.3',0.83,1),
		('甚平',46,'男','1974.4.2',4.38,2),
		('阿拉丁',46,'男','1974.1.31', 4, 2),
		('小八',38,'男','1982.8.8', 0.2, 2),
		('汉库克',31,'女', '1989.9.2',0.8,3),
		('白胡子',72,'男','1948.4.6',50.46,4),
		('马尔科',45, '男','1975.10.5', 20, 4),
		('艾斯',22,'男','1998.1.1', 5.5, 4),
		('罗',26,'男','1994.10.6', 5, 5),
		('贝波',22,'男','1998.11.20', 500e-8, 5);

-- 创建新用户并授权
CREATE USER 'zq'@'localhost' IDENTIFIED BY '111111';
GRANT ALL ON one_piece.* TO 'zq'@'localhost';
```

#### 2.0.2 对IDEA上的侧边栏Database进行配置

配置好后就可以在Database面板上实时地查看数据库的信息了

1. 与数据库建立连接: 打开IDEA上的Sidebar➡Database, 在Database面板上的空白处点击右键, 选择`New`➡`Data Source`➡`MySQL` , 按要求完善好数据库服务器的连接信息, 之后点击OK
2. 显示数据库中的表以及表的列这些概要(Schemas)信息: 在新建立的数据库连接上右键, 选择`Database Tools`➡`Manage Shown Schemas...`, 勾选这个数据库, 就能显示这些概要信息了.

### 2.1 示例代码

```java
		// 1. 注册驱动, 可以省略, 因为第二步会帮忙注册驱动的
        Class.forName("com.mysql.jdbc.Driver");

        // 2. 跟数据库服务器上的数据库建立网络连接
        String url = "jdbc:mysql://127.0.0.1:3306/one_piece?characterEncoding" +
                "=utf-8";
        Connection onePiece = DriverManager.getConnection(
                url, "zq", "111111");

        // 3. 获取SQL语句执行平台, 以便客户端向数据库发送sql语句
        Statement onePieceStatement = onePiece.createStatement();

        // 4. 执行sql语句
        // 添加一条记录
        String addSQL = "INSERT INTO pirate VALUES(NULL, '薇薇' , 18 , '女', '2002.6.1', 0 , 1);";
        //执行添加后就能得到反馈信息
        int recordsUpdate = onePieceStatement.executeUpdate(addSQL);
        System.out.println("recordsUpdate = " + recordsUpdate);

        // 删除一条记录
        String deleteSQL = "DELETE FROM pirate WHERE pname = '薇薇';";
        //执行删除后就能得到反馈信息
        int recordsDel = onePieceStatement.executeUpdate(deleteSQL);
        System.out.println("recordsDel = " + recordsDel);

        //查询所有记录
        String querySQL = "SELECT t.`tname`, p.`pname`, p.`age`\n" +
                "FROM pirate p RIGHT JOIN team t ON p.`team_id` = t.`team_id`;";
        //执行查询后就能获得反馈信息
        ResultSet queryRS = onePieceStatement.executeQuery(querySQL);
        while (queryRS.next()){
            String tname = queryRS.getString("tname");
            String pname = queryRS.getString("pname");
            int age = queryRS.getInt("age");

            System.out.println("团队: " + tname + "\t姓名: " + pname +
                    "\t年龄: " + age);
        }

        // 5. 关闭资源
        //遵循后开先关原则
        queryRS.close();
        onePieceStatement.close();
        onePiece.close();
```

### 2.2 代码详解

#### 2.2.1 注册驱动

> - JDBC中定义驱动的接口 : `java.sql.Driver`
> - MySQL驱动包中实现了这个接口的类: `com.mysql.jdbc.Driver`

| 注册驱动的方式                          | 描述                                                         |
| --------------------------------------- | ------------------------------------------------------------ |
| Class.forName(驱动包中实现驱动接口的类) | 将这个类加载进JVM的方法去后, 就会将这个驱动的对象注册为驱动了 |

##### 2.2.2.1 示例代码

```java
Class.forName("com.mysql.jdbc.Driver");
```

##### 2.2.2.2 为什么这样可以注册驱动?

因为类`com.mysql.jdbc.Driver`在加载进方法区后, 会像执行静态代码块, 在这个静态代码块里会执行注册驱动操作, 以下是相关代码

```java
public class Driver extends NonRegisteringDriver implements java.sql.Driver {
    public Driver() throws SQLException {
    }

    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException var1) {
            throw new RuntimeException("Can't register driver!");
        }
    }
}
```

==因为新版本的MySql驱动Jar包中, 注册驱动的类由`com.mysql.jdbc.Driver`换成了`com.mysql.jdbc.cj.Driver`, `com.mysql.jdbc.Driver`被弃用了, 而且新的注册驱动的类会经由SPI自动注册驱动, 不需要手动注册数据库驱动了了."Loading class 'com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'. The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary."==

#### 2.2.2 与数据库建立连接

> - Connection接口, 代表一个客户端与数据库服务器的连接对象, 具体的实现由数据库服务器软件的厂商实现
> - 使用`DriverManager`类的静态方法`getConnection`可以获取相关数据库的链接

| DriverManager接口中获取连接对象的静态的方法                  | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| Connection getConnection(String url, String user, String password) | 通过url知道数据库服务器的地址, 用户名和密码决定能否成功跟数据库建立连接 |

##### 2.2.2.1. getConnection方法3个连接参数说明

| 连接参数 | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| url      | 连接MySQL服务器时url的格式:<br />jdbc:mysql://IP地址:3306/数据库名?characterEncoding=utf-8 |
| user     | 数据库服务器的登录名                                         |
| password | 登陆密码                                                     |

##### 2.2.2.2 对url的详细说明

```java
"jdbc:mysql://ip地址:端口号/数据库名?characterEncoding=utf-8"
```

- JDBC规定url的格式由三部分组成,每个部分中间使用冒号分隔

  - 第一部分是协议`jdbc`, 这是固定的
  - 第二部分是子协议, 就是数据库服务器的种类, 连接的数据库服务器是mysql, 第二部分自然是mysql了
  - 第三部分是由数据库厂商规定的, 我们需要了解每个数据库掺上的要求, mysql类型的url的第三部分分为数据库服务器的ip地址、端口号、以及要使用的数据库名称组成， 还可以额外添加编码类型， 这样可以避免乱码

- 示例

  - ```sql
    "jdbc:mysql://localhost:3306/one_piece?characterEncoding=utf-8"
    ```

#### 2.2.3 获取SQL语句执行平台

- 语句执行平台的作用: 将要执行的SQL语句发送给数据库服务器去执行, 然后获取执行结果
- 通过Connection对象的createStatement方法获取这个SQL语句执行平台对象

| Connection接口中的方法      | 说明                    |
| --------------------------- | ----------------------- |
| Statement createStatement() | 创建SQL语句执行平台对象 |

- Statement对象: 将要执行的SQL语句发送给数据库服务器去执行, 然后获取执行结果

| Statement类 常用方法               | 说明                                                         |
| ---------------------------------- | ------------------------------------------------------------ |
| int executeUpdate(String sql)      | 一般只用来执行`insert/update/delete`语句, 返回int类型的值, 表示受到影响的记录个数 |
| Resultset executeQuery(String sql) | 一般只用来执行`select`语句, 返回ResultSet结果集对象, 该对象封装了执行select语句后的查询结果 |

#### 2.2.4 处理结果集ResultSet

- 只有在执行查询操作时, 草需要处理结果集

- | Resuolt接口的方法         | 说明                                                         |
  | ------------------------- | ------------------------------------------------------------ |
  | boolean next()            | 将指针指向结果集中的下一行<br />如果该行有记录, 则返回true, 否则返回false |
  | boolean previous()        | 将指针指向结果集中的下一行<br />如果该行有记录, 则返回true, 否则返回false |
  | Xxx getxxx(String "列名") | 指针所在行的  记录中的  该列的值                             |
  | Xxx getXxx(int "第几列")  | 指针所在行的  记录中的  第几列的值<br />从1开始数            |
  | int getRow()              | 指针所指向的记录是结果集中的第几行(从1开始数)                |
  | boolean first()           | 将指针指向结果集中的第一条记录                               |
  | boolean isFirst()         | 判断指针是否指向结果集中的第一条记录                         |
  | void beforeFirst()        | 将指针指向结果集中的第一条记录**之前**                       |
  | boolean isBoforeFirst()   | 判断指针是否指向结果集中的第一条记录**之前**                 |
  | boolean last()            | 将指针指向结果集中的最后一条记录                             |
  | boolean isLast()          | 判断指针是否指向结果集中的最后一条记录                       |
  | void afterLast()          | 将指针指向结果集中的最后一条记录**之后**                     |
  | boolean isAfterLast()     | 判断指针是否指向结果集中的最后一条记录**之后**               |

#### 2.2.5 释放资源

需要释放的对象: `ResultSet`结果集  `Statement`语句执行平台  `Connection`连接对象

> - `Connection`连接对象是连接客户端与数据库服务器的连接通道, 而`Statement`语句执行平台对象就是这条通道里的客户端➡数据库服务器的一条航线, 用来输送SQL语句, `ResultSet`结果集是这条通道里的数据库服务器➡客户端的一条航线, 用来输送数据库服务器执行上述SQL语句后的执行结果. 所以关闭时应该遵循后开先关的原则, 像关闭`ResultSet`对象, 之后是`Statement`对象, 最后是`Connection`对象
> - 一个`Connection`连接通道里可以有多条`Statement`航线
> - 一条`Statement`航线只会与一条`ResultSet`航线绑定, 当`Statement`与新的`ResultSet`航线绑定之前, 旧的`ResultSet`航线会关闭

### 2.3 步骤总结

1. 注册驱动(可以省略)
2. 跟数据库建立连接
3. 获取SQL语句执行平台
4. 处理结果集(只在查询时需要处理)
5. 释放资源

## 三. JDBC工具类

### 3.1 什么时候自己创建工具类

- 如果一个功能经常要用到, 我们建议把这个功能做成一个工具类, 可以在不同的地方重用
- "获得数据库连接"这一操作, 将在以后的增删改查苏原有功能中都存在, 可以封装工具类JDBCUtils, 提供获取连接对象的方法, 从而达到代码的重复利用

### 3.2 JDBCUtils类

#### 3.2.1 成员变量

- 常量
  - DRIVERNAME : String    驱动
  - URL : String  数据库服务器的链接地址
  - USER : String    登录数据库服务器的用户名
  - PASSWORD : String    登陆密码

#### 3.2.2 成员方法

- 静态方法
  - getConnection : Connection  获取连接对象

#### 3.3 示例代码



```java
// 这并非最终的代码, 以后还需要不断地升级更新
public class JDBCUtils {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/one_piece" +
            "?characterEncoding=utf-8";
    private static final String USER = "zq";
    private static final String PASSWORD = "111111";

    //不需要通过此种方式注册驱动了, 这个驱动会经由SIP自动注册
    // 1. 注册驱动
    /*static{
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    // 2. 与数据库建立连接
    public static Connection getDBConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER,
                    PASSWORD);
    }


}
```

## 四. SQL注入

### 4.0 数据准备

```sql
CREATE TABLE user(uid INT PRIMARY KEY AUTO_INCREMENT, uname VARCHAR(10) UNIQUE, upassword VARCHAR(15));

INSERT INTO user VALUES(NULL, '路飞', '111111');
INSERT INTO user VALUES(NULL, '汉库克', '222222');
SELECT * FROM user;
```

### 4.1 SQL注入演示

```java
		Scanner scanner = new Scanner(System.in);
        String account = InputUtils.getString("请输入账户:",scanner);
        String password = InputUtils.getString("请输入密码:",scanner);
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            dbConnection = JDBCUtils.getDBConnection();

            statement = dbConnection.createStatement();
            String sql = "select * from user where uname = '" + account + "' &&" +
                    " upassword = '" + password + "';";

            resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                System.out.println("账号: " + account + "\t密码: " + password +
                        "\t登录成功");
            }else{
                System.out.println("账号: " + account + "\t密码: " + password +
                        "\t登录失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                CloseUtils.closeResources(resultSet,statement,dbConnection,
                        scanner);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
```

- 输入`路飞`和`111111`, 拼接成的字符串是`select * from user where uname ='路飞' && upassword = '111111';`, 显示登录成功, 没有问题
  - ![](%E7%AC%AC05%E7%AB%A0%20JDBC.assets/One_2021-03-05_202142.png)
- 输入`路飞`和`aaa`, 拼接成的字符串是`select * from user where uname = '路飞' && upassword = 'aaa';`, 显示登录失败, 没有问题
  - ![](%E7%AC%AC05%E7%AB%A0%20JDBC.assets/One_2021-03-05_203736.png)
- 输入`路飞`和`aaa' or '1' = '1`, 拼接成的字符串是`select * from user where uname = '路飞' && upassword = 'aaa' or '1' = '1';`, 本该作为参数的字符串利用拼接字符串是的漏洞将`or '1' = '1'`注入到了SQL语句的判断语句中, 影响了结果的正确性
  - ![](%E7%AC%AC05%E7%AB%A0%20JDBC.assets/One_2021-03-05_203033.png)

### 4.2 如何防止SQL注入

- **要想防止SQL注入就必须避免用户输入的参数和我们编写的SQL语句进行简单的字符串拼接**
- 使用PreparedStatement对象作为语句执行平台可以避免SQL注入

### 4.3 预处理对象--PreparedStatement

#### 4.3.1 PreparedStatement接口介绍

- PreparedStatement是Statement接口的子接口, 继承了父接口中的所有方法, 它是一个预编译的语句执行对象, 即预编译的语句执行平台
- 预编译: 是指SQL语句被预编译, 并存储在PreparedStatement对象中, 然后可以使用该对象多次高效地执行SQL语句

#### 4.3.2 PreparedStatement对象的特点

- 可以对使用占位符`?`的SQL语句进行预编译, 避免了字符串的拼接, 从而防止了SQL注入
- 因为对SQL语句进行了预编译, 所以在多次执行相同的语句时就可以跳过编译SQL语句的过程了, 提高了复用SQL语句的执行效率

#### 4.3.3 获取PreparedStatement对象

- 通过`Connection`对象创建`PreparedStatement`对象

| Connection接口中获取PreparedStatement对象的方法 | 说明                                                         |
| ----------------------------------------------- | ------------------------------------------------------------ |
| PreparedStatement preparedStatement(String sql) | sql--指定预编译的SQL语句, 该语句中使用占位符`?`来表示一个参数, |

#### 4.4.4 PreparedStatement接口中常用的方法

| 常用方法                                     | 说明                                  |
| -------------------------------------------- | ------------------------------------- |
| int executeUpdate()                          | 执行关于insert/update/delete的SQL语句 |
| ResultSet executeQuery()                     | 执行关于select的SQL语句               |
| void setDouble(int parameterIndex, double x) | 将double类型的值x赋值给指定编号的参数 |
| void setInt(int parameterIndex, int x)       | 将int类型的值x赋值给指定编号的参数    |
| void setString(int parameterIndex, String x) | 将String类型的值x赋值给指定编号的参数 |
| void setObject(int parameterIndex, Object x) | 将Object类型的值x赋值给指定编号的参数 |

#### 4.4.5 PreparedStatement的使用步骤

1. 与数据库建立连接, 获取Connection对象
2. 编写SQL语句, 位置内容用`?`占位
3. 获取预编译语句执行平台, 即PreparedStatement对象
4. 设置实际参数: setXXX(占位符的位置, 值)\
5. 执行SQL语句, 获取结果
6. 关闭资源

#### 4.4.6 示例代码 -- 使用PreparedStatement避免SQL注入

```java
	    Scanner scanner = new Scanner(System.in);
        String account = InputUtils.getString("请输入账户:",scanner);
        String password = InputUtils.getString("请输入密码:",scanner);

        Connection dbConnection = null;
        PreparedStatement preparedStatement =
                null;
        ResultSet resultSet = null;
        try {
            dbConnection = JDBCUtils.getDBConnection();
            String sql = "select * from user where uname = ? && upassword = ?;";
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1,account);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                System.out.println("账号: " + account + "\t密码: " + password +
                        "\t登录成功");
            }else{
                System.out.println("账号: " + account + "\t密码: " + password +
                        "\t登录失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseUtils.closeResources(resultSet, preparedStatement, dbConnection,scanner);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
```

尝试sql注入, 结果失败了

![](%E7%AC%AC05%E7%AB%A0%20JDBC.assets/One_2021-03-05_214904.png)

##### 4.4.6.1 预处理语句中占位符`?`与其他字符串进行拼接

```java
String sql01 = "select * from user where uname = concat('?','%')";
String sql02 = "select * from user where uname = concat('?','_')";
String sql03 = "select * from user where uname = concat('%',?,'%')";
String sql04 = "select * from user where uname = concat('?','飞')";
```



#### 4.4.7 Statement与PreparedStatement的区别

- Statement用于执行静态SQL语句,在执行时, 必须指定一个事先准备好的SQL语句
- PreparedStatement时预编译的SQL语句对象, 语句中可以包含动态参数`?`, 在执行时可以为`?`动态设置值
- PreparedStatement可以减少编译次数, 从而提高数据库性能

![](%E7%AC%AC05%E7%AB%A0%20JDBC.assets/%E4%BB%BB%E5%8A%A1%E4%B8%80_JDBC-1614994434569.png)

## 五. JDBC控制事务

> 之前我们是使用MySQL的命令来操作事务, 接下来我们使用JDBC来操作银行转账的事务

### 6.0 准备数据

```sql
CREATE TABLE account(aic INT PRIMARY KEY AUTO_INCREMENT, 
			aname VARCHAR(10) UNIQUE NOT NULL, 
			money DOUBLE DEFAULT 0);
INSERT INTO account(aname, money) VALUES('路飞', 1000), 
					('汉库克', 1000);
SELECT * FROM account;
```

### 6.1 事务相关API

#### 6.1.1 `Connection`接口

| Connection接口中实现事务管理的方法      | 说明                                            |
| --------------------------------------- | ----------------------------------------------- |
| void setAutoCommit(boolean autoCommit)  | 参数为false时, 表示关闭自动提交, 相当于开启事务 |
| void commit()                           | 提交事务                                        |
| void rollback()                         | 回滚事务                                        |
| boolean getAutoCommit()                 | 值为false时, 表示事务处于开启状态               |
| void setTransactionIsolation(int level) | 设置事务隔离等级                                |
| int getTransactionIsolation()           | 获取事务隔离等级                                |

### 6.2 开发步骤

1. 与数据库建立连接
2. 开启事务
3. 获取到PreparedStatement对象, 执行两次更新操作
4. 正常情况下提交事务
5. 出现异常回滚事务
6. 最后关闭资源

### 6.3 示例代码

```java
		Scanner scanner = null;
        Connection dbConnection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        try {
            //1. 与数据库建立连接,并开启事务
            dbConnection = JDBCUtils.getDBConnection();
            dbConnection.setAutoCommit(false);

            //2. 创建两个sql语句的预处理执行平台, 一个用来减, 一个用来加
            String sql1 = "update account set money = money - ? where aname =" +
                    " " +
                    "'路飞';";
            preparedStatement1 = dbConnection.prepareStatement(sql1);

            String sql2 =
                    "update account set money = money + ? where aname = " +
                            "'汉库克';";
            preparedStatement2 = dbConnection.prepareStatement(sql2);

            //获取用户想转账多少钱
            scanner = new Scanner(System.in);
            double transfer = InputUtils.getDouble(
                    "'路飞'向'汉库克'转账多少钱?", scanner);

            preparedStatement1.setDouble(1, transfer);
            preparedStatement2.setDouble(1, transfer);
            //3. 执行转账
            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();

            //除0操作,可以产生异常导致事务失败
            /*int a = 1 / 0;*/
            //4. 转账成功, 提交事务
            dbConnection.commit();
            System.out.println("转账成功");
        } catch (Exception e) {
            try {
                if (null != dbConnection) {
                    //5. 转账失败, 回滚事务
                    dbConnection.rollback();
                    System.out.println("转账失败, 进行回滚");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } finally {
            try {
                CloseUtils.closeResources(preparedStatement2,
                        preparedStatement1,
                        dbConnection, scanner);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
```

## 六. 批处理(batch)

### 6.1 什么是批处理(batch)

- 批处理指的是一次操作中执行多条**增删改**的SQL语句, 批处理相比于一次只发送一行语句的执行效率要高很多
- 批处理可用于对数据进行增删改, **但一般只用于增**, 因为删改可以使用where条件表达式, 比一条一条地删改快多了
- 未使用批处理的时候, 语句执行平台需要把大量的SQL语句一句一句地传输给数据库服务器, 然后数据库服务器还需要将执行结果一个一个地反馈给客户端, 使用批处理后, 语句执行平台会把大量的SQL语句打包为一个整体, 只需要一次就可以把所有的语句传输给数据库服务器, 数据库服务器在执行完这些语句后, 会把每条SQL语句的执行结果封装到一个数组中传输给客户端

### 6.2 实现批处理的方法

- `Statement`和`PreparedStatement`都支持批处理操作
- `Statement`通过`void addBatch(String sql)`方法往批处理中添加内容, 通过`int[] executeBatch()方法`来执行批处理
- `PreparedStatement`通过`void addBatch()`方法往批处理中添加内容, 通过`int[] executeBatch()`方法来执行批处理

### 6.3 示例代码

#### 6.3.0 数据准备

```sql
use one_piece;
CREATE TABLE batch_test(bid INT PRIMARY KEY, bname VARCHAR(10));
```

#### 6.3.1 `PreparedStatement`实现批处理

- 注意: MySQL数据库的批处理默认是关闭的, 所以需要在url上加一个参数`rewriteBatchedStatements=true`才能打开MySQL数据库批处理

- ```java
  String url = "jdbc:mysql://localhost:3306/one_piece?" +
                  "characterEncoding=utf-8&rewriteBachedStatement=true";
  ```

- url中的多个参数可以用`&`连接起来

```java
    /**
    * 批量添加数据
    */
	@Test
    public void batchAddTest() throws Exception {
        String url = "jdbc:mysql://localhost:3306/one_piece?" +
                "characterEncoding=utf-8&rewriteBachedStatement=true";
        String user = "zq";
        String password = "111111";
        Connection dbConnection = DriverManager.getConnection(url,user,
                password);
        String sql = "INSERT INTO batch_test VALUES(?, ?);";
        PreparedStatement preparedStatement =
                dbConnection.prepareStatement(sql);
        for (int i = 1; i <= 10000; i++){
            preparedStatement.setInt(1,i);
            preparedStatement.setString(2,"蒂法" + i);
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
        CloseUtils.closeResources(preparedStatement,dbConnection);
    }

	/**
	* 批量删除数据
	*/
    @Test
    public void deleteBatchTest() throws Exception {
        String url = "jdbc:mysql://localhost:3306/one_piece?" +
                "characterEncoding=utf-8&rewriteBachedStatement=true";
        String user = "zq";
        String password = "111111";
        Connection dbConnection = DriverManager.getConnection(url,user,
                password);
        String sql = "delete from batch_test where bname = ?";
        PreparedStatement preparedStatement =
                dbConnection.prepareStatement(sql);
        for (int i = 1; i <= 10000; i++){
            preparedStatement.setString(1,"蒂法" + i);
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
        CloseUtils.closeResources(preparedStatement,dbConnection);
    }
```

## 七. MySQL元数据

## 7.1 什么是元数据

> 除表中的记录之外的数据都是元数据, 可以分为三类

- **增改删记录后的结果信息**: 增改删语句执行后收到影响的记录数
- **数据库和数据表的信息**: 包含了数据库及数据表的结构信息
- **MySQL服务器信息**: 包含了数据库服务器的当前状态、版本号等

### 7.2 SQL获取元数据的常用命令

| 命令                                        | 介绍                      |
| ------------------------------------------- | ------------------------- |
| show status;                                | 查看服务器当前状态        |
| select version();                           | 查看MySQL服务器的版本信息 |
| show databases;                             | 列出所有数据库            |
| select database();                          | 获取当前使用的数据库名    |
| show tables;                                | 显示当前数据库下的所有表  |
| desc 表名;<br />或者show columns from 表名; | 查询表中的详细信息        |
| show index from 表名;                       | 显示数据表的详细索引信息  |

### 7.3 使用JDBC获取元数据

通过JDBC也可以获取到元数据, 比如数据库的相关信息, 或者党委偶们使用程序查询一个不熟悉的表时, 我们可以通过获取元数据信息, 了解表中有多少个字段, 字段的名称和字段的类型

#### 7.3.1 常用类介绍

##### 7.3.1.1 JDBC中描述元数据的类

| 元数据类          | 作用                   |
| ----------------- | ---------------------- |
| DatabaseMetaData  | 描述数据库的元数据对象 |
| ResultSetMetaData | 描述结果集的元数据对象 |

- `DatabaseMetaData`对象可以通过数据库连接对象即`Connection`对象的`getMeataData()`方法来获取
- `ResultSetMetaData`对象可以通过结果集对象即`ResultSet`对象的`getMetaData()`方法来获取

##### 7.3.1.2 `DatabaseMetaData`的常用方法

| 方法                               | 方法说明                 |
| ---------------------------------- | ------------------------ |
| String getUrl()                    | 获取数据库的url          |
| String getUserName()               | 获取当前数据库的用户名   |
| String getDatabaseProductName()    | 获取数据库的产品名称\    |
| String getDatabaseProductVersion() | 获取数据库的版本号       |
| String getDriverName()             | 返回驱动程序的名称       |
| boolean isReadOnly()               | 判断数据库是否只允许只读 |

##### 7.3.1.3 `ResultSetData`的常用方法

| 方法                                | 方法说明                              |
| ----------------------------------- | ------------------------------------- |
| int getColumnCount()                | 当前结果集中共有多少**列**            |
| String getColumnName(int inde       | 获取指定列号的列名, 列号是从1开始数的 |
| String getColumnTypeName(int index) | 获取指定列号的类型, 列号是从1开始数的 |

#### 7.4 示例代码

```java
String url = "jdbc:mysql://localhost:3306/one_piece?characterEncoding" +
                "=utf-8&rewriteBatchedStatements=true";
        String user = "zq";
        String password = "111111";
        Connection dbConnection = DriverManager.getConnection(url, user,
                password);

        DatabaseMetaData databaseMetaData = dbConnection.getMetaData();
        System.out.println(databaseMetaData.getURL());
        System.out.println(databaseMetaData.getUserName());
        System.out.println(databaseMetaData.getDatabaseProductName());
        System.out.println(databaseMetaData.getDatabaseProductVersion());
        System.out.println(databaseMetaData.getDriverName());
        System.out.println(databaseMetaData.isReadOnly());

        System.out.println("----------------------------------");

        Statement statement = dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from pirate;");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        System.out.println(resultSetMetaData.getColumnCount());
        System.out.println(resultSetMetaData.getColumnName(2));
        System.out.println(resultSetMetaData.getColumnTypeName(2));
        
        CloseUtils.closeResources(resultSet,statement,dbConnection);
```

