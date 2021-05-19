# 第16章 异常机制和File类

[toc]

## 一. 异常机制(重点)

### 1. 基本概念

![ ](%E7%AC%AC16%E7%AB%A0%20%E5%BC%82%E5%B8%B8%E6%9C%BA%E5%88%B6%E5%92%8CFile%E7%B1%BB.assets/Throwable%E7%B1%BB.png)

- 异常就是"不正常"的含义, 在Java语言中主要指程序运行过程中发生的不正常情况导致程序中途中断
- `java.lang.Throwable`类是Java语言中错误类`Error`类和异常类`Exception`类的超类
  - 程序未运行到最后就中途被打断强行结束了
- 其中`Error`类主要用于描述Java虚拟机无法解决的严重错误, 通常无法编码解决, 如: JVM挂掉了
  - 程序的载体, 也就是JVM发生的异常, 比如系统崩溃, 虚拟机错误, 内存空间不足, 
  - 开发者对这种原因导致的程序中断无能为力
- 其中`Exception`类主要用于描述因编程错误或偶然外在因素导致的轻微错误, 通常可以通过编码解决. 

### 2. `Exception`类的分类

![ ](%E7%AC%AC16%E7%AB%A0%20%E5%BC%82%E5%B8%B8%E6%9C%BA%E5%88%B6%E5%92%8CFile%E7%B1%BB.assets/Exception%E7%B1%BB.png)

#### ① 非检测性异常

- 这里的检测是指编译时对代码进行检测, 来判断代码运行过程中是否可能会产生异常
- 非检测性异常是指检测时不能被发现的异常
- 非检测性异常是指在程序运行阶段才可能发生的异常, 这种异常大多是程序运行时第一次发生异常导致程序中断了了, 开发者才知道有这种异常, 然后针对这种异常进行处理

![ ](%E7%AC%AC16%E7%AB%A0%20%E5%BC%82%E5%B8%B8%E6%9C%BA%E5%88%B6%E5%92%8CFile%E7%B1%BB.assets/%E9%9D%9E%E6%A3%80%E6%B5%8B%E6%80%A7%E5%BC%82%E5%B8%B8.png)

- 0做除数    ---    ArithmeticException
- 用大于或等于数组长度的数字获取数组中的元素 --- ArrrayIndexOutOfBoundException
- 用值为null的变量调用方法    ---    NullPointException
- 将一个父类的实例强行转换成子类类型    ---    ClassCastException
- 强行将不只是由数字组成的字符串转换成数字    ---    NumberFormatException

#### ② 检测性异常

- 这里的检测是指编译时对代码进行检测, 来判断代码运行过程中是否可能会产生异常
- 检测性异常就是检测时可以被发现的异常
- 这种异常在编译时就可以被发现,

![ ](%E7%AC%AC16%E7%AB%A0%20%E5%BC%82%E5%B8%B8%E6%9C%BA%E5%88%B6%E5%92%8CFile%E7%B1%BB.assets/%E6%A3%80%E6%B5%8B%E6%80%A7%E5%BC%82%E5%B8%B8.png)

### 3. `Exception`类的作用

`Exception`类主要用于描述因编程错误或偶然外在因素导致的轻微错误, 通常可以通过编码解决, 避免程序中断 , ==解决这种异常的方式就是在能导致异常发生的环境下, 不执行可能发生异常的代码==, 如: 在除法表达式中0作为除数, 那就用if语句, 当0作为除数的情况发生时, 不执行这个表达式 , 这样就避免了异常的产生, 使代码顺利运行到结束了. 

- 编程中所说的异常都是指这种异常
- 这种异常可以通过  if语句,   控制    可能会产生异常的代码    在处于会发生异常的环境时   不会被执行. 异常语句不被执行, 程序也就不会中断了
- 或者使用try  catch语句, 将可能发生异常的代码放到try语句里
  - 当异常没有发生时, catch语句里的内容就不会被执行;
  - 有异常发生时, catch语句就会捕获到这个异常(即`Exception`对象), 同时将代码的执行回退到异常代码被执行之前(try语句里面的异常代码以及异常代码后面的代码都不会被执行了), 然后执行catch语句里面的内容, 之后再执行其他的代码 , 这样程序就不会被中断了

### 4. `Exception`类的常用方法

| 功能                  | 方法                   |
| --------------------- | ---------------------- |
| [示例代码](#示例代码) | void printStackTrace() |
| 异常的提示信息        | String getMessage()    |

###### 示例代码

```java
Exception in thread "main" com.lagou.zq.AgeException: 年龄输入不合法//异常类型, 以及具体的异常提示信息, String getMessage()获取的就是这个信息
	at com.zq.study.code.task1.Student.setAge(Student.java:29)//最初的异常抛出位置
	at com.zq.study.code.task1.Student.<init>(Student.java:12)//调用者
	at com.zq.CustomExceptionTest.main(CustomExceptionTest.java:10)//最外层的调用者, try catch语句最好在这里使用
```

### 5. 使用if避免异常代码执行

- 在以后的开发中尽量使用if条件判断==避免==异常的发生

  - ```java
    public static int divide(int divisor, int dividend ){
        int result = 0 ;
        if(0 != divisor){//使用if语句,避免在会发生异常的环境下执行可能发生异常的代码
            result = dividend / divisor;
        }
        
        return result;
    }
    ```

- 但是过多的if条件判断会导致程序的代码加长、臃肿、可读性差

### .6 try  catch语句捕获异常

使用try  catch语句, 将可能发生异常的代码放到try语句里

- 当异常没有发生时, try语句里的代码会正常执行, catch语句里的内容不会被执行;
- 有异常发生时, catch语句就会==捕获==到这个异常(即`Exception`对象), 同时==将代码的执行回退到异常代码被执行之前==(try语句里面的异常代码以及异常代码后面的代码都不会被执行了), 然后执行catch语句里面的内容, 之后再执行其他的代码 , 这样程序就不会被中断了

#### (1) 语法格式

```java
try{
    可能发生异常的代码;
}catch(异常类型 引用变量名){
    针对该类异常的处理代码;
}
...//可以有多个catch语句
 finally{
    编写无论是否发生异常都要执行的代码    
}
```

- 当有多个catch语句时, 小异常[^注1]的catch语句  必须在  大异常[^注2]的catch语句的上面 , 否则会导致语法错误
  - 这是因为如果大异常[^注1]在上面的话由于多态的原因, 大异常的catch语句也会抓取小异常[^注1],那放在下面的小异常[^注1]catch语句就派不上任何用场了.
  - 为什么不直接只用一个catch(Exception e)语句, 这样就可以一个catch语句抓取所有异常了
    - 这样写是可行的, 但会导致可读性差, 还有如果try语句里有着会导致不同异常发生的多行代码, 那不同的catch语句可以针对对应的异常做出相应处理 , 只有一个catch(Excetion e)语句是做不到如此灵活的

[^注1]: 即子类类型的异常
[^注2]:即父类类型的异常

#### (2) 执行顺序

```java
public static String tryCatchTest(int divisor, int dividend) {
        System.out.println("这里是不会产生异常的语句1");
        System.out.println("这里是不会产生异常的语句2");
        try{
            System.out.println("Above: 我是try语句里的语句, 在异常语句的上面");
            int result = dividend / divisor;//可能产生异常的语句
            System.out.println("Below: 我是try语句里的语句, 在异常语句的下面");
        }catch (ArithmeticException e){
            System.out.println("我是catch语句里的语句, 用来执行异常发生时的处理措施, " +
                    "执行到我就说明有异常产生, 我是" +
                    "在异常发生时才会执行的语句, 执行了我就可以避免程序在异常发生时中断了" +
                    ",保证程序正常运行到结束");
        }
        System.out.println("这里是不会产生异常的语句3");
        System.out.println("这里是不会产生异常的语句4");
        return "执行了方法的return语句";
}
```

- 未发生异常

  - ```java
    System.out.println(tryCatchTest(1, 10));
    /*
    这里是不会产生异常的语句1
    这里是不会产生异常的语句2
    Above: 我是try语句里的语句, 在异常语句的上面
    Below: 我是try语句里的语句, 在异常语句的下面
    这里是不会产生异常的语句3
    这里是不会产生异常的语句4
    执行了方法的return语句
    */
    ```

- 产生异常

  - ```java
    System.out.println(tryCatchTest(0, 10));
    /*
    这里是不会产生异常的语句1
    这里是不会产生异常的语句2
    Above: 我是try语句里的语句, 在异常语句的上面
    我是catch语句里的语句, 用来执行异常发生时的处理措施, 执行到我就说明有异常产生, 我是在异常发生时才会执行的语句, 执行了我就可以避免程序在异常发生时中断了,保证程序正常运行到结束
    这里是不会产生异常的语句3
    这里是不会产生异常的语句4
    执行了方法的return语句
    ```

#### (3) 有finally语句时的执行顺序

- 异常有无发生, finally语句都会被执行

```java
public static String tryCatchTest(int divisor, int dividend) {
        System.out.println("这里是不会产生异常的语句1");
        System.out.println("这里是不会产生异常的语句2");
        try{
            System.out.println("Above: 我是try语句里的语句, 在异常语句的上面");
            int result = dividend / divisor;//可能产生异常的语句
            System.out.println("Below: 我是try语句里的语句, 在异常语句的下面");
        }catch (ArithmeticException e){
            System.out.println("我是catch语句里的语句, 用来执行异常发生时的处理措施, " +
                    "执行到我就说明有异常产生, 我是" +
                    "在异常发生时才会执行的语句, 执行了我就可以避免程序在异常发生时中断了" +
                    ",保证程序正常运行到结束");
        }finally {
            System.out.println("我是finally语句里的语句");
        }
        System.out.println("这里是不会产生异常的语句3");
        System.out.println("这里是不会产生异常的语句4");
        return "执行了方法的return语句";
    }
```

- 无异常发生

  - ```java
    System.out.println(tryCatchTest(1, 10));
    /*
    这里是不会产生异常的语句1
    这里是不会产生异常的语句2
    Above: 我是try语句里的语句, 在异常语句的上面
    Below: 我是try语句里的语句, 在异常语句的下面
    我是finally语句里的语句
    这里是不会产生异常的语句3
    这里是不会产生异常的语句4
    执行了方法的return语句
    */
    ```

- 有异常发生

  - ```java
    System.out.println(tryCatchTest(0, 10));
    /*
    这里是不会产生异常的语句1
    这里是不会产生异常的语句2
    Above: 我是try语句里的语句, 在异常语句的上面
    我是catch语句里的语句, 用来执行异常发生时的处理措施, 执行到我就说明有异常产生, 我是在异常发生时才会执行的语句, 执行了我就可以避免程序在异常发生时中断了,保证程序正常运行到结束
    我是finally语句里的语句
    这里是不会产生异常的语句3
    这里是不会产生异常的语句4
    执行了方法的return语句
    */
    ```

##### finally语句的必须执行特性

- 如果方法在中断或结束时还未轮到finally语句执行, 那么就必须在程序中断或结束前运行完finally语句, 当运行完finally语句里面的代码后, 才运行方法进行中断或结束语句

```java
public static String tryCatchTest(int divisor, int dividend) {
        System.out.println("这里是不会产生异常的语句1");
        try{
            System.out.println("Above: 我是try语句里的语句, 在异常语句的上面");
            int result = dividend / divisor;//可能产生异常的语句
            System.out.println("Below: 我是try语句里的语句, 在异常语句的下面");
            return "我是try语句里的return语句";//未发生异常时, 会执行try语句里的return语句, 此时还未轮到finally语句执行
        }catch (ArithmeticException e){
            return "我是catich语句里的一个return语句";//发生异常时,会执行catch语句里的return语句, 此时还未轮到finally语句执行
        }finally {
            System.out.println("我是finally语句里的语句");
        }
    }
```

- 未发生异常的结果

  - ```java
    System.out.println(tryCatchTest(1, 10));
    /*
    这里是不会产生异常的语句1
    Above: 我是try语句里的语句, 在异常语句的上面
    Below: 我是try语句里的语句, 在异常语句的下面
    我是finally语句里的语句
    我是try语句里的return语句
    */
    ```

- 发生异常的结果

  - ```java
    System.out.println(tryCatchTest(0, 10));
    /*这里是不会产生异常的语句1
    Above: 我是try语句里的语句, 在异常语句的上面
    我是finally语句里的语句
    我是catich语句里的一个return语句*/
    ```

- 在轮到finally语句执行前, 如果有return语句执行了, 那就会在返回 返回值这一步暂停(==注意这里的返回值已经确定了, 只是正在等待被返回而已)==, 然后执行finally语句里的代码, 当finally语句里的代码执行结束后, 返回 返回值的暂停状态就会解除, 已经确定的返回值就会被返回. 

  - 如果finally语句里有return语句的话, 那么在执行完这个return语句后, 方法也就执行完毕了, 被跳过的返回 返回值这一步不需要执行了.
  
  - ```java
    public static int showFinallyResult(){
        try{
            return 1;
        }catch(Exceptiont e){
            return 2;
        }finally{
            return 3;
        }
    }
    
    //运行结果
    //3
    //返回的是finally语句里的运行结果
    ```
  
  - ```java
    public static int showFinallyResult(){
        int x = 1;
        try{
            return ++x;
        }catch(Exceptiont e){
            ++x;
        }finally{
            ++x;
        }
    }
    
    //运行结果
    //2
    //try语句中 返回 2这个返回值这一步会进入暂停状态(这里返回的是2这个常量,而不是x这个变量), 当执行完finally语句里的内容后, 暂停状态会解除, 2作为返回值会被返回
    ```
  
  - ```java
    public static int showFinallyResult(){
        int x = 1;
        try{
            return ++x;
        }catch(Exceptiont e){
        }finally{
            return ++x;
        }
    }
    //运行结果
    //2
    //try语句中 返回 2这个返回值这一步会进入暂停状态(这里返回的是2这个常量,而不是x这个变量), 此时变量x的值是2, 当执行完finally语句里的++x后, x的值为3, 3作为返回值被返回后, 方法执行结束, try语句里的暂停下来的步骤也就不需要执行了, 所以最终会返回3
    ```

### 7. 异常的抛出

- 在某些特殊情况下有些异常在本方法内不能处理或者不便处理, 就可以将该异常抛给该方法的调用者, 让调用者决定怎么解决

- 异常的抛出分两类
  - 方法体内抛出 --- 抓取的或生成的异常对象被抛给了方法名 , 使用的是`throw`关键字, 后面跟的是个异常对象, 需要用到if语句或者try  catch语句, 在会发生异常的环境下把将要发生的异常抛给方法名

    - 这种方式一般用于抛出本方法内自定义的语句引发的异常

    - 语法格式

    - ```java
      try{
      	...
      }catch(Exception e){
          throw e;
      }
      ```

  - 方法名上抛出 --- 使用的是throws关键字, 后面跟的是异常类型, 多个异常用`,`隔开, 小异常[^注1]类型在前,大异常[^注2]类型在后

    - 这种方式抛出的异常多是本方法内调用了其他会抛出异常的方法

    - 语法格式

    - ```java
      public void show() throws Exception{
          ...
      }
      ```

- 父类抛出异常的方法在重写时的原则

  - 子类重写的方法可以不抛出异常, 也可以抛出异常, 但只能抛出父类抛出的异常的相同类型或者子类类型
  - 根据上一条规则, 可得出结论: 若父类方法没有抛出异常, 那么子类重写的方法就不能抛出异常
    - 所以这类重写的方法遇到异常时, 只能使用try catch语句在自己内部处理解决, 不能抛出

### 8. 自定义的异常

#### (1) 使用场景

比如`Person`类的年龄字段age, 人类的年龄也就是在0~150的范围, 如果创建对象时传进来参数在这个范围之外时想要对调用者做出提醒, 要求调用者对传入这种参数时做出相应的处理, 那就可以在这种情况下new一个自定义的对象并抛出去, 如果调用者不用try catch做出处理措施, 就会提示这里有异常

#### (2) 实现流程

1. 自定义xxxException异常类继承Exception类或者其子类

2. 提供两个版本呢的构造方法, 
   
   - 一个是无参构造方法, 无参构造方法第一行写`super()`
   - 一个是字符串作为参数的构造方法, 方法第一行写`super(message)`
   
   ```java
   //示例代码
   //这是一个提示年龄不合理的代码
   public class AgeException extends Exception{
       public AgeException(){
           super();
       }
       public AgeException(String message){
           super(message);
       }
   }
   ```
   
   

#### (3) 异常的产生

   `throw new 异常类型(实参)`

   ```java
   //示例代码
   public Person{
       private String name;
       private int age;
       public Person(){}
       public Person(String name, int age) throws AgeException{
           setName(name);
           setAge(age);
       }
       public void setAge(int age) throws AgeException{
           if(age < 0 || age > 150){
               //------+++++++===========在这里==========++++++-------------
               throw new AgeException("不接受年龄小于0大于150的妖怪")
           }
           this.age = age;
       }
       
       public void setName(String name){
           this.name = name;
       }
       
       public String getName(){
           return name;
       }
       public int getAge(){
           return age;
       }
   }
   ```


## 二. File类(重点)

### 1. 基本概念

- java.io.File类主要用于描述文件或露露路径的抽象表示信息, 可以获取文件或目录的特征信息, 如: 名字(全称), 大小 , 最后一次的修改日期
- 创建一个File对象就是将这个对象通过路径[^注3]跟硬盘上的一个文件或文件夹相关联
- `/`或`\`是路径的分隔符, 由于Java语言中`\`是转义字符, 所以路径中的`\`要用`\\`表示
- `.`是文件后缀名的标记, 如果创建文件时文件名里没有`.`则这个文件会创建失败

[^注3]: 路径可以是一个文件夹的路径, 也可以是一个文件的路径

### 2. 常用构造方法

| 功能                                             | 方法                              |
| ------------------------------------------------ | --------------------------------- |
| 根据路径将本对象跟硬盘上对应的文件或文件夹相关联 | File(String pathname)             |
|                                                  | File(String parent, String child) |
|                                                  | File(File parent, String child)   |

### 3. 常用的静态Field(成员变量/字段)

| 功能                   | Field                     |
| ---------------------- | ------------------------- |
| char类型的路径分隔符   | static char separatorChar |
| String类型的路径分隔符 | static String separator   |

### 4. 常用的方法

| 功能                               | 方法             |
| ---------------------------------- | ---------------- |
| 判断对象关联的文件或文件夹是否存在 | boolean exsist() |

#### (1) 不存在

不存在的话就将其创建出来

##### a. 文件夹

| 功能                                                         | 方法             |
| ------------------------------------------------------------ | ---------------- |
| 创建本目录(即文件夹), 如果上级目录不存在, 就会创建失败       | boolean mkdir()  |
| 创建本目录(即文件夹), 如果上级目录或者多层上级目录不存在, 就先将这些上级目录创建出来, 再创建本目录 | boolean mddirs() |

##### b.文件

| 功能                                                         | 方法                    |
| ------------------------------------------------------------ | ----------------------- |
| 创建本文件, 路径中的文件名必须有`.`和后缀名才能创建成功, 并且文件的上级目录存在才能创建成功 | boolean createNewFile() |
| 获取父目录, 创建文件前应该先判断一下父目录是否存在, 如果不存在,就先把父目录创建出来 | File getParentFile()    |

#### (2) 存在

| 功能(判断)                                                   | 方法                                      |
| ------------------------------------------------------------ | ----------------------------------------- |
| 判断本对象关联的是否是一个文件夹                             | boolean isDirectory()                     |
| 判断本对象关联的是否是一个文件                               | boolean isFile()                          |
|                                                              |                                           |
| **功能(获取信息)**                                           | **方法**                                  |
| 获取名字                                                     | String getName()                          |
| 获取绝对路径                                                 | String getAbsolutePath()                  |
| 获取文件最后一次修改的时间(距离标准时间的毫秒值)             | long lastModified()                       |
| 获取文件或文件夹的长度(即存储大小)[^注4]                     | long length()                             |
| 获取该目录下所有内容的File对象                               | File[] listFiles()                        |
| 获取该目录下符合筛选器规则的所有内容的File对象               | File[] listFiles(FileFilter filter)[^注5] |
|                                                              |                                           |
| **功能(删除文件或文件夹)**                                   | 方法                                      |
| 删除本对象关联的文件或文件夹, 如果关联的文件夹有子文件或子文件夹则会删除失败 | boolean delete()                          |
| **功能(重命名文件)**                                         | **方法**                                  |
| 如果dest已存在, 则会重命名失败; 如果dest的parentFile不存在, 则会重命名失败; 因为本质上只是修改了路径名, 没有发生复制文件的行为, 所以不能重命名到不同的硬盘分区上; 重命名后调用该方法的对象关联的路径不会发生改变. | boolean renameTo(File dest)               |

[^注4]: 文件夹是不占用存储空间的, 所以以该方法获得的文件夹的长度总是为0 ; 一般所说的文件夹的大小是指该目录下所有子文件的长度之和, 这种文件夹的长度需要自己算
[^注5]:FileFilter是一个文件筛选器的接口, 只有一个boolean accept(File pathname)方法需要重写, 若accept方法返回值为true, 那么pathname文件会被放到File[]数组里, 若返回值为false, 就不会放入

筛选器示例代码:

```java
//只筛选出文件夹
FileFilter filter = new FileFilter(){
    @Override
    public boolean accept(File pathname){
        return pathname.isDirectory();
    }
}

//只筛选出文件
FileFilter filter = (File pathname) -> {//lambda表达式
    return pathname.isFile();
}
```



#### 获取文件夹的大小

==注意分析第22行注释掉的代码==

```java
/**
     * 这是一个获取文件夹大小的方法, 其实就是文件夹内所有文件,包括子文件夹内的所有文件的大小之和
     * @param dir
     * @param length  length是用来记录长度的之和,调用该方法时length的值只能是0, 否则获取到的文件夹的大小是错的
     * @return 返回的是文件夹的大小
     * @throws FileNotFoundException
     * @throws RuntimeException
     */
    private static long dirLength(File dir, long length) throws FileNotFoundException,IOException {
        if (dir.exists() && dir.isDirectory()){
            File[] files = dir.listFiles();
            /**
             * 思路就是获取本文件夹下所有子文件和子文件夹的长度之和
             */
            for (File file :
                    files) {
                if (file.isFile()){
                    length += file.length();
                }
				//获取子文件夹大小需要用到递归
                if (file.isDirectory()){
                    //length = dirLength(file, length);//length是已经遍历过的子文件和子文件的长度之和, 如果把length作为参数传进去,那么获得的返回值就是这个子文件夹跟已经遍历过的子文件和子文件夹的长度之和.
                    //length += dirLength(file, length);是错误的
                    //所以递归中为了避免出错, 除了参加递归的参数可以改变, 其他参数的知不可以改变
                    length += dirLength(file, 0);//dirLeng(file,0)获得的是file文件夹的长度                
                }
            }
            return length;
        }else if (dir.exists() && dir.isFile()){
            throw new IOException(dir.getAbsolutePath() + "是个文件, 不是个文件夹");
        }else{
            throw new FileNotFoundException("找不到路径为" + dir.getAbsolutePath() + "的文件夹");
        }

    }
```

#### 删除一个文件夹

```java
private static void dirDelete(File dir) {
        File[] files = dir.listFiles();
        for (File file :
                files) {
            if (file.isFile()) {
                file.delete();
                //System.out.println(file.getName() + "被删除了");
            }
            if (file.isDirectory()){
                dirDelete(file);
            }
        }
        dir.delete();
        //System.out.println(dir.getName() + "被删除了");
    }
```

### 2.5 `""`  `"/"`  和`"../"`表示的路径

- `""`表示的是相对路径, 即本文件的父目录
- `"../"`表示的是本文件相对路径的父目录
- 路径`"/"`表示的是根目录, 即本文件所在分区的根目录

#### 2.5.1 路径`""`示例

```java
       File file1 = new File("");
        System.out.println(file1.getAbsoluteFile());
        /*
        D:\GitWorkSpaces\java_studynote\stage02\module02
         */
		//扩展使用
        File file2 = new File("com\\zq");
        System.out.println(file2.getAbsoluteFile());
        /*
        D:\GitWorkSpaces\java_studynote\stage02\module02\com\zq
         */
```

#### 2.5.2 路径`"/"`示例

```java
        File file1 = new File("\\");
        System.out.println(file1.getAbsoluteFile());
        /*
        D:\
         */

        //扩展使用
        File file2 = new File("\\com\\zq");
        System.out.println(file2.getAbsoluteFile());
        /*
        D:\com\zq
         */
```

