<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2021/5/23
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<%
    pageContext.setAttribute("name","黑百合");
%>--%>
<c:set var="name" value="黑百合" scope="page"/>
${name}

<jsp:useBean id="widowmaker" class="com.zq.servlet.Student" scope="page"/>

<c:set target="${widowmaker}" property="name" value="黑百合"/>
<c:set target="${widowmaker}" property="age" value="30"/>
<c:set target="${widowmaker}" property="gender" value="女"/>

<p>${widowmaker.name} , ${widowmaker.age} , ${widowmaker.gender}</p>

${empty widowmaker}<br/>
<c:remove var="widowmaker"/>
${empty widowmaker}<br/>

${empty name}<br/>
<c:remove var="name"/>
${empty name}<br/>
</body>
</html>
