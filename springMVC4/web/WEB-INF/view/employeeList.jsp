<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>

<html>
<head>
    <title>employee List</title>
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
                    <th>Department</th>
                    <th>Operation</th>
                </thead>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
