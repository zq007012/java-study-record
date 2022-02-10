<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2022/2/9
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>js方式的Ajax的demo</title>

    <script>
        function run() {
            console.log("开始运行")
            //1.创建 核心对象
            var xmlHttp;

            //2.判断浏览器类型
            if (window.XMLHttpRequest) {
                xmlHttp = new XMLHttpRequest();
            } else {
                xmlHttp = new ActiveXObject("MicroSoft.XMLHTTP");
            }


            //3.建立连接
            /*
             * 三个参数:
             *      1.请求方式 get post
             *      2.请求资源的路径
             *      3.是否为异步 是 or 否
             */
            xmlHttp.open("GET", "/hello_maven/login?username=bayonetta", true)
            //4.发送请求
            xmlHttp.send();

            //5.获取响应结果
            /*
             * 什么时候获取响应数据?
             *     在服务器响应成功后获取
             */
            //监听readyState状态改变
            xmlHttp.onreadystatechange=function () {
                //readyState==4 响应已经就绪, 200 访问成功
                if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                    // 获取到响应了
                    var text = xmlHttp.responseText;
                    console.log(text);
                    alert(text);
                }
            }
        }
    </script>
</head>
<body>
<input type="button" value="发送异步请求" onclick="run()"><br>
局部刷新 <input type="text">
</body>
</html>
