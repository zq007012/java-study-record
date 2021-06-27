<%--
  Created by IntelliJ IDEA.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>实现会话中属性状态的改变</title>
</head>
<body>
<%
    // 增加属性
    session.setAttribute("name", "caocao");
    // 修改属性
    session.setAttribute("name", "caoren");
    // 删除属性
    session.removeAttribute("name");
%>
</body>
</html>
