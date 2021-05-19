# 第05章 ECMAScript6

[toc]

## 一. ECMAScript6简介

ECMAScript6.0(以下简称ES6)是JavaScript语言的下一代标准, 已经在2015年6月正式发布了.它的目标, 是使得JavaScript语言可以用来编写复杂的大型应用程序,成为企业级开发语言.

### 1.1 ECMASript和JavaScript的关系

- 1996年11月, JavaScript的创造者Netscape公司, 决定将JavaScript提交给标准化组织ECMA, 希望这种语言能够成为国际标准.
- ECMA发布262号标准文件(ECMA-262)的第一版, 规定了浏览器脚本语言的标准, 并将这种语言成为ECMAScript, 这个版本就是1.0版.
- 因此, ES和JS的关系是, ES是JS的规格, JS是ES的实现. 这有点类似Java语言中的类与对象的关系, ES就像是个类, 而JS就是根据这个类创建出来的对象

### 1.2 ES6与ECMAScript2015的关系

- 2011年, ECMAScript 5.1 版本发布后, 就开始指定6.0版本了. 因此, ES6这个词的原意, 就是指JavaScript语言的下一个版本的标准
- ES6既是一个历史名词, 也是一个泛指, 含义是5.1版本以后的JavaScript的下一代标准, 涵盖了ES2015、ES2016、ES2017等等.

## 二. 搭建前端环境

### 2.1 Node环境

#### 2.1.1 什么是Node.js

- Node.js是一个软件, 是可以运行在服务端(不依赖服务器)的JavaScript运行时环境
- Node.js是脱离了浏览器的JavaScript运行时环境, 其基于Google的V8引擎, V8引擎执行JavaScript的速度非常快, 性能非常好

#### 2.1.2 Node.js有什么用

- 如果你是前端程序员, 你不懂得像PHP、Python或Ruby等动态编程语言，然后你想创建自己的前端服务， 那Node.js是一个非常好的选择
- Node.js是脱离了浏览器的JavaScript运行时环境, 弱国你熟悉JavaScript, 那么你将会很容易得学会Node.js. 当然, 弱国你是后端程序员, 想不熟一些高性能的服务, 那么学习Node.js也是一个非常好的选择. 

#### 2.1.3 安装

##### 2.1.3.1 下载

- 官网: <https://nodejs.org/en/>

- LTS: 长期支持版本

- Current: 最新版

- 安装: 双击软件 --> Next --> Finish

##### 2.1.3.2 查看版本

- 在dos窗口中执行命令`node -v`来查看版本
- 如果在vscode的终端里无法执行此命令, 那就`以管理员身份运行`vscode, 应该就可以执行此命令了

##### 2.1.3.3 使用`Node.js`运行js文件

- 创建demo文件夹, 创建一个`hello.js`文件
- 在这个文件里写上`console.log("Hello World");`
- 在demo文件夹下, 按住`shift`同时点击右键, 选择`open in windows terminal`
- 输入`node hello.js`按下`enter`后, 就能运行这个js文件了

##### 2.1.3.4 服务端应用开发(了解)

```js
const http = require("http");
http.createServer(function(request, response){
    response.writeHead(200, {"Content-type":"text/plain"});
response.end("Hello, Welcome");
}).listen(8888);
console.log("服务器已启动, 请访问`http://127.0.0.1:8888'");
```

- 服务器启动成功后, 在浏览器中输入:<http://127.0.0.1:8888>查看webserver是否成功运行
- 停止服务, 在dos终端点击: ctrl + c

### 2.2 NPM环境

#### 2.2.1 什么是NPM

- NPM全称是Node Package Manager, 是Node.js包管理工具
- 是全球最大的模块生态系统, 里面所有的模块都是开源免费的, 也是Node.js的包管理工具, 作用如同后端的Maven
- 如果一个项目需要引入很多第三方的js文件, 比如地图、报表等， 文件杂而乱， 自己去网上下载， 到处都是广告和病毒， 那么，我们就想办法， 把这些js文件统一放到一个仓库里， 大家谁需要，谁就娶仓库中拿过来， 方便多了。 NPM就是负责与这个仓库系统对接， 如果你需要某个js文件， 那就去通过NPM到远程仓库下载，放到本地磁盘中， 进而引用到我们的项目中

#### 2.2.2 NPM工具的安装位置

- Node.js软件就包含了NPM工具, 所以在安装时就会NPM工具安装上
- Node.js中NPM包和工具的默认安装位置是:`Node.js安装目录/node_modules`
  - 在这个目录下可以看见NPM目录, NPM本身就是被NPM包管理的一个工具, 说明Node.js已经集成了NPM工具
- 在终端输入`npm -v`可以查看NPM的版本

#### 2.2.3 使用NPM管理项目

##### 2.2.3.1 项目初始化

- 全新创建一个目录, 作为项目目录, 打开终端输入`npm init`

- ```properties
  # 接下来是一堆项目信息等待着你输入，如果使用默认值或你不知道怎么填写，则直接回车即可。
  
  # package name:  你的项目名字叫啥
  # version: 版本号
  # description: 对项目的描述
  # entry point: 项目的入口文件（一般你要用那个js文件作为node服务，就填写那个文件）
  # test command: 项目启动的时候要用什么命令来执行脚本文件（默认为node app.js）
  # git repository: 如果你要将项目上传到git中的话，那么就需要填写git的仓库地址（这里就不写地址了）
  # keywirds： 项目关键字（我也不知道有啥用，所以我就不写了）
  # author: 作者的名字（也就是你叫啥名字）
  # license: 发行项目需要的证书（这里也就自己玩玩，就不写了）
  ```

- 最后会生成`package.json`文件, 这个是包的配置文件, 相当于maven的pom.xml配置文件, 之后可以根据需要进行修改

- 上述初始化一个项目有些麻烦, 可以通过一切按照默认值初始化来简化初始化过程, 只需在终端输入`npm init -y`

#### 2.2.4 修改NPM镜像和全局工具的本地安装位置

- NPM默认的仓库网址是官网<https://registry.npmjs.org>下载依赖包的, 但是由于墙的存在导致这个网站在国内速度很慢

- 为了提高下载速度建议将NPM的仓库地址改为<https://registry.npm.taobao.org>, 淘宝NPM镜像是一个完整的`npmjs.org`镜像, 同步频率目前为10分钟一次, 以保证尽量与官方服务同步

- 设置镜像, **打开终端, 输入以下命令**, 就可以永久修改NPM的仓库地址了

  - ```properties
    npm config set registry https://registry.npm.taobao.org
    ```

- 设置npm全局工具的安装位置(建议英文目录), **打开终端, 输入以下命令**

  - ```properties
    npm config set prefix "D:\\GitWorkSpaces\\NodeGlobal"
    ```
    
  - 然后**必须把该目录添加到环境变量`path`中, 这样就能在终端中调用到安装在此文件夹下的全局工具了**, 否则会影响到以后安装的全局工具的使用

- 查看npm配置信息, **打开终端, 输入以下命令**

  - ```properties
    npm config list
    ```



#### 2.2.5 `npm install`命令的使用

**在项目下打开终端, 输入以下命令**

```properties
npm install jquery
```

- 使用`npm install`命令可以下载安装依赖包的最新版

- 依赖包的下载安装位置: `项目目录\node_modules`

- 依赖包安装时会自动在项目目录下添加`package-lock.json`, 这个文件帮助锁定安装包的版本

- 同时`package.json`文件中, 依赖包会被添加到`dependencies`节点下, 类似maven中的`<dependencies>`

- jQuery的版本很多, 以上命令只会下载最新版本的jQuery

  - 可以使用`npm install jquery@版本号`方式来下载指定的版本

    - ```properties
      npm install jquery@3.5.0
      ```

## 三. ES6基本语法

- ES标准中不包含DOM和BOM的定义, 之涵盖基本数据类型、关键字、语句、运算符、内建对象、内建函数等通用语法
- 本部分只学习前端开发中ES6的必要之时， 方便后面项目开发中对代码的理解

### 3.1 关键字`let`声明变量

**与关键字`var`声明变量有什么区别**

#### 3.1.1 作用域不同

**在vscode的终端中输入`node`后, 在输入js文件名时, 可以点击`Tab`键自动补全文件名**

```js
{
    var a = "Mercy";
    let b = "黑百合";
}
console.log("a = " + a);
console.log("b = " + b);//ReferenceError: b is not defined
```

#### 3.1.2 声明次数不同

```js
var m = "蒂法";
var m = "艾丽丝";
let n= "Mercy";
let n= "WidowMaker";//SyntaxError: Identifier 'n' has already been declared 

console.log(m);
console.log(n);
```

#### 3.1.3 声明后进入内存的时间点不同

- 所有var声明的变量不论处于文件的何种位置, 都会在加载js文件时进入内存(注意只是进入了内存, 此时变量既没有初始化, 也没有赋值), 当执行这个js文件前var声明的变量已经在内存中了
- 而let声明的变量, 只有在执行这个js文件的过程中才会在声明的时候进入内存

```js
console.log(m);// var声明的m已经进入了内存, 可以调用到
var m = "蒂法";

console.log(n);//ReferenceError: Cannot access 'n' before initialization let声明的变量还未进入内存, 调用不到
let n = "艾丽丝";
```

### 3.2 关键字`const`声明常量

- 关键字`const`用来声明常量, 常量是一种只读变量

- 常量一旦声明后, 其值是不允许改变的, 否则会报错`TypeError: Assignment to constant variable.`

  - ```js
    const tifa = "蒂法";
    console.log(tifa);
    
    tifa = "艾丽丝";
    console.log(tifa);
    ```

- 常量声明时必须初始化, 否则会报错: `SyntaxError: Missing initializer in const declaration`

  - ```js
    const tifa = "蒂法";
    console.log(tifa);
    
    const aerith;
    ```

### 3.3 解构赋值

- 解构赋值是对赋值运算符的扩展
- 它是一种针对数组或者对象进行模式匹配, 然后对其中的变量进行赋值
- 解构, 顾名思义, 就是将集合型数据进行分解, 拆分, 把里面的值注意遍历获取
- 在代码书写上简洁易读, 于一更加清晰明了; 也方便了复杂对象中数据字段的获取

#### 3.3.1 数组解构

```js
var arr = ["Mercy", "黑百合", "小美"];

//传统的js
let a = arr[0];
let b = arr[1];
let c = arr[2];
console.log(a,b,c);

//ES6的结构
var [x, y, z] = arr;
console.log(x, y, z);

let [l, m, n] = arr;
console.log(l, m, n);
```

#### 3.3.2 对象解构

- ==注意: 数组解构用的是中括号`[]`, 对象结构用的是大括号`{}`==
- **对象解构的变量名必须跟对象中的属性相同**

```js
var hero = {
    heroName : "Mercy",
    heroGender : "女",
    heroSaying:"Hero's never die."
};

// 传统的js
let theName = hero.heroName;
let gender = hero.heroGender;
let saying = hero.heroSaying;
console.log("姓名: " + theName, "性别: " + gender, "口头禅: " + saying);

//ES6的结构
let {heroName, heroGender, heroSaying} = hero;//对象解构的变量名必须跟对象中的属性相同
console.log("姓名: " + heroName, "性别: " + heroGender, "口头禅: " + heroSaying);
```

### 3.4 模板字符串

- 模板字符串相当于加强版的字符串
- 模板字符串使用的是反引号``
  - 模板字符串除了作为普通字符串, 还可以用来定义多行字符串
  - 模板字符串中可以调用变量, 也可以插入表达式
  - 模板字符串中可以调用函数

#### 3.4.1 定义多行字符串

```js
// 普通字符串
var generalStr = "床前明月光\n疑是地上霜\n举头望明月\n低头思故乡";
console.log(generalStr);
console.log("------------------------------")
//模板字符串
var modelStr = `床前明月光
疑是地上霜
举头望明月
低头思故乡`;
console.log(modelStr);
```

#### 3.4.2 字符串中调用变量和插入表达式

语法格式: `${变量名}`    `${表达式}`

```js
var userName = "蒂法";
var age = 30;
//传统的字符串拼接
console.log("我叫" + userName + ", 今年" + age + "岁了");
console.log(`------------------`);
//模板字符串
console.log(`我叫${userName}, 今年${age}岁了`);
console.log(`我叫${userName}, 明年${age + 1}岁了`)
```

#### 3.4.3 字符串中调用函数

语法格式: `${调用函数}`

```js
function saying(){
    return "Hero's never die."
}

console.log(`当所有队友都死翘翘了的时候, 只听天使说了一句"${saying()}", 然后所有人都满血复活了`);
```

### 3.5 声明对象时简写

**定义对象的时候, 可以用参数的名字作为属性的名字**

```js
let userName = "穗乃果";
let age = 24;

//传统
let user1 = {
    userName : userName,
    age :age
}
console.log(user1);
console.log(`------------------`);
//es6新语法中的简写
let user2 = {userName, age};
console.log(user2);
```

### 3.6 对象中定义方法简写

```js
//传统
let user1 = {
     saying : function(){
         console.log(`Hero's never die.`);
     }
};
user1.saying();

//es6
let user2 = {
    saying(){
        console.log(`一枪一个`);
    }
};
user2.saying();
```

### 3.7 对象扩展运算符

对象扩展运算符`{...}`将参数对象中所有可以遍历的属性拿出来, 然后拷贝给新对象

#### 3.7.1 拷贝对象(深拷贝)

```js
let user1 = {
    name : "黑百合",
    age:33, 
    saying(){return `一枪一个`}};
console.log(user1);

//将对象的地址赋值给另一个变量
let user2 = user1;
console.log(user2);

//拷贝对象, 深拷贝出一个新的对象, 将新对象的地址赋值给变量
let user3 = {...user1};
console.log(user3);
```

#### 3.7.2 合并对象

- 吞噬合并(将多个多个对象合并成一个新的对像)
- 如果后面的对象跟前面的对象有相同的属性, 那么只会保留后面的对象的属性

```js
let user1 = {
    name : "黑百合",
    age:33, 
    saying(){return `一枪一个`}};
console.log(user1);

let user2 = {
    name : "WidowMaker",
    team : `黑爪`
}
console.log(user2);

//两个对象合并
let user3 = {...user1 , ...user2};
console.log(user3);

//三个对象合并
let user4 = {usedName : "艾米莉"};
let user5 = {...user1 , ...user2 , ...user4};
console.log(user5);
```

 ### 3.8 函数的默认参数

- 关键字`arguments`表示的是参数组成的数组, 默认参数不会保存到这个数组里
- 可以在参数列表中给某个形参赋值, 那么这个值就是形参的默认值了

```js
function test(name, age = 18 ){
    console.log(arguments);
    console.log(`我叫${name}, 今年${age}岁了`)
}

test("黑百合", 33);
test("Mercy");
test();
test("小美", null);
test("穗乃果", "")
test("红叶", undefined)
```

### 3.9 函数的不定参数

- 不定参数必须在相残列表的最后面

```js
function test(...args){
    console.log(args.toString());
}


test("黑百合");
test();
test("黑百合", "蒂法", "艾丽丝", "红叶","不知火舞");

//不定参数必须在形参列表的最后面
function user(name, age, ...args){
    console.log(name);
    console.log(age);
    console.log(`姓名:${name}, 年龄:${age}岁, 习惯:${args.toString()}`)

}

user("于谦", 60, `抽烟`,`烫头`,`喝酒`);
```

### 3.10 箭头函数

- 箭头函数提供了一种更加简洁的函数书写方式, 基本语法是: `(形参列表) =>{函数体}`
- 在箭头函数中, 当形参只有一个时, 形参列表外的小括号`()`可以省略
- 在箭头函数中, 当函数体只有一行return语句时, 大括号`{}`和`return`可以省略

```js
//传统
var demo1 = function(a, b){
    var result = a * b;
    return result;
}
console.log(demo1(1, 10));

//箭头函数
var demo2 = (a , b) => {
    var result = a * b;
    return result;
}
console.log(demo2(2,10));
//当形参只有一个时, 形参列表外的小括号可以省略
var demo3 = a => {
    var result = a * 10;
    return result;
}
console.log(demo3(3));
//当函数体只有一行return语句时, 大括号和return可以省略
var demo4 = (a , b) => a * b;
console.log(demo3(4,10));

```

### 3.11 Promise对象(了解)

- 用来解决嵌套函数的回调噩梦

```js
setTimeout(()=>{
    console.log(1);
    setTimeout(()=>{
        console.log(2);
        setTimeout(()=>{
            console.log(3);
            setTimeout(()=>{
                console.log(4);
            },1000);
        },1000);
    },1000);
},1000);

//Promise的构造函数接收一个参数，是函数，
//并且传入两个参数：resolve(异步操作执行成功后的回调函数)，reject(异步操作执行失败后的回调函数)
next = n => new Promise(function(resolve, reject) {
    setTimeout(function() {
      resolve(n);
    }, 1000);
  });

next(1)
  .then(res => { // 成功
    console.log(res);
    return next(2); //在then方法中调用的next方法，一定要用return ，否则不会通过resolve把数据往下传递
  })
  .then(res => {
    console.log(res);
    return next(3);
  })
  .then(res => {
    console.log(res);
  })
  .catch(() => {  //处理失败：catch方法的第二个参数是失败的回调
    console.log("出错啦！");
  });
```

### 3.12 模块化

- 如果在a.js文件中定义了5个方法，现在b.js文件中想使用a中的5个方法，怎么办？
- java语言的做法是import导入所需的方法之后，就能使用了。es6的模块化，也是用的同意思路, 不过实现起来要比Java语言多一个导出的步骤
  - 首先将一个js文件声明成一个模块导出之后，
  - 另一个js文件才能引入这个模块
- 在一个js文件中同一模块只加载一次（是单例的）， 若再去加载同目录下同文件，直接从内存中读取。

#### 3.12.1 传统的模块化

##### 3.12.1.1 导出模块与导入模块

###### 3.12.1.1.1 导出

user.js文件, 使用``module.export={...}`来到出文件中的成员

```js
function addUser(name){
    return `保存${name}成功`;
}

function removeUser(name){
    return `移除${name}成功`;
}

//为两个方法起个名字, 调用时就用起的名字调用
module.exports={
    add : addUser,
    remove : removeUser
}
```

###### 3.12.1.1.2 导入

- test.js文件,  test.js文件和user.js文件在同一文件夹下
- 使用`require(path)`来导入所需的文件, 被导入的文件就封装成了一个对象
- 当导出的文件和导入的文件在同一文件夹下时, 相对路径前要有`./`表示这两个文件在同一文件夹下

```js
let user = require("./user.js");//引入user模块

console.log(user);
console.log(user.add("黑百合"));
console.log(user.remove("吉尔"));
```

##### 3.12.1.2 直接以本名为名字
###### 3.12.1.2.1 导出

user.js文件, 使用``module.export={...}`来到出文件中的成员

```js
function addUser(name){
    return `保存${name}成功`;
}

function removeUser(name){
    return `移除${name}成功`;
}

//为两个方法起个名字, 直接以本名为名字, 调用时就用本名调用
module.exports={
    addUser,
    removeUser
}
```

###### 3.12.1.2.2 导入

- test.js文件,  test.js文件和user.js文件在同一文件夹下
- 使用`require(path)`来导入所需的文件, 被导入的文件就封装成了一个对象
- 当导出的文件和导入的文件在同一文件夹下时, 相对路径前要有`./`表示这两个文件在同一文件夹下

```js
let user = require("./user.js");//引入user模块

console.log(user);
console.log(user.addUser("黑百合"));
console.log(user.removeUser("吉尔"));
```

#### 3.12.2 ES6的模块化

##### 3.12.2.1 导出

- user.js , 使用`export{...}`来导出需要导出的成员

```js
let name = "Mercy";
let age = 33;
let saying = () => `Hero's never die.`

//声明模块并导出
export{
name,age,saying
}

/*
babel user.js -o .\dist\user.js
babel test.js -o .\dist\test.js
node .\dist\test.js

*/
```

##### 3.12.2.2 导入

- test.js, 使用`import {...} from "path"`来导入模块

```js
import {name,age,saying} from "./user.js"


console.log(name);
console.log(age);
console.log(saying());
/*或者
import user from "./user.js"


console.log(user.name);
console.log(user.age);
console.log(user.saying());
*/
```

- 运行时会报错, 这是因为现阶段的Node.js软件不支持ES6的import语法, 我们需要将以上ES6语法编写的代码转换为ES5语法的代码. 这就需要用到babel工具了

### 3.13 babel环境

- babel是一个广泛使用的转码器, 可以将ES6语法编写的代码转换为ES5语法编写的代码, 从而使得代码可以已在现有的环境中成功执行, 这意味着, 你可以用ES6语法编写代码, 而不用担心现有环境是否支持

#### 3.13.1 安装babel全局工具

- 打开终端, 运行以下命令, 安装babel全局工具

  - ```properties
    npm install --global babel-cli
    ```

- babel被安装在了[2.2.4 修改NPM镜像和全局工具的本地安装位置](#2.2.4 修改NPM镜像和全局工具的本地安装位置)中设置的全局工具的本地安装位置了

- 查看版本, 在终端中输入以下命令

  - ```properties
    babel --version
    ```

  - 如果报错的话,则是因为`Win10`默认禁止运行有危险的脚本, 修改一下系统策略就好了

    - 以管理员身份打开cmd, 输入以下代码

    - ```properties
      set-ExecutionPolicy RemoteSigned
      ```

    - 然后输入`y`

    - ![](%E7%AC%AC05%E7%AB%A0%20ECMAScript6.assets/ECMAScript6%E8%AF%A6%E8%A7%A3.png)

    - 现在, 就可以查看版本号了

#### 3.13.2 给项目安装转码器

- 创建`babel-test`文件夹, 在该文件夹下终端输入以下命令将该文件夹初始化成项目

  - ```properties
    npm init -y
    ```

- 给本项目安装babel转码器模块: 在本项目下打开终端, 输入以下命令

  - ```properties
    npm install --save-dev babel-preset-es2015
    ```

- 给本项目创建babel转码配置文件, 创建一个名为`.babelrc`的文件, 在该文件内输入一下代码

  - ```properties
    {
      "presets": ["es2015"],
      "plugins": []
    }
    ```

#### 3.13.3 对本项目下的指定js文件进行转码

- 在本项目下创建dist目录, 用来存放转码后的文件

- 在本项目下打开终端, 运行以下命令对指定的js文件进行转码

  - ```properties
    babel user.js --out-file .\dist\test.js
    ```

  - 或者

  - ```properties
    babel user.js -o .\dist\test.js
    ```

- 运行转码后的js文件

  - ```properties
    node .\dist\test.js
    ```



### 3.14 ES6模块化的另一种写法

#### 3.14.1 `as`的用法

##### 3.14.1.1 在导出模块的文件中的用法

`user.js`中如果导出时不想暴露模块当中的变量名字, 可以通过`as`来进行操作

```js
let name = "Mercy";
let age = 33;
let saying = () => `Hero's never die.`

//声明模块并导出
export{
name as n,
age as a,
saying as s
}

/*
babel user.js -o .\dist\user.js
babel test.js -o .\dist\test.js
node .\dist\test.js

*/
```

`test.js`文件中, 需要以以下方式接收

```js
import {n,a,s} from "./user.js"


console.log(n);
console.log(a);
console.log(s());

/*
babel user.js -o .\dist\user.js
babel test.js -o .\dist\test.js
node .\dist\test.js

*/
```

##### 3.14.1.2 在导入模块的文件中的用法

###### 3.14.1.2.0 准备导出模块的文件

`user.js`

```js
let name = "Mercy";
let age = 33;
let saying = () => `Hero's never die.`

//声明模块并导出
export{
name,
age,
saying
}

/*
babel user.js -o .\dist\user.js
babel test.js -o .\dist\test.js
node .\dist\test.js

*/
```

###### 3.14.1.2 接收整个模块

`test.js`

```js
import * as hero from "./user.js"


console.log(hero.name);
console.log(hero.age);
console.log(hero.saying());
```

##### 3.14.1.3 对导出模块中的变量进行重命名

`test.js`

```js
import {name as n, age as a, saying as s} from "./user.js"


console.log(n);
console.log(a);
console.log(s());
```

#### 3.14.2 导出模块中的默认导出

- 就是将所有需要导出的变量放入到一个对象中, 然后通过export default进行导出

```js
export default{
    name : "Mercy",
    age : 33,
    saying(){ 
        return `Hero's never die`;
    } 
}

/*
babel user.js -o .\dist\user.js
babel test.js -o .\dist\test.js
node .\dist\test.js

*/
```

==这种方式导出的模块只能以一个变量接收整个文件为对象的方式来导入==

`test.js`

```js
import hero from "./user.js"//这种方式导出的模块只能以一个变量接收整个文件为对象的方式来导入

console.log(hero.name);
console.log(hero.age);
console.log(hero.saying());

```



