# 第06章 Bootstrap

[toc]

## 一. Bootstrap简介

### 1.1 什么时Bootstrap

- Bootstrap来自Twitter, 是目前最受欢迎的响应式前端框架
- Bootstrap是基于HTML、CSS、JavaScript的， 他简介灵活， 使得Web开发更加快捷。

### 1.2 为什么使用Bootstrap

- 移动设备优先：自Bootstrap3起， 框架包含了贯穿于整个库的移动设备优先的样式
  - 不是简单的增加一些可选的针对移动设备的样式， 而是直接融合进了框架的内核中.
  - 也就是说, 针对移动设备的样式融合进了框架的每个角落, 而不是增加一个额外的文件.
- 浏览器支持: 所有的主流浏览器都支持Bootstrap
- 容易上手: 只要具备HTML和CSS的基础知识, 就可以开始学习Bootstrap
- ==响应式设计==: Bootstrap的响应式CSS能够自适应于台式机、笔记本、平板电脑和手机
- 它为开发人员创建接口提供了一个简洁统一的解决方案
- 它包含了功能强大的内置组件， 易于定制
- 它还提供了基于Web的定制
- 它是开源的

### 1.3 下载与使用

- 英文官网：<https://getbootstrap.com>

- 中文官网: <https://www.bootcss.com/>
- 下载好后, 解压压缩包, 就得到bootstrap的资源文件了.
  - 分别是css文件夹、js文件夹、font文件夹
- 由于bootstrape是依赖jQeury的， 所以还要往js文件夹里放一个jQuery.js文件

## 二. Bootstrap的使用

- 在使用`Bootstrap`前需要将`bootstrap.min.css`作为外部样式表引入到html文件中, 在`<head>`标签下新增一下标签

  - ```html
    <link href="bootstrap.min.css的路径" rel="stylesheet"/>
    ```



### 2.1 表格

#### 2.1.1 Bootstrap提供的标签

- `<table>`
  - `<caption>` 表格的题目
  - `<thead>`    表格**标题行**的容器元素
    - `<th>`    表格标题行的专用单元格元素

  - `<tbody>`    表格所有内容行的容器元素
    - `<tr>`    表格行元素
      - `<td>`    单元格元素

#### 2.1.2 `<table>`常用类样式

- 标签`<table>`常用的类样式
  - `.table`    为表格添加基础样式(行与行有分割线, 自适应浏览器宽度)
  - `.table-striped`    隔行变色
  - `.table-borderd`    为所有单元格添加边框
  - `table-hover`    给`<tbody>`内所有行添加鼠标悬停事件, 鼠标悬停行高亮显示
  - `table-condensed`    让表格更加紧凑

#### 2.1.3 `<th>`   `<tr>`  `<td>`的情景色样式

- 这些样式提供了某些情境下元素该有的的颜色, 一般会配合jQuery中的类样式函数`addClass()`  `removeClass()`  `toggleClass()`来使用
- 这些样式用在<th>`   `<tr>`  `<td>`这几个元素上
  - `.active`     这种颜色用来表示元素被激活了(即鼠标悬停到了该元素上)
  - `.success`    这种颜色用来表示该元素里的内容被成功完成了
  - `.info`    这种颜色用来表示该元素里的内容是个提示信息
  - `.warning`    这种颜色用来表示该元素里的内容是个警告信息
  - `.danger`    这种颜色用来表示该元素里的内容是个提示极度危险的信息

#### 2.1.4 响应式表格样式

- `.table-responsive`    当浏览器的宽度小于768px时, 表格下面会出现横向滚动条
- **这个样式不能使用在`<table>`元素上**
- ==使用方法:==使用一个`<div>`元素作为父容器将表格包裹起来, 将这个样式赋值给这个`<div>`容器, 这样这个表格就是响应式表格了

#### 2.1.5 示例代码

```html
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link href="Libraries/css/bootstrap.min.css" rel="stylesheet" />
  </head>
  <style></style>
  <body>
    <div class="table-responsive">
      <table class="table table-bordered table-striped table-hover">
        <caption
          style="
            font-size: 2em;
            text-align: center;
            font-weight: bolder;
            color: black;
          "
        >
          各个动画中的美女
        </caption>
        <thead>
          <th>守望先锋</th>
          <th>最终幻想7</th>
          <th>死或生</th>
          <th>火影忍者</th>
          <th>死神</th>
          <th>海贼王</th>
        </thead>
        <tbody>
          <tr>
            <td class="active">Mercy</td>
            <td class="success">蒂法</td>
            <td class="info">罗丝玛丽</td>
            <td class="warning">千手纲手</td>
            <td class="danger">四枫院夜一</td>
            <td>白星</td>
          </tr>
          <tr>
            <td>WidowMaker</td>
            <td>艾丽丝</td>
            <td>米拉</td>
            <td>日向雏田</td>
            <td>碎蜂</td>
            <td>汉库克</td>
          </tr>
          <tr>
            <td>小美</td>
            <td>杰西</td>
            <td>海莲娜</td>
            <td>手鞠</td>
            <td>更木八千流</td>
            <td>罗宾</td>
          </tr>
          <tr>
            <td>法老之鹰</td>
            <td>玛姆</td>
            <td>穗乃果</td>
            <td>天天</td>
            <td>松本乱菊</td>
            <td>艾米</td>
          </tr>
          <tr>
            <td>猎空</td>
            <td>碎蜂</td>
            <td>霞</td>
            <td>夕日红</td>
            <td>虎彻勇音</td>
            <td>小紫</td>
          </tr>
          <tr>
            <td>D·Va</td>
            <td>涅音梦</td>
            <td>红叶</td>
            <td>照美冥</td>
            <td>赫利贝尔</td>
            <td>黑玛丽亚</td>
          </tr>
        </tbody>
      </table>
    </div>
  </body>
```

### 2.2 `<label>`元素

#### 2.2.1 第一种用法

- 该元素下放一些文本内容和一个按钮元素或者一个表单控件元素

- **当悬浮到或点击该元素下的文本内容时, 按钮元素或表单控件元素就会发生对应的悬浮事件或点击事件**

- ```html
  <label>
        WidowMaker<br />
        <button>WidowMaker</button><br />
        艾米莉 </label
      >
  ```

#### 2.2.2 第二种用法

- 该元素的属性`for`的值与一个按钮元素或者表单元素的属性`id`值相同

- **当悬浮到或点击该元素下的文本内容时, 相应的按钮元素或表单控件元素就会发生相同的悬浮事件或点击事件**

- ```html
  <label for="demo">Demo图片</label>
      <p>这是一条分割线</p>
      <button id="demo">Demo按钮</button>
  ```



### 2.3 表单

#### 2.3.1 表单控件元素的样式

- 所有设置了 `.form-control` 类的 `<input>`、`<textarea>` 和 `<select>` 元素都将被默认设置宽度属性为 `width: 100%;`。 将 `label` 元素和前面提到的控件包裹在 `.form-group` 中可以获得最好的排列

```html
   <form>
      <div class="form-group">
        <label for="account">账号:</label>
        <input id="account" type="text" class="form-control" />
      </div>
      <div class="form-group">
        <label for="nickname">昵称:</label>
        <input id="nickname" type="text" class="form-control" />
      </div>
    </form>
```

#### 2.3.2 表单的布局

##### 2.3.2.1 表单默认布局

- `<form>`元素不设置任何类样式
- 表单下的所有元素竖直方向一行一行地排列

```html
   <form>
      <div class="form-group">
        <label for="account">账号:</label>
        <input id="account" type="text" class="form-control" />
      </div>
      <div class="form-group">
        <label for="nickname">昵称:</label>
        <input id="nickname" type="text" class="form-control" />
      </div>
    </form>
```

##### 2.3.2.2 表单内联布局

- `<form>`元素的类样式设置为`form-inline`
- **当屏幕宽度大于768px时, 表单下的所有元素排列在同一行, 当屏幕的宽度768px时, 表单下的所有元素会自动恢复成默认布局**

```html
    <form class="form-inline">
      <div class="form-group">
        <label for="account">账号:</label>
        <input id="account" type="text" class="form-control" />
      </div>
      <div class="form-group">
        <label for="nickname">昵称:</label>
        <input id="nickname" type="text" class="form-control" />
      </div>
    </form>
```

#### 2.3.3 表单控件元素

##### 2.3.3.1 输入框

```html
<form>
      <div class="form-group">
        <label for="textDemo"> 输入框 </label>
        <input id="textDemo" type="text" class="form-control" />
      </div>
    </form>
```

##### 2.3.3.2 文本框

- 可手动调节宽高

```html
    <form>
      <div class="form-group">
        <label for="textAreaDemo"> 文本框 </label>
        <textarea id="textAreaDemo" class="form-control"></textarea>
      </div>
    </form>
```

##### 2.3.3.3 复选框

###### 2.3.3.3.1 默认复选框样式

- 一个复选框以及该复选框的文本说明应该放入到一个`<label>`元素中
- 然后这个`<label>`元素应该放入到一个`<div>`中,  `<div>`的样式可以是`checbox`默认布局, 这样复选框间会谁知方向排列或者`checkbox-inline`内联布局, 这样复选框间水平方向排列
- 然后多个这样的`<div>`排列在一起就形成了一个复选框组

```html
<style>
    dd {
      margin-left: 0px;
      text-indent: 1.5em;
    }
  </style>
  <body style="padding: 20px">
    <form>
      <dl>
        <dt>最终幻想7</dt>
        <dd>
          <div class="checkbox">
            <label> <input type="checkbox" />艾丽丝 </label>
          </div>
          <div class="checkbox">
            <label> <input type="checkbox" />蒂法 </label>
          </div>
          <div class="checkbox">
            <label> <input type="checkbox" />杰西 </label>
          </div>
        </dd>
      </dl>
      <hr />
      <b>死或生:&nbsp;&nbsp;</b>
      <div class="checkbox-inline">
        <label> <input type="checkbox" />穗乃果 </label>
      </div>
      <div class="checkbox-inline">
        <label> <input type="checkbox" />红叶 </label>
      </div>
      <div class="checkbox-inline">
        <label> <input type="checkbox" />不知火舞 </label>
      </div>
    </form>
  </body>
```

###### 

###### 2.3.3.3.2 复选框样式--复选按钮

- 需要引入程序库`bootstrap.min.js`
- 一个复选框以及该复选框的文本说明应该放入到一个`<label>`元素中, `<label>`的样式是`btn btn-success`
- 多个这样的`<label>`放到`<div>`元素中, `<div>`的样式是`btn-group`, 还需设置一个属性`data-toggle`, 属性的值为`buttons`, 这样就会去除掉复选框的那个框, 变成一个纯粹的按钮的了

```html
<body style="padding: 20px">
    <form>
      <b>死或生:&nbsp;&nbsp;</b>
      <div class="btn-group" data-toggle="buttons">
        <label class="btn btn-success"> <input type="checkbox" />穗乃果 </label>
        <label class="btn btn-success"> <input type="checkbox" />红叶 </label>
        <label class="btn btn-success">
          <input type="checkbox" />不知火舞
        </label>
        <label class="btn btn-success"> <input type="checkbox" />米拉 </label>
        <label class="btn btn-success"> <input type="checkbox" />艾莲娜 </label>
      </div>
    </form>
</body>
```

##### 2.3.3.4 单选框

###### 2.3.3.4.1 默认单选框样式

- 一个单选框以及该单选框的文本说明应该放入到一个`<label>`元素中
- 然后这个`<label>`元素应该放入到一个`<div>`中,  `<div>`的样式可以是`radio`默认布局, 或者`radio-inline`内联布局
- 然后多个这样的`<div>`排列在一起就形成了一个复选框组

```html
  <style>
    dd {
      margin-left: 0px;
      text-indent: 1.5em;
    }
  </style>
  <body style="padding: 20px">
    <form>
      <dl>
        <dt>最终幻想7</dt>
        <dd>
          <div class="radio">
            <label> <input name="ff7" type="radio" />艾丽丝 </label>
          </div>
          <div class="radio">
            <label> <input name="ff7" type="radio" />蒂法 </label>
          </div>
          <div class="radio">
            <label> <input name="ff7" type="radio" />杰西 </label>
          </div>
        </dd>
      </dl>
      <hr />
      <b>死或生:&nbsp;&nbsp;</b>
      <div class="radio-inline">
        <label> <input name="doa" type="radio" />穗乃果 </label>
      </div>
      <div class="radio-inline">
        <label> <input name="doa" type="radio" />红叶 </label>
      </div>
      <div class="radio-inline">
        <label> <input name="doa" type="radio" />不知火舞 </label>
      </div>
    </form>
  </body>
```

###### 

###### 2.3.3.4.2 复选框样式--复选按钮

- 需要引入程序库`bootstrap.min.js`
- 一个单选框以及该单选框的文本说明应该放入到一个`<label>`元素中, `<label>`的样式是`btn btn-success`
- 多个这样的`<label>`放到`<div>`元素中, `<div>`的样式是`btn-group`, 还需设置一个属性`data-toggle`, 属性的值为`buttons`, 这样就会去除掉单选框的那个框, 变成一个纯粹的按钮的了

```html
<form>
      <b>死或生:&nbsp;&nbsp;</b>
      <div class="btn-group" data-toggle="buttons">
        <label class="btn btn-success"> 
          <input name="doa" type="radio" />
          穗乃果 
        </label>
        <label class="btn btn-success"> 
          <input name="doa" type="radio" />
          红叶 
        </label>
        <label class="btn btn-success">
          <input name="doa" type="radio" />
          不知火舞
        </label>
        <label class="btn btn-success"> 
          <input name="doa" type="radio" />
          米拉 
        </label>
        <label class="btn btn-success"> 
          <input name="doa" type="radio" />
          艾莲娜 
        </label>
      </div>
    </form>
```

#### 2.3.4 补充

##### 2.3.4.1 禁用属性`disabled`

- 只写属性即可,不必写属性值
- 为输入框设置 `disabled` 属性可以禁止其与用户有任何交互（焦点、输入等）。被禁用的输入框颜色更浅，并且还添加了 `not-allowed` 鼠标状态。

```html
<form>
      <div class="form-group">
        <label for="account1">
          用户名1: 
        </label>
        <input id="account1" type="text" value="不禁用" class="form-control"/>
      </div>
      <div class="form-group">
        
        <label for="account2">
          用户名2: 
        </label>
        <input id="account2" type="text" value="禁用" class="form-control" disabled/>
      </div>
      
    </form>
```

##### 2.3.4.2 静态控件

- 如果需要在表单中将一行纯文本和 `label` 元素放置于同一行，为 `<p>` 元素添加 `.form-control-static` 类即可。

```html
<form class="form-inline">
      <div class="form-group">
        <label>
          用户名1: 
        </label>
        <p class="form-control-static">波雅·汉库克</p>
      </div>
    </form>
```

##### 2.3.4.3 隐藏label的类`sr-only`

为`<label>`元素设置`.sr-only`类可以隐藏该`<label>`元素

##### 2.3.4.4 图标类`input-group-addon`

为`<span>`元素设置类`.input-group-addon`可以将该`<span>`元素内的内容一图标的样式显示出来

### 2.4 按钮元素

#### 2.4.1 按钮的颜色: 7种

```html
<div>
      <button>普通按钮</button>
      <button class="btn btn-default">默认按钮</button>
      <button class="btn btn-primary">主要按钮</button>
      <button class="btn btn-success">成功按钮</button>
      <button class="btn btn-info">信息按钮</button>
      <button class="btn btn-warning">警告按钮</button>
      <button class="btn btn-danger">危险按钮</button>
      <button class="btn btn-link">链接按钮</button>
    </div>
```

#### 2.4.2 按钮的大小: 4种

```html
<div>
      <button class="btn btn-lg btn-primary">台式机屏幕</button>
      <button class="btn btn-primary">笔记本屏幕</button>
      <button class="btn btn-sm btn-primary">平板屏幕</button>
      <button class="btn btn-xs btn-primary">手机屏幕</button>
    </div>
```

### 2.5 图片样式

#### 2.5.1 图片形状

- `.img-rounde` : 圆角图片样式, 其实就是css属性`border-radius:6px`
- `.img-circle`: 圆形图片样式, 其实就是css属性`border-radius:50%`
- `.img-thumbnail`: 缩略图片样式, 其实就是设置了一些`padding`和一个灰色的边框

```html
<style>
    figure {
      padding: 10px;
      width: 300px;
    }
    img {
      width: 300px;
    }
    figcaption {
      text-align: center;
    }
  </style>
  <body>
    <figure>
      <figcaption>圆角图片</figcaption>

      <img class="img-rounded" src="image/nami (3).jpg" />
    </figure>
    <figure>
      <figcaption>圆形图片</figcaption>

      <img class="img-circle" src="image/nami (4).jpg" />
    </figure>
    <figure>
      <figcaption>缩略图</figcaption>

      <img class="img-thumbnail" src="image/nami (5).jpg" />
    </figure>
  </body>
```

#### 2.5.2 响应式图片

- 在 Bootstrap 版本 3 中，通过为图片添加 `.img-responsive` 类可以让图片支持响应式布局。其实质是为图片设置了 `max-width: 100%;`、 `height: auto;` 和 `display: block;` 属性，从而让图片在其父元素中更好的缩放。
- 如果需要让使用了 `.img-responsive` 类的图片水平居中，请使用 `.center-block` 类，不要用 `.text-center`

```html
<img src="image/nami (13).jpg" class="img-responsive" />
```

### 2.6 下拉菜单组件

- 外围容器`<div>`, `class="dropdown"`
  - 菜单按钮`<button>`的`class="dropdown"`
  - 菜单条目`<ul>`的`class="dropdown-menu"`

```html
   <div class="dropdown">
      <button class="btn btn-primary" data-toggle="dropdown">
        火影忍者<span class="caret"></span>
      </button>
      <ul class="dropdown-menu">
        <li><a href="#">千手纲手</a></li>
        <li><a href="#">日向雏田</a></li>
        <li><a href="#">照美冥</a></li>
      </ul>
    </div>
```

### 2.7 分页组件

```html
  <div>
      <ul class="pagination">
        <li class="previous"><a href="#">&laquo;</a></li>
        <li class="active"><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
        <li class="next"><a href="#">&raquo;</a></li>
      </ul>
    </div>
    <hr />
    <div>
      <ul class="pager">
        <li class="previous"><a href="#">上一页</a></li>
        <li><a href="#">A</a></li>
        <li><a href="#">B</a></li>
        <li><a href="#">C</a></li>
        <li><a href="#">D</a></li>
        <li><a href="#">E</a></li>
        <li class="next"><a href="#">下一页</a></li>
      </ul>
    </div>
```

### 2.8 栅格系统(grid system)

- 栅格参数
  - `.col-lg-`    lg ≥ 1200px
  - `.col-md-`    992px ≤ md ＜ 1200px
  - `.col-sm-`     768px ≤ sm < 992px
  - `.col-xs-`    sm ＜ 768px
- 需要一个栅格容器`<div>`, 这个栅格容器必须设置属性`class`, class的值有两个可选
  - `.container`    可以设置栅格容器的宽度并支持响应式布局的容器, 此时栅格容器在父元素的居中位置
  - `.container-fluid`    不能设置栅格容器的宽度, 栅格容器的宽度是父元素的宽度.
- 栅格容器下有一个或多个栅格行容器`<div>`, 栅格行容器必须设置属性`class="row"`
- 栅格行容器下有一个或多个栅格单元格容器`<div>`, 
  - 栅格的每一行被等分成12列, 可以通过对栅格单元格设置一个单元格占几列来设置单元格的宽度, 可以通过设置栅格参数类来实现
  - 当多个相邻的栅格单元格的宽度超过12列时, 超出的栅格单元格就会在栅格行容器里重启一行排列
  - 栅格参数相关的类
    - `.col-lg-X`    当视口lg ≥ 1200px时, 栅格单元格的宽度占x列
    - `.col-md-X`    当视口l992px ≤ md ＜ 1200px时, 栅格单元格的宽度占x列
    - `.col-sm-X`     当视口l768px ≤ sm < 992px时, 栅格单元格的宽度占x列
    - `.col-xs-X`    当视口lsm ＜ 768px时, 栅格单元格的宽度占x列

```html
   <div class="container" style="width=1200px;background-color: azure;">
      <div class="row">
        <div class="col-lg-1 col-md-2 col-sm-3 col-xs-4">汉库克</div>
        <div class="col-lg-1 col-md-2 col-sm-3 col-xs-4">汉库克</div>
        <div class="col-lg-1 col-md-2 col-sm-3 col-xs-4">汉库克</div>
        <div class="col-lg-1 col-md-2 col-sm-3 col-xs-4">汉库克</div>
        <div class="col-lg-1 col-md-2 col-sm-3 col-xs-4">汉库克</div>
        <div class="col-lg-1 col-md-2 col-sm-3 col-xs-4">汉库克</div>
        <div class="col-lg-1 col-md-2 col-sm-3 col-xs-4">汉库克</div>
        <div class="col-lg-1 col-md-2 col-sm-3 col-xs-4">汉库克</div>
        <div class="col-lg-1 col-md-2 col-sm-3 col-xs-4">汉库克</div>
        <div class="col-lg-1 col-md-2 col-sm-3 col-xs-4">汉库克</div>
        <div class="col-lg-1 col-md-2 col-sm-3 col-xs-4">汉库克</div>
        <div class="col-lg-1 col-md-2 col-sm-3 col-xs-4">汉库克</div>
      </div>
    </div>
```

### 2.9 缩略图组件

### 2.10 模态框组件

模态框容器`<div>`, 需要设置属性`class="modal"`, 模态框默认点击阴影部分, 模态框隐藏, 但是当设置属性`data-backdrop="static"`时, 点击阴影部分, 模态框不会隐藏, 

- 模态框会话容器`<div>`, 需要设置属性`class="modal-dialog"`
  - 模态框会话容器的内容容器`<div>`, 需要设置属性`class="modal-content"`
    - 模态框头部容器`<div>`, 需要设置属性`class="modal-header"`
      - 标题, 需要设置属性`class="modal-title"`
      - 取消按钮, 需要设置属性`class="close" data-dismisss="modal"`
    - 模态框身躯容器`<div>`,需要设置属性 `clas="modal-body"`
    - 模态框脚部容器`<div>`, 需要设置属性`class="modal-footer"`
      - 取消按钮, 需要设置属性`data-dismiss="modal"`, 这样点击取消按钮后模态框就会隐藏





