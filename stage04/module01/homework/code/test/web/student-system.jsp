<%@ page import="com.zq.model.javabean.Student" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: zq007
  Date: 2021/5/10
  Time: 9:17
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
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>学生信息管理系统</title>
    <link rel="stylesheet" href="lib/css/bootstrap.min.css"/>
</head>
<style>
    th,
    td {
        text-align: center;
    }
</style>
<script src="lib/js/jquery-3.6.0.min.js"></script>
<script src="lib/js/bootstrap.min.js"></script>
<body style="max-width: 1200px; padding: 5px" class="center-block">
<!--提示信息的模态框-->
<%
    String notice = null;
    String lastOperate = (String) session.getAttribute("lastOperate");
    if (null != lastOperate) {
        switch (lastOperate) {
            case "add":
                notice = "添加学生成功";
                break;
            case "modify":
                notice = "编辑学生成功";
                break;
            case "remove":
                notice = generateRemoveNotice(session);
                break;
            default:
                break;
        }
    }
    session.removeAttribute("lastOperate");
    session.removeAttribute("result");

%>
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
                 class="modal-body text-center lead"><%=notice%>
            </div>
            <div class="modal-footer">
                <button id="warningConfirm" class="btn btn-primary">确认</button>
                <button class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<%
    if (null != notice) {
%>
<script>
    $("#warningModal").modal("show");
</script>
<%
    }
%>
<div class="text-right">
    <a class="btn btn-default" href="quit">退出</a>
</div>

<h1 class="text-center">学生信息管理系统</h1>
<hr/>

<div class="form-inline">
    <div class="form-group">
        <button id="addBtn" class="btn btn-info">新增</button>
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
                placeholder="请输入学号或者姓名查询"
                class="form-control"
        />
    </div>
</div>
<div style="margin-top: 10px; margin-bottom: 3px" class="btn-group">
    <button id="selectAllOrNone" class="btn btn-warning btn-xs">全选</button>
    <button id="selectReverse" class="btn btn-warning btn-xs">反选</button>
</div>

<%
    List<Student> studentList = (List<Student>) session.getAttribute("studentList");
    if (studentList != null && studentList.size() > 0) {
        int amountOfStudents = studentList.size();
        int amountOfPages = amountOfStudents % 20 == 0 ? amountOfStudents / 20 :
                amountOfStudents / 20 + 1;
        String indexOfPageStr = request.getParameter("indexOfPage");
        int indexOfPage = Integer.parseInt(indexOfPageStr == null ? "1" : indexOfPageStr);
%>
<table
        id="userList"
        class="table table-striped table-hover table-responsive table-bordered"
>
    <thead>
    <th>选择</th>
    <th>序号</th>
    <th>学号</th>
    <th>姓名</th>
    <th>性别</th>
    <th>生日</th>
    <th>邮箱</th>
    <th>备注</th>
    </thead>
    <tbody id="userInfoTbody">
    <%
        int times = indexOfPage - 1;
        int max = Math.min(20 * indexOfPage, amountOfStudents);
        for (int i = 20 * times; i < max; i++) {
            Student student = studentList.get(i);


    %>
    <tr>
        <td>
            <label class="sr-only">
                <%=student.getStudentId()%>
            </label>
            <input type="checkbox"/>
        </td>
        <td><%=i + 1%>
        </td>
        <td><%=student.getNumber()%>
        </td>
        <td><%=student.getName()%>
        </td>
        <td><%=student.getGender()%>
        </td>
        <td><%=student.getBirthday()%>
        </td>
        <td><%=student.getEmail()%>
        </td>
        <td><%=student.getNotes()%>
        </td>
    </tr>

    <%
        }
    %>
    </tbody>
</table>
<ul class="pagination">
    <%
        if (indexOfPage - 1 < 1) {
    %>
    <li class="disabled">
        <span>
        <span aria-hidden="true">&laquo;</span>
        </span>
    </li>
    <%
    } else {
    %>
    <li>
        <a href="student-system.jsp?indexOfPage=<%=indexOfPage-1%>"
           aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
        </a>
    </li>
    <%
        }
    %>

    <%
        for (int i = 1; i < amountOfPages + 1; i++) {
            if (i != indexOfPage) {
    %>
    <li><a href="student-system.jsp?indexOfPage=<%=i%>"><%=i%>
    </a></li>
    <%
    } else {
    %>
    <li class="active">
        <span><%=i%> <span class="sr-only">(current)</span></span>
    </li>
    <%
            }
        }
    %>
    <%
        if (indexOfPage + 1 > amountOfPages) {
    %>
    <li class="disabled">

        <span>
            <span aria-hidden="true">&raquo;</span>
        </span>
    </li>
            <%
    } else {
    %>
    <li>
        <a href="student-system.jsp?indexOfPage=<%=indexOfPage+1%>"
           aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
        </a>
    </li>
    <%
        }
    %>

</ul>
<%
} else {
%>
<p class="text-center lead">
    没有查询到相关数据
</p>
<%
    }
%>
</body>
<script>
    $(function () {
        //search按钮的点击事件
        $("#searchBtn").click(function () {
            let keyword = $("#numberOrNameSearch").val();
            if (referenceIsEmpty(keyword)) {
                //搜索框里没有数据, 那就显示所有学生的信息
                location.href = "show-all";
            } else {
                //搜索框里有数据, 那就对该数据进行模糊搜索
                location.href = "search?keyword=" + keyword;
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
                location.href = "remove?removeIdList=" + encodeURI(JSON.stringify(selectedStudentIdArr));
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
        $(`#modifyBtn`).click(function () {
            let selectedStudentIdArr = getSelectedStudentIdArr();
            let selectedCount = selectedStudentIdArr.length;
            if (selectedCount == 1) {
                location.href = "modify-ready?" + "studentId=" + selectedStudentIdArr[0];
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
            warningModal.modal(`show`);
        }

        //获取用户选择行的索引组成的数组
        function getSelectedStudentIdArr() {
            let selectedStudentIdArr = [];
            let allTrList = userInfoTbody.children();
            let selectedLabelList = userInfoTbody
                .children()
                .find(`input[type="checkbox"]:checked`)
                .prev();

            selectedLabelList.each(function (index) {
                selectedStudentIdArr.push($(this).text().trim());
            });
            console.log(selectedStudentIdArr);
            return selectedStudentIdArr;
        }

        //add按钮的点击事件
        $(`#addBtn`).click(function () {
            location.href = "add-student.jsp"
        });

        const warningModal = $(`#warningModal`);
        const userInfoTbody = $("#userInfoTbody");
        userInfoTbody.children("tr:even").addClass("info");
        userInfoTbody.children("tr:odd").addClass("warning");

        //当点击复选框的父单元格时就会调用该复选框的click()方法, 相当于点击了复选框
        userInfoTbody.find("input[type='checkbox']").parent().click(function () {
            $(this).children("input[type='checkbox']").click();
        });

        //给每一个复选框添加一个点击事件, 当勾选上时, 为该行添加的颜色类".danger", 取消勾选时移除该颜色类
        $(`input[type="checkbox"]`).click(function (event) {
            if (this.checked) {
                $(this).parent().parent().addClass("danger");
            } else {
                $(this).parent().parent().removeClass("danger");
            }

            let allCheckboxes = $("input[type='checkbox']");
            let checkedBoxes = allCheckboxes.filter(":checked");

            if (allCheckboxes.length == checkedBoxes.length) {
                $("#selectAllOrNone").text("全不选");
            } else {
                $("#selectAllOrNone").text("全选");
            }
            //停止冒泡, 防止与父单元格的点击形成死循环
            event.stopPropagation();
        });

        //全选/全不选按钮的点击事件
        $("#selectAllOrNone").click(function () {
            let selectStatus = $(this).text();
            if ("全选" == selectStatus) {
                userInfoTbody.find("input[type='checkbox']").prop("checked", true).parent().parent().addClass("danger");
                $(this).text("全不选");
            } else {
                userInfoTbody.find("input[type='checkbox']").prop("checked", false).parent().parent().removeClass("danger");
                $(this).text("全选");
            }
        });

        //反选按钮的点击事件
        $("#selectReverse").click(function () {
            let allCheckboxes = userInfoTbody.find("input[type='checkbox']");
            let checkedCount = 0;
            allCheckboxes.each(function (index) {
                let checked = $(this).prop("checked");
                checked = checked == true;
                $(this).prop("checked", !checked);
                if (!checked) {
                    $(this).parent().parent().addClass("danger");
                } else {
                    $(this).parent().parent().removeClass("danger");
                }

                checkedCount += !checked == true ? 1 : 0;
            });

            if (allCheckboxes.length == checkedCount) {
                $("#selectAllOrNone").text("全不选");
            } else {
                $("#selectAllOrNone").text("全选");
            }
        });
    });
</script>
</html>
<%!
    private String generateRemoveNotice(HttpSession session) {
        int amount = (Integer) session.getAttribute("removeAmount");
        int successCount = (Integer) session.getAttribute("removeSuccessCount");
        session.removeAttribute("removeAmount");
        session.removeAttribute("removeSuccessCount");
        return "您勾选了" + amount + "条信息, 成功删除" + successCount + "条信息";
    }
%>