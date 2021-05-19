# 第02章 CSS

[toc]

## 一. CSS介绍

- CSS是指层叠样式表, Cascading style sheet
- 通过CSS可以让我们定义HTML元素如何显示
- CSS可以让我们把原本HTML不能描述的效果, 通过CSS描述出来
- 通过CSS描述我们的html页面, 可以让我们的页面更加漂亮, 可以提高工作效率
- **CSS的出现可以让, HTML专注于提供内容, 而CSS对这些内容进行打扮**

## 二. CSS与HTML的结合方式

### 2.1 html内联CSS, 又叫html行内CSS

- 就是在我们的HTML标签上通过属性`style`来引用CSS代码

  - 优点: 简单方便
  - 缺点: 一套装扮模式只能用来打扮一个html标签, 有些大材小用

- ```html
  <p style='color:rebeccapurple; font-size:5em'>我的目标是那星辰大海</p>
  ```

### 2.2 html内部CSS

- 我们通过在html文件内编辑`<style>`标签来声明我们的CSS, 标签`<style>`必须写在标签`</head>`和标签`<body>`之间
- 优点; 一套装扮模式可以用来打扮多个标签
- 缺点: 这套装扮模式只能在本html文件中使用\

#### 2.2.1 语法

```html
<style>
    选择器名{
        属性x : 值a;
        属性y : 值b;
        ...
        属性z : 值b
    }
    <!-- 可以有多个不同的选择器 -->
</style>
```

### 2.3 html外部CSS

- 我们需要单独创建一个CSS文件, 这个CSS文件的的后缀名就是`.css`

- css文件的格式

  - ```css
    选择器1{
        ...
    }
    选择器2{
        ...
    }
    ...
    选择器n{
        ...
    }
    ```

- 有两种引入外部css文件的方式:

  - 标签`<link/>`引入    在html文件的`<head>`标签的下通过子标签`<link/>`来引用这个CSS文件
  
    - ```html
      <link href="CSS示例/文本样式.css" rel="stylesheet" />
      ```
  
    - 必须有属性`href`和属性`rel`, `rel`的值只能写`stylesheet`
  
  - @import引入    在标签`</head`和标签`<body>`之间编写标签`<style>`, 在标签`<style>`下通过语句`@import url(css文件路径)`来引入这个CSS文件
  
    - ```html
      <style>
          @import url(CSS示例/文本样式.css);
      </style>
      ```
  
    - 注意`@import url(css文件路径)`必须是`<style>`标签下的第一行

#### 2.3.1 两种外部引入的区别

- 加载顺序的不同
  - 标签`<link/>`引入css的方式    会先加载css文件, 然后再加载html文件, 也就是说用户要么会看到一个美美的装扮好的网页, 要么看到404
  - @import引入方式, 会先加载html文件, 然后才下载然后加载CSS文件, 如果网络条件不好, 用户就会看到一个没有装扮的页面, 印象用户对网站的映像
- 标签`<link/>`方式引入CSS文件后, 可以通过javascript对css文件里的代码进行动态修改, 而@import方式引入CSS文件的方式不支持这么做

### 2.4 html引入css时的优先级

行内引入css > 内部引入css > 外部引入cs

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link href="CSS示例/link文本样式.css" rel="stylesheet" />
  </head>
  <style>
    @import url(CSS示例/import文本样式.css);

    .cssExample {
      color: greenyellow;
      font-size: 2em;
      font-weight: bold;
    }
  </style>
  <body>
    <!-- 行内引入css -->
    <p style="color: blueviolet; font-size: 3em; font-weight: bold">
      我是要成为海贼王的男人
    </p>
    <!-- 内部引入css -->
    <p class="cssExample">我是要成为海贼王的男人</p>
    <!-- 外部引入css, 以link方式 -->
    <p class="linkExample">link</p>
    <!-- 外部引入css, 以@import方式 -->
    <p class="importExample">import</p>
  </body>
</html>
```



## 三. CSS的使用

### 3.0 CSS中的注释

```css
/* 这是css中的注释方式 */
```



### 3.1 CSS中的选择器

#### 3.1.1 标签选择器

`labelName`    又名元素选择器, 这种选择器可以打扮标签名为`labelName`的标签

```html
<style>
    div{
        ...
    }
</style>
```



#### 3.1.2 类选择器

`.classValue`    这种选择器可以打扮属性`class`的值为classvalue的标签(任何标签都可以设置属性`class`)

```html
<style>
    .classValue{
        ...
    }
</style>
```

还可以指定类选择器的标签类型

```html
<style>
    div.classValue{
        ...
    }
</style>
```



#### 3.1.3 id选择器

`#idValue`    种种选择器可以打扮属性`id`值为`idValue`的标签(**任何标签都可以设置属性`id`**)

```html
<style>
    #idValue{
        ...
    }
</style>
```

#### 3.1.4 选择器组

- 使用同一种装扮的不同名称的选择器可以合起来声明, 不过名字之间要用`,`隔开

- ```css
  h1, div, .classVaule, #idValue{
      ...
  }
  ```
  
- 选择器组经常这样使用

  - ```css
    h1, div, .classValue, #idValue{
        ...
    }
    ...
    .classValue{
        ...
    }
    ...
    h1{
        ...
    }
    ...
    div{
        ...
    }
    ...
    #idValue{
        ...
    }
    ```

  - 最后的显示效果就是选择器与选择器组加起来的效果

#### 3.1.5 派生选择器

- 只有标签才能使用派生选择器

- 子代派生选择器    举例 `div>p`, 即只有`<div`>标签的子标签中的`<p>`标签才能使用这个选择器

  - ```css
    div>p{
        ...
    }
    ```

- 后代派生选择器    举例`div p`, 即只要标签`<div>`下的所有标签`<p>`都可以使用这个选择器

  - ```css
    div p{
        ...
    }
    ```

#### 3.1.6 CSS伪类

- CSS伪类可以对css选择器添加一些特殊效果, 即使用这个css选择器的标签在进行了某个操作后, 这个css选择器的属性会做出对应的改变, 从而导致这个标签的显示效果发生改变

- 伪类属性列表, 使用顺序必须遵循爱恨顺序, 即LoVeHAte顺序

  - `:link`           鼠标未曾点击过
  - `:visited`    鼠标点击过后
  - `:hover`         鼠标悬浮其上
  - `:active`      鼠标正在点击(即点下去还未松手)
  - `:first-child`    跟鼠标没有关系, 用来装扮  调用这个选择器  的  标签  的第一个子标签

- ```html
      a{
          color:bule
      }
      a:link{
          color:green
      }
      a:visited{
          color:red
      }
      a:hover{
          font-size:3em
      }
      a:active{
          color:rgba(89, 0, 255, 0.849)
      }
  ```

- ```html
  <a href="https://www.baidu.com" target="_blank"/>百度一下, 你就知道
  ```

#### 3.1.7 选择器显示的优先级

- 同一个标签如果可以调用多种选择器, 那么这些选择器的效果都会显示, 只不过高优先级的选择器的效果会覆盖低优先级的选择器(注意是覆盖而不是抹除, 如果高优先级的选择器是透明或者未能完全铺满标签区域的话, 就能看到低优先级的选择器的显示效果)
- id选择器 > 类选择器 > 标签选择器

### 3.2 CSS基本属性

#### 3.2.1 文本方面的属性

| 需求                                | 属性                  | 可取的值                                                     |
| ----------------------------------- | --------------------- | ------------------------------------------------------------ |
| 字体类型                            | font-family           | 字体类型的名称                                               |
| 字体大小                            | font-size             | 值一般只使用两种: <br />带有px单位的数字, 例如: `50px`表示一个字符的宽高各为50px<br />带em单位的数字, 例如: `5em`表示字符大小为基本字符大小的5倍 |
| 字体颜色                            | color                 | 值有三种模式:<br />描述颜色的文本<br />#019afc<br />rgb(255,1,0) |
| 字体加粗                            | font-weight           | 值只有两个:<br />none    不加粗, 默认值<br />bold    加粗    |
| 字体倾斜                            | font-style            | 值只有两个<br />none  不倾斜, 默认值<br />italic  倾斜       |
| 线的种类 (给文本划线来强调突出文本) | text-decoration-line  | none  没有线, 默认值<br />underline  下划线<br />overline  上划线<br />line-through  穿过线, 即删除线<br />underline overline  线和线还可以组合 |
| 线的颜色                            | text-decoration-color | 线的颜色, 默认黑色                                           |
| 线的样式 (给文本划线来强调突出文本) | text-decoration-style | solid  实线, 默认值<br />dashed  虚线<br />dotted  点线<br />double  双线<br />wavy  波浪线 |
| 文本在行内的水平对齐方式            | text-align            | 值只有三个:<br />left    行内左对齐, 默认值<br />right    行内右对齐<br />center    行内居中 |
| 首行文本缩进量                      | text-indent           | 值一般只用一种模式:<br />带有em单位的数字, 例如: `2em`表示首行缩进两个字符 |
| 设置每行的高度                      | line-height           | 值一般只使用两种: <br />带有px单位的数字, 例如: `50px`表示每行的高度都为50px<br />带em单位的数字, 例如: `5em`表示每行的高度为基本字符大小的5倍 |

#### 3.2.2 背景方面的属性

| 属性                  | 说明                                           | 值                                                           |
| --------------------- | ---------------------------------------------- | ------------------------------------------------------------ |
| background-color      | 设置标签的背景颜色                             | 值有三种模式:<br />描述颜色的文本<br />#019afc<br />rgb(255,1,0) |
| background-image      | 设置标签的背景图片, 背景图片会覆盖背景颜色     | 值的样式    `url('图片的路径')`                              |
| background-position   | 背景图片的位置                                 | 一般只有两种模式:<br />`center`    背景图片的中心与标签的中心重合<br />Xpx Ypx    背景图片的原点相对于原来的原点的位移 |
| background-repeat     | 背景图片是否作为墙纸重复填充标签以及重复的方向 | 有4个值:<br />no-repeat    不重复<br />repeat    重复填充标签的全部区域, 默认值<br />repeat-x    只重复填充一行<br />repeat-y    只重复填充一列 |
| background-attachment | 背景图片的附着方式                             | 有两个值:<br />scroll    背景图片会附着在标签上, 随着标签区域的滚动而滚动, 默认值<br />fixed    背景图片会附着在屏幕上, 就算标签区域滚动走了背景图片的位置也不会改变, 这种情况下背景图片常用来做为水印来使用 |

#### 3.2.3 列表方面的属性

CSS列表方面的属性作用如下:

- 为无序列表设置各种编号样式, 比如实心圆点, 空心圆, 黑方块, 甚至是图片
- 为有序列表设置各种编号样式, 比如数字, 字母, 罗马数字等等
- 列表中的每一行其实可以分成编号和内容两部分， 这两部分是分开的， 编号有编号的位置，内容有内容的位置，就算编号的位置上什么也没有，编号的位置仍就在那里， 内容部分也不能侵入过来

| 属性                | 说明                     | 值                                                           |
| ------------------- | ------------------------ | ------------------------------------------------------------ |
| list-style-type     | 设置列表的编号样式       | 有多个可取的值:<br />none    无编号, 只是编号没了, 位置还在那里<br />disc    黑色实心圆, 无序列表的默认值<br />circle   空心圆<br />square    黑色实心方块<br />decimal    数字, 有序列表的默认值<br />decimal-leading-zero    自动补0的数字, 就是01  02  03这样的样式<br />lower-roman    小写罗马数字<br />up-roman    大写罗马数字<br />lower-alpha    小写字母<br />up-alpha    大写字母 |
| list-style-image    | 设置列表编号的样式为图片 | 值是 url('图片路径')的形式                                   |
| list-style-position | 设置列表编号的位置       | 值有两个:<br />outside    靠外边, 缩进2个字符, 默认值<br />inside    靠里边, 缩进4个字符 |

```html
<style>
    li.navigation{
        float: left;
        width: 100px;
        list-style-type: none;
        background-color: black;
        font-size: 1.5em;
        color:cornsilk;
        text-align: center;
        line-height: 40px;
        cursor:pointer
    }

    li.navigation:hover{
        background-color: rgba(168, 115, 15, 0.904);
        font-weight: bold;
        font-size: 2em;
    }
</style>
<body>
        <ul>
            <li class='navigation'>艾瑞丝</li>
            <li class='navigation'>蒂法</li>
            <li class='navigation'>小美</li>
            <li class='navigation'>黑百合</li>
            <li class='navigation'>DVA</li>
        </ul>
    
</body>
```

#### 3.2.4 盒子模型(Box Model)

- 所有html标签都可以看做是个盒子, 在CSS中, "box model"这一术语在进行设计和布局时使用\
- CSS盒子模型本质上是一个盒子, 用于描述一个html标签， 它包括：
  - 边距(margin)    与父标签填充物也就是padding的距离, 与相邻的兄弟标签边框的距离
  - 边框(border)    本标签的边框
  - 填充(padding)    沿着边框上右下左四条线的方向向内填充
  - 实际内容(content)
- 盒子模型的示意图
  - ![](%E7%AC%AC02%E7%AB%A0%20CSS.assets/CSS%E8%AF%A6%E8%A7%A3.png)
- **边框的左上角是元素象限的原点**

#### 3.2.5 边框方面的属性

- **边框的左上角是标签象限的原点**
- 表格的边框设置时, `<table>`的css只负责表格四周的边框, `<td>`的css负责单元格的边框

| 需求     | 属性         | 值                                                           |
| -------- | ------------ | ------------------------------------------------------------ |
| 边框样式 | border-style | 9个值<br />none  没有边框, 默认值<br />dashed  虚线<br />dotted  点线<br />double  双线<br />solid  实线<br />groove  3D沟槽边框<br />ridge  3D脊边框<br />inset  3D嵌入边框<br />outset  3D突出边框 |
| 边框粗细 | border-width |                                                              |
| 边框颜色 | border-color |                                                              |

```html
<style>
    div {
      background-color: rgba(143, 188, 143, 0.89);
      height: 500px;
      margin-top: 15px;
      border-width: 30px;
      border-color: rgba(178, 34, 34, 0.767);
    }
    #div1 {
      border-style: dashed;
    }
    #div2 {
      border-style: dotted;
    }
    #div3 {
      border-style: double;
    }
    #div4 {
      border-style: solid;
    }
    #div5 {
      border-style: groove;
    }
    #div6 {
      border-style: ridge;
    }
    #div7 {
      border-style: inset;
    }
    #div8 {
      border-style: outset;
    }
    #div9 {
      border-style: none;
    }
  </style>
  <body>
    <div id="div1"></div>
    <div id="div2"></div>
    <div id="div3"></div>
    <div id="div4"></div>
    <div id="div5"></div>
    <div id="div6"></div>
    <div id="div7"></div>
    <div id="div8"></div>
    <div id="div9"></div>
  </body>
```



#### 3.2.6 轮廓属性(outline)

- 轮廓(outline)是绘制于元素周围的一条线, 位于边框边缘的外围, 可以起到突出元素这个盒子的作用
- 轮廓不属于元素的盒子模型体系

| 需求       | 属性          | 值                                                           |
| ---------- | ------------- | ------------------------------------------------------------ |
| 轮廓的样式 | outline-style | 9个值<br />none  没有轮廓, 默认值<br />dashed  虚线<br />dotted  点线<br />double  双线<br />solid  实线<br />groove  3D沟槽轮廓<br />ridge  3D脊轮廓<br />inset  3D嵌入轮廓<br />outset  3D突出轮廓 |
| 轮廓的粗细 | outline-width |                                                              |
| 轮廓的颜色 | outline-color |                                                              |

### 3.3 CSS定位

确定元素的的宽高

#### 3.3.1 默认定位

- 默认定位有三种块级元素(block）、行内元素(inline)、行内块元素(inline-block)

##### 3.3.1.1 行内元素(inline)

- 不自动换行
- 设置宽高无效
- 行内元素作为盒子时
  - margin仅能设置左右，不能设置上下
  - border、padding、content可以正常设置

##### 3.3.1.2 行内块元素(inline-block)

- 不自动换行
- 能够设置宽高
- 行内块元素作为盒子时，所有的属性都可正常设置

##### 3.3.1.3 块级元素(block)

- 自动换行
- 能够设置宽高
- 块级元素作为盒子时，所有的属性都可以正常设置

##### 3.3.1.4 属性`display`

可以用来转换元素, 比如见行内元素转换成行内块元素, 这样就可以设置宽高了

#### 3.3.2 浮动定位

- 浮动定位可以通过属性`float`实现

- float的默认值是none, 即不使用浮动定位

- float的值left, 会将本元素转换为行内块元素, 并且将本元素浮动到父标签的最左边, 将原来的元素顶到了后边

- float的值right, 会将本元素转换为行内块元素, 并且将本元素浮动到父标签的最右边, 将原来的元素顶到了前边

- ```html
  <style>
      body {
        background-color: rgba(255, 20, 145, 0.294);
      }
      span {
        width: 100px;
        height: 200px;
        background-color: darkcyan;
        color: rgba(248, 234, 37, 0.884);
        font-size: 1.5em;
        margin-right: 10px;
        margin-top: 10px;
      }
      #floatDemo3 {
        float: left;
      }
      #floatDemo1 {
        float: right;
      }
      #floatDemo2 {
        float: none;
      }
    </style>
    <body>
      <span>我是span1</span>
      <span>我是span2</span>
      <span>我是span3</span><br />
      <span id="floatDemo1">我是span1</span>
      <span id="floatDemo2">我是span2</span>
      <span id="floatDemo3">我是span3</span><br />
    </body>
  ```

#### 3.3.3 相对定位

- 属性`position`, 值为`relative`

- 相对本该在的位置进行偏移, 即以本元素本该在的位置为原点, 

- 属性`left`的值为横坐标, 属性`top`的值为纵坐标进行偏移

- ```html
  <style>
      body {
        background-color: rgba(178, 34, 34, 0.322);
      }
      span {
        font-size: 2em;
      }
      #span1,
      #span2,
      #span3,
      #span4,
      #span5,
      #span6 {
        border-style: solid;
        border-color: rgba(34, 25, 25, 0.842);
      }
      #span1,
      #span4 {
        background-color: rgba(34, 135, 139, 0.247);
      }
      #span2,
      #span5 {
        background-color: rgba(233, 245, 122, 0.664);
      }
      #span3,
      #span6 {
        background-color: rgba(103, 87, 197, 0.438);
      }
      #span4 {
        position: relative;
        top: 20px;
        left: 30px;
      }
      #span5 {
        position: relative;
        top: -20px;
        left: -30px;
      }
      #span6 {
        position: relative;
        top: 0px;
        left: 0px;
      }
    </style>
    <body>
      <span id="span1">艾瑞丝</span>
      <span id="span2">蒂法</span>
      <span id="span3">杰西</span><br />
      <span id="span4">艾瑞丝</span>
      <span id="span5">蒂法</span>
      <span id="span6">杰西</span>
    </body>
  ```

#### 3.3.4 固定定位

- 属性`position`, 值为`fixed`

- 以标签`<body>`的原点为原点, 

- 属性`left`的值为横坐标, 属性`top`的值为纵坐标进行偏移

- ```html
   <style>
      div {
        background-color: aqua;
        width: 500px;
        height: 4000px;
        position: relative;
        left: 200px;
        top: 100px;
      }
      span {
        font-size: 2em;
        background-color: rgb(160, 129, 189);
        padding: 5px 5px;
        position: fixed;
        left: 100px;
        top: 50px;
      }
    </style>
    <body style="background-color: bisque">
      <div>
        <span>我的目标是那星辰大海</span>
      </div>
    </body>
  ```

#### 3.3. 5 绝对定位

- 属性`position`, 值为`absolute`
- 以进行了定位的(浮动定位、相对定位、固定定位、绝对定位)、 关系最近的长辈元素的原点作为原点
- 属性`left`的值为横坐标, 属性`top`的值为纵坐标进行偏移

```html
  <style>
    div {
      background-color: aqua;
      width: 500px;
      height: 4000px;
      /*position: relative;
      left: 200px;
      top: 100px;*/
      /*float: right;*/
      position: fixed;
      left: 200px;
      top: 100px;
    }
    span {
      font-size: 2em;
      background-color: rgb(160, 129, 189);
      padding: 5px 5px;
      position: absolute;
      left: 100px;
      top: 50px;
    }
  </style>
  <body style="background-color: bisque">
    <div>
      <span>我的目标是那星辰大海</span>
    </div>
  </body>
```

#### 3.3.6 Z轴属性`z-index`

- 用来决定层叠的多个标签谁在上面, 谁在下面
- 值大的在上面, 值小的在下面
- z值没有额定数值(整型数字即可, 具体数字几, 看需求)

```html
<style>
    span {
      font-weight: bold;
      font-size: 2em;
      position: absolute;
      left: 100px;
      top: 50px;
      display: inline-block;
      width: 500px;
      height: 100px;
    }
    #luffy {
      color: rgba(0, 0, 139, 0.651);
      background-color: rgba(211, 50, 184);
      z-index: 11; /*路飞在上面*、
      /* z-index: 1; /*路飞在下面*/
    }
    #hancock {
      color: rgba(90, 15, 128, 0.897);
      background-color: rgba(128, 255, 0);
      z-index: 10;
    }
  </style>
  <body style="background-color: burlywood">
    <span id="luffy">我是要成为海贼王的男人--蒙奇·D·路飞</span>
    <span id="hancock">我要成为海贼王----------波雅·汉库克</span>
  </body>
```

## 四. CSS3

### 4.1 圆角属性`border-radius`

- 可以使元素的背景和边框变成圆角

- | 值                                 | 说明                               |
  | ---------------------------------- | ---------------------------------- |
  | border-raduis : Apx  Bpx  Cpx  Dpx | A--左上  B--右上  C--右下  D--左下 |
  | border-raduis : A%  B%  C%  D%     | A--左上  B--右上  C--右下  D--左下 |
  | border-radius: Apx                 | A--四个角都是A                     |
  | border-radius: A%                  | A--四个角都是A                     |

- 标签的宽高相等, 同时border-radius的值为50%时, 这个标签就成了圆形

```html
<style>
    span {
      position: relative;
      left: 100px;
      top: 100px;
      font-size: 3em;
      font-weight: bold;
      background-color: darkturquoise;
      padding: 5px 5px 5px 5px;
      border-style: solid;
      border-width: 10px;
    }
    #borderRadius1 {
      border-radius: 10px 40px 0px 80px;
    }
    #borderRadius2 {
      border-radius: 30px;
    }
    #borderRadius3 {
      display: inline-block;
      width: 500px;
      height: 500px;
      border-radius: 50%;
      text-align: center;
    }
  </style>
  <body>
    <span>我的目标是那星辰大海</span><br />
    <p>&nbsp;</p>
    <span id="borderRadius1">我的目标是那星辰大海</span>
    <p>&nbsp;</p>
    <span id="borderRadius2">我的目标是那星辰大海</span>
    <p>&nbsp;</p>
    <span id="borderRadius3">圆形</span>
  </body>
```

### 4.2 盒子阴影属性`box-shadow`

- 给标签的盒子模型体系加上阴影特效

- **值的示例:** box-shadow : 1  2  3  4  5

  - 1 -- 水平偏移
  - 2 -- 垂直偏移
  - 3 -- 模糊半径
  - 4 -- 扩张半径
  - 5 -- 阴影颜色

- ```html
  <style>
      span {
        display: inline-block;
        width: 500px;
        height: 500px;
        background-color: darkviolet;
        box-shadow: 50px 50px 30px 30px rgba(128, 128, 128, 0.6);
      }
    </style>
    <body>
      <span></span>
    </body>
  ```

### 4.3 背景

#### 4.3.1 背景颜色--渐变色

- 渐变色实际上是一张图片, 可以看成是由定义渐变色的关键字和颜色组成的图片, 所以渐变色这个值 只能由  接收图片的css属性  来接收, 比如: background属性和background-image属性

##### 4.3.1.1 背景颜色线性渐变

- 关键字:**linear-gradient** , linear的意思是线性的, gradient的意思时坡度、梯度
- 竖直向上方向的显示起始线，根据角度偏移这条线后， 在这条线的方向上, 颜色依次渐变， 默认得角度是180度

| 值的示例                                                     | 说明                                      |
| ------------------------------------------------------------ | ----------------------------------------- |
| background : linear-gradient(颜色1, 颜色2)                   |                                           |
| background : linear-gradient(颜色1, 颜色2, ... , 颜色n)      |                                           |
| background : linear-gradient(Xdeg, 颜色1, 颜色2, ... , 颜色n) | deg的全称是degree, 等级、度数、角度的意思 |

```css
<style>
    div {
      font-size: 5em;
      font-weight: bold;
    }
    #demo1 {
      background: linear-gradient(red, rgba(198, 211, 20, 0.952), green);
    }
    #demo2 {
      background: linear-gradient(
        180deg,
        red,
        rgba(198, 211, 20, 0.952),
        green
      );
    }
    #demo3 {
      background: linear-gradient(90deg, red, rgba(198, 211, 20, 0.952), green);
    }
    #demo4 {
      background: linear-gradient(
        -90deg,
        red,
        rgba(198, 211, 20, 0.952),
        green
      );
    }
    #demo5 {
      background: linear-gradient(10deg, red, rgba(198, 211, 20, 0.952), green);
    }
    #demo6 {
      background: linear-gradient(60deg, red, rgba(198, 211, 20, 0.952), green);
    }
  </style>
  <body>
    <div id="demo1">我要成为世界第一的大剑豪</div>
    <div id="demo2">我要成为世界第一的大剑豪</div>
    <div id="demo3">我要成为世界第一的大剑豪</div>
    <div id="demo4">我要成为世界第一的大剑豪</div>
    <div id="demo5">我要成为世界第一的大剑豪</div>
    <div id="demo6">我要成为世界第一的大剑豪</div>
  </body>
```

##### 4.3.1.2 背景颜色径向渐变

- 以元素的中心为圆心, 然后从圆心向外发散渐变

- 关键字: **radial-gradient**, radial的意思是散射的, 辐射的

- | 值的示例                                                | 说明 |
  | ------------------------------------------------------- | ---- |
  | background : radial-gradient(颜色1, 颜色2)              |      |
  | background : radial-gradient(颜色1, 颜色2, ... , 颜色n) |      |

```html
<style>
    div {
      font-size: 5em;
      font-weight: bold;
      width: 500px;
      height: 300px;
    }
    #demo1 {
      background: radial-gradient(red, green);
    }
    #demo2 {
      background: radial-gradient(red, rgba(198, 211, 20, 0.952), green);
    }
    #demo3 {
      width: 500px;
      height: 500px;
      border-radius: 50%;
      background: radial-gradient(
        red,
        rgba(198, 211, 20, 0.952),
        green,
        rgba(116, 16, 136, 0.473),
        black
      );
    }
  </style>
  <body>
    <div id="demo1">我要成为世界第一的大剑豪</div>
    <div id="demo2">我要成为世界第一的大剑豪</div>
    <div id="demo3"></div>
  </body>
```

#### 4.3.2 背景原点属性`background-origin`

- 属性`background-origin`, 用来指定背景图像位置的原点

- 属性`background-position`与属性`background-origin`的异同

  - 相同
    - 都能设置背景图片的原点
  - 区别
    - `background-origin`的优先级大于`background-position`
    - `background-position`的原点是相对`background-origin`设置的原点来偏移的
    - `background-origin`只能设置三个值, `background-position`能设置无数值
    - `backgound-position`的一个值`center`的本质上其实就是设置背景图片原点的偏移值, 使背景图片的中心与标签的中心重合

- 可取的值

  - border-box    以边框的原点为原点
  - padding-box    以padding的原点为原点,  默认值
  - content-box    以内容的原点为原点

- ```html
  <style>
      div {
        width: 500px;
        height: 300px;
        border-style: solid;
        border-width: 50px;
        border-color: rgba(192, 46, 185, 0.37);
        margin-top: 30px;
        padding: 50px 50px;
        background-size: contain;
        background-repeat: no-repeat;
        background-position: center;
      }
      #demo {
        background-image: url("image/burin\ \(2\).gif");
      }
      #demo1 {
        background-image: url("image/burin\ \(2\).gif");
        background-origin: border-box;
      }
      #demo2 {
        background-image: url("image/burin\ \(2\).gif");
        background-origin: padding-box;
      }
      #demo3 {
        background-image: url("image/burin\ \(2\).gif");
        background-origin: content-box;
      }
    </style>
    <body style="background-color: rgb(139, 190, 139)">
      <div id="demo"></div>
      <div id="demo1"></div>
      <div id="demo2"></div>
      <div id="demo3"></div>
    </body>
  ```

  | 图片                                                         |
  | ------------------------------------------------------------ |
  | ![burin (2)](%E7%AC%AC02%E7%AB%A0%20CSS.assets/burin%20(2).gif) |

  

#### 4.3.3 背景图片裁切属性`background-clip`

- 属性`background-clip`, 用来裁切掉盒子体系中装不下的部分

- 可取的值

  - border-box    裁切掉边框外的部分, 默认值
  - padding-box    裁切掉padding外的部分
  - content-box    裁切掉content外的部分

- ```html
  <style>
      div {
        display: inline-block;
        width: 500px;
        height: 300px;
        border-style: solid;
        border-width: 50px;
        border-color: rgba(192, 46, 185, 0.37);
        margin-top: 30px;
        padding: 50px 50px;
        background-repeat: no-repeat;
        background-position: center;
      }
      #demo {
        background-image: url("image/burin\ \(2\).gif");
      }
      #demo1 {
        background-image: url("image/burin\ \(2\).gif");
        background-clip: border-box;
      }
      #demo2 {
        background-image: url("image/burin\ \(2\).gif");
        background-clip: padding-box;
      }
      #demo3 {
        background-image: url("image/burin\ \(2\).gif");
        background-clip: content-box;
      }
    </style>
    <body style="background-color: rgb(139, 190, 139)">
      <img src="image/burin (2).gif" />
      <div id="demo"></div>
      <div id="demo1"></div>
      <div id="demo2"></div>
      <div id="demo3"></div>
    </body>
  ```

#### 4.3.4 背景图片大小属性`background-size`

- 属性`background-size`, 用来控制背景图片的缩放
- 可以取三个值
  - auto    原始大小, 默认值
  - cover   缩放到可以完全覆盖背景区域的最小大小
  - content    缩放到正好能够放进背景区域的最大大小

### 4.4 过渡动画属性`transition`

- 用来设置标签的某个或者某几个属性从一个状态到另一个状态这个**时间段内**的变化过程

- 只有标签的状态发生改变时, 对应的过渡动画才会显示

- 这里的状态是指类似  伪类中的状态(link  从未点击过; visited  已经点击过; hover  悬停;  active  正在点击中)

- 缺点是只能设置状态变化前和状态变化后的这两个时间点的属性值, 无法控制这个时间段内的属性值

- 属性`transition` 可以分解成四个过渡动画属性

  - 属性`transition-property`    设置启用过渡动画功能的属性, 可以设置单个的值, 也可以设置多个值, 多个值之间用空格隔开
    - 例如`transition-property: width`
    - 例如`transition-property: width height`
  - 属性`transition-duration`    设置过渡动画的持续时间
  - 属性`transition-timing-function`    设置过渡动画的定时变化函数, 有5种值
    - linear    匀速变化
    - ease    减速变化
    - ease-in    加速变化
    - ease-out    减速变化
    - ease-in-out    先加速后减速变化
  - 属性`transiton-delay`    过渡动画开始前的延迟时间

- 目前CSS3只为部分属性开发出过渡动画功能, 下图就是这些属性

  - ![](%E7%AC%AC02%E7%AB%A0%20CSS.assets/%E6%9C%89%E8%BF%87%E6%B8%A1%E5%8A%9F%E8%83%BD%E7%9A%84%E5%B1%9E%E6%80%A7.png)
  - 也可在以下网址查看<https://developer.mozilla.org/zh-CN/docs/Web/CSS/CSS_animated_properties>

- ```html
  <style>
      div {
        width: 100px;
        height: 100px;
        background-color: rgba(184, 109, 109, 0.692);
        margin-top: 30px;
      }
      #demo1 {
        transition-property: width height;
        transition-duration: 800ms;
        transition-timing-function: linear;
        transition-delay: 0s;
      }
      #demo2 {
        transition-property: width height;
        transition-duration: 800ms;
        transition-timing-function: linear;
        transition-delay: 0s;
      }
  
      #demo1:hover {
        width: 300px;
      }
      #demo2:hover {
        width: 300px;
        height: 300px;
      }
    </style>
    <body>
      <div id="demo1"></div>
      <div id="demo2"></div>
    </body>
  ```

### 4.5 动画属性`animation`

- 用来给标签设置一个动画特效, 可以通过给标签的单个属性或多个属性 在动画持续时间内 的不同时间点 设置不同的值来控制动画效果

- 动画特效可以可以随时开始, 不依赖状态的变化

- 可动画属性<https://developer.mozilla.org/zh-CN/docs/Web/CSS/CSS_animated_properties>

- 属性`animation`可以分解成六个动画属性

  - `animation-name`    动画关键帧的名称

    - 动画关键帧需要在选择器外进行声明, 格式如下

    - ```css
      /* 第一种方式 */
      @keyframes 名称{
          form{...}
          to{...}
      }
      /* 第二种方式, 建议使用该方式 */
      @keyframes 名称{
          0%{...}
          10%{...}
          20%{...}
          ...
          100%{...}
      }
      ```

  - `animation-duration`    动画持续时长

  - `animation-timing-function`    动画在各个时间点间的变化函数, 可取5个值

    - linear
    - ease
    - ease-in
    - ease-out
    - ease-in-out

  - `animation-iteration-count`    动画持续时间内关键帧的迭代重复次数, 有两种可取的值

    - 数字, 表示次数
    - infinite, 表示无数次, 取这个值后,不论动画的持续时长是多少, 关键帧都会一直重复下去

  - `animation-direction`    关键帧组的执行方向

    - normal    自上而下, 正常执行, 默认值
    - alternate    自上而下, 来回执行
    - alternate-reverse    自下而上, 来回执行

  - `animation-delay`    动画执行前的延迟时间, 可省略

- ```html
  <style>
      img {
        width: 100px;
        /* animation: zoomIn 2s ease-in-out 3s 5; */
        animation-name: zoomIn;
        animation-duration: 3s;
        animation-timing-function: ease-in-out;
        animation-delay: 1s;
        animation-iteration-count: infinite;
        animation-direction: alternate;
      }
      @keyframes zoomIn {
        /* form {
          width: 500px;
        }
        to {
          height: 600px;
        } */
  
        10% {
          width: 100px;
          height: auto;
        }
        50% {
          width: 300px;
          height: auto;
        }
        100% {
          width: 500px;
          height: auto;
        }
      }
    </style>
    <body>
      <img src="image/nami (12).jpg" />
    </body>
  ```

## 五. 补充

### 5.1 垂直对齐属性`vertical-align`

#### 5.1.1 子元素使用`vertical-align:top`

子元素中使用`vertical-align:top`, 可以使子元素的上边框与父元素的上边框对齐

#### 5.1.2 父元素中配合`display:table-cell`使用

父元素中配合`display:table-cell`使用, 可以实现父元素中的子元素垂直方向的向上对齐、居中对齐、向下对齐

```html
<style>
选择器名{
    display:table-cell;
    vertical-align: middle;/*可以取值top  middle  bottom*/
}
</style>
```

### 5.2 表格单元格的间距

- 属性`border-collapse`和`border-spacing`
- 在标签`<table>`的选择器中使用, 可以用来设置表格单元格之间的间距
- `border-collapse`有两个值: `collapse`和`separate`
  - `collapse`    单元格间用一条边框线隔开
  - `separate`    单元格间具有距离, 需配合`border-spacing`使用
    - `border-spacing`用来设置单元格将的距离

### 5.3 `width`和`height`

`width`和`height`设置的是元素的盒子模型中的content的高度

### 5.4 属性`overflow`

- `overflow:scroll`  
- 当父元素的宽高小于子元素的宽高时, 父元素使用这个属性后父元素就可以滚动了, 从而装下子元素

### 5.5 属性`visibility`

- 表示元素是否可见
- 值为`hidden`时隐藏元素, 注意元素实际上还是在那里, 只是看不到罢了
- 值为`visible`时元素可见



