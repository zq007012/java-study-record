<%@ page import="com.lagou.demo03.Student" %><%--
  Created by IntelliJ IDEA.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>实现session中数据的钝化和活化操作</title>
</head>
<body>
<%
    // 创建Student类型的对象
    Student student = new Student();
    student.setName("zhangfei");
    // 将数据放入到session中
    session.setAttribute("student", student);
%>

</body>
</html>
