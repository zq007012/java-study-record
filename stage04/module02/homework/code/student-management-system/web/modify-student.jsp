<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.zq.controller.servlet.WebConstant" %>
<%@page import="com.zq.model.factory.ClassGradeDaoFactory" %>
<%@ page import="com.zq.model.factory.StudentDaoFactory" %>
<%@ page import="com.zq.model.javabean.ClassGrade" %>
<%@ page import="com.zq.model.javabean.Student" %>
<%@ page import="com.zq.model.service.ClassGradeManagerService" %>
<%@ page import="com.zq.model.service.StudentMangerService" %>
<%@ page import="java.util.List" %>

<%
    try {
        ClassGradeManagerService cgMg = new ClassGradeManagerService(ClassGradeDaoFactory.newClassGradeDao());
        List<ClassGrade> cgList = cgMg.getAll();
        pageContext.setAttribute("cgList", cgList);

        StudentMangerService stuMg = new StudentMangerService(StudentDaoFactory.newStudentDao());
        String studentId = request.getParameter(WebConstant.STUDENT_ID);
        Student student = stuMg.getStudentByStudentId(studentId);
        pageContext.setAttribute("student", student);
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<c:set var="lastOperate" value="${sessionScope.studentLastOperate}"/>
<c:remove var="studentLastOperate" scope="session"/>
<c:set var="lastOperateResult" value="${sessionScope.lastOperateResult}"/>
<c:remove var="lastOperateResult" scope="session"/>
<c:set var="notices" value="${sessionScope.notices}"/>
<c:remove var="notices" scope="session"/>
<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2021/6/2
  Time: 19:48
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
    <title>编辑学生信息</title>
    <link rel="stylesheet" href="lib/css/bootstrap.min.css"/>
</head>
<script src="lib/js/bootstrap.min.js"></script>
<script src="lib/js/jquery-3.6.0.min.js"></script>
<body style="background-color: rgba(0, 0, 0, 0.055)">
<div style="width: 600px; margin-left: auto; margin-right: auto">
    <jsp:include page="main-last.jsp">
        <jsp:param name="url" value="student-system.jsp"/>
    </jsp:include>
    <h1 class="text-center">编辑学生信息</h1>

    <hr style="border: none; border-top: 3px solid black"/>

    <form
            style="padding: 15px 25px; font-size: 1.2em"
            action="modify-student"
            method="post"
    >
        <div class="form-group hide">
            <label class="control-label" for="studentId">
                主键id:&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="font-weight: bolder"></span>
            </label>
            <input id="studentId" name="studentId" type="text"
                   class="form-control"
                   value="${param.studentId}" />
        </div>
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
                    max="99999999"
                    placeholder="学号必须是不小于20000000的8位数的整数"
                    class="form-control"
                    value="${!empty param.number ? param.number : student.number}"
                    required
            />
        </div>
        <div class="form-group">
            <label class="control-label" for="name">
                姓名:&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="font-weight: bolder"></span>
            </label>
            <input id="name" name="name" type="text" class="form-control"
                   value="${!empty param.name ? param.name :student.name}"
                   required/>
        </div>
        <div class="form-group">
            <label class="control-label" for="classGradeId">
                班级:&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="font-weight: bolder"></span>
            </label>

            <select id="classGradeId" name="classGradeId"
                    class="form-control                      " required>
                <c:forEach var="cg" items="${cgList}">
                    <option value="${cg.classGradeId}"
                        ${cg.classGradeId.equals(!empty param.classGradeId ? param.classGradeId : student.classGradeId) ? 'selected' : ''}>
                        <strong>${cg.classGradeName}</strong>
                    </option>
                </c:forEach>
            </select>

        </div>

        <div class="form-group">
            <label class="control-label" for="gender">性别:</label>
            <div id="gender" class="radio" style="margin-left: 2.5em">
                <label class="radio-inline">
                    <input name="gender" type="radio" value="男"
                    ${!'女'.equals(!empty param.gender ? param.gender :student.gender) ? 'checked' : ''}/>
                    男
                </label>
                <label class="radio-inline">
                    <input name="gender" type="radio" value="女"
                    ${'女'.equals(!empty param.gender ? param.gender :student.gender) ? 'checked' : ''}/>
                    女
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
                    value="${!empty param.birthday ? param.birthday :student.birthday}"
                    max="2015-01-01"
                    min="1990-01-01"
            />
        </div>
        <div class="form-group">
            <label for="email" class="control-label">
                邮箱:&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="font-weight: bolder"></span>
            </label>
            <input id="email" name="email" type="email"
                   value="${!empty param.email ? param.email :student.email}"
                   class="form-control"/>
        </div>
        <div class="form-group">
            <label for="notes" class="control-label">备注:</label>
            <textarea id="notes" name="notes"
                      class="form-control">${!empty param.notes ? param.notes :student.notes}</textarea>
        </div>
        <input type="submit" class="btn btn-primary" value="确定"/>
        <a class="btn btn-default" href="student-system.jsp">
            取消
        </a>
    </form>
</div>
</body>
<c:if test="${WebConstant.ADD.equals(lastOperate) &&
                    WebConstant.FAILED.equals(lastOperateResult)}">
    <script>
        alert("${notices[0]}");
    </script>
</c:if>
</html>

