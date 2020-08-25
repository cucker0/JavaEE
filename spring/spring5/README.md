web中使用Spring
==

## 操作步骤
1. 加入额外的jar包
    ```text
    spring-web
    spring-webmvc
    ```

2. 在web.xml中配置spring配置文件的位置和文件名
    [web.xml](web/WEB-INF/web.xml)
    
3. 创建IOC容器
    ```text
    在WEB应用被加载到服务器时就创建IOC容器
    
    在ServletContextListener监听器的contextInitialized(ServletContextEvent sce)方法中，
    创建IOC容器，并保存到servletContext(application域对象)的一个属性中
    ```
    [SpringServletContextListener](src/com/java/spring/struts2/listenner/SpringServletContextListener.java)  

4. servlet中获取IOC，并从IOC中获取bean对象
    [PersonServlet](src/com/java/spring/struts2/servlets/PersonServlet.java)
5. 配置tomat,并访问测试