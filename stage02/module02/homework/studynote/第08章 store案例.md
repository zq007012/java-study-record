# 任务八  store案例

## 一. 商城案例表设计

通过对商城项目的部分表关系进行分析,完成数据库表的设计

### 1.1 表关系分析

![](../../../../../%E6%8B%89%E5%8B%BE%E6%95%99%E8%82%B2Java%E5%B0%B1%E4%B8%9A%E6%80%A5%E8%AE%AD%E8%90%A5%E5%AD%A6%E4%B9%A0%E8%B5%84%E6%96%99/%E7%AC%AC02%E9%98%B6%E6%AE%B5/%E6%A8%A1%E5%9D%972%20JDBC&XML/04%20%E4%BB%BB%E5%8A%A1%E5%9B%9B%20(%E7%BB%BC%E5%90%88%E6%A1%88%E4%BE%8B)/01_%E8%AF%BE%E4%BB%B6/%E4%BB%BB%E5%8A%A1%E5%9B%9B%20%20%E7%BB%BC%E5%90%88%E6%A1%88%E4%BE%8B.assets/store%E6%95%B0%E6%8D%AE%E5%BA%93%E4%B8%8B5%E5%BC%A0%E8%A1%A8%E9%97%B4%E7%9A%84%E5%85%B3%E7%B3%BB.bmp) 

### 1.2 建库,建表

1) 创建名为 store的数据库, 对应商城项目

```mysql
create database store;
-- 为授权给用户zq@localhost操作该数据库
grant all on store.* to 'zq'@'localhost';
```

2) 创建用户表

```mysql
CREATE TABLE user (
  user_id varchar(32) PRIMARY KEY,	-- 用户ID
  username varchar(20) , -- 用户名
  password varchar(20) , -- 密码
  telephone varchar(20) , -- 电话
  birthday date , -- 生日
  gender varchar(10) -- 性别
);
```

```mysql
INSERT INTO USER VALUES 
('001','渣渣辉','123456','13511112222','2015-11-04','男'),
('002','药水哥','123456','13533334444','1990-02-01','男'),
('003','大明白','123456','13544445555','2015-11-03','男'),
('004','长海','123456','13566667777','2000-02-01','男'),
('005','乔杉','123456','13588889999','2000-02-01','男');
```

3) 创建订单表

```mysql
CREATE TABLE orders (
  orders_id varchar(32)   PRIMARY KEY, -- 订单id
  ordertime datetime ,	-- 下单时间 
  total double , -- 总金额
  name varchar(20), -- 收货人姓名
  telephone varchar(20) , -- 电话
  address varchar(30) , -- 地址
  state int(11) ,  -- 订单状态
  user_id_foreign varchar(32), -- 外键字段 对应用户表id
  FOREIGN KEY(user_id_foreign) REFERENCES user(user_id)
);
```

```mysql 
-- 插入一条订单数据
INSERT INTO orders 
VALUES('order001','2019-10-11',5500,'乔杉','15512342345','皇家洗浴',0,'001');
```

4) 创建商品分类表

```mysql
CREATE TABLE category (
  category_id varchar(32) PRIMARY KEY,
  cname varchar(20)
); 
```

```mysql
INSERT INTO `category` VALUES ('1','手机数码'),('2','电脑办公'),('3','运动鞋服'),('4','图书音像');
```

5) 创建商品表

```mysql
CREATE TABLE product (
  product_id varchar(32)  PRIMARY KEY,	-- 商品id
  pname varchar(50) , -- 商品名称 
  price double, -- 商品价格
  pdesc varchar(255), -- 商品描述
  pflag int(11) , -- 商品状态 1 上架 ,0 下架
  category_id_foreign varchar(32) , -- 外键对应 分类表id
  FOREIGN KEY (category_id_foreign) REFERENCES category(category_id)
);
```

```mysql
INSERT INTO `product` VALUES 
('1','小米6',2200,'小米 移动联通电信4G手机 双卡双待',0,'1'),
('2','华为Mate9',2599,'华为 双卡双待 高清大屏',0,'1'),
('3','OPPO11',3000,'移动联通 双4G手机',0,'1'),
('4','华为荣耀',1499,'3GB内存标准版 黑色 移动4G手机',0,'1'),
('5','华硕台式电脑',5000,'爆款直降，满千减百',0,'2'),
('6','MacBook',6688,'128GB 闪存',0,'2'),
('7','ThinkPad',4199,'轻薄系列1)',0,'2'),
('8','联想小新',4499,'14英寸超薄笔记本电脑',0,'2'),
('9','李宁音速6',500,'实战篮球鞋',0,'3'),
('10','AJ11',3300,'乔丹实战系列',0,'3'),
('11','AJ1',5800,'精神小伙系列',0,'3');
```

5) 订单项表 (中间表)

```mysql
-- 订单项表
CREATE TABLE orderitem (
  itemid VARCHAR(32) PRIMARY KEY, -- 订单项ID
  product_id_foreign VARCHAR(32),  -- 外键 对应商品表 id
  orders_id_foreign VARCHAR(32), -- 外键 对应订单表 id
  FOREIGN KEY (product_id_foreign) REFERENCES product(product_id),
  FOREIGN KEY (orders_id_foreign) REFERENCES orders (orders_id)
);
```

```mysql
-- 向中间表中插入两条数据
INSERT INTO orderitem VALUES('item001','1','order001');
INSERT INTO orderitem VALUES('item002','11','order001');
```

## 二. 环境搭建

### 2.1 项目结构

```
com.zq.app 测试包 用于对DAO代码进行测试
com.zq.dao dao包  数据访问层,包含所有对数据库的相关操作的类
com.za.entity 实体包 保存根据数据库表 对应创建的JavaBean类
导入zq-utils依赖模块, 修改resources文件夹中的数据库连接池配置
```

### 2.2 导入所需Jar包

```
我们只需要导入ZqLibararies依赖仓库到项目中就可以了
```

### 2.3 配置IDEA上的Database侧边栏

- Host : localhost
- User : zq
- password : 111111
- Database :  store
- URL : jdbc:mysql://localhost:3306/store?characterEncoding=utf-8

## 三. 初步创建Entity类和Dao类的步骤

### 3.0 Entity类的特点

- JavaBean组件被称为Entity类
- 一个Entity类对应数据库中的一张表
- Entity类的名称 = 实体表的名称
- Entity类的一个对象 = 表的一行记录
- Entity类的属性 = 实体表的字段

### 3.1 利用IDEA自动在entity包中创建所有的javabean组件

1. 打开侧边栏`Database`, 选择数据库`store`中所有的表, 右键 选择`Scripted Extensions`, 再选择`Generate POJOs.groovy`, 就可以批量创建所有的javabean组件了

### 3.2 初步完善Entity类

1. 所有Entity类实现Serializable接口, 生成UUID
2. 所有的Entity都创建一个空参构造

### 3.3 初步创建Dao类

> 从SqlYog的获取所有表间的关系图(通过在SqlYog `文件`  `新架构设计器` `把所有表拖到架构设计其中`获取),
>
> 图片中的`1`即为1, 图片中的`∞`即为多

1. Dao最好使用单例设计模式, 并且要封装一个数据库连接池DataSource对象, 生成这个对象的getter和setter
2. 一的Dao要创建一个可以获取一的实体对象的方法, 参数是多的实体对象, 本质上是多的外键值
3. 多的Dao要创建一个可以获取多的实体对象集合的方法, 参数是一的实体对象, 本质上是一的主键值

### 3.4 完善Entity类

#### 3.4.1 一对多关系

1. 一的实体要有多的实体对象的集合,  这个集合通过多的Dao方法获取, 这个方法的参数是this
2. 多的实体要有一的实体对象,  这个对象通过一的Dao方法获取, 这个方法的参数是this

#### 3.4.2 多对多关系

1. 多的实体要有**中间表实体对象的集合,** 这个集合通过中间表的Dao方法获取, 这个Dao方法的参数是this
2. 多的实体要有另一个多的实体对象的集合, 这个集合通过中间表的实体对象和另一个多的Dao方法获得, 这个Dao方法的参数是上述**中间表实体对象的集合**的每个元素
3. 中间表要有两个多的对象, 这两个对象分给通过对应的Dao的方法获取, 方法的参数是this

### 3.5 按需求完成Entity类和Dao类

按需求完成Entity类和Dao类其他的部分

## 四. 实现以下需求

### 4.0 store数据库所有表间的关系图

![store数据库所有表间的关系](../../../../../%E6%8B%89%E5%8B%BE%E6%95%99%E8%82%B2Java%E5%B0%B1%E4%B8%9A%E6%80%A5%E8%AE%AD%E8%90%A5%E5%AD%A6%E4%B9%A0%E8%B5%84%E6%96%99/%E7%AC%AC02%E9%98%B6%E6%AE%B5/%E6%A8%A1%E5%9D%972%20JDBC&XML/04%20%E4%BB%BB%E5%8A%A1%E5%9B%9B%20(%E7%BB%BC%E5%90%88%E6%A1%88%E4%BE%8B)/01_%E8%AF%BE%E4%BB%B6/%E4%BB%BB%E5%8A%A1%E5%9B%9B%20%20%E7%BB%BC%E5%90%88%E6%A1%88%E4%BE%8B.assets/store%E6%95%B0%E6%8D%AE%E5%BA%93%E4%B8%8B5%E5%BC%A0%E8%A1%A8%E9%97%B4%E7%9A%84%E5%85%B3%E7%B3%BB.bmp)

### 4.1 需求

1. 编写一个注册用户的方法,接收的参数是一个User对象
2. 编写一个 用户登录的方法,接收的参数是 用户名 和密码, 返回值是User对象
3. 根据商品ID 获取商品名称 ,商品价格 以及商品所属分类的名称
4. 根据分类ID 获取商品分类信息
5. 查询指定分类ID 下的商品个数
6. 查询指定分类ID 下的所有商品信息
7. 获取 uid为 001 的用户的所有订单信息
8. 获取订单编号为 order001的订单中的所有商品信息














































