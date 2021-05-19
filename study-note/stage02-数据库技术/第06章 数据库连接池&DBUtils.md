# 第06章 数据库连接池&DbUtils

[toc]

## 一. 数据库连接池

### 1.1 连接池介绍

#### 1.1.1 什么是连接池

实际开发中"获得链接"或"释放资源"是非常消耗系统资源的两个过程,为了解决此类性能问题, 通常我们采用连接池技术, 来共享连接Connectiion. 这样我们就不需要每次远程操作数据库都要创建连接, 释放资源了, 这些操作都交给了连接池

#### 1.1.2 连接池的好处

用连接池来管理Connection对象, 这样可以重复使用Connection对象, 当使用完Connection对象后, 调用Connection对象的close()方法并不会真的关闭Connection, 而是把Connection放到连接池里闲置起来

### 1.2 普通JDBC方式与连接池方式

- 普通JDBC方式
  - ![](%E7%AC%AC06%E7%AB%A0%20%E6%95%B0%E6%8D%AE%E5%BA%93%E8%BF%9E%E6%8E%A5%E6%B1%A0&DBUtils.assets/%E6%99%AE%E9%80%9AJDBC%E6%96%B9%E5%BC%8F.png)
- 连接池方式
  - ![](%E7%AC%AC06%E7%AB%A0%20%E6%95%B0%E6%8D%AE%E5%BA%93%E8%BF%9E%E6%8E%A5%E6%B1%A0&DBUtils.assets/%E6%95%B0%E6%8D%AE%E5%BA%93%E8%BF%9E%E6%8E%A5%E6%B1%A0%E6%96%B9%E5%BC%8F.png)

### 1.3 数据库连接池的使用

> - Java为数据库连接池提供了公共的接口:`javax.sql.DataSource`, 各个数据库连接池的厂商需要让自己的连接池实现这个接口, 这样应用程序就可以方便的而切换不同厂商的连接池了
> - 注意: 数据库服务器驱动的厂商和数据库连接池的厂商是不同的

#### 1.3.1 DBCP连接池

> DBCP是一个开源的连接池, 全称是Database connection pool, 是Apache成员之一, 在企业开发中也比较常见, tomcat内置的连接池

##### 1.3.1.1 下载DBCP连接池的Jar包

1. 谷歌搜索`Apache Commons`, 或者点击网址<https://commons.apache.org/>
2. 点击网页右侧栏中的`Realses`, 网页搜索`DBCP`, 点击`DBCP`
3. 在`Binaries`下找到所需的版本, Windows系统使用`.zip`类型的文件, 解压后找到所需的`commons-dbcp2-2.8.0.jar`
4. `dbcp2`还需要配合`Apache`旗下的`pool2`包和`loggin`包才能使用, 用相同的方法把这两个包下载下来

##### 1.3.1.2 编辑DBCPUtils

```java
public class DBCPUtils {
    public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/one_piece" +
            "?characterEncoding=utf-8";
    public static final String USER = "zq";
    public static final String PASSWORD = "111111";
    /**
     * 创建一个连接池对象, 用final修饰后, 这个引用的值就不能修改了
     */
    private static BasicDataSource dataSource = new BasicDataSource();

    static{
        dataSource.setDriverClassName(DRIVER_NAME);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaxTotal(30);
        dataSource.setMaxIdle(15);
        dataSource.setMinIdle(5);
        dataSource.setInitialSize(5);
    }

    public static Connection getDbConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static BasicDataSource getDataSource(){
        return dataSource;
    }
```

##### 1.3.1.3 测试DBCPUtils

```java
Connection dbConnection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            dbConnection = DBCPUtils.getDbConnection();
            statement = dbConnection.createStatement();
            //1. 获取所有团队的名字即每个团队下所有成员的名字
            String sql = "SELECT t.`tname`,p.`pname` FROM pirate p RIGHT JOIN " +
                    "team t ON p.`team_id` = t.`team_id`;";
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String team = resultSet.getString("tname");
                String name = resultSet.getString("pname");
                System.out.println(team + " : " + "\t" + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseUtils.closeResources(resultSet, statement, dbConnection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
```

##### 1.3.1.4 DBCP中常见配置项

**BasicDataSources**对象需要进行设置的属性

| 属性            | 描述             |
| --------------- | ---------------- |
| driverClassName | 数据库驱动名称   |
| url             | 数据库服务器地址 |
| userName        | 用户名           |
| password        | 密码             |
| maxTotal        | 最大连接数量     |
| maxIdle         | 最大空闲连接数   |
| minIdle         | 最小空闲连接数   |
| initialSize     | 初始化连接数     |

#### 1.3.2 C3P0连接池

> C3P0是一个开源的JDBC连接池, 支持JDBC3和JDBC2的标准规范, 由`Steve Waldman`开发, 在github上可以搜索到. 目前使用它的开源项目有Hibernate、Spring等.
>
> c3p0 is a mature, highly concurrent JDBC Connection pooling library, with support for caching and reuse of PreparedStatements.

##### 1.3.2.1 下载C3P0连接池的jar包

1. 打开网址<https://www.mchange.com/projects/c3p0/>
2. 点击`Download the latest version from c3p0's site on SourceForge`
3. 点击下载`Released /c3p0-bin/c3p0-0.9.5.5/c3p0-0.9.5.5.bin.zip`, 解压后在lib文件夹下的所有jar包都要用到
5. 点击下载`Released /c3p0-src/c3p0-0.9.5.5/c3p0-0.9.5.5.src.zip`, 解压后在`src/test-properties`下找到`c3p0-config.xml`这是c3p0的配置文件

##### 1.3.2.2 对C3P0进行设置

其实就是修改`c3p0-config.xml`的内容, 

1. 在模块下创建一个`resources`文件夹, 右键  点击`Mark Directory as`, 点击``resource root`,
2. 如果没有显示`resources root`, 那就打开模块下的`模块名.iml`文件, 在按照标签层级module/component/content找到标签`content`, 在该标签下插入`<sourceFolder url="file://$MODULE_DIR$/resources" type="java-resource" />`

```xml
<c3p0-config>
    <!-- 主要是看named-config标签里的设置 -->
    <!--默认配置, 下面的自定义配置没设置到的就是用默认配置里的内容, -->
    <default-config>
        <!-- 连接池创建好后就往里面放10个数据库连接-->
        <property name="initialPoolSize">10</property>
        <!-- 连接池里至少要有10个连接 -->
        <property name="minPoolSize">10</property>
        <!-- 连接池里至多有100个的连接 -->
        <property name="maxPoolSize">100</property>
        <!-- 连接池里的某个连接在30秒内没有使用过的话就会自动销毁, 如果为0, 则这个连接永远不会销毁 -->
        <property name="maxIdleTime">30</property>
    </default-config>

    <!--配置连接池mysql, 一个named-config标签就是一个连接池-->
    <named-config name="mysql">
        <!-- 数据库驱动-->
        <property name="driverClass">com.mysql.jdbc.Driver</property>
        <!-- 数据库服务器的url-->
        <property name="jdbcUrl">jdbc:mysql://localhost:3306/one_piece?characterEncoding=UTF-8</property>
        <!-- 用户 -->
        <property name="user">zq</property>
        <!-- 密码 -->
        <property name="password">111111</property>
    </named-config>
    <!--可以配置多个named-config, 为不同的url或不同的user的创建连接池-->

</c3p0-config>
```

###### 配置介绍

| name            | 说明                                                         |
| --------------- | ------------------------------------------------------------ |
| **driverClass** | 驱动                                                         |
| **jdbcUrl**     | 数据库服务器地址                                             |
| **user**        | 用户                                                         |
| **password**    | 密码                                                         |
| initialPoolSize | 连接池创建好后就往里面放x个数据库连接                        |
| maxIdleTime     | 连接池里的某个连接在x秒内没有使用过的话就会自动销毁, 如果为0, 则这个连接永远不会销毁 |
| maxPoolSize     | 连接池最多开x个连接                                          |
| minPoolSize     | 连接池中最少要有x个连接                                      |

- 标签**default-config**下一般需要设置:`initialPoolSize` `maxIdleTime` `maxPoolSize` `minPoolSize`
- 标签**named-config**下必须设置`diverClass` `jdbcUrl` `user` `password`, 其他的可以按需要设置

##### 1.3.2.3 编辑C3P0Utils

```java
public class C3P0Utils {
    //1. 创建连接池
    //默认创建的连接池对象使用的是标签default-config下的设置
    /*public static final ComboPooledDataSource DATA_SOURCE =
            new ComboPooledDataSource();*/
    //可以通过传入字符串参数来使用指定named-config标签下的设置
    private static ComboPooledDataSource dataSource =
            new ComboPooledDataSource("mysql");

    /**
     * 从数据库连接池里获取一个数据库的连接
     * @return
     * @throws SQLException
     */
    public static Connection getDbConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static ComboPooledDataSource getDataSource(){
        return dataSource;
    }
}
```

#### 1.3.3 DRUID连接池

> Druid(德鲁伊)是阿里巴巴卡法的号称为监控而生的数据库连接池, Druid是目前最好的数据库连接池, 在功能、性能、扩展性方面，都超过其他数据库连接池， 同时加入了日志监控，可以很好地监控DB池连接和SQL的执行情况。

##### 1.3.3.1 下载DRUID连接池的jar包

1. 打开github， 搜索`druid`, 打开`alibaba/druid`
2. 点击打开`Release`, 点击`druid下载`后面的网址
3. 点击下载`druid-版本号.jar`和`druid-版本号-sources.jar`

##### 1.3.3.2 对DRUID进行配置

其实就是修改`druid.properties`的内容, 

1. 在模块下创建一个`resources`文件夹, 右键  点击`Mark Directory as`, 点击``resource root`,

2. 如果没有显示`resources root`, 那就打开模块下的`模块名.iml`文件, 在按照标签层级module/component/content找到标签`content`, 在该标签下插入`<sourceFolder url="file://$MODULE_DIR$/resources" type="java-resource" />`

3. 在该文件夹下创建一个`druid.properties`文件, 文件的内容是

   - ```txt
     driverClassName=com.mysql.jdbc.Driver
         url=jdbc:mysql://localhost:3306/one_piece?characterEncoding=utf-8
     username=zq
         password=111111
         initialSize=5
         maxActive=100
         maxWait=3000
     ```
     
   - 第1行`driverClassName=com.mysql.jdbc.Driver`不需要写了, 因为新版本的MySql驱动Jar包中, 注册驱动的类由`com.mysql.jdbc.Driver`换成了`com.mysql.jdbc.cj.Driver`, `com.mysql.jdbc.Driver`被弃用了, 而且新的注册驱动的类会经由SPI自动注册驱动, 不需要手动注册数据库驱动了.



##### 1.3.3.3 编辑DRUIDUtils

```java
public class DRUIDUtils {
    private static DataSource dataSource;

    static {
        try {
            Properties properties = new Properties();
            InputStream druidProperties =
                    DRUIDUtils.class.getClassLoader().getResourceAsStream(
                            "druid.properties");
            properties.load(druidProperties);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getDbConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static DataSource getDataSource(){
        return dataSource;
    }
}
```

## 二. DbUtils工具类

### 2.1 DbUtils介绍

使用JDBC我们发现冗余代码太多了,为了简化开发, 我们选择使用DbUtils, DbUtils是Apache组织提供的一个对JDBC进行简单封装的开源工具类库, 使用它能够简化JDBC应用程序的开发, 同时也不会影响程序的性能

### 2.2 DbUtils下载

1. 搜索`Apache common`, 或直接打开网址<https://commons.apache.org/>
2. 点击`Release`, 网页搜索`DbUtils`, 点击`DbUtils`
3. 点击`commons-dbutils-1.7-bin.zip`, 下载后解压

### 2.3 DbUtils核心功能

- `QueryRunner`中提供对sql语句操作的API
- `ResultSetHandler`接口, 用于定义select操作后, 怎样封装结果集
- `UbUtils`类, 就是一个工具类, 定义了关闭资源与事物处理相关方法



### 2.4 DbUtils完成CRUD

#### 2.4.1 QueryRunner核心类

##### 2.4.1.1 构造方法

| 构造方法                           | 说明                                                         |
| ---------------------------------- | ------------------------------------------------------------ |
| QueryRunner()                      | 在执行sql语句时, 需要手动提供数据库连接, 被称为**手动模式**  |
| QueryRunner(DataSoruce dataSource) | 绑定数据源(即数据库连接池), DbUtil底层会自动从该连接池里获取连接, 被称为**自动模式**,  **建议使用该模式创建对象** |

##### 2.4.1.2 常用方法

| 方法                                                         | 说明                       |
| ------------------------------------------------------------ | -------------------------- |
| int update(Connection conn, String sql, Object...params)     | 用来对表数据进行**增改删** |
| `<T> T query(Connection conn, String sql, Object...params)`  | 用来对表数据进行**查询**   |
| int update(String sql, Object...params)                      | 用来对表数据进行**增改删** |
| `<T> T query(String sql, ResultSetHandler<T> rsh,Object...params)` | 用来对表数据进行**查询**   |

==注意: QueryRunner中编写的sql语句最后最好不要加`;`, 因为DbUtils底层会自动帮你加的,==

#### 2.4.2 QueryRunner对象的创建

##### 2.4.2.1 手动模式

```java
QueryRunner queryRunner = new QueryRunner();
```

##### 2.4.2.2 自动模式

```java
QueryRunner queryRunner = new QueryRunner(DRUIDUtils.getDataSource())
```

#### 2.4.3 QueryRunner实现增改删操作

##### 2.4.3.1 核心方法

###### 2.4.3.1.1 QueryRunner中

- 自动模式

  - `int update(String sql, Object[] params)`
  - `int update(String sql,Object...params)`

- 手动模式

  - `int update(Connection conn, String sql, Object[] params)`
  - `int update(Connection conn, String sql, Object...params)`

- | 参数            | 说明                                        |
  | --------------- | ------------------------------------------- |
  | Connection conn | 数据库连接对象                              |
  | String sql      | 可以是普通sql, 也可以是占位符`?`形式的sql   |
  | Object...params | Oject类型的可变参数, 用来设置占位符上的参数 |
  | Object[] params | Object类型的数组, 用来设置占位符上的参数    |

###### 2.4.3.1.2 DbUtils中

- `void closeQuietly(Connection conn`

##### 2.4.3.2 编码步骤

1. 创建QueryRunner对象(手动模式或自动模式)
2. 编写sql(可以按需设置占位符`?`)
3. 设置占位符参数(如果sql有占位符)
4. 执行
5. 关闭资源

##### 2.4.3.3 示例代码

```java
   /**
     * 手动模式实现添加数据
     *
     * @throws SQLException
     */
    @Test
    public void insertCreate() throws SQLException {
        //1. 创建QueryRunner对象
        QueryRunner queryRunner = new QueryRunner();
        //2. 编写sql语句
        String sql = "insert into pirate values(?,?,?,?,?,?,?)";
        //3. 设置占位符参数
        Object[] params = new Object[]{null, "薇薇", 19, "女", "2001.6.1", 10, 1};
        Connection dbConnection = DRUIDUtils.getDbConnection();
        //4. 执行
        int updateRows1 = queryRunner.update(dbConnection, sql, params);
        System.out.println("updateRows1 = " + updateRows1);
        //再添加一条数据
        // 3&4. 设置占位符参数并执行
        int updateRows2 = queryRunner.update(dbConnection,
                sql, null, "瑞贝卡", 17, "女", "2003.8.1", 0, 1);
        System.out.println("updateRows2 = " + updateRows2);

        //5. 关闭资源
        DbUtils.closeQuietly(dbConnection);
    }

    /**
     * 自动模式修改记录
     * @throws SQLException
     */
    @Test
    public void updateTest() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DRUIDUtils.getDataSource());
        String sql = "update pirate set reward = ? where pname = ?";
        int updateRows = queryRunner.update(sql, 0, "薇薇");
        System.out.println("更新了" + updateRows + "行记录");
    }

    /**
     * 自动模式删除记录
     * @throws SQLException
     */
    @Test
    public void deleteTest() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DRUIDUtils.getDataSource());
        String sql = "delete from pirate where pname in(?, ?)";
        int deleteRows = queryRunner.update(sql, "薇薇","瑞贝卡");
        System.out.println("删除了" + deleteRows + "行记录");
    }
```

#### 2.4.4 QueryRunner实现查询操作

##### 2.4.4.0  案例相关知识

###### 2.4.4.0.1 表和类之间的关系

- 整个表结构可以看作是一个类
- 表中的一行记录, 对应一个类的实例(对象)
- 表中的一列, 对应类中的一个成员变量

###### 2.4.4.0.2 JavaBean组件

JavaBean组件就是一个类, 开发中通常用于封装数据, 一般放置在entity或者entities包下, 其具有以下特点:

- 必须能够序列化, 即实现`Seriallizable`接口
- 提供私有字段; private 类型 变量名(变量名必须与表中对应的列明相同)
- 提供空参构造方法
- 对每个私有字段都要提供getter和setter

##### 2.4.4.1 `ResultSetHandler<T>`接口

`ResultSetHandler<T>`可以对查询出来的ResultSet结果集进行分析处理, 达到一些业务上的需求

##### 2.4.4.2 `ResultSetHandler<T>`的常用实现类

以下`ResultSetHandler<T>`接口的实现类可以大大减少对数据库进行查询操作时的代码量, 优化程序

| `ResulSetHandler<T>`实现类 | 说明                                                         |
| -------------------------- | ------------------------------------------------------------ |
| `ArrayHandler<T>`          | 将结果集中的第一条记录封装到一个Object[]数组中, 数据组中的每一个元素就是这条记录中的每一个字段的值 |
| `ArrayListHandler<T>`      | 将结果集中的每一条记录分别封装到对应的Object[]数组中, 再将这些数组封装到一个List集合中 |
| `BeanHandler<T>`           | 将结果集中的第一条记录封装到一个指定的JavaBean组件中         |
| `BeanListHandler<T>`       | 将结果集中的每一条记录都分别封装到对应的javaBean组件中, 再将这些JavaBean封装到一个List集合中 |
| ColumnListHandle\<T\>      | 将结果集中指定的列的  所有的值, 封装到一个List集合中         |
| `MapHandler<T>`            | 将结果集中的第一条记录封装到一个Map<String,Object>集合中, key是字段的名称, value是字段的值 |
| `MapListHandler<T>`        | 将结果集中的每一条记录都分别封装到对应的Map<String,Object>集合中, key是字段的名称, value是字段的值<br />再将这些Map集合封装到一个List集合中 |
| KeyedHandler\<T\>          | 将结果集中的每一条记录都分别封装到对应的Map<String,Object>集合中, key是字段的名称, value是字段的值; <br />再将这些Map集合封装到一个`Map<String, Map<String,Object>>`集合中,  该集合的key是记录中一个字段的值(创建该对象是通过columnIndex指定是哪一个字段, 默认是第1个), value就是封装了记录 (key所在的记录) 的Map<String,Object>集合 |
| `ScalarHandler<T>`         | 用于封装单个数据 例如 `select count(列名) from 表名`         |

##### 2.4.4.3 演示代码

###### 2.4.4.3.1 创建一个跟pirate表对应的JavaBean组件

```java
    @Test
    public void arrayHandlerTest() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DRUIDUtils.getDataSource());
        String sql = "select * from pirate where age > 20";
        Object[] result = queryRunner.query(DRUIDUtils.getDbConnection(), sql,
                new ArrayHandler());
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void arrayListHandlerTest() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DRUIDUtils.getDataSource());
        String sql = "select * from pirate where age > ?";
        List<Object[]> result = queryRunner.query(sql, new ArrayListHandler(),
                20);
        for (Object[] objects :
                result) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void beanHandlerTest() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DRUIDUtils.getDataSource());
        String sql = "select * from pirate where age > ?";
        Pirate result = queryRunner.query(sql, new BeanHandler<>(Pirate.class)
                , 20);

        System.out.println(result.toString());
    }

    @Test
    public void beanListHandlerTest() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DRUIDUtils.getDataSource());
        String sql = "select * from pirate where age > ?";
        List<Pirate> result = queryRunner.query(sql,
                new BeanListHandler<>(Pirate.class)
                , 20);

        for (Pirate pirate :
                result) {
            System.out.println(pirate.toString());
        }
    }

    @Test
    public void mapHandler() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DRUIDUtils.getDataSource());
        String sql = "select * from pirate where age > ?";
        Map<String, Object> result = queryRunner.query(sql, new MapHandler()
                , 20);

        System.out.println(result.toString());
    }

    @Test
    public void mapListHandler() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DRUIDUtils.getDataSource());
        String sql = "select * from pirate where age > ?";
        List<Map<String, Object>> result = queryRunner.query(sql,
                new MapListHandler()
                , 20);

        for (Map<String, Object> map :
                result) {
            System.out.println(map.toString());
        }
    }

    @Test
    public void keyedHandler() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DRUIDUtils.getDataSource());
        String sql = "select * from pirate where age > ?";
        Map<String, Map<String, Object>> result = queryRunner.query(sql,
                new KeyedHandler<>(2)
                , 20);

        System.out.println(result.toString());
    }
    
    @Test
    public void scalarHandlerTest() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DRUIDUtils.getDataSource());
        String sql = "select count(pid) from pirate where age > ?";
        Long result = queryRunner.query(sql,
                new ScalarHandler<>(), 20);

        System.out.println("pirate表中有" + 14 + "个20岁以上的记录");
    }
```

### 2.5 DbUtils实现批处理

> 只能批处理**增删改**, 但是一般只用于增, 因为删改可以使用**where条件表达式**, 比一条一条地操作快多了

#### 2.5.1 核心类及所用方法

- 核心类`QueryRunner`

- | 批处理实现方法                                               | 说明                                      |
  | ------------------------------------------------------------ | ----------------------------------------- |
  | `int[] batch(String sql, Object[][] params)`                 | 自动模式创建的`QueryRunner`对象使用该方法 |
  | `int[] batch(Connection conn, String sql, Object[][] params)` | 手动模式创建的`QueryRunner`对象使用该方法 |

- | 参数   | 说明                                                         |
  | ------ | ------------------------------------------------------------ |
  | conn   | 数据库连接                                                   |
  | sql    | 一般是插入数据的sql语句, 该语句一定会使用占位符`?`           |
  | params | 将需要批处理的每一条数据封装到对应的Object[]数组中, 再将这些数组封装到一个`Object[]erw[]`二维数组中, 这个二维数组就是所有需要插入的数据 |

#### 2.5.2 示例代码

```java
	/**
     * 批量插入数据
     * @throws SQLException
     */
    @Test
    public void batchInsertTest() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DRUIDUtils.getDataSource());
        //注意DbUtils中的进行批处理是的sql语句最后绝对不能加`;`
        String sql = "insert into batch_test values(?,?)";
        //1. 创建一个临时存放sql参数的集合作为临时容器
        LinkedList<Object[]> paramsTemp = new LinkedList<>();
        //2. 每一条sql参数存放分别到对应的Object[]数组中, 再把这个Object[]数组存放到临时容器中
        for (int i = 10001; i <= 20000; i++){
            paramsTemp.add(new Object[]{i,"爱丽丝"+i});
        }
        //3. 创建一个长度跟临时容器相同的二维数组作为最终容器
        Object[][] params = new Object[paramsTemp.size()][];
        //4. 将临时容器中的元素一个一个地存放到最终容器中
        paramsTemp.toArray(params);

        int[] batch = queryRunner.batch(sql,params);
    }

    /**
     * 批量删除数据
     */
    @Test
    public void batchDeleteTest() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DRUIDUtils.getDataSource());
        String sql = "delete from batch_test where bname = ?";

        LinkedList<Object[]> paramsTemp = new LinkedList<>();
        for (int i = 10001; i <= 20000; i++) {
            paramsTemp.add(new Object[]{"爱丽丝" + i});
        }
        Object[][] params = new Object[paramsTemp.size()][];
        paramsTemp.toArray(params);
        int[] deleteResult = queryRunner.batch(sql, params);
    }
```

### 2.6 DbUtils实现事务操作

#### 2.6.1 核心类及所用方法

##### 2.6.1.1 Connection类

| 方法                                   | 说明                                |
| -------------------------------------- | ----------------------------------- |
| void setAutoCommit(boolean autoCommit) | 将autoCommit的值设为`false`开启事务 |

##### 2.6.1.2 QueryRunner类

**只能使用手动模式创建**, 只能使用update(conn, sql, params)方法和query(conn, sql, handler, params)方法实现增查改删

##### 2.6.1.3 DbUtils类

| 方法                                          | 说明                               |
| --------------------------------------------- | ---------------------------------- |
| void commitAndCloseQuietly(Connection conn)   | 提交事务, 并且静默地关闭数据库连接 |
| void rollbackAndCloseQuietly(Connection conn) | 回滚事务, 并且静默地关闭数据库连接 |



#### 2.6.2 示例代码

```java
	@Test
    public void test(){
        Connection dbConnection = null;
        Scanner scanner = null;
        double transfer = 0;
        try {
            QueryRunner queryRunner = new QueryRunner();
            dbConnection = DRUIDUtils.getDbConnection();
            dbConnection.setAutoCommit(false);
            String sql1 = "update account set money = money - ? where aname = '路飞'";
            String sql2 =
                        "update account set money = money + ? where aname = '汉库克'";
            scanner = new Scanner(System.in);
            transfer = InputUtils.getDouble(
                    "'路飞'向'汉库克'转账多少钱(可以精确到小数点后两位)",scanner);

            queryRunner.update(dbConnection,sql1,transfer);
            queryRunner.update(dbConnection,sql2,transfer);

            //如果转账额度超过了余额, 就抛出异常
            if (transfer > getBalance("路飞", queryRunner, dbConnection)){
                throw new Exception("余额不足, 无法转账");
            }

            DbUtils.commitAndCloseQuietly(dbConnection);
            System.out.println("'路飞'向'汉库克'转账" + transfer + "元成功");
        } catch (Exception e) {
            DbUtils.rollbackAndCloseQuietly(dbConnection);
            System.out.println(e.getMessage());
        } finally {
            CloseUtils.closeResourcesQuietly(scanner);
        }


    }

    /**
     * 获取账户`name`的余额
     * @param name
     * @param queryRunner
     * @param dbConnection
     * @return
     */
    private double getBalance(String name, QueryRunner queryRunner, Connection dbConnection) throws SQLException {
        String sql = "select money from account where aname = ?";
        Object[] result = queryRunner.query(dbConnection, sql,
                new ArrayHandler(),name);
        return (double) result[0];
    }
```







