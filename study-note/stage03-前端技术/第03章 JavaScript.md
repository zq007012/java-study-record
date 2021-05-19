# 第03章 JavaScript

[toc]

## 一. JavaScript介绍

- 虽然是以Java作为前缀, 但是Java语言与JavaScript语言的关系, 就像老婆和老婆饼间的关系一样, 没有半毛钱关系
- 网景公司在Netscape2.0首先推出了Javascript
- JavaScript的正式名称是`ECMAScript`, 此标准由ECMA组织发展和维护, 简称`js`
- JavaScript是一种网页编程技术, 用来向HTML页面添加交互行为
- JavaScript是一种基于对象和事件驱动的结石性脚本, 直接嵌入HTML页面, 由浏览器解释执行代码, 不进行预编译

### 1.1 js的特点

- 可以使用任何文本编辑工具编写, 只需要浏览器就可以执行程序(**后面会学不用浏览器也能运行的方式**)
- 解释执行:事先不编译, 使用时再逐行编译执行
- 基于对象: 基于语言内置的大量现成的对象
- ==JavaScript的标识符是区分大小写的, 规则跟Java的相同==
- 应用场景(一般是指在浏览器上)
  - 客户端数据计算
  - 客户端表单数据提交前的合规性验证
  - 浏览器事件的触发
  - 网页特殊显示效果制作

### 1.2 js的组成

- ECMAScript: 定义==核心语法==、关键字、运算符、数据类型等一系列标准
- DOM: document object model，文档对象模型， 将一个html页面的所有节点看成一个一个对应的对象， 更有层次感的管理每一个节点。
- BOM: brower object model，浏览器对象模型， 用来对浏览器串口进行访问和操作， 使用BOM， 开发者可以移动窗口， 改变装天蓝中的文本以及执行其他与页面内容不直接而相关的动作， 使BOM独树一帜且又常常令人困惑的地方在于， 它只是JavaScript的一个部分， 但没有任何相关的标准
  - 弹出新的浏览器窗口
  - 移动、关闭浏览器窗口以及调整窗口大小
  - 提供Web浏览器详细信息的定位对象
  - 提供用户屏幕分辨率详细信息的屏幕对象
  - 对cookie的支持
  - IE扩展了BOM， 加入了ActiveXObject类， 可以通过JavaScript实例化ActiveX对象， 进而实现ajax局部刷新技术

## 二. HTML与JS的结合方式

**使用js的三种方式**

### 2.1 行内脚本

1. 事件属性
2. 事件属性的值是具体的操作

例如;

```html
<button onclick="alert('行内脚本弹框测试')">行内脚本弹框测试</button>
```

### 2.2 内部脚本

- 使用标签`<script>`
- 标准是写在`<head>`和`<body>`之间(脖子位置), 但其实只要写在html文件内部就可以, 无论什么位置都行, 比如`</html>`后面, `<p></p>`内部
- 可以创建多个`<script>`标签
- 因为JavaScript是解释性语言, 所以多个`<script>`标签就会按照其在html文件中的上下顺序来执行

```html
<script>
    alert("第一个内部脚本弹框测试");
  </script>
  <body>
    <button onclick="alert('行内脚本弹框测试')">行内脚本弹框测试</button>
  </body>
  <script>
    alert("第二个内部脚本弹框测试");
  </script>
```

### 2.3 外部脚本

- 外部脚本文件的后缀名是`.js`

- 引入方式, 通过标签`<script>`的属性`src`来引入

  - ```html
    <scrip src="js文件路径"></scrip>
    ```
    
  - ```html
    <script src='js/demo.js'></script>
    ```

### 2.4 js三种使用方式的优先级

- 一个html文件中可以有多个`script`元素
- 因为是解释型语言, 所以谁在上谁先执行

## 三. JavaScript的编程基础

### 3.1 变量

- js是弱类型语言, 所以在声明变量的时候,所有的数据类型都是`var`
  - 声明变量: `var x;`    `var x,y;`
- 虽然变量在声明变量的时候数据类型都是`var`, 但变量保存的数据还是有具体的类型的, 这些数据区分为4种类型; `string`    `number`    `boolean`    `object`
  - 字符串类型: `string`, 首位用单引号或双引号括起
    - `var variate='波雅·汉库克'`
  - 数值类型: `number`     包括整数和小数
    - `var variate=100`
    - `var variate=1.1111`
  - 布尔类型: `boolean`    仅有两个值`false`和`true`
    - 算数运算中, false=0, true=1

#### 3.1.1 自动类型转换

- 变量保存的数据在进行运算时可以按情况自动进行类型转换
- 数值 + 字符串: 数字自动转换为字符串, 结果是两个字符串拼接
  - 10 + 'a'  = '10' + 'a' = 10a'
- 数值  + 布尔值: 布尔值自动转换为数字, 结果是两个数字相加
  - 10 + true = 10 + 1 = 11
- 字符串 + 布尔值: 布尔值自动转换为字符串, 结果是两个字符串拼接
  - 'a' + false = 'a' + 'false' = 'afalse'
- 布尔值 + 布尔值:  两个布尔值分别自动转换为数字, 结果是两个数字相加
  - false + false = 0 + 0 = 0
- 布尔值可以自动转为数值、字符串， 数值可以自动转为字符串， 但不能反过来

#### 3.1.2 数据类型转换的全局函数

- **parseInt(param) **:  转换为整数, 该方法是复制了一份param的值, 然后对复制的值进行转换为整数的操作, 最后将转换结果作为返回值返回, 并没有改变param的值

  - 如果param的值不能转换为整数, 则返回NaN(not a number, NaN是代表非数字值的特殊值, 该值用于表示某个值不是数字)

  - ```html
    <script>
        var a = parseInt("20");
        var b = parseInt("20.9");
        var x = 10.6;
        var y = parseInt(x);
        console.log("a = " + a);
        console.log("b = " + b);
        console.log("x = " + x);
        console.log("y = " + y);
      </script>
    ```

  

- **parseFloat(param) **: 转换为浮点数, 该方法是复制了一份param的值, 然后对复制的值进行转换为整数的操作, 最后将转换结果作为返回值返回, 并没有改变param的值

  - 如果param的值不能转换为浮点数, 则返回NaN(not a number, NaN是代表非数字值的特殊值, 该值用于表示某个值不是数字)

- **typeOf(param) **: 获取变量param的类型, 返回的是一个表示类型的字符串, 有四种返回值`string`  `number`  `boolean`  `object`

#### 3.1.3 关键字`null`与`undefined`

- null  表示这个变量的值为null, null代表空值, 可以通过给一个变量赋值null来清除变量的内容

- undefined  则表示声明了变量但从未赋值

- ```html
  <script>
      var a = null;
      var b;
      console.log("a = " + a);
      console.log("b = " + b);
    </script>
  ```

### 3.2 算术运算

- `+`    `-`    `*`    `/`    `%`(取余数)

- `++`    `--`  使用方法跟在java中一样

  - ```html
    <script>
    	var c = 5;
        var d = 5;
        console.log("c = " + c++);
        console.log("d = " + ++d);
    </script>
    ```

### 3.3 关系运算

#### 3.3.1 严格相等与不严格相等

- 严格相等`===`    符号两边变量的值的数据类型必须相同, 并且数值也要相同

- 不严格相等`==`    符号两边变量的数值相同即可

- ```html
  <script>
      var e = "1";
      var f = "true";
      var g = true;
      var h = 1;
      console.log("'e==f' = " + (e == f));
      console.log("'e===f' = " + (e === f));
      console.log("'e==g' = " + (e == g));/*g先由布尔值转为数值, 再由数值转为字符串*/
      console.log("'e===g' = " + (e === g));
      console.log("'e==h' = " + (e == h));
      console.log("'e===h' = " + (e === h));
      console.log("'f==h' = " + (f == h));
      console.log("'f===h' = " + (f === h));
  </script>
  ```

##### 3.3.2 其他关系运算符

`<`  `>`  `!==`  `!===`

### 3.4 逻辑运算

- `&&`    `||`    `!`
- 逻辑运算符的用法跟在java中一样

### 3.5 控制语句

- if  else 语句
- switch语句
- for循环语句
- while循环语句

用法跟在java中一样

### 3.6 字符串常用的API(JavaScript提供)

- length属性, 获取字符串的长度, 及字符串中字符的个数
- toUpperCase() : 字母全部转为大写
- toLowerCase(): 字母全部转为小写
- charAt(index): 返回指定下标上的字符
- indexOf(param) : 查找字符串中字符首次出现的下标
- lastIndexOf(param): 查找字符串中字符最后一个出现的下标
- subString(start, end) : 截取字符串中的一部分(从下标start开始, 到下标end前结束, 即不包括end)
- subStr(start, length): 截取字符串中的一部分(从下标start开始的length个元素)
- replace(oldSub, newSub): 将字符串中的所有oldSub子串换成newSub
- split(splitSub) : 切除掉字符串上的所有splitSub子串, 从而将这个字符串切割成n个小字符串, 返回的是这些小字符串组成的字符串数组
- match(regex) : 判断字符串是否匹配正则表达式字符串regex, 返回的是一个布尔值

### 3.7 数组

JavaScript中的数组, 长度可变, 元素的类型可以相同, 也可以不同

#### 3.3.1 创建数组的三种方式

```javascript
/* 第一种 */
var arr1 = new Array();
/* 第二种 */
var arr2 = new Array(1, '汉库克', false);
/* 第三种 */
var arr3 = [1, '汉库克', false];//注意是中括号
```

#### 3.7.2 给数组中的元素赋值的方法

```javascript
    var arr1 = new Array();
    var arr2 = new Array(1, "蒂法", true);
    var arr3 = [1, "蒂法", true];
    for (var i = 0; i < 7; i++) {
      arr1[i] = "demo" + i;
      if (i > 2) {
        arr2[i] = "demo" + i;
        arr3[i] = "demo" + i;
      }
    }
    console.log("arr1=" + arr1.toString());
    console.log("arr2=" + arr2.toString());
    console.log("arr3=" + arr3.toString());
```

#### 3.7.3 数组常用的方法

- toString() : 返回一个包含数组所有元素(元素间用`,`隔开)的字符串
- push(element1, ...elements)    将一个或多个元素添加到数组的末尾，并返回该数组的新长度
- join(str) : 将数组中的所有元素转换为字符串, 并使用str将这些字符串拼接起来形成一个新的字符串, 返回这个字符串
- concat(params) : 复制这个数组, 然后将参数列表params中的参数作为复制体数组的新元素添加到复制体数组的后面, 最后返回这个复制体数组
  - 所以作为本体的数组是没有发生改变的
- slice(start , end) : 提取数组中从下标start开始, 到下标end前结束(不包括end)的元素, 将这些元素组合成一个新的数组, 然后返回这个数组
- reverse() : 将数组中的元素位置反转(倒序)
- sort() : 将数组中的元素按照字符排序
  - sort( new Function('a ', 'b', 'return a-b') )     将数组中的元素按照数字大小增序排序
  - sort(new Function( 'a' , 'b' , 'return b-a') )    将数组中的元素按照数字大小降序排序
- splice(start[, deleteCount[, item1[, item2[, ...]]]])    移除掉从start位置开始的deleteCount个元素, 然后换上新的元素
  - start    指定修改的开始位置（从0计数）
  - delete    [可选] 整数，表示要移除的数组元素的个数, 不写的话就会删除从start开始的所有元素
  - item1, item2,... [可选]    要添加进数组的元素,从`start` 位置开始

### 3.8 数学对象`Math`

- `Math`对象用于执行数学任务
- 没有构造函数
- 无需创建, 直接把Math作为对象使用就可以调用其所有属性和方法, 就像Java中所有方法都是静态方法的工具类那样来使用

#### Math对象的常用方法

| 需求                           | 方法       |
| ------------------------------ | ---------- |
| 获取数值x的绝对值              | abs(x)     |
| 获取两个数中的较大值           | max( x, y) |
| 获取两个数中的较小值           | min( x, y) |
| 返回x的y次幂                   | pow(x, y)  |
| 返回0~1之间的随机数            | random()   |
| 返回x的平方根                  | sqrt(x)    |
| 返回x的天花板整数(有小数就进1) | cell(x)    |
| 返回x的地板整数(忽略掉小数)    | floor(x)   |
| 返回x的四舍五入的整数值        | round(x)   |

### 3.9 数值对象Number

#### 3.9.1 构造函数

- `Number(x)`

#### 3.9.2 Number常用方法

| 需求                                                         | 方法       |
| ------------------------------------------------------------ | ---------- |
| 以四舍五入的方式保留x的小数点后y位, 并返回这个数值(对x本身没有改变) | toFixed(y) |

#### 3.9.3 示例代码

```javascript
var num = new Number(3.1415926);
var y = num.toFixed(2);
var z = num.toFixed(3);
console.log()
```

### 3.10 JS中使用==正则表达式==的方式

#### 3.10.1 直接声明一段文本是个正则表达式对象

```javascript
var phone = '18888888888'; // 判断: 这是个手机号
var reg = /^1\d{10}$/; // 文本以/^开头, 以$/结尾时, 就表明这个文本是一个正则表达式对象
var result = reg.test(age);
if(result){
    console.log("验证通过");
}else{
    console.log("验证未通过");
}
```

#### 3.10.2 将一个字符串封装成正则表达式对象

```javascript
var phone = '18888888888'; // 判断: 这是个手机号
var reg = new RegExp("1\\d{10}"); // 将一个字符串封装成正则表达式对象
var result = reg.test(age);
if(result){
    console.log("验证通过");
}else{
    console.log("验证未通过");
}
```

- 在JavaScript中编写**正则表达式字符串**的步骤
  - 先编辑好正则表达式
  - 将表达式中的所有`\`替换为`\\`(防止字符串的转义)
  - 将表达式中的所有`"`替换为`\"`(防止字符串将`"`识别成字符串的终止符)
  - 用**双引号**包裹表达式,从而形成一个正则表达式字符串

### 3.11 日期对象

```javascript
    var time = new Date();
    console.log(time);

    var year = time.getFullYear();
    var month = time.getMonth() + 1;
    var day = time.getDate();
    var hour = time.getHours();
    var mm = time.getMinutes();
    var ss = time.getSeconds();
    var ms = time.getMilliseconds();
    var timeStr = year + "年" + month + "月" + day + "日 " + hour + ":" + mm + ":" + ss + "." + ms
    console.log(timeStr);
```

### 3.12 函数

- 使用关键字`function`声明函数

- ```javascript
  function 函数名(形参列表){
      //函数体
      return 返回值;
  }
  ```

- **函数声明后不会立即执行, 函数只有在调用后才会执行**

- 注意; 

  - 函数的形参不能带数据类型
  - 分号是用来分隔可执行javascript语句的, 由于函数声明不是一个可执行语句, 所以不能以分号结束

#### 3.12.1 无返回值的函数

```javascript
// 声明函数
function getSum(a, b){
    var sum = a + b;
    console.log(a + "+" + b + " = " + sum);
}
//调用函数
getSum(1, 2);
```

#### 3.12.2 有返回值的函数

```javascript
//声明函数
function getSum(a, b){
    var sum = a + b;
    return sum;
}
//调用函数
var sum = getSum(a, b);
console.log("两数之和是: " + sum);
```

#### 3.12.3 封装函数所有参数的数组对象`arguments`

```javascript
function func(a, b, c){
    console.log("有" + arguments.length + "个参数");
    console.log("第3个参数是" + arguments[3-1]);
}

//调用函数
func("蒂法", "艾瑞丝", "汉库克");
```

#### 3.12.4 使用函数对象`Funciton`声明函数

##### 3.12.4.1 格式

```javascript
var 变量名 = new Function(形参列表, "函数体; retun 返回值");
//变量名就是生命的函数名
```

##### 3.12.4.2 示例代码

```javascript
//使用函数对象 Function 声明函数, 变量名就是函数名
/*
var getMultiply = new Function("a", "b", "var multiply = a * b; return mutiply");
//或者简写成
*/
var getMultiply = new Function("a", "b", "return a * b");//以 ; 号结束, 因为这种函数声明方式是个语句
// 调用函数
var multiply = getMultiply(999, 888);
console.log(multiply);
```

#### 3.12.5 匿名函数

即没有名称的函数, 实际上接收这个函数的变量充当了这个函数的名字

```javascript
//匿名函数需要一个变量来接收
var getQuotient = function(dividend, divide){
    return dividend / divide;
};// 以分号结束, 因为种声明方式是个语句

//调用函数, 接收匿名函数的变量充当了匿名函数的名字
console.log(getQuotient(5, 2));
```

#### 3.12.6 常用的JS全局函数

| 函数                                             | 说明                                                         |
| ------------------------------------------------ | ------------------------------------------------------------ |
| [isNaN( variate ) : boolean](#3.12.6.1 )         | 不考虑类型, 检查variate是否是非数值                          |
| [eval( str )](#3.12.6.2 )                        | 用来将字符串中的运算转换成数字运算                           |
| [encodeURI( str ) : string](#3.12.6.3 和)        | 用来将非英文字符 编码 为英文编码                             |
| [decodeURI( str ) : string](#3.12.6.3 和)        | 用来将 encodeURI( str )编码的字符解码回去                    |
| [alert( str )](#3.12.6.4 弹框输出)               | 弹框输出, 弹框显示str                                        |
| [console.log( str )](#3.12.6.5 控制台日志输出)   | 控制台日志输出str                                            |
| [document.write( str )](#3.12.6.6 网页代码输出)  | 将str作为代码添加到`<body>`元素中, 会替换掉`<body>`内原有的代码<br />但不会替换上一次write时添加的代码, 只会追到到上一次write的代码之后 |
| [confirm( str ) : boolean](#3.12.6.7 弹出确认框) | 弹出确认框, 点击确认返回true, 点击取消返回false              |
| [promot( str ) : string](#3.12.6.8 弹出输入框)   | 弹出输入框<br />当点击输入框上的`确定`后会返回输入框里输入的内容, 如果输入框里没有内容,返回值则为一个空字符串`""`, 如果点击输入框上的`取消`, 返回值则为`null` |



##### 3.12.6.1 `isNaN(variate)`

- **不考虑类型**, 检查variate是否是**非数值**

- is not a number

- ```javascript
      console.log(isNaN(123));//false
      console.log(isNaN("123"));//false
      console.log(isNaN(false));//false
      console.log(isNaN(true));//false
      console.log(isNaN("false"));//true
      console.log(isNaN("true"));//true
      console.log(isNaN(4 - 1));//false
      console.log(isNaN("4-1"));//true
  ```

##### 3.12.6.2 `eval(str)`

- 用来转换字符串中的运算

- ```javascript
      console.log("3 + 1");// 3 + 1
      console.log(eval("3 + 1"));// 4
      console.log(eval("a + 1"));//error
  ```

##### 3.12.6.3 `encodeURI(str)`和`decodeURI(str)`

- `encodeURI(str)`和`decodeURI(str)`是成对来使用的，因为浏览器的地址栏有中文字符的话，可以会出现不可预期的错误，所以可以encodeURI把==非英文字符==**编码**为==英文编码==，decodeURI可以用来把字符**解码**回来。

- `encodeURI(str)`方法不会对以下四个字符进行编码：`:`、`/`、`;`和 `?`

  - `encodeURIComponent(str)方`法会对上述四个字符进行编码, 相对应的则需要用`decodeURIComponent(str)`来解码

- ```javascript
      var encodeStr = encodeURI("hancock汉库克:;?/");
      var decodeStr = decodeURI(encodeStr);
      console.log(encodeStr);
      console.log(decodeStr);
      var encodeComponentStr = encodeURIComponent("hancock汉库克:;?/");
      var decodeComponentStr = decodeURIComponent(encodeStr);
      console.log(encodeComponentStr);
      console.log(decodeComponentStr);
  ```

##### 3.12.6.4 弹框输出`alert(str)`

- 弹框输出`alert("hello world");`

##### 3.12.6.5 控制台日志输出`console.log(str)`

- **控制台日志输出**`console.log("hello world");`
- 谷歌浏览器按`F12`进入控制台

##### 3.12.6.6 网页代码输出`document.write(str)`

- 将str作为代码添加到`<body>`元素中, 会替换掉`<body>`内原有的代码

- 但不会替换上一次document.write( str )添加的代码, 只会追到到上一次write的代码之后

- ```html
   <body>
      吃了吗?<br />
      <img style="height: 200px" src="image/nami (3).jpg" /><br />
      <button onclick="test()">测试网页代码输出</button>
    </body>
    <script>
      function test() {
        document.write("吃了.");
        document.write("吃了.<br/>");
        document.write("<button onclick='test1()'>测试网页代码输出</button>");
      }
    
      function test1() {
        document.write("<br/>吃没吃关你啥事?");
      }
    </script>
  ```

##### 3.12.6.7 弹出确认框`confirm(str)`

- **确认框**`confirm("是否退出?");`

##### 3.12.6.8 弹出输入框`prompt(str)`

- 弹出输入框`prompt("请输入你的名字: ")`

- 该方法具有返回值, 当点击输入框上的`确定`后会返回输入框里输入的内容, 如果输入框里没有内容,返回值则为一个空字符串`""`, 如果点击输入框上的`取消`, 返回值则为`null`

- ```javascript
  	var name = prompt("请输入你的名字");
      console.log("name = " + name);
  ```

#### 3.12.7 闭包函数

##### 3.12.7.1 闭包函数的概念

指有权访问另一个函数作用域中的变量 的函数, 一般情况就是在一个函数中包含另一个函数

##### 3.12.7.2 闭包函数的作用

- 访问函数内部变量, 使函数在运行环境中一直存在, 不会被垃圾回收器回收
- 简单来说: 就是在函数的局部范围内声明一个封闭的环境, 此环境不会被垃圾回收器探测到, 保证了数据的安全唯一性
- 想了解闭包函数, 首先要了解什么是JavaScript中的全局变量, 什么是JavaScript中的局部变量

##### 3.12.7.3 JavaScript中的全局变量和局部变量

- 变量声明时可以加 `var` , 也可以不加
- 函数外声明的变量都是全局变量
- 函数内声明时不加 `var` 的变量是全局变量, 函数内声明时加 `var`的变量是局部变量

```html
<script>
    //变量声明时可以加 var , 也可以不加
    var aGlobal = "全局变量a";//函数外声明的变量都是全局变量
    bGlobal = "全局变量b";//函数外声明的变量都是全局变量
    function demo(){
        cGlobal = "全局变量c";//函数内声明时不加 var 的变量是全局变量
        var dArea = "局部变量d";//函数内声明时加 var的变量是局部变量
    }
    
    function test(){
        console.log(aGlobal);
        console.log(bGlobal);
        console.log(cGlobal);
        console.log(dArea);
    }
    //调用test方法
    test();
</script>
```

##### 3.12.7.4 闭包函数示例

需求: 统计本方法执行了多少次

```javascript
var count = 0;//总次数
function test1(){
    count++;
}
test1();
test1();
test1();
test1();
count = 0;//谁都可以访问并修改count, 导致最后统计的不准确
test1();
console.log(count)
```

谁都可以访问count, 所以count变量并不安全, 因为是全局变量, 毕竟当你统计的时候别人可以更改count的值, 从而导致统计结果不准确

如可才能安全呢? 将count声明为局部变量

```javascript
function test1(){
    var count = 0;//总次数
    return count++;
}
test1();
test1();
test1();
test1();
console.log(test1());
```

count成为局部变量后是安全了, 但是每次调用test方法, 都会重新声明一个count,  导致无法统计次数

思路是: count是个局部变量, 不能在方法外被访问到, 同时在再次调用test方法时count变量仍旧是第一次调用test方法时声明的那个变量count, 这是闭包函数就能起作用了

```javascript
function test1(){
    var count = 0;//总次数
    function getExecuteCount(){
        return count++;
    }
    return getExecuteCount();
}
test1();
test1();
test1();
test1();
console.log(test1());
```

- 闭包函数, 就是在一个函数内嵌套一个内函数, 这个外函数就被称作闭包函数
- 闭包函数内嵌套的内函数只有在外函数被调用时才会执行, 内函数内的局部变量外函数是访问不到的
- 闭包函数第一次被调用后就会一直存在于运行环境中, 直到运行环境关闭才会跟着销毁, 所以运行环境关闭前再次调用闭包函数时调用到的其实是已经存在与运行环境中的闭包函数, 因此闭包函数的局部变量不是新建的, 而是复用上次调用该闭包函数时的值
- 闭包函数的优点: 方便调用上下文中声明的局部变量, 逻辑紧密, 数据安全
- 闭包函数的缺点: 因为使用闭包, 可以使闭包函数在执行完后不被销毁, 闭包函数中的局部变量会一直占据着内存, 如果大量使用闭包就会造成内存泄露, 内存消耗很大

## 四. 通过JS中的DOM来管理html页面中的元素

### 4.1 DOM概念

- 在一个html页面中, 会使用很多标签来规划制作页面
- 每个标签都有它存在的意义, 如果我们想要动态地修改某个标签的值. 就需要在页面中找到这个标签元素
- W3C组织的工程师们为我们提供了**文档对象模型**, 来管理作这些标签元素
- DOM(Docment object model) -- 文档对象模型, 就是将整个html页面看成是一棵树, 根节点`html`就相当于树干, 所有的标签都是从根元素延伸出去的分支, 摸清结构, 找到某个标签就不再困难了
  - 在节点树中, 顶端节点(html)就是根节点(root)
  - 每个节点都有父节点(除了根节点)
  - 任何一个节点都可以拥有任意数量的子节点
  - 同胞是拥有相同父节点的节点

> - JS中的DOM将整个html页面封装成了`document`对象, 
> - 将一个个的元素封装成了一个个的元素对象, 
> - 通过调用`docment`对象中的相关方法可以获取这些元素对象,
> - 通过调用元素对象中的相关方法及属性可以管理这些节点

### 4.2 对象`document`的常用属性和方法

#### 4.2.1 对象`document`的常用属性

- ==属性可以获取值, 也可以用来赋值==

| 属性 | 说明                       |
| ---- | -------------------------- |
| body | 获取本页面的`body`元素对象 |

#### 4.2.2 对象`document`中的常用方法

| 方法                             | 返回值         | 说明                         |
| -------------------------------- | -------------- | ---------------------------- |
| getElementById( elementId )      | 元素对象       | 通过Id属性值获取元素对象     |
| getElementsByName( elementName ) | 元素对象数组   | 通过name属性值获取元素对象集 |
| getElementsByTagName( tagName )  | 元素对象数组   | 通过标签名获取元素对象集     |
|                                  |                |                              |
| createElement(tagName)           | 创建的元素对象 | 创建一个元素对象             |

### 4.3 元素对象的常用属性和方法

==元素对象可以视为节点对象, 节点对象也可以视为元素对象, 所以这两者的属性和方法是通用的==

#### 4.3.1 元素对象常用的属性

| 属性                    | 说明                                                         |
| ----------------------- | ------------------------------------------------------------ |
| `clientHeight`          | padding + content                                            |
| `scrollHeight`          | padding + content                                            |
| `offsetHeight`          | boder + padding + content                                    |
| `clientWidth`           | padding + content                                            |
| `scrollWidth`           | padding + content                                            |
| `offsetWidth`           | boder + padding + content                                    |
|                         |                                                              |
| innerHTML               | 1. 获取值 --- 获取元素对象内的所有内容(文本内容和子元素)<br />2. 赋值 --- 用新值替换元素对象内的所有内容(文本内容和子元素)<br />3. 类型是string |
| innerText               | 1. 获取值 --- 获取元素对象内的所有文本内容<br />2. 赋值 --- 用文本内容替换元素对象内的所有内容(文本内容和子元素)<br />3. 类型是string |
|                         |                                                              |
| 属性名                  | 1. 获取本元素对象的对应属性的值<br />2. 给本元素对象的对应属性赋值<br /><br />3. 如果未设置该属性则返回一个空字符串<br />4. 属性的值的类型是string |
| id                      | 1. 获取本元素对象的属性name的值<br />2. 给本元素对象的属性name赋值<br />3. 类型是string |
| name                    | 1. 获取本元素对象的属性id的值<br />2. 给本元素对象的属性id赋值<br />3. 类型是string |
| value                   | 1. 获取本元素对象的属性value的值<br />2. 给本元素对象的属性value赋值<br />3. 类型是string |
|                         |                                                              |
| parentNode              | 获取父元素的节点对象                                         |
| firstChild              | 获取第一个子元素的节点对象                                   |
| lastChild               | 获取最后一个自己元素的节点对象                               |
| previousSibling         | 获取本元素之前的元素的节点对象                               |
| nextSibling             | 获取本元素之后的元素的节点对象                               |
| children                | 获取本元素的所有子元素的节点对象                             |
|                         |                                                              |
| contentWindow           | iframe元素对象的专用属性, 用来获取iframe标签引用的网页的window对象 |
| contentDocument         | iframe元素对象的专用属性, 用来获取iframe标签引用的网页的docment对象 |
|                         |                                                              |
| [style](#行内CSS的对象) | 获取本元素对象的行内css的对象                                |

##### 行内CSS的对象`style`

- 可以用来设置本元素的行内css
- `style.color="red"`    `style.fontSize="2em"`    `style.height="100px"`等等

#### 4.3.2 元素对象常用的方法

##### 4.3.2.1 属性方面的方法

| 方法                                  | 返回值  | 说明                                                         |
| ------------------------------------- | ------- | ------------------------------------------------------------ |
| getAttrigbute(attributeName)          | string  | 1. 获取属性attributeName的值, 如果未设置该属性, 则返回null<br />2. 此方法只能获取到用户设置的属性值而无法获取系统设置的属性值<br />3. 建议使用`元素对象.属性名`的方式获取属性值或者赋值 |
| setAttribute(attributeName, newValue) | void    | 设置属性attributeName的值为newValue                          |
| removeAttribute( attributeName )      | void    | 移除属性attributeName                                        |
| hasAttribute( attributeName )         | boolean | 判断是否设置了属性attributeName                              |

##### 4.3.2.2 节点操作方面的方法

| 方法                               | 返回值 | 说明                                                         |
| ---------------------------------- | ------ | ------------------------------------------------------------ |
| cloneNode( deep : boolen )         | Node   | 克隆一个本元素的节点对象<br />deep的值为true时, 克隆内容包括所有的子元素<br />deep的值为false时, 克隆内容不包括子元素 |
|                                    |        |                                                              |
| appendChild( element / node)       | Node   | 追加一个子元素, 返回值就是这个追加的元素的节点对象           |
| removeChild( element/ node )       | Node   | 移除掉本元素下的一个子元素, 返回值是移除的这个子元素         |
| replaceChild( newChild, oldChild ) | Node   | 用newChile替换本元素下的一个子元素oldChild, 返回值是newChild |
|                                    |        |                                                              |
| remove()                           | void   | 从本元素的父节点中移除本元素                                 |

##### 4.3.2.3 事件监听方面的方法

| 方法                                                | 返回值 | 说明                                                         |
| --------------------------------------------------- | ------ | ------------------------------------------------------------ |
| addEventListener( Event.type, listener, options)    | void   | 给本元素添加一个事件监听器<br />就是当Event.type这个事件发生时, 在listenner中何处理这个事件, <br />listener必须是一个没有参数和没有返回值的方法<br />options是boolean类型, 是个可选值, 默认值是false, false表示以事件冒泡**(由内而外)**的方式传递事件, true表示以事件捕获**(由外而内)**的方式传递事件<br /> |
| removeEventListener( Event.type, listener, options) | void   | 移除事件监听器, Event.type和listener相同, 就能移除掉         |

### 4.4 应用示例

#### 4.4.1 阻止表单提交

- 当表单标签`<form>`的属性`onsubmit="return false"`时, 表单就会不会提交, 当`onsubmint="return true"`时表单就会成功提交;

- 或者当表单提交按钮的属性`onclick="return false"`时, 单就会不会提交, 当`onclick="return true"`时表单就会成功提交;

- ```html
    <body>
      <form action="https://www.baidu.com" method="POST">
        账号:&nbsp;<input
          id="account"
          type="text"
          placeholder="请输入账号"
        /><br />
        <input type="submit" onclick="return check()" />
      </form>
    </body>
    <script>
      function check() {
        var account = document.getElementById("account").value;
        if (account != undefined && account != null && account != "") {
          return true;
        } else {
          alert("账号不能为空!!!");
          return false;
        }
      }
    </script>
  ```

- ```html
  <body>
      <form action="https://www.baidu.com" method="POST" onsubmit="return check()">
        账号:&nbsp;<input
          id="account"
          type="text"
          placeholder="请输入账号"
        /><br />
        <input type="submit" />
      </form>
    </body>
    <script>
      function check() {
        var account = document.getElementById("account").value;
        if (account != undefined && account != null && account != "") {
          return true;
        } else {
          alert("账号不能为空!!!");
          return false;
        }
      }
    </script>
  ```

#### 4.4.2 购物车全选效果

==事件属性的值中传输的参数的值为`this`时, `this`表示的是本元素的对象==

```html
<style>
    table {
      border-style: solid;
      border-collapse: collapse;
    }
    td {
      border-style: solid;
      padding: 5px 30px;
      text-align: center;
      vertical-align: middle;
    }
  </style>
  <script>
      /*实现全选或全不选*/
    function selectAll(element) {
      var allGoods = document.getElementsByName("goods");
      var checked = element.checked;
      allGoods.forEach((goods) => {
        goods.checked = checked;
      });
    }
  </script>
  <body>
    <table>
      <tr>
        <td>
           <!-- 方法的参数为this时, this表示的时本元素的对象 -->
          <input type="checkbox" onchange="selectAll(this)" />
          全选
        </td>
        <td>名称</td>
        <td>单价(元)</td>
      </tr>
      <tr>
        <td><input type="checkbox" name="goods" /></td>
        <td>方便面</td>
        <td>3.00</td>
      </tr>
      <tr>
        <td><input type="checkbox" name="goods" /></td>
        <td>酸奶</td>
        <td>5.00</td>
      </tr>
      <tr>
        <td><input type="checkbox" name="goods" /></td>
        <td>火腿肠</td>
        <td>4.00</td>
      </tr>
    </table>
  </body>
```

#### 4.4.3 表格隔行变色

```html
<style>
    table {
      border-style: solid;
      border-collapse: collapse;
    }
    td {
      border-style: solid;
      padding: 5px 30px;
      text-align: center;
      vertical-align: middle;
    }
  </style>
  <script>
      /*实现全选或全不选*/
    function selectAll(element) {
      var allGoods = document.getElementsByName("goods");
      var checked = element.checked;
      allGoods.forEach((goods) => {
        goods.checked = checked;
      });
    }
  </script>
  <body>
    <table>
      <tr>
        <td>
          <input type="checkbox" onchange="selectAll(this)" />
          全选
        </td>
        <td>名称</td>
        <td>单价(元)</td>
      </tr>
      <tr>
        <td><input type="checkbox" name="goods" /></td>
        <td>方便面</td>
        <td>3.00</td>
      </tr>
      <tr>
        <td><input type="checkbox" name="goods" /></td>
        <td>酸奶</td>
        <td>5.00</td>
      </tr>
      <tr>
        <td><input type="checkbox" name="goods" /></td>
        <td>火腿肠</td>
        <td>4.00</td>
      </tr>
    </table>
    <script>
        /*实现表格隔行变色*/
      var trArray = document.getElementsByTagName("tr");
      for (var i = 0; i < trArray.length; i++) {
        if (i % 2 == 1) {
          trArray[i].style.backgroundColor = "rgb(172, 151, 194)";
        }
      }
    </script>
  </body>
```

## 五. 事件

js捕获某个动作而做出的反馈

HTML事件的例子:

- 当用户点击鼠标时 -- onclick
- 当网页已加载时 -- onload
- 当鼠标移动到元素上时 -- onmouseover
- 当输入字段被改变时 -- onchange(输入框失去了焦点, 且字段发生了改变)
- 当选项的勾选状态改变时 -- onchange
- 当HTML表单被提交时 -- onsubmit
- 当用户触发按键时 -- onkeydown

### 5.1 窗口事件(Window Event)

**仅在`<body>`元素和`<iframe>` `<frameset>`元素中有效**

- onload    文档或框架被完全载入的事件

### 5.2 表单元素事件(Form Element Events)

- **仅在表单元素及表单控件元素中有效**
  - onblur    元素失去焦点的事件
  - onfocus    元素获得焦点的事件
  - onsubmit
  - onchange
    - 对于文本输入框    失去焦点且输入字段发生改变的事件
    - 对于单选框和多选框    勾选状态发生改变的事件
- 阻止表单提交的几种方式
  - `onsubmit="return false"`, false可以换成方法, 方法的返回值是true则提交, 返回值是false则不提交
  - `onclick="return false"`, false可以换成方法, 方法的返回值是true则提交, 返回值是false则不提交
  - `onkeydown="return false"`, false可以换成方法, 方法的返回值是true则提交, 返回值是false则不提交

### 5.3 鼠标事件(Mouse Event)

- onclick    元素被鼠标单击的事件
- ondblclick    元素被鼠标双击的事件
- onmouseout    鼠标指针移除元素区域的事件
- onmouseover    鼠标指针悬浮于元素之上的事件

### 5.4 键盘事件

**注意, 当使用键盘向元素中输入一个字母时, 按键在按下去和按着的过程中, 字母是没有输入到元素中的, 只有当按键弹上来字母才会输入到元素中**

- onkeydown    一个键盘按键被按下去的事件(只要按了就行)
- onkeypress    一个键盘按键被按住不动的事件
- onkeyup    一个键盘按键被按下去后弹上来的事件

### 5.5 `event`对象

- 在一个网页中只有一个事件对象, 这个事件对象会在元素之间进行传递(事件冒泡或事件捕捉)
- ==在javascript中`event`表示当前网页中的事件对象==
- `event.keyCode`可以获取被按的按键值

### 5.6 事件监听器

#### 5.6.1 添加事件监听器的方式

##### 5.6.1.1 元素行内添加是监听器

- 这种添加方式中, 作为事件处理器的方法可以有参数, 也可以有返回值, 当this作为参数时, 表示的是本元素的元素对象

```html
  <body>
   <p><button id="honoka" onclick="honoka(this)">穗乃果</button></p>
  </body>
  <script>
    function honoka(element) {
      console.log("-----------------");
      console.log("点击了穗乃果");
      console.log(element);
      console.log(event.type);
      console.log("-----------------");
    }
  </script>
```

##### 5.6.1.2 通过元素对象的addEventListener方法添加

- **作为事件处理器的方法必须是无参数并且无返回值的函数,** 在该函数中this表示本元素的元素对象, event表示发生的这个事件的事件对象
- 注意: 当作为事件处理器的方法以lambda表达式方式编写时, 在该方法中的this表示的是window对象
- **这种方式时, 该元素可以多次调用该方法对同一个事件添加多个事件处理器, 该事件发生时这些事件处理器会根据添加顺序依次执行**

###### 5.6.1.2.1 事件处理器是个直接编写的匿名函数

- 这个匿名函数必须是无参且无返回值
- 在该函数中this表示本元素的元素对象, event表示发生的这个事件的事件对象

```html
  <body>
    <p><button id="mercy">Mercy</button></p>
  </body>
  <script>
    //1. 在参数中直接编写事件处理器
    var btnMercy = document.getElementById("mercy");
    btnMercy.addEventListener("click", function () {
      console.log("-----------------");
      console.log("点击了Mercy");
      console.log(this);
      console.log("-----------------");
    });
  </script>
```

###### 5.6.1.2.2 事件处理器调用了构造函数

- 这个构造函数必须无参且无返回值, **调用该函数时只写该构造函数的名字, 不能有`()`**
- 在该函数中this表示本元素的元素对象, event表示发生的这个事件的事件对象

```html
  <body>
    <p><button id="aerith">爱丽丝</button></p>
  </body>
  <script>
    //2. 事件处理器是个构造函数
    var btnAerith = document.getElementById("aerith");
    btnAerith.addEventListener("click", demo);
    function demo() {
      console.log("-----------------");
      console.log("点击了" + this.innerText);
      console.log(this);
      console.log(event.type);
      console.log("-----------------");
    }
  </script>
```

###### 5.6.1.2.3 事件处理器是以lambda表达式的方式编写的匿名函数

- 注意: 当作为事件处理器的方法以lambda表达式方式编写时, 在该方法中的this表示的是window对象

```html
  <body>
    <p><button id="tifa">蒂法</button></p>
  </body>
  <script>
    //3. 以lambda表达式方式编写事件处理器
    var btnTifa = document.getElementById("tifa");
    btnTifa.addEventListener("click", () => {
      console.log("-----------------");
      console.log("点击了" + this.innerText);
      console.log(this);
      console.log(event.type);
      console.log("-----------------");
    });
  </script>
```

##### 5.6.1.3 通过元素对象的事件属性添加

- 跟[5.6.1.2 通过元素对象的addEventListener方法添加](#5.6.1.2 通过元素对象的addEventListener方法添加)一样, 事件处理器有三种编写方式
- **作为事件处理器的方法必须空参, 但可以有返回值**
- ==这种方式时, 该元素的同一个事件只能添加一个事件处理器, 后添加的事件处理器会替换掉前一个事件处理器, 当事件处理器为null时, 就不会对该事件进行处理了==

```html
 <body>
    <p><button id="mercy">Mercy</button></p>
    <p><button id="aerith">爱丽丝</button></p>
    <p><button id="tifa">蒂法</button></p>
  </body>
  <script>
    //1. 在参数中直接编写事件处理器
    var btnMercy = document.getElementById("mercy");
    btnMercy.onclick = function () {
      console.log("-----------------");
      console.log("点击了Mercy");
      console.log(this);
      console.log("-----------------");
    };

    //2. 事件处理器是个构造函数
    var btnAerith = document.getElementById("aerith");
    btnAerith.onclick = demo;
    function demo() {
      console.log("-----------------");
      console.log("点击了" + this.innerText);
      console.log(this);
      console.log(event.type);
      console.log("-----------------");
    }

    //3. 以lambda表达式方式编写事件处理器
    var btnTifa = document.getElementById("tifa");
    btnTifa.onclick = () => {
      console.log("-----------------");
      console.log("点击了" + this.innerText);
      console.log(this);
      console.log(event.type);
      console.log("-----------------");
    };
  </script>
```

#### 5.6.2 移除事件监听器

- 以[5.6.1.1 元素行内添加是监听器](#5.6.1.1 元素行内添加是监听器)方式添加的事件监听器无法移除
- 以[5.6.1.2 通过元素对象的addEventListener方法添加](#5.6.1.2 通过元素对象的addEventListener方法添加)方式添加的事件监听器, 只能通过`removeEventListener(Event.type, listener, option):void`方法移除, **只要listener相同就可以移除**
- 以[5.6.1.3 通过元素对象的事件属性添加](#5.6.1.3 通过元素对象的事件属性添加)方式添加的事件监听器, 只能通过给该事件属性赋值null的方式移除

### 5.7 事件传递

- 在嵌套元素中, 在内层元素发生的事件按逻辑来说在外层元素上也发生了, 事件只有这么一个, 那就需要一个元素处理完再交由下一个元素去处理, 一直到最后一个元素
- **在嵌套元素中, 内层元素上发生的事件才需要传递**
- **在嵌套的元素中, 事件有两种传递方式: 事件冒泡和事件捕捉**

#### 5.7.1 事件冒泡

- 在嵌套的元素中, 发生在内层元素上的事件由内而外地传递
- 默认以事件冒泡的方式传递事件(options的值为false时, 以事件冒泡方式传递事件)

```html
 <style>
    div {
      background-color: bisque;
      width: 600px;
      height: 600px;
    }
    img {
      height: 400px;
    }
  </style>
  <body>
    <div id="outer">
      <img id="inner" src="image/nami (10).jpg" />
    </div>
    <script>
      var outer = document.getElementById("outer");
      var inner = document.getElementById("inner");
      outer.addEventListener("click", (ev) => {
        console.log(ev.type);
        console.log("点击了" + outer.id);
      });
      inner.addEventListener("click", (ev) => {
        console.log(ev.type);
        console.log("点击了" + inner.id);
      });
    </script>
  </body>
```



#### 5.7.2 事件捕获

- 在嵌套的元素中, 发生在内层元素上的事件由外而内地传递
- options的值为true时, 以事件捕捉方式传递事件

```html
 <style>
    div {
      background-color: bisque;
      width: 600px;
      height: 600px;
    }
    img {
      height: 400px;
    }
  </style>
  <body>
    <div id="outer">
      <img id="inner" src="image/nami (10).jpg" />
    </div>
    <script>
      var outer = document.getElementById("outer");
      var inner = document.getElementById("inner");
      outer.addEventListener(
        "click",
        (ev) => {
          console.log(ev.type);
          console.log("点击了" + outer.id);
        },
        true//options的值为true时, 以事件捕捉方式传递事件
      );
      inner.addEventListener(
        "click",
        (ev) => {
          console.log(ev.type);
          console.log("点击了" + inner.id);
        },
        true//options的值为true时, 以事件捕捉方式传递事件
      );
    </script>
  </body>
```



#### 5.7.3 停止事件的传递

- event.stopPropagation()    阻止事件向下一层传递

- 在内层元素上发生的事件以事件冒泡方式传递时, 需要在内层阻止了事件向下一层传递

  - ```html
     <style>
        div {
          background-color: bisque;
          width: 600px;
          height: 600px;
        }
        img {
          height: 400px;
        }
      </style>
      <body>
        <div id="outer">
          <img id="inner" src="image/nami (10).jpg" />
        </div>
        <script>
          var outer = document.getElementById("outer");
          var inner = document.getElementById("inner");
          outer.addEventListener("click", (ev) => {
            console.log(ev.type);
            console.log("点击了" + outer.id);
          });
          inner.addEventListener("click", (ev) => {
            console.log(ev.type);
            console.log("点击了" + inner.id);
            //事件冒泡方式传递时, 阻止了事件向下一层传递
            ev.stopPropagation();
          });
        </script>
      </body>
    ```

- 在内层元素上发生的事件以事件捕捉方式传递时, 需要在外层阻止了事件向下一层传递

  - ```html
    <style>
        div {
          background-color: bisque;
          width: 600px;
          height: 600px;
        }
        img {
          height: 400px;
        }
      </style>
      <body>
        <div id="outer">
          <img id="inner" src="image/nami (10).jpg" />
        </div>
        <script>
          var outer = document.getElementById("outer");
          var inner = document.getElementById("inner");
          outer.addEventListener(
            "click",
            (ev) => {
              console.log(ev.type);
              console.log("点击了" + outer.id);
              //事件捕捉方式传递时, 阻止了事件向下一层传递
              ev.stopPropagation();
            },
            true
          );
          inner.addEventListener(
            "click",
            (ev) => {
              console.log(ev.type);
              console.log("点击了" + inner.id);
            },
            true
          );
        </script>
      </body>
    ```

## 六. 面向对象OOP

### 6.1 使用Object创建通用对象

```javascript
      var user = new Object();
      user.name = "蒂法";
      user.age = 30;
      user.say = function () {
        console.log(
          "大家好, 我叫" + this.name + ", 我今年" + this.age + "岁了."
        );
      };

      //调用对象中的方法
      console.log(user.name);
      console.log(user.age);
      user.say();
```

### 6.2 使用构造函数创建对象

```js
      function userInfo(name, age) {
        this.name = name;
        this.age = age;
        this.say = function () {
          console.log(
            "大家好, 我叫" + this.name + ", 我今年" + this.age + "岁了."
          );
        };
      }
      //调用对象中的方法;
      var user = new userInfo("爱丽丝", 23);
      console.log(user.name);
      console.log(user.age);
      user.say();
```

### 6.3 使用直接量创建对象

- 实际即使使用`json`创建对象
- 成员赋值时使用`:`
- 注意成员之间使用`,`分隔, 最后一个成员后不写`,`

```js
      var user = {
        name: "Mercy", //注意成员之间使用,分隔, 最后一个成员后不写,
        age: 33,
        say: function () {
          console.log(
            "大家好, 我叫" + this.name + ", 我今年" + this.age + "岁了."
          );
        },
      };
      //调用对象中的方法
      console.log(user.name);
      console.log(user.age);
      user.say();
```



## 七. JSON

- JSON(JavaScript Object Notation)是一种轻量级的数据交换格式
- 易于人阅读和编写, 同时也易于机器解析和生成

- 格式:

  - 单个对象

    - ```js
      {属性1 : 值1, 属性2 : 值2, 方法名1 : function(参数列表){方法体}, ... , 属性n : 值n}
      ```

  - 多个对象组成的数组

    - ```js
      [对象1, 对象2, 对象3, ... , 对象n]
      ```
    
  - 属性不能有引号`""`, 属性值按需写引号`""`

### 7.1 单个对象

```js
var tifa = {name : "蒂法", age : 30};
var aerith = {name : "艾丽丝", age : 23};

console.log("姓名: " + tifa.name + ", 年龄: " + tifa.age);
console.log("姓名: " + aerith.name + ", 年龄: " + aerith.age);
```

### 7.2 多个对象组成的数组

```js
var personArr = [{name : "蒂法", age : 30}, {name : "艾丽丝", age : 23}];

console.log("姓名: " + personArr[0].name + ", 年龄: " + personArr[0].age);
console.log("姓名: " + personArr[1].name + ", 年龄: " + personArr[1].age);
```

### 7.3在JS中字符串与JSON对象互转

#### 7.3.1 JSON对象转字符串

- ```js
  var str = JSON.stringify({name:"汉库克", gender:"女", age:33});
  ```

#### 7.3.2 字符串转JSON对象

-  ```js
  var json = JSON.parse("{name:"汉库克", gender:"女", age:33}");
  ```


## 八. 通过js中的BOM来管理浏览器

- Brower Object Model是用来管理浏览器的, 有多个对象可以使用
- `window`  ---  浏览器窗口对象
  - `window.screen`  ---  电脑屏幕对象
- `navigator`  ---  浏览器信息对象
- `location`  ---  定位对象
- `history`  ---  浏览器历史记录对象
- `localStorage`  ---  本地存储对象
- `sessionStorage`  ---  会话存储对象

### 8.1 浏览器窗口对象`window`

`window`对象是浏览器窗口的实例, 所以可以获取浏览器的一些信息, 也可以对浏览器窗口进行相应的控制

#### 8.1.1 浏览器窗口特征(Window Feature)

**注意这是浏览器窗口特征, 而非`window`对象的属性**

| 特征        | 说明                                                         |
| ----------- | ------------------------------------------------------------ |
| channelmode | yes\|no\|1\|0<br />是否使用剧院模式, yes或1表示使用, no或0表示不使用<br />默认为no |
| directories | yes\|no\|1\|0<br />是否添加目录按钮,  yes或1表示添加, no或0表示不添加<br />默认为yes |
| fullscreen  | yes\|no\|1\|0<br />是否使用全屏模式显示浏览器, yes或1表示使用, no或0表示不使用<br />默认为no |
| height      | 网页显示区域的高度, 单位:px                                  |
| width       | 网页显示区域的宽度, 单位:px                                  |
| left        | 浏览器窗口的原点在屏幕中的横坐标                             |
| top         | 浏览器窗口的原点在屏幕中的纵坐标                             |
| resizable   | yes\|no\|1\|0<br />是否可调节尺寸,  yes或1表示可调节 no或0表示不可调节<br />默认为yes |
| location    | yes\|no\|1\|0<br />是否显示地址字段,  yes或1表示显示 no或0表示不显示<br />默认为yes |
| menubar     | yes\|no\|1\|0<br />是否显示菜单栏,  yes或1表示显示 no或0表示不显示<br />默认为yes |
| scrollbars  | yes\|no\|1\|0<br />是否显示滚动条(横向滚动条和纵向滚动条),  yes或1表示显示, no或0表示不显示<br />默认为yes |
| status      | yes\|no\|1\|0<br />是否显示状态栏,  yes或1表示显示, no或0表示不显示<br />默认为yes |
| titlebar    | yes\|no\|1\|0<br />是否显示标题栏,  yes或1表示显示, no或0表示不显示<br />默认为yes |
| toolbar     | yes\|no\|1\|0<br />是否显示工具栏,  yes或1表示显示, no或0表示不显示<br />默认为yes |

#### 8.1.2 `window`对象常用的属性和方法

##### 8.1.2.1 `windown`对象常用的属性

| 属性        | 说明                                                         |
| ----------- | ------------------------------------------------------------ |
| innerHeight | 网页区域的高度                                               |
| outerHeight | 整个浏览器的高度                                             |
| innerWidth  | 网页区域的宽度                                               |
| outWidth    | 整个浏览器的宽度                                             |
|             |                                                              |
| document    | 在本浏览器上当前显示的网页的document对象                     |
| frames      | 在本浏览器上当前显示的网页上的所有iframe元素组成的集合<br />只能通过frames[ frameId ]来获取这个iframe元素的对象, 注意: **frameId指的是iframe元素的id值** |
|             |                                                              |
| screen      | 屏幕的对象                                                   |

##### 8.1.2.2 `window`对象常用的方法

| 属性                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| open(url?: string, target?: string, features?: string, replace?: boolean): Window | 在一个新的浏览器窗口中打开url<br />url是要打开的网址, 必须有<br />target, feature, replace是可选的, 可写可不写<br />target可以是跟url不相关的字符串<br />target, feature同时存在时会在新的浏览器窗口打开url; 如果target和feature只有一个或者都没有的话, 就会在新标签页打开url |

```html
  <body>
    <img style="height: 350px" src="image/burin (3).gif" /><br />
    <button onclick="openNewWindow()">在新窗口打开网页</button><br />
  </body>
  <script>
    console.log(window.innerHeight);
    console.log(window.outerHeight);
    console.log(window.document);
    function openNewWindow() {
      window.open("https://www.baidu.com", "", "width=600,height=300");//这里的target是个空字符串也行
    }
  </script>
```

#### 8.1.3  电脑屏幕对象`window.screnn`

##### `screen`对象常用的属性

| 属性   | 说明                 |
| ------ | -------------------- |
| width  | 屏幕的宽度, 单位: px |
| height | 屏幕的高度, 单位: px |

### 8.2  浏览器对象`navigator`

#### `navigator`对象常用的属性

| 属性          | 说明                               |
| ------------- | ---------------------------------- |
| appCodeName   | 浏览器的代号                       |
| appName       | 浏览器的名称                       |
| appVersion    | 浏览器的版本                       |
| platform      | 硬件平台, 即浏览器运行在什么系统上 |
| userAgent     | 用户代理                           |
| cookieEnabled | 是否启用了cookie, boolean属性      |

### 8.3 定位对象`location`

> 包含当前显示网页的url信息, 通常用来做页面跳转

#### 8.3.1 `location`对象常用的属性

| 属性     | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| href     | 1. 获取当前网页的url, 就是会获取到`http://locathost:8080/index.html`的全部<br />2. 赋值一个新的url后, 当前网页会自动跳转到新的网页中, **注意是直接在当前标签页上跳转, 不会打开新的标签页** |
| pathname | 1. 获取当前网页的uri, 就是会获取到`http://locathost:8080/index.html`中的`/index.html`<br />2. 赋值一个新的uri后, 当前网页会自动跳转到新的网页中, **注意是直接在当前标签页上跳转, 不会打开新的标签页** |

#### 8.3.2 `location`对象常用的方法

| 方法     | 说明         |
| -------- | ------------ |
| reload() | 刷新当前页面 |

#### 8.3.3 示例代码

```html
<body>
    <button onclick="reload()">刷新</button><br />
    <button onclick="showUrl()">显示当前页面的网址</button
    ><span style="border-style: solid" id="url"></span><br />
    <button onclick="skip()">跳转</button>
  </body>
  <script>
    function reload() {
      location.reload();
    }
    function showUrl() {
      var url = location.href;
      document.getElementById("url").innerHTML = decodeURI(url);
    }
    function skip() {
      location.href = "https://www.baidu.com";
    }
  </script>
```

### 8.4 浏览器历史记录对象`history`

#### 8.4.1 常用的方法

| 方法   | 说明           |
| ------ | -------------- |
| back() | 返回上一级页面 |
| go(-1) | 返回上一级页面 |

#### 8.4.2 示例代码

##### 31-上一级页面.html

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>31-上一级页面</title>
  </head>
  <body>
    <a href="32.下一页页面.html"下一页</a>
  </body>
</html>

```

##### 32-下一页页面.html

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>32-下一页页面</title>
  </head>
  <body>
    <button onclick="previous()">返回上一页</button>
  </body>
  <script>
    function previous() {
      history.back();
    }
  </script>
</html>

```

### 8.5 存储对象

- 有两种存储对象, 本地存储对象`localStorage`和会话存储对象`sessionStorage`
- 每一个标签页都有一个本地存储对象`localStorage`
- 浏览器从打开到关闭的这段时间是一个会话, 所以只有一个会话存储对象`sessionStorage`

#### 8.5.1 本地存储对象`localStorage`

- 每一个标签页打开到关闭的这段时间内会有一个专有的本地存储对象`localStorage`, 当这个标签页关闭时, 其独有的`localStorage`对象就会被回收掉
- 也就是说在同一个标签页上打开的王也会共用一个`localStorage`对象

##### 常用的方法

| 方法                                             | 说明     |
| ------------------------------------------------ | -------- |
| setItem( itemName : string , itemValue : string) | 保存数据 |
| getItem( itemName : string )                     | 获取数据 |
| removeItem( itemName : string )                  | 移除数据 |

- 也可以通过`localStorage[itemName]`的方式获取数据
- 通过``localStorage[itemName] = itemValue`的方式保存数据
- 注意itemName和itemValue的类型都是string

#### 8.5.2 会话存储对象`sessionStorage`

- 浏览器从打开到关闭的这段时间是一个会话, 所以这段时间只有一个会话存储对象`sessionStorage`, 当浏览器关闭时这个对象就会被回收掉
- 使用一个浏览器窗口的期间又打开了另一个窗口, 这两个窗口属于同一个会话, 所以使用的是同一个会话存储对象
- 也就是说在一个会话期间浏览器上打开的网页和标签页共用一个`sessionStorage`对象

##### 常用的方法

| 方法                                             | 说明     |
| ------------------------------------------------ | -------- |
| setItem( itemName : string , itemValue : string) | 保存数据 |
| getItem( itemName : string )                     | 获取数据 |
| removeItem( itemName : string )                  | 移除数据 |

- 也可以通过`sessionStorage[itemName]`的方式获取数据
- 通过`sessionStorage[itemName] = itemValue`的方式保存数据
- 注意itemName和itemValue的类型都是string

##### 示例代码

```html
  <body>
    <button onclick="openNewWindow()">在新的浏览器中打开本网页</button>
    <button onclick="setItem()">设置一个sessionItem</button>
  </body>
  <script>
    console.log(sessionStorage.getItem("test"));
    function openNewWindow() {
      window.open("33.上一个浏览器.html", "", "width=300,height=300");
    }

    function setItem() {
      sessionStorage.setItem("test", "666");
      console.log(sessionStorage.getItem("test"));
    }
  </script>
```



## 九. 定时操作功能

- 跟定时操作功能相关的全局函数有3个

- | 函数                                    | 功能                                                         |
  | --------------------------------------- | ------------------------------------------------------------ |
  | setInterval(handler , timeout) : number | 1.handler可以以匿名函数的方式直接编写, 这个匿名函数**必须无参且无返回值**; handler也可以是调用构造函数, 该构造函数**必须无参且无返回值**, **并且调用时不能写**`()`<br />2. timeout的单位是毫秒<br />3. 表示调用本方法后, **每隔timeout毫秒就执行一次handler方法**<br />4. 返回值是个数字, 用来标识本定时操作 |
  | setTimeout(handler , timeout ) : number | 1. handler可以以匿名函数的方式直接编写, 这个匿名函数必须无参也无返回值; handler也可以是调用构造函数, 该构造函数必须无参也无返回值, 并且调用时不能写`()`<br />2. timeout的单位是毫秒<br />3. 表示调用本方法后, **在timeout毫秒后执行一次handler方法, 注意只执行一次**<br />4. 返回值是个数字, 用来标识本定时操作 |
  | clearInterval( intervalNumber ): void   | 用来取消返回值为 intervalNumber的定时操作                    |


### 9.1 编写一个时钟

```html
<style>
    ul {
      list-style-type: none;
      font-size: 2.5em;
      padding-left: 0px;
      font-weight: bold;
    }
    li {
      display: inline;
      margin-right: -12px;
    }

    @keyframes hiddenVisible {
      0% {
        color: black;
      }
      50% {
        color: rgba(0, 0, 0, 0);
      }
      100% {
        color: black;
      }
    }
    .colon {
      animation-name: hiddenVisible;
      animation-duration: 1s;
      animation-timing-function: ease-in;
      animation-direction: alternate;
      animation-iteration-count: infinite;
    }
  </style>
  <body onload="setClock()">
    <ul>
      <li id="year">2021</li>
      <li>年</li>
      <li id="month">12</li>
      <li>月</li>
      <li id="day">31</li>
      <li>日&nbsp;&nbsp;</li>
      <li id="hour">24</li>
      <li class="colon">&nbsp;:&nbsp;</li>
      <li id="minute">59</li>
      <li class="colon">&nbsp;:&nbsp;</li>
      <li id="second">59</li>
    </ul>
  </body>
  <script>
    var date, year, month, day, hour, minute, second, colonArr;

    function setClock() {
      year = document.getElementById("year");
      month = document.getElementById("month");
      day = document.getElementById("day");
      hour = document.getElementById("hour");
      minute = document.getElementById("minute");
      second = document.getElementById("second");
      colonArr = document.getElementsByClassName("colon");
      // handler时setTimer()方法, setTimer方法不能有参数,不能有返回值, qie'zuo'wei
      setInterval(setTimer, 1000);
    }

    function setTimer() {
      date = new Date();
      year.innerHTML = date.getFullYear();
      month.innerHTML = date.getMonth() + 1 + "";
      day.innerHTML = date.getDate();
      hour.innerHTML = date.getHours();
      minute.innerHTML = date.getMinutes();
      second.innerText = date.getSeconds() + "";
    }
  </script>
```

### 9.2 暂停选妹子

```html
  <style>
    img {
      height: 450px;
    }
    button {
      font-size: 2em;
      padding: 5px 15px;
    }
  </style>
  <body>
    <div style="height: 460px">
      <img style="height: 450px" id="container" src="image/nami (1).jpg" />
    </div>
    <button id="start" onclick="selectImage.start()">开始</button
    >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <button id="stop" onclick="selectImage.stop()">停止</button>
  </body>
  <script>
    var selectImage = new Object();
    selectImage.container = null;
    selectImage.interValNumber = null;
    selectImage.randomInt = null;
    selectImage.hadStart = false;
    selectImage.start = function () {
      container = document.getElementById("container");
      if (!selectImage.hadStart) {
        console.log("开始随机换图");
        selectImage.intervalNumber = setInterval(selectImage.randomChange, 100);
        selectImage.hadStart = true;
      } else {
        console.log("已经开始随机换图");
      }
    };

    selectImage.randomChange = function () {
      randomInt = Math.round((Math.floor(Math.random() * 10) + 1) * 1.2);
      container.src = "image/nami (" + randomInt + ").jpg";
    };

    selectImage.stop = function () {
      clearInterval(selectImage.intervalNumber);
      selectImage.hadStart = false;
      console.log("停止随机换图");
        console.log("此时的随机数是: " + randomNumber);
    };
  </script>
```

## 十. 动态地添加动画帧`@keyframes`

- DOM可以一个style标签封装成CSSStyleSheet对象, CSSStyleSheet对象可以动态地添加或删除css选择器和动画帧
- `document.styleSheets`可以获取本页面所有`<style>`标签封装而成的CSSStyleSheet对象组成的集合中
  - **动态添加的`<style>`节点的CSSStyleSheet对象是这个集合的最后一个元素**
- `CSSStyleSheet`对象可以通过方法`CSSStyleSheet.insertRule(rule: string, index?: number): number`来添加一个选择器或者一个动画帧
  - rule的格式可以是: 
    - `"div{height: 400px; background-color: red}"`
    - 或者`"@keyframes zoomIn{0%{...} 50%{...} 100%{...}}"`
  - index是指这个选择器或者动画帧规则的在style标签中的插入位置, 一般设为0即可
  - 返回值是这个选择器规则或者动画帧规则在style标签中的位置
- `CSSStyleSheet`对象可以通过方法`CSSStyleSheet.removeRule(index?: number): void`移除掉指定的规则
  - index是指这个规则的位置

## 十一. 高精度计算

- javascript自带的四则运算无法进行高精度计算, 所以需要引用`Math.js`程序库

- Math.js提供了进行高精度计算的各种方法, 以及单位转换, 货币转换, 还有其它的高级数学运算

- 这里主要用到高精度计算

  - 首先,要调用`math.config( jsonObj )`来开启`math`对象的高精度计算功能

    - ```js
      const math.config ({
        // Default type of number
        // Available options: 'number' (default), 'BigNumber', or 'Fraction'
        number: 'BigNumber',
      
        // Number of significant digits for BigNumbers
        precision: 20
      });
      ```

  - 然后就可以调用`math.evaluate( str )`进行高精度数学运算了

    - ```js
      let result1 = math.evaluate("1 + 2 * 3 + 7 / 3");
      let result2 = math.evaluate("0.1 + 0.2");
      let result2 = math.evaluate("0.1 * 0.2");
      ```

    - 

















