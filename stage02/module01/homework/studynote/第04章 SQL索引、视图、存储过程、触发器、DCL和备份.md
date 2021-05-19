# 第04章 SQL索引、视图、存储过程、触发器、DCL和备份

[toc]

## 一. 索引

### 1.1 什么是索引

- 在数据库中, 对列(字段)建立索引可以大大提高该列(字段)的查询速度,  通过善用这些锁魂,可以令数据的查询和运行更加高效.
- 如果合理的设计且使用索引的数据库是一辆跑车的话, 那么没有设计和使用索引的数据库就是一个独轮手推车. 索引就好比汉语字典的目录页, 我们可以按拼音、笔画、偏旁部首**(这些就相当于建立了索引的列)**等排序的目录**(相当于索引)**快速查找到需要的字。
- 设置索引后可以极大地提高本列的查询速率
- 只有设置了索引的列才能设置自增约束(auto_increment)
- 索引必须有名字

### 1.2 常见索引分类

| 索引名称 | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| 主键索引 | 设置了主键约束的列本身就是个索引, 叫做主键索引               |
| 外键索引 | 设置了外键约束的列同时会生成外键索引, 并绑定该外键索引       |
| 唯一索引 | 设置了唯一约束的列会同时生成唯一索引, 并绑定该唯一索引       |
| 普通索引 | 为既没有设置主键约束也没有设置唯一约束的列 设置的索引叫做普通索引 |

**MySQL将表的索引和数据保存在后缀名为`.ibd`的文件中, 表结构保存在`.frm`文件中**

### 1.3 主键索引

> 设置了主键约束的列叫做主键, 一个表中只能有一列是主键, 主键的值用来表示本条记录

- 设置了主键约束的列本身就是个索引, 叫做主键索引
- 主键索引是一种特殊的唯一索引
- 主键索引的名字是`PRIMARY`, 类型是`Unique`
- 主键索引的添加和删除其实就是主键约束的添加和删除

### 1.4 唯一索引

- 设置了唯一约束的列会同时生成唯一索引, 并绑定该唯一索引
- 唯一索引的名字跟列名相同, 索引类型是`Unique`
- 删除唯一索引时同时也会删除列的唯一约束

#### 1.4.1 添加唯一索引

其实就是添加唯一约束

在建表时就添加唯一约束, 或者建表后用以下格式添加

`ALTER TABLE 表名 ADD UNIQUE(列名);`

#### 1.4.2 删除唯一索引

==删除唯一索引的同时也会删除掉唯一约束, 所以删除唯一约束也是使用以下格式==

`ALTER TABLE 表名 DROP INDEX 索引名`

==唯一约束的索引名跟列名是相同的==

### 1.5 普通索引

- 为既没有设置主键约束也没有设置唯一约束的列 设置的索引  叫做普通索引
- 索引的唯一任务是加快对数据的访问速度, 因此应该只为那些最经常出现在查询条件、分组条件、排序条件中的数据列创建索引
- 普通索引的名字需要用户来起, 一般是`index_列名`

#### 1.5.1 语法格式

##### 1.5.1.1 添加普通索引

###### 1.5.1.1.1 在建表时添加

```sql
CREATE TABLE emp3(
	...
	INDEX 索引名(列名)
);
```

###### 1.5.1.1.2 表建好再添加

```sql
ALTER TABLE 表名 ADD INDEX 索引名(列名);
```

##### 1.5.1.2 删除普通索引

```sql
ALTER TABLE 表名 DROP INDEX 索引名;
```

#### 1.5.2 示例

##### 1.5.2.1 添加普通索引

```sql
CREATE TABLE emp(
	eid INT PRIMARY KEY AUTO_INCREMENT,
	ename VARCHAR(10) UNIQUE,
	age INT,
	INDEX index_age(age)
);
SHOW CREATE TABLE emp;
```

![](%E7%AC%AC04%E7%AB%A0%20SQL%E7%B4%A2%E5%BC%95%E3%80%81%E8%A7%86%E5%9B%BE%E3%80%81%E5%AD%98%E5%82%A8%E8%BF%87%E7%A8%8B%E3%80%81%E8%A7%A6%E5%8F%91%E5%99%A8%E5%92%8CDCL.assets/%E7%B4%A2%E5%BC%95%E7%A4%BA%E6%84%8F%E5%9B%BE.png)

或者

```sql
CREATE TABLE emp2(
	eid INT PRIMARY KEY AUTO_INCREMENT,
	ename VARCHAR(10) UNIQUE,
	age INT
);
ALTER TABLE emp2 ADD INDEX index_age(age);
SHOW CREATE TABLE emp2;
```

##### 1.5.2.2 删除索引

```sql
ALTER TABLE emp DROP INDEX index_age;
SHOW CREATE TABLE emp;
```

### 1.6 索引的优缺点

- 添加索引首先应考虑在where、order by、group by涉及的列上建立索引
- 索引的优点
  - 极大地提高了查询速度
  - 可以显著地减少查询中分组和排序的时间
- 索引的缺点
  - 创建索引和维护索引需要时间, 而且数据量越大所耗费的时间越长
  - 当对表中的数据进行增删改的时候,  索引也要同时进行对应的维护, 降低了数据的维护速度

## 二. MySQL视图

### 2.1 什么是视图

- 视图是一种虚拟表
- 视图建立在已有表的基础上, 视图赖以建立的这些表称为基表
- 向视图提供数据内容的语句为`SELECT`语句, 可以将视图理解为被存储起来的`SELECT`语句
- 视图是基表的一种表现形式

### 2.2 视图的作用

- 权限控制时可以使用
  - 比如, 只允许用户查询基表中的某几个列, 其他列的结果不允许显示给客户, 那就对基表的的这几个列开通视图, 只允许用户查询这个视图, 但不允许用户查询基表, 这样用户只能查询和显示这几个列了, 起到了权限控制的作用
- 简化复杂的多表查询
  - 视图本身是一条查询SQL, 我们可以将一次复杂的查询构建成一张视图, 用户只要查询视图就可以从复杂查询的结果中进行二次查询了(不需要再编写复杂的SQL了)
  - 视图主要是为了复用多表查询的结果, 这样就能简化多表查询了

### 2.3 视图的创建和使用

#### 2.3.0 数据准备

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
```

#### 2.3.1 创建视图

##### 2.3.1.1 创建格式

```sql
-- 创建视图
CREATE VIEW 视图名 AS SELECT语句;
-- 显示所有匹配指定字符串的视图
SHOW TABLE STATUS LIKE '关键字';
-- 显示指定视图的结构
DESC 视图名;
-- 显示指定视图创建时的结构
SHOW CREATE VIEW 视图名;
-- 删除视图
DROP VIEW 视图名;
```

==视图中的列只能是select语句中显示的列==

##### 2.3.1.2 示例

```sql
CREATE VIEW view_straw_hat AS SELECT p.`pname`,p.`gender`,p.`age`,t.`tname`
				FROM pirate p LEFT JOIN team t ON p.`team_id` = t.`team_id`;

CREATE VIEW view_straw_hat1 AS SELECT p.`pname`,t.`tname`
				FROM pirate p LEFT JOIN team t ON p.`team_id` = t.`team_id`;
```

```sql
-- 显示所有匹配指定字符串的视图
SHOW TABLE STATUS LIKE '%view%';

-- 视图可以视作是一种特殊的表, 所以也可以通过show tables; 来显示
SHOW TABLES;
```

```sql
-- 显示指定视图创建时的结构
SHOW CREATE VIEW view_straw_hat;
SHOW CREATE VIEW view_straw_hat1;
```

```sql
-- 显示指定视图的结构
DESC view_straw_hat;
DESC view_straw_hat1;
```

```sql
-- 删除视图
DROP VIEW view_straw_hat1;
-- 显示所有匹配指定字符串的视图
SHOW TABLE STATUS LIKE '%view%';
```

#### 2.3.2 使用视图

```sql
-- 像表那样使用就行了
SELECT * FROM view_straw_hat;
SELECT * FROM view_straw_hat WHERE gender = '女';

-- 当基表上的数据改变时, 视图上的数据也会改变
INSERT INTO team VALUES(NULL, '红发海贼团');
INSERT INTO pirate VALUES(NULL, '香克斯', 39, '男', '1971.3.9', 40.489, 6);
SELECT * FROM view_straw_hat;
```

### 2.4 视图与表的区别

- 视图是建立在表的基础上,表存储数据库中的数据, 而视图只是做一个数据的展示
- 通过试图不能改变表中的数据, 即试图只能用来查, 不能对数据进行增删改, 因为一般情况下视图中的数据都是表中的列经过计算得到的结果,不允许更新
- 删除视图, 基表不受影响, 而删除基表, 视图就不再起作用了(注意是不再起作用而非消失, 如果再创建一个同名的表, 该视图就可以重新起作用了)

## 三. MySQL存储过程(了解)

### 3.1 什么是存储过程

- MySQL5.0版本开始支持存储过程
- 存储过程(Stored Procedure)是一种数据库中存储复杂程序, 以便外部程序调用的一种数据库对象. 
  - 进一步理解, 存储过程(Stored Procedure)是一个数据库对象, 这个对象存储了能对数据库进行指定复杂操作的SQL语句集, 用户只需要调用这个存储过程, 就能得到这个SQL语句集的操作结果
- 存储过程是为了完成特定功能的SQL语句集, 经编译创建并保存在数据库中,用户可以通过指定存储过程的名字并给定参数(需要时)来调用执行
- 简单理解: 存储过程其实就是一堆SQL语句的合并, 中间还加入了一些逻辑控制

### 3.2 存储过程的优缺点

#### 3.2.1 优点

- 存储过程一旦调试完成后, 就可以稳定运行, 前提是: 业务需求相对稳定, 没有变化
- 存储过程减少了业务系统与数据库的交互, 降低了耦合, 数据库交互更加快捷
  - 这是因为业务系统跟数据库服务器是分开的, 业务系统想要数据库服务器办一件由多条SQL语句集组成的事时, 就必须先把这些SQL语句集发给数据库服务器, 然后数据库服务器再执行这些SQL语句集, 而存储过程把这些SQL语句集封装保存到了数据库服务器上, 当业务系统需要办理这件事时, 就不需要编写发送大量的SQL语句集了, 只需要发送个存储过程的名字名字即可, 数据库服务器就会根据其保存的存储过程来执行对应的操作

#### 3.2.2 缺点

- 在互联网行业中, 大量使用MySQL服务器软件, 但MySQL的存储过程功能与Oracle的相比较弱, 所以较少使用
- 互联网行业的需求变化较快, 不符合存储过程的使用前提
- 存储过程一直十分困难, 在数据库集群环境下, 保证各个库之间存储过程变更一致也十分困难
  - ==阿里的代码规范里也提出了禁止使用存储给, 因为存储过程难以调试和扩展, 更没有一致性==

### 3.3 存储过程的创建方式

#### 3.3.0 显示所有存储过程

```sql
-- 显示执行数据库下的所有存储过程
SHOW PROCEDURE STATUS WHERE db = '数据库名';
-- 显示指定存储过程的创建代码
SHOW CREATE PROCEDURE 存储过程名;
```



#### 3.3.1 无参的存储过程

##### 3.3.1.1 格式

```sql
DELIMITER $$ -- 声明语句结束符, 可以自定义, 一般使用$$\
CREATE PROCEDURE 存储过程名()  -- 声明存储过程
BEGIN        -- 开始编写存储过程的内容
SQL语句集;    -- 存储过程的内容
END $$       -- 编写存储过程结束
```

##### 3.3.1.2 示例

```sql
DELIMITER $$
CREATE PROCEDURE show_members()
BEGIN
SELECT t.tname, p.pname
	FROM pirate p LEFT JOIN team t ON p.team_id = t.team_id ORDER BY CONVERT(t.tname USING GBK) ASC;
END $$
```

##### 3.3.1.3 调用存储过程

###### 3.3.1.3.1 格式

```sql
CALL 存储过程名();
```

###### 3.3.1.3.2 示例

```sql
CALL show_members();
```

##### 3.3.1.4 删除存储过程

###### 3.3.1.4.1 格式

```sql
DROP PROCEDURE 存储过程名;
```

###### 3..3.1.4.2 示例

```sql
DROP PROCEDURE show_members;
```

#### 3.3.2 有参的存储过程

##### 3.3.2.1 格式

```sql
DELIMITER $$ -- 声明语句结束符, 可以自定义, 一般使用$$\
CREATE PROCEDURE 存储过程名(IN 参数名x 参数类型1, IN 参数y 参数类型2, ... , IN 参数名z 参数类型n)  -- 声明存储过程
BEGIN        -- 开始编写存储过程的内容
SQL语句集;    -- 存储过程的内容
END $$       -- 编写存储过程结束
```

##### 3.3.2.2 示例

```sql
DELIMITER $$
CREATE PROCEDURE add_and_show_pirate(IN pname VARCHAR(10), IN age INT, IN birthday DATE, IN gender CHAR(1), IN reward DOUBLE(12,8), IN team_id INT)
BEGIN
INSERT INTO pirate VALUES(NULL, pname, age , gender, birthday, reward, team_id);
SELECT * FROM pirate;
END $$
```

##### 3.3.2.3 调用存储过程

###### 3.3.2.3.1 格式

```sql
CALL 存储过程名(值1, 值2, ... , 值n);
```

###### 3.3.2.3.2 示例

```sql
SELECT * FROM pirate;
CALL add_and_show_pirate('薇薇', 18, '2002.6.1', '女', 0, 1);
```

##### 3.3.2.4 删除存储过程

###### 3.3.2.4.1 格式

```sql
DROP PROCEDURE 存储过程名;
```

###### 3..3.2.4.2 示例

```sql
DROP PROCEDURE show_members;
```

#### 3.3.3 有参同时能给变量赋值的存储过程

##### 3.3.3.1 格式

在创建时参数的最后多了个`out 变量名 变量类型`, 并且要在SQL语句集之后通过`set @变量名 = 值;`为变量赋值和通过`select @变量名;`将这个只返回给存储过程的调用者

```sql
DELIMITER $$ -- 将MySql的结束符从 ; 改为 $$ , 避免执行时因为与sql语句集的 ; 混淆而导致出错, 当创建结束后该符号会自动换回;
CREATE PROCEDURE 存储过程名(IN 参数名x 参数类型1, IN 参数y 参数类型2, ... , IN 参数名z 参数类型n,OUT 变量名 变量类型)  -- 声明存储过程
BEGIN        -- 开始编写存储过程的内容
SQL语句集;    -- 存储过程的内容
SET @变量名=值;
SELECT @变量名;
END $$       -- 创建存储过程结束, $$ 作为结束符的生涯也就结束了
```

##### 3.3.3.2 示例

```sql
DELIMITER $$
CREATE PROCEDURE demo_procedure(IN pname VARCHAR(10), IN age INT, IN birthday DATE, IN gender CHAR(1), IN reward DOUBLE(12,8), IN team_id INT, OUT return_value INT)
BEGIN
INSERT INTO pirate VALUES(NULL, pname, age , gender, birthday, reward, team_id);
SET @return_value=1;
SELECT @return_value;
END $$
```

##### 3.3.3.3 调用存储过程

###### 3.3.3.3.1 格式

```sql
CALL 存储过程名(值1, 值2, ... , 值n,@变量名);
```

###### 3.3.3.3.2 示例

```sql
CALL demo_procedure('薇薇', 18, '2002.6.1', '女', 0, 1, @return_value);
```

##### 3.3.3.4 删除存储过程

###### 3.3.3.4.1 格式

```sql
DROP PROCEDURE 存储过程名;
```

###### 3..3.3.4.2 示例

```sql
DROP PROCEDURE demo_procedure;
```

## 四. MySQL触发器(了解)

### 4.1 什么是触发器(trigger)

触发器(trigger)是MySQL提供给程序员和数据分析员的  用来保证数据完整性的一种方法, 它始于表事件相关的特殊的存储过程, trigger的执行性不是由程序调用, 也不是手动启动, 而是由事件来触发,比如当对一个表进行(insert, update, delete)操作时就会激活trigger执行相应的操作

### 4.2 触发器(trigger)创建的四个要素

1. 监视地点(也就是哪一个表table)
2. 监视事件(在这个表上发生了insert/update/delete中的哪一个事件)
3. 触发时间(在监视事件执行前还是执行后 去执行触发事件)
4. 触发事件(监视事件发生前或后要执行的操作)

### 4.3 创建触发器(trigger)

#### 4.3.0 显示所有触发器(trigger)

```sql
SHOW TRIGGERS;
```

#### 4.3.1 格式

```sql
delimiter $        -- 将MySql的结束符从 ; 改为 $ , 避免执行时因为与sql语句集的 ; 混淆而导致出错, 当创建结束后该符号会自动作废
create trigger 触发器名称
before/after insert/update/delete  -- 触发时间和监视事件, before/after只能选一个, insert/update/delete只能选一个
on 表名称         -- 出发地点
for each row     -- 固定写法, 叫做行触发器, 表示监视每一行是否发生了监视事件
begin
SQL语句集          -- 触发事件
end $             -- 创建触发器结束, $ 作为结束符的生涯自动结束
```

#### 4.3.2 示例

##### 4.3.2.0 准备数据

```sql
SELECT * FROM team;
-- 给team表添加一个人数列
ALTER TABLE team ADD headcount INT DEFAULT 0;

SELECT * FROM team;
-- headcount列的值就是没给团队里的人数
UPDATE team t 
SET t.headcount = (SELECT COUNT(p.pid) 
			FROM pirate p 
			WHERE p.team_id = 1
		  ) 
WHERE t.`team_id` = 1;

UPDATE team t 
SET t.headcount = (SELECT COUNT(p.pid) 
			FROM pirate p 
			WHERE p.team_id = 2
		  ) 
WHERE t.`team_id` = 2;

UPDATE team t 
SET t.headcount = (SELECT COUNT(p.pid) 
			FROM pirate p 
			WHERE p.team_id = 3
		  ) 
WHERE t.`team_id` = 3;

UPDATE team t 
SET t.headcount = (SELECT COUNT(p.pid) 
			FROM pirate p 
			WHERE p.team_id = 4
		  ) 
WHERE t.`team_id` = 4;

UPDATE team t 
SET t.headcount = (SELECT COUNT(p.pid) 
			FROM pirate p 
			WHERE p.team_id = 5
		  ) 
WHERE t.`team_id` = 5;

UPDATE team t 
SET t.headcount = (SELECT COUNT(p.pid) 
			FROM pirate p 
			WHERE p.team_id = 6
		  ) 
WHERE t.`team_id` = 6;

SELECT * FROM team;
```

##### 4.3.2.1 创建触发器

###### 1.当向表pirate中添加记录时, 表team中对应团队的headcount加1

```sql
DELIMITER $
CREATE TRIGGER trigger_insert
AFTER INSERT -- 触发时间可以是after也可以是before
ON pirate
FOR EACH ROW
BEGIN
UPDATE team t 
SET t.headcount = t.headcount + 1 
WHERE t.team_id = (SELECT p.team_id 
			FROM pirate p 
			WHERE p.pid = (SELECT MAX(p.pid) FROM pirate p)
                  );
END $
```

```sql
SELECT * FROM team;
INSERT INTO pirate VALUES(NULL,'薇薇',18,'女','2002.7.1',0,1);
SELECT * FROM team;
```

###### 2. 当删除掉表pirate中的一条记录时,表team中对应团队的headcount减1

```sql
DELIMITER $
CREATE TRIGGER trigger_delete
BEFORE DELETE  -- 必须是before, 这样才能在记录删除前, 获取到记录的team_id
ON pirate
FOR EACH ROW
BEGIN
UPDATE team t 
SET t.headcount = headcount - 1
WHERE t.team_id = (SELECT p.team_id 
			FROM pirate p 
			WHERE p.pid = (SELECT MAX(p.pid) FROM pirate p)
		  );
END $
```

```sql
SELECT * FROM team;
DELETE FROM pirate WHERE pname = '薇薇';
SELECT * FROM team;
```

### 4.4 删除触发器

```sql
DROP TRIGGER 触发器名称;
```

```sql
DROP TRIGGER trigger_insert;
```



## 五. DCL(Database control language)

### 5.0 `root`用户

MySQL的超级管理员是`root`用户, 拥有全部的权限, 除了可以对所有数据库进行CRUD(增查改删)外, 还可以CRUD其他的用户, 并对这些用户分配权限

#### 修改指定用户的密码

```sql
-- 登录root用户后,通过以下格式改任意用户的密码, 只有mysql能这么做
mysqladmin -u用户名 -p旧密码 新密码
```

### 5.1 创建用户及授权

#### 5.1.1 创建用户

```sql
CREATE USER '用户名'@'主机名' IDENTIFIED BY '密码';
```

| 参数   | 说明                                                         |
| ------ | ------------------------------------------------------------ |
| 用户名 | 创建的用户名称, 即登录名称                                   |
| 主机名 | 一般是个IP地址, 只允许该用户 在 使用该IP地址的主机上 登录<br />如果只允许该用户在本地主机登录(即数据库服务器所在的系统)的话, 可以将主机名设为`localhost`<br />如果想让该用户可以在任意主机上使用的话, 可以将主机名设置为`%`. (`%`是个通配符, 可以表示任意多个字符) |
| 密码   | 登录密码                                                     |

```sql
CREATE USER 'tifa'@'localhost' IDENTIFIED BY '111111';
CREATE USER 'aerith'@'%' IDENTIFIED BY '222222';
CREATE USER 'jill'@'192.168.1.107' IDENTIFIED BY '333333';
```

#### 5.1.2 用户授权

==现阶段学的授权, 只能授予到  表这一级  的权限, 也就是说用户只能对  已创建好的表  有相应的权限(比如对列的crud, 对记录的crud), 用户是不能建表的==

创建好用户后, 需要对该用户进行授权

```sql
GRANT 权限1,权限2, ... ,权限n ON 数据库名.表名 TO '用户名'@'主机名';
```

| 参数                    | 说明                                                         |
| ----------------------- | ------------------------------------------------------------ |
| 权限1,权限2, ... ,权限n | 可以是1个权限, 也可以是多个权限, 此种方法授权的范围是对指定表的列进行crud, 对表中的记录进行crud, 权限的关键字`ALTER` `INSERT` `SELECT` `UPDATE` `DELETE`, 也可以使用关键字`ALL`来表示所有的权限 |
| ON                      | 用来指定权限针对哪些数据库中的哪些表, `*`这个符号表示所有的数据库或表 |
| TO                      | 用来指定哪个用户拥有该权限                                   |

```sql
-- 授予指定的权限, 指定库上的指定表
GRANT ALTER,INSERT,SELECT,UPDATE,DELETE ON one_piece.pirate to 'tifa'@'localhost';
```

```sql
-- 授予所有的权限, 指定库上的指定表
GRANT ALL ON one_piece.pirate TO 'tifa'@'localhost';
```

```sql
-- 授予所有的权限, 指定库上的所有表
GRANT ALL ON one_piece.* TO 'tifa'@'localhost';
```

```sql
-- 授予所有的权限, 所有库上的任意表
GRANT ALL ON *.* TO 'tifa'@'localhost';
```

```sql
-- 授予所有的权限, 指定库上的符合指定字符串规律的表, %表示任意多个字符, _表示任意一个字符
GRANT ALL ON one_piece.`%pirate_` TO 'tifa'@'localhost';
SHOW GRANTS FOR 'tifa'@'localhost';
```

#### 5.1.3 查看权限

只能用来查看指定用户的权限

##### 5.1.3.1 格式

```sql
SHOW GRANTS FOR '用户名'@'主机名';
```

##### 5.1.3.2 示例

```sql
SHOW GRANTS FOR 'tifa'@'localhost';
```

### 5.2 查询用户

**MySQL服务器将所有的账号及相关信息都保存到了数据库mysql下的user表中了, 可以通过查询表mysql.user的方式来查看这个MySQL服务器下的所有用户的信息, 但是不会显示密码信息**

```sql
SELECT * FROM mysql.user;
```

### 5.3 删除用户

#### 5.3.1 格式

```sql
DROP USER '用户名'@'主机名';
```

#### 5.3.2 示例

```sql
SELECT * FROM mysql.user;

DROP USER 'jill'@'192.168.1.107';

SELECT * FROM mysql.user;
```



## 六. 数据库备份和还原

备份的应用场景: 在服务器进行数据传输、数据存储、和数据交换的过程中如果硬件发生了损坏，比如主机意外停机或存储介质损坏， 这时如果没有采取数据备份和数据恢复的手段和措施，就会导致数据的丢失， 造成的损失是无法弥补预估量的。

### 6.1 SQLYog数据库备份和还原

#### 6.1.1 备份

1. 选中要备份的数据库， 右键  选择备份导出➡选择 备份到数据库(当然也可以选择**计划备份**来定时备份数据库)
2. 指定文件位置，选择导出即可

#### 6.1.2 还原

1. **先创建一个同名的数据库**(或者删除出错的数据库, 再创建一个同名的数据库)
2. 选中该数据库, 右键  选择导入, 再选择执行SQL脚本
3. 找到备份文件, 点击执行即可

#### 6.1.3 还原出错时的解决办法

1. 检查是否创建了同名数据库, 如果创建了同名数据库, 
2. 修改允许导入的最大数据包的值
   1. 打开MySQL的`my.ini`文件
   2. 在`[mysqld]`下添加`max_allowed_packet=1024M`, `1024M`可以按需修改

### 6.2 命令行(CMD)备份和还原

==本方法会出现大量复杂的错误,不建议使用==

在设置好MySQL的环境变量的前提下, 打开`CMD`执行以下命令

#### 6.2.1 备份

```dos
mysqldump -uroot -p密码 数据库名 > 文件路径
```

文件名一般是数据库名.sql, 注意`>`前后必须有空格

```dos
mysqldump -uroot -p000000 db > d:/db.sql
```

#### 6.2.2 还原

**注意: 还原前需要先创建一个同名的数据库**

在`CMD`上登录root账户后, 运行一下dos命令

```dos
source 备份文件路径
```

