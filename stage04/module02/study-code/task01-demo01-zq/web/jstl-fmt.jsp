<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2021/5/24
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Date date = new Date();
    pageContext.setAttribute("date", date);
%>
<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss"/>
</body>
</html>
