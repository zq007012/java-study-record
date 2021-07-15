# Git与Tortoise的简单使用

## 一. 下载并安装git

下载地址:<https://git-scm.com/downloads>

安装时, 直接点下一步进行安装

## 二. 下载并安装TortoiseGit

下载地址:<https://tortoisegit.org/download/>

在` Language Packs`区域下载汉化包

安装好`TortoiseGit`软件后, 在安装汉化包, 打开`TortoisGit`的设置, 在**常规设置**里进行如下设置

![](Git与Tortoise的简单使用.assets/One_2021-07-05_222033.png)

## 三. 在Gitee创建仓库

创建仓库时进行如下配置

![](Git与Tortoise的简单使用.assets/One_2021-07-05_222931.png)

## 四. 克隆远程仓库到本地

在本地的任意目录下点击右键, 点击`Git克隆...`

![](Git与Tortoise的简单使用.assets/屏幕截图 2021-07-05 224122.png)

对跳出来的面板进行如下配置, 点击确定, 远程仓库的就会克隆到本地

![](Git与Tortoise的简单使用.assets/One_2021-07-05_224252.png)



## 五. 仓库的提交与推送

**只有克隆到本地的仓库才能配置提交与推送**

在本地仓库的文件夹上点击右键, 点击`TortoiseGit(T)`→`设置(S)`

![](Git与Tortoise的简单使用.assets/屏幕截图 2021-07-05 224956.png)

点击`Git`, 再点击`编辑本地.git/config(L)`

![](Git与Tortoise的简单使用.assets/One_2021-07-05_225412.png)

将`url = https://gitee.com/zq007012/java-study-record`**改成**`url = https://gitee用户名:登录密码@gitee.com/zq007012/java-study-record`, 注意用户名和密码中不能有`@`这个符号

![](Git与Tortoise的简单使用.assets/One_2021-07-05_230216.png)

之后就可以对本地仓库进行提交和推送了