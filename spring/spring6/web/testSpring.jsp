<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.java.spring.struts2.bean.Person" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test Spring</title>
</head>
<body>
<%
    // 从application域对象中获取IOC容器的引用
    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);

    // 从IOC容器中获取需要的bean对象
    Person person = ctx.getBean(Person.class);
    person.info();
    response.getWriter().write(System.currentTimeMillis() + ": " + person.toString());
%>
</body>
</html>
