# 第0X章 SQL学习中遇到的问题

### 1. Incorrect string value

#### 错误提示

```sql
Incorrect string value: '\xF0\x9F...' for column 'XXX' at row 1
```

#### 原因

原因是UTF-8编码有可能是两个、三个、四个字节。Emoji表情或者某些特殊字符是4个字节，而Mysql的utf8编码最多3个字节，所以数据插不进去。

#### 解决办法

解决办法是升级MySQL到最新版本或者修改MySQL的设置, 以便其支持utf8编码的4个字节

##### 1. 升级MySQL到最新版本

建议使用本方法, 一劳永逸

##### 2. 修改MySQL的设置

1. 在mysql的安装目录下找到my.ini,作如下修改： 

   1. ```sql
      [mysqld]
      
      character-set-server=utf8mb4
      
      [mysql]
      
      default-character-set=utf8mb4
      ```

   2. 修改后重启Mysql服务或者重启电脑

2. 将已经建好的表也转换成utf8mb4

   1. ```sql
      alter table 表名 convert to character set utf8mb4 collate utf8mb4_bin; 
      ```





