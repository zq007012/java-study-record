<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2022/4/30
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<script src="${pageContext.request.contextPath}/lib/js/jquery.min.js"></script>
<body>
<button id="btn1">提交json对象格式的数据</button>
<br/>
<button id="btn2">提交json数组格式的数据</button>
<br/>
<form style="border-style: dashed"
      action="${pageContext.request.contextPath}/singleFile" method="post"
      enctype="multipart/form-data">
    <input name="name" type="text" placeholder="请输入姓名"><br>
    <input name="filePic" type="file"><br>
    <input name="money" type="text" placeholder="请输入金额"><br>
    <input type="submit" value="提交"/>
</form>

<form style="border-style: dashed"
      action="${pageContext.request.contextPath}/multipleFiles" method="post"
      enctype="multipart/form-data">
    <input name="name" type="text" placeholder="请输入姓名"><br>
    <input name="money" type="text" placeholder="请输入金额"><br>
    <input name="filePics" type="file"><br>
    <input name="filePics" type="file"><br>
    <input name="filePics" type="file"><br>
    <input type="submit" value="提交"/>
</form>
<a href="${pageContext.request.contextPath}/errorTest">errorTest</a><br/>
</body>
<script>
    $("#btn2").click(function () {
        console.log("点击了btn2----------------")
        let url = "${pageContext.request.contextPath}/jsonArrayRequest";
        let accounts = [{name: "不知火舞", money: 22222},
            {name: "蒂米特雷斯库", money: 33333},
            {name: "贝优妮塔", money: 11111}];
        $.ajax({
            type: "POST",
            contentType: "application/json;charset=utf-8",
            url: url,
            data: JSON.stringify(accounts),
            success: function (respData) {
                console.log(respData);
            },
            error: function(error){
                console.log(error.responseText);
                //替换掉本网页的html节点
                let newDoc = document.open("text/html", "replace");
                newDoc.write(error.responseText);
                newDoc.close();
            }
        });
    });
    $("#btn1").click(function () {
        console.log("点击了btn1------------");
        let url = "${pageContext.request.contextPath}/jsonObjRequest";
        let account = {name: "穗乃果", money: "10000"};
        $.ajax({
            type: "POST",
            contentType: "application/json;charset=utf-8",
            url: url,
            data: JSON.stringify(account),
            dataType: "json",
            success: function (resp) {
                console.log(resp);
            },
            error: function(error){
                console.log(error.responseText);
                //替换本网页的html节点
                let newDoc = document.open("text/html", "replace");
                newDoc.write(error.responseText);
                newDoc.close();
            }
        });
    });
</script>
</html>
