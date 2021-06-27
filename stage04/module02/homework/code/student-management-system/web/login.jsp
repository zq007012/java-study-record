<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.zq.controller.servlet.WebConstant" %>
<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2021/5/9
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>

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
    <h1 class="text-center">学生管理系统</h1>
    <h1 class="text-center">登录界面</h1>
    <hr style="border: none; border-top: 3px solid black"/>
    <form
            style="padding: 15px 25px; font-size: 1.25em"
            action="verify-login"
            method="post"
    >
        <div class="form-group">
            <label for="userName">
                账号:
                <span style="color: rgba(255, 0, 0, 0.678); margin-left: 1em">
                </span>
            </label>
            <input
                    type="text"
                    class="form-control"
                    id="userName"
                    name="userName"
                    placeholder="请输入账号..."
                    value="${WebConstant.FAILED.equals(sessionScope.loginStatus) ? sessionScope.user.userName : ''}"

            />
        </div>
        <div class="form-group">
            <label for="password">
                密码:
                <span style="color: rgba(255, 0, 0, 0.678); margin-left: 1em"></span>
            </label>
            <input
                    type="password"
                    class="form-control"
                    id="password"
                    name="password"
                    placeholder="请输入密码..."
                    value="${WebConstant.FAILED.equals(sessionScope.loginStatus) ? sessionScope.user.password : ''}"

            />
        </div>
        <%-- 当用户登录失败或者登录状态失效或者注册成功后, 显示一条提示--%>
        <c:if test="${!(empty sessionScope.loginStatus) || !(empty sessionScope.registerResult)}">
            <div id="logInFailed"
                 style="text-align: center; padding: 5px; background-color: rgba(185, 182, 180, 0.377);color: red;font-weight: bolder;"
            >
                <c:choose>
                    <c:when test="${sessionScope.loginStatus.equals(WebConstant.FAILED)}">
                        登录失败, 账号或密码错误
                    </c:when>
                    <c:when test="${sessionScope.loginStatus.equals(WebConstant.INVALID)}">
                        登录已失效, 请输入账号和密码
                    </c:when>
                    <c:when test="${sessionScope.registerResult.equals(WebConstant.SUCCESS)}">
                        注册成功, 请输入账号和密码登录
                        <c:remove var="registerResult"/>
                    </c:when>

                </c:choose>
            </div>
        </c:if>
        <div class="checkbox" style="margin-left: 1em">
            <label>
                <input name="absolveAWeek" value="absolveAWeek" type="checkbox"
                       style="zoom:125%; vertical-align: middle"/>
                <span style="vertical-align: middle;font-size: 0.85em">一周内免登录</span>
            </label>
        </div>
        <div class="form-group">
            <input
                    class="form-control btn btn-primary btn-lg text-center"
                    style="margin-top: 10px; padding: 5px"
                    type="submit"
                    value="登录"
            />
        </div>
    </form>
    <a href="register.jsp">注册新账号</a>
</div>
</body>
<script>
    $(function () {
        $("#userName").on("input", function () {
            isEmpty($(this), "账号不能为空!!!");
            $("#logInFailed").hide();
        });
        $("#password").on("input", function () {
            isEmpty($(this), "密码不能为空!!!");
            $("#logInFailed").hide();
        });

        $("form").submit(function (event) {
            if (checkHasEmpty()) {
                event.preventDefault();
            }
        });

        function checkHasEmpty() {
            //去掉账号和密码开头与结尾的空格再提交
            let account = $("#userName");
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
