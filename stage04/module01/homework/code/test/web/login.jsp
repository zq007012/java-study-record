<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2021/5/9
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<!-- 如果登录状态是"success"就直接重定向到学生信息管理系统的界面 -->
<%
    if ("success".equals(session.getAttribute("logIn"))) {
        response.sendRedirect("student-system.jsp");
        return;
    }
%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>登录界面</title>
    <link rel="stylesheet" href="lib/css/bootstrap.min.css"/>
</head>
<script src="lib/js/jquery-3.6.0.min.js"></script>
<script src="lib/js/bootstrap.min.js"></script>

<body style="background-color: rgba(0, 0, 0, 0.055)">
<div style="width: 500px; margin-left: auto; margin-right: auto">
    <h1 class="text-center">学生信息管理系统</h1>
    <h1 class="text-center">登录界面</h1>
    <hr style="border: none; border-top: 3px solid black"/>
    <form
            style="padding: 15px 25px; font-size: 1.2em"
            action="verify"
            method="post"
    >
        <div class="form-group">
            <label for="account">
                账号:
                <span style="color: rgba(255, 0, 0, 0.678); margin-left: 1em"></span
                ></label>
            <input
                    type="text"
                    class="form-control"
                    id="account"
                    name="account"
                    placeholder="请输入账号..."
            />
        </div>
        <div class="form-group">
            <label for="password"
            >密码:<span
                    style="color: rgba(255, 0, 0, 0.678); margin-left: 1em"
            ></span
            ></label>
            <input
                    type="password"
                    class="form-control"
                    id="password"
                    name="password"
                    placeholder="请输入密码..."
            />
        </div>
        <%
            //如果session对象中的"logIn"属性的值不是"success", 则说明没有登录成功
            //在返回的页面中显示一条提醒登陆失败或者登录失效的信息
            String logInStatus = (String) session.getAttribute("logIn");
            if (null != logInStatus && !"success".equals(logInStatus)) {
        %>
        <div
                id="logInFailed"
                style="
            text-align: center;
            padding: 5px;
            background-color: rgba(185, 182, 180, 0.377);
            color: red;
            font-weight: bolder;
          "
        >
            <%
                if ("failed".equals(logInStatus)) {
            %>
            登录失败, 账号或密码错误
            <script>
                //保留之前输入的的错误账号和密码
                $("#account").val("<%=request.getParameter("account") == null ? "" :request.getParameter("account") %>");
                $("#password").val("<%=request.getParameter("password") == null ? "" :request.getParameter("password") %>")
            </script>
            <%
                }
            %>
            <%
                if ("invalid".equals(logInStatus)) {
            %>
            您还未登录, 请输入账号和密码
            <%
                }
            %>
        </div>

        <%
            }
        %>
        <input
                class="form-control btn btn-primary btn-lg text-center"
                style="margin-top: 10px; padding: 5px"
                type="submit"
                value="登录"
        />
    </form>
</div>
</body>
<script>
    $(function () {


        $("#account").on("input", function () {
            isEmpty($(this), "账号不能为空!!!");
            $("#logInFailed").hide();
        });
        $("#password").on("input", function () {
            isEmpty($(this), "密码不能为空!!!");
            $("#logInFailed").hide();
        });

        $("form").submit(function (event) {
            if (checkHasEmpty()){
                event.preventDefault();
            }
        });
        function checkHasEmpty() {
            //去掉账号和密码开头与结尾的空格再提交
            let account = $("#account");
            account.val(account.val().trim());
            let password = $("#password");
            password.val(password.val().trim());

            let accountIsEmpty = isEmpty(account, "账号不能为空!!!");
            let passwordIsEmpty = isEmpty(password, "密码不能为空!!!");
            return accountIsEmpty || passwordIsEmpty;
        }

        <!-- 检测输入框中的内容是否为空, 如果输入框中的内容为空, 则显示提示信息以及该百年输入框的样式-->
        function isEmpty(jquery, errorInfo) {
            let value = jquery.val().trim();
            let empty = value == null || value == "";
            if (empty) {
                $(jquery)
                    .parent()
                    .addClass("has-error")
                    .children("label")
                    .children("span")
                    .text(errorInfo);
            } else {
                $(jquery)
                    .parent()
                    .removeClass("has-error")
                    .children(":first")
                    .children("span")
                    .text("");
            }
            return empty;
        }
    });


</script>
</html>
