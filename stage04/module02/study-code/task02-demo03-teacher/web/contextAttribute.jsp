<%--
  Created by IntelliJ IDEA.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>实现ServletContext对象属性的改变</title>
</head>
<body>
<%
    // 增加属性
    application.setAttribute("name", "sunquan");
    // 修改属性
    application.setAttribute("name", "zhouyu");
    // 删除属性
    application.removeAttribute("name");
%>
</body>
</html>
