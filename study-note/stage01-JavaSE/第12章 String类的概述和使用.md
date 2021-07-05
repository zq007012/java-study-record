# 第12章 String类的概述和使用

[toc]

## 一. String类的概念(重点)

- `java.lang.String`类用于描述字符串, Java程序中所有的字符串字面值都可以使用该类的对象加以描述, 如:"abc"
- ==String类是由`final`关键字修饰的, 所以不能被继承==
- 从jdk1.9开始该类的底层不再使用`char[]`来存储数据,而是改成了`byte[]`加上编码标记, 从而节约了一些空间
- 该类描述的字符串内容是个常量不可更改

## 二. 常量池的概念(原理)

由于String类型描述的字符串内容是常量不可改变,因此java虚拟机将首次出现的字符串放入常量池中,若后续代码中出现了相同字符串内容则直接是泳池中已有的字符串而无需申请内存及创建对象,从而提高了性能.

```java
String str1 = "abc";
String str2 = new String("abc");
String str3 = "abc";
String str4 = new String(str2);
String str5 = new String(new byte[]{97, 98, 99});
System.out.println("str1 == str2 :" + (str1 == str2));//false
System.out.println("str1 == str3 :" + (str1 == str3));//true
System.out.println("str1 == str4 :" + (str1 == str4));//false
System.out.println("str2 == str4 :" + (str2 == str4));//false
System.out.println("str5 : " + str5);//abc
System.out.println("str2 == str5 :" + (str2 == str5));//false
```

- ==通过自动拆装箱第一次获得的String对象会保存在常量池, 当下一次以自动拆装箱获取同一字符串的String对象时,不会创建新的对象,而是获取到存在于常量池中的该字符串的对象==
- 以构造方法创建的String对象不会保存到常量池
- 以静态方法`static String valueOf(参数列表)`获取的String对象会保存到常量池中

## 三. 常用的构造方法

| 功能                                                        | 方法                                         |
| ----------------------------------------------------------- | -------------------------------------------- |
| 创建空字符字符串对象,即`""`                                 | String()                                     |
| 使用bytes数组中下标从offset位置开始的length个字节来创建对象 | String(byte[] bytes, int offset, int length) |
| 使用byte数组中的所有内容创建对象                            | String(byte[] bytes)                         |
| 使用value数组中下标从offset位置开始的length个字符来创建对象 | String(char[] value, int offset, int length) |
| 使用value数组中的所有字符创建对象                           | String(char[] value)                         |
| 根据参数指定的字符串内容来创建**新的对象**                  | String(String orginal)                       |

## 四. 常用的静态方法

| 功能                                                         | 方法                                         |
| ------------------------------------------------------------ | -------------------------------------------- |
| 根据输入的参数创建对应的String对象,创建的对象会保存进常量池,下一次使用该方法创建同意字符串的对象时,获得的是已经在常量池中的该对象.    获得的对象是调用`obj`的`toString()`方法形成的字符串 | static String  valueOf(Object obj)[^String1] |

[^String1]:这个方法还有八个重载方法, 其中六个的参数是除byte和short外的基本数据类型,剩下的两个是参数列表是`(char[] data)` 和 (char[] data, int offset, int count)

## 五. 常用的实例方法

| 功能                                                         | 方法                                                  |
| ------------------------------------------------------------ | ----------------------------------------------------- |
| 返回字符串本身, 返回的是其本身,没有创建新的对象              | String toString()                                     |
| 获取本字符串在常量池中的对象                                 | String intern()                                       |
|                                                              |                                                       |
| **功能**                                                     | **方法**                                              |
| 将当前字符串转换为byte数组                                   | byte[] getBytes()                                     |
| **功能**                                                     | **方法**                                              |
| 将当前字符串转换为char数组                                   | char[] getCharArray()                                 |
| 字符串的长度(即有几个字符)                                   | int length()                                          |
| 字符串第index位置上的字符,(从0开始数)                        | char charAt(int index)                                |
| 判断字符串是否为空字符串                                     | boolean isEmpty()                                     |
|                                                              |                                                       |
| **功能**                                                     | **方法**                                              |
| 比较调用对象和参数对象的大小关系(ASCII编码),返回值小于0则表示在前,大余0则表示在后 | int compareTo(String anotherString)                   |
| 不考虑大小写,比较调用对象和参数对象的大小关系, 返回值小于0则表示在前,大余0则表示在后 | int compareToIgnoreCase(String anotherString)         |
|                                                              |                                                       |
| **功能**                                                     | **方法**                                              |
| 两个字符串进行拼接, 获取拼接后形成的新的字符串               | String concat(String str)                             |
| **功能**                                                     | **方法**                                              |
| 字符串是否包含指定的内容                                     | boolean contains(CharSquence[^String2] s)             |
| **功能**                                                     | **方法**                                              |
| 返回字符串的小写形式,返回的是一个新的对象                    | String toLowerCase()                                  |
| 返回字符串的大写形式,返回的是一个新的对象                    | String toUpperCasd()                                  |
|                                                              |                                                       |
| **功能**                                                     | **方法**                                              |
| 判断字符串是否以参数字符串开头                               | boolean startsWith(String prefix)                     |
| 判断从字符串的指定位置开始是否以参数字符串开头               | boolean startsWith(String prefix, int offset)         |
| 判断字符串是否以参数字符串结尾                               | boolean endsWith(String suffix)                       |
| 获得本字符串去掉开头和结尾的空白字符形成的新字符串           | String trim()                                         |
|                                                              |                                                       |
| **功能**                                                     | **方法**                                              |
| 判断调用字符串与参数字符串的内容是否相同                     | boolean equals(Object obj)                            |
| 获取字符串的哈希值                                           | int hashCode()                                        |
| 判断忽略大小写后,调用字符串与参数字符串的内容是否相同        | boolean equalsIgnoreCase(String anotherString)        |
| 获取字符串的哈希值                                           | int hashCode()                                        |
|                                                              |                                                       |
| **功能**                                                     | **方法**                                              |
| 获取指定字符在字符串中第一次出现的位置,没有则返回`-1`        | int indexOf(int ch)[^String3]                         |
| 获取指定字符从fromIndex位置开始在字符串中第一次出现的位置,没有则返回`-1` | int indexOf(int ch, int fromIndex)[^String3]          |
| 从后往前找,指定字符第一次出现的位置,没有则返回`-1`           | int lastIndexOf(int ch)[^String3]                     |
| 以fromeIndex位置为最后,从后往前找,指定字符第一次出现的位置, 没有则返回`-1` | int lastIndexOf(int ch, int fromIndex)[^String3]      |
| 获取指定字符串在字符串中第一次出现的位置,没有则返回`-1`      | int indexOf(String str)                               |
| 获取指定字符串从fromIndex位置开始在字符串中第一次出现的位置,没有则返回`-1` | int indexOf(String str, int fromIndex)                |
| 从后往前找,指定字符串第一次出现的位置,没有则返回`-1`         | int lastIndexOf(String str)                           |
| 以fromeIndex位置为最后,从后往前找,指定字符串第一次出现的位置, 没有则返回`-1` | int lastIndexOf(String str, int fromIndex)            |
|                                                              |                                                       |
| **功能**                                                     | **方法**                                              |
| 获得本字符串从下标beginIdex开始到endIndex(不包括)结束组成的新字符串 | String subString(int beginIndex, int endIndex)        |
| 获取本字符串从下标beginIndex开始到结尾组成的新字符串         | String subString(int beginIndex)                      |
|                                                              |                                                       |
| **功能**                                                     | **方法**                                              |
| 获取使用newChar字符换掉本字符串中所有为oldChar的字符形成的字符串, **本对象不会发生改变** | String replace(char oldChar, char newChar)            |
| **功能**                                                     | **方法**                                              |
| 判断本字符串是否匹配参数指定的正则表达式规则                 | boolean matches(String regex)                         |
| 参数regex为正则表达式,以regex所表示的字符串为分隔符,将字符串拆分成字符串数组 | String[] split(String regex)                          |
| 用replacement替换掉本字符串中第一个符合regex这个正则表达式规则的字符串, 获取形成的新的字符串, **本对象不会发生改变** | String replaceFirst(String regex, String replacement) |
| 用replacement替换掉本字符串中所有符合regex这个正则表达式规则的字符串, 获取形成的新的字符串, **本对象不会发生改变** | String replaceAll(String regex, String replacement)   |

[^String2]: `String`		`StringBuffer`		`StringBuilder`都实现了这个接口,所以这个方法可以接收这些类型的对象
[^String3]: 一般输入的参数是个字符, 编译器会根据编码表自动将字符转换为对应的int数值

