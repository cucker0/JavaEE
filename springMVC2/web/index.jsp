<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SpringMVC 2</title>
    <style>
        label {
            display: block;
            margin: 0 0 5px 0;
        }
        .pojo {
            margin: 0 0 0 20px;
        }
    </style>
</head>
<body>
<li>
    <a href="springmvc/testRequestMapping">test RequestMapping</a>
</li>
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
<li>
    <a href="springmvc/testParams?username=王平&age=10">test Params 不符合要求参数</a>
</li>

<li>
    <a href="springmvc/testParams?username=王平&age=12">test Params 符合要求参数</a>
</li>

<li>
    <a href="springmvc/testHeaders">test Headers </a>
</li>

<li>
    <a href="springmvc/a/xxyyzz/testAntPath">test AntPath </a>
</li>

<li>
    <a href="springmvc/testPathVariable/100">test PathVariable 路径变量 参数占位符</a>
</li>

<div>
    <li>RESTful</li>

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

</div>

<li>
    <a href="springmvc/testRequestParam?name=小图&age=26">test RequestParam</a>
</li>
<li>
    <a href="springmvc/testRequestParam?name=小图">test RequestParam 省略不必要的参数</a>
</li>

<li>
    <a href="springmvc/testRequestHeader">test RequestHeader</a>
</li>

<li>
    <a href="springmvc/testCookieValue">test CookieValue</a>
</li>

<div>
    <li>POJO 普通java bean对象</li>
    <form class="pojo" action="springmvc/testPojo" method="post">
        <label>
            username: <input type="text" name="username">
        </label>
        <label>
            password: <input type="text" name="password">
        </label>
        <label>
            age: <input type="number" name="age">
        </label>
        <label>
            email: <input type="text" name="email">
        </label>
        <label>
            <%-- 级联属性 --%>
            province: <input type="text" name="address.province">
        </label>
        <label>
            city: <input type="text" name="address.city">
        </label>

        <input type="submit" value="提交">
    </form>
</div>

<li>
    <a href="springmvc/testServletApi">test ServletApi</a>
</li>

<li>
    <a href="springmvc/testModelAndView">test ModelAndView</a>
</li>

<li>
    <a href="springmvc/testMap">test Map</a>
</li>

<li>
    <a href="testSessionAttributes">test sessionAttribute</a>
</li>


<div>
    <li>ModelAttribute</li>
    <form class="pojo" action="springmvc/testModelAttribute" method="post">
        <div>
            <input type="hidden" name="id" value="2">
        </div>
        <label>
            username: <input type="text" name="username" value="邝美云">
        </label>
        <label>
            age: <input type="number" name="age" value="33">
        </label>
        <label>
            email: <input type="text" name="email" value="kuangmy@gmail.com">
        </label>
        <label>
            <%-- 级联属性 --%>
            province: <input type="text" name="address.province" value="山西">
        </label>
        <label>
            city: <input type="text" name="address.city" value="太原">
        </label>

        <input type="submit" value="提交">
    </form>
</div>
</body>
</html>
