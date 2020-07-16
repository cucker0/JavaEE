Spring与Struts2整合
==

## 整合目的
```text
使用IOC容器来管理struts 2的action对象
```

## 整合步骤
* struts 2 jar包
    ```text
    struts 2官网 https://struts.apache.org/

    所需包
    asm-7.1.jar
    asm-analysis-7.1.jar
    asm-commons-7.1.jar
    asm-tree-7.1.jar
    commons-fileupload-1.4.jar
    commons-io-2.6.jar
    commons-lang3-3.8.1.jar
    commons-logging-1.2.jar
    dwr-1.1.1.jar
    freemarker-2.3.28.jar
    javassist-3.20.0-GA.jar
    log4j-api-2.12.1.jar
    ognl-3.1.26.jar
    struts2-core-2.5.22.jar
    struts2-spring-plugin-2.5.22.jar
    ```
* spring jar包
    ```text
    aopalliance-1.0.jar
    aspectjweaver-1.9.5.jar
    commons-logging-1.2.jar
    spring-aop-5.2.3.RELEASE.jar
    spring-aspects-5.2.3.RELEASE.jar
    spring-beans-5.2.3.RELEASE.jar
    spring-context-5.2.3.RELEASE.jar
    spring-context-support-5.2.3.RELEASE.jar
    spring-core-5.2.3.RELEASE.jar
    spring-expression-5.2.3.RELEASE.jar
    spring-instrument-5.2.3.RELEASE.jar
    spring-jcl-5.2.3.RELEASE.jar
    spring-jdbc-5.2.3.RELEASE.jar
    spring-jms-5.2.3.RELEASE.jar
    spring-messaging-5.2.3.RELEASE.jar
    spring-orm-5.2.3.RELEASE.jar
    spring-oxm-5.2.3.RELEASE.jar
    spring-test-5.2.3.RELEASE.jar
    spring-tx-5.2.3.RELEASE.jar
    spring-web-5.2.3.RELEASE.jar
    spring-webmvc-5.2.3.RELEASE.jar
    ```

* 添加spring配置文件
    ```text
    <!-- 注意：在IOC容器中配置struts 2的action时，必须配置scope="prototype"，不配置的情况默认为单例的
        action为非单例的，每个请求都一个新的action对象来处理 -->
    <bean id="personAction" class="com.java.spring.struts2.action.PersonAction"
          scope="prototype">
        <property name="personService" ref="personService"/>
    </bean>
    ```
    
* 加入struts 2配置
    ```text
    直接在src目录下创建 struts.xml文件，文件名必须为struts.xml
    ```
    **配置如下：**
    ```text
    <struts>
        <constant name="struts.enable.DynamicMethodInvocation" value="false"/>
        <constant name="struts.devMode" value="true"/>
        <package name="default" namespace="/" extends="struts-default">
            <!-- Spring整合struts2时，在struts2中配置spring的action的class时，需要指向IOC容器中该bean的id
            <action name="person-save"，使用 根路径/person-save 就能访问到此资源
            -->
            <action name="person-save" class="personAction">
                <result>/success.jsp</result>
            </action>
        </package>
    </struts>
    ```
    
    ![](image/add_struts_config.png)
    

## 整合的工作原理
```text
action实例先从spring的IOC容器中获取，如果没有再通过反射创建对象实例
```


struts2-spring-plugin jar包下的struts-spring.xml配置文件中：
```text
<struts>
    <bean type="com.opensymphony.xwork2.ObjectFactory" name="spring" class="org.apache.struts2.spring.StrutsSpringObjectFactory" />
```

struts2-core jar包下SpringObjectFactory的实现
```java
package com.opensymphony.xwork2.spring;

public class SpringObjectFactory extends ObjectFactory implements ApplicationContextAware {
    public Object buildBean(String beanName, Map<String, Object> extraContext, boolean injectInternal) throws Exception {
        Object o;
        if (this.appContext.containsBean(beanName)) {
            o = this.appContext.getBean(beanName);
        } else {
            Class beanClazz = this.getClassInstance(beanName);
            o = this.buildBean(beanClazz, extraContext);
        }

        if (injectInternal) {
            this.injectInternalBeans(o);
        }

        return o;
    }

}
```
