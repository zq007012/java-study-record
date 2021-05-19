# 第02章 DQL单表高级查询、约束和事务

[toc]

## 零. 数据准备

### 0.1 建表

```sql
CREATE TABLE student(
	uid INT PRIMARY KEY AUTO_INCREMENT, 
	sname VARCHAR(20), 
	gender CHAR(1), 
	age INT, 
	birthday DATE, 
	score_final DOUBLE(5,2), 
	pass_final BOOLEAN, 
	team VARCHAR(10)
);
```

### 0.2 插入记录

```sql
INSERT INTO student VALUES( NULL, '路飞', '男',  20,  '2000.11.12', 469.25,  NULL, '草帽');
INSERT INTO student VALUES( NULL, '索隆', '男', 19, '2001.02.20', 492.10,  NULL, '草帽');
INSERT INTO student VALUES( NULL, '娜美', '女', 18, '2002.06.15', 670.00, NULL, '草帽');
INSERT INTO student VALUES( NULL, '乌索普', '男', 20, '2000.06.19', 490.05, NULL, '草帽');
INSERT INTO student VALUES( NULL, '香吉士', '男', 18, '2002.05.25', 630.35, NULL, '草帽');
INSERT INTO student VALUES( NULL,  '乔巴', '男', 15, '2005.07.21', 500.05, NULL, '草帽');
INSERT INTO student VALUES( NULL, '罗宾', '女',  30, '1990.05.29', 690.95, NULL, '草帽');
INSERT INTO student VALUES( NULL, '布鲁克', '男', 99, '1921.03.03', 490.85, NULL, '草帽');
INSERT INTO student VALUES( NULL, '弗兰奇', '男', 41, '1979.05.05', 680.55, NULL, '草帽');
INSERT INTO student VALUES( NULL, '甚平', '男', 45, '1975.08.28', 630.35, NULL, '太阳');
INSERT INTO student VALUES( NULL, '薇薇', '女',  22,  '1998.08.14', 560.00,  NULL, '公主');
INSERT INTO student VALUES( NULL, '汉库克', '女', 33, '1987.08.25', 630.35,  NULL, '九蛇');
INSERT INTO student VALUES( NULL, '瑞贝卡', '女', 17, '2003.12.12', 590.00, NULL, '公主');
INSERT INTO student VALUES( NULL, '白星', '女', 16, '2004.07.22', 550.05, NULL, '公主');
INSERT INTO student VALUES( NULL, '吉米', '女', 18, '2002.05.25',  570.05, NULL, '人鱼');
INSERT INTO student VALUES( NULL, '布琳', '女', 20, '2000.09.21', 490.55, NULL, '公主');

SELECT * FROM student;

UPDATE student SET pass_final = TRUE WHERE score_final >= 500;
UPDATE student SET pass_final = FALSE WHERE score_final <500;

SELECT * FROM student;
```



## 一. DQL单表查询结果的高级显示

### 1.1 排序显示

> 其实就是将需要的记录从数据库里查找出来后, 再对这些找出来的记录依据某些列的升序或者降序进行显示

#### 1.1.1 单列排序

> 对记录进行排序, 排序的依据是记录中某一列的值的大小

##### 1.1.1.1 语法格式

```sql
-- 默认升序
select * from 表名 where 条件表达式 order by 列名x;

-- 升序, asc是ascending的缩写
select * from 表名 where 条件表达式 order by 列名x asc;

-- 降序, desc是descending的缩写
select * from 表名 where 条件表达式 order by 列名x desc;
```

或者不进行条件查询

```sql
-- 默认升序
select * from 表名 order by 列名x;

-- 升序, asc是ascending的缩写
select * from 表名 order by 列名x asc;

-- 降序, desc是descending的缩写
select * from 表名 order by 列名x desc;

-- 根据汉字拼音来排序
select * from 表名 order by convert(列名x using GBK) asc;
```

##### 1.1.1.2 示例

```sql
-- 不排序
SELECT uid, sname, score_final FROM student WHERE score_final > 600;
-- 默认升序
SELECT uid, sname, score_final FROM student WHERE score_final > 600 ORDER BY score_final;
-- 指定升序
SELECT uid, sname, score_final FROM student WHERE score_final > 600 ORDER BY score_final ASC;
-- 指定降序
SELECT uid, sname, score_final FROM student WHERE score_final > 600 ORDER BY score_final DESC;
```

或者不进行条件查询

```sql
-- 不排序
SELECT uid, sname, score_final FROM student;
-- 默认升序
SELECT uid, sname, score_final FROM student ORDER BY score_final;
-- 指定升序
SELECT uid, sname, score_final FROM student ORDER BY score_final ASC;
-- 指定降序
SELECT uid, sname, score_final FROM student ORDER BY score_final DESC;
```

#### 1.1.2 组合排序

`ORDER BY 列名X ASC/DESC, 列名y ASC/DESC, 列名z ASC/DESC  `

##### 组合排序的逻辑

![ ](%E7%AC%AC02%E7%AB%A0%20MySQL%E5%8D%95%E8%A1%A8%E3%80%81%E7%BA%A6%E6%9D%9F%E5%92%8C%E4%BA%8B%E5%8A%A1.assets/%E7%BB%84%E5%90%88%E6%8E%92%E5%BA%8F%E7%9A%84%E9%80%BB%E8%BE%91.png)

```sql
-- 不排序
SELECT * FROM student;
-- 单列排序
SELECT * FROM student ORDER BY score_final DESC;
-- 组合排序
SELECT * FROM student ORDER BY team ASC, score_final DESC, sname;
```

### 1.2 聚合显示

> 聚合显示  显示的是对所有查询出来的记录  的该列  进行对应统计后信息, 所以聚合显示只会显示一行对应统计后的信息, 

==聚合函数只有把记录查询出来后才能使用, 所以where条件语句不能使用组合函数==

| 聚合函数    | 说明                                   |
| ----------- | -------------------------------------- |
| count(列名) | 统计该列所有的值的个数(会忽略空值null) |
| sum(列名)   | 求和, 该列所有的值的和                 |
| max(列名)   | 获取最大值, 该列所有的值中的最大值     |
| min(列名)   | 获取最小值, 该列所有的值中的最小值     |
| avg(列名)   | 获取平均值, 该列所有的值的平均数       |

#### 1.2.1 语法

```sql
-- 可以按需要增减聚合函数的个数以及改变聚合函数与列名的搭配
select 聚合函数a(列名x), 聚合函数b(列名y), 聚合函数c(列名z) from 表名;

-- 聚合函数也可以以别名显示
select 聚合函数a(列名x) as '别名1', 聚合函数b(列名y) as '别名2', 聚合函数c(列名z) as '别名3' from 表名;
```

#### 1.2.2 示例

```sql
-- 聚合函数的使用
SELECT 
	COUNT(sname), 
	SUM(score_final), 
	MAX(score_final), 
	MIN(score_final), 
	AVG(score_final) 
FROM student;
-- 聚合函数和别名显示的配合使用
SELECT 
	COUNT(sname) AS '人数', 
	SUM(score_final) AS '总分数', 
	MAX(score_final) AS '最高分', 
	MIN(score_final) AS '最低分', 
	AVG(score_final) AS '平均分数' 
FROM student;
```

### 1.3 分组显示

> 将查询出来的记录按照某一列的值进行分组, 该列的值相同的记录为一组, 分组后最小的处理单位是组, 

`group by 列名 having 聚合函数相关的条件表达式`

##### 1.3.1 分组显示的使用条件

> 分组显示至少要配合一个聚合显示才有意义

```sql
SELECT team, score_final FROM student GROUP BY team;
-- 毫无意义, 只会显示每一组的第一条记录

SELECT team,MAX(score_final) FROM student GROUP BY team;
-- 显示每组的最高分数

SELECT team,MAX(score_final),MIN(score_final), AVG(score_final) FROM student GROUP BY team;
-- 显示每组的最高分数,最低分数, 平均分数
```

##### 1.3.2 分组后筛选再显示

###### `having 条件表达式`

- `having 条件表达式`是分组后的筛选条件
- `having 条件表达式`中的条件表达式必须是聚合函数相关的才有意义, 因为只有聚合函数才能处理**组级别**的数据

```sql
SELECT team,MAX(score_final) FROM student GROUP BY team;
-- 显示每组的相关信息;

SELECT team,MAX(score_final) FROM student GROUP BY team HAVING MAX(score_final) > 600;
-- 筛选出最高分数大于600的组, 然后显示这些组的相关信息;

SELECT team,MAX(score_final) FROM student GROUP BY team HAVING MAX(score_final) > 500 && MAX(score_final) < 600;
-- 筛选出最高分数大于500小于600的组, 然后显示这些组的相关信息;

SELECT 
	team,MAX(score_final) 
		FROM student 
			GROUP BY team 
				HAVING MAX(score_final) > 500 && MAX(score_final) < 600 
			ORDER BY MAX(score_final);
-- 筛选出最高分数大于500小于600的组, 对这些组按照最高分数排序后,显示这些组的相关信息;
```

### 1.4 限定范围查询

`limit offset, length`  

- 数据库表中记录的行数是从0开始数的
- offset  开始查询的行数, 最小值为0
- length 记录的条数
- 从offset行开始, length条记录的范围内查询

> 只在表中限定范围内的记录里进行查询

```sql
select * from 表名 limit offset, length where 条件表达式;
```

```sql
-- 不限定范围查询
SELECT * FROM student;
-- 限定范围查询
SELECT * FROM student LIMIT 2,5;
```

## 二. 约束(Constraint)

### 2.1 约束的作用

- 对表中的数据**在数据类型之上**==进行==**进一步的限制**, 以保证插入的数据的正确性、有效性、完整性。
- 约束是列(字段)的一种属性

### 2.2 常见的约束

| 约束名     | 关键字                                           |
| ---------- | ------------------------------------------------ |
| 主键约束   | primary key                                      |
| 自增约束   | auto_increment, 可通过修改类型的方式来添加或删除 |
| 唯一约束   | unique                                           |
| 非空约束   | not null, 可通过修改类型的方式来添加或删除       |
| 外键约束   | foreign key                                      |
| 默认值约束 | default, 可通过修改类型的方式来添加或删除        |

### 2.3 主键约束

- 设置了主键约束的列(字段)被称作**主键**
- 主键的特点：不可重复(一个表中只能有一列主键)、值必须唯一、值必须非空、可设置自增约束
- 主键约束被删除的列还保留着非空约束
- 主键本身也是个索引
- 用来代表数据库中的该条记录
- 关键字`primary key`
- 语法格式`列名 列类型 primary key`

#### 2.4.1 主键的设置方法

##### 2.4.1.1 创建表时就设置好主键

```sql
-- 第一种 直接在字段类型后面设置
CREATE TABLE emp1(
    eid INT PRIMARY KEY, 
    ename VARCHAR(10), 
    gender CHAR(1)
);
DESC emp1;

-- 第二种 写好所有字段后, 在声明哪一列(字段)是主键, 这也是声明约束的通用方式, 建议使用这种方式
CREATE TABLE emp2(
    eid INT, 
    ename VARCHAR(10), 
    gender CHAR(1),
    PRIMARY KEY(eid)
);
DESC emp2;
```

##### 2.1.1.2 表创建好后设置主键

```sql
CREATE TABLE emp3(
    eid INT, 
    ename VARCHAR(10), 
    gender CHAR(1)
);

ALTER TABLE emp3 ADD PRIMARY KEY(eid);
DESC emp3;
```



#### 2.1.2 删除主键约束

__主键约束不能通过修改类型的方式改变__

```sql
-- 只会删除列的主键约束, 不会删除数据, 而且列还会保留非空约束
DESC emp6;
ALTER TABLE emp6 DROP PRIMARY KEYT;
DESC emp6;

-- 如果一个主键设置了自增约束,那么删除主键约束前必须先删除自增约束
ALTER TABLE emp6 ADD PRIMARY KEY(eid);
ALTER TABLE emp6 eid INT AUTO_INCREMENT;
ALTER TABLE emp6 MODIFY eid INT;
ALTER TABLE emp6 DROP PRIMARY KEY;
```

#### 2.1.3 哪些字段可以作为主键

- 通常针对业务区设计主键, 每张表都涉及一个主键id
- 主键是用来代表本行记录的, 是给数据库和程序使用的, 跟最终的客户无关, 所以主键跟现实没有关系, 不需要跟现实意义绑定, 只要能够保证主键的值不重复就好了.

### 2.3 自增约束

- 关键字`auto_increment`
- 只有既是整数类型又绑定了索引的列才可以设置自增约束
- 删除索引前必须先删除自增约束
- 数据库中的记录是按照主键大小升序排列的
- 如果插入的数据的本列值为null, 那么数据库中保存的本行本列的值就是上一行的值+1
- 可以设置自增的起始值, 插入的值可以小于这个起始值
- 默认的自增起始值是1
- 自增约束可以通过修改列类型的方式添加或删除

#### 2.3.1 默认的自增起始值

```sql
-- 使用默认的自增起始值: 1
CREATE TABLE emp5(
    -- 设置了两个约束, 一个主键约束, 一个自增约束
    eid INT PRIMARY KEY AUTO_INCREMENT, 
    ename VARCHAR(10), 
    gender CHAR(1)
);

SELECT * FROM emp5;
INSERT INTO emp5 VALUES(NULL,'路飞','男');
INSERT INTO emp5 VALUES(NULL,'索隆','男');
INSERT INTO emp5 VALUES(NULL,'娜美', '女');
INSERT INTO emp5 VALUES(10,'罗宾','女');
INSERT INTO emp5 VALUES(NULL,'乔巴', '男');
SELECT * FROM emp5;
```

#### 2.3.2 设置自增起始值

```sql
-- 设置自增起始值, 插入的值可以小于这个起始值
CREATE TABLE emp6(
    -- 设置了两个约束, 一个主键约束, 一个自增约束
    eid INT PRIMARY KEY AUTO_INCREMENT, 
    ename VARCHAR(10), 
    gender CHAR(1)
) AUTO_INCREMENT = 100;

SELECT * FROM emp6;
INSERT INTO emp6 VALUES(NULL,'路飞','男');
INSERT INTO emp6 VALUES(NULL,'索隆','男');
INSERT INTO emp6 VALUES(NULL,'娜美', '女');
-- 插入的值可以小于这个起始值
INSERT INTO emp6 VALUES(10,'罗宾','女');

-- 数据库中的记录是按照主键大小升序排列的
-- 如果插入的数据的本列值为null, 那么数据库中保存的本行本列的值就是上一行的值+1
INSERT INTO emp6 VALUES(NULL,'乔巴', '男');
SELECT * FROM emp6;
```

#### 2.3.3 删除主键的自增约束

```sql
-- 其实就是把主键的类型改成没有自增约束就行了
CREATE TABLE emp(
    -- 设置了两个约束, 一个主键约束, 一个自增约束
    eid INT PRIMARY KEY AUTO_INCREMENT, 
    ename VARCHAR(10), 
    gender CHAR(1)
);
-- 删除主键的自增约束
ALTER TABLE emp MODIFY eid INT;
```



#### 2.3.4 删除全部记录的不同方法对自增的影响

```sql
-- 重新开始自增, 因为该种方法是删除整张表后,在重新建一张新表
SELECT * FROM emp5;
TRUNCATE TABLE emp5;
INSERT INTO emp5 VALUES(null,'乔巴','男');
SELECT * FROM emp5;
```

```sql
-- 从删除的最大主键开始自增
SELECT * FROM emp5;
DELETE FROM emp5;
INSERT INTO emp5 VALUES(NULL,'布鲁克','男');
SELECT * FROM emp5;
```

### 2.4 唯一约束

- 关键字`unique`
- 设置了唯一约束的列里不能有重复的值, 但是可以有多个空值
- 唯一约束自带索引, 删除掉这个索引就能删除唯一约束
- 一个表中可以设置多个唯一约束
- 设置了唯一约束的列  的值  可以是空值null

```sql
CREATE TABLE emp(
    -- 设置了两个约束, 一个主键约束, 一个自增约束
	eid INT PRIMARY KEY AUTO_INCREMENT, 
    -- 唯一约束
	sname VARCHAR(10) UNIQUE, 
	gender CHAR(1)
);
DESC emp;
```

或者

`ALTER TABLE emp ADD UNIQUE(列名)`

```sql
CREATE TABLE emp(
	eid INT PRIMARY KEY AUTO_INCREMENT, 
    -- 唯一约束
	sname VARCHAR(10), 
	gender CHAR(1)
);

SHOW CREATE TABLE emp;

-- 给sname添加唯一约束
ALTER TABLE emp ADD UNIQUE(sname);
SHOW CREATE TABLE emp;
```

**删除唯一约束**

`ALTER TABLE 表名 DROP INDEX 列名;`

```sql
-- 只能通过删除索引来删除唯一约束
ALTER TABLE emp DROP INDEX ename;
```



### 2.5 非空约束

- 关键字`not null`
- 设置了非空约束的列不能插入空值
- 设置了非空约束的列  的值不能修改为null

```sql
CREATE TABLE emp(
    -- 设置了两个约束, 一个主键约束, 一个自增约束
	eid INT PRIMARY KEY AUTO_INCREMENT, 
    -- 唯一约束
	sname VARCHAR(10) UNIQUE, 
    -- 非空约束
	gender CHAR(1) NOT NULL
);
DESC emp;
```

#### 给列设置多个约束

```sql
CREATE TABLE emp(
    -- 设置了两个约束, 一个主键约束, 一个自增约束
	eid INT PRIMARY KEY AUTO_INCREMENT, 
    -- 设置了两个约束, 一个非空约束, 一个唯一约束
	sname VARCHAR(10) NOT NULL UNIQUE, 
    -- 设置了两个约束, 一个唯一约束, 一个非空约束
	gender CHAR(1) UNIQUE NOT NULL
);
DESC emp;
```

### 2.6 默认值约束

- 关键字`default 默认值`
- 用来指定某列的默认值, 如果某条记录的该列插入的值为空值, 那么数据库中保存的该条记录的  该列的值  就是默认值
- 所以默认值约束不能跟唯一约束一起使用
- 设置了默认值约束 的列 的值 可以修改为null

```sql
CREATE TABLE emp(
	eid INT PRIMARY KEY AUTO_INCREMENT, 
	sname VARCHAR(10) NOT NULL UNIQUE, 
	gender CHAR(1) NOT NULL;
	team CHAR(10) DEFAULT '群众';
);
DESC emp;
```

### 2.7 外键约束

- 关键字`foreign key`
- 将在下一章夺标中学习

## 三. 事务

> 事务 是一个整体, 由一条或者多条SQL语句组成的整体, 只有事务中所有语句执行成功, 事务才能执行成功, 如果事务中有一条SQL语句执行失败, 那么事务就会整体执行失败并且数据库会回滚到事务开始之前的状态.

### 3.1 事物的四大特性(ACID)

| 特性                | 含义                                                         |
| ------------------- | ------------------------------------------------------------ |
| 原子性  atomicity   | 每个事务都是一个整体, 造成的结果只能整体成功或者整体失败, 没有部分失败选项、部分成功的选项。 |
| 一致性  consistency | 数据库中的数据是为了描述现实中存在的东西的状态, <br />而事务描述的是现实中对这些东西的对应操作, <br />经过事务处理后的数据库中的数据  必须  与现实中进行对应操作后的东西的状态  保持一致 |
| 隔离性  isolation   | 事务与事务间不应该相互影响,执行时腰包里隔离状态, 可以通过设置隔离等级来实现 |
| 持久性  durability  | 一旦事务执行成功, 对数据库的修改是持久的, 就算关机, 数据也是会保存下来的 |

### 3.2 事务的开始、提交和回滚

#### 3.2.1 事务的执行逻辑

![ ](%E7%AC%AC02%E7%AB%A0%20MySQL%E5%8D%95%E8%A1%A8%E3%80%81%E7%BA%A6%E6%9D%9F%E5%92%8C%E4%BA%8B%E5%8A%A1.assets/%E5%BC%80%E5%A7%8B%E4%BA%8B%E5%8A%A1.png)

#### 3.2.2 事物的开始、提交、回滚

- 事物的开始    `start transaction;` 或者`begin transacton;`
- 事务的提交    `commit;`
- 事物的回滚    `rollback`

> 事务语句的编写需要跟类似Java语言中的try{...}catch语句配合使用; `start transation;`  `多条SQL语句;`  `commit;` 要写在try语句中, `rollback;`要写在catch语句中

#### 3.2.3 单条SQL语句的事务属性

- 如果单条SQL语句没有放入事务的语句中, 那么这条SQL语句就是一个事务
- MySQL对单句SQL事务进行了自动的事务提交处理, 自动开启事务, 执行这条SQL语句, 执行成功后自动提交, 执行失败后自动回滚

##### 3.2.3.1 查看MySQL服务器自动提交单句事务的功能状态

```sql
SHOW VARIABLES LIKE 'autocommit';
```

##### 3.2.3.2 开启自动提交单句事务的功能

```sql
SET @@autocommit = on;
```

##### 3.2.3.3 关闭自动提交单句事务的功能

```sql
SET @@autocommit = off;
```



### 3.3 事务的隔离级别

> 一个数据库服务器里的 所有数据库的 隔离级别都是相同的

#### 3.3.1 并发访问会导致的事务隔离问题

> 并发访问也就是至少有两个事务在同时进行

##### 3.3.1.1 脏读

事务a读取到了事务b未提交的数据

##### 3.4.1.2 不可重复读

事务a多次查询到的同一记录的数据结果是不同的, 因为同一时间其它的事务在对该数据进行多次修改并执行成功且提交了.

##### 3.4.1.3 幻读

事务a查询到了一条记录, 但在对这条记录进行下一步操作时发现这条记录不见了, 因为同一时间其他的事物把这条记录删除了, 且这个事务执行成功并提交了. 就好像事务a产生了这条记录存在的幻觉

事务a在插入一条不存在的记录时因为该条记录已存在而导致执行失败了, 因为同一时间其他事务插入了该条记录且这个事务在事务a执行之前执行成功并提交了. 就好像事务a产生了这条记录不存在的幻觉.

#### 3.4.2 隔离级别的分类

> 以下隔离级别越来越高, 但执行效率越来越低

| 隔离级别         | 可解决的并发访问导致的事务隔离问题              |
| ---------------- | ----------------------------------------------- |
| read uncommitted | 解决不了任何并发访问导致的事务隔离问题          |
| read commited    | 可以解决脏读(Oracle数据库默认的级别)            |
| repeatable read  | 可以解决脏读、不可重复读(MySQL数据库默认的级别) |
| serializable     | 可以解决脏读、不可重复读、幻读                  |

#### 3.4.3 查看和设置数据库服务器隔离级别

##### 3.4.3.1 查看数据库服务器的隔离级别

###### MySQL8.0之前的查看方法

```sql
-- 查看当前数据库的隔离级别, MySQL8.0之前的查看方法
SELECT @@tx_isolation;
-- 或者
SHOW VARIABLES LIKE 'tx_isolation';
```

###### MySQL8.0之后的查看方法

```sql
-- 查看当前数据库的隔离级别, MySQL8.0之后的查看方法
SELECT @@transaction_isolation;
-- 或者
SHOW VARIABLES LIKE 'transaction_isolation';
```

##### 3.4.3.2 设置数据库服务器的隔离级别

**设置后只有重新连接数据库服务器才能看到修改效果**

```sql
-- 设置数据库服务器的隔离级别, 执行本语句后只有重新连接数据库服务器才能看到修改效果
-- 修改是针对数据库服务器的, 所以服务器里所有的数据库的隔离级别都改了
SET GLOBAL TRANSACTION ISOLATION LEVEL 隔离级别;
```

示例

```sql
SET GLOBAL TRANSACTION ISOLATION LEVEL READ COMMITTED;

SET GLOBAL TRANSACTION ISOLATION LEVEL REPEATABLE READ;
```

