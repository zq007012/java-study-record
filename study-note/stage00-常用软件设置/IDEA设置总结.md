# IDEA设置总结

[toc]

## 零. 备份IDEA的设置

- 为避免手贱将IDEA的设置删除, 所以不如把IDEA的设置目录放到`OneDriver`下面, 可以随时同步
  - config文件夹下是自定义的配置文件和插件
  - system文件夹下是idea与项目沟通的桥梁, 在编辑项目是会有大量的文件产生与修改
  - 所以只需要把config文件夹移动到OneDriver下就行了

- 打开IDEA软件下的bin目录, 打开`idea.properties`, 在取消以下几行的注释, 并作出自定义修改, 父目录最好要有版本号, 这样可以避免新版本的设置将旧版本的设置覆盖, 因为许多有用的设置都在旧版本里

```properties
idea.config.path=${user.home}/OneDrive/.IntelliJIdea2019.3/config
```

```properties
idea.plugins.path=${idea.config.path}/plugins
```

## 一. Appearance & Behavior

### 1.1 Appearance

字体和字体大小

- [x] Use custom font 我选的是`Microsoft YaHei UI` Size:`13`

#### Accessibility

- [x] Use constrast scrollbars(使用高对比度滚动条)

#### UI Options

- [ ] Automatically position mouse cursor on default button    指针自动移动到默认按钮

Tooltip initial delay(ms): 工具提示信息多久后显示(选600)

#### Window Options

- [x] Show memory indicator    显示内存治时期
- [x] Disable mnemonics in menu    在菜单上显示助忆键

### 1.2 Menus and Toolbars

无

### 1.3 System Settings

**Startup/Shutdown**

- [ ] Reopen last project on startup    IDEA开启时打开上一次关闭时的项目

**Synchronization**

- [x] Save files automatically if application is idle for 15 sec.    应用限制15s后自动保存文件

#### HTTP Proxy

##### Manual proxy configuration(手动设置翻墙代理)

- [x] HTTP    ---    只有选http才能成功连接到翻墙代理

#### Updates

- [ ] Automatically check updates for ...

为...自动检查更新

### 1.4 File Colors

### 1.5 Scopes(范围)

### 1.6 Notifications(通知)

### 1.7 Quick Lists

### 1.8 Path Variables(路径变量)

## 二. KeyMap

| Map                                                | key                   |
| -------------------------------------------------- | --------------------- |
| Refactor(重构)                                     | Alt + R               |
| New(创建新的...)                                   | Alt + N               |
| Generate(生成)                                     | Alt + G               |
| Surround With ...(用...包裹)                       | Alt + T               |
| Hide Active Tool Window(隐藏打开的侧边栏)          | Alt + double click    |
| Reformat code(格式化代码)                          | Alt + L               |
| Completion-basic(代码补全提示)                     | Alt + Space           |
| Completion-Cyclic expand word                      | Shift + Ctrl + /      |
| Comment with block comment(使用多行注释)           | Alt + /               |
| Run(运行)                                          | Alt + A               |
| Fix suggestions(修复代码的建议)                    | Alt + Enter           |
| Optimize import(完善包的导入, 少的加入, 多的删除)  | Alt + O               |
| 在子类中显示所有可以被==重写==的父类方法           | Ctrl + O              |
| Export to text file(导出到文本文件)                | Ctrl + Alt + O        |
| 显示该成员的文档注释                               | Ctrl + Q              |
| 上一个代码中的编译错误(previous highlighted error) | Alt + U(up)           |
| 下一个代码中的编译错误(next highlight error)       | Alt + D(down)         |
| 向下复制一行                                       | Ctrl + D              |
| 剪切本行                                           | Ctrl + X              |
| 删除本行                                           | Ctrl + Y              |
| undo   取消操作                                    | Ctrl + Z              |
| redo   恢复被取消的操作                            | Ctrl + Shift + Z      |
| forward  跳转到光标下一步所在的位置                | ctrl + alt + 向右箭头 |
| back  跳转到光标上一步所在的位置                   | ctrl + alt + 向左箭头 |
| 阿里编码规约扫描                                   | Alt + S               |
| 从父类方法跳转到子类重写的方法                     | Ctrl + Alt + 左键点击 |
| 从子类方法跳转到父类方法                           | Ctrl + U              |

## 三. Editor

### 1. General

#### Appearance

- [x] Show method separators -- 显示方法分割线
- [x] Show whitespaces  

#### Code Completion

- [ ] Match case: ...    匹配大小写

### 2. Font

Size: 20    编辑器默认字体大小

### 3. Color Scheme    颜色计划

`Language Default`→`Comments`    在这里修改注释的颜色

### 4. Code Style

- General
  - Hard wrap at   <u>80</u>   column  --- 编辑区右侧白色竖线的显示位置

- [ ] wrap on typing --- 打字到右侧竖线后自动换行			

#### java

##### Wrapping and Braces

- [ ] Ensure right margin is not exceeded ---  不超过右侧竖线, 在格式化时超过的部分自动换行

#### File and Code Templates    文件和代码模板

##### Files

###### 自定义类文档注释

- 类、接口、枚举、泛型  ---  分别选Class、Interface、Enum、AnnotationType

  - 在`#parse("File Header.java")`下一行添加

  - ```java
    /**
      * TODO
      * @author : ${USER}
      * @date : ${DATE} ${TIME}
      * @version : V1.0
    */
    ```
  

##### other



#### Live Templates

| 术语                  | 说明                                         |
| --------------------- | -------------------------------------------- |
| declaration           | 声明, 类名  方法名  变量名                   |
| statement             | 语句                                         |
| expression            | 表达式, 作用范围是类体, 可以在类体中生成方法 |
| comment               | 注释                                         |
| string                | 字符串                                       |
| other                 | 其他                                         |
| smart type completion | 智能补全                                     |

**iterations组里有很多有关遍历的有用模板**

自定义活动模板

- case -- case语句的模板

  - ```java
    case $CASE$:
        $CONTENT$
        break;
    $END$
    ```

  - 点击按钮`Edit varables` , 所有变量的`expresson`设置为`suggestIndexName()` , 可以是光标自动移动到变量的位置

  - `change context `为 `statement`  `smart type complation`

- swi  ---  swithc语句的模板

  - ```java
    switch ($VAR$) {
    case $CASE1$:
        $CONTENT1$
        break;
    case $CASE2$:
        $CONTENT2$
        break;
    $END$
        default:
            break;
    }
    ```

  - 点击按钮`Edit varables` , 所有变量的`expresson`设置为`suggestIndexName()` , 可以是光标自动移动到变量的位置

  - `change context `为 `statement`  `smart type complation`

- @test -- Junit测试方法的模板

  - ```java
    @Test
    public void $methodName$(){
        $END$
    }
    ```

  - 点击按钮`Edit varables` , 将`methodName`的`expresson`设置为`suggestVariableName()` , 可以是光标自动移动到变量的位置

  - `change context `为 `expression`  `smart type complation`
  
- singleton -- 单例设计模式(懒汉式)

  - ```java
    private static $className$ instance;
    $END$
    private $className$(){
        
    }
        
    public static $className$ getInstance(){
        if(null == instance){
            synchronized($className$.class){
                if(null == instance){
                    instance = new $className$();
                }
            }
        }
        return instance;
    }
    ```

  - 点击按钮`Edit varables` , 将`className`的`expresson`设置为`className()` , 可以自动获取类名

  - `change context `为`declar` `expression`  `smart type complation`  `statement`

- daosingleton -- 单例设计模式(懒汉式)

  - ```java
    private static $className$ instance;
    private QueryRunner queryRunner;
    private DataSource dataSource;
    
    private $className$() throws Exception {
        dataSource = DruidPool.getInstance().getDataSource();
        queryRunner = new QueryRunner(dataSource);
    }
        
    public static $className$ getInstance() throws Exception {
        if(null == instance){
            synchronized($className$.class){
                if(null == instance){
                    instance = new $className$();
                }
            }
        }
        return instance;
    }
    $END$
    ```

  - 点击按钮`Edit varables` , 将`className`的`expresson`设置为`className()` , 可以自动获取类名

  - `change context `为`declar` `expression`  `smart type complation`  `statement`

###   5. Inspection

#### 自动生成serialVersionUID

Java  -  Serialization issues -  Serializable class without "serialVersionUID"  勾选上并将serverity改成Error, 这样在生成一个实现或间接实现Serilizable接口的类时, 如果不在这个类里声明serialVersionUID字段, IDEA就会报错, 按下`Alt + Enter`IDEA就会自动帮你生成serialVersionUID.



## 四. Plugin

| 功能                                                         | 插件名称                      |
| ------------------------------------------------------------ | ----------------------------- |
| 彩虹色的括号---可以以同一种颜色突出显示承兑的括号            | Rainbow Brackets              |
| 阿里巴巴代码规范, 一定要关闭实时检测功能, 否则会与错误提示功能冲突, 导致错误提示缓慢, 通过点击Tools➡阿里编码规约➡关闭实时检测功能来设置 | Alibaba Java Coding Guideline |
| 在对象名上点击`alt+enter`选择生成所有的setter                | GenerateAllSetter             |
| 使用注解生成全参构造器 无参构造器 所有成员变量的getter/setter equals hashcode toString方法 | Lombok                        |
| 对控制台的字体颜色进行个性化设置, 高亮显示某行或者特定的文字, 安装好该插件后, 在控制台上点击右键→点击`Open Grep Console settings`可以对`Grep Console`的显示进行设置 | Grep Console                  |

## 五. `Custom VM Options`相关设置

- 这个设置的文件是`idea64.exe.vmoptions`, 会保存在`user/.IntelliJIdea版本号/config`, 这个位置**是不会**随着[零. 备份IDEA的设置](#零. 备份IDEA的设置)中`config`文件夹的更改而更改
- 还有不要想着修改`IDEA安装目录/bin`下面的`idea64.exe.vmoptions`, 不会起作用的, 甚至可能导致IDEA无法启动

### 5.1 Junit测试时Sanner对象无法从控制台读取数

- 点击`Help`➡`Edit Custom VM Options`, 在最后一行添加以下代码, 然后重启IDEA

- ```properties
  -Deditable.java.test.console=true
  ```

### 5.2 控制台的Tomcat日志信息乱码

`Help`→`Edit Custom VM Options...`, 在最后一行添加,以下代码, 然后重启IDEA

```properties
-Dfile.encoding=UTF-8
```



## 六. 添加单个依赖和依赖库

> - 依赖是针对同一个项目(project)下的两个模块(module)来说的, 
> - 同一个project下模块a与模块b默认是没有联系的, 也就是说模块a不能调用模块b的类,
> - 只有设置相应的依赖后模块a和模块b才能建立联系
>   - 设置了模块a依赖模块b, 模块a才能调用模块b的类, 但模块b不能调用模块a的类
>   - 设置了模块b依赖模块a, 模块b才能调用模块b的类, 但模块a不能调用模块b的类
>   - 只有同时设置了模块a依赖模块b, 模块b依赖模块a, 模块a才能调用模块b的类同时模块b也能调用模块b的类
> - IDEA从项目外添加依赖其实就是把一个jar包或文件夹作为模块导入到项目(project)中, 然后让项目中的模块依赖这个新加入的模块

### 6.1 添加单个依赖

**可以依赖Jar包和模块**

#### 6.1.1 同一个项目下的模块

File➡Project Structure➡Project Setting➡Modules 选择要建立依赖的模块, 点击`Dependencies` , 点击`+`, 点击`3. Module Dependency...`

#### 6.1.2 从项目外导入依赖

1. `File`➡`Project Structure`➡`Project Setting`➡`Modules` , 选择要建立依赖的模块➡Dependencies➡点击`+`  选择`1.Jars or directories...` 找到要依赖Jar包或文件夹后的点击OK, 遇到以下弹框一定要点击`Reuse`
   1. ![](IDEA%E8%AE%BE%E7%BD%AE%E6%80%BB%E7%BB%93.assets/One_2021-05-05_105144-1620263071110.png)

### 6.2 添加依赖库

> 把多个作为依赖的Jar包或者文件夹整合到一个文件夹下, 方便管理

1. `File`➡`Project Structure`➡`Project Setting`➡`Libraries`➡点击`+`   选择Java, 找到作为依赖库的文件夹后点击OK
2. `File`➡`Project Structure`➡`Project Setting`➡`Module`, 选择要添加依赖库的模块, 点击`Dependencies`, 点击`+`, 点击`2. Library...`, 选择依赖库, 点击`OK`

### 6.3 为web应用添加依赖和依赖库

**因为web应用编译后的文件不像java应用那样在编译时会自动将依赖和依赖库放到了out文件夹里, 所以添加依赖时还需进一步设置**

#### 6.3.1 第一步

先使用[6.1 添加单个依赖](#6.1 添加单个依赖)和[6.2 添加依赖库](#6.2 添加依赖库)的方法添加依赖和依赖库

#### 6.3.2 第二步

`Project Stucture`→`Project Setting`→`Artifacts`, 点击需要添加依赖的模块, 然后点击`Output Layout`, 在右侧的`Avaliable Elements`中会显示本项目下的所有模块, 点开这些模块之后就是能添加到`WEB-INFO`中的依赖或者依赖库了, 直接双击就可以把jar包或者模块添加到`WEB-INFO/classes`文件中, 或者把库添加到`WEB-INFO/lib`中

## 七. 侧边栏/又名Sidebar/又名Tool Windows Bar

### 7.1 从侧边栏移除某个bar

在这个bar上点击右键, 选择`remove from sidebar`

### 7.2 在侧边栏上显示某个bar

点击菜单栏中的菜单`View`➡`Tool Windows`➡选择要显示的bar

### 7.3 设置Database侧边栏

- 右侧侧边栏的第二个就是Database侧边栏, 点开后点击`+`→`Data Source`→`MySQL`
- 设置`Name`
- 接下来设置`Host`  `Port`  `User`  `Password`  `Database`
- 点击`Test Connection`测试能否成功连接

## 八. 导入项目或者导入模块

- 遇到下图所示的弹框, 一定要选`Reuse`, 否则会覆盖掉项目或者模块原来的`.iml`配置文件, 导致依赖和一些资源配置丢失
  - ![](IDEA%E8%AE%BE%E7%BD%AE%E6%80%BB%E7%BB%93.assets/One_2021-05-05_105144-1620263071110.png)

