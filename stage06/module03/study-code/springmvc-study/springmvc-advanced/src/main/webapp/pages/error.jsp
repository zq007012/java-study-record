<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2022/5/5
  Time: 7:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         language="java" %>
<html>
<head>
    <title>error</title>
</head>
<body>
<h1>状态码: ${pageContext.response.status}</h1>
<h1>${errorMsg}</h1>
<ul style="list-style-type:none">
    <c:forEach items="${errorStackTrace}" begin="0" var="stackTrace">
       <li>${stackTrace.toString()}</li>
    </c:forEach>
</ul>
</body>
</html>
