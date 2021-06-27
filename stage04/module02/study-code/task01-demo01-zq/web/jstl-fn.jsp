<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2021/5/24
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
    <c:set var="name" value="Mercy"/>
    ${fn:toUpperCase(name)}
</body>
</html>
