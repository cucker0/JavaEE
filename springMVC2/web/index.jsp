<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SpringMVC 2</title>
</head>
<body>
<p>
    <a href="springmvc/testRequestMapping">testRequestMapping</a>
</p>
<div>
    <form action="springmvc/testMethod" method="post">
        <input type="submit" value="testMethod post请求">
    </form>
</div>
<div>
    <form action="springmvc/testMethod" method="get">
        <input type="submit" value="testMethod get请求">
    </form>
</div>
<p>
    <a href="springmvc/testParams?username=王平&age=10">testParams 不符合要求参数</a>
</p>

<p>
    <a href="springmvc/testParams?username=王平&age=12">testParams 符合要求参数</a>
</p>

<p>
    <a href="springmvc/testHeaders">testHeaders </a>
</p>

<p>
    <a href="springmvc/a/xxyyzz/testAntPath">testAntPath </a>
</p>

<p>
    <a href="springmvc/testPathVariable/100">testPathVariable 路径变量 参数占位符</a>
</p>
</body>
</html>
