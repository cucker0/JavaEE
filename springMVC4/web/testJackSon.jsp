<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test JackSon</title>
    <script src="static/js/jquery-3.4.1.min.js"></script>
    <script>
        function testJackSon() {
            $.getJSON("${pageContext.request.contextPath}/testJson", function (data) {
                console.log(data);
                var html = "";
                data.forEach(function (item, index) {
                    var node = "<li>";
                    node += item["lastName"] + ", " + item["gender"] + ", " + item["email"] + ", ￥" +  item["salary"] + ", "
                        + item["birth"]
                        + ", " + item["department"]["departmentName"];
                    node += "</li>";
                    html += node;
                    console.log(item);
                });
                $("#emplist").html(html);
            });
        }


        $(function () {
            $(".jackson").click(testJackSon);
        });
    </script>
</head>
<body>
<div>
    <button class="jackson">测试JackSon，F12查看浏览器控制台</button>
    <div id="emplist"></div>
</div>
</body>
</html>
