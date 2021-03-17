thymeleaf使用手册
==

## Thymeleaf简介
Thymeleaf是一个现代化的java服务端模板引擎，可处理HTML、XML、JavaScript、CSS及纯文本text

参考资料
* [thymeleaf官网](https://www.thymeleaf.org/)
* [thymeleaf使用手册](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)

## Text


## 标准表达式

## 设置属性值

## 迭代器

### th:each


## 条件求值

## 模板布局
定义fragment并引用fragment

在模板中，通常有些部分需要包括其他模板，如：header, footer, menu等

### 定义fragment
语法，使用下面这个属性
>th:fragment="fragment_name"

### 引用fragement
>th:insert="~{文件名 :: fragment_name}"

或
>th:insert="文件名 :: fragment_name"

**示例**
* 定义fragment
    在SpringBoot项目创建 ./src/main/resources/templates/footer.html
    ```html
    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org">
    
      <body>
        <!-- 定义一个名为 copy 的fragment -->
        <div th:fragment="copy">
          &copy; 2011 The Good Thymes Virtual Grocery
        </div>
      
      </body> 
    </html>
    ```
* ./src/main/resources/templates/success.html中引用上面定义的fragment
    ```html
    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <title>Success</title>
    </head>
    <body>
        <div th:insert="~{footer :: copy}">
        </div>
    
        <hr>
        <!-- 等价于 -->
        <div th:insert="footer :: copy">
    
        </div>
    
    </body>
    </html>
    ```

### fragment规范语法

* ~{templatename::selector}

    引用 模板templatename中的selector片段，selector可以是定义的fragment，也可以是id等选择器
    
* ~{templatename}

    引用 完整的templatename模板文件

* ~{::selector} 或 ~{this::selector}
    
    先从本文件中查找selector，再从根路径下查找匹配的 selector片段

**完整功能版示例**
```html
<div th:insert="footer :: (${user.isAdmin}? #{footer.admin} : #{footer.normaluser})"></div>
```

### 引用未定义th:fragment的模板片段
* ./src/main/resources/templates/footer.html
    ```html
    <!DOCTYPE html>
    <html xmlns:th="http://www.thymeleaf.org">
      <body>
        <div id="copy-section">
          &copy; 2021 The Good Thymes Virtual Grocery
        </div>
      
      </body> 
    </html>
    ```
* 引用id属性片段
    ```html
    <body>
    
      ...
      <div th:insert="~{footer :: #copy-section}"></div>
    </body>
    ```

### th:insert, th:replace, th:include区别
th:insert, th:replace, th:include都可用于引用fragment，但效果有些差别。

其中th:include从thymeleaf 3.0开始不再推荐使用

* th:insert
    当前标签中插入fragment
* th:replace
    fragment替换当前标签
* th:include
    只插入定义的fragment标签的内的html，不包括定义的fragment标签本身

**对比示例**
* 定义的fragment
    ```html
    <footer th:fragment="myfooter">
      &copy; 2021 The Good Thymes Virtual Grocery
    </footer>
    ```
* 三种方式引用定义的myfooter fragment
    ```html
    <body>
    
      ...
    
      <div th:insert="footer :: myfooter"></div>
    
      <div th:replace="footer :: myfooter"></div>
    
      <div th:include="footer :: myfooter"></div>
      
    </body>
    ```

    渲染效果
    ```html
    <body>
    
      ...
    
      <div>
        <footer>
          &copy; 2021 The Good Thymes Virtual Grocery
        </footer>
      </div>
    
      <footer>
        &copy; 2021 The Good Thymes Virtual Grocery
      </footer>
    
      <div>
        &copy; 2021 The Good Thymes Virtual Grocery
      </div>
      
    </body>
    ```

### 参数化fragment
* 定义带参fragment
    ```html
    <div th:fragment="frag (onevar,twovar)">
        <p th:text="${onevar} + ' - ' + ${twovar}">...</p>
    </div>
    ```

* 引用参数化fragment
    
    只能使用 th:insert 和 th:replace 来引用参数化fragment
    ```html
    <div th:replace="::frag (${value1},${value2})">...</div>
    或
    <div th:replace="::frag (onevar=${value1},twovar=${value2})">...</div>
    ```

#### 不带fragment参数的fragment局部变量
* 定义fragment
    ```html
    <div th:fragment="frag">
        ...
    </div>
    ```

* 引用fragment
    ```html
    <div th:replace="::frag (onevar=${value1},twovar=${value2})">
    
    <!-- 等价于 -->
    <div th:replace="::frag" th:with="onevar=${value1},twovar=${value2}">
    ```

#### th:assert在模板中断言
th:assert 断言的条件要成立，否则要报异常

```html
<div th:assert="${onevar},(${twovar} != 43)">...</div>
```

```html
<header th:fragment="contentheader(title)" th:assert="${!#strings.isEmpty(title)}">...</header>
```

### 灵活布局
Flexible layouts 灵活布局，不是css中的flex布局

* 定义fragment

    base.thml
    ```html
    <head th:fragment="common_header(title,links)">
    
      <title th:replace="${title}">The awesome application</title>
    
      <!-- Common styles and scripts -->
      <link rel="stylesheet" type="text/css" media="all" th:href="@{/css/awesomeapp.css}">
      <link rel="shortcut icon" th:href="@{/images/favicon.ico}">
      <script type="text/javascript" th:src="@{/sh/scripts/codebase.js}"></script>
    
      <!--/* Per-page placeholder for additional links */-->
      <th:block th:replace="${links}" />
    
    </head>
    ```
* 引用fragment
    ```html
    ...
    <head th:replace="base :: common_header(~{::title},~{::link})">
    
      <title>Awesome - Main</title>
    
      <link rel="stylesheet" th:href="@{/css/bootstrap.min.css}">
      <link rel="stylesheet" th:href="@{/themes/smoothness/jquery-ui.css}">
    
    </head>
    ...
    ```

    * 渲染后的效果
    ```html
    ...
    <head>
    
      <title>Awesome - Main</title>
    
      <!-- Common styles and scripts -->
      <link rel="stylesheet" type="text/css" media="all" href="/awe/css/awesomeapp.css">
      <link rel="shortcut icon" href="/awe/images/favicon.ico">
      <script type="text/javascript" src="/awe/sh/scripts/codebase.js"></script>
    
      <link rel="stylesheet" href="/awe/css/bootstrap.min.css">
      <link rel="stylesheet" href="/awe/themes/smoothness/jquery-ui.css">
    
    </head>
    ...
    ```

#### 使用空fragment
```text
~{}  表示一个空的fragment对象
```
举上面 [灵活布局](#灵活布局) 中的例子

需求：只替换title

* 引用fragment方式如下
    ```html
    <head th:replace="base :: common_header(~{::title},~{})">
    
      <title>Awesome - Main</title>
    
    </head>
    ...
    ```

* 渲染效果
    ```html
    ...
    <head>
    
      <title>Awesome - Main</title>
    
      <!-- Common styles and scripts -->
      <link rel="stylesheet" type="text/css" media="all" href="/awe/css/awesomeapp.css">
      <link rel="shortcut icon" href="/awe/images/favicon.ico">
      <script type="text/javascript" src="/awe/sh/scripts/codebase.js"></script>
    
    </head>
    ...
    ```

#### 使用no-operation token
no-operation token，无操作令牌
符号：_  即一个英文下划线

继续引用灵活布局中的例子

需求：不替换links

* 引用fragment方式

    ```html
    ...
    <head th:replace="base :: common_header(_,~{::link})">
    
      <title>Awesome - Main</title>
    
      <link rel="stylesheet" th:href="@{/css/bootstrap.min.css}">
      <link rel="stylesheet" th:href="@{/themes/smoothness/jquery-ui.css}">
    
    </head>
    ...
    ```
* 渲染效果
    ```html
    ...
    <head>
    
      <title>The awesome application</title>
    
      <!-- Common styles and scripts -->
      <link rel="stylesheet" type="text/css" media="all" href="/awe/css/awesomeapp.css">
      <link rel="shortcut icon" href="/awe/images/favicon.ico">
      <script type="text/javascript" src="/awe/sh/scripts/codebase.js"></script>
    
      <link rel="stylesheet" href="/awe/css/bootstrap.min.css">
      <link rel="stylesheet" href="/awe/themes/smoothness/jquery-ui.css">
    
    </head>
    ...
    ```

#### 条件插入fragment
* 例1
    ```html
    ...
    <div th:insert="${user.isAdmin()} ? ~{common :: adminhead} : ~{}">
      <div>we are here</div>
    </div>
    ...
    ```
    * 当user.isAdmin()为true时，插入模common中的adminhead fragment
    * 当user.isAdmin()为false时，使用空fragment插入当前标签中，为替换当前标签中内的html，即清空当前标签的html内容

* 例2
    ```html
    ...
    <div th:insert="${user.isAdmin()} ? ~{common :: adminhead} : _">
        Welcome [[${user.name}]], click <a th:href="@{/support}">here</a> for help-desk support.
    </div>
    ...
    ```
    * user.isAdmin()为true时，common::adminhead内容替换当前标签中的html内容
    * user.isAdmin()为false时，不做任何操作，即当前标签中的html内容继续保留

* 例3
    ```html
    ...
    <!-- The body of the <div> will be used if the "common :: salutation" fragment  -->
    <!-- does not exist (or is empty).                                              -->
    <div th:insert="~{common :: salutation} ?: _">
        Welcome [[${user.name}]], click <a th:href="@{/support}">here</a> for help-desk support.
    </div>
    ...
    ```

#### 删除模板中的fragment
* 定义fragment

    需求：在引用时需要删除模板中的前端测试数据
    ```html
    <table>
      <tr>
        <th>NAME</th>
        <th>PRICE</th>
        <th>IN STOCK</th>
        <th>COMMENTS</th>
      </tr>
      <tr th:each="prod : ${prods}" th:class="${prodStat.odd}? 'odd'">
        <td th:text="${prod.name}">Onions</td>
        <td th:text="${prod.price}">2.41</td>
        <td th:text="${prod.inStock}? #{true} : #{false}">yes</td>
        <td>
          <span th:text="${#lists.size(prod.comments)}">2</span> comment/s
          <a href="comments.html" 
             th:href="@{/product/comments(prodId=${prod.id})}" 
             th:unless="${#lists.isEmpty(prod.comments)}">view</a>
        </td>
      </tr>
      <!-- 前端测试数据 --start -->
      <tr class="odd" th:remove="all">
        <td>Blue Lettuce</td>
        <td>9.55</td>
        <td>no</td>
        <td>
          <span>0</span> comment/s
        </td>
      </tr>
      <tr th:remove="all">
        <td>Mild Cinnamon</td>
        <td>1.99</td>
        <td>yes</td>
        <td>
          <span>3</span> comment/s
          <a href="comments.html">view</a>
        </td>
      </tr>
      <!-- 前端测试数据 --end -->
    </table>
    ```

**th:remove的属性**
* all
    >Remove both the containing tag and all its children.
* body
    >Do not remove the containing tag, but remove all its children.
* tag
    >Remove the containing tag, but do not remove its children.
* all-but-first
    >Remove all children of the containing tag except the first one.
* none
    >Do nothing. This value is useful for dynamic evaluation.

**条件删除示例**
```html
<a href="/something" th:remove="${condition}? tag : none">Link text not to be removed</a>
```

```html
<a href="/something" th:remove="${condition}? tag">Link text not to be removed</a>
```

## 局部变量
Local Variables


## Attribute属性优先级

<table >
    <thead>
    <tr class="header">
        <th >Order优先级</th>
        <th >Feature功能</th>
        <th >Attributes属性</th>
        <th >备注</th>
    </tr>
    </thead>
    <tbody>
    <tr class="odd">
        <td >1</td>
        <td >Fragment inclusion <br>fragment插入</td>
        <td ><code>th:insert</code><br>
            <code>th:replace</code></td>
        <td></td>
    </tr>
    <tr class="even">
        <td >2</td>
        <td >Fragment iteration <br>fragment迭代器</td>
        <td ><code>th:each</code></td>
        <td></td>
    </tr>
    <tr class="odd">
        <td >3</td>
        <td >Conditional evaluation <br>条件判断</td>
        <td ><code>th:if</code><br>
            <code>th:unless</code><br>
            <code>th:switch</code><br>
            <code>th:case</code></td>
        <td></td>
    </tr>
    <tr class="even">
        <td >4</td>
        <td >Local variable definition <br>局部变量的定义</td>
        <td ><code>th:object</code><br>
            <code>th:with</code></td>
        <td></td>
    </tr>
    <tr class="odd">
        <td >5</td>
        <td >General attribute modification <br>一般属性的修改，支持 prepend, append</td>
        <td ><code>th:attr</code><br>
            <code>th:attrprepend</code><br>
            <code>th:attrappend</code></td>
        <td></td>
    </tr>
    <tr class="even">
        <td >6</td>
        <td >Specific attribute modification <br>特性属性的修改</td>
        <td ><code>th:value</code><br>
            <code>th:href</code><br>
            <code>th:src</code><br>
            <code>...</code></td>
        <td></td>
    </tr>
    <tr class="odd">
        <td >7</td>
        <td >Text (tag body modification)</td>
        <td ><code>th:text</code><br>
            <code>th:utext</code></td>
        <td>
            th:text 会转义特殊字符 <br>
            th:utext 不转义特殊字符，utext: unescaped text，
        </td>
    </tr>
    <tr class="even">
        <td >8</td>
        <td >Fragment specification <br>定义fragment</td>
        <td ><code>th:fragment</code></td>
        <td></td>
    </tr>
    <tr class="odd">
        <td >9</td>
        <td >Fragment removal <br>删除fragment</td>
        <td ><code>th:remove</code></td>
        <td></td>
    </tr>
    </tbody>
</table>


## 注释和Block块
### parser-level comment blocks
```html
<!--/* 
... 
*/-->
```

将删除 <!--/* 与 */-->之间的所有内容，包括注释符

示例
```html
<!--/* This code will be removed at Thymeleaf parsing time! */-->
```
```html
<!--/*--> 
  <div>
     you can see me only before Thymeleaf processes me!
  </div>
<!--*/-->
```

### prototype-only comment blocks
```html
<!--/*/
...
/*/-->
```

仅原型注释块。直接访问静态的html页面时，当作html的注释块。

thymeleaf解析时，只删除 <!--/*/ 和 /*/-->标记符，里面的内容照样解析

示例
```html
<span>hello!</span>
<!--/*/
  <div th:text="${...}">
    ...
  </div>
/*/-->
<span>goodbye!</span>
```

渲染效果
```html
<span>hello!</span>
 
  <div th:text="${...}">
    ...
  </div>
 
<span>goodbye!</span>
```

### th:block合成标记
th:block 是元素处理器，相当于是属性容器

```html
<table>
  <th:block th:each="user : ${users}">
    <tr>
        <td th:text="${user.login}">...</td>
        <td th:text="${user.name}">...</td>
    </tr>
    <tr>
        <td colspan="2" th:text="${user.address}">...</td>
    </tr>
  </th:block>
</table>
```

原型
```html
<table>
    <!--/*/ <th:block th:each="user : ${users}"> /*/-->
    <tr>
        <td th:text="${user.login}">...</td>
        <td th:text="${user.name}">...</td>
    </tr>
    <tr>
        <td colspan="2" th:text="${user.address}">...</td>
    </tr>
    <!--/*/ </th:block> /*/-->
</table>
```

## 行内表达式
行内表达式可以在标签的html内容中写，其他的表达式只能当作标签的属性写
```html
[[...]]  会转义特殊字符

[(...)]  不转义特殊字符
```

* 示例

有一个变量
```html
msg = 'This is <b>great!</b>'
```

* [[...]]示例
    ```html
    <p>The message is "[[${msg}]]"</p>
    ```
    
    渲染效果
    ```html
    <p>The message is "This is &lt;b&gt;great!&lt;/b&gt;"</p>
    ```

* [(...)]示例
    ```html
    <p>The message is "[(${msg})]"</p>
    ```
    渲染效果
    ```html
    <p>The message is "This is <b>great!</b>"</p>
    ```






## 文本模板模式

## 配置详情

## 模板缓存

## 解耦模板逻辑

## 附录A_表达式基本对象

## 附录B_表达式实用程序对象

## 附录C_标记选择器语法