<%@ page import="java.util.Date" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>实现格式标签的使用</title>
</head>
<body>
<%
    // 获取当前系统时间
    Date date = new Date();
    pageContext.setAttribute("date", date);
%>

当前系统时间为：${date}
<hr/>
<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
</body>
</html>
