<%@ page import="java.io.File" %>
<%@ page import="java.awt.font.ImageGraphicAttribute" %><%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2021/5/8
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>程序代码区</title>
</head>
<body>
<h1><%="看美女啦!!!"%></h1>
<% File dir = new File(application.getRealPath("images"));
    System.out.println(dir.getAbsoluteFile());
    File[] images = dir.listFiles();
    for (File image : images) {
%>
<img src="<%="images/" + image.getName()%>" style="height: 300px" alt="<%=image.getPath()%>不存在"/><br/>
<%
    }
%>
</body>
</html>
