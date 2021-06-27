<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2021/6/10
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="i" begin="1" end="8" step="2" varStatus="status">
    <p>
        ----------这是第${status.count}次循环----------<br/>
        i = ${i}<br/>
        status.index = ${status.index}<br/>
        status.count = ${status.count}<br/>
        status.last = ${status.last}<br/>
        status.first = ${status.first}<br/>
    </p>
</c:forEach>
</body>
</html>
