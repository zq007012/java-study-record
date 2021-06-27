<%@ page import="com.zq.Person" %><%--
  Created by IntelliJ IDEA.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>实现session中对象的绑定和解除</title>
</head>
<body>
<%
    // 准备一个Person类型的对象
    Person nami = new Person();
    nami.setName("娜美");
    nami.setAge(20);
    Person robin = new Person();
    robin.setName("罗宾");
    robin.setAge(30);
    // 将对象nami与session对象进行绑定
    session.setAttribute("person", nami);

    // 将对象robin与session绑定, 并将对象nami与seesion解绑
    session.setAttribute("person", robin);

    // 将对象robin与session解绑
    session.removeAttribute("person");
%>
</body>
</html>
