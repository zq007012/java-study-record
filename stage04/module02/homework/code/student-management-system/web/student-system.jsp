<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2021/5/31
  Time: 9:17
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
    <title>学生信息管理系统</title>
    <link rel="stylesheet" href="lib/css/bootstrap.min.css"/>
</head>
<script src="lib/js/jquery-3.6.0.min.js"></script>
<script src="lib/js/bootstrap.min.js"></script>
<body style="max-width: 1200px; padding: 5px" class="center-block">

<c:set var="lastOperate" value="${sessionScope.studentLastOperate}"/>
<c:remove var="studentLastOperate" scope="session"/>
<c:set var="lastOperateResult" value="${sessionScope.lastOperateResult}"/>
<c:remove var="lastOperateResult" scope="session"/>
<c:set var="notices" value="${sessionScope.notices}"/>
<c:remove var="notices" scope="session"/>

<!--提示信息的模态框-->
<div id="warningModal" class="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
                <h2 class="modal-title">提示信息</h2>
            </div>
            <div id="warningInfo"
                 class="modal-body text-center lead">
                <c:if test="${!empty notices}">
                    <c:forEach var="notice" items="${notices}"
                               varStatus="status">
                        <c:choose>
                            <c:when test="${status.last}">
                                ${notice}
                            </c:when>
                            <c:otherwise>
                                ${notice}<br/>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:if>
            </div>
            <div class="modal-footer">
                <button id="warningConfirm" class="btn btn-primary"
                        data-dismiss="modal">确认
                </button>
                <button class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<c:if test="${!(empty notices)}">
    <script>
        $("#warningModal").modal("show");
    </script>
</c:if>

<jsp:include page="main-last.jsp" flush="true">
    <jsp:param name="url" value="index.jsp"/>
</jsp:include>

<h1 class="text-center">学生信息管理系统</h1>
<hr/>

<div class="form-inline">
    <div class="form-group">
        <a class="btn btn-info" href="add-student.jsp">新增</a>
        <button id="removeBtn" class="btn btn-info">删除</button>
        <button id="modifyBtn" class="btn btn-info">编辑</button>
        <button id="searchBtn" class="btn btn-info">查询</button>
    </div>

    <div class="form-group">
        <label for="numberOrNameSearch"
               class="sr-only">numberOrNameSearch</label>
        <input
                id="numberOrNameSearch"
                type="search"
                placeholder="请输入学号、学生姓名或者班级名查询"
                class="form-control"
        />
    </div>
</div>
<div style="margin-top: 10px; margin-bottom: 3px" class="btn-group">
    <button id="selectAll" class="btn btn-warning btn-xs">全选</button>
    <button id="selectNone" class="btn btn-warning btn-xs">全不选</button>
    <button id="selectReverse" class="btn btn-warning btn-xs">反选</button>
</div>

<div class="table-responsive">
    <table
            id="userList"
            class="table table-striped table-hover table-bordered"
    >
        <thead>
        <th class="text-center">选择</th>
        <th class="text-center">序号</th>
        <th class="text-center">学号</th>
        <th class="text-center">姓名</th>
        <th class="text-center">班级</th>
        <th class="text-center">性别</th>
        <th class="text-center">生日</th>
        <th class="text-center">邮箱</th>
        <th class="text-center">备注</th>
        </thead>
        <c:set var="list" value="${sessionScope.studentList}"/>
        <c:choose>
        <c:when test="${empty list}">
            <tr>
                <td colspan="5" class="lead">没有搜索到班级数据</td>
            </tr>
        </c:when>
        <c:otherwise>
        <c:set var="indexOfPage"
               value="${empty param.indexOfPage ? 1 : param.indexOfPage}"/>
        <c:set var="amountOfClassGrade"
               value="${list.size()}"/>
        <c:set var="amountOfPage"
               value="${Math.ceil(amountOfClassGrade / 50)}"/>
        <c:set var="times" value="${indexOfPage -1}"/>
        <c:set var="startOfList" value="${50 * times}"/>
        <c:set var="endOfList"
               value="${Math.min(50 * indexOfPage -1 , amountOfClassGrade -1)}"/>
        <tbody id="userInfoTbody">

        <c:forEach var="student" varStatus="id"
                   items="${list}" begin="${startOfList}"
                   end="${endOfList}">
            <tr>
                <td class="text-center">
                    <label class="sr-only">
                            ${student.studentId}
                    </label>
                    <input type="checkbox"/>
                </td>
                <td class="text-center">${id.index + 1}
                </td>
                <td class="text-center">${student.number}
                </td>
                <td class="text-center">${student.name}
                </td>
                <td class="text-center">
                    <a href="${'search-class-grade?keyword='.concat(student.classGrade.classGradeName)}">${student.classGrade.classGradeName}</a>
                </td>
                <td class="text-center">${student.gender}
                </td>
                <td class="text-center">${student.birthday}
                </td>
                <td class="text-center">${student.email}
                </td>
                <td class="text-center">${student.notes}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div>共搜索到${list.size()}条相关信息</div>
<ul class="pagination" style="margin-top: 3em">
    <c:choose>
        <c:when test="${indexOfPage == 1}">
            <li class="disabled">
                <span>
                <span aria-hidden="true">&laquo;</span>
                </span>
            </li>
        </c:when>
        <c:otherwise>
            <li>
                <a href="${'student-system.jsp?indexOfPage='.concat(indexOfPage - 1)}"
                   aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:otherwise>
    </c:choose>
    <c:forEach var="i" begin="1" end="${amountOfPage}">
        <c:choose>
            <c:when test="${i == indexOfPage}">
                <li class="active">
                    <span>${i} <span
                            class="sr-only"></span></span>
                </li>
            </c:when>
            <c:otherwise>
                <li>
                    <a href="${'student-system.jsp?indexOfPage='.concat(i)}">
                            ${i}
                    </a>
                </li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:choose>
        <c:when test="${indexOfPage == amountOfPage}">
            <li class="disabled">
                <span>
                <span aria-hidden="true">&raquo;</span>
                </span>
            </li>
        </c:when>
        <c:otherwise>
            <li>
                <a href="${'student-system-system.jsp?indexOfPage='.concat(indexOfPage + 1)}"
                   aria-label="Previous">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:otherwise>
    </c:choose>
</ul>
</c:otherwise>
</c:choose>
</body>
<script>
    $(function () {
        const warningModal = $("#warningModal");
        const userInfoTbody = $("#userInfoTbody");
        userInfoTbody.children("tr:even").addClass("info");
        userInfoTbody.children("tr:odd").addClass("warning");
        //search按钮的点击事件
        $("#searchBtn").click(function () {
            let keyword = $("#numberOrNameSearch").val();
            if (referenceIsEmpty(keyword)) {
                //搜索框里没有数据, 那就显示所有学生的信息
                location.href = "show-all-student";
            } else {
                //搜索框里有数据, 那就对该数据进行模糊搜索
                location.href = "search-student?keyword=" + keyword;
            }
        });

        function referenceIsEmpty(variate) {
            let isEmpty = variate == null || variate == undefined;
            if ("string" === typeof (variate)) {
                isEmpty = isEmpty || variate == "";
            }
            return isEmpty;
        }

        //提示模态框确认按钮的点击事件
        $("#warningConfirm").click(function () {
            let activatedByBtn = warningModal.data("activatedByBtn");
            if (!referenceIsEmpty(activatedByBtn) && "removeBtn" == activatedByBtn) {
                let selectedStudentIdArr = warningModal.data("selectedStudentIdArr");
                warningModal.removeData("activatedByBtn");
                warningModal.removeData("selectedStudentIdArr");
                location.href = "remove-student?selectedStudentIdArr=" + encodeURI(JSON.stringify(selectedStudentIdArr));
            }


            warningModal.modal("hide");
        });
        //remove按钮的点击事件
        $("#removeBtn").click(function () {
            let selectedStudentIdArr = getSelectedStudentIdArr();
            let selectedCount = selectedStudentIdArr.length;
            if (selectedCount < 1) {
                showWarningModal("请至少勾选一条要删除的信息");
            } else {
                warningModal.data("activatedByBtn", "removeBtn");
                warningModal.data("selectedStudentIdArr", selectedStudentIdArr);
                showWarningModal("确定要删除勾选的" + selectedCount + "条数据吗?");
            }
        });
        //modify按钮的点击事件
        $("#modifyBtn").click(function () {
            let selectedStudentIdArr = getSelectedStudentIdArr();
            let selectedCount = selectedStudentIdArr.length;
            if (selectedCount == 1) {
                location.href = "modify-student.jsp?" + "studentId=" + selectedStudentIdArr[0];
            } else if (selectedCount < 1) {
                showWarningModal("请勾选一条要编辑的信息");
            } else {
                showWarningModal(
                    "本程序无法同时编辑多条信息, 请只勾选一条您要编辑的信息!!!"
                );
            }

        });

        //显示提示信息的模态框
        function showWarningModal(warningInfo) {
            warningModal.find(".modal-body").text(warningInfo);
            warningModal.modal("show");
        }

        //获取用户选择行的索引组成的数组
        function getSelectedStudentIdArr() {
            let selectedClassGradeIdArr = [];
            let selectedLabelList = userInfoTbody
                .children()
                .find("input[type='checkbox']:checked")
                .prev();

            selectedLabelList.each(function () {
                selectedClassGradeIdArr.push($(this).text().trim());
            });
            console.log(selectedClassGradeIdArr);
            return selectedClassGradeIdArr;
        }

        //当点击复选框的父单元格时就会调用该复选框的click()方法, 相当于点击了复选框
        userInfoTbody.find("input[type='checkbox']").parent().click(function () {
            $(this).children("input[type='checkbox']").click();
        });

        //给每一个复选框添加一个点击事件, 当勾选上时, 为该行添加的颜色类".danger", 取消勾选时移除该颜色类
        $("input[type='checkbox']").click(function (event) {
            if (this.checked) {
                $(this).parent().parent().addClass("danger");
            } else {
                $(this).parent().parent().removeClass("danger");
            }

            //停止冒泡, 防止与父单元格的点击形成死循环
            event.stopPropagation();
        });

        //全选按钮的点击事件
        $("#selectAll").click(function () {
            userInfoTbody.find("input[type='checkbox']").not(":checked").click();
        });
        //全不选按钮的点击事件
        $("#selectNone").click(function () {
            userInfoTbody.find("input[type='checkbox']:checked").click();
        });

        //反选按钮的点击事件
        $("#selectReverse").click(function () {
            userInfoTbody.find("input[type='checkbox']").click();
        });
    });
</script>
</html>