<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.zq.controller.servlet.WebConstant" %>
<%@page import="com.zq.utils.NumberUtils" %>
<c:set var="grade" value="${WebConstant.GRADE}"/>
<c:set var="clazz" value="${WebConstant.CLAZZ}"/>
<c:set var="teacher" value="${WebConstant.TEACHER}"/>
<c:set var="watchwords" value="${WebConstant.WATCHWORDS}"/>
<c:set var="lastOperate" value="${sessionScope.classGradeLastOperate}"/>
<c:remove var="classGradeLastOperate" scope="session"/>
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
    <title>添加新班级</title>
    <link rel="stylesheet" href="lib/css/bootstrap.min.css"/>
</head>
<script src="lib/js/bootstrap.min.js"></script>
<script src="lib/js/jquery-3.6.0.min.js"></script>
<body style="background-color: rgba(0, 0, 0, 0.055)">
<div style="width: 600px; margin-left: auto; margin-right: auto">
    <jsp:include page="main-last.jsp">
        <jsp:param name="url" value="class-grade-system.jsp"/>
    </jsp:include>
    <h1 class="text-center">添加新班级</h1>

    <hr style="border: none; border-top: 3px solid black"/>

    <form
            style="padding: 15px 25px; font-size: 1.2em"
            action="add-class-grade"
            method="post"
    >
        <div class="form-group">
            <label for="${grade}">
                班级名:
                <span style="font-weight: bolder"></span>
            </label><br/>
            <select id="${grade}" name="${grade}" class="controls-row" required
                    style="margin-left: 3em"
            >
                <c:forEach var="i" begin="1" end="9">
                    <c:choose>
                        <c:when test="${NumberUtils.intToCn(i).equals(param[grade])}">
                            <option value="${NumberUtils.intToCn(i)}" selected>
                                    ${NumberUtils.intToCn(i)}
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
                    id="${clazz}"
                    name="${clazz}"
                    type="number"
                    min="1"
                    style="width: 4em"
                    class="controls-row" required
                    value="${param[clazz]}"
            />班

        </div>
        <div class="form-group">
            <label class="control-label" for="${teacher}">
                班主任:&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="font-weight: bolder"></span>
            </label>
            <input id="${teacher}" name="${teacher}" type="text"
                   class="form-control" required value="${param[teacher]}"/>
        </div>
        <div class="form-group">
            <label for="${watchwords}" class="control-label">口号:</label>
            <textarea id="${watchwords}" name="${watchwords}" rows="5"
                      placeholder="不超过100字"
                      class="form-control"
            >${param[watchwords]}</textarea>
        </div>
        <input type="submit" class="btn btn-primary" value="确定"/>
        <a class="btn btn-default" href="class-grade-system.jsp">
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

