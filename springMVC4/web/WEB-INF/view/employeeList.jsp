<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    // URL格式：
    // https://ip:port/工程名/
    String baseUrl = "";
    String host = request.getServerName();
    if (request.getScheme().equalsIgnoreCase("https") && request.getServerPort() == 443) {
        baseUrl = String.format("%s://%s%s/", request.getScheme(), host, request.getContextPath());
    } else if (request.getScheme().equalsIgnoreCase("http") && request.getServerPort() == 80) {
        baseUrl = String.format("%s://%s%s/", request.getScheme(), host, request.getContextPath());
    } else {
        baseUrl = String.format("%s://%s:%s%s/", request.getScheme(), host, request.getServerPort(), request.getContextPath());
    }
    request.setAttribute("baseUrl", baseUrl);
%>

<html>
<head>
    <title>employee List</title>
    <style>
        div table {
            width: 800px;
            /*margin: 0 auto;*/
            border-collapse: collapse; /* 合并表格边距 */
        }

        td, th {
            border: 1px solid black;
        }
    </style>
    <script src="${requestScope.baseUrl}static/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        // DOM就绪后
        $(function () {
            $("a.delete").click(function () {
                var path = $(this).attr("href");
                var url = '${requestScope.get("baseUrl")}' + path;
                $.ajax({
                    // 提交数据的类型 POST、GET、PUT、DELETE
                    type: "POST",
                    // 提交的网址
                    url: url,
                    // 提交的数据
                    data: {'_method': 'DELETE'},
                    // 返回数据的格式
                    datatype: "html",//"xml", "html", "script", "json", "jsonp", "text".
                    // 在请求之前调用的函数
                    // beforeSend: function(){
                    //
                    // },
                    // 成功返回之后调用的函数
                    success: function(data){
                        location.reload();
                    }   ,
                    // // 调用执行后调用的函数
                    // complete: function(XMLHttpRequest, textStatus){
                    //     //HideLoading();
                    // },
                    // 调用出错执行的函数
                    error: function(){
                        // 请求出错处理
                        location.reload();
                    }
                });
                // 阻止默认事件
                return false;
            });


            // 使用form表单发送post请求代替delete请求
            // $(".delete").click(function(){
            //     var href = $(this).attr("href");
            //     $("form").attr("action", href).submit();
            //     return false;
            // });
        });
    </script>
</head>
<body>
<div>
<%--    <form action="" method="POST">--%>
<%--        <input type="hidden" name="_method" value="DELETE"/>--%>
<%--    </form>--%>

    <c:choose>
        <c:when test="${empty requestScope.employees}">
            <h4>没有任何员工信息</h4>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                    <th>ID</th>
                    <th>LastName</th>
                    <th>Gender</th>
                    <th>Email</th>
                    <th>Salary</th>
                    <th>Birth</th>
                    <th>Department</th>
                    <th colspan="2">Operation</th>
                </thead>
                <c:forEach var="e" items="${requestScope.employees}">
                    <tr>
                        <td>${e.id}</td>
                        <td>${e.lastName}</td>
                        <td>${e.gender == 0 ? "女" : "男"}</td>
                        <td>${e.email}</td>
                        <td>${e.salary}</td>
                        <td>${e.birth}</td>
                        <td>${e.department.departmentName}</td>
                        <td><a href="emp/${e.id}">Edit</a></td>
                        <td><a class="delete" href="emp/${e.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</div>
<div>
    <a href="emp">添加员工</a>
</div>
</body>
</html>
