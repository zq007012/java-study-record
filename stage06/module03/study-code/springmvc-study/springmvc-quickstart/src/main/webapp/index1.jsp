<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2022/4/24
  Time: 8:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/user/simpleParams?username=路飞&money=100">基本类型的请求参数</a><br>

<form style="border-style: dashed;padding: 5px"
      action="${pageContext.request.contextPath}/user/simpleParamsByPost"
      method="post">
    姓名: <input type="text" name="username" placeholder="请输入姓名"/><br/>
    存款: <input type="text" name="money" placeholder="请输入存款"/><br/>
    <input type="submit" value="提交"/>
</form>

<form style="border-style: dashed;padding: 5px"
      action="${pageContext.request.contextPath}/user/pojoParams" method="post">
    姓名: <input type="text" name="name" placeholder="请输入姓名"/><br/>
    存款: <input type="text" name="money" placeholder="请输入存款"/><br/>
    <input type="submit" value="对象类型"/>
</form>

<form style="border-style: dashed;padding: 5px"
      action="${pageContext.request.contextPath}/user/arrayParams"
      method="post">
    <input type="checkbox" name="ids" value="1"/>1<br/>
    <input type="checkbox" name="ids" value="2"/>2<br/>
    <input type="checkbox" name="ids" value="3"/>3<br/>
    <input type="checkbox" name="ids" value="4"/>4<br/>
    <input type="checkbox" name="ids" value="5"/>5<br/>
    <input type="checkbox" name="ids" value="6"/>6<br/>
    <input type="submit" value="数组类型"/>
</form>

<form style="border-style: dashed;padding: 5px"
      action="${pageContext.request.contextPath}/user/listParams" method="post">
    第一个账户: <br/>
    姓名: <input type="text" name="accountList[0].name" placeholder="请输入姓名"/><br/>
    存款: <input type="text" name="accountList[0].money" placeholder="请输入存款"/><br/>
    第二个账户: <br/>
    姓名: <input type="text" name="accountList[1].name" placeholder="请输入姓名"/><br/>
    存款: <input type="text" name="accountList[1].money" placeholder="请输入存款"/><br/>
    第三个账户: <br/>
    姓名: <input type="text" name="accountList[2].name" placeholder="请输入姓名"/><br/>
    存款: <input type="text" name="accountList[2].money" placeholder="请输入存款"/><br/>
    第四个账户: <br/>
    姓名: <input type="text" name="accountList[3].name" placeholder="请输入姓名"/><br/>
    存款: <input type="text" name="accountList[3].money" placeholder="请输入存款"/><br/>
    第五个账户: <br/>
    姓名: <input type="text" name="accountList[4].name" placeholder="请输入姓名"/><br/>
    存款: <input type="text" name="accountList[4].money" placeholder="请输入存款"/><br/>

    <input type="submit" value="list集合类型"/>
</form>

<form style="border-style: dashed;padding: 5px"
      action="${pageContext.request.contextPath}/user/mapParams" method="post">
    第一个账户: <br/>
    姓名: <input type="text" name="accountMap['a0'].name" placeholder="请输入姓名"/><br/>
    存款: <input type="text" name="accountMap['a0'].money" placeholder="请输入存款"/><br/>
    第二个账户: <br/>
    姓名: <input type="text" name="accountMap['a1'].name" placeholder="请输入姓名"/><br/>
    存款: <input type="text" name="accountMap['a1'].money" placeholder="请输入存款"/><br/>
    第三个账户: <br/>
    姓名: <input type="text" name="accountMap['a2'].name" placeholder="请输入姓名"/><br/>
    存款: <input type="text" name="accountMap['a2'].money" placeholder="请输入存款"/><br/>
    第四个账户: <br/>
    姓名: <input type="text" name="accountMap['a3'].name" placeholder="请输入姓名"/><br/>
    存款: <input type="text" name="accountMap['a3'].money" placeholder="请输入存款"/><br/>
    第五个账户: <br/>
    姓名: <input type="text" name="accountMap['a4'].name" placeholder="请输入姓名"/><br/>
    存款: <input type="text" name="accountMap['a4'].money" placeholder="请输入存款"/><br/>

    <input type="submit" value="map集合类型"/>
</form>

<form style="border-style: dashed;padding: 5px"
      action="${pageContext.request.contextPath}/user/dateParams" method="post">
    生日: <input type="text" name="birthday" placeholder="请输入生日"><br/>
    <input type="submit" value="提交生日">
</form>

<form style="border-style: dashed;padding: 5px"
      action="${pageContext.request.contextPath}/user/requestParamAnnoDemo" method="post">
    名字: <input type="text" name="name" placeholder="请输入名字"><br/>
    <input type="submit" value="提交名字">
</form>

<a href="${pageContext.request.contextPath}/user/requestHeaderAnnoDemo">@requestHeader测试</a><br/>
<a href="${pageContext.request.contextPath}/user/cookieValueAnnoDemo">@cookieValue测试</a><br/>
<a href="${pageContext.request.contextPath}/user/servletApiDemo">servletApi测试</a><br/>

</body>
</html>
