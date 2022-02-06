# 第01章 MySQL基础和SQL入门

[toc]

## 一. 数据库(Database)的基本概念

### 1. 什么是数据库

- 数据库(Database)就是存储和管理数据的仓库
- 其本质是一个文件, 按照统一的规则(SQL)将数据保存在电脑硬盘上

### 2. 数据存储方式的比较

| 存储方式 | 优点                                                         | 缺点                                                         |
| -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 内存     | 速度快                                                       | 数据是临时状态的不能永久保存                                 |
| 文件     | 数据是可以永久保存的                                         | 使用IO流操作文件, 读写都不方便                               |
| 数据库   | 1. 数据是可以永久保存的<br />2. 方便存储和管理数据<br />3. 使用统一的方式(SQL)操作数据，可以很方便地对数据库中的数据进行增查改删(CRUD)的操作 | 占用资源, 有些管理数据库的软件(比如Orable数据库、MySQL商业版)还要付费 |

- 增 --- Create --- C
- 查 --- Retrieve --- R
- 改 --- Update --- U
- 删 --- Delete --- D

### 3. 常见的数据库软件

- Oracle数据库软件、MySQL数据库软件、Microsoft SQL Server软件、IBM DB2软件等等
- 但电脑上安装上这些软件中的一种时，电脑就成了对应类型的数据库服务器，我们可以在服务器本地对数据库进行操作、或者远程对服务器上的数据库进行操作
- 每一个软件都会都有一套自己的规则来管理数据库，==但是所有软件在管理数据库在操作时都遵循SQL语言==，所以只要学好SQL语言就可以操作所有数据库软件上的数据库

#### 开发中常见的数据库类型

| 数据库类型   | 介绍                                                         |
| ------------ | ------------------------------------------------------------ |
| My SQL数据库 | 社区(Community)版本开源免费, 商业版(Enterprise)收费<br />常作为中小型项目的数据库首选 |
| Oracle数据库 | 收费的大型数据库, Oracle的核心产品, 安全性高                 |
| DB2          | IBM的数据库产品, 收费的超大型数据库, 常在银行系统中使用      |
| SQL Server   | 微软公司收费的中型数据库, C# .net等语言常使用, 但该数据库只能运行在windows系统上, 扩展性、稳定性、安全性、性能都表现平平 |

## 二.  MySQL

### 1. 概述

- MySQL在被Oracle公司收购后，自6.0版本分为Community(社区版)和Enterprise(商业版)两个版本
- Community(社区版)是开源免费的，但不提供官方服务支持
- Enterprise(商业版)是收费的，提供官方服务支持
- Community 5.7.28版本是历史版本中最稳定的版本，所以学习过程中使用这个版本

### 2. MySQL下载

1. 打开MySQL官网<https://www.mysql.com/>
2. 点击`DOWNLOADS`
3. 点击`MySQL Community (GPL) Downloads`
4. 点击`MySQL Installer for Windows`
   1. `General Availability (GA) Release`是最新的版本
   2. `Archieves`(档案)是历史版本
   3. 下载那个比较大的安装文件

### 3. 安装

- 只用到了服务器功能, 安装上其他的东西会拖慢电脑的性能,所以选`Server only`
- 选`Standalone MySQL Server/Classic MySQL Replication`
- Config Type设置为`Development Computer`耗费电脑资源较少
- [x] Tcp/IP                                      port:<u>3306</u>
- [x] Open Windows Firewall port for network access
- ==设置MySQL Root Password==(**一定要记住这个root用户的密码**)
- 记住**MySQL服务的名字**--==Windows Service Name==(Community5.7.28版本默认的名字是MySQL57, 8.0版本的名字是MySQL80)

### 4. MySQL环境变量配置

- 安装结束后马上配置MySQL的环境变量
  - 打开文件资源管理器, 右键此电脑, 选择属性
  - 滚动到最下面,点击高级系统设置,点击环境变量
  - 系统变量里设置
    - 新建: 变量名--MYSQL_HOME    变量值--MySQL的安装位置
    - Path    %MYSQL_HOME%\bin

### 5. MySQL服务的手动启动与关闭

#### (1). 从Windows服务启动MySQL服务

1. 右键`Win`图标, 点击计算机管理, 选择服务和应用程序, 双击服务打开
2. 找到**MySQL服务**, 可以通过右键进行启动或停止

#### (2). Dos命令方式启动

1. 以管理员身份打开CMD或者Windows Terminal
2. 启动MySQL服务: `net start MySQL服务的名字`
3. 关闭MySQL服务: `net stop MySQL服务的名字`

### 6. MySQL服务器默认时区和默认编码的设置

1. 在`C:\ProgramData\MySQL\MySQL Server 8.0`下找到`my.ini`

   1. 先将`my.ini`备份到其他位置, 如果失败了, 就用备份覆盖修改后的my.ini

2. 打开`my.ini`

3. 修改默认时区

   1. 查找`[mysqld]`, 在`[mysqld]`下输入以下文本(==若没有`[mysqld]`这一行，则在文末手动添加==)

      1. ```
         default-time_zone='+8:00'
         ```

   2. +8:00表示东八区, 是中国法定时区, 设置时区在IDEA中要用

4. 修改mysql的服务端默认编码
   1. 在`[mysqld]`下面添加(==若没有`[mysqld]`这一行，则在文末手动添加==)

      1. ```shell
         character-set-server=utf8mb4
         ```

5. 设置mysql客户端的默认编码,

   1. 在`[client]`下面添加(==若没有`[client]`这一行，则在**文末**手动添加==)

      1. ```shell
         default-character-set=utf8mb4
         ```

   2. 设置客户端默认编码 ，此处不加，客户端查询出来的中文好像还是拉丁文

6. 重启MySQL服务

### 7. 卸载

- 卸载结束后记得删除`C:\ProgramData\MySQL`下的内容, 这样才不会影响到下次的安装
- 彻底删除MySQL服务,否则会影响下次的安装

  - 打开搜索, 输入regedit打开注册表编辑器

  - 找到以下路径中的MySQL文件夹删除掉(有的找不到就算了), 然后重启电脑

    - `\HKEY_LOCAL_MACHINE\SYSTEM`
      - `\ControlSet001\services\eventlog\Application\MySQL`
      - `\ControlSet002\services\eventlog\Application\MySQL`
      - `\CurrentControlSet\Services`
        - `\Eventlog\Applications\MySQL`
        - `\MySQL服务的名字(Windows Service Name)`

### 8. 登录MySQL数据库

MySQL软件在电脑上开辟了一块地盘用来存放和管理数据库, 外部人员可以通过MySQL服务在这块地盘上创建和管理数据库, 但是要进入这块地盘就需要输入账号和密码, 不同的账号在这块地盘上有不同的权限, 比如root账号有着最高的权限, 且可以创建其他的账号并给予对应的权限

#### 1. 命令行登录数据库

| 命令                                   | 说明                                            |
| -------------------------------------- | ----------------------------------------------- |
| mysql -u用户名 -p密码                  | 使用指定用户名和密码登录当前计算机的MySQL数据库 |
| mysql -u用户名 -h服务器的IP地址 -p密码 | -h    远程登陆MYSQL服务端                       |
| exit或者quit                           | 退出数据库                                      |

##### 演示

![](%E7%AC%AC01%E7%AB%A0%20MySQL%E5%9F%BA%E7%A1%80%E5%92%8CSQL%E5%85%A5%E9%97%A8.assets/One_2021-01-26_183850.png)

![](%E7%AC%AC01%E7%AB%A0%20MySQL%E5%9F%BA%E7%A1%80%E5%92%8CSQL%E5%85%A5%E9%97%A8.assets/One_2021-01-26_183719.png)

#### 2. 修改MySQL指定用户的登录密码

```
mysqladmin -u用户名 -p旧密码 password 新密码 
```



#### 3. 客户端登录--SQLYog

- 在创建新连接输入用户名和密码登录MySQL服务器时可能出现问题, 这是因为创建连接时需要MySQL开启一个验证密码的插件,可通过以下步骤开启
  - 在虚拟机上登录MySQL服务器的`root`用户
  - 输入以下代码, 把用户 主机名 密码改成新连接所使用的**用户名** **主机名** 和**密码**
  - ```sql
   #1. 修改加密规则 
  ALTER USER '用户'@'主机名' IDENTIFIED BY '密码' PASSWORD EXPIRE NEVER; 
  #2. 更新一下用户的密码
ALTER USER '用户'@'主机名' IDENTIFIED WITH mysql_native_password BY '密码';
  #3. 刷新权限
  FLUSH PRIVILEGES; 
  #4. 重置密码
  alter user '用户'@'主机名' identified by '密码'; 
   ```
   ```
  
   ```
  
   ```
  
   ```
  
   ```
  
   ```
  
   ```
  
   ```
  
   ```
  
   ```
  
   ```
  
   ```
  
   ```
  
   ```
  
   ```
  
   ```
  
   ```
  
   ```
  
   ```
  
   ```
  
   ```
  
   ```
  
   ```
  
   ```
- <https://github.com/webyog/sqlyog-community/wiki/Downloads>下载最新版SQLYog Community Edition, 但是建议使用旗舰版

- 打开工具--首选项--字体编辑器, 进行如下设置

- ![ ](%E7%AC%AC01%E7%AB%A0%20MySQL%E5%9F%BA%E7%A1%80%E5%92%8CSQL%E5%85%A5%E9%97%A8.assets/One_2021-01-26_190940.png)

### 9. MySQL的目录结构

#### (1). MySQL的安装目录

MySQL的默认安装目录是:`C:\Program Files\MySQL\MySQL Server 5.7`

| 目录    | 目录内容                   |
| ------- | -------------------------- |
| bin     | 放置一些可执行文件         |
| docs    | 文档                       |
| include | 包含(头)文件               |
| lib     | 依赖库                     |
| share   | 用于存放字符集、语言等信息 |

#### (2). MySQL配置文件与数据库文件所在目录

目录`C:\ProgramData\MySQL\MySQL Server 5.7`

- my.ini是配置文件，一般不建议修改
- data目录下面存放的MySQL管理的数据库文件

### 10. MySQL自带的四个数据库

| 数据库             | 作用                                                         |
| ------------------ | ------------------------------------------------------------ |
| information_schema | 信息概要，信息数据库，保存的是其他数据库的相关信息           |
| mysql              | MySQL核心数据库，保存的是所有的用户账户和密码以及对应的权限  |
| performance_schema | 性能概要，保存性能相关的数据，监视MySQL的性能                |
| sys                | 记录了DBA(DataBase Administrator,即数据库管理员)所需要的一些系统信息，更方便地让DBA快速了解数据库的运行情况 |

## 三. SQL(重点)

### 1. 概念

#### (1). 什么是SQL

SQL的全称是Structure Query Language，结构性查询语言， 是一种特殊目的的编程语言，是一种数据库查询语言，也是一种程序设计语言，用于存取数据以及查询、更新和管理关系型数据库系统

#### (2). SQL的作用

- 是所有关系型数据库的统一管理(增查改删)规范，不同的关系型数据库都可以使用SQL语言进行管理
- 所有的关系型数据库都可以使用SQL
- 不同数据库之间的SQL有一些区别(相当于方言之间的区别)

### 2. SQL通用语法

- SQL语言可以单行或者多行书写，以分号`;`结尾
- 可以使用空格和缩进来增加语句的可读性
- MySQL中使用SQL语言不区分大小写，一般是SQL语言关键字大写，数据库名、表名和列名小写且单词间用下划线`_`隔开

#### (1). 注释方式

| 注释语法          | 说明                                                   |
| ----------------- | ------------------------------------------------------ |
| -- 单行注释内容   | 单行注释，注意：`--`与单行注释内容之间一定要有一个空格 |
| #单行注释内容     | 单行注释                                               |
| /\*多行注释内容*/ | 多行注释                                               |

#### (2). SQL的分类

| 分类                                          | 说明                                                         |
| --------------------------------------------- | ------------------------------------------------------------ |
| DDL(Data Definition Language), 数据定义语言   | 用来定义数据库对象: 数据库、表、列，比如创建或删除一个数据库，在数据库内删除或创建一个表，在一个表内定义一个新列或删除一列，比如选择使用某个数据库以及使用该数据库中的某个表 |
| DML(Data Manipulation Language), 数据操作语言 | 用来对数据库的表中的记录进行增删改                           |
| DQL(Data Query Language), 数据查询语言        | 用来查询数据库中表中的记录                                   |
| DCL(Data Control Language), 数据控制语言      | 用来创建或删除用户，并定义该用户的访问权限                   |

==这里重点学习DML和DQL==

### 3. DDL(Data Definition Language)的用法

#### (1). DDL操作数据库的语法

| 命令                                             | 说明                                                     |
| ------------------------------------------------ | -------------------------------------------------------- |
| create database 数据库名;                        | 创建数据库                                               |
| create database 数据库名 character set 字符集名; | 以指定编码创建数据库                                     |
| show databases;                                  | 显示所有数据库                                           |
| show create database 数据库名;                   | 显示数据库的信息, 其实就是显示数据库的编码信息           |
| alter database 数据库名 character set 字符集名;  | 更换数据库的编码                                         |
| drop database 数据库名;                          | 删除该数据库, 该数据库不存在的话会报错error              |
| drop database if exists 数据库名                 | 如果该数据库存在, 那就将其删除, 否则什么也不做, 不会报错 |

示例:

```sql
-- 创建数据库db1、db2
CREATE DATABASE db1;
CREATE DATABASE db2 CHARACTER SET utf8;
SHOW DATABASES;
```

```sql
-- 显示数据库db1、db2的编码
SHOW CREATE DATABASE db1;
SHOW CREATE DATABASE db2;
```

```sql
-- 切换数据库，显示正在使用的数据库的名称
USE db1;
SELECT DATABASE();
USE db2;
SELECT DATABASE();
```

```sql
-- 修改数据库的编码
ALTER DATABASE db1 CHARACTER SET gbk;
ALTER DATABASE db2 CHARACTER SET latin1;
SHOW CREATE DATABASE db1;
SHOW CREATE DATABASE db2;
```

```sql
-- 删除数据库
SHOW DATABASES;
DROP DATABASE db2;
SHOW DATABASES;
```

常用的字符集：默认`latin1`，欧美`ASCII` `ISO-8859-1` , 中文`GBK` `BIG5` , 全球`UTF8`

#### (2). DDL操作数据库中表及列的语法

##### 0. 准备工作

| 命令               | 说明                                                         |
| ------------------ | ------------------------------------------------------------ |
| use 数据库名;      | 切换到该数据库中, 进入到该数据库中 , 这样才能对该数据库中的表进行操作 |
| select database(); | 显示正在使用的数据库的名称                                   |

##### ①. 表中的列常用的数据类型

| 列(字段)类型  | 说明                                                         | 对应的java类型   |
| ------------- | ------------------------------------------------------------ | ---------------- |
| int           | 4个字节的整数                                                | int              |
| boolean       | 本质值tinyint类型, 插入的值可以是false, 也可以是true, 或者tinyint取值范围内的整数值, 0 代表false, 其他数代表true |                  |
| double        | 8个字节的浮点数                                              | double           |
| varchar(长度) | 最多4个字节的字符, 插入值时必须用`''`或者`""`包裹            | java.lang.String |
| date          | 日期类型, 只有年月日, 没有时分秒, 格式: YYYY-MM-DD, 插入值时必须用`''`或者`""`包裹 | Date             |

##### ②. 详细的列(字段)数据类型

###### a. 整数类型

| 列(字段)类型 | 说明                                                         | 对应的java类型                                               |
| ------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| tinyint      | 1个字节的整数, 最高位管正负, 所以取值范围是: ==-2^7^ ~ 2^7^-1== | int                                                          |
| smallint     | 2个字节的整数, 最高位管正负, 所以取值范围是: ==-2^15^ ~ 2^15^-1== | int                                                          |
| mediumint    | 3个字节的整数,最高位管正负, 所以取值范围是: ==-2^23^ ~ 2^23^ - 1== | int                                                          |
| ==int==      | 4个字节的整数, 最高位管正负, 所以取值范围是: ==-2^31^ ~ 2^31^-1== | int                                                          |
| bigint       | 8个字节的整数, 最高位管正负, 所以取值范围是: ==-2^63^ ~ 2^63^-1== | 默认对应java.lang.Long;<br />unsign(无符号)对应java.math.BigInteger |

==注: 整数值不必限定长度, 就算限定了也不会遵守这个限定的, 比如tinyint(1), 就可以输入大于1或小于-1的值==

###### b. 小数类型

| 列(字段)类型 | 说明                                                         | 对应的java类型 |
| ------------ | ------------------------------------------------------------ | -------------- |
| float(m,d)   | 4个字节, 单精度, m--值能取得的最大的位数个数, d--小数部分可取得最大个数, **整数部分的最大的位数个数时m-d** | float          |
| double(m,d)  | 8个字节, 双精度, m--值能取得的最大的位数个数,, d--小数部分可取得最大个数, **整数部分的最大的位数个数时m-d** | double         |
| decimal(m,d) | 用来存储以字符串形式来表示的小数, 比如字符串 -- "3.1415926"就是这种浮点数类型, m--值能取得的最大的位数个数,, d--小数部分可取得最大个数, **整数部分的最大的位数个数时m-d** | double         |

==注: m是插入的小数值能取得的最大的位数个数, d是小数部分的位数个数, 小数部分的位数个数是不变的, 插入的值小数部分的个数不到d的会加0补齐, 超过d的部分会被截掉,所以整数部分能取得的最大位数个数是m-d==

==在MySQL8.0开始就不建议给浮点数限定位数了, 估计再下一个版本就会移除限定长度的功能了==

###### c. 字符串类型

| 列(字段)类型 | 说明                                                         | 对应的java类型   |
| ------------ | ------------------------------------------------------------ | ---------------- |
| char(n)      | n -- 固定长度, 必须设置n, 超过n的会被截掉, 不足的会用空格补齐, <br />n表示的是字符数, n的取值范围是==0 ~ 2^8^-1==(255),<br />存储的数据最多占==2^8^==个字节 | java.lang.String |
| varchar(n)   | n -- 可变长度, 必须设置n, 超过n的部分会被截掉, 不足的不变, <br />n表示的是字符数,n的取值范围是==0 ~ 2^16^-1==(65535) <br />存储的数据最多占==2^16^==个字节 | java.lang.String |
| tinytext     | 可变长度, 不需要设置n, 存储的数据最多占==2^8^==个字节        | java.lang.String |
| text         | 可变长度, 不需要设置n, 存储的数据最多占==2^16^==个字节       | java.lang.String |
| mediumtext   | 可变长度, 不需要设置n, 存储的数据最多占==2^24^==个字节       | java.lang.String |
| longtext     | 可变长度, 不需要设置n, 存储的数据最多占==2^32^==个字节       | java.lang.String |

- 检索速率: char(n) > varchar(n) > text
- 每种字符串类型的数据都会在数据的最后使用对应的字节来保存这个字符串的长度, 所以实际上每种字符串类型中字符串部分的数据所占的空间最大为
  - char(n)    字符串最多占==2^8^ -1==个字节
  - varchar(n)    字符串最多占==2^16^ - 2==个字节
  - tinytext    字符串最多占==2^8^ - 1==个字节
  - text    字符串最多占==2^16^ - 2==个字节
  - mediumtext    字符串最多占==2^24^ - 3==个字节
  - longtext    字符串最多占==2^32^ - 4==个字节

###### d. 日期类型

| 列(字段)类型 | 说明                                                         | 对应的java类型                     |
| ------------ | ------------------------------------------------------------ | ---------------------------------- |
| date         | 3字节, 日期, 即年月日, 格式: yyyy-MM-dd , 输入格式; `'2020.01.01'` | Date, 也可以是字符串               |
| time         | 3字节, 时间, 即时分秒, 格式: HH:mm:ss, 输入格式: `'22:00:00'` | Date, 也可以是字符串               |
| datetime     | 8字节, 日期时间, 即年月日 时分秒, 格式: yyyy-MM-dd HH:mm:ss, 输入格式: `'2020.01.01 22:00:00'` | Date, 也可以是字符串               |
| timestamp    | 4字节, 自动存储记录修改是的时间, 格式: Yyyyy-MM-dd HH:mm:ss, 输入格式跟datetime的一样但最大只能到2038年 | java.sql.Timestamp, 也可以是字符串 |
| year         | 1字节, 年份, 格式:  yyyy 或 yy                               | Date, 也可以是字符串               |

==注: 插入日期类型的值时,必须将这个值用单引号`''`或者双引号`""`包裹起来==

**日期类型也可以比较大小, 数字较大的为大, 数字较小的为小**

##### ③. 创建表

- 语法格式

  - ```sql
    CREATE TABLE 表名(字段名称1 字段类型, 字段名称2 字段类型, 字段名称3 字段类型);
    ```


- 实例

  - ```sql
    -- 创建表之前一定要确认现在正使用某个数据库
    CREATE TABLE student(name varchar(20), age INT, birthday DATE, final_score DOUBLE(3, 2) );
    ```


##### ④. 查看表结构

###### 3.3.2.4.1 简化用法

==不包含字段的注释以及外键==

- 语法格式

  - ```sql
    DESC 表名;
    ```

- 示例

  - ```sql
    DESC student;
    ```


###### 3.3.2.4.2 查询`information_schema.columns`表

MySQL把所有数据库的字段信息都保存到了数据库`information_schema`的`columns`表中了, 该表的一条记录就包含了MySQL数据库中的对应表的所有字段的信息

```sql
-- 查询字段的的所有信息
SELECT * FROM information_schema.columns WHERE table_schema = 'db1' AND table_name = 'student';
-- 查询字段的部分信息
SELECT column_name, column_type, is_nullable, column_key, column_default, extra, column_comment FROM information_schema.columns WHERE table_schema = 'db1' AND table_name = 'student';
```



##### ⑤. 复制表(只会复制表结构)

###### a. 创建新表时复制另一个表的表结构

- 语法格式

  - ```sql
    -- 复制同一个数据库内的表结构
    CREATE TABLE 新表名 LIKE 旧表名;
    ```


- 示例

  - ```sql
    CREATE TABLE college_student LIKE student;
    ```

###### b. 在一个数据库中创建新表时复制另一个数据库中的表的表结构

- 语法格式

  - ```sql
    CREATE TABLE 目标数据库.目标表 LIKE 源数据库.源表;
    ```

- 示例

  - ```sql
    -- 可以同名
    CREATE TABLE db2.student LIKE db1.student;
    
    -- 也可以不同名
    CREATE TABLE db2.new_student LIKE db1.student;
    ```



##### ⑥. 删除表

- 语法结构

  - ```sql
    DROP TABLE 表名; -- 从正在使用的数据库中永久删除该表
    DROP TABLE IF EXISTS 表名; -- 如果正在使用的数据库中存在该表, 那么就把该表永久删除,否则什么也不做
    ```

- 示例

  - ```sql
    DROP TABLE college_student;
    ```

  - ```sql
    DROP TABLE IF EXISTS college_student;
    ```

##### ⑦. 修改表

除修改表名外, 都会用到`ALTER TABLE 表名`

###### a. 修改表名

- 语法格式

  - ```sql
    RENAME TABLE 旧表名 TO 新表名;
    ```

- 示例

  - ```sql
    RENAME TABLE student TO college_student;
    ```

###### b. 修改表的编码(字符集)

关键字`CHARACTER SET`

- 语法格式

  - ```sql
    -- 修改表的编码(字符集)
    ALTER TABLE 表名 CHARACTER SET 字符集;
    -- 显示表现在所用的编码(字符集)
    SHOW CREATE TABLE 表名;
    ```

- 示例

  - ```sql
    ALTER TABLE student CHARACTER SET gbk;
    SHOW CREATE TABLE student;
    ```

###### c. 向表中添加新列

关键字`ADD`

- 语法格式

  - ```sql
    ALTER TABLE 表名 ADD 列名 列类型;
    ```

- 示例

  - ```sql
    ALTER TABLE student ADD gender CHAR(1);
    ```

###### d. 修改列类型

关键字`MODIFY`

- 语法格式

  - ```sql
    ALTER TABLE 表名 MODIFY 列名 新类型;
    ```

- 示例

  - ```SQL
    ALTER TABLE student MODIFY sname VARCHAR(100);
    ```

###### e. 修改列名称及类型

关键字`CHANGE`

- 语法格式

  - ```sql
    ALTER TABLE 表名 CHANGE 旧列名 新列名 新列类型;
    ```

- 示例

  - ```sql
    ALTER TABLE student CHANGE sname student_name VARCHAR(50);
    ```

###### f. 删除列

关键字`DROP`

- 语法格式

  - ```sql
    ALTER TABLE 表名 DROP 列名;
    ```

- 示例

  - ```sql
    ALTER TABLE student DROP final_score;
    ```

### 4. DML(Data manipulation language)的用法

**DML是用来对表中的记录进行==增==  ==删==  ==改==的**


#### (0). 数据准备

```sql
/*
表名: student
表中字段:
		学员ID, uid          int
		姓名,   sname        varchar(20)
		性别,   gender       char(1)
		年龄,   age          int
		生日,   birthday     date
		期末成绩,score_final  double(5, 2)
		期末及格,pass_final   boolean
*/
CREATE TABLE student(uid INT, sname VARCHAR(20), gender CHAR(1), age INT, birthday DATE, score_final DOUBLE(5,2), pass_final BOOLEAN);
DESC student;
```

#### (1). 插入数据

- `INSERT INTO 表名(...) VALUES(...)`
- **字符串类型的值**==必须用==单引号`''`或者双引号`""`包裹
- **日期类型的值**==必须用==单引号`''`或者双引号`""`包裹
- **整数和浮点数[除decimal(m, d)之外]类型的值**==不能用==单引号`''`或者双引号`""`包裹
  - **decimal(m, d)类型的值**==必须用==单引号`''`或者双引号`""`包裹
  - d是浮点数小数部分的个数, m是整个浮点数能取得的值得最大位数, 所以浮点数能取得的最多的位数是`m-d`
- **布尔类型的值**只有两个true和false, ==不能用==单引号`''`或者双引号`""`包裹
- 布尔类型的值也可以输入tinyint类型取值范围内整数值, 0代表false, 其他的值代表true
- **如果要插入空值, 可以写null或者NULL, ==不能用==单引号或者双引号包裹**
- ==在SQL语言中建议尽量使用`''`, 这样的话跟java语言配合使用时会减少很多工作量==

##### a. 插入全部字段

**插入的值必须与表中的字段相对应**

```sql
INSERT INTO student VALUES(1, '路飞', '男', 19, '2001.04.09', 550.01, TRUE);
```

##### b. 插入指定字段

**指定的字段值必须跟指定的字段名相对应**

1. 可以指定全部

   1. ```sql
      INSERT INTO 表名(字段1名, 字段2名, 字段3名, ... , 字段n名) VALUES(字段1值, 字段2值, 字段3值, ... , 字段n值);
      ```

   2. ```sql
      INSERT INTO student(uid, sname, gender, age, birthday, score_final, pass_final) VALUES(3, '路飞', '男', 20, '2020.03.23', 510.00, TRUE);
      ```
   
2. 可以指定部分, 没指定的部分默认值为空值, 即null

   1. ```sql
      INSERT INTO 表名(字段2名, 字段3名, 字段5名) VALUES(字段2值, 字段3值, 字段5值);
      ```

   2. ```sql
      INSERT INTO student(uid, sname, birthday, pass_final) VALUES(3, '香吉士', '2002.11.11', TRUE);
      ```

3. 指定部分的顺序可以跟表中的不同

   1. ```sql
      INSERT INTO 表名(字段5名, 字段1名, 字段7名) VALUES(字段5值, 字段1值, 字段7值);
      ```

   2. ```sql
      INSERT INTO student(gender, uid, birthday, age) VALUES('男', 4, '1998.05.06',22);
      ```

#### (2). 更改数据

`UPDATE 表名 SET ...`

- 不带条件修改, 会对所有已插入的  记录的  对应列的  值  进行修改

  - ```sql
    UPDATE 表名 set 列名1 = 值1, 列名2 = 值2, ... , 列名n = 值n;
    ```

- 带条件的修改, 会对所有符合条件的  记录的  对应列的  值  进行修改, 注意多个条件表达式必须用SQL语言中的逻辑运算符连接起来`AND`    `OR`    `NOT`

  - ```sql
    UPDATE 表名 set 列名1 = 值1, 列名2 = 值2, 列名3 = 值3, ... , 列名n = 值n WHERE 条件表达式1 逻辑运算符 条件表达式2 逻辑运算符 条件表达式3 ...;
    ```

  - ```sql
    UPDATE student SET age = 18, gender = '男', score_final = 650.95 WHERE uid = 3 AND sname = '香吉士';
    ```

  - ```sql
    UPDATE student SET score_final = 690.00 WHERE sname = '娜美';
    ```

#### (3). 删除数据

`delete from 表名 where 条件表达式;`

##### a. 删除所有数据

###### ①. 不写条件表达式就行了

会将表中的记录一条一条地删除, 效率低, 不推荐使用, 建议使用下一种方法

`delete from 表名`

```sql
DELETE FROM student;
```

###### ②. truncate table 表名

会先删除整张表, 然后重新创建一张结构相同但没有记录的表, 效率高, 建议使用该方法

`truncate table 表名`

```sql
TRUNCATE TABLE student
```

##### b. 删除符合指定条件的记录

**需要使用条件表达式, 参考[(2). 条件查询](# (2). 条件查询)**

```sql
DELETE FROM student where pass_final = false;
```

### 5. DQL(Data query language)的用法

- 关键字`select 列名1,列名2, ... , 列名n from 表名 where 条件表达式`
- 注意select查询语句包括了从数据库中查询、得到查询结果、在查询结果中挑出显示结果、将显示结果显示出来，所以说select语句的显示结果只是查询结果的一部分
  - 这里面查询结果是由一条一条的记录构成的
  - 而显示结果是由上述记录的部分列构成的

#### (0). 准备数据

```sql
# 创建学生表
/*
学生id          uid : int
学生姓名       sname : varchar(20)
性别	       gender : char(1)
年龄            age : int
生日       birthday : date
期末成绩 score_final : double(5,2)
期末及格  pass_final : boolean
*/
-- 创建学生表
CREATE TABLE student(uid INT, sname VARCHAR(20), gender CHAR(1), age INT, birthday DATE, score_final DOUBLE(5,2), pass_final BOOLEAN);
DESC student;

-- 插入记录record
INSERT INTO student VALUES(1,'路飞','男', 20, '2000.11.12',469.25, FALSE);
INSERT INTO student VALUES(2,'索隆','男',19,'2001.02.20',492.10, FALSE);
INSERT INTO student VALUES(3,'娜美','女',18,'2002.06.15',670.00,TRUE);
INSERT INTO student VALUES(4,'乌索普','男',20,'2000.06.19',690.05,TRUE);
INSERT INTO student VALUES(5,'香吉士','男',18,'2002.05.25',650.75,TRUE);
INSERT INTO student VALUES(6, '乔巴','男',15,'2005.07.21',600.05,TRUE);
INSERT INTO student VALUES(7,'罗宾','女', 30,'1990.05.29',690.95,TRUE);
INSERT INTO student VALUES(8,'布鲁克','男',99,'1921.03.03',590.85, NULL);
INSERT INTO student VALUES(9,'弗兰奇','男',41,'1979.05.05',680.55, null);
INSERT INTO student VALUES(10,'甚平','男',45,'1975.08.28',650.50,TRUE);
INSERT INTO student VALUES(11,'薇薇','女', 22, '1998.08.14',660.00, TRUE);
INSERT INTO student VALUES(12,'汉库克','女',33,'1987.08.25',630.35, TRUE);
INSERT INTO student VALUES(13,'瑞贝卡','女',17,'2003.12.12',590.00, NULL);
INSERT INTO student VALUES(14,'白星','女',16,'2004.07.22',550.05,NULL);
INSERT INTO student VALUES(15,'吉米','女',18,'2002.05.25', 570.05,TRUE);
INSERT INTO student VALUES(16,'布琳','女',20,'2000.09.21',490.55,TRUE);
```

#### (1). 查询结果的显示

##### ①. 显示所有字段

```sql
-- 查询到所有记录
-- 显示这些记录的所有字段
SELECT * FROM student;
```

##### ②.仅显示指定的字段

```sql
-- 查询到所有的记录
-- 仅显示sname age和gender字段
SELECT sname , age , gender FROM student;
```

##### ③. 别名显示

**查询student中的所有记录, 以别名显示查询结果**

```sql
-- 查询到所有的记录
-- 这些记录的列名以别名显示
SELECT sname AS '姓名', age AS '年龄', gender AS '性别' FROM student;
```

##### ④. 去重显示

- 去重关键字`distinct`
- 去重查询只能查一列, 不能有多列, 否则会导致语法错误或者去重失败

```sql
-- 查询到所有的记录
-- 仅显示一列的值, 且这些值里没有重复相同的值
SELECT DISTINCT gender FROM student;
```

##### ⑤. 运算显示

数学运算符`+`    `-`    `*`     `/`    `%`

查询结果参与运算,并且最终显示的是运算后结果

```sql
-- 查询到所有的记录
-- 指定的列显示的是进行指定运算后的值
SELECT sname, score_final / 6 FROM student;
```

#### (2). 条件查询

##### ①. 比较运算符

| 比较运算符                      | 说明                                                         |
| ------------------------------- | ------------------------------------------------------------ |
| `=`  `!=`  `>`  `<`  `>=`  `<=` | 等于  不等于  大于  小于  大于等于  小于等于                 |
| between 值1 and 值2             | 值在值1和值2的范围内, 包括值1和值2                           |
| in(值1, 值2, 值3, ... , 值n )   | 值是包含在(值1, 值2, 值3, ... , 值n )之中                    |
| is null                         | ==查询某一列中值为null的记录, 注意不能写 `= null`==, 必须是`is null` |
| is not null                     | ==查询某一列中值不为null的记录, 注意不能写 `!= null`==, 必须是`is not null` |
| like '%张%'                     | 模糊查询, 针对字符串                                         |
| not like "%张%"                 |                                                              |

##### ②. 逻辑运算符

**逻辑运算符可以将两个条件表达式连接起来, 连接起来后的表达式可以视作一个条件表达式**

| 逻辑运算符    | 说明                         |
| ------------- | ---------------------------- |
| and    &&     | 两个都等同于Java语言中的&&   |
| or       \|\| | 两个都等同于Java语言中的\|\| |
| not           | 等同于Java语言中的`!`, 即非  |

##### ③. 示例

```sql
-- 比较运算符的使用
SELECT * FROM student WHERE score_final > 500;
SELECT * FROM student WHERE score_final != 500;
SELECT * FROM student WHERE birthday < '2000.01.01';
SELECT * FROM student WHERE gender = '女';
-- between ... and ...  的使用
SELECT * FROM student WHERE score_final BETWEEN 500 AND 600;
-- in(值1, 值2, 值3, ... , 值n)的使用
SELECT * FROM student WHERE age IN(18, 22, 45);\
-- is null的使用
SELECT * FROM student WHERE pass_final IS NULL;
-- 逻辑运算符的使用
SELECT * FROM student WHERE gender = '女' && score_final BETWEEN 600 AND 700;
SELECT * FROM student WHERE gender = '女' && score_final BETWEEN 600 AND 700 && age > 30;
SELECT * FROM student WHERE NOT pass_final IS NULL;
```

##### ④. 模糊查询

###### a. 通配符

| 通配符      | 说明                 |
| ----------- | -------------------- |
| `_`  下划线 | 表示匹配任意一个字符 |
| %           | 表示匹配任意多个字符 |

###### b. 示例

```sql
-- 查询名字是两个字的学生信息
SELECT * FROM student WHERE sname LIKE '__';##两个下划线
-- 查询名字含'克'的学生信息
SELECT * FROM student WHERE sname LIKE '%克%';
```

