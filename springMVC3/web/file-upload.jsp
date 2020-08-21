<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>file upload</title>
</head>
<body>
<div>
    <h4>上传文件</h4>
    <form action="upload" method="post" enctype="multipart/form-data">
        file: <input type="file" name="file">
        <br>
        <input type="submit" value="上传">
    </form>
</div>
</body>
</html>
