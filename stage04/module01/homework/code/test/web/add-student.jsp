<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2021/5/14
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<!-- 如果登录状态不是"success"就直接重定向到登录界面 -->
<%
    if (!"success".equals(session.getAttribute("logIn"))) {
        session.setAttribute("logIn", "invalid");
        response.sendRedirect("login.jsp");
        return;
    }
%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>添加新学生</title>
    <link rel="stylesheet" href="lib/css/bootstrap.min.css" />
</head>
<script src="lib/js/bootstrap.min.js"></script>
<script src="lib/js/jquery-3.6.0.min.js"></script>
<body style="background-color: rgba(0, 0, 0, 0.055)">
<div style="width: 600px; margin-left: auto; margin-right: auto">
    <h1 class="text-center">添加新学生</h1>

    <hr style="border: none; border-top: 3px solid black" />

    <form
            style="padding: 15px 25px; font-size: 1.2em"
            action="add"
            method="post"
    >
        <div class="form-group">
            <label class="control-label" for="number">
                学号:&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="font-weight: bolder"></span>
            </label>
            <input
                    id="number"
                    name="number"
                    type="number"
                    min="20000000"
                    ;
                    max="99999999"
                    ;
                    placeholder="学号必须是不小于20000000的8位数的整数"
                    class="form-control"
            />
        </div>
        <div class="form-group">
            <label class="control-label" for="name">
                姓名:&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="font-weight: bolder"></span>
            </label>
            <input id="name" name="name" type="text" class="form-control" />
        </div>

        <div class="from-group">
            <label class="control-label" for="gender">性别:</label>
            <div id="gender" class="radio" style="margin-left: 2.5em">
                <label class="radio-inline">
                    <input name="gender" type="radio" value="男" checked="true" /> 男
                </label>
                <label class="radio-inline">
                    <input name="gender" type="radio" value="女" /> 女
                </label>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label" for="birthday">出生日期:</label>
            <input
                    id="birthday"
                    name="birthday"
                    type="date"
                    class="form-control"
                    value="2005-01-01"
                    max="2015-01-01"
                    min="1990-01-01"
            />
        </div>
        <div class="form-group">
            <label for="email" class="control-label">
                邮箱:&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="font-weight: bolder"></span>
            </label>
            <input id="email" name="email" type="email" class="form-control" />
        </div>
        <div class="form-group">
            <label for="notes" class="control-label">备注:</label>
            <textarea id="notes" name="notes" class="form-control"></textarea>
        </div>
        <input type="submit" class="btn btn-primary" value="确定" />
        <input
                id="cancelBtn"
                type="button"
                class="btn btn-default"
                value="取消"
        />
    </form>
</div>
</body>
<script>
    $(function () {
        <%
        // 从请求中获取'addFailed'属性, 如果有这个属性就说明是从addServlet转发过来的
        // 就需要判断添加学生是否成功了
        String addFailed = (String) request.getAttribute("addFailed");
        if (null != addFailed){
        %>
        $(`#number`).val(`<%=request.getParameter("number")%>`).data("unusableNumber", `<%=request.getParameter("number")%>`)
            .prev().children("span").text("已存在该学号, 请换个学号")
            .parent().parent().addClass(`has-error`);
        $(`#name`).val(`<%=request.getParameter("name")%>`)
            .prev().children("span").text("")
            .parent().parent().removeClass(`has-error`);
        $(`input[type="radio"][name="gender"][value="<%=request.getParameter("gender")%>"]`)
            .prop("checked", true);
        $(`#birthday`).val(`<%=request.getParameter("birthday")%>`);
        $(`#email`).val(`<%=request.getParameter("email")%>`)
            .prev().children("span").text("")
            .parent().parent()
            .removeClass(`has-error`);
        $(`#notes`).val(`<%=request.getParameter("notes")%>`);
        <%
        }
        %>
        $("#cancelBtn").click(function () {
            location.href = "student-system.jsp";
        });
        //监听表单提交事件
        $("form").submit(function (event) {
            //表单里有控件的值为空的话就阻止提交
            if (formHasEmpty()) {
                event.preventDefault();
            }
        });


        //学号输入框的非空判断
        $("#number").on("input", function () {
            isEmpty($(this), "学号不能为空!!!");

            let unusableNumber = $(this).data("unusableNumber");
            if (null != unusableNumber && undefined != unusableNumber){
                if ( unusableNumber == $(this).val()){
                    $(this)
                        .parent().addClass("has-error")
                        .children(":first")
                        .children("span").text("已存在该学号");
                }else{
                    $(this)
                        .parent().removeClass("has-error")
                        .children(":first")
                        .children("span").text("");
                }
            }
        });
        //姓名输入框的非空判断
        $("#name").on("input", function () {
            isEmpty($(this), "姓名不能为空!!!");
        });
        //邮箱输入框的非空判断
        $("#email").on("input", function () {
            isEmpty($(this), "邮箱不能为空!!!");
        });

        function formHasEmpty() {
            //去掉账号和密码开头与结尾的空格再提交
            let num = $("#number");
            num.val(num.val().trim());
            let name = $("#name");
            name.val(name.val().trim());
            let email = $("#email");
            email.val(email.val().trim());

            let accountIsEmpty = isEmpty(num, "学号不能为空!!!");
            let passwordIsEmpty = isEmpty(name, "姓名不能为空!!!");
            let emailIsEmpty = isEmpty(email, "邮箱不能为空!!!");
            return accountIsEmpty || passwordIsEmpty || emailIsEmpty;
        }

        // 检测输入框中的内容是否为空, 如果输入框中的内容为空, 则显示提示信息以及改变输入框的样式
        function isEmpty(jquery, errorInfo) {
            let value = jquery.val().trim();
            let empty = value == null || value === "";
            if (empty) {
                $(jquery)
                    .parent()
                    .addClass("has-error")
                    .children(":first")
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

