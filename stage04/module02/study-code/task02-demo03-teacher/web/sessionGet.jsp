<%--
  Created by IntelliJ IDEA.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>实现session中数据的获取</title>
</head>
<body>
<%
    Object student = session.getAttribute("student");
    System.out.println("获取到的数据为：" + student);
%>
</body>
</html>
