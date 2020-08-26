<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>testHttpMessageConverter</title>
</head>
<body>
<h4>test HttpMessageConverter(查看上传文件内容的body内容)</h4>
<div>
    <form action="testHttpMessageConverter" method="post" enctype="multipart/form-data">
        <div>
            File:<input type="file" name="file">
        </div>
        <div>
            Desc:<input type="text" name="desc">
        </div>
        <input type="submit" value="提交">
    </form>
</div>
</body>
</html>
