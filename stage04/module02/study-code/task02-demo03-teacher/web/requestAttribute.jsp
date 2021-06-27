<%--
  Created by IntelliJ IDEA.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>实现请求中属性状态的改变</title>
</head>
<body>
<%
    // 实现属性的添加
    request.setAttribute("name", "zhangfei");
    // 实现属性的修改
    request.setAttribute("name", "guanyu");
    // 实现属性的删除
    request.removeAttribute("name");
%>
</body>
</html>
