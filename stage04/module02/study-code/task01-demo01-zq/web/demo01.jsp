<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2021/5/20
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% pageContext.setAttribute("name", "黑百合");%>
<p>pageScope: ${pageScope.get("name")}</p>
<p>pageScope: ${pageScope.name}</p>

<% request.setAttribute("name", "吉斯·帝·拉伊");%>
<p>requestScope: ${requestScope.get("name")}</p>
<p>requestScope: ${requestScope.name}</p>

<% session.setAttribute("name", "波雅·汉库克");%>
<p>sessionScope: ${sessionScope.get("name")}</p>
<p>sessionScope: ${sessionScope.name}</p>

<% application.setAttribute("name", "妮可·罗宾");%>
<p>applicationScope: ${applicationScope.get("name")}</p>
<p>applicationScope: ${applicationScope.name}</p>

<!-- 键值对的类型是 String - String -->
<p>param: ${param.get("name")}</p>
<p>param: ${param.name}</p>
<p>age-param: ${param.get("age")}</p>
<p>age-param: ${param.age}</p>
<!-- 键值对的类型是 String - String[] -->
<p>paramValues: ${paramValues.get("name")}</p>
<p>paramValues: ${paramValues.name}</p>

<p>header: ${header.get("Accept")}</p>
<p>header: ${header.accept}</p>
<p>headerValues: ${headerValues.get("Accept")}</p>
<p>headerValues: ${headerValues.Accept}</p>

<p>cookie: ${cookie.get("JSESSIONID")}</p>
<p>cookie: ${cookie.JSESSIONID}</p>

<p>initParam: ${initParam.get("name")}</p>
<p>initParam: ${initParam.name}</p>

<%
    ArrayList<String> list=new ArrayList<>();
    list.add("不知火舞");
    list.add("红叶");
    list.add("黑百合");

    pageContext.setAttribute("list", list);
%>
<p>${pageScope.list[0]}</p>
<p>${pageScope.list[1]}</p>
<%@page import="java.util.List" %>
<p>${list.indexOf("红叶")}</p>
</body>
</html>
