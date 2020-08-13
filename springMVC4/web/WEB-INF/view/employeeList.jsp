<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>employee List</title>
    <style>
        div table {
            width: 800px;
            /*margin: 0 auto;*/
            border-collapse: collapse; /* 合并表格边距 */
        }

        td, th {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<div>
    <c:choose>
        <c:when test="${empty requestScope.employees}">
            <h4>没有任何员工信息</h4>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                    <th>ID</th>
                    <th>LastName</th>
                    <th>Gender</th>
                    <th>Email</th>
                    <th>Salary</th>
                    <th>Birth</th>
                    <th>Department</th>
                    <th colspan="2">Operation</th>
                </thead>
                <c:forEach var="e" items="${requestScope.employees}">
                    <tr>
                        <td>${e.id}</td>
                        <td>${e.lastName}</td>
                        <td>${e.gender == 0 ? "女" : "男"}</td>
                        <td>${e.email}</td>
                        <td>${e.salary}</td>
                        <td>${e.birth}</td>
                        <td>${e.department.departmentName}</td>
                        <td><a href="emp/${e.id}">Edit</a></td>
                        <td><a href="emp/${e.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</div>
<div>
    <a href="emp">添加员工</a>
</div>
</body>
</html>
