<%--
  Created by IntelliJ IDEA.
  User: 86187
  Date: 2020/7/7
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%--
       文件上传三要素
            1.表单提交方式必须是 post
            2.表单的 enctype属性必须为: multipart/from-data
            3.表单中必须有文件上传项
       --%>
    <form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/upload">
        <input type="file" name="upload">
        <br>
        <input type="text" name="name">
        <input type="text" name = "password">
        <input type="submit" value="文件上传">
    </form>

    <img src="/upload/1f5f26e1f21f4375bfb72b27144a4541_琦玉.jpg">
</body>
</html>
