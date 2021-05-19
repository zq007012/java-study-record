# JDK安装好后在使用前必须进行的设置

[toc]

## 1. 配置环境变量

### 为什么要配置

方便在控制台编译和运行java程序,不必进入到jdk的安装目录里去运行,可以在任意目录下编译并运行java文件程序.

### 配置步骤

| 1    | ![ ](%E5%AE%89%E8%A3%85%E5%A5%BD%E5%90%8E%E5%9C%A8%E4%BD%BF%E7%94%A8%E5%89%8D%E5%BF%85%E9%A1%BB%E8%BF%9B%E8%A1%8C%E7%9A%84%E8%AE%BE%E7%BD%AE.assets/1.png) |
| ---- | ------------------------------------------------------------ |
| 2    | ![ ](%E5%AE%89%E8%A3%85%E5%A5%BD%E5%90%8E%E5%9C%A8%E4%BD%BF%E7%94%A8%E5%89%8D%E5%BF%85%E9%A1%BB%E8%BF%9B%E8%A1%8C%E7%9A%84%E8%AE%BE%E7%BD%AE.assets/2.png) |
| 3    | ![ ](%E5%AE%89%E8%A3%85%E5%A5%BD%E5%90%8E%E5%9C%A8%E4%BD%BF%E7%94%A8%E5%89%8D%E5%BF%85%E9%A1%BB%E8%BF%9B%E8%A1%8C%E7%9A%84%E8%AE%BE%E7%BD%AE.assets/3.png) |
| 4    | ![ ](%E5%AE%89%E8%A3%85%E5%A5%BD%E5%90%8E%E5%9C%A8%E4%BD%BF%E7%94%A8%E5%89%8D%E5%BF%85%E9%A1%BB%E8%BF%9B%E8%A1%8C%E7%9A%84%E8%AE%BE%E7%BD%AE.assets/4.png)<br /> |
| 5    | ![ ](%E5%AE%89%E8%A3%85%E5%A5%BD%E5%90%8E%E5%9C%A8%E4%BD%BF%E7%94%A8%E5%89%8D%E5%BF%85%E9%A1%BB%E8%BF%9B%E8%A1%8C%E7%9A%84%E8%AE%BE%E7%BD%AE.assets/5.png) |

配置JAVA_HOME是因为一些编程软件需要用到,比如IDEA

==JAVA_HOME的值是JDK的安装目录==

## 2. 配置JRE环境

JDK自JDK11之后就默认不安装JRE模块了,但我们可以通过代码使JDK安装这个模块

__==使用管理员权限打开CMD或者Powershell==__,输入代码`cd jdk的安装目录`,进入jdk的安装目录

``` java
cd "c:\Program Files\Java\jdk-11.0.8"
```

==进入这个目录的原因是为了把jre安装到这个目录中,如果进入的是其他目录,那么就会把jre安装到其他目录中==

输入代码

``` java
jlink.exe --module-path jmods --add-modules java.desktop --output jre
```



## 3. 编译时出现的编码错误

```java
错误: 编码 GBK 的不可映射字符 (0x8D)
```

这是因为要编译的java文件是以utf-8编码保存的，而jdk在中文环境的系统中编译时默认的解码是gbk，所以会出现这这错误。

解决的方法自然是在编译时告诉jdk这个java文件是utf-8编码的，要求jdk以utf-8编码来识别编译这个文件,方法有两个

### 在编译时在中间加一段代码`-enconding UTF-8`

这种方法每次编译的时候都得加

例如: 

```java
javac -encoding UTF-8 HelloWorld.java
```

### 设置`JAVA_TOOL_OPTIONS`环境变量

这种方法只要设置一次就行了

新建一个环境变量,变量名为`JAVA_TOOL_OPTIONS`,变量值为`-Dfile.encoding=UTF-8`

![](%E5%AE%89%E8%A3%85%E5%A5%BD%E5%90%8E%E5%9C%A8%E4%BD%BF%E7%94%A8%E5%89%8D%E5%BF%85%E9%A1%BB%E8%BF%9B%E8%A1%8C%E7%9A%84%E8%AE%BE%E7%BD%AE.assets/One_2020-09-26_185253.png)

