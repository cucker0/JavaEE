<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>i18n login</title>
</head>
<body>
<div>
    <h4>i18n login page(根据浏览器的默认语言，会显示相应的语言)</h4>
    <div>
        <label>
            <fmt:message key="i18n.username"/>
        </label>
        <br>
        <label>
            <fmt:message key="i18n.password"/>
        </label>
    </div>
</div>
</body>
</html>
