<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>i18n en_US</title>
</head>
<body>
<div>
    <div>
        <a href="i18n?locale=zh_CN">中文</a>
        <a href="i18n?locale=en_US">English</a>
    </div>
    <div>
        <p><fmt:message key="i18n.username"/></p>
        <p><fmt:message key="i18n.password"/></p>
    </div>
</div>
</body>
</html>
