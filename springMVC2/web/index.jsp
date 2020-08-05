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

<div>
    <h4>RESTful</h4>

    <div>
        <a href="springmvc/testRest/1">testRestful GET</a>
    </div>

    <div>
        <form action="springmvc/testRest" method="post">
            <input type="submit" value="testRestful POST">
        </form>
    </div>

    <div>
        <form action="springmvc/testRest/1" method="post">
            <%-- 告诉服务端把post请求方法转成 PUT请求方法 --%>
            <input type="hidden" name="_method" value="PUT">
            <input type="submit" value="testRestful PUT">
        </form>
    </div>

    <div>
        <form action="springmvc/testRest/1" method="post">
            <%-- 告诉服务端把post请求方法转成 DELETE请求方法 --%>
            <input type="hidden" name="_method" value="DELETE">
            <input type="submit" value="testRestful DELETE">
        </form>
    </div>

    <div>
        <a href="springmvc/testRequestParam?name=小图&age=26">testRequestParam</a>
    </div>
    <div>
        <a href="springmvc/testRequestParam?name=小图">testRequestParam 省略不必要的参数</a>
    </div>

    <div>
        <a href="springmvc/testRequestHeader">testRequestHeader</a>
    </div>
</div>

</body>
</html>
