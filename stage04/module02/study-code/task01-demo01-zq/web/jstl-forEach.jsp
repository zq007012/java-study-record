<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2021/5/24
  Time: 9:15
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
    ArrayList<String> nameList = new ArrayList<>();
    nameList.add("娜美");
    nameList.add("罗宾");
    nameList.add("白星");
    pageContext.setAttribute("nameList", nameList);
%>
<c:forEach var="name" items="${nameList}" step="2" begin="0"
           end="${nameList.size() - 1}">
    ${name}<br/>
</c:forEach>

</body>
</html>
