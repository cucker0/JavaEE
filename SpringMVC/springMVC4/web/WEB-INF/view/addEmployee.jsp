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
        .form-error {
            color: red;
        }
    </style>
</head>
<body>
<h4>添加员工信息</h4>
<div>
    <form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">
<%--        <div>--%>
<%--            &lt;%&ndash; 显示所有的错误消息 &ndash;%&gt;--%>
<%--            <form:errors path="*"></form:errors>--%>
<%--        </div>--%>
        <div>
            <label>
                LastName:<form:input path="lastName"/>
                <form:errors path="lastName" cssClass="form-error"/>
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
                <form:errors path="email" cssClass="form-error"/>
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
                <form:errors path="birth" cssClass="form-error"/>
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
