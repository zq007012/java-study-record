# 第03章 MySQL多表、外键、子查询、数据库设计

[toc]

## 一. 多表

### 1.1 多表

实际开发中, 一个项目通常需要很多张表才能完成. 例如一个商城i项目的数据库, 需要有很多张表: 用户表、分类表、商品表、订单表、购物车表...

### 1.2 单表的缺点

- 冗余, 同一个字段中会出现大量的重复数据, 耗费大量的存储空间.
  - 解决办法 -- 设计多张表, 使用外键约束使这些表间建立联系

### 1.3 表间的关系

> 拥有外键的表被称为从表, 与外键对应的主键所在的表叫做主表.

### 1.4 外键约束

#### 1.4.1 什么是外键

- 外键是指从表中设置了外键约束的列(字段)
- 外键一定要与主表中的主键相对应
- 外键自带索引
- 使用外键约束可以让两张表之间产生一个对应关系, 从而保证主从表间引用的完整性

#### 1.4.2 创建外键约束

> 只有存在两张表, 且一个表的字段会引用另一个表字段的值时, 设置外键约束才有意义

> 外键约束一般是先创建一个完全体的表, 然后根据需要将这个完全提的表拆分成具有主从之分的两张表, 之后在从表的外键上设置外键约束

##### 1.4.2.1 语法格式

1. 创建表时就设置好外键约束

   1. ```sql
      -- 知道可以这样写就行
      constraint 外键约束名 foreign key(外键字段名) references 主表名(主键字段名);
      -- 其中外键约束名, 指的是这个外键约束的名字, 这个名字必须是唯一的不能跟其它的外键约束的名字重复.
      -- constraint的意思是约束
      -- constraint 外键约束名  可以不写, 如果不写系统会自动帮你设置一个不重复的外键约束名
      ```

   2. ```sql
      -- 这种写法必须记住
      foreign key(外键字段名) references 主表名(主键字段名)
      ```

2. 已有表添加外键约束

   1. ```sql
      -- 知道可以这样写就行
      alter table 从表名 add constraint 外键约束名 foreign key(外键字段名) references 主表名(主键字段名)
      ```

   2. ```sql
      -- 这种写法必须记住
      alter table 从表名 add foreign key(外键字段名) references 主表名(主键字段名)
      ```

##### 1.4.2.2 示例

将下表拆分为主表和从表, team为外键

```sql
CREATE TABLE student(
	sid INT PRIMARY KEY AUTO_INCREMENT,
	sname VARCHAR(10) NOT NULL,
	gender CHAR(1),
	age INT,
	birthday DATE,
	score_final DOUBLE(5,2),
	pass_final BOOLEAN,
	team VARCHAR(5)
);
```

拆分成student从表和team主表

###### 1.4.2.2.1 创建从表时就设置外键

```sql
-- team表中的tean_id为主键, 另一列的名字是team
CREATE TABLE team(
	team_id INT PRIMARY KEY AUTO_INCREMENT,
	team VARCHAR(10)
);

-- student表中的team_id是外键, 对应team表中的主键team_id
CREATE TABLE student(
	sid INT PRIMARY KEY AUTO_INCREMENT,
	sname VARCHAR(10) NOT NULL,
	gender CHAR(1),
	age INT,
	birthday DATE,
	score_final DOUBLE(5,2),
	pass_final BOOLEAN,
	team_id INT,
	FOREIGN KEY(team_id) REFERENCES team(team_id)
);
```

###### 1.4.2.2.2 创建从表后在设置外键

```sql
CREATE TABLE student(
	sid INT PRIMARY KEY AUTO_INCREMENT,
	sname VARCHAR(10) NOT NULL,
	gender CHAR(1),
	age INT,
	birthday DATE,
	score_final DOUBLE(5,2),
	pass_final BOOLEAN,
	team_id INT
);

ALTER TABLE student ADD FOREIGN KEY(team_id) REFERENCES team(team_id);
```

#### 1.4.3 向有外键约束的表中插入数据

- 添加外键约束后, 就会产生强制性的外键数据检查, 从而保证了数据的完整性和一致性
  - 意思就是: 向从表添加数据时, 会先检查添加到外键上的数据在主表中是否存在;  若主表中有, 插入数据才能成功; 若主表中没有, 插入数据就会失败.
- 向从表中插入数据前, 应该先完善主表里的数据

```sql
-- 先完善主表中的数据
SELECT * FROM team;
INSERT INTO team VALUES(NULL, '草帽');
INSERT INTO team VALUES(NULL,'九蛇');
INSERT INTO team VALUES(NULL,'公主');
INSERT INTO team VALUES(NULL,'人鱼');
INSERT INTO team VALUES(NULL,'太阳');
SELECT * FROM team;

-- 再向从表中插入数据
SELECT * FROM student;
INSERT INTO student VALUES(NULL, '甚平', '男', 45, '1975.02.28', 699.00, TRUE, 1);
INSERT INTO student VALUES(NULL, '白星', '女', 16, '2004.12.28', 499.00, FALSE, 3);
SELECT * FROM student;
```

#### 1.4.4 删除外键约束

- 语法

  - ```sql
    alter table 从表名 drop foreign key 外键约束名;
    ```

  - 可通过`show create table 从表名`查看从表建表时的信息, 来找到设置外键时的外键约束名

- 示例

  - ```sql
    SHOW CREATE TABLE student;
    ALTER TABLE student DROP FOREIGN KEY student_ibfk_1;
    SHOW CREATE TABLE student;
    ```

#### 1.4.5 外键约束注意事项

- 从表外键类型必须与对应的主表逐渐的类型一致, 否则会建表失败
- 从表插入数据前, 应该先确认主表的数据是否已完善
- 删除数据时, 应该先删除从表中的数据
- 从表未开启级联删除时, 如果从表中外键上的数据使用到了主表中的数据, 那么主表中的相关数据是无法删除的
- 可以随意删除从表
- 删除主表前必须先解除与从表的外键约束关系, 或者先删除掉从表才能删除主表
- 只有先清空了主表的数据, 才能清空从表的数据
- 主表不能使用`truncate from 主表`来清空数据, 但从表可以

#### 1.4.6 级联删除

- 只有从表可以设置级联删除

- 关键字`on delete cascade`,  必须跟在外键约束设置后面

- 主表可以随意删除数据, 并且从表中使用了这些数据的记录也会同时删除

- 级联删除不建议开启, 因为数据是很宝贵的, 级联删除不仅会删除主表上的数据, 还会删除大量从表上的使用了相关数据的记录. 很容易引起误操作.

- 开启级联删除

  - ```sql
    -- 在创建表时就开启
    CREATE TABLE student(
    	sid INT PRIMARY KEY AUTO_INCREMENT,
    	sname VARCHAR(10) NOT NULL,
    	gender CHAR(1),
    	age INT,
    	birthday DATE,
    	score_final DOUBLE(5,2),
    	pass_final BOOLEAN,
    	team_id INT,
        -- 级联删除必须跟在外键约束设置的后面
    	FOREIGN KEY(team_id) REFERENCES team(team_id) ON DELETE CASCADE
    );
    ```

  - ```sql
    -- 在创建表后开启级联删除
    -- 1. 先删除从表的原外键约束
    SHOW CREATE TABLE student;
    ALTER TABLE student DROP FOREIGN KEY student_ibfk_1;
    SHOW CREATE TABLE student;
    -- 2. 再创建一个开启了级联删除的新外键约束
    ALTER TABLE student ADD FOREIGN KEY(team_id) REFERENCES team(team_id) ON DELETE CASCADE;
    SHOW CREATE TABLE student;
    ```

- 关闭级联删除

  - 建表时级联删除是默认关闭的

  - ```sql
    -- 关闭级联删除
    -- 1. 先删除从表的外键约束
    SHOW CREATE TABLE student;
    ALTER TABLE student DROP FOREIGN KEY student_ibfk_1;
    SHOW CREATE TABLE student;
    -- 2. 再创建一个没有开启级联删除的外键约束
    ALTER TABLE student ADD FOREIGN KEY(team_id) REFERENCES team(team_id);
    SHOW CREATE TABLE student;
    ```

- 级联删除的效果展示

  - ```sql
    -- 级联删除的效果展示
    SELECT * FROM student;
    DELETE FROM team WHERE team_id = 1;
    SELECT * FROM student;
    ```



### 1.5 多表关系设置

**在sqlYog中, 点击菜单栏中的`文件`, 点击`新架构设计器`, 然后把数据库中的多张表一张一张地拖拽到架构设计器页面中, 就能看到这些表间的主从关系了, 主表为`1`, 从表为`∞`**

表与表之间的三种关系一对多关系, 多对多关系, 一对一关系

#### 1.5.1 一对多关系

以student表和team表举例

- student表中的一条记录就是一个学生, team表中的一条记录就是一个团队
- 如果一个学生只能进入一个团队, 而一个团队里可以有多个学生的话, 那么这两个表就是一对多关系. 
  - 需要在student表上建立指向team表的外键, 使team表与student表建立主从表关系
  - **一对多关系中, 一是主表, 多是从表**

#### 1.5.1 多对多关系

- 如果一个学生可以进入多个团队, 且一个团队里可以有多个学生的话, 那么这两个表就是多对多关系. 
  - 需要创建第三个表作为中间表, 在中间表上分别建立指向student表和team表的外键
  - Student表和中间表是主从表关系, team表和中间表是主从表关系, 但student表和team表间是没有主从表关系的
  - student表和team表间的多对多关系可以在中间表的数据上体现出来
  - 可以在中间表上查询某个团队里有哪些学生, 查询某个学生参加了哪些团队
  - **多对多关系中, 多与多是中间表的主表, 中间表是从表**
- 如果一个学生只能进入一个团队, 一个团队里也只能有一个学生, 那么这两个表就是一对一关系
  - 建议将这两个表合并为一个表, 弄成两个是在浪费资源

## 二. 多表查询

### 2.1 什么是多表查询

- DQL的一种查询功能
- 利用主从表之间的关系, 在多张表见查询到需要的数据
- SQL表中的多表查询一般是指主从表两张表间的查询

### 2.2 数据准备

> 数据要求: 
>
> ​	一个学生只能进一个团队, 一个团队里可以有多个学生
>
> ​	一为团队, 多为学生, 学生表是从表, 外键所在的表, 团队表是主表
>
> ​	学生表: id主键 姓名  年龄  所在团队
>
> ​	团队表: id主键 团队名称
>

#### 2.2.1 建表

```sql
-- 先创建主表
CREATE TABLE team(
	team_id INT PRIMARY KEY AUTO_INCREMENT,
	team VARCHAR(10) UNIQUE NOT NULL
);
-- 再创建从表
CREATE TABLE student(
	student_id INT PRIMARY KEY AUTO_INCREMENT, 
	sname VARCHAR(10) UNIQUE NOT NULL, 
	age INT NOT NULL, 
	team_id INT,
	FOREIGN KEY(team_id) REFERENCES team(team_id)
);
```

#### 2.2.2 插入数据

```sql
-- 先完善主表的数据
INSERT INTO team VALUES(NULL,'草帽');
INSERT INTO team VALUES(NULL,'九蛇');
INSERT INTO team VALUES(NULL, '太阳');
INSERT INTO team VALUES(NULL, '公主');
INSERT INTO team VALUES(NULL, '白胡子');
SELECT * FROM team;
-- 再向从表插入数据
INSERT INTO student VALUES(NULL, '路飞', 17, 1);
INSERT INTO student VALUES(NULL, '娜美', 17, 1);
INSERT INTO student VALUES(NULL, '甚平', 45, 3);
INSERT INTO student VALUES(NULL, '汉库克', 30, 2);
INSERT INTO student VALUES(NULL, '黛西', 19, 2);
INSERT INTO student VALUES(NULL, '桔梗', 22, 2);
INSERT INTO student VALUES(NULL, '乔巴', 14, 1);
INSERT INTO student VALUES(NULL, '艾斯', 19, 5);
INSERT INTO student VALUES(NULL, '瑞贝卡', 15, 4);
INSERT INTO student VALUES(NULL, '玛格丽特', 20, 2);
INSERT INTO student VALUES(NULL, '薇薇', 17, 4);
INSERT INTO student VALUES(NULL, '白星', 15, 4);
INSERT INTO student VALUES(NULL, '维奥拉', 25, 4);
INSERT INTO student VALUES(NULL, '马尔科', 26, 5);
INSERT INTO student VALUES(NULL, '香吉士', 19, 1);
INSERT INTO student VALUES(NULL, '乌索普', 17, 1);
INSERT INTO student VALUES(NULL, '乔兹', 32, 5);
INSERT INTO student VALUES(NULL, '罗宾', 32, 1);
INSERT INTO student VALUES(NULL, '费舍尔', 50, 3);
INSERT INTO student VALUES(NULL, '布鲁克', 99, 1);
INSERT INTO student VALUES(NULL, '阿拉丁', 43, 3);
INSERT INTO student VALUES(NULL, '弗兰奇', 38, 1);

INSERT INTO student VALUES(NULL,'巴基',43,NULL);
INSERT INTO team VALUES(NULL,'小丑');

SELECT * FROM student;
SELECT * FROM team;
```

### 2.3 笛卡尔积

> 比如集合1{a, b, c}与集合2{1, 2}相乘形成的新集合{a1, a2, a3, b1, b2, b3, c1, c2, c3}就是笛卡尔积.

```sql
SELECT * FROM student, team;
-- 查找出来的结果就是笛卡尔积, 笛卡尔积式的结果没有任何实用意义.
```

==多表查询时必须使用条件查询才能避免笛卡尔积==

### 2.4 多表查询的分类

- 多表查询分为内连接查询和外连接查询
- 不论哪种多表查询方式, 都可以通过给表起别名来简化代码
  - `from 左表 坐标别名, 右表 右表别名`
- SQL查询语句中跟在`from`后面, 写在左边的是左表, 写在右边的是右表
- 多表查询中会先根据`on条件表达式`筛选一遍数据, 然后`where条件条件表达式`对这些筛选出的数据再筛选一次
  - 连接条件一般只写在`on条件表达式`里, 不写在`where条件表达式`里, 这样可以避免逻辑混乱

#### 2.4.1 内连接多表查询

- `on条件表达式`和`where条件表达式`的作用效果是一样的
- 连接条件最好只在`on条件表达式`里写, 这样不容易出错
- 先满足`on条件表达式`, 再满足`where条件表达式`
- 先根据条件表达式中的非连接条件筛选出左表和右表中符合条件的数据, 然后以筛选出的左表中的数据为基础,根据连接条件到筛选出的右表数据中查找匹配的右表数据, 能在右表中找到匹配数据的左表数据留下, 否则去除

##### 2.4.1.1 隐式内连接

- 使用`左表,右表 where 查询条件`进行多表查询的方式, 查询条件一定要包含连接条件

###### 2.4.1.1.1 格式

```sql
select 列名1,列名2, ... , 列名n from 左表, 右表 where 条件表达式;
```

###### 2.4.1.1.2 示例

**显示所有加入了团队的学生的姓名 年龄 以及加入的团队**

```sql
SELECT student.`student_id`, student.`sname`, student.`age`, team.`team`
FROM student, team
ON student.team_id = team.team_id
;

-- 设置多表的别名来简化SQL代码
SELECT s.`student_id`, s.`sname`, s.`age`, t.`team`
FROM student s, team t
ON s.`team_id` = t.`team_id`
;
-- 会发现 巴基 没有显示出来, 因为巴基没有加入任何团队
```

##### 2.4.1.2 显式内连接

- 使用`左表 inner join 右表 on 查询条件`进行多表查询的方式
  - `inner`可以简化掉, 即`左表 join 右表 on 查询条件`
- 

###### 2.4.1.2.1 格式

```sql
select 列名1, 列名2, ... , 列名n from 左表 join 右表 on 条件表达式;

-- 还可以在 on条件 后面加 where条件
select 列名1, 列名2, ... , 列名n from 左表 join 右表 on 条件表达式 where 条件表达式;
```

###### 2.4.1.2.2 示例

**显示所有加入了团队的学生的姓名 年龄 以及加入的团队**

```sql
-- 查询
SELECT s.`student_id`, s.`sname`, s.`age`, t.`team`
FROM student s JOIN team t
ON s.`team_id` = t.`team_id`
;
-- 会发现 巴基 没有显示出来, 因为巴基没有加入任何团队
```

**显示所有加入 草帽团 的  学生  的姓名 年龄 以及加入的团队**

```sql
SELECT s.`student_id`, s.`sname`, s.`age`, t.`team`
FROM student s JOIN team t
ON s.`team_id` = t.`team_id` && t.`team` = '草帽'
;
```



####  2.4.2 外连接多表查询

- 分为做左外连接多表查询和右外连接多表查询
- `on条件表达式`和`where条件表达式`的作用效果是不一样的
- 先满足`on条件表达式`,再满足`where条件表达式`

##### 2.4.2.1 左外连接查询

- 不论`on条件表达式`里的条件是什么, 左表中的数据都会全部显示
- `on条件表达式`中的非连接条件会决定 哪些左表中的数据 不需要匹配右表中的数据, 这部分左表数据对应的右表数据全部以null显示
- `on条件表达式`中的非连接条件会决定 哪些左表中的数据 需要匹配右表中的数据, 这部分左表数据会根据连接条件到右表中查找匹配的信息, 找到的就显示出来, 找不到的就不显示

###### 2.4.2.1.1

**显示所有学生的姓名 年龄, 如果加入了团队, 就将团队名显示出来**

```sql
SELECT s.`student_id`, s.`sname`, s.`age`,t.`team`
FROM student s LEFT JOIN team t
ON s.`team_id` = t.`team_id`;
-- 会发现所有右表中的学生都显示出来了, 没有加入团队的巴基团队信息为null
```

###### 2.4.2.1.2

**显示所有学生的姓名 年龄, 只有草帽团队的学生显示队名**

```sql
SELECT s.`student_id`, s.`sname`, s.`age`,t.`team`
FROM student s LEFT JOIN team t
ON s.`team_id` = t.`team_id` && t.`team` = '草帽'
;
-- 会发现所有右表中的学生都显示出来了, 但只有草帽团的团队名显示出来了, 其他团队的团队名都是null
```

###### 2.4.2.1.3

**显示所有学生的姓名 年龄, 只有年龄大于30的人显示队名**

```sql
SELECT s.`student_id`, s.`sname`, s.`age`,t.`team`
FROM student s LEFT JOIN team t
ON s.`team_id` = t.`team_id` && s.`age` > 30
;
```



###### 2.4.2.1.4

**只显示草帽团队的学生信息**

```sql
SELECT s.`student_id`, s.`sname`, s.`age`,t.`team`
FROM student s LEFT JOIN team t
ON s.`team_id` = t.`team_id`
WHERE t.`team` = '草帽'
;
-- where条件表达式对on条件表达式筛选出的数据进行了再一次筛选, 而非两个条件表达式的条件叠加
```

##### 2.4.2.2 右外连接多表查询
- 不论`on条件表达式`里的条件是什么, **右表**中的数据都会全部显示
- `on条件表达式`中的非连接条件会决定 哪些**右表**中的数据 不需要匹配**左表**中的数据, 这部分**右表**数据对应的**左表**数据全部以null显示
- `on条件表达式`中的非连接条件同时也决定了 哪些**右表**中的数据 需要匹配**左表**中的数据, 这部分**右表**数据会根据连接条件到**左表**中查找匹配的信息, 找到的就显示出来, 找不到的就以null显示



## 三. 子查询

### 3.1 什么是子查询

#### 3.1.1 概念

- 将一条select查询语句的结果X, 作为另一条select语句的一部分
  - 比如将X作为表使用, 作为条件表达式中的一部分使用

#### 3.1.2 特点

- 子查询必须在小括号中

### 3.2子查询的常见分类

- **where型/on型/having型**子查询: 将子查询的结果作为父查询查询条件的组成部分, 这里的子查询结果一般都进行了聚合函数的处理
- **from型**子查询: 将子查询的结果, 作为父查询的表来使用
- **exists型**子查询: 将单列多行的子查询结果**作为父查询的表达式in(值1,值2, ... , 值n)中**的值来使用

### 3.3 where型/on型/having型子查询

这里的子查询结果一般都进行了聚合函数的处理

#### 3.3.1 语法格式:

```sql
SELECT 要查询列 FROM 表名 WHERE 列名 > (子查询语句);
```

#### 3.3.2 示例

```sql
SELECT *
FROM student
WHERE age > (SELECT AVG(age) FROM student)
;
```

### 3.4 from型子查询

- 将子查询的结果, 作为父查询的表来使用
- **from型子查询中, 必须写子查询的别名**
- **在from型子查询中父查询的能显示的列是由子查询显示的列来决定的**

#### 3.4.1 语法格式

```sql
SELECT 要查询的列 FROM (子查询语句) 子查询的别名 WHERE 条件;
```

#### 3.4.2 示例

```sql
SELECT sname, age
FROM (SELECT sname, age FROM student WHERE age > 20) subStudent
WHERE age < 40
;
```

### 3.5 exists型子查询

- 将单列多行的子查询结果**作为父查询的表达式in(值1,值2, ... , 值n)中**的值来使用
- 子查询语句里不能使用limit限定符

#### 3.5.1 语法格式

```sql
SELECT 要查询的字段 FROM 表 WHERE 列名 IN(子查询语句);
```

#### 3.5.2 示例

```sql
SELECT sname, age 
FROM student
WHERE team_id IN(SELECT team_id FROM team WHERE team_id > 2 && team_id < 5);
```

### 3.6 子查询总结

- 子查询如果查出的是一列的聚合函数结果, 那就作为条件表达式的组成部分使用
  - 子查询如果查出的是一列多行的结果, 那就作为`in(值1,值2, ... , 值n)`中值的的集合来使用
- 子查询如果查处的是多列的结果, 那就作为一张表来使用(子查询要起别名)
- 如果子查询的显示结果要作为表达式in(值1, 值2, ... , 值n)的值来使用, 那么子查询因该显示的是列名只有一个
- 如果子查询的显示结果要作为条件表达式的组成部分, 那么子查询的应该显示一列的聚合函数
- 如果子查询的显示结果要作为表来使用, 那么子查询必须起别名,且子查询应该显示多列

## 四. 数据库设计

### 4.1 数据库三范式(空间最省)

- 概念: 三范式就是设计数据库的规则（范式--normal form）
  - 为了建立冗余较小、结构合理的数据库，设计数据库时必须遵循一定的规则。在关系型数据库中这种规则就称为范式。
  - 范式是符合某一种设计要求的总结，要想设计一个结构合理的关系型数据库，就必须满足一定的范式
  - 满足最低要求的范式是第一范式J(1NF)，在第一范式的基础上进一步满足更多规范的称为第二范式(2NF)，其余范式以此类推，一般说来，数据库只需满足到第三范式(3NF)就行了

#### 4.1.1 第一范式(1NF - First Normal Form)

- 列要有原子性， 即不可分割
- 第一范式是最基本的范式， 数据库表里的列都是单一属性的，不可再分的，如果数据表中每个列都是不可再分的最小数据单元，则满足第一范式
- 示例
  - ![ ](%E7%AC%AC03%E7%AB%A0%20MySQL%E5%A4%9A%E8%A1%A8%E3%80%81%E5%A4%96%E9%94%AE%E3%80%81%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AE%BE%E8%AE%A1.assets/%E7%AC%AC%E4%B8%80%E8%8C%83%E5%BC%8F.png)



#### 4.1.2 第二范式(2NF - Second Normal Form)

- 概念：一张表只能描述一件事

#### 4.1.3 第三范式(3NF - Third Normal Form)

- 消除传递依赖
- 列所代表的信息， 如果能够被推导出来， 那么该列就不应该被设计出来
- 示例
  - ![ ](%E7%AC%AC03%E7%AB%A0%20MySQL%E5%A4%9A%E8%A1%A8%E3%80%81%E5%A4%96%E9%94%AE%E3%80%81%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AE%BE%E8%AE%A1.assets/%E7%AC%AC%E4%BA%8C%E8%8C%83%E5%BC%8F.png)

### 4.2 数据库反三范式(以空间换时间)

- 概念
  - 反范式化指的是通过增加冗余或重复的数据来提高数据库的读性能
  - 浪费存储空间，提高了查询效率，减少了查询时间(以空间换时间)
- 示例
  - 两张表，用户表、订单表，用户表中有字段name，而订单表中也存在字段name
  - ![](%E7%AC%AC03%E7%AB%A0%20MySQL%E5%A4%9A%E8%A1%A8%E3%80%81%E5%A4%96%E9%94%AE%E3%80%81%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AE%BE%E8%AE%A1.assets/%E5%8F%8D%E4%B8%89%E8%8C%83%E5%BC%8F.png)
  - 当需要查询“订单表”所有数据并且只需要“用户表”的name字段时，没有冗余字段就需要去join连接用户表，假设用户表中数据量非常的大，那么这次连接查询就会非常耗费系统的性能
  - 这是冗余的字段就可以派上用场了，有冗余字段我们查一张表就可以了

### 4.3 总结

设计一个关系型数据库，要先遵循三范式规则设计出多个表来节省空间，然后再遵循反三范式规则添加冗余字段来提高查询效率。