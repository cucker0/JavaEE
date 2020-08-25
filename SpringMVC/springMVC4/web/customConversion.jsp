<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>customConversion</title>
    <style type="text/css">
        .input-emp {
           width: 300px;
        }
    </style>
</head>
<body>
<div>
    <form action="customConversion" method="post">
        <label>
            字符串Employee：<input class="input-emp" name="employee" placeholder="lastName,gender,email,salary,birth,departmentId"
                               value="马苏,0,masu@qq.com,60000.0,1981-02-17,1">
        </label>
        <div>
            <input type="submit" value="提交">
        </div>
    </form>
</div>
</body>
</html>
