# f第01章 HTML

[toc]

## 一. HTML介绍

### 1.1 介绍

- HTML指的是**超文本**元素语言(Hyper Text Markup Language), 元素又可以叫作标记
- HTML是专门用来编写网页的计算机语言
- 超文本(Hyper Text(是指在HTML语言规范中被赋予了特殊含义的文本, 这些超文本是做为HTML文件的元素元素、元素属性、元素属性的值来使用

### 1.2 HTML元素的规范

### 1.3 VSCode--常用开发html的软件

### 1.4 HTML文件命名的规范

- 使用小写的英文字母，数字和下划线和减号的组合，不得包含汉字空格和特殊符号

## 二. HTML常用的元素

### 2.1 文件级元素

**就是一个html文件中必不可少的元素**

#### 2.1.1 `<html>`元素:

**代表当前书写的是一个HTML文档**

##### 2.1.1.1 属性`lang`

- 用来设置当前网页时用的是什么国家的语言, 浏览器的自动翻译插件会根据这个属性的值来决定是否翻译这个网页
- 值是各个国家官方语言的缩写, 比如英语`en`, 简体中文`zh-cn`

#### 2.1.2 `<head>`元素:

**存储本页面的一些重要的信息, 该元素下的数据不会显示在网页上**

##### 2.1.2 子元素`<title>`

- `<title>`元素只能是`<head>`元素的字元素
- 该元素存放的内容是网页元素的名字

#### 2.1.3 `<body>`元素

- **该元素下的数据会显示在网页上**
- 网页上的内容需要换行时, 后面一定要有换行元素, 或者使用具有换行功能的元素将该内容包裹

##### 2.1.3.1 属性`text`

**用于设置该元素存储的文本数据的颜色[^color]**, 默认黑色

该属性的值有三种形式:

- `'颜色单词'` 形式    例如: 'red'   `black`    `green`
- `'rgb(111, 122, 133)'`形式
- `'#88aaee'` 形式

##### 2.1.3.2 属性`bgcolor`

**用于设置页面的背景颜色[^color]**, 默认白色

该属性的值有三种形式:

- `'颜色单词'` 形式    例如: 'red'   `black`    `green`, 默认是`black`
- `'rgb(111, 122, 133)'`形式
- `'#88aaee'` 形式

##### 2.1.3.3 属性`background`

用于设置网页的背景图片, 会覆盖网页的背景颜色(注意是覆盖而不是抹除), 默认无

该属性的值:

- `'图片的路径'`

#### 2.1.4 示例代码

```html
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>01-fileMarkUp</title>
    </head>

    <body text='#ffffb6' bgcolor='#69491d' background='image/bg2.jpg'>
        波雅·汉库克
        妮可·罗宾
        贝优妮塔
        黑百合
        劳拉
        穗乃果
    </body>

</html>
```

### 2.2 排版元素

排版元素只会是`<body>`元素的子元素或者在其子元素下使用

#### 2.2.1 HTML注释

```html
<!-- 这是html注释的书写方式 -->
```

#### 2.2.2 换行元素 -- `<br/>`

- `<br/>`元素就是一个**换行(回车)功能元素**, 元素中的`/`可有可无. 有`/`是html语言的标准化, 但是html语言是一门不那么严谨的语言
- br是break的简写

#### 2.2.3 段落元素 -- `<p>`

- 在`<p>`元素中的内容, 会与开始元素之前和结束元素之后的内容分别**自动换行**并分别产生一个空白行从而形成一个段落
- p是paragraph的简写

##### 2.2.3.1 属性`align`

- align的意思是对齐, 排列, 用来设置段落中的内容的对齐方式, 
- 可取的值有:
  - left     左对齐, **默认值**
  - right    右对齐
  - center  居中对齐

#### 2.2.4 水平线元素 -- `<hr/>`

- 会与元素之前和元素之后的内容**自动换行**
- 独自成行, 会在页面上产生一条水平线
- hr是horizontal的简写

##### 2.2.4.1 属性`width`

- width的意思是宽度, 用来设置水平线的宽度
- 值有两种模式
  - 百分比模式, 例如`50%` 即宽度为浏览器宽度的50%, **默认值**是百分比模式的100%`
  - 像素数模式, 例如`500px` 即宽度占500个像素

##### 2.2.4.2 属性`align`

- align的意思是对齐, 排列, 用来设置水平线的在行内的对齐方式, 
- 可取的值有:
  - left     左对齐, **默认值**
  - right    右对齐
  - center  居中对齐

##### 2.2.4.3 属性`size`

- 用来设置水平线的厚度
- 值是像素数模式, 例如`50px`, 即厚度有50个像素

##### 2.3.4.4 属性`color`

- 用来设置水平线的颜色
- 该属性的值有三种模式
  - `'颜色单词'` 模式   例如: 'red'   'black'  'green', 默认是'black'
  - `'rgb(111, 122, 133)'`形式
  - `'#88aaee'` 形式

#### 2.2.5 分区元素`<div>`和`<span>`

- 分区元素有两种, 分别是`<div>`和`<span>`
- `div`元素是一个块元素, 其存储的内容会与开始元素之前的内容和结束元素之后的内容分别**自动换行**, 这样的元素为块级元素
- `<span>`元素不会自动换行, 但可以划分出一块区域, 这样的元素为行内元素
- `<span>`可以是`div`的子元素, 但`<div>`不能是`<span>`的子元素
- `<div>`和`<span>`需要配合css才能发挥最大的实力

### 2.3 字体方面的元素

用来设置文本内容的字体, 字体大小和字体颜色

#### 2.3.1 字体样式元素`<font>`

##### 2.3.1.2 属性`face`

- 用于设置字体,
- 值就是字体的名称, 例如: 宋体  隶属   楷体等等

##### 2.3.1.2 属性`size`

- 用于设置字体的大小
- 大小取值范围是1~7， 1最小，7最大， 默认值是3， 配合css可以更大

##### 2.3.1.3 属性`color`

- 用来设置字体的颜色， 会覆盖掉`<body>`元素设置的字体颜色
- 该属性的值有三种模式
  - `'颜色单词'` 模式   例如: 'red'   'black'  'green', 默认是'black'
  - `'rgb(111, 122, 133)'`形式
  - `'#88aaee'` 形式

#### 2.3.2 字体格式化元素

- `<b>`    文本加粗, bold的简写
- `<i>`    文本倾斜, incline的简写
- `<del>`    在文本上加删除线 , delete line的简写
- `<u>`      在文本下加下划线, under line的简写
- [html5新增元素](#4.4 新增字体格式化元素)

#### 2.3.3 标题元素`<h1>` `<h2>` `<h3>` `<h4>` `<h5>` `<h6>`

- 用来存储作为标题的文本
- `<h1>`作为标题的等级最高, `<h6>`作为标题的等级最低
- 会与开始元素之前和结束元素之后的内容分别**自动换行**, 并产生一定距离, 字体也进行了加粗处理

### 2.4 列表方面的元素

- 列表元素可以对与其配套的子元素下的内容以列表的形式展示
- 列表与相邻的元素隔着一个空白行
- 列表元素分为有序列表元素和无序列表元素

#### 2.4.1 有序列表元素`<ol>`

- 用来表明该元素下的内容一有序列表的样式显示
- 与子元素`<li>`是配套的, li是list的简写
- ol, 是ordered list的简写, ordered的意思是有序的, list是清单, 列表的意思

##### 2.4.1.1 属性`type`

- 用来设置排序时的序号样式
- 可取的值有有5个
  - 'A'    大写字母样式的序号
  - 'a'    小写字母样式的序号
  - '1'    数字样式的序号, **默认值**
  - 'i'    小写罗马数字样式的序号
  - 'I'    大写罗马数字样式的序号

##### 2.4.1.2 属性`start`

- 值是个整数数字, 表示序号从几开始, **默认值**是'1'

##### 2.4.1.3 示例代码

```html
<body>
    <ol type="1">
      <li>艾瑞丝</li>
      <li>蒂法</li>
      <li>千手纲手</li>
      <li>日向雏田</li>
      <li>贝优妮塔</li>
    </ol>
    <hr size="5px" color="red" />
    <ol type="1" start="10">
      <li>艾瑞丝</li>
      <li>蒂法</li>
      <li>千手纲手</li>
      <li>日向雏田</li>
      <li>贝优妮塔</li>
    </ol>
    <hr size="5px" color="red" />
    <ol type="A">
      <li>艾瑞丝</li>
      <li>蒂法</li>
      <li>千手纲手</li>
      <li>日向雏田</li>
      <li>贝优妮塔</li>
    </ol>
    <hr size="5px" color="red" />
    <ol type="A" start="10">
      <li>艾瑞丝</li>
      <li>蒂法</li>
      <li>千手纲手</li>
      <li>日向雏田</li>
      <li>贝优妮塔</li>
    </ol>
    <hr size="5px" color="red" />
    <ol type="I" start="10">
      <li>艾瑞丝</li>
      <li>蒂法</li>
      <li>千手纲手</li>
      <li>日向雏田</li>
      <li>贝优妮塔</li>
    </ol>
  </body>
```



#### 2.4.2 无序列表元素`<ul>`

- 用来表明该元素下的内容以无序列表的样式显示
- **与`<li>`子元素是配套的, li是list的简写**
- ul是unordered list, unordered的意思是无序的

##### 2.4.2.1 属性`type`

- 表示无序列表序号的样式
- 可取的值有3个
  - 'disc'    圆盘也就是实心圆样式, **默认值**
  - 'circle'    圆圈也就是空心圆样式
  - 'square'    方块样式

##### 2.4.2.2 示例代码

```html
  <body>
    <ul type="disc">
      <li>艾瑞丝</li>
      <li>蒂法</li>
      <li>千手纲手</li>
      <li>日向雏田</li>
      <li>贝优妮塔</li>
    </ul>
    <hr size="5px" color="red" />
    <ul type="square">
      <li>艾瑞丝</li>
      <li>蒂法</li>
      <li>千手纲手</li>
      <li>日向雏田</li>
      <li>贝优妮塔</li>
    </ul>
    <hr size="5px" color="red" />
    <ul type="circle">
      <li>艾瑞丝</li>
      <li>蒂法</li>
      <li>千手纲手</li>
      <li>日向雏田</li>
      <li>贝优妮塔</li>
    </ul>
  </body>
```

### 2.5 图像元素`<img>`

- 可以让我们的网页引入一张图片并显示出来, img是image的简写
- 可以引入静态图片, 也可以引入动态图片

#### 2.5.1 属性`src`

- 用于设置图片的来源
- 值有两种模式
  - '图片的本地路径'
  - '图片的网络url'

#### 2.5.2 属性`width`

- width的意思是宽度, 用来设置图片在网页中的宽度
- 值有两种模式
  - 百分比模式, 例如`50%` 即宽度为浏览器宽度的50%
  - 像素数模式, 例如`500px` 即宽度占500个像素
  - 默认值有两种
    - 当未设置属性height时, 属性width的默认值是图片原始宽度的像素大小
    - 当设置了属性height时, 属性width的默认值是图片等比例缩放后图片宽度的像素大小

#### 2.5.3 属性`height`

- height的意思是高度, 用来设置图片在网页中的高度
- 值只有1种模式
  - 像素数模式, 例如`500px` 即宽度占500个像素
  - 默认值有两种
    - 当未设置属性width时, 属性height的默认值是图片原始高度的像素大小
    - 当设置了属性width时, 属性height的默认值是图片等比例缩放后图片高度的像素大小

#### 2.5.4 属性`border`

- 用来设置图片边框的厚度, 边框的颜色是`<body>`元素的属性text设置的颜色
- 值只有一种模式
  - 像素数模式, 例如`5px` 即不骗边框厚度占5个像素, 默认值是0px

#### 2.5.5 属性`alt`

- src中的图片加载失败时要显示的文本信息
- 值就是一些文本, 默认值是空文本

#### 2.5.6 属性`titile`

- 当鼠标悬停在图片上时, 显示的文本信息
- 至九十一些文本, 默认值是空文本

#### 2.5.7 属性`align`

- 当图片和文本处于同一行时, 决定图片和和文本的对齐方式
- **可取的值有5个, 但建议只使用默认值'bottom', 因为其它的值会导致文本与图片的排版混乱**
  - 'left'
  - 'right'
  - 'middle'
  - 'top'
  - 'bottom'    **默认值**

#### 2.5.8 示例代码

```html
<body>
    <img src="image/burin (1).gif" alt="图片加载失败" title="泳池美女" />
    <hr size="5px" color="green" />
    <img src="image/burin (1.gif" alt="图片加载失败" title="泳池美女" />
    <hr size="5px" color="green" />
    <img src="image/nami (7).jpg" alt="图片加载失败" title="CG美女" />
    <hr size="5px" color="green" />
  </body>
```



### 2.6 超链接元素`<a>`

- 可以实现跳转到其他页面的操左

- **超链接元素存储的内容会显示在网页上**, 点击这个内容就会跳转到另一个网页上

- 超链接元素存储的内容不仅可以是文本数据, 也可以是图片元素

  - ```html
    <body>
        <a href="https://www.baidu.com">这是一个超链接, 点击后就可以跳转</a>
        <hr size="5px" color="red" />
        <a href="https://www.baidu.com" target="_self"
          >这是一个超链接, 点击后就可以跳转</a
        >
        <hr size="5px" color="red" />
        <a href="https://www.baidu.com" target="_blank"
          >这是一个超链接, 点击后就可以跳转</a
        >
        <hr size="5px" color="red" />
        <a href="https://www.baidu.com" target="_blank"
          ><img src="image/nami (1).jpg" title="点击图片后跳转"
        /></a>
      </body>
    ```

- 点击超链接元素显示在网页上的文本或者图片就可以跳转到属性href所指的链接上

#### 2.6.1 属性`href`

- 全称是hyper text reference, 表示要跳转到的链接

- 值就是个url链接

- 当属性`href`的值为字符`#`开头的字符串时, 可以禁止超链接元素的点击跳转

  - ```html
    <a href="#">禁止跳转</a><br/>
    <a href="#    ">禁止跳转</a><br/>
    <a href="#https://www.baidu.com">禁止跳转</a><br/>
    ```



#### 2.6.2 属性`target`

- 表示在何处打开这个链接页面
- 可取的值有2个
  - '_self'    在本页面打开, **默认值**
  - '_blank'    在空白窗口(也就是新窗口)打开, blank的意思是空白的

#### 2.6.3 功能性链接

不怎么使用

##### 2.6.3.1 发邮件

点击后会打开计算机上默认的发送邮件软件, 收件人是href里的邮箱

```html
<a href='mailto:zq007012@foxmail.com'>zq的邮箱</a>
```

##### 2.6.3.2 QQ聊天窗口

```html
<a href='tencent://message/?uid=QQ号&Menu=yes'>
	<img src='http://wpa.qq.com/pa?p=1:615050000:7'/>
    <!-- 'http://wpa.qq.com/pa?p=1:615050000:7'是qq图标的url -->
</a>
```

### 2.7 表格元素

- 表格由三部分组成: 父元素`<table>`、子元素`<tr>`、次级子元素`<td>`
- `<tr>`只能是`<table>`的子元素, `<td>`只能是`<tr>`的子元素
- `<table>`表示这里有一个表格, 而`<tr>`表示这是表格中的一行, `<td>`表示这是一个单元格

#### 2.7.1 表格元素`<table>`

- 用来定义一个表格, 可用来定义表格的边框粗细, 表格的总宽度, 表格在行内的对齐方式, 单元格间的间距
- `<table>`元素中的内容会与开始元素前和结束元素后的内容分别**自动换行**

##### 2.7.1.1 属性`border`

- 用来设置表格边框的粗细, 
- 值可以是是数字也可以是带像素单位px的数字, 两者具有相同的作用, 例如`10`和`10px`表示的都是表格边框的粗细为10个像素, 默认值是`0px`
- 只有`border`的值大于0时, 表格的单元格才会被一条黑色实线包裹起来

##### 2.7.1.2 属性`width`

- 表格的总宽度
- 值有两种模式
  - 百分比模式, 例如`50%` 即宽度为浏览器宽度的50%, **默认值**是百分比模式的100%`
  - 像素数模式, 例如`500px` 即宽度占500个像素

##### 2.7.1.3 属性`align`

- 用来表示表格整体在行内的对齐方式
- 可取的值有:
  - left     左对齐, **默认值**
  - right    右对齐
  - center  居中对齐

##### 2.7.1.4 属性`cellspacing`

- 用来设置单元格间的间距, cell的意思是细胞, 指的是单元格, spacing指空间 间距
- 值可以是是数字也可以是带像素单位px的数字, 两者具有相同的作用, 例如`10`和`10px`表示的都是单元格间的间距为10个像素,
- 当`border`的值等于0时, `cellsapcing`的默认值为`0px`
- 当`border`的值大于0时, `cellsapcing`的默认值为`1px`
- 当`border`的值大于0时, 每个单元格都会被一条黑色实线包裹, 此时当`cellspacing`的值为0时, 相邻单元格之间的黑色实线就会重合, 看起来好像变成了一条

#### 2.7.2 表格行元素`<tr>`

- 表示表格中的一行, 可以该行里的所有单元格的样式进行设置
-  `<tr>`元素的个数决定了这个表格有多少行

##### 2.7.2.1 属性`align`    

- 用来设置该行下所有单元格内的文本在单元格内的对齐方式
- 可取的值有3个:
  - left     左对齐, **默认值**
  - right    右对齐
  - center  居中对齐

#### 2.7.3 单元格元素`<td>`

- 表示一个单元格,  是table data cell的简写, 
- 除了用来存储数据外, 还可以设置列和行的合并

##### 2.7.3.1 属性`colspan`

- 用来设置从本单元格开始数  向下  合并几个单元格
- 值是个数字, 例如'5', 表示向下合并5个单元格(包括本单元格), 默认值是'0'

##### 2.7.3.2 属性`rowspan`

- 用来设置从本单元格开始数  向右  合并几个单元格
- 值是个数字, 例如'5', 表示向右合并5个单元格(包括本单元格), 默认值是'0'

##### 2.7.4 示例代码

实现以下表格

![](%E7%AC%AC01%E7%AB%A0%20HTML.assets/One_2021-03-19_170223.png)

<img src="%E7%AC%AC01%E7%AB%A0%20HTML.assets/elf.gif" style="zoom:50%;" />

```html
<body>
    <table border="5px" width="75%" align="center" cellspacing="0">
      <tr align="center">
        <td colspan="3">
          <font size="5" color="#6830e9"><b>海贼王</b></font>
        </td>
      </tr>
      <tr align="center">
        <td rowspan="3">
          美女榜<br />
          <img
            src="image/burin (3).gif"
            height="200px"
            alt="图片加载失败"
            title="精灵"
          />
        </td>
        <td>汉库克</td>
        <td>白星</td>
      </tr>
      <tr align="center">
        <td>罗宾</td>
        <td>奥莉维亚</td>
      </tr>
      <tr align="center">
        <td>瑞贝卡</td>
        <td>薇薇</td>
      </tr>
    </table>
  </body>
```

### 2.8 表单元素

- 表单由表单元素`<form>`和与之配套的一系列子元素(又名元素、控件)构成
- 表单可以让我们将客户端录入的信息上传到服务器端
- 简单说, 通过表单可以将要提交的数据提交到指定的位置
- 常见的登录页面和注册页面, 都是使用表单实现的
  - ![](%E7%AC%AC01%E7%AB%A0%20HTML.assets/HTML%E8%AF%A6%E8%A7%A3.png)

#### 2.8.1 表单元素`<form>`

- 用来设置表单下收集到的内容要提交到的**目的地**以及提交的方式
- 存储用来收集内容的表单控件

##### 2.8.1.1 属性`action`

- 设置表单下收集到的内容要提交到的目的地
- 值是个url

##### 2.8.1.2 属性`method`

- 用来设置表单下收集到的内容提交的方式
- 可取的值有两个
  - get    此种方式提交时传输的数据量较少(只会传输普通文字信息, 传输照片文件的话会失败), 明文提交(提交时再浏览器的url后面会显示要提交的数据, 不适合用于登陆操作)
  - post    此种方式提交时传输的数据量较大(传递文字图片文件都行), 密文提交(浏览器的url后面看不到提交的数据信息)

#### 2.8.2 表单控件

- 每个表单控件都==必须设置==属性`name`, 这样就能根据name的值对表单提交的数据加以识别了
- [html5新增的表单控件](#4.3 新增[表单控件)

##### 2.8.2.1 输入控件`<input/>`

- 输入控件可以根据属性`type`的值而产生不同的种类的输入控件
- [html5新增输入控件](#4.3.1 新增的输入控件`<input/>`)

###### 2.8.2.1.1 属性`type`

- 可以用来设置输入控件的种类
- 值有八个, 可以用来设置八种输入控件
  - 'text'   普通的文本输入框, 还需配合另外两个属性来使用
    - 属性`placeholder`    用来设置输入框的提示文本, 当向输入框中输入文字时, 该提示会自动消失, placeholder的意思是占位符
    - 属性`maxlength`    用来该输入框里最多能输入多少个字符, 值是个数字, 比如'12', 表示这个输入框里最多输入12个字符
  - 'password'    密码输入框, 输入的文字不会显示, 以黑色实心圆代替, 还需配合另外两个属性来使用
    - 属性`placeholder`    用来设置输入框的提示文本, 当向输入框中输入密码时, 该提示会自动消失, placeholder的意思是占位符
    - 属性`maxlength`    用来该输入框里最多能输入多少个字符, 值是个数字, 比如'12', 表示这个输入框里最多输入12个字符
  - 'checkbox'    可以进行多选的复选框, 属性name的值相同的复选框构成了一组复选框, 这组复选框里的复选框通过属性`value`的值加以区分, , 复选框还可以通过另一属性来设置默认勾选的选项
    - 属性`value`    用于区分同一组复选框里的复选框, 每一个复选框都==必须设置==属性`value`, 并且值不可重复, 值可以是任意文本
    - 属性`checked`    只要元素里写了这个属性不管值是什么都表明这个复选框被勾选了, 若不想这个复选框被勾选就不要在元素里写这个属性
  - 'radio'    可以进行单选的单选框, 属性name的值相同的单选框构成了一组单选框, 这组单选框里的单选框通过属性`value`的值加以区分, 单选框还可以通过另一属性`checked`来设置默认勾选的选项
    - 属性`value`     用于区分同一组单选框里的单选框, 每一个单选框都==必须设置==属性`value`, 并且值不可重复, 值可以是任意文本
    - 属性`checked`    只要元素里写了这个属性不管这个属性的值是什么都表明这个单选框被勾选了, 若不想这个复选框被勾选就不要在元素里写这个属性
  - 'file'    用来上传文件的输入框
  - 'reset'    在表单下生成一个重置按钮, **没有可扩展性**, 点击后可以清除所有表单中已输入的内容, 可以通过设置属性`value`来设置按钮上显示文字
    - 属性`value`    用来设置按钮上显示的文字, 之可以是任意文本
  - 'submit'    在表单下生成一个提交按钮, **没有可扩展性**, 可以提交表单中所有输入的内容, 可以通过设置属性`value`来设置按钮上显示文字
    - 属性`value`    用来设置按钮上显示的文字, 之可以是任意文本
  - 'button'    在表单下生成一个普通按钮, 一般用来配合javascript使用, **可扩展性很高**, 可以通过设置属性`value`来设置按钮上显示文字
    - 属性`value`    用来设置按钮上显示的文字, 之可以是任意文本

###### 2.8.2.1.2 属性`name`

- 每个输入控件都必须设置属性`name`, 这样就能根据name的值对表单提交的数据加以识别了
- 值可以是任意文本
- 编程技巧:  输入控件的属性`name`建议写在属性`type`之后

###### 2.8.2.1.3 属性`disable`

- 只要元素里写了这个属性, 不管这个属性的值是什么都表明这个表单元素不可更改, 只有不写这个属性这个表单元素才可以修改

##### 2.8.2.2 下拉框控件

- 父元素`<select>`和子元素`<option>`配合使用才是一个完整的下拉框控件
- 其中父元素`<select>`==必须设置==属性`name`, 子元素`<option>`==必须设置==属性`value`

###### 2.8.2.2.1 下拉框控件父元素`<select>`

- 用来声明这里有一个下拉框, 表单提交时被选择的选项的属性value的值会与该元素的属性name的值绑定提交
- 必须设置属性`name`, 之可以是任意文本

###### 2.8.2.2.2 下拉框控件子元素`<option>`

- 下拉框的一个选项, 如果该下拉框有多个选项, 那就添加多个`<option>`元素
- 必须设置属性`value`, 以区分不同的选项, 值可以是任意文本

##### 2.8.2.3 文本域控件`<textarea>`

- 用来生成一个可输入多行文字的多行文本框
- 存储的内容会输入到文本框中, 会成为输入文字的一部分

###### 2.8.2.3.1 属性`name`

- 用于与其他控件做出区分, **必须设置**, 值可以是任意文本

###### 2.8.2.3.2 属性`cols`

- 用于设置多行文本框的列数, 值是个数字

###### 2.8.2.3.3 属性`row`

- 用于设置多行文本框的行数, 值是个数字

###### 2.8.2.3.4 属性`placeholder`

- 用于设置多行文本框的提示, 当向输入框中输入文字时, 该提示会自动消失, placeholder的意思是占位符
- 值可以是任意文本

###### 2.8.2.3.5 属性`maxlength`

- 该多行文本框中最多能输入多少个文字
- 值是个数字

##### 2.8.2.4 按钮控件`<button>`

- ==表单中的按钮控件只具有提交表单的功能,== 跟**输入控件中的submit按钮**作用重复, 作用很容易跟表单外的按钮作用混淆而产生误解, 所以==在表单中最好不要使用按钮元素`<button>`==
- 存储的内容会显示在按钮上

###### 2.8.2.4.1 属性`name`

- 用于与其他控件做出区分, **必须设置**, 值可以是任意文本

##### 2.8.2.5 示例代码

```html
<body>
    <form action="https://www.zq.com" method="get">
      账号:
      <input type="text" placeholder="请输入账号" maxlength="12" /><br />
      密码:
      <input type="password" placeholder="请输入密码" maxlength="12" /><br />
      兴趣:
      <input type="checkbox" name="hobby" value="游戏" />游戏 &nbsp;
      <input type="checkbox" name="hobby" value="漫画" checked="checked" />漫画
      &nbsp; <input type="checkbox" name="hobby" value="动画" />动画 &nbsp;
      <input type="checkbox" name="hobby" value="CG" />CG &nbsp;<br />
      性别:
      <input type="radio" name="gender" value="男" />男 &nbsp;
      <input
        type="radio"
        name="gender"
        value="中性"
        checked="checked"
      />中性&nbsp; <input type="radio" name="gender" value="女" />女 &nbsp;<br />
      上传文件:
      <input type="file" /><br />
      <input type="reset" value="重置" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="submit" value="提交" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="button" value="按钮" /><br />
      海贼团:
      <select name="team">
        <option>请选择您的团队</option>
        <option value="草帽">草帽</option>
        <option value="九蛇">九蛇</option>
        <option value="海军">海军</option></select
      ><br />
      自我介绍:<br />
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <textarea
        placeholder="请输入您的自我介绍, 最多300字"
        maxlength="300"
        rows="6"
        cols="50"
      >
      </textarea
      ><br />
    </form>
  </body>
```



### 2.9 按钮元素`<button>`

- 表单外的按钮元素是个普通按钮, 可以配合javascript进行很多操作, 可扩展性很高

- 存储的内容会显示在按钮上

- ```html
    <body>
      <button>这是一个按钮</button>
    </body>
  ```



### 2.10 框架元素

- 框架元素由框架集元素`<frameset>`和框架元素`<frame>`组成
- 可以定制HTML页面布局, 用多个html页面瓶装成一个html页面
- 注意: 框架元素和`<body>`元素不可共存

#### 2.10.1 框架集元素`<frameset>`

- 一个html文件中存在元素`<body>`时, 就不能有框架集元素`<frmaeset>`, 存在框架集元素`<frameset>`时, 就不能有元素`<body>`
- `<frameset>`的子元素可以是个`<frameset>`也可以是个`<frame>`, 但只能在这两个里面选
- **注意:** 一个框架集元素`<frameset>`一般只会设置属性`rows`和属性`cols`中的一个, 而不会两个同时设置

##### 2.10.1.1 属性`rows`

- 可以按`rows`的值, 按照百分比把父元素分成指定的几行
- 值是'15%, * , 25%'形式, 意思是父元素所在的区域被划分成3行, 第一行占比15%, 第二行占剩下的, 第三行占25%; 例如'10%, 20%, 10%, *', 意思就是父元素所在的区域被划分成4行, 第一行占比10%, 第二行占比20%, 第三行占比!0%, 第四行占剩下的. 所以`*`表示的就是剩下的意思

##### 2.10.1.2  属性`cols`

- 可以按`cols`的值, 按照百分比把父元素分成指定的几列
- 值是'15%, * , 25%'形式, 意思是父元素所在的区域被划分成3列, 第一列占比15%, 第二列占剩下的, 第三列占25%; 例如'10%, 20%, 10%, *', 意思就是父元素所在的区域被划分成4列, 第一列占比10%, 第二列占比20%, 第三列占比!0%, 第四列占剩下的. 所以`*`表示的就是剩下的意思

#### 2.10.2 框架元素`<frame>`

- 在父元素中排行第几, 就会占据第几部分的地盘, 用来向框架集中引入html文件

##### 2.10.2.1 属性`src`

- 用来设置要放置在这一地盘下的html文件
- 值是个html的url或者本地路径

#### 2.10.3 示例代码

<img src="%E7%AC%AC01%E7%AB%A0%20HTML.assets/%E6%A1%86%E6%9E%B6%E6%A0%87%E7%AD%BE.png" style="zoom:75%;" />

##### 2.10.3.1 top.html

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <body>
    <p align="center">
      <font size="7"><b>头部数据显示区</b></font>
    </p>
  </body>
</html>

```

##### 2.10.3.2 left-content.html

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <body>
    <p align="center">
      <font size="7"><b>左栏数据显示区</b></font>
    </p>
  </body>
</html>

```

##### 2.10.3.3 right-content.html

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <body>
    <p align="center">
      <font size="7"><b>内容数据显示区</b></font>
    </p>
  </body>
</html>

```

##### 2.10.3.4 bottom.html

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <body align="center">
    <p align="center">
      <font size="7"><b>底部数据显示区</b></font>
    </p>
  </body>
</html>

```

##### 2.10.3.5 框架元素的使用示例

创建一个`frame示例`文件夹, 把`top.html`  `left-content.html`  `right-content.html`  `bottom.html`放到这个文件夹下

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>框架元素</title>
  </head>
  <frameset rows="20%, * , 15%">
    <frame src="frame示例/top.html"></frame>
    <frameset cols="10%, *">
      <frame src="frame示例/left-content.html"></frame>
      <frame src="frame示例/right-content.html"></frame>
    </frameset>
    <frame src="frame示例/bottom.html"></frame>
  </frameset>
</html>

```



## 三. 其他元素和特殊字符

### 3.1 元元素`<meta/>`

- `<meta>`元素只能是`<head>`元素的子元素

- ```html
  <head>
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Document</title>
  </head>
  ```

#### 3.1.1 属性`charset`

- 用来设置当前页面的字符编码, 值一般是`UTF-8`或者`GBK`, 最好使用`UTF-8`

#### 3.1.2 属性`name`和属性`content`

- `name='viewport'`和`content=``width=device-width, initial-scale=1.0`表示显示窗口的宽度等于设备的宽度, 网页上的文字和图形初始的放大比例是1.0, 也就是跟原来的大小相同

#### 3.1.3 属性`http-equiv`和属性`content`

##### 3.1.3.1 设值兼容ie到最高版本

```html
<meta http-equiv='X-UA-Compatibale' content='ie=edge'/>
```

##### 3.1.3.2 设置5秒后刷新本页面

```html
<meta http-equiv='refresh' content="5"/>
```

##### 3.1.3.3 设置5秒后跳转到另一页面(在本标签上跳转, 不会打开新标签)

```html
<meta http-equiv='refresh' content="5; url=https://www.baidu.com"/>
```



### 3.2 `<link>`元素

- 只能作为`<head>`元素的子元素
- 主要用来引入css文件

### 3.3 特殊字符

记住一个空格字符`&nbsp;`

## 四. HTML5

### 4.1 HTML5与HTML4的关系

- h5是h4的升级版本, h5可以兼容h4的代码
- h5与h4的区别
  - h5开始彻底地大小写不敏感, 也就是说彻底地忽略了元素、属性、属性值中大小写的不同
  - h5开始可以不写包裹属性值的单引号或双引号
  - h5开始可以不写结束元素(其实是html编译器自动帮忙补全了

### 4.2 新增语义化元素

- html4中, 所有的容器元素95%都会使用`<div>`容器, `<div>`容器使用过多的话,很难区分彼此
- h5开始新增许多语义化元素, 这些元素的功能与`<div>`的功能一模一样, 用法也一模一样, 没有特殊的功能, 只是为了让开发者专名专用, 更好地区分容器罢了
  - `<header>`元素    专门用来作为网页头部的容器, 包括页眉部分
  - `<nav>`元素    专门用来作为网页导航区域的容器
  - `<article>`元素    表示文章的容器
  - `<hgroup>`元素    用来存放内容与标题的容器
  - `<section>`元素    表示网页中的内容区域的容器
  - `<aside>`元素    用来存放文章的目录的容器, 也可以存放一些跳转到上一页、下一页等按钮
  - `<figure>`  用来引用图片、表格或者代码块的容器
    
    - `<figcaption>`    对这个图片进行介绍的文字，是`<figure>`元素的专属子元素，用来描述图片的文字内容
    
    - `<figure>`的width值要跟引用的图片\表格\代码块的width值稍大或者相同
    
    - ```html
          <figure style="max-width: 300px">
            <figcaption style="text-align: center">身材好的美女</figcaption>
      
            <img src="image/nami (3).jpg" style="max-width: 300px" />
          </figure>
      ```
  - `<foot>`    表示王爷的底部区域的容器, 又称页脚
- 语义化元素建议使用出来的效果图
  
  - ![](%E7%AC%AC01%E7%AB%A0%20HTML.assets/html5%E6%96%B0%E5%A2%9E%E8%AF%AD%E4%B9%89%E6%A0%87%E7%AD%BE-1616335718619.jpg)

### 4.2 多媒体元素

#### 4.2.1 视频元素`<video>`

- 用来在网页上播放视频, 还可以播放音频, 注意只能引入相对路径下的视频文件
- 支持三种格式的视频: `mp4`  `ogg`  `webm`

##### 4.2.1.1 属性`src`

- 设置视频源

##### 4.2.1.2 属性`controls`

- 用于显示视频的控制面板, 即播放/暂停  进度条  音量  全屏的控制按钮
- 值只有1个'controls', 默认不显示, 只有设置了这个值才能开启控制面板

##### 4.2.1.3 属性`autoplay`

- 用于自动播放(谷歌浏览器会失败, 360浏览器可以成功自动播放)
- 值只有1个'autoplay', 默认不开启, 只有设置了这个值才会开启

##### 4.2.1.4 属性`loop`

- 用于开启循环播放
- 值只有1个`loop`, 默认不开启, 只有设置了这个值才会开启

### 4.3 新增[表单控件](#2.8.2 表单控件)

#### 4.3.1 新增的输入控件`<input/>`

- 其实就是新增了几个属性`type`的值

##### 4.3.1.1 属性`type`

- 'email'    邮箱输入框提交时会检测输入的文本是否包含`@`符号
- 'color'    调色板
- 'date'    日历
- 'time'    时间
- 'moth'    月份
- 'week'    一年中的第几周
- 'range'    滑块
- 'search'     搜索框(带❌号, 可一键删除框中输入的内容)
- 'url'    url输入框
- 'tel'    电话输入框
- number    数值域, 需配合三个属性使用
  - 属性`min`    最小值(默认值是1), 值是个数字
  - 属性`max`    最大值(默认无上限), 值是个数字
  - 属性`step`     递增量(默认值是1), 值是个数字

#### 4.3.2 进度条控件`<progress>`

#### 4.3.3 新增联想下拉框控件`<datalist>`

- 与`<select>`下拉框控件相比多了个模糊查询的功能

- `<datalist>`控件必须设置属性`id`, 需要配合`<input/>`输入框控件才能使用, `<input/>`必须设置属性`list`, `<input/>`属性`list`的值必须跟`<datalist>`属性`id`的值相同

- 需配合子元素`<option>`使用, `<option>`用来存放选项, `<option>`元素必须设置属性`value`, 表单提交时提交的就是被选择的`<option>`元素的属性value的值

- ```html
  <input list='cities' placeholder='请选择城市'/>
  <datalist id='cities'>
  	<option value='北京'>北京</option>
      <option value='上海'>上海</option>
      <option value='深圳' selected='selected'>深圳</option>
      <option value='广州'>广州</option>
  </datalist>
  ```


#### 4.3.4 示例代码

```html
<form action="https://www.zq.com" method="post">
      <input type="color" /><br />
      <input type="date" /><br />
      <input type="time" /><br />
      <input type="month" /><br />
      <input type="week" /><br />
      <input type="range" /><br />
      <input type="search" placeholder="请输入关键字" /><br />
      <progress></progress><br />
      <input list="teamList" placeholder="请选择您的团队或输入关键字" />
      <datalist id="teamList">
        <option value="草帽">草帽</option>
        <option value="九蛇">九蛇</option>
        <option value="小丑">小丑</option>
        <option value="太阳">太阳</option>
        <option value="白胡子">白胡子</option>
        <option value="海军">海军</option>
      </datalist>
    </form>
```



### 4.4 新增[字体格式化元素](#2.3.2 字体格式化元素)

#### 4.4.1 高亮元素`<mark>`

### 4.5 框架元素`<iframe>`

- 从html5开始元素`<frameset>`和`<frame>`弃用了, 由`<iframe>`元素代替
- 元素`<iframe>`需要在`<body>`元素中使用
- 元素`<iframe>`是个行内块元素
- 元素`<iframe>`可以配合`div`来实现竖直方向的结构, 可以配合`<span>`来实现横向方向的结构

#### 4.5.1 属性`frameborder`

`frameborder="no"`可以去除框架之间的边框

### 4.6 定义列表元素`<dl>`

- `<dl>` 元素定义一个定义列表（definition list）。

- `<dl>` 元素用于结合 `<dt>` （定义列表中的项目）和 <dd> （描述列表中的项目）。

- **`<dl>`元素的各项之前是没有序号的**, 这是与`<ul>`和`<or>`的不同之处

- ```html
  <dl>
        <dt>守望先锋</dt>
            <dd>Mercy</dd>
            <dd>WidowMaker</dd>
            <dd>D·Va</dd>
        <dt>死或生</dt>
            <dd>穗乃果</dd>
            <dd>艾莲娜</dd>
            <dd>红叶</dd>
        	  <dd>不知火舞</dd>
        <dt>最终幻想7</dt>
            <dd>蒂法</dd>
            <dd>艾丽丝</dd>
            <dd>杰西</dd>
      </dl>
  ```



## 五. html引入文件时路径的书写规范

### 5.1 绝对路径

- 相对路径是`file:///相对路径`的格式
- 绝对路径一般不使用, 因为一些元素不支持绝对路径

### 5.2 相对路径

| 相对路径              | 说明                                              |
| --------------------- | ------------------------------------------------- |
| **imgae/nami.jpg**    | image文件夹跟html文件处于相同文件夹下             |
| **/image/nami.jpg**   | image文件夹处于根目录下                           |
| **../image/nami.jpg** | image文件夹处于html文件所在文件夹的上一级文件夹下 |



[^color]: 可以到网站<https://htmlcolorcodes.com/zh/yanse-biao/>或者<上http://khroma.co/generator/>查找想用的颜色