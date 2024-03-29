SpringBoot框架中各层（DTO、DAO、Service、Controller）理解
==


## 粗略理解
```text
View层   Controller层（响应用户请求）
Service层    （接口接口实现类）
DAO层，   即Mapper层（抽象类：xxxMapper.java文件，具体实现在xxxMapper.xml）
Model层  （实体类：xxx.java）
```

## VO、DTO、DO、PO理解

VO：View Object，视图层，其作用是将指定页面的展示数据封装起来。

DTO：Data Transfer Object，数据传输对象

DO：Domain Object，领域对象

PO：Persistent Object，持久化对象

### 模型
用户发出请求（填写表单），表单的数据被展示层匹配为VO

展示层把VO转换为服务层对应方法所要求的DTO，提交给服务层

服务层先将DTO的数据构造（或重建）一个DO，调用DO的业务方法完成具体业务

服务层再将DO转换为持久层对应的PO，调用持久层的持久化方法，把PO传递持久化方法，完成持久化操作


## PO、VO、BO、DTO、DO、POJO、JavaBean、JavaBeans

* PO：持久对象 (persistent object)

    po(persistent object)就是在Object/Relation Mapping框架中的Entity，po的每个属性基本上都对应数据库表里面的某个字段。完全是一个符合Java Bean规范的纯Java对象，没有增加别的属性和方法。持久对象是由insert数据库创建，由数据库delete删除的。基本上持久对象生命周期和数据库密切相关。

* VO： 表现层对象(View Object)

    主要对应展示界面显示的数据对象，用一个VO对象来封装整个界面展示所需要的对象数据，数据脱敏，去掉用户隐私数据。

* BO：业务对象层的缩写(Business Object)

    封装业务逻辑的java对象，通过调用DAO方法，结合PO,VO进行业务操作。具体可以看网上的一个例子：

    比如一个简历，有教育经历、工作经历、社会关系等等。
    我们可以把教育经历对应一个PO，工作经历对应一个PO，社会关系对应一个PO。
    建立一个对应简历的BO对象处理简历，每个BO包含这些PO。
    这样处理业务逻辑时，我们就可以针对BO去处理。

* DTO：数据传输对象(Data Transfer Object)

    是一种设计模式之间传输数据的软件应用系统。数据传输目标往往是数据访问对象从数据库中检索数据。数据传输对象与数据交互对象或数据访问对象之间的差异是一个以不具有任何行为除了存储和检索的数据（访问和存取器）。简单来说，当我们需要一个对象10个字段的内容，但这个对象总共有20个字段，我们不需要把整个PO对象全部字段传输到客户端，而是可以用DTO重新封装，传递到客户端。此时，如果这个对象用来对应界面的展现，就叫VO。

* JavaBean:一种可重用组件

    即“一次性编写，任何地方执行，任何地方重用”。满足三个条件①类必须是具体的和公共的②具有无参构造器③提供一致性设计模式的公共方法将内部域暴露成员属性。

    主要用途：可以用在 功能、处理、值、数据库访问和JSP中任何可以用Java代码创造的对象。

    有两种：一种是有用户界面（UI，User Interface）的JavaBean；还有一种是没有用户界面，主要负责处理事务（如数据运算，操纵数据库）的JavaBean。JSP通常访问的是后一种JavaBean。

分类：通常有Session bean，Entity bean，MessageDrivenBean三大类

——Session bean会话构件，是短暂的对象，运行在服务器上，并执行一些应用逻辑处理，它由客户端应用程序建立，其数据需要自己来管理。分为无状态和有状态两种。

——Entity bean实体构件，是持久对象，可以被其他对象调用。在建立时指定一个唯一标示的标识，并允许客户程序，根据实体bean标识来定位beans实例。多个实体可以并发访问实体bean，事务间的协调由容器来完成。

——MessageDriven Bean消息构件，是专门用来处理JMS（Java Message System）消息的规范（EIB2.0）。JMS是一种与厂商无关的API，用来访问消息收发系统，并提供了与厂商无关的访问方法，以此来访问消息收发服务。JMS客户机可以用来发送消息而不必等待回应。

* JavaBeans:JavaBeans 

    从狭义来说，指的是 JavaBeans 规范也就是位于 java.beans 包中的一组 API。从广义上来说，JavaBeans 指的是 API 集合，比如 Enterprise JavaBeans。

* POJO：POJO（Plain Ordinary Java Object）简单的Java对象

    实际就是普通JavaBeans，是为了避免和EJB混淆所创造的简称。通指没有使用Entity Beans的普通java对象，可以把POJO作为支持业务逻辑的协助类。

    POJO实质上可以理解为简单的实体类，顾名思义POJO类的作用是方便程序员使用数据库中的数据表，对于广大的程序员，可以很方便的将POJO类当做对象来进行使用，当然也是可以方便的调用其get,set方法。POJO类也给我们在struts框架中的配置带来了很大的方便。

    一个POJO持久化以后就是PO
    
    直接用它传递、传递过程中就是DTO
    直接用来对应表示层就是VO

### DAO: 数据访问对象是第一个面向对象的数据库接口

是一个数据访问接口(Data Access Object)。它可以把POJO持久化为PO，用PO组装出来VO、DTO。

DAO模式是标准的J2EE设计模式之一.开发人员使用这个模式把底层的数据访问操作和上层的商务逻辑分开.一个典型的DAO实现有下列几个组件：

1. 一个DAO工厂类；

2. 一个DAO接口；

3. 一个实现DAO接口的具体类；

4. 数据传递对象（有些时候叫做值对象）.

具体的DAO类包含了从特定的数据源访问数据的逻辑，一般一个DAO类和一张表对应，每个操作要和事务关联。

