<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>success page</title>
</head>
<body>
    <div class="title">
        <h4>访问成功的页面</h4>
        <p>Referer: ${header.get("Referer")}</p>
        <p>Accept-Language: ${header.get("Accept-Language")}</p>
    </div>
</body>
</html>
