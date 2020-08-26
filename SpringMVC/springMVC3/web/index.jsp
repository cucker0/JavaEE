<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SpringMVC 3</title>
</head>
<body>
<div>
    <li>
        <a href="login">login page</a>
    </li>
    <li>
        <a href="mylogin">配置直接转发的页面 mylogin</a>
    </li>
    <li>
        <a href="testHttpMessageConverter.jsp">test HttpMessageConverter(上传文件，查看表单的内容)</a>
    </li>
    <li>
        <a href="testHttpEntity">test HttpEntity</a>
    </li>
    <li>
        <a href="testResponseEntiry">test ResponseEntity(下载文件)</a>
    </li>
    <li>
        <a href="i18n">i18n国际化</a>
    </li>
    <li>
        <a href="file-upload.jsp">上传文件</a>
    </li>
    <li>
        <a href="testExceptionHandlerExceptionResolver?num=1">test ExceptionHandlerExceptionResolver</a>
        <br>
        <a href="testExceptionHandlerExceptionResolver?num=0">test ExceptionHandlerExceptionResolver 2</a>
    </li>
    <li>
        <a href="testResponseStatusExcptionResover?username=admin&code=3344">test ResponseStatusExcptionResover</a>
        <br>
        <a href="testResponseStatusExcptionResover?username=admin&code=1010">test ResponseStatusExcptionResover2</a>
        <br>
        <a href="testResponseStatusExcptionResover?username=qq&code=3344">test ResponseStatusExcptionResover3</a>
    </li>
    <li>
        <a href="testDefaultHandlerExceptionResolver">Test DefaultHandlerExceptionResolver</a>
    </li>
    <li>
        <a href="testSimpleMappingExceptionResolver?index=1">Test SimpleMappingExceptionResolver</a>
        <br>
        <a href="testSimpleMappingExceptionResolver?index=4">Test SimpleMappingExceptionResolver 2</a>

    </li>
</div>
</body>
</html>
