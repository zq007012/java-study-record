<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2022/2/9
  Time: 8:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>注册页面</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script>
        $(function () {
            $("#msg").hide();
            $("#username").blur(function () {
                var name = $(this).val().trim();
                console.log("${pageContext.request.contextPath}/checkName");
                if (name == null || name.length === 0){
                    $("#msg").hide();
                }else{
                    $.ajax({
                        url : "${pageContext.request.contextPath}/checkName",
                        method : "GET",
                        data : { username : name },
                        dataType : "json",
                        success : function (data) {
                            console.log(data);
                            console.log(data.msg);
                            $("#msg").text(data.msg).show();
                        },
                        error : function () {
                            $("#msg").text("").hide();
                            alert("请求处理失败");
                        }

                    });
                }
            })
        });
    </script>
</head>
<body>
    <form>
        用户名: <input type="text" id="username" name="username"/><span id="msg" style="color:red ; font-size: 1em">吃了吗</span><br/>
        密码: <input type="text">
    </form>
</body>
</html>
