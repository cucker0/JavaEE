<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>employee list</title>
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
    <table>
        <tr>
            <th>id</th>
            <th>LastName</th>
            <th>gender</th>
            <th>email</th>
        </tr>
        <c:forEach var="emp" items="${requestScope.emps}">
            <tr>
                <td>${emp.id}</td>
                <td>${emp.lastName}</td>
                <td>
                    <c:if test="${emp.gender == 1}">男</c:if>
                    <c:if test="${emp.gender == 0}">女</c:if>
                </td>
                <td>${emp.email}</td>
            </tr>
        </c:forEach>

    </table>

</div>
</body>
</html>
