<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2021/5/23
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         language="java" %>
<html>
<head>
    <title>out</title>
</head>
<body>
<c:out value="黑百合"></c:out>
<c:set scope="page" var="name" value="红叶"></c:set>
<c:out value="${pageScope.name}"></c:out>
</body>
</html>
