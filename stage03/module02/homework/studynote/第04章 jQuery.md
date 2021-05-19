# 第04章 jQuery

[toc]

## 一. jQuery介绍

- jQuery由美国人Jhon Resi(约翰·莱西格)于2006年创建
- jQuery是目前最流行的JavaScript程序库, 它是对JavaScript对象和函数的进一步封装
  - jQuery其实就是一个js文件, 这个文件对JavaScript对象和函数的进行了进一步封装, 这样使用jQuery封装好的对象时就可以`Write less, Do more`了
- jQuery的设计思想是`Write less, Do more`

### 1.1 jQuery能做什么?

- 访问和操作DOM元素
- 控制DOM元素的css样式
- 对页面事件进行处理
- 扩展新的jQuery对象
- 与Ajax技术完美结合

### 1.2 jQuery的优势

- 体积小, 压缩后就只是一个100KB左右的js文件
- 强大的选择器
- 出色的DOM封装
- 可靠的事件处理机制
- 出色的浏览器兼容性

### 1.3 jQuery下载

- jQuery的官网: <https://jquery.com/>
- 进入jQuery官网, 点击`Download jQuery`
  - ![](%E7%AC%AC04%E7%AB%A0%20jQuery.assets/One_2021-04-16_135601.png)
- 点击`Download the compressed, production jQuery 3.6.0`下载压缩后的发布版本, 下载下来后是一个js文件

## 二. jQuery的使用

- jQuery作为一个单独存在的js文件, 并不会与其他的js文件发生冲突, 在页面中使用`<script>`元素引入这个js文件即可, 

- **这个用来引入jQuery的`<script>`元素最好写在`<body>`元素之前**

- **注意用来引入jQuery的`<script>`元素内是无法调用jQuery的对象的, 只有在该元素之后的`<script>`元素中才能调用jQuery里的对象**

- ```html
  <script src="../js/jquery-3.6.4.min.js"></script>
  <script>
  	//在这里使用jQuery里的对象
  </script>
  ```

### 2.1 基本的语法介绍

- jQuery中的工厂函数`$( str ) `可以用来获取指定的元素的jQuery对象, 也可以创建一个节点

- **注意: jQuery元素对象和JavaScript元素对象是不同种类的对象, 这两类对象有着各自的一套方法, 注意不要用串了, 否则会导致error**

- 当`$("selector")`调用方法时,  选择器"`selector`"对应的所有元素都会执行该方法所表示的操作

- ```html
  <script src="../js/jquery-3.6.4.min.js"></script>
  <script>
  	var jsElement = document.body;//js元素对象
      var jQueryElement = $("body");//jQury元素对象
      
      //js元素对象转jQuery元素对象
      var jQueryEle = $(jsElement);
      
      //jQuery元素对象转js元素对象
      var jsEle = jQueryElement.get(0);
  </script>
  ```

### 2.2 选择器

#### 2.2.1 基本选择器

| 名称       | 语法构成                                       | 描述                                                         | 示例                                           |
| ---------- | ---------------------------------------------- | ------------------------------------------------------------ | ---------------------------------------------- |
| 标签选择器 | `"tagName"`                                    | 所有标签名是tagName的元素                                    | `$("div")`                                     |
| 类选择器   | `".classValue"`                                | 所有class属性的值是classValue的元素                          | `$(".book")`                                   |
| id选择器   | `"#idValue"`                                   | **获取第一个**id属性值为idValue的元素<br />==id属性值是唯一的, 如果多个元素的id属性值相同的话, 那么只会去找第一个元素== | `$("#book1")`                                  |
| 交集选择器 | `"tagName.classValue"`或者`"tagNamae#idValue"` | 所有标签名是tagName的元素中 class属性的值是classValue的元素<br />所有标签名是tagName的元素中 id属性的值是idValue的元素 | `$("div.book")`<br />`$("div#book1")`          |
| 并集选择器 | `"selector1, selector2, ... , selectorN"`      | 匹配这些选择器的所有元素                                     | `$("div, .book, #book1, div.book, div#book1")` |
| 全局选择器 | `"*"`                                          | 网页中的所有元素                                             | `$("*")`                                       |

#### 2.2.2 层次选择器

| 名称           | 语法构成                | 描述                                                     | 示例                 |
| -------------- | ----------------------- | -------------------------------------------------------- | -------------------- |
| 后代选择器     | `"ancestor descendant"` | 选取ansestor元素下的后代元素中的匹配`descendant`  的元素 | `$("div span")`      |
| 子代选择器     | `"parent>child"`        | 选取parent元素下的子代元素中的匹配`child`  的元素        | `$("div>span")`      |
| 相邻元素选择器 | `"previous+next"`       | 选取紧邻previous元素之后的next元素                       | `$("#book1+.image")` |
| 同辈元素选择器 | `"previous~sibling"`    | 选取previous元素之后的同辈元素中的匹配`silbling`  的元素 | `$("#book1~.image")` |

#### 2.2.3 属性选择器

| 语法构成                                 | 描述                                                         | 示例                                       |
| ---------------------------------------- | ------------------------------------------------------------ | ------------------------------------------ |
| `"[attribute]"`                          | 设置了属性attribute的元素                                    | `$([class])`                               |
| `"[attribute='value']"`                  | 设置了属性attribute, 并且**值是**value的元素                 | `$("[class='book']")`                      |
| `"[attribute!='value']"`                 | 设置了属性attribute, 并且**值不是**value的元素               | `$("[class!='book']")`                     |
| `"[attrbute^='value']"`                  | 设置了属性attribute, 并且**值是以value开头**的元素           | `$("[class^='bo']")`                       |
| `"[attribute$='value']"`                 | 设置了属性attribute, 并且**值是以value结尾**的元素           | `$("[class$='ok']")`                       |
| `"[attribute*='value']"`                 | 设置了属性attribute, 并且**值包含value**的元素               | `$("[class*='oo']")`                       |
| `"[selector1][selector2]...[selectorN]"` | 同时满足属性选择器selector1, selector2, ... , selectorN的元素 | `$("[id][class='book'][name*='莫言 著']")` |

#### 2.2.4 过滤选择器

| 语法构成       | 描述                                           | 示例                                   |
| -------------- | ---------------------------------------------- | -------------------------------------- |
| :first         | 选取第一个元素                                 | `$("li:first")`                        |
| :last          | 选取最后一个元素                               | `$("li:last")`                         |
| :even          | 选取索引是偶数的所有元素(索引从0开始)          | `$("li:even")`                         |
| :odd           | 选取索引是奇数的所有元素(索引从0开始)          | `$("li:odd")`                          |
| :not(selector) | 选取**不匹配selector**选择器的所有元素         | `$("li:not(#book2)")`                  |
| :eq(index)     | 选取**索引值等于**index的所有元素(索引从0开始) | `$("li:eq(3)")`                        |
| :gt(index)     | 选取**索引值大于**index的所有元素(索引从0开始) | `$("li:gt(3)")`                        |
| :lt(index)     | 选取**索引值小于**index的所有元素(索引从0开始) | `$("li:lt(3)")`                        |
| :checked       | 被勾选的复选框元素或单选框元素                 | `$("input[type='checkbox']:checked")$` |

### 2.3 jQuery常用的方法

#### 2.3.1 事件方面的方法

##### 2.3.1.1 鼠标事件

- 鼠标事件适当用户的在文档上移动或单击鼠标时而产生的事件, 常用的鼠标事件有:

- | 方法                   | 描述                          | 执行时机   |
  | ---------------------- | ----------------------------- | ---------- |
  | `click( handler)`      | 事件发生时就会执行handler函数 | 单击鼠标时 |
  | `mouseover( handler)`  | 事件发生时就会执行handler函数 | 鼠标移过时 |
  | `mouserout( handler )` | 事件发生时就会执行handler函数 | 鼠标移出时 |

- handler有三种形式

  - handler是个无参的匿名函数, 
    - 可以有返回值, 也可以没有返回值
    - 在这个函数的函数体中, `this`表示该元素的**js元素对象**, `event`表示的是事件对象
  - handler是个无参的构造函数, 调用该构造函数时不能写小括号`()`
    - 可以有返回值, 也可以没有返回值
    - 在这个函数的函数体中, `this`表示该元素的**js元素对象**, `event`表示的是事件对象
  - handler是个无参的箭头函数
    - 可以有返回值, 也可以没有返回值
    - 在这个函数的函数体中, `this`表示该元素的BOM中的window对象,  `event`表示的是事件对象

```html
  <style>
    img {
      height: 300px;
    }
  </style>
  <script src="js/jquery-3.6.0.min.js"></script>
  <body>
    <img src="image/nami (10).jpg" />
  </body>
  <script>
    //handler是个个无参且无返回值的匿名函数
    $("img").click(function () {
      console.log("鼠标点击了本图片");
      console.log(this);
      console.log(event.type);
    });
    //handler是个无参且无返回值的构造函数, 调用该构造函数时不能写小括号
    $("img").mouseover(test);
    function test() {
      console.log("鼠标移动到了本图片上");
      console.log(this);
      console.log(event.type);
    }

    //handler是个无参且无返回值的箭头函数
    $("img").mouseout(() => {
      console.log("鼠标从本图片上移走了");
      console.log(this);
      console.log(event.type);
    });
  </script>
```

##### 2.3.1.2 键盘事件

- 用户每次按下或者释放键盘上的按键时就会产生键盘事件, 常用的键盘事件有:

- | 方法                 | 执行时机       |
  | -------------------- | -------------- |
  | `keydown( handler )` | 按下键盘按键时 |
  | `keyup( handler )`   | 释放键盘按键时 |

- **对于输入框, 只有键盘按键释放时, 文字才会输入到输入框中**

```html
  <style>
    input {
      font-size: 1.5em;
    }
  </style>
  <script src="js/jquery-3.6.0.min.js"></script>
  <body>
    <input type="text" />
    <h1></h1>
  </body>
  <script>
    $("input").keyup(function () {
      var str = $(this).val();
      $("h1").text(str);
    });
  </script>
```

##### 2.3.1.3 表单事件

- 当元素获得焦点时, 会触发`focus`事件, 失去焦点时, 会触发`blur`事件

- | 方法                 | 执行时机                                                     |
  | -------------------- | ------------------------------------------------------------ |
  | focus( handler )     | 获得焦点                                                     |
  | blur( handler )      | 失去焦点                                                     |
  | change( handler )    | 失去焦点后检测到值发生改变                                   |
  | submit( hander )     | 表单提交事件, 在handler中可以使用`event.preventDefault()`**阻止表单的提交** |
  | **submiet()**        | **提交本表单**                                               |
  | on("input", handler) | 检测到值发生改变                                             |
  
- 需求: 一个文本输入框, 开始时显示**请输入姓名...**  , 当文本框获取焦点时, 或判断文本框内的文字是否为**请输入姓名...**  , 是的话就清空, 不是的话就保留下来, 当文本框失去焦点时, 先判断文本框内的文字是否为空或全是空格, 是的话就换成**请输入姓名...**  , 不是的话 ,  就判断输入的文本是否在**["黑百合", "小美", "穗乃果"]**名单中, 并将结果显示出来,

```html
 <style>
    form {
      font-size: 1.5em;
    }
    input {
      font-size: 0.8em;
      margin-bottom: 10px;
    }
    span {
      background-color: rgba(230, 225, 221, 0.904);
      font-size: 0.8em;
      padding: 3px;
      color: red;
    }
  </style>
  <script src="js/jquery-3.6.0.min.js"></script>
  <body>
    <form action="https://www.baidu.com">
      姓名: <input id="name" value="请输入姓名..." />
      <span id="checkNameRes"></span>
    </form>
  </body>
  <script>
    var nameList = ["黑百合", "小美", "穗乃果"];
    $("form").submit(function(event){
        //阻止表单提交
        event.preventDefault();
    });
    $("#name")
      .focus(function () {
        $("#checkNameRes").text("");
        var str = $(this).val();
        if (str == "请输入姓名...") {
          $(this).val("");
        }
      })
      .blur(function () {
        var str = $(this).val();
        if (str.match(/^ *$/) || str == null) {
          $(this).val("请输入姓名...");
        } else {
          str = str.trim();
          console.log(str);
          if (nameList.indexOf(str) == -1) {
            $("#checkNameRes").text("'" + str + "'不在名单中");
          } else {
            $("#checkNameRes").text("'" + str + "'处于名单中");
          }
        }
      });
  </script>
```

##### 2.3.1.4 鼠标悬停复合事件

- `hovere(handlerIn, handlerOut)`方法相当于`mouseover`与`mouseout`事件的组合
- 需求: 鼠标移动到图片上时显示边框, 鼠标移出边框时去掉边框

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <script src="js/jquery-3.6.0.min.js"></script>
  <body>
    <img style="width: 300px" src="image/nami (12).jpg" />
  </body>
  <script>
    $("img").hover(
      function () {
        $(this)
          .css("border-style", "solid")
          .css("border-width", "10px")
          .css("border-color", "red");
      },
      function () {
        $(this).css("border-style", "none");
      }
    );
  </script>
</html>

```

##### 2.3.1.5 事件的动态绑定

- 相当于js元素对象的addEventListener方法
- `JQuery.on( events:string , handler : Function):JQuery` 
  - events是个表示事件的字符串, 可以表示一种事件, 也可以表示多种事件
    - 一种事件    示例: `"click"`
    - 多种事件    示例: `"click mouseover"`
- 在handler方法中可以通过event.type来获取当前事件的种类, `this`则可以获取当前元素的js元素对象
- **对同一个事件添加的多个事件监听器会累积, 而非替换**

```html
  <style>
    button {
      color: white;
      font-size: 1.5em;
      margin-bottom: 10px;
      padding: 5px 10px;
      background-image: linear-gradient(
        90deg,
        rgb(122, 17, 122),
        rgb(202, 12, 202),
        rgb(116, 12, 202)
      );
    }
  </style>
  <script src="js/jquery-3.6.0.min.js"></script>
  <body>
    <button id="btn1">click</button><br />
    <button id="btn2">click mouseover</button>
  </body>
  <script>
    $("#btn1").on("click", function () {
      console.log("鼠标点击了btn1");
    });
    $("#btn2").on("click mouseover", function () {
      if (event.type == "click") {
        console.log("鼠标点击了btn2");
      } else if (event.type == "mouseover") {
        console.log("鼠标移动了btn2上");
      }
    });
  </script>
```

##### 2.3.1.6 解除事件的绑定

`.off()`    该方法可以解除``2.3.1.1~2.3.1.5`中学习的方法 绑定的事件监听器

- `JQuery.off() : JQuery`    解除该元素绑定的所有事件监听器
- `JQuery.off(Event.type:string)`    解除该元素的Event.type绑定的所有监听器

##### 2.3.1.7 文档加载完毕的事件

- jQuery的动态绑定事件的方法是不能绑定文档加载("load")事件的, 需要用到专门的方法`ready()`
- `ready( handler ) : jQuery  `  当 DOM（document object model 文档对象模型）加载完毕且页面完全加载（包括图像）时发生 ready 事件。
  - **由于该事件在文档就绪后发生，因此把所有其他的 jQuery 事件和函数置于该事件中是非常好的做法。**
- **注意：ready() 方法不应该与` <body onload=""> `一起使用。**
- ==ready()方法只能由当前文档的jQuery对象调用==, 所以有两种书写方式
  - `$(document).ready( handler )`    全写
  - `$( handler )`    简写

##### 2.3.1.8 event.prevetDefault()与event.stopPropagation()的区别

- `event.prevetDefault()`
  - 会阻止这个事件默认的处理方式, 比如复选框点击事件发生后会默认改变选择状态, 提交按钮点击后会默认提交表单, 这些默认处理方式会被阻止;
  - 但是该方法不会阻止事件的传递, 
  - 凡是解决这个事件的默认处理方式都会被阻止, 就算该事件被传递到了另一个方法, 该特性也不会变
    - 比如点击提交按钮后, 执行该方法, 就会导致表单提交被阻止, 就算该事件对象被传递给了表单的submit方法, 该事件对象仍旧会阻止默认处理方式的执行
- `event.stopPropagation()`是停止这个事件冒泡传递下去, 但不会阻止事件的默认处理方式 
  - 比如点击提交按钮后, 会阻止点击事件传递给表单, 但不妨碍该事件的默认处理方式的执行, 表担任就会被提交
  - 比如点击复选框, 会阻止点击事件传递给父元素, 但不妨碍该复选框选择状态的改变

#### 2.3.2 元素的隐藏或显示方面的方法

##### 2.3.2.1 `show()`、`hide()`、`toggle()`

- 注意这些函数是异步函数, 也就是说这些函数都是在主线程之外的其他线程里执行的
- 具有动画效果， 沿着元素的左斜边缩放，同时透明度也发生变化, 直至元素完全显示或者完全隐藏, 元素完全隐藏时会让出元素的位置

- `JQuery.show(duration?: string | number, complete?: Function): JQuery`   沿着元素的左斜边显示元素
  - duration    显示元素的速度, **可选参数**, 默认值是0, 此时没有动画效果，可以取值以下三种
    - "slow"
    - "fast"
    - 数字, 数字表示是多少毫秒
  - complete方法是指显示完元素后要执行的操作, **可选参数,** 默认什么也不做
- `JQuery.hide(duration?: string | number, complete?: Function)`    沿着元素的左斜边隐藏元素, **元素完全隐藏时会让出元素的位置**
  - duration    显示元素的速度, **可选参数**, 默认值是0, 此时没有动画效果，可以取值以下三种
    - "slow"
    - "fast"
    - 数字, 数字表示是多少毫秒
  - complete方法是指隐藏完元素后要执行的操作, **可选参数,** 默认什么也不做
- `JQuery.toggle(duration?: string | number, complete?: Function`    沿着元素的左斜边切换元素的隐藏状态和显示状态, **元素切换为完全隐藏状态后会让出元素的位置**
  - duration    显示元素的速度, **可选参数**, 默认值是0, 此时没有动画效果，可以取值以下三种
    - "slow"
    - "fast"
    - 数字, 数字表示是多少毫秒
  - complete方法是指切换完元素状态后要执行的操作, **可选参数,** 默认什么也不做

```html
  <style>
    img {
      height: 300px;
    }
  </style>
  <script src="js/jquery-3.6.0.min.js"></script>
  <body>
    <img src="image/nami (8).jpg" />
    <div>
      <button id="show">显示</button>
      <button id="hide">隐藏</button>
      <button id="toggle">切换</button>
    </div>
  </body>
  <script>
    $("#show").click(function () {
      $("img").show("slow");
    });
    $("#hide").on("click", function () {
      $("img").hide("fast");
    });
    $("#toggle").on("click", function () {
      $("img").toggle(1000);
    });
  </script>
```

##### 2.3.2.2 `slideDown()`、`slideUp()`、`slideToggle()`

- 注意这些函数是异步函数, 也就是说这些函数都是在主线程之外的其他线程里执行的
- 具有动画效果， 沿着元素的左斜边缩放，透明度不发生变化, 直至元素完全显示或者完全隐藏, 元素完全隐藏时会让出元素的位置

- `JQuery.slideDown(duration?: string | number, complete?: Function): JQuery`   沿着元素的左斜边显示元素
  - duration    显示元素的速度, **可选参数**, 默认值是0, 此时仍具有动画效果，可以取值以下三种
    - "slow"
    - "fast"
    - 数字, 数字表示是多少毫秒
  - complete方法是指显示完元素后要执行的操作, **可选参数,** 默认什么也不做
- `JQuery.slideUp(duration?: string | number, complete?: Function)`    沿着元素的左斜边隐藏元素, **元素完全隐藏时会让出元素的位置**
  - duration    显示元素的速度, **可选参数**, 默认值是0, 此时仍具有动画效果，可以取值以下三种
    - "slow"
    - "fast"
    - 数字, 数字表示是多少毫秒
  - complete方法是指隐藏完元素后要执行的操作, **可选参数,** 默认什么也不做
- `JQuery.slideToggle(duration?: string | number, complete?: Function`    沿着元素的左斜边切换元素的隐藏状态和显示状态, **元素切换为完全隐藏状态后会让出元素的位置**
  - duration    显示元素的速度, **可选参数**, 默认值是0, 此时仍具有动画效果，可以取值以下三种
    - "slow"
    - "fast"
    - 数字, 数字表示是多少毫秒
  - complete方法是指切换完元素状态后要执行的操作, **可选参数,** 默认什么也不做

```html
  <style>
    img {
      height: 300px;
    }
  </style>
  <script src="js/jquery-3.6.0.min.js"></script>
  <body>
    <img src="image/nami (8).jpg" />
    <div>
      <button id="slideDown">显示</button>
      <button id="slideUp">隐藏</button>
      <button id="slideToggle">切换</button>
    </div>
  </body>
  <script>
    $("#slideDown").click(function () {
      $("img").slideDown();
    });
    $("#slideUp").on("click", function () {
      $("img").slideUp("fast");
    });
    $("#slideToggle").on("click", function () {
      $("img").slideToggle(5000);
    });
  </script>
```

##### 2.3.2.3 `fadeIn()`、`fadeOut()`、`fadeToggle()`

- 注意这些函数是异步函数, 也就是说这些函数都是在主线程之外的其他线程里执行的
- 具有动画效果, 改变元素的透明度, 直至元素完全显示或者完全隐藏, 元素完全透明时会让出元素的位置
- `JQuery.fadeIn(duration?: string | number, complete?: Function): JQuery`   元素的透明度由小到大逐渐显示
  - duration    显示元素的速度, **可选参数**, 默认值是0, 此时仍具有动画效果，可以取值以下三种
    - "slow"
    - "fast"
    - 数字, 数字表示是多少毫秒
  - complete方法是指显示完元素后要执行的操作, **可选参数,** 默认什么也不做
- `JQuery.fadeOut(duration?: string | number, complete?: Function)`    元素的透明度由大到小直至完全隐藏, **元素完全隐藏时会让出元素的位置**
  - duration    显示元素的速度, **可选参数**, 默认值是0, 此时仍具有动画效果，可以取值以下三种
    - "slow"
    - "fast"
    - 数字, 数字表示是多少毫秒
  - complete方法是指隐藏完元素后要执行的操作, **可选参数,** 默认什么也不做
- `JQuery.fadeToggle(duration?: string | number, complete?: Function`    沿着元素的左斜边切换元素的隐藏状态和显示状态, **元素切换为完全隐藏状态后会让出元素的位置**
  - duration    显示元素的速度, **可选参数**, 默认值是0, 此时仍具有动画效果，可以取值以下三种
    - "slow"
    - "fast"
    - 数字, 数字表示是多少毫秒
  - complete方法是指切换完元素状态后要执行的操作, **可选参数,** 默认什么也不做

```html
  <style>
    img {
      height: 300px;
    }
  </style>
  <script src="js/jquery-3.6.0.min.js"></script>
  <body>
    <img src="image/nami (8).jpg" />
    <div>
      <button id="fadeIn">显示</button>
      <button id="fadeOut">隐藏</button>
      <button id="fadeToggle">切换</button>
    </div>
  </body>
  <script>
    $("#fadeIn").click(function () {
      $("img").fadeIn("slow");
    });
    $("#fadeOut").on("click", function () {
      $("img").fadeOut("fast");
    });
    $("#fadeToggle").on("click", function () {
      $("img").fadeToggle(5000);
    });
  </script>
```

#### 2.3.3 改变元素透明度的方法

- 改变元素的透明度到某个值, 并且在改变的过程中会有动画效果
- `JQuery.fadeTo(duration: string | number, opacity: number, complete?: Function): JQuery`
  - 动画的持续时间, 可以取值以下三种
    - "slow"
    - "fast"
    - 数字, 数字表示是多少毫秒
  - opacity    设置元素的透明度, 取值范围为0~1, 0为完全透明, 1为完全不透明
  - complete方法是指切换完元素状态后要执行的操作, **可选参数,** 默认什么也不做

```html
  <style>
    img {
      height: 300px;
    }
  </style>
  <script src="js/jquery-3.6.0.min.js"></script>
  <body>
    <img src="image/nami (8).jpg" />
    <div>
      <button id="fadeIn">显示</button>
      <button id="fadeOut">隐藏</button>
      <button id="fadeToggle">切换</button>
      <button id="fadeTo">fadeTo</button>
    </div>
  </body>
  <script>
    $("#fadeIn").click(function () {
      $("img").fadeIn("slow");
    });
    $("#fadeOut").on("click", function () {
      $("img").fadeOut("fast");
    });
    $("#fadeToggle").on("click", function () {
      $("img").fadeToggle(3000);
    });
    $("#fadeTo").on("click", function () {
      $("img").fadeTo(2000, 0.3);
    });
  </script>
```

#### 2.3.4 链式编程

jQuery元素对象的所有方法的返回值几乎都是该对象本身, 所以可以以链式编程的方式编写代码

需求: 随即切换图片, 并带有滑动效果

```html
  <style>
    img {
      height: 450px;
    }
    button {
      font-size: 2em;
      padding: 5px 20px;
      margin-bottom: 10px;
    }
  </style>
  <script src="js/jquery-3.6.0.min.js"></script>
  <body>
    <button id="toggle">换图</button><br />
    <img id="image" src="image/nami (1).jpg" />
  </body>
  <script>
    function randomInt(start, end) {
      var factor = end - start + 1;
      return Math.floor(Math.random() * factor + start);
    }

    $("#toggle").click(function () {
      $("#image")
        .slideUp(2000, function () {
          $(this).attr("src", `image/nami (${randomInt(1, 12)}).jpg`);
        })
        .slideDown(2000);
    });
  </script>
```

#### 2.3.5 DOM操作方面的方法

##### 2.3.5.1 获取元素宽高方法

| 方法                           | 说明                             |
| ------------------------------ | -------------------------------- |
| `JQuery.height(): number`      | contentHeight                    |
| `JQuery.innerHeight(): number` | padding + contentHeight          |
| `JQuery.outerHeight(): number` | border + padding + contentHeight |
| `JQuery.width(): number`       | contentWidthJQuery.              |
| `JQuery.innerWidth(): number`  | padding + contentWidth           |
| `JQuery.outerWidth(): number`  | border + padding + contentWidth  |

##### 2.3.5.2 获取和修改元素内容的方法

| 方法                                                   | 说明                                                         |
| ------------------------------------------------------ | ------------------------------------------------------------ |
| `JQuery.text(): string`                                | 获取元素内的所有文本内容,包括子元素的文本内容                |
| `JQuery.html(text: string | number | boolean): JQuery` | 将元素内的所有内容( 包括所有内文本内容和所有子元素) , 都替换成文本, 文本内容是text |
| `JQuery.html(): string`                                | 获取元素内的所有代码                                         |
| `JQuery.html(htmlString: string): JQuery`              | 将元素内的所有代码替换为代码text                             |

##### 2.3.5.3 获取和修改表单元素的value值的方法

- 注意: ==value属性只有表单元素有==

| 方法                                                    | 说明                  |
| ------------------------------------------------------- | --------------------- |
| `JQuery.val(): any`                                     | 获取表单元素的value值 |
| `JQuery.val(value: string | number | string[]): JQuery` | 修改表单元素的value值 |

##### 2.3.5.4 获取和修改元素的指定属性的值

- 表单元素中的属性`checked`  `disabled`可以赋值 `true`  `false`

| 方法                                         | 说明                                                         |
| -------------------------------------------- | ------------------------------------------------------------ |
| `JQuery.attr(attributeName: string): string` | 获取元素的`attributeName`属性的值<br />无法获取未曾进行显式初始化的  属性的  默认初始化值, 比如属性`checked`和`disable`, 都是由默认初始化值的, 但无法获取 |
| `JQuery.attr(attributes: Object): JQuery`    | [设置指定属性的值](#设置指定属性的值), 可以给任何属性赋值    |
| `JQuery.prop(attributeName: string): string` | 获取元素的`attributeName`属性的值, <br />可以获取未曾进行显式初始化的  属性的  默认初始化值, 比如属性`checked`和`disable`的默认初始化值就可以被获取到 |
| `JQuery.prop(attributes: Object): JQuery`    | 设置指定属性的值, 可以给任何属性赋值                         |

###### 设置指定属性的值

- 设置一个属性的值, 示例

  - ```js
    $("img").attr("title", "汉库克");
    ```

- 设置多个属性的值, 示例

  - ```js
    $("img").attr({ title: "艾米莉", alt: "图片加载失败了", height: "400px" });
    ```

#### 2.3.6 CSS样式方面的方法

##### 2.3.6.1 CSS样式函数

| 方法                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| `JQuery.css(propertyName: string): string`                   | 获得该元素的CSS中的属性propertyName的值                      |
| `JQuery.css(propertyName: string, value: string | number): JQuery` | 将该元素的CSS中的属性propertyName的值设置为value             |
| `JQuery.css(properties: Object): JQuery`                     | 设置该元素的CSS中的属性<br />这里面的属性名由多个单词组成时,以驼峰命名法则写出 |

需求: 打开一张图片, 要求可以放大, 可以缩小, 还可以回复原始尺寸

```html
  <style>
    .zoomIn {
      transition-property: width height;
      transition-duration: 1s;
      transition-timing-function: ease-in;
    }
  </style>
  <script src="js/jquery-3.6.0.min.js"></script>
  <body>
    <button id="btnZoomOut">放大图片</button>
    <button id="btnZoomIn">缩小图片</button>
    <button id="btnOriginSize">原始大小</button><br />
    <img src="image/nami (7).jpg" height="400px" />
  </body>
  <script>
    //给图片设置一个过渡动画的样式
    $("img").css({
      transitionProperty: "width height",
      transitionDuration: "500ms",
      transitionTimingFunction: "ease-in",
    });

    //恢复图片的原始尺寸
    $("#btnOriginSize").click(function () {
      $("img").css("height", "auto");
    });
    //缩小
    $("#btnZoomIn").click(function () {
      var height = $("img").height();
      if (height > 50) {
        height = height - 50;
      }
      $("img").css("height", height + "px");
    });
    //放大
    $("#btnZoomOut").click(function () {
      var height = $("img").height();
      $("img").css("height", height + 50 + "px");
    });
  </script>
```

##### 2.3.6.2 类样式函数

- 可以将本元素在style元素中的类选择器. 

| 方法                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| `JQuery.addClass(className: string): JQuery`                 | 将本元素跟`<style>`元素内的名为className的类选择器j进行**绑定** |
| `JQuery.removeClass(className?: string): JQuery`             | 将本元素跟`<style>`元素内的名为className的类选择器**解除绑定** |
| `JQuery.toggleClass(className: string, switch?: boolean): JQuery` | 切换本元素跟`<style>`元素内的名为className的类选择器的绑定状态<br />switch是个可选参数, 不写时, 可以在绑定与不绑定两个状态见任意切换, 值为true时, 则只能切换为绑定状态, 值为false时, 则智能切换为不绑定状态 |

- className可以表示一个类选择器, 也可以表示多个类选择器
  - 例如: `"book" `表示一个名为book的类选择器
  - 例如: `"book plant food"`表示三个类选择器, 他们分别名为book、plant、food

```html
  <style>
    .a {
      height: 400px;
    }
    .b {
      border-style: solid;
      border-width: 20px;
      border-color: blueviolet;
    }
    .c {
      padding: 20px;
      background-color: burlywood;
    }
    .d {
      transition-property: width height;
      transition-duration: 500ms;
      transition-timing-function: ease-in;
    }
  </style>
  <script src="js/jquery-3.6.0.min.js"></script>
  <body>
    <button id="btn1">添加过渡动画类样式</button><br />
    <button id="btn2">添加多个类样式</button><br />
    <button id="btn3">移除类样式</button><br />
    <button id="btn4">切换类样式</button><br />
    <img src="image/nami (3).jpg" />
  </body>
  <script>
    $(`#btn1`).click(function () {
      $(`img`).addClass("d");
    });
    $(`#btn2`).click(function () {
      $(`img`).addClass("a b c");
    });
    $(`#btn3`).click(function () {
      $(`img`).removeClass("a b c");
    });
    $(`#btn4`).click(function () {
      $(`img`).toggleClass("a b c");
    });
  </script>
```

#### 2.3.7 节点操作方面的方法

##### 2.3.7.1 创建节点

- 通过jQuery的工厂函数`$( str )`来创建一个节点, 使用方法
  - `$("<img src='image/nami (3).jpg'/>")`
  - `$("<div></div>")`
  - `$("<table><tr><td>1</td><td>2</td></tr><tr><td>3</td><td>4</td></tr></table>")`

```html
  <script src="js/jquery-3.6.0.min.js"></script>
  <body></body>
  <script>
    $("body")
      .append($(`<div><img src="image/nami (3).jpg"/></div>`))
      .append(
        $(
          "<table><tr><td>1</td><td>2</td></tr><tr><td>3</td><td>4</td></tr></table>"
        )
      )
      .append(
        $("<div style='width:300px;height:300px;background-color:red;'></div>")
      );
  </script>
```

##### 2.3.7.2 添加节点

###### 2.3.7.2.1 添加子节点

| 方法                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| JQuery.**append**(content1: string \| any[] \| Element \| JQuery \| DocumentFragment \| Text, ...content2: any[]): JQuery | 为本元素添加子节点(添加到最后面)<br />可以添加一个子节点, 也可以添加多个子节点<br />**添加多个节点时, 节点作为参数时的顺序就是结点的顺序**<br />==虽然content的类型没有限制, 但最好还是使用jQuery对象, 这样出错率较小,也容易排除bug== |
| JQuery.**prepend**(content1: string \| any[] \| Element \| JQuery \| DocumentFragment \| Text, ...content2: any[]): JQuery | 为本元素添加子节点(添加到最前面)<br />可以添加一个子节点, 也可以添加多个子节点<br />**添加多个节点时, 节点作为参数时的顺序就是结点的顺序**<br />==虽然content的类型没有限制, 但最好还是使用jQuery对象, 这样出错率较小,也容易排除bug== |
| JQuery.**appendTo**(target: string \| any[] \| Element \| JQuery): JQuery | 将本元素作为子节点添加到target元素内的最后面<br />==虽然target的类型没有限制, 但最好还是使用jQuery对象, 这样出错率较小,也容易排除bug== |
| JQuery.**prependTo**(target: string \| any[] \| Element \| JQuery): JQuery | 将本元素作为子节点添加到target元素内的最前面<br />==虽然target的类型没有限制, 但最好还是使用jQuery对象, 这样出错率较小,也容易排除bug== |

```html
  <script src="js/jquery-3.6.0.min.js"></script>
  <body>
    <div style="background-color: rgba(255, 0, 0, 0.287)">
      <span>汉库克</span><br />
      <img height="400px" src="image/nami (7).jpg" />
      <br />
    </div>
  </body>
  <script>
    $("div").append(
      $(`<span>穗乃果</span>`),
      $(`<br />`),
      $(`<img height="400px" src="image/nami (8).jpg" />`),
      $(`<br/>`),
      $(`<span>戴安娜</span>`),
      $(`<br />`),
      $(`<img height="400px" src="image/nami (9).jpg" />`)
    );
    $("div").prepend(
      $(`<span>穗乃果</span>`),
      $(`<br />`),
      $(`<img height="400px" src="image/nami (8).jpg" />`),
      $(`<br/>`),
      $(`<span>戴安娜</span>`),
      $(`<br />`),
      $(`<img height="400px" src="image/nami (9).jpg" />`),
      $(`<br/>`)
    );
  </script>
```



###### 2.3.7.2.2 添加同辈节点

| 方法                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| JQuery.**after**(content1: string \| any[] \| Element \| JQuery \| DocumentFragment \| Text, ...content2: any[]): JQuery | 在本元素之后添加节点<br />可以添加一个子节点, 也可以添加多个子节点<br />**添加多个节点时, 节点作为参数时的顺序就是结点的顺序**<br />==虽然content的类型没有限制, 但最好还是使用jQuery对象, 这样出错率较小,也容易排除bug== |
| JQuery.**before**(content1: string \| any[] \| Element \| JQuery \| DocumentFragment \| Text, ...content2: any[]): JQuery | 在本元素之前添加节点<br />可以添加一个子节点, 也可以添加多个子节点<br />**添加多个节点时, 节点作为参数时的顺序就是结点的顺序**<br />==虽然content的类型没有限制, 但最好还是使用jQuery对象, 这样出错率较小,也容易排除bug== |
| JQuery.**insertAfter**(target: string \| any[] \| JQuery \| Element \| Text): JQuery | 将本元素作为添加到target元素的后面<br />==虽然target的类型没有限制, 但最好还是使用jQuery对象, 这样出错率较小,也容易排除bug== |
| JQuery.**insertBefore**(target: string \| any[] \| JQuery \| Element \| Text): JQuery | 将本元素作为添加到target元素的前面<br />==虽然target的类型没有限制, 但最好还是使用jQuery对象, 这样出错率较小,也容易排除bug== |

##### 2.3.7.3 替换节点

| 方法                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| JQuery.**replaceWith**(newContent: string \| any[] \| JQuery \| Element \| Text): JQuery | 使用newContent元素将本元素替换掉<br />==虽然newContent的类型没有限制, 但最好还是使用jQuery对象, 这样出错率较小,也容易排除bug== |
| JQuery.**replaceAll**(target: string \| any[] \| JQuery \| Element): JQuery | 使用本元素替换到target元素<br />==虽然target的类型没有限制, 但最好还是使用jQuery对象, 这样出错率较小,也容易排除bug== |

##### 2.3.7.4 节点保存数据的方法

- 用于元素存储数据, 或者获取一系列元素中第一个元素存储的数据[^注1]
- `JQuery.data(key: string, value: any): JQuery`    存储或修改数据
  - `JQuery.data(key, value):JQuery`
  - `JQuery.data(obj):JQuery`      obj: An object of key-value pairs of data to update
- `JQuery.data(key: string): any`    返回该系列匹配元素的第一个元素保存的数据
  - `JQuery.data(key):any`
  - `JQuery.data():Object`    返回值是该元素存储的所有数据的json对象
- `JQuery.removeData([name] : string): JQuery`   移除该元素保存的数据
  - `JQuery.removeData():JQuery`    移除所有的数据
  - `JQuery.removeData([name]):JQuery`    移除指定的数据
  - `JQuery.removeData([list]):JQuery`    移除指定的一些类数据, list : Type: Array or String.     An array or space-separated string naming the pieces of data to delete.

##### 2.3.7.5 复制节点

`JQuey.clone()`     克隆出一份相同的元素(包括所有的属性和后代元素)

- `JQuery.clone([withDataAndEvents?: boolean]): JQuery`    克隆出一份相同的元素(包括所有的属性和后代元素), withDataAndEvents是个可选参数, 默认值是false, 表示是否克隆该元素保存的数据(通过`JQuery.data()`方法保存的数据)和该元素的事件处理器, true为克隆, false为不克隆
- `JQuery.clone(withDataAndEvents?: boolean[, deepWithDataAndEvents?: boolean]): JQuery`    克隆出一份相同的元素(包括所有的属性和后代元素), deepWithDataAndEvents是个可选参数, 默认值是false, 表示是否克隆该元素的后代元素保存的数据(通过`JQuery.data()`方法保存的数据)和该元素后代元素的事件处理器, true为克隆, false为不克隆;
  - 注意: 只有withDataAndEvents的值为true时, 设置deepWithDataAndEvents的值才有意义, 如果withDataAndEvents的值为false, 那么不论deepWithDataAndEvents值是什么, 都**不会克隆**该元素的后代元素保存的数据(通过`JQuery.data()`方法保存的数据)和该元素后代元素的事件处理器

```html
  <style>
    .hasBorder {
      border-style: solid;
      border-width: 30px;
      border-color: blueviolet;
    }
  </style>
  <body>
    <button>[false,false]</button><br />
    <button>[true,false]</button><br />
    <button>[false,true]</button><br />
    <button>[true,true]</button><br />
    <button>复制元素</button><br />
    <div style="background-color: cornsilk; margin-bottom: 30px">
      <span>
        吃了吗?
        <span>早就吃了</span>
      </span>
      <br />
      <img height="400px" src="image/nami (3).jpg" />
    </div>
  </body>
  <script>
    $("div").click(function () {
      $(this).toggleClass("hasBorder");
    });
    $("img").click(function () {
      $(this).toggleClass("hasBorder");
      event.stopPropagation();
    });
    $("button").click(function () {
      var arr = JSON.parse($(this).text());
      var withDataAndEvent = arr[0];
      console.log(withDataAndEvent);
      var deepWithDataAndEvent = arr[1];
      console.log(deepWithDataAndEvent);

      $("body").append(
        $("div").first().clone(withDataAndEvent, deepWithDataAndEvent)
      );
      console.log(document);
    });
  </script>
```

##### 2.3.7.6 删除节点

###### 2.3.7.6.1 删除整个节点

- `JQuery.remove([selector?: string]):JQuery`
  - selector是个可选参数
    - 当不写这个参数时, 就会将工厂函数匹配的所有元素从document中移除
    - 当写这个参数时, 就会从工厂函数匹配的所有元素中筛选出selector匹配的所有元素, 然后将这些筛选出的元素从document中移除

```html
  <style>
    div {
      font-size: 3em;
      font-weight: bold;
      color: rgb(122, 18, 148);
    }
    #demo1 {
      background-color: rgba(214, 130, 130, 0.582);
    }
    #demo2 {
      background-color: rgba(21, 58, 223, 0.39);
    }
    #demo3 {
      background-color: rgba(103, 187, 18, 0.39);
    }
    img {
      height: 200px;
    }
  </style>
  <script src="js/jquery-3.6.0.min.js"></script>
  <body>
    <button>remove()</button>
    <button>remove("#demo2")</button>
    <div id="demo1">
      demo1<br />
      <img src="image/burin (1).gif" />
    </div>
    <div id="demo2">
      demo2<br />
      <img src="image/burin (2).gif" />
    </div>
    <div id="demo3">
      demo3<br />
      <img src="image/burin (3).gif" />
    </div>
  </body>
  <script>
    $(`button`).click(function () {
      var str = $(this).text().trim();
      if (str == "remove()") {
        $(`div`).remove();
      } else {
        console.log(str);
        $(`div`).remove(`#demo2`);
      }
    });
  </script>
```

###### 2.3.7.6.2 清空节点内容

- `JQuery.empty():JQuery`    会从document中移除此元素下的所有文本内容和子元素

```html
 <style>
    div {
      font-size: 3em;
      font-weight: bold;
      color: rgb(122, 18, 148);
    }
    #demo1 {
      background-color: rgba(214, 130, 130, 0.582);
      padding-bottom: 50px;
    }
    #demo2 {
      background-color: rgba(21, 58, 223, 0.39);
    }
    img {
      height: 200px;
    }
  </style>
  <script src="js/jquery-3.6.0.min.js"></script>
  <body>
    <button>empty()</button>
    <div id="demo1" name="">
      demo1<br />
      <img src="image/burin (1).gif" />
      <div id="demo2">
        demo2<br />
        <img src="image/burin (2).gif" />
      </div>
    </div>
  </body>
  <script>
    $("#demo1").click(function () {
      alert("早上好!!!!!!");
    });
    $(`button`).click(function () {
      $("#demo1").empty().css({ width: "200px", height: "200px" });
    });
  </script>
```

##### 2.3.7.7 遍历节点

###### 2.3.7.7.1 长辈节点

**本元素的长辈节点**

| 方法                                          | 说明                                                         |
| --------------------------------------------- | ------------------------------------------------------------ |
| `JQuery.parent([selector?: string]): JQuery`  | **父元素中遍历**==Get the parent== of each element in the current set of matched elements, optionally filtered by a selector.<br />selector:    A string containing a selector expression to match elements against. |
| `JQuery.parents([selector?: string]): JQuery` | **长辈元素中遍历**==Get the ancestors(长辈)== of each element in the current set of matched elements, optionally filtered by a selector.<br />selector:    A string containing a selector expression to match elements against. |

###### 2.3.7.7.2 同辈节点

**本元素的同辈节点**

| 方法                                           | 说明                                                         |
| ---------------------------------------------- | ------------------------------------------------------------ |
| `JQuery.next([selector?: string]): JQuery`     | **相邻的下一个**Get the ==immediately following== sibling of each element in the set of matched elements. If a selector is provided, it retrieves the next sibling only if it matches that selector.<br />selector:    A string containing a selector expression to match elements against. |
| `JQuery.prev([selector?: string]): JQuery`     | **相邻的上一个**Get the ==immediately preceding== sibling of each element in the set of matched elements. If a selector is provided, it retrieves the previous sibling only if it matches that selector.<br />selector:    A string containing a selector expression to match elements against. |
| `JQuery.siblings([selector?: string]): JQuery` | **同辈的节点(前后都选)**Get the siblings of each element in the set of matched elements, optionally filtered by a selector.<br />selector:    A string containing a selector expression to match elements against. |

###### 2.3.7.7.3 晚辈节点

**本元素的晚辈节点**

| 方法                                                   | 说明                                                         |
| ------------------------------------------------------ | ------------------------------------------------------------ |
| `JQuery.children([selector?: string]): JQuery`         | **子节点**Get the children of each element in the set of matched elements, optionally filtered by a selector.<br />selector:    A string containing a selector expression to match elements against. |
| `JQuery.find(selector: string|JQuery|element): JQuery` | **后辈节点**Get the descendants  of each element in the current set of matched elements, filtered by a selector, jQuery object, or element.<br />selector:    必需写的参数 |



##### 2.3.7.8 过滤节点

| 方法                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| `JQuery.first(): JQuery`                                     | **第一个**Reduce the set of matched elements to the first in the set. |
| `JQuery.last(): JQuery`                                      | **最后一个**Reduce the set of matched elements to the final one in the set. |
| `JQuery.even(): JQuery`                                      | 偶数索引                                                     |
| `JQuery.odd(): JQuery`                                       | 奇数索引                                                     |
| `JQuery.eq(index: number): JQuery`                           | Reduce the set of matched elements to the one at the specified index. |
| `JQuery.not(selector: string|JQuery|element|Araray): JQuery` | **挑选出所有不匹配的**Remove elements from the set of matched elements. |
| JQuery.filter(selector: string): JQuery                      | **挑选出所有匹配的**Reduce the set of matched elements to those that match the selector . |
| `JQuery.is(selector: string|JQuery|element): boolean`        | **判断是否有匹配的**check the current matched set of elements against a selector, element, or jQuery object and **return `true` if ==at least one== of these elements matches the given arguments.** |

## 三. 案例

### 3.1 手风琴特效

```html
  <script src="js/jquery-3.6.0.min.js"></script>
  <body>
    <ul>
      <li>
        <dl>
          <dt>最终幻想7</dt>
          <dd>蒂法</dd>
          <dd>艾丽丝</dd>
          <dd>杰西</dd>
        </dl>
      </li>
      <li>
        <dl>
          <dt>守望先锋</dt>
          <dd>Mercy</dd>
          <dd>WidowMaker</dd>
          <dd>D·Va</dd>
        </dl>
      </li>
      <li>
        <dl>
          <dt>死或生</dt>
          <dd>穗乃果</dd>
          <dd>艾莲娜</dd>
          <dd>红叶</dd>
          <dd>不知火舞</dd>
        </dl>
      </li>
    </ul>
  </body>
  <script>
    $("dt").siblings().slideUp();
    $(function () {
      $("dt").click(function () {
        $(this).siblings().slideToggle(500);
        $("dd").not($(this).siblings()).slideUp(500);
      });
    });
  </script>
```

### 3.2 购物车结算

![](%E7%AC%AC04%E7%AB%A0%20jQuery.assets/One_2021-04-21_121826.png)

```html
 <style>
    span {
      display: inline-block;
      width: 3em;
      height: 1.4em;
    }
    a {
      display: inline-block;
      border-style: solid;
      border-color: black;
      background-color: rgba(136, 135, 129, 0.226);
      width: 1.4em;
      height: 1.4em;
      text-decoration: none;
      text-align: center;
      color: black;
    }
    a:active {
      background-color: rgba(136, 135, 129, 0.651);
    }
    table {
      border-style: solid;
      border-width: 5px;
      border-collapse: collapse;
      border-spacing: 0px;
    }
    td {
      border-style: solid;
      border-width: 3px;
      font-size: 1.5em;
      width: 10em;
      height: 2em;
      vertical-align: middle;
      text-align: center;
    }
    .title {
      background-color: darkgray;
      font-weight: bolder;
    }
  </style>
  <script src="js/jquery-3.6.0.min.js"></script>
  <!--导入一个可以进行精确运算的程序库-->
  <script src="js/math.min.js"></script>
  <body>
    <table>
      <tr class="title">
        <td>商品编号</td>
        <td>名称</td>
        <td>单价(元)</td>
        <td>数量</td>
        <td>总价(元)</td>
      </tr>
      <tr>
        <td>1001</td>
        <td>炸香肠</td>
        <td>3</td>
        <td>
          <a href="javascript:void(0);">-</a><span>&nbsp;1&nbsp;</span
          ><a href="javascript:void(0);">+</a>
        </td>
        <td>3</td>
      </tr>
      <tr>
        <td>1002</td>
        <td>王八</td>
        <td>8.8</td>
        <td>
          <a href="javascript:void(0);">-</a><span>&nbsp;1&nbsp;</span
          ><a href="javascript:void(0);">+</a>
        </td>
        <td>8.8</td>
      </tr>
      <tr>
        <td>1003</td>
        <td>恐龙</td>
        <td>1000</td>
        <td>
          <a href="javascript:void(0);">-</a><span>&nbsp;1&nbsp;</span
          ><a href="javascript:void(0);">+</a>
        </td>
        <td>1000</td>
      </tr>
      <tr>
        <td colspan="5" style="text-align: right">
          <span>总价:&nbsp;</span>
          <span id="allTotalPrice" style="color: red; font-weight: bolder"
            >1011.8</span
          >
          <span>元</span>
          <button style="font-size: 0.9em; margin-right: 5px">提交订单</button>
        </td>
      </tr>
    </table>
  </body>
  <script>
    //对math对象进行设置, 以便可以进行精确计算
    math.config({
      number: "BigNumber",
    });

    $("a").click(function () {
      //1. 获取被点击的按钮
      var thisEle = $(this);
      //2. 根据被点击的按钮, 获取本行商品的 单价元素, 数量元素, 总价元素, 合计元素
      var priceEle = thisEle.parent().prev();
      var countEle = thisEle.siblings(`span`);
      var totalPriceEle = thisEle.parent().next();
      var allTotalPriceEle = $("#allTotalPrice");
      //3. 获取本行商品的 单价 数量 总价 合计
      var price = priceEle.text().trim();
      var count = countEle.text().trim();
      var totalPrice = totalPriceEle.text().trim();
      var allTotalPrice = allTotalPriceEle.text().trim();
      //4. 计算商品的数量
      var symbol = $(this).text().trim();
      count = math.evaluate(`${count}${symbol}1`);
      //5. 如果数量小于1, 就弹出确认框
      if (count < 1) {
        var removeMe = confirm("是否删除该商品?");
        //5.1 如果用户点击了确定就上出本行商品, 否则什么也不做
        if (removeMe) {
          //删除本行商品
          thisEle.parent().parent().remove();
          //修改总价的合计
          allTotalPrice = math.evaluate(`${allTotalPrice}${symbol}${price}`);
          allTotalPriceEle.text(allTotalPrice);
        }

        //弱国count<1, 那就不执行之后的语句了
        return;
      }
      //修改本行商品的数量, 总价, 以及总价的合计
      totalPrice = math.evaluate(`${totalPrice}${symbol}${price}`);
      allTotalPrice = math.evaluate(`${allTotalPrice}${symbol}${price}`);
      countEle.text(`${count}`);
      totalPriceEle.text(totalPrice);
      allTotalPriceEle.text(allTotalPrice);
    });
  </script>
```



## FootNotes

[^注1]: Store arbitrary data associated with the matched elements or return the value at the named data store for the first element in the set of matched elements.

