<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>add a employee</title>
    <style type="text/css">
        form div {
            margin: 10px 0 0 0;
        }
        form {
            margin: 0 0 0 10px;
        }
    </style>
</head>
<body>
<h4>添加员工信息</h4>
<div>
    <form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">
        <div>
            <label>
                LastName:<form:input path="lastName"/>
            </label>
        </div>
        <div>
            <label>
                <%
                    Map<String, String> genders = new HashMap<>();
                    genders.put("0", "Female");
                    genders.put("1", "Male");
                    request.setAttribute("genders", genders);
                %>
                Gender:<form:radiobuttons path="gender" items="${requestScope.genders}"/>
            </label>
        </div>
        <div>
            <label>
                Email:<form:input path="email"/>
            </label>
        </div>
        <div>
            <label>
                Salary:<form:input path="salary"/>
            </label>
        </div>
        <div>
            <label>
                Birth:<form:input path="birth"/>
            </label>
        </div>
        <div>
            <label>
                Department:<form:select path="department.id" itemValue="id" items="${departments}" itemLabel="departmentName"/>
            </label>
        </div>
        <div>
            <input type="submit" value="提交">
        </div>
    </form:form>
</div>
</body>
</html>
