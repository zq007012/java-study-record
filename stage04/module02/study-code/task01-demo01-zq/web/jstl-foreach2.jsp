<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2021/6/10
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String[] nameArr = {"黑百合", "穗乃果", "汉库克", "贝优妮塔", "娜美","Mercy"};
    pageContext.setAttribute("nameArr",nameArr);
%>
<c:forEach var="name" items="${nameArr}" step="2" varStatus="status">
    <p>
        ----------这是第${status.count}次循环----------<br/>
        姓名: ${name}<br/>
        status.index = ${status.index}<br/>
        status.count = ${status.count}<br/>
        status.first = ${status.first}<br/>
        status.last = ${status.last}<br/>
    </p>
</c:forEach>
</body>
</html>
