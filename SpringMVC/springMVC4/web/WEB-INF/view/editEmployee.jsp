<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>edit employee</title>
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
<h4>更新员工信息</h4>
<div>
    <form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">
        <%-- 把POST请求转PUT请求 --%>
        <input type="hidden" name="_method" value="PUT">
        <form:hidden path="id"/>
        <div>
            LastName:${employee.lastName}
        </div>
        <div>
            <label>
                <%
                    Map<String, String> genders = new HashMap<>();
                    genders.put("0", "Female");
                    genders.put("1", "Male");
                    request.setAttribute("genders", genders);
                %>
                <%-- path属性必须在modelAttribute对应的bean对象的属性中必须有才能用 --%>
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
                Salary:<form:input path="salary"/>￥
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
