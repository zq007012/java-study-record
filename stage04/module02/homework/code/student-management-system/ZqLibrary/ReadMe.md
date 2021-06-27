- mysql-connector --- mysql服务器驱动, [下载地址](https://dev.mysql.com/downloads/connector/j/), 选择`Platform Independent`
  - mysql-connector-java-8.0.23.jar
- c3p0 --- c3p0数据库连接池, [下载地址](https://www.mchange.com/projects/c3p0/)
  - c3p0-0.9.5.5.jar
  - c3p0-oracle-thin-extras-0.9.5.5.jar
  - mchange-commons-java-0.2.19.jar
- dbcp2 --- dbcp数据库连接池, 需要配合pool2和logging使用,  [下载地址](https://commons.apache.org/downloads/index.html), ==apache==
  - commons-dbcp2-2.8.0.jar
  - commons-logging-1.2.jar
  - commons-pool2-2.9.0.jar

- druid --- druid数据库连接池, [下载地址](https://github.com/alibaba/druid), ==alibaba==

  - druid-1.2.5.jar

- dbutils --- 增删改查工具类, 需要配合pool2, [下载地址](https://commons.apache.org/downloads/index.html), ==apache==

  - commons-dbutils-1.7.jar

- dom4j --- 用于XML、xParth和XLT的解析， 他应用于Java平台， 采用了Java集合框架并完全支持DOM、SAX和JAXP, [下载地址](https://github.com/dom4j/dom4j/releases/tag/version-2.1.3)

  - dom4j-2.1.3.jar

- jaxen --- 用于解析xml文档时支持xpath语言的使用, [下载地址](<https://mvnrepository.com/>)

  - jaxen-1.2.0.jar

- fastjson --- 用于将json字符串转化为对象, 和将对象转化为json字符串, [下载地址](https://github.com/alibaba/fastjson/wiki/Quick-Start-CN), ==alibaba==

  - fastjson-1.2.53.jar

    - ```java
      String text = JSON.toJSONString(obj); //序列化
      VO vo = JSON.parseObject("{...}", VO.class); //反序列化
      ```

- taglibs --- JSP标准标签库, [下载地址](https://tomcat.apache.org/download-taglibs.cgi), ==tomcat==

  - taglibs-standard-compat-1.2.5.jar
  - taglibs-standard-spec-1.2.5.jar
  - taglibs-standard-impl-1.2.5.jar
  - taglibs-standard-jstlel-1.2.5.jar

- 

