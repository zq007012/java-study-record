<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2021/5/25
  Time: 21:00
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
    <title>学生管理系统</title>
    <link rel="stylesheet" href="lib/css/bootstrap.min.css"/>
</head>
<script src="lib/js/bootstrap.min.js"></script>
<script src="lib/js/jquery-3.6.0.min.js"></script>
<body>
<div
        style="margin-left: auto; margin-right: auto; max-width: 600px"
        class="text-center"
>
    <div class="text-right"><a class="btn btn-default btn-xs" href="quit">退出</a></div>
    <h1 class="text-center">学生管理系统</h1>
    <hr/>

    <div class="col-sm-6 col-xs-12 text-center" style="height: 80px;">
        <a type="button" class="btn btn-primary btn-lg" href="show-all-class-grade">
            <span class="glyphicon glyphicon-align-left"></span>
            &nbsp;&nbsp;班级信息管理系统
        </a>
    </div>
    <div class="col-sm-6 col-xs-12 text-center" style="height: 80px;">
        <a type="button" class="btn btn-primary btn-lg" href="show-all-student">
            <span class="glyphicon glyphicon-education"></span>
            &nbsp;&nbsp;学生信息管理系统
        </a>
    </div>
</div>
</body>
</html>

