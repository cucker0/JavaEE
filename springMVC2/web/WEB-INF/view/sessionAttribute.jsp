<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sessionAttribute</title>
</head>
<body>
<div>
    request user: ${requestScope.user}
    <br>
    request phone: ${requestScope.phone}
</div>
<br>
<div>
    session user: ${sessionScope.user}
    <br>
    session phone: ${sessionScope.phone}
</div>
</body>
</html>
