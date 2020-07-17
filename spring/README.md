# Spring


## 什么是Spring
```text
Spring是一个开源的java框架。
Spring为简化企业级应用开发而生。
Spring是一个IOC(DI)和AOP容器框架。

特点：
* 轻量级
    Spring是非侵入性的，基于spring开发的应用，其对象可以不依赖spring的API
* 依赖注入
    DI：dependency injection，依赖注入、
    IOC：Inversion of Control，控制反转
* 面向切面编程
    AOP：aspect oriented programming
* 容器
    Spring 是一个容器, 因为它包含并且管理应用对象的生命周期
* 框架
    Spring 实现了使用简单的组件配置组合成一个复杂的应用. 在 Spring 中可以使用 XML 和 Java 注解组合这些对象
* 一站式
    在 IOC 和 AOP 的基础上可以整合各种企业应用的开源框架和优秀的第三方类库 
    （实际上 Spring 自身也提供了展现层的 SpringMVC 和 持久层的 Spring JDBC）
```

spring jar包下载
```text
https://repo.spring.io/webapp/#/artifacts/browse/tree/General/libs-release-local/org/springframework/spring/5.2.7.RELEASE/spring-5.2.7.RELEASE-dist.zip
```

* spring模块
![](../images/spring/spring模块.png)

## idea创建spring工程
* 新建工程
    ![](../images/spring/idea新建spring项目01.png)  
    
    ![](../images/spring/idea新建spring项目02.png)  

    spring jar包
    ```text
    aopalliance-1.0.jar
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
    ```

* 创建bean类或其他类
    [User bean类](../spring/src/com/java/first/bean/User.java)

* 新建spring配置文件
    ![](../images/spring/新建spring配置文件.png)  
    
    在spring配置文件[spring-config.xml](../spring/src/spring-config.xml)中配置bean
    ```text
    <!-- 配置bean -->
    <bean id="user" class="com.java.first.bean.User">
        <!-- 通过类中的setter方法设置属性值 -->
        <property name="name" value="赵敏"/>
        <property name="age" value="18"/>
        <property name="sex" value="0"/>
    </bean>
    ```

* 测试
    [测试用例 testUser()](../spring/src/com/java/first/www/Main.java)


## spring中的bean配置

### IOC容器(DI容器)
* IOC(Inversion of Control)
```text
反转控制

其思想是反转资源获取的方向. 
传统的资源查找方式要求组件向容器发起请求查找资源. 
作为回应, 容器适时的返回资源. 而应用了 IOC 之后, 
则是容器主动地将资源推送给它所管理的组件, 
组件所要做的仅是选择一种合适的方式来接受资源. 这种行为也被称为查找的被动形式
```

* DI(Dependency Injection)
```text
依赖注入

OC 的另一种表述方式：即组件以一些预先定义好的方式(例如: setter 方法)接受来自如容器的资源注入. 
相对于 IOC 而言，这种表述更直接
```

### 配置bean
* 配置形式
    * 基于xml文件
    * 基于注解

* bean配置方式
    * 通过全类型(反射)
    * 通过工厂方法(静态工厂方法 & 实例工厂方法)
    * FactoryBean
* IOC 容器 BeanFactory & ApplicationContext 概述
* 依赖注入的方法
    * 属性注入
    * 构造器注入
* 自动装配
* bean之间的关系
    * 继承
    * 依赖
* bean的作用域
    * singleton
    * prototype(每次调用时生成一个新的对象)
    * web环境作用域
* 使用外部属性文件
* spEL(spring表达式)
* IOC容器中bean实例的生命周期
* spring 5.x 型特性：泛型依赖注入


#### 基于xml文件配置bean，即spring配置文件配置bean
```text
    <!-- 配置bean -->
    <bean id="user" class="com.java.first.bean.User">
        <!-- 通过类中的setter方法设置属性值 -->
        <property name="name" value="赵敏"/>
        <property name="age" value="18"/>
        <property name="sex" value="0"/>
    </bean>
```

规则
```text
id：Bean 的名称

* 在 IOC 容器中必须是唯一的
* 若 id 没有指定，Spring 自动将权限定性类名作为 Bean 的名字，即首字符小写的类名
* id 可以指定多个名字，名字之间可用逗号、分号、或空格分隔
```

### spring IOC容器
```text
在 Spring IOC 容器读取 Bean 配置创建 Bean 实例之前, 必须对它进行实例化. 只有在容器实例化后, 才可以从 IOC 容器里获取 Bean 实例并使用.

Spring 提供了两种类型的 IOC 容器实现.
    * BeanFactory: IOC 容器的基本实现.
    * ApplicationContext: 提供了更多的高级特性. 是 BeanFactory 的子接口.

    * BeanFactory 是 Spring 框架的基础设施，面向 Spring 本身；ApplicationContext 面向使用 Spring 框架的开发者，
    几乎所有的应用场合都直接使用 ApplicationContext 而非底层的 BeanFactory
    * 无论使用何种方式, 配置文件时相同的
```

### ApplicationContext
```text
ApplicationContext 的主要实现类：
    * ClassPathXmlApplicationContext：从 类路径下加载配置文件
    * FileSystemXmlApplicationContext: 从文件系统中加载配置文件

ConfigurableApplicationContext 扩展于 ApplicationContext，
    新增加两个主要方法：refresh() 和 close()， 让 ApplicationContext 具有启动、刷新和关闭上下文的能力
    
ApplicationContext 在初始化上下文时就实例化所有单例的 Bean。

WebApplicationContext
    是专门为 WEB 应用而准备的，它允许从相对于 WEB 根目录的路径中完成初始化工作
```

### 从IOC容器中获取bean实例
调用ApplicationContext的getBean方法

BeanFactory方法
![](../images/spring/BeanFactory方法.png)

[IOC容器中获取bean testCar()](src/com/java/first/www/Main.java)


### 依赖注入的方式
* 属性注入
* 构造器注入
* 工厂方法注入（不推荐）

* 属性注入
    ```text
    属性注入即通过 setter 方法注入Bean 的属性值或依赖的对象
    
    属性注入使用 <property> 元素, 使用 name 属性指定 Bean 的属性名称，
    value 属性或 <value> 子节点指定属性值
    ```
    ```text
        <bean id="user" class="com.java.first.bean.User">
            <!-- 通过类中的setter方法设置属性值 -->
            <property name="name" value="赵敏"/>
            <property name="age" value="18"/>
            <property name="sex" value="0"/>
        </bean>
    ```

* 构造器注入
    ```text
    通过构造方法注入Bean 的属性值或依赖的对象，它保证了 Bean 实例在实例化后就可以使用。
    
    构造器注入在 <constructor-arg> 元素里声明属性, <constructor-arg> 中没有 name 属性
    
    字面值：可用字符串表示的值，可以通过 <value> 元素标签或 value 属性进行注入。
    基本数据类型及其封装类、String 等类型都可以采取字面值注入的方式
    若字面值中包含特殊字符，可以使用 <![CDATA[]]> 把字面值包裹起来。  
    ```
    ```text
    <!-- 通过构造器设置属性值 -->
    <bean id="car" class="com.java.first.bean.Car">
        <!-- 当不指定index、type等时，值顺序要与构造器的形参一致，值类型也要对应 -->
        <constructor-arg value="奥迪"/>
        <constructor-arg value="德国英戈尔施塔特"/>
        <constructor-arg value="220"/>
        <constructor-arg value="689800.00"/>
    </bean>
  
    <!-- 若bean类有多个构造器时，可以通过指定index、type等来更加精确的定位值与形参的对应关系 -->
    <bean id="car2" class="com.java.first.bean.Car">
        <constructor-arg value="200" index="2"/>
        <constructor-arg value="比亚迪" index="0"/>
        <constructor-arg value="中国深圳" index="1"/>
    </bean>
    <bean id="car3" class="com.java.first.bean.Car">
        <constructor-arg value="8" index="2" type="float"/>
        <constructor-arg value="比亚迪" index="0"/>
        <constructor-arg value="中国深圳" index="1"/>
    </bean>
    <bean id="car4" class="com.java.first.bean.Car">
        <!-- 属性值中包含特殊字符的设置方法 -->
        <!-- ![CDATA[属性值]] -->
        <constructor-arg value="30" index="2" type="float"/>
        <constructor-arg index="0">
            <value><![CDATA[红旗<$$>]]></value>
        </constructor-arg>
        <constructor-arg value="中国吉林" index="1"/>
    </bean>
    <bean id="car4" class="com.java.first.bean.Car">
        <!-- 属性值中包含特殊字符的设置方法 -->
        <!-- ![CDATA[属性值]] -->
        <constructor-arg value="30" index="2" type="float"/>
        <constructor-arg index="0">
            <value><![CDATA[红旗<$$>]]></value>
        </constructor-arg>
        <constructor-arg value="中国吉林" index="1"/>
    </bean>  
    ```
### 引用其他bean
```text
在 Bean 的配置文件中, 可以通过 <ref> 元素或 ref  属性为 Bean 的属性或构造器参数指定对 Bean 的引用. 
也可以在属性或构造器里包含 Bean 的声明, 这样的 Bean 称为内部 Bean

```
配置示例
```text
<bean id="dao2" class="com.java.ref.bean.Dao"></bean>
<bean id="service" class="com.java.ref.bean.Service">
    <!-- 引用已经存在的实例作为属性值 -->
    <property name="dao" ref="dao2">
    </property>
</bean>
```

### 内部bean
```text
当 Bean 实例仅仅给一个特定的属性使用时, 可以将其声明为内部 Bean. 
内部 Bean 声明直接包含在 <property> 或 <constructor-arg> 元素里, 
不需要设置任何 id 或 name 属性

内部 Bean 不能使用在任何其他地方
```
示例
```text
<bean id="service2" class="com.java.ref.bean.Service">
    <property name="dao">
        <!-- 内部bean实例对象，不能被外部的bean引用，也没必须设置id属性 -->
        <bean class="com.java.ref.bean.Dao">
            <property name="dataSource" value="c3p0"/>
        </bean>
    </property>
</bean>
```

### null值属性
可以使用专用的 <null/> 元素标签为 Bean 的字符串或其它对象类型的属性注入 null 值

示例
```text
<bean id="dao" class="com.java.ref.bean.Dao">
    <!-- 设置属性值为null -->
    <property name="dataSource">
        <null/>
    </property>
</bean>
```

### 级联属性
```text
<bean id="web" class="com.java.ref.bean.Web">
    <property name="service" ref="service2"/>
    <!-- 设置级联属性值 -->
    <property name="service.dao.dataSource" value="DBCP2"/>
</bean>
```

### 集合属性
```text
* 在 Spring中可以通过一组内置的 xml 标签(例如: <list>, <set> 或 <map>) 来配置集合属性.

* 配置 java.util.List 类型的属性, 需要指定 <list>  标签, 在标签里包含一些元素. 这些标签可以通过 <value> 指定简单的常量值, 通过 <ref> 指定对其他 Bean 的引用. 通过<bean> 指定内置 Bean 定义. 通过 <null/> 指定空元素. 甚至可以内嵌其他集合.
数组的定义和 List 一样, 都使用 <list>

* 配置 java.util.Set 需要使用 <set> 标签, 定义元素的方法与 List 一样.

* Java.util.Map 通过 <map> 标签定义, <map> 标签里可以使用多个 <entry> 作为子标签. 每个条目包含一个键和一个值. 

* 必须在 <key> 标签里定义键

* 因为键和值的类型没有限制, 所以可以自由地为它们指定 <value>, <ref>, <bean> 或 <null> 元素. 

* 可以将 Map 的键和值作为 <entry> 的属性定义: 简单常量使用 key 和 value 来定义; Bean 引用通过 key-ref 和 value-ref 属性定义

* 使用 <props> 定义 java.util.Properties, 该标签使用多个 <prop> 作为子标签. 每个 <prop> 标签必须定义 key 属性.

```

```text
<!-- 设置集合属性 -->
<bean id="user0" class="com.java.first.bean.User">
    <!-- 通过类中的setter方法设置属性值 -->
    <property name="name" value="赵敏"/>
    <property name="age" value="18"/>
    <property name="sex" value="0"/>
    <property name="cars">
        <!-- 使用list标签来设置集合属性值 -->
        <list>
            <ref bean="car11"/>
            <ref bean="car12"/>
        </list>
    </property>
</bean>

<!-- 声明集合类型的bean -->
<util:list id="cars">
    <ref bean="car11"/>
    <ref bean="car12"/>
    <bean class="com.java.first.bean.Car">
        <constructor-arg value="16" index="2" type="float"/>
        <constructor-arg value="丰田" index="0"/>
        <constructor-arg value="日本" index="1"/>
    </bean>
</util:list>
```

### 使用p命名空间
```text
Spring 从 2.5 版本开始引入了一个新的 p 命名空间，可以通过 <bean> 元素属性的方式配置 Bean 的属性。
使用 p 命名空间，是对property标签的简写
```

```text
<!-- 使用P命名空间 -->
<bean id="user3" class="com.java.first.bean.User"
      p:name="白眉鹰王"
      p:age="80"
      p:sex="1"
      p:cars-ref="cars">
</bean>
```

### 继承bean配置
```text
* Spring 允许继承 bean 的配置, 被继承的 bean 称为父 bean. 继承这个父 Bean 的 Bean 称为子 Bean
* 子 Bean 从父 Bean 中继承配置, 包括 Bean 的属性配置
* 子 Bean 也可以覆盖从父 Bean 继承过来的配置
* 父 Bean 可以作为配置模板, 也可以作为 Bean 实例. 若只想把父 Bean 作为模板, 可以设置 <bean> 的abstract 属性为 true, 这样 Spring 将不会实例化这个 Bean
* 并不是 <bean> 元素里的所有属性都会被继承. 比如: autowire, abstract 等.
* 也可以忽略父 Bean 的 class 属性, 让子 Bean 指定自己的类, 而共享相同的属性配置. 但此时 abstract 必须设为 true

```
示例
```text
<!-- 使用 parent属性 来完成实例之间的继续
    所有属性都会继承过来，如有指定其他的属性值，则会覆盖
-->
<bean id="user4"
      parent="user0"
      p:name="杨逍">
</bean>
```

### xml配置bean自动装配
```text
* Spring IOC 容器可以自动装配 Bean. 需要做的仅仅是在 <bean> 的 autowire 属性里指定自动装配的模式

* byType(根据类型自动装配): 若 IOC 容器中有多个与目标 Bean 类型一致的 Bean. 在这种情况下, Spring 将无法判定哪个 Bean 最合适该属性, 所以不能执行自动装配.

* byName(根据名称自动装配): 必须将目标 Bean 的名称和属性名设置的完全相同.

* constructor(通过构造器自动装配): 当 Bean 中存在多个构造器时, 此种自动装配方式将会很复杂. 不推荐使用

**缺点**
在 Bean 配置文件里设置 autowire 属性进行自动装配将会装配 Bean 的所有属性. 然而, 若只希望装配个别属性时, autowire 属性就不够灵活了. 
autowire 属性要么根据类型自动装配, 要么根据名称自动装配, 不能两者兼而有之.

```

### 继承bean配置
```text
* Spring 允许继承 bean 的配置, 被继承的 bean 称为父 bean. 继承这个父 Bean 的 Bean 称为子 Bean

* 子 Bean 从父 Bean 中继承配置, 包括 Bean 的属性配置

* 子 Bean 也可以覆盖从父 Bean 继承过来的配置

* 父 Bean 可以作为配置模板, 也可以作为 Bean 实例. 若只想把父 Bean 作为模板, 可以设置 <bean> 的abstract 属性为 true, 这样 Spring 将不会实例化这个 Bean

* 并不是 <bean> 元素里的所有属性都会被继承. 比如: autowire, abstract 等.

* 也可以忽略父 Bean 的 class 属性, 让子 Bean 指定自己的类, 而共享相同的属性配置. 但此时 abstract 必须设为 true
```
示例
```text
<!-- 使用 parent属性 来完成实例之间的继续
    所有属性都会继承过来，如有指定其他的属性值，则会覆盖
-->
<bean id="user4"
      parent="user0"
      p:name="杨逍">
</bean>
```

### 依赖bean配置
```text
* Spring 允许用户通过 depends-on 属性设定 Bean 前置依赖的Bean，前置依赖的 Bean 会在本 Bean 实例化之前创建好

* 如果前置依赖于多个 Bean，则可以通过逗号，空格或的方式配置 Bean 的名称
```
```text
<!-- depents-on属性 -->
<bean id="user5" depends-on="user00"
      parent="user0" p:name="石破天">
</bean>
```


### scope属性配置bean的作用域
```text
* 在 Spring 中, 可以在 <bean> 元素的 scope 属性里设置 Bean 的作用域. 

* 默认情况下, Spring 只为每个在 IOC 容器里声明的 Bean 创建唯一一个实例, 
    整个 IOC 容器范围内都能共享该实例：所有后续的 getBean() 调用和 Bean 引用都将返回这个唯一的 Bean 实例.
    该作用域被称为 singleton, 它是所有 Bean 的默认作用域.
```

**scope属性值可选值**

scope属性 | 说明
:--- |:---
singleton |默认值，不显式写scope属性值时的值，<br>在spring IOC容器中只保存一个bean实例，bean以单实例存在
prototype |每次调用getBean方法获取bean实例时，都会生成一个新的实例返回  
request |每次HTTP请求都会创建一个新的bean实例，<br>该作用域仅适用于WebApplicationContext环境 
session |同一个HTTP Session共享一个bean实例，不同的session使用不同的bean实例，<br>该作用域仅适用于WebApplicationContext环境 


### 使用外部属性文件
```text
在配置文件里配置 Bean 时, 有时需要在 Bean 的配置里混入系统部署的细节信息(例如: 文件路径, 数据源配置信息等). 
而这些部署细节实际上需要和 Bean 配置相分离

Spring 提供了一个 PropertyPlaceholderConfigurer 的 BeanFactory 后置处理器,
这个处理器允许用户将 Bean 配置的部分内容外移到属性文件中. 可以在 Bean 配置文件里使用形式为 ${var} 的变量, 
PropertyPlaceholderConfigurer 从属性文件里加载属性, 并使用这些属性来替换变量.
```