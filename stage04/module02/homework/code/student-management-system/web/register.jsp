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
    <title>注册</title>
    <link rel="stylesheet" href="lib/css/bootstrap.min.css"/>
</head>
<script src="lib/js/jquery-3.6.0.min.js"></script>
<script src="lib/js/bootstrap.min.js"></script>

<body style="background-color: rgba(0, 0, 0, 0.055)">
<div style="width: 500px; margin-left: auto; margin-right: auto">
    <h1 class="text-center">注册管理员账号</h1>
    <hr style="border: none; border-top: 3px solid black"/>
    <form
            style="padding: 15px 25px; font-size: 1.25em"
            action="register"
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
                    value="${WebConstant.FAILED.equals(requestScope.registerResult) ? param.userName : ''}"

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

            />
        </div>
            <div class="form-group">
                <label for="checkPassword">
                    校验密码:
                    <span style="color: rgba(255, 0, 0, 0.678); margin-left: 1em"></span>
                </label>
                <input
                        type="password"
                        class="form-control"
                        id="checkPassword"
                        name="checkPassword"
                        placeholder="请再次输入密码..."
                />
            </div>
            <%-- 当注册失败后, 显示一条提示--%>
            <c:if test="${WebConstant.FAILED.equals(requestScope.registerResult)}">
            <div id="registerFailed"
                 style="text-align: center; padding: 5px; background-color: rgba(185, 182, 180, 0.377);color: red;font-weight: bolder;"
            >
                注册失败, 已存在该账号
            </div>
            </c:if>
            <div class="form-group">
                <input
                        class="col-xs-5 btn btn-primary btn-lg text-center"
                        style="margin-top: 10px; padding: 5px"
                        type="submit"
                        value="注册"
                />
                <a
                        class="col-xs-5 col-xs-offset-2 btn btn-default btn-lg text-center"
                        style="margin-top: 10px; padding: 5px"
                        href="login.jsp"
                >取消</a>
            </div>
    </form>
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
        });
        $("#checkPassword").on("input", function () {
            isEmpty($(this), "校验密码不能为空!!!");
            if ($("#password").val() != $("#checkPassword").val()) {
                $("#checkPassword").parent()
                    .addClass("has-error")
                    .children("label")
                    .children("span")
                    .text("两次输入的密码不一致");
            }else{
                $("#checkPassword").parent()
                    .removeClass("has-error")
                    .children("label")
                    .children("span")
                    .text("");
            }
        });

        $("form").submit(function (event) {
            if (checkHasEmpty() || $("#password").val() != $("#checkPassword").val()) {
                event.preventDefault();
            }
        });

        function checkHasEmpty() {
            //去掉账号和密码开头与结尾的空格再提交
            let account = $("#userName");
            account.val(account.val().trim());
            let password = $("#password");
            password.val(password.val().trim());
            let checkPassword = $("#checkPassword");
            password.val(password.val().trim());

            let accountIsEmpty = isEmpty(account, "账号不能为空!!!");
            let passwordIsEmpty = isEmpty(password, "密码不能为空!!!");
            let checkPasswordIsEmpty = isEmpty(checkPassword, "校验密码不能为空!!!");
            return accountIsEmpty || passwordIsEmpty || checkPasswordIsEmpty;
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
                if (jquery.prev("label").children("span").text().includes("不能为空")){
                    $(jquery)
                        .parent()
                        .addClass("has-error")
                        .children("label")
                        .children("span")
                        .text("");
                }
            }
            return empty;
        }
    });


</script>
</html>
