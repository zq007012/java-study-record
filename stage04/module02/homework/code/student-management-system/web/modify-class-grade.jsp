<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.zq.controller.servlet.WebConstant" %>
<%@ page import="com.zq.model.factory.ClassGradeDaoFactory" %>
<%@ page import="com.zq.model.javabean.ClassGrade" %>
<%@page import="com.zq.model.service.ClassGradeManagerService" %>
<%@page import="com.zq.utils.NumberUtils" %>
<%@ page import="com.zq.utils.EmptyUtils" %>
<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2021/6/7
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         language="java" %>
<%
    String classGradeId = request.getParameter(WebConstant.CLASS_GRADE_ID);
    try {
        ClassGradeManagerService manager = new ClassGradeManagerService(
                ClassGradeDaoFactory.newClassGradeDao());
        ClassGrade classGrade = manager.getClassGradeByClassGradeId(classGradeId);
        pageContext.setAttribute("classGrade", classGrade);

        String cgName = request.getParameter(WebConstant.CLASS_GRADE_NAME);
        String classGradeName = EmptyUtils.isEmpty(cgName) ? classGrade.getClassGradeName() : cgName;
        String grade = classGradeName.substring(0,
                classGradeName.indexOf('年'));
        int clazz = Integer.parseInt(classGradeName.substring(classGradeName.indexOf('级') + 1, classGradeName.length() - 1));
        pageContext.setAttribute("grade", grade);
        pageContext.setAttribute("clazz", clazz);
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<c:set var="lastOperate" value="${sessionScope.classGradeLastOperate}"/>
<c:remove var="classGradeLastOperate" scope="session"/>
<c:set var="lastOperateResult" value="${sessionScope.lastOperateResult}"/>
<c:remove var="lastOperateResult" scope="session"/>
<c:set var="notices" value="${sessionScope.notices}"/>
<c:remove var="notices" scope="session"/>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>编辑班级</title>
    <link rel="stylesheet" href="lib/css/bootstrap.min.css"/>
</head>
<script src="lib/js/bootstrap.min.js"></script>
<script src="lib/js/jquery-3.6.0.min.js"></script>
<body style="background-color: rgba(0, 0, 0, 0.055)">
<div style="width: 600px; margin-left: auto; margin-right: auto">
    <jsp:include page="main-last.jsp">
        <jsp:param name="url" value="class-grade-system.jsp"/>
    </jsp:include>
    <h1 class="text-center">编辑班级</h1>

    <hr style="border: none; border-top: 3px solid black"/>

    <form
            style="padding: 15px 25px; font-size: 1.2em"
            action="modify-class-grade"
            method="post"
    >
        <div class="form-group hide">
            <label class="control-label" for="classGradeId">
                班级ID:&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="font-weight: bolder"></span>
            </label>
            <%--班级ID也在表单里, 只是不可见--%>
            <input id="classGradeId" name="classGradeId" type="text"
                   class="form-control" value="${param.classGradeId}"/>
        </div>

        <div class="form-group">
            <label for="grade">
                班级名:
                <span style="font-weight: bolder"></span>
            </label><br/>
            <select id="grade" name="grade" class="controls-row" required
                    style="margin-left: 3em"
            >
                <c:forEach var="i" begin="1" end="9">
                    <c:choose>
                        <c:when test="${NumberUtils.intToCn(i).equals(grade)}">
                            <option value="${grade}" selected>
                                    ${grade}
                            </option>
                        </c:when>
                        <c:otherwise>
                            <option value="${NumberUtils.intToCn(i)}">
                                    ${NumberUtils.intToCn(i)}
                            </option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>年级
            <input
                    id="clazz"
                    name="clazz"
                    type="number"
                    min="1"
                    style="width: 4em"
                    class="controls-row" required
                    value="${clazz}"
            />班

        </div>
        <div class="form-group">
            <label class="control-label" for="teacher">
                班主任:&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="font-weight: bolder"></span>
            </label>
            <input id="teacher" name="teacher" type="text"
                   class="form-control" required
                   value="${!empty param.teacher ? param.teacher : classGrade.classTeacher}"/>
        </div>
        <div class="form-group">
            <label for="watchwords" class="control-label">口号:</label>
            <textarea id="watchwords" name="watchwords" rows="5"
                      placeholder="不超过100字"
                      class="form-control"
            >${!empty param.watchwords? param.watchwords : classGrade.watchwords}
            </textarea>
        </div>
        <input type="submit" class="btn btn-primary" value="确定"/>
        <a class="btn btn-default" href="class-grade-system.jsp">
            取消
        </a>
    </form>
</div>
</body>
<c:if test="${WebConstant.MODIFY_CLASS_GRADE.equals(lastOperate) &&
                    WebConstant.FAILED.equals(lastOperateResult)}">
    <script>
        alert("${notices[0]}");
    </script>
</c:if>
</html>
