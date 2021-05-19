# 第01章 SQL中四个分类的使用示例

[toc]



## 一. 需求

创建三个数据库`school`    `school_bak`    `school_bak2`

| 学生表         | 高中生表       | 大学生表       | student                      | high_student                       | college_student                       |
| -------------- | -------------- | -------------- | ---------------------------- | ---------------------------------- | ------------------------------------- |
| 学号           | 学号           | 学号           | uid : INT                    | uid : INT                          | uid : INT                             |
| 姓名           | 姓名           | 姓名           | sname : VARCHAR(20)          | sname : VARCHAR(20)                | sname : VARCHAR(20)                   |
| 性别           | 性别           | 性别           | sex  后改成 gender : CHAR(1) | sex  后改成 gender : CHAR(1)       | sex  后改成 gender : CHAR(1)          |
| 年龄           | 年龄           | 年龄           | age : INT, 之后删除          | age : INT, 之后删除                | age : INT, 之后删除                   |
| 生日           | 生日           | 生日           | birthday : DATE              | birthday : DATE                    | birthday : DATE                       |
| 期末成绩       | 期末成绩       | 期末成绩       | score_final : DOUBLE(5, 2)   | score_final : DOUBLE(5, 2)         | score_final : DOUBLE(5, 2)            |
| 新增: 期末及格 | 新增: 期末及格 | 新增: 期末及格 | 新增: pass_final: boolean    | 新增: pass_final: boolean          | 新增: pass_final: boolean             |
| 团队           | 团队           | 团队           | team : varchar(10)           | team : varchar(10)                 | team : varchar(10)                    |
|                | 中考成绩       | 中考成绩       |                              | score_high_entrance : DOUBLE(3, 2) | score_high_entrance : DOUBLE(3, 2)    |
|                |                | 高考成绩       |                              |                                    | score_college_entrance : DOUBLE(3, 2) |
| 期中成绩       | 期中成绩       | 期中成绩       | score_middle : DOUBLE(3, 2)  | score_middle : DOUBLE(3, 2)        | score_middle : DOUBLE(3, 2)           |

## 二. DDL(Data Definition Language)示例

### 1. 数据库级别的操作

```sql
-- 创建数据库
CREATE DATABASE school;
-- 以指定的编码(字符集)创建数据库, 默认的编码是latin1
CREATE DATABASE school_bak CHARACTER SET utf8;
CREATE DATABASE school_bak2;

-- 本服务器里所有的数据库
SHOW DATABASES;

-- 显示指定数据库的信息(主要是编码信息)
SHOW CREATE DATABASE school;
SHOW CREATE DATABASE school_bak;
SHOW CREATE DATABASE school_bak2;

-- 修改数据库的编码
ALTER DATABASE student CHARACTER SET utf8;
SHOW CREATE DATABASE school;

-- 数据库不能改名, 因为会导致数据的丢失

# 删除数据库

SHOW DATABASES;
-- 删除数据库, 如果数据库不存在的话, 会报error
DROP DATABASE school_bak;
SHOW DATABASES;
-- 删除数据库, 如果数据库不存在的话, 会报error
DROP DATABASE school_bak;
SHOW DATABASES;
-- 删除数据库, 如果数据库不存在的话,不会报error, 删除数据库时推荐使用该语法
DROP DATABASE IF EXISTS school_bak;
SHOW DATABASES;
-- 删除数据库, 如果数据库不存在的话,不会报error, 删除数据库时推荐使用该语法
DROP DATABASE IF EXISTS school_bak2;
SHOW DATABASES;
-- 删除数据库, 如果数据库不存在的话,不会报error, 删除数据库时推荐使用该语法
DROP DATABASE IF EXISTS school_bak2;
SHOW DATABASES;
```

### 2. 表级别的操作

```sql
# 表的操作
-- 切换到数据库school中, 进入数据库student中
USE school;
-- 显示正在使用的数据库的名称
SELECT DATABASE();
-- 显示正在使用的数据库里的所有表
SHOW TABLES;

-- 创建表
CREATE TABLE student(uid INT, sname VARCHAR(20), sex CHAR(1), age INT, birthday DATE, score_final DOUBLE(3,2));

-- 在创建表时复制另一个表的结构, 注意只有新建表时才能复制表结构
CREATE TABLE student_high LIKE student;


SHOW TABLES;

# 复制表student的表结构到另一个数据库的表中, 注意只有新建表时才能复制表结构
-- 在创建表时复制另一个表的结构, 复制表student的表结构到另一个数据库的表中, 可以同名
CREATE TABLE school_bak.student LIKE school.student;
-- 在创建表时复制另一个表的结构, 复制表student的表结构到另一个数据库的表中, 也可以不同名
CREATE TABLE school_bak.student_high LIKE school.student;

-- 切换数据库到school_bak中
USE school_bak;
-- 现时本数据库中所有的表
SHOW TABLES;

-- 切换到数据库school中
USE school;
SELECT DATABASE();

-- 查看指定表的表结构
DESC student;

-- 查看指定表的编码(字符集)
SHOW CREATE TABLE student;

SHOW TABLES;
-- 修改表的名称
RENAME TABLE student_high TO student_new;
SHOW TABLES;

RENAME TABLE student_new TO student_high;
SHOW TABLES;

SHOW CREATE TABLE student;
-- 修改表的编码(字符集), 表的编码默认是跟数据库的编码相同的, 允许改成不同的
ALTER TABLE student CHARACTER SET gbk;
SHOW CREATE TABLE student;

-- 删除表
SHOW TABLES;
-- 直接删除, 如果表不存在, 就会报错
DROP TABLE student_high;
-- 先判断表是否存在, 存在就删除, 不存在就什么也不做
DROP TABLE IF EXISTS student_high;
SHOW TABLES;
```

### 3. 列级别的操作

```sql
# 对表中的列进行操作
DESC student_high;
-- 在表中新建一列
ALTER TABLE student ADD pass_final BOOLEAN;
DESC student;
ALTER TABLE student ADD team VARCHAR(10);
DESC student;

-- 修改列类型
ALTER TABLE student MODIFY sname VARCHAR(50);
DESC student;
-- 修改列名称
ALTER TABLE student CHANGE sex gender CHAR(1);
DESC student;

-- 删除指定表中的指定列
DESC student;
ALTER TABLE student DROP birthday;
DESC student;
-- birthdaylie还是要用的, 再加回来
ALTER TABLE student ADD birthday DATE;
```

## 三. DML(Data manipulation language)示例

```sql
#对记录进行增  改  删
-- 插入全部字段
INSERT INTO student VALUES(1, '路飞', '男', 20, '2000.10.01', 600.12, TRUE,'草帽');
SELECT * FROM student;

-- 插入指定字段
-- 指定全部
INSERT INTO student(uid, sname, gender, age, birthday, score_final, pass_final, team) VALUES(2, '索隆', '男', 22, '2002.08.29', 440.75, FALSE, '草帽');
SELECT * FROM student;
-- 指定部分
INSERT INTO student(uid,sname,gender) VALUES(3,'香吉士','女');
SELECT * FROM student;
-- 指定部分的字段的顺序可以跟表中的不同
INSERT INTO student(sname,gender,uid) VALUES('乔巴', '男', 5);
INSERT INTO student(uid,sname, pass_final, age, score_final) VALUES(4, '娜美', FALSE, 22, 680.00);
SELECT * FROM student;

#修改数据
-- 不带条件修改
UPDATE student SET pass_final = TRUE, gender = '男';
SELECT * FROM student;
-- 带条件修改
UPDATE student SET gender = '女', birthday = '1998.08.12' WHERE sname = '娜美' && uid = 4;
SELECT * FROM student;

#删除记录
-- 删除符合指定条件的记录
DELETE FROM student WHERE birthday IS NULL;
SELECT * FROM student;
-- 删除全部记录
-- 效率高, 建议使用
TRUNCATE TABLE student;
SELECT * FROM student;
-- 效率低, 不建议使用
DELETE FROM student;
SELECT * FROM student;
```



## 四. DQL(Data query language)示例

### (0) 数据准备

#### 建表

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

#### 插入数据

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

### (1). 查询结果的显示

```sql
#查询结果的显示
-- 查询到所有记录
-- 显示这些记录的所有字段
SELECT * FROM student;

-- 查询到所有的记录
-- 仅显示sname age和gender字段
SELECT sname , age , gender FROM student;

-- 查询到所有的记录
-- 这些记录的列名以别名显示
SELECT sname AS '姓名', age AS '年龄', gender AS '性别' FROM student;

-- 查询到所有的记录
-- 仅显示一列的值, 且这些值里没有重复相同的值
SELECT DISTINCT gender FROM student;

-- 查询到所有的记录
-- 指定的列显示的是进行指定运算后的值
SELECT sname, score_final / 6 FROM student;
```

### 2. 条件查询

```sql
#比较运算符的使用
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

#逻辑运算符的使用
SELECT * FROM student WHERE gender = '女' && score_final BETWEEN 600 AND 700;
SELECT * FROM student WHERE gender = '女' && score_final BETWEEN 600 AND 700 && age > 30;
SELECT * FROM student WHERE NOT pass_final IS NULL;

#字符串模糊查询
-- 查询名字是两个字的学生信息
SELECT * FROM student WHERE sname LIKE '__';##两个下划线
-- 查询名字含'克'的学生信息
SELECT * FROM student WHERE sname LIKE '%克%';
```

### 3. 查询结果的高级显示



