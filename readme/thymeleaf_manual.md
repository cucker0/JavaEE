thymeleaf使用手册
==

## Table Of Contents
* [Thymeleaf简介](#Thymeleaf简介)
* [Text](#Text)
* [标准表达式语法](#标准表达式语法)
    * [表达式基本对象](#表达式基本对象)
    * [表达式内置对象](#表达式内置对象)
    * [URL链接](#URL链接)
    * [URL链接类型](#URL链接类型)
    * [fragment](#fragment)
    * [条件表达式](#条件表达式)
    * [false时默认值表达式](#false时默认值表达式)
* [设置属性值](#设置属性值)
    * [设置特定的属性](#设置特定的属性)
    * [属性后追加值和属性前插入值](#属性后追加值和属性前插入值)
    * [友好的html5属性及元素名](#友好的html5属性及元素名)
* [迭代器](#迭代器)
    * [迭代状态变量](#迭代状态变量)
* [条件求值](#条件求值)
    * [th:if判断规则](#thif判断规则)
    * [th:switch](#thswitch)
* [模板布局](#模板布局)
    * [定义fragment](#定义fragment)
    * [引用fragement](#引用fragement)
    * [fragment规范语法](#fragment规范语法)
    * [引用未定义th:fragment的模板片段](#引用未定义thfragment的模板片段)
    * [th:insert, th:replace, th:include区别](#thinsert-threplace-thinclude区别)
    * [参数化fragment](#参数化fragment)
        * [不带fragment参数的fragment局部变量](#不带fragment参数的fragment局部变量)
        * [th:assert在模板中断言](#thassert在模板中断言)
    * [灵活布局](#灵活布局)
        * [使用空fragment](#使用空fragment)
        * [使用no-operation token](#使用no-operation-token)
        * [条件插入fragment](#条件插入fragment)
        * [删除模板中的fragment](#删除模板中的fragment)
    * [模板使用示例(公共片段的抽取)](https://github.com/cucker0/JavaEE/blob/master/readme/SpringBoot.md#thymeleaf公共片段抽取)
* [局部变量](#局部变量)
* [Attribute属性优先级](#Attribute属性优先级)
* [注释和Block块](#注释和Block块)
    * [parser-level comment blocks](#parser-level-comment-blocks)
    * [prototype-only comment blocks](#prototype-only-comment-blocks)
    * [th:block合成标记](#thblock合成标记)
* [行内表达式](#行内表达式)
    * [关闭行内表达式](#关闭行内表达式)
    * [行内text](#行内text)
    * [行内javaScript](#行内javaScript)
    * [行内css](#行内css)
* [文本模板模式](#文本模板模式)
* [配置详情](#配置详情)
* [模板缓存](#模板缓存)
* [SpringBoot静态资源加版本号](#SpringBoot静态资源加版本号)
* [解耦模板逻辑](#解耦模板逻辑)
* [附录A_表达式基本对象](#附录A_表达式基本对象)
* [附录B_表达式内置对象](#附录B_表达式内置对象)
* [附录C_标记选择器语法](#附录C_标记选择器语法)


## Thymeleaf简介
Thymeleaf是一个现代化的java服务端模板引擎，可处理HTML、XML、JavaScript、CSS及纯文本text

参考资料
* [thymeleaf官网](https://www.thymeleaf.org/)
* [thymeleaf使用手册](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)

## Text
作用：替换该标签内的html内容

属性
```html
th:text   转义特殊字

th:utext  不转义特殊字符
```

已有变量
```html
home.welcome=Welcome to our <b>fantastic</b> grocery store!
```

* th:text示例
    ```html
    <p th:text="#{home.welcome}">Welcome to our grocery store!</p>
    ```
    渲染效果
    ```html
    <p>Welcome to our &lt;b&gt;fantastic&lt;/b&gt; grocery store!</p>
    ```
* th:utext示例 
    ```html
    <p th:utext="#{home.welcome}">Welcome to our grocery store!</p>
    ```
    渲染效果
    ```html
    <p>Welcome to our <b>fantastic</b> grocery store!</p>
    ```

th:text="" 直接设置一个字符串时，注意字符串空格，需要转义

像这样的将报错："xxx": Could not parse as expression: "v5 v5 v5" (template: "xxx" - line 15, col 10)
```html
    <div th:text="v5 v5 v5">
        my text
        <p>ge ge ge ...</p>
    </div>
```

这样使用字符串正常
```html
    <div th:text="v5v5v5">
        my text
        <p>ge ge ge ...</p>
    </div>
```

可以定义局部变量来解决这一问题
```html
    <div th:with="tips='v5 v5 v5'" th:text="${tips}">
        my text
        <p>ge ge ge ...</p>
    </div>
```

渲染效果
```html
    <div>
        v5 v5 v5
    </div>
```


## 标准表达式语法
* Simple expressions(简单表达式,)
    ```text
    ${...}  Variable Expressions 获取变量值。获取对象的属性，调用方法。用的是OGNL表达式
    *{...}  Selection Variable Expressions 选择器表达式，在功能上跟 ${...}相同
    #{...}  Message Expressions 获取国际化内容
    @{...}  Link URL Expressions
    ~{...}  Fragment Expressions
    ```
    
    * ${...} vs *{...}
        ```html
        <div th:object="${session.user}">
        <p>Name: <span th:text="*{firstName}">Sebastian</span>.</p>
        <p>Surname: <span th:text="*{lastName}">Pepper</span>.</p>
        <p>Nationality: <span th:text="*{nationality}">Saturn</span>.</p>
        </div>
        ```
        等价于
        ```html
        <div>
          <p>Name: <span th:text="${session.user.firstName}">Sebastian</span>.</p>
          <p>Surname: <span th:text="${session.user.lastName}">Pepper</span>.</p>
          <p>Nationality: <span th:text="${session.user.nationality}">Saturn</span>.</p>
        </div>
        ```
    
* Literals(字面量)
    ```text
    Text literals: 'one text', 'Another one!',…
    Number literals: 0, 34, 3.0, 12.3,…
    Boolean literals: true, false
    Null literal: null
    Literal tokens: one, sometext, main,…
    ```

* Text operations(文本运算)
    ```html
    String concatenation: +
    Literal substitutions: |The name is ${name}|
    ```
    
* Arithmetic operations(算术运行)
    * Binary operators(二元运算)
        >+, -, *, /, %
    * Minus sign (unary operator一元操作)负号
        >\-
        
* Boolean operations(布尔运算)
    ```text
    Binary operators: and, or
    Boolean negation (unary operator): !, not
    ```
* Comparisons and equality(比较运算)
    ```text
    Comparators: >, <, >=, <= (gt, lt, ge, le)
    Equality operators: ==, != (eq, ne)
    ```
* Conditional operators(条件运算)
    * If-then
        >(if) ? (then)
    * If-then-else，即三元运算
        >(if) ? (then) : (else)
    * Default
        >(value) ?: (defaultvalue)

* Special tokens(特殊符号)
    ```text
    _  No-Operation 无操作符，即不要做任何操作
    ```

可以复合表达
```html
'User is of type ' + (${user.isAdmin()} ? 'Administrator' : (${user.type} ?: 'Unknown'))
```

### 表达式基本对象
```text
#ctx: the context object.
#vars: the context variables.
#locale: the context locale.
#request: (only in Web Contexts) the HttpServletRequest object.
#response: (only in Web Contexts) the HttpServletResponse object.
#session: (only in Web Contexts) the HttpSession object.
#servletContext: (only in Web Contexts) the ServletContext object.
```
示例
```html
Established locale country: <span th:text="${#locale.country}">US</span>.
```

### 表达式内置对象
```text
#execInfo: information about the template being processed.

#messages: methods for obtaining externalized messages inside variables expressions, 
    in the same way as they would be obtained using #{…} syntax.

#uris: methods for escaping parts of URLs/URIs

#conversions: methods for executing the configured conversion service (if any).

#dates: methods for java.util.Date objects: formatting, component extraction, etc.

#calendars: analogous to #dates, but for java.util.Calendar objects.

#numbers: methods for formatting numeric objects.

#strings: methods for String objects: contains, startsWith, prepending/appending, etc.

#objects: methods for objects in general.

#bools: methods for boolean evaluation.

#arrays: methods for arrays.

#lists: methods for lists.

#sets: methods for sets.

#maps: methods for maps.

#aggregates: methods for creating aggregates on arrays or collections.

#ids: methods for dealing with id attributes that might be repeated 
    (for example, as a result of an iteration).
```

### URL链接
符号
```html
@{...}
```

### URL链接类型
* Absolute URLs
    >http://www.thymeleaf.org
* Relative URLs
    * Page-relative
        >user/login.html
    * Context-relative
        
        (context name in server will be added automatically)
        >/itemdetails?id=3 
    * Server-relative
    
        (allows calling URLs in another context (= application) in the same server.
        >~/billing/processInvoice 
    * Protocol-relative URLs
        >//code.jquery.com/jquery-2.0.3.min.js

**示例**
```html
<!-- Will produce 'http://localhost:8080/gtvg/order/details?orderId=3' (plus rewriting) -->
<a href="details.html" 
   th:href="@{http://localhost:8080/gtvg/order/details(orderId=${o.id})}">view</a>

<!-- Will produce '/gtvg/order/details?orderId=3' (plus rewriting) -->
<a href="details.html" th:href="@{/order/details(orderId=${o.id})}">view</a>

<!-- Will produce '/gtvg/order/3/details' (plus rewriting) -->
<a href="details.html" th:href="@{/order/{orderId}/details(orderId=${o.id})}">view</a>
```

### fragment
用于定义fragment片段

[定义fragment](#定义fragment)

[引用fragment](#引用fragement)

### 条件表达式
示例
```html
<tr th:class="${row.even}? 'even' : 'odd'">
  ...
</tr>
```

```html
<tr th:class="${row.even}? (${row.first}? 'first' : 'even') : 'odd'">
  ...
</tr>
```

```html
<tr th:class="${row.even}? 'alt'">
  ...
</tr>
```

### false时默认值表达式
```html
<div th:object="${session.user}">
  ...
  <p>Age: <span th:text="*{age} ?: '(no age specified)'">27</span>.</p>
</div>
```

等价于
```html
<div th:object="${session.user}">
  ...
  <p>Age: <span th:text="*{age != null} ? *{age} : '(no age specified)'">27</span>.</p>
</div>
```

## 设置属性值
符号
```html
th:attr="attr1=val1,attr2=val2"
```

* 示例1
已有变量，国际化配cn置文件的一个变量
```html
submit.btn="提交"
```

```html
<form action="subscribe.html" th:attr="action=@{/subscribe}">
  <fieldset>
    <input type="text" name="email" />
    <input type="submit" value="Subscribe!" th:attr="value=#{submit.btn}"/>
  </fieldset>
</form>
```

当在中文环境下，渲染的效果
```html
<form action="/gtvg/subscribe">
  <fieldset>
    <input type="text" name="email" />
    <input type="submit" value="提交"/>
  </fieldset>
</form>
```

* 示例2

已有变量
```html
logo=Logo de Good Thymes
```

```html
<img src="../../images/gtvglogo.png" 
     th:attr="src=@{/images/gtvglogo.png},title=#{logo},alt=#{logo}" />
```

渲染效果
```html
<img src="/gtgv/images/gtvglogo.png" title="Logo de Good Thymes" alt="Logo de Good Thymes" />
```

### 设置特定的属性

<table>
<tbody>
<tr class="odd">
<td style="text-align: left;"><code>th:abbr</code></td>
<td style="text-align: left;"><code>th:accept</code></td>
<td style="text-align: left;"><code>th:accept-charset</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:accesskey</code></td>
<td style="text-align: left;"><code>th:action</code></td>
<td style="text-align: left;"><code>th:align</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:alt</code></td>
<td style="text-align: left;"><code>th:archive</code></td>
<td style="text-align: left;"><code>th:audio</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:autocomplete</code></td>
<td style="text-align: left;"><code>th:axis</code></td>
<td style="text-align: left;"><code>th:background</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:bgcolor</code></td>
<td style="text-align: left;"><code>th:border</code></td>
<td style="text-align: left;"><code>th:cellpadding</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:cellspacing</code></td>
<td style="text-align: left;"><code>th:challenge</code></td>
<td style="text-align: left;"><code>th:charset</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:cite</code></td>
<td style="text-align: left;"><code>th:class</code></td>
<td style="text-align: left;"><code>th:classid</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:codebase</code></td>
<td style="text-align: left;"><code>th:codetype</code></td>
<td style="text-align: left;"><code>th:cols</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:colspan</code></td>
<td style="text-align: left;"><code>th:compact</code></td>
<td style="text-align: left;"><code>th:content</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:contenteditable</code></td>
<td style="text-align: left;"><code>th:contextmenu</code></td>
<td style="text-align: left;"><code>th:data</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:datetime</code></td>
<td style="text-align: left;"><code>th:dir</code></td>
<td style="text-align: left;"><code>th:draggable</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:dropzone</code></td>
<td style="text-align: left;"><code>th:enctype</code></td>
<td style="text-align: left;"><code>th:for</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:form</code></td>
<td style="text-align: left;"><code>th:formaction</code></td>
<td style="text-align: left;"><code>th:formenctype</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:formmethod</code></td>
<td style="text-align: left;"><code>th:formtarget</code></td>
<td style="text-align: left;"><code>th:fragment</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:frame</code></td>
<td style="text-align: left;"><code>th:frameborder</code></td>
<td style="text-align: left;"><code>th:headers</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:height</code></td>
<td style="text-align: left;"><code>th:high</code></td>
<td style="text-align: left;"><code>th:href</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:hreflang</code></td>
<td style="text-align: left;"><code>th:hspace</code></td>
<td style="text-align: left;"><code>th:http-equiv</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:icon</code></td>
<td style="text-align: left;"><code>th:id</code></td>
<td style="text-align: left;"><code>th:inline</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:keytype</code></td>
<td style="text-align: left;"><code>th:kind</code></td>
<td style="text-align: left;"><code>th:label</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:lang</code></td>
<td style="text-align: left;"><code>th:list</code></td>
<td style="text-align: left;"><code>th:longdesc</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:low</code></td>
<td style="text-align: left;"><code>th:manifest</code></td>
<td style="text-align: left;"><code>th:marginheight</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:marginwidth</code></td>
<td style="text-align: left;"><code>th:max</code></td>
<td style="text-align: left;"><code>th:maxlength</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:media</code></td>
<td style="text-align: left;"><code>th:method</code></td>
<td style="text-align: left;"><code>th:min</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:name</code></td>
<td style="text-align: left;"><code>th:onabort</code></td>
<td style="text-align: left;"><code>th:onafterprint</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:onbeforeprint</code></td>
<td style="text-align: left;"><code>th:onbeforeunload</code></td>
<td style="text-align: left;"><code>th:onblur</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:oncanplay</code></td>
<td style="text-align: left;"><code>th:oncanplaythrough</code></td>
<td style="text-align: left;"><code>th:onchange</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:onclick</code></td>
<td style="text-align: left;"><code>th:oncontextmenu</code></td>
<td style="text-align: left;"><code>th:ondblclick</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:ondrag</code></td>
<td style="text-align: left;"><code>th:ondragend</code></td>
<td style="text-align: left;"><code>th:ondragenter</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:ondragleave</code></td>
<td style="text-align: left;"><code>th:ondragover</code></td>
<td style="text-align: left;"><code>th:ondragstart</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:ondrop</code></td>
<td style="text-align: left;"><code>th:ondurationchange</code></td>
<td style="text-align: left;"><code>th:onemptied</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:onended</code></td>
<td style="text-align: left;"><code>th:onerror</code></td>
<td style="text-align: left;"><code>th:onfocus</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:onformchange</code></td>
<td style="text-align: left;"><code>th:onforminput</code></td>
<td style="text-align: left;"><code>th:onhashchange</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:oninput</code></td>
<td style="text-align: left;"><code>th:oninvalid</code></td>
<td style="text-align: left;"><code>th:onkeydown</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:onkeypress</code></td>
<td style="text-align: left;"><code>th:onkeyup</code></td>
<td style="text-align: left;"><code>th:onload</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:onloadeddata</code></td>
<td style="text-align: left;"><code>th:onloadedmetadata</code></td>
<td style="text-align: left;"><code>th:onloadstart</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:onmessage</code></td>
<td style="text-align: left;"><code>th:onmousedown</code></td>
<td style="text-align: left;"><code>th:onmousemove</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:onmouseout</code></td>
<td style="text-align: left;"><code>th:onmouseover</code></td>
<td style="text-align: left;"><code>th:onmouseup</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:onmousewheel</code></td>
<td style="text-align: left;"><code>th:onoffline</code></td>
<td style="text-align: left;"><code>th:ononline</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:onpause</code></td>
<td style="text-align: left;"><code>th:onplay</code></td>
<td style="text-align: left;"><code>th:onplaying</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:onpopstate</code></td>
<td style="text-align: left;"><code>th:onprogress</code></td>
<td style="text-align: left;"><code>th:onratechange</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:onreadystatechange</code></td>
<td style="text-align: left;"><code>th:onredo</code></td>
<td style="text-align: left;"><code>th:onreset</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:onresize</code></td>
<td style="text-align: left;"><code>th:onscroll</code></td>
<td style="text-align: left;"><code>th:onseeked</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:onseeking</code></td>
<td style="text-align: left;"><code>th:onselect</code></td>
<td style="text-align: left;"><code>th:onshow</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:onstalled</code></td>
<td style="text-align: left;"><code>th:onstorage</code></td>
<td style="text-align: left;"><code>th:onsubmit</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:onsuspend</code></td>
<td style="text-align: left;"><code>th:ontimeupdate</code></td>
<td style="text-align: left;"><code>th:onundo</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:onunload</code></td>
<td style="text-align: left;"><code>th:onvolumechange</code></td>
<td style="text-align: left;"><code>th:onwaiting</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:optimum</code></td>
<td style="text-align: left;"><code>th:pattern</code></td>
<td style="text-align: left;"><code>th:placeholder</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:poster</code></td>
<td style="text-align: left;"><code>th:preload</code></td>
<td style="text-align: left;"><code>th:radiogroup</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:rel</code></td>
<td style="text-align: left;"><code>th:rev</code></td>
<td style="text-align: left;"><code>th:rows</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:rowspan</code></td>
<td style="text-align: left;"><code>th:rules</code></td>
<td style="text-align: left;"><code>th:sandbox</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:scheme</code></td>
<td style="text-align: left;"><code>th:scope</code></td>
<td style="text-align: left;"><code>th:scrolling</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:size</code></td>
<td style="text-align: left;"><code>th:sizes</code></td>
<td style="text-align: left;"><code>th:span</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:spellcheck</code></td>
<td style="text-align: left;"><code>th:src</code></td>
<td style="text-align: left;"><code>th:srclang</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:standby</code></td>
<td style="text-align: left;"><code>th:start</code></td>
<td style="text-align: left;"><code>th:step</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:style</code></td>
<td style="text-align: left;"><code>th:summary</code></td>
<td style="text-align: left;"><code>th:tabindex</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:target</code></td>
<td style="text-align: left;"><code>th:title</code></td>
<td style="text-align: left;"><code>th:type</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:usemap</code></td>
<td style="text-align: left;"><code>th:value</code></td>
<td style="text-align: left;"><code>th:valuetype</code></td>
</tr>
<tr class="even">
<td style="text-align: left;"><code>th:vspace</code></td>
<td style="text-align: left;"><code>th:width</code></td>
<td style="text-align: left;"><code>th:wrap</code></td>
</tr>
<tr class="odd">
<td style="text-align: left;"><code>th:xmlbase</code></td>
<td style="text-align: left;"><code>th:xmllang</code></td>
<td style="text-align: left;"><code>th:xmlspace</code></td>
</tr>
</tbody>
</table>

### 属性后追加值和属性前插入值
`th:attrappend`  原属性后追加值

`th:attrprepend`  原属性前插入值

* 示例
    ```html
    <input type="button" value="Do it!" class="btn" th:attrappend="class=${' ' + cssStyle}" />
    ```
    如果把cssStyle变量的值设置为"warning"，则渲染效果如下
    ```html
    <input type="button" value="Do it!" class="btn warning" />
    ```

### 友好的html5属性及元素名
示例
```html
<table>
    <tr data-th-each="user : ${users}">
        <td data-th-text="${user.login}">...</td>
        <td data-th-text="${user.name}">...</td>
    </tr>
</table>
```

等价于
```html
<table>
    <tr th:each="user : ${users}">
        <td th:text="${user.login}">...</td>
        <td th:text="${user.name}">...</td>
    </tr>
</table>
```

## 迭代器
用于遍历对象，符号
```html
th:each
```

* 示例
    ```html
        ...
        <table>
          <tr>
            <th>NAME</th>
            <th>PRICE</th>
            <th>IN STOCK</th>
          </tr>
          <tr th:each="prod : ${prods}">
            <td th:text="${prod.name}">Onions</td>
            <td th:text="${prod.price}">2.41</td>
            <td th:text="${prod.inStock}? #{true} : #{false}">yes</td>
          </tr>
        </table>
        ...
    ```

* 可迭代的对象类型
    ```html
    java.util.List
    java.util.Iterable
    java.util.Enumeration
    java.util.Iterator
    java.util.Map
    java.util.Map.Entry
    array数组
    ```

### 迭代状态变量
```text
index  The current iteration index, starting with 0. 

count  The current iteration index, starting with 1.

size  The total amount of elements in the iterated variable.

current  The iter variable for each iteration. This is the current property. 当前正遍历的元素

even/odd  Whether the current iteration is even or odd. boolean 类型

first  Whether the current iteration is the first one. boolean 类型

last  Whether the current iteration is the last one. boolean 类型
```

* 示例1（设置status变量）
    ```html
    <table>
      <tr>
        <th>NAME</th>
        <th>PRICE</th>
        <th>IN STOCK</th>
      </tr>
      <!-- 只在单数行中添加 class="odd" 属性 -->
      <tr th:each="prod,iterStat : ${prods}" th:class="${iterStat.odd}? 'odd'">
        <td th:text="${prod.name}">Onions</td>
        <td th:text="${prod.price}">2.41</td>
        <td th:text="${prod.inStock}? #{true} : #{false}">yes</td>
      </tr>
    </table>
    ```

* 示例2（不设置status变量）

    则用遍历元素的变量名作为前缀，+ Stat，来表示status变量
    ```html
    <table>
      <tr>
        <th>NAME</th>
        <th>PRICE</th>
        <th>IN STOCK</th>
      </tr>
      <tr th:each="prod : ${prods}" th:class="${prodStat.odd}? 'odd'">
        <td th:text="${prod.name}">Onions</td>
        <td th:text="${prod.price}">2.41</td>
        <td th:text="${prod.inStock}? #{true} : #{false}">yes</td>
      </tr>
    </table>
    ```

## 条件求值

```html
th:if

th:unless  取反，即取反

th:switch - th:case  多分支判断
```

* 只有当prod.comments才添加此连接
    ```html
    <a href="comments.html"
       th:href="@{/product/comments(prodId=${prod.id})}" 
       th:if="${not #lists.isEmpty(prod.comments)}">view</a>
    ```
    等价于
    ```html
    <a href="comments.html"
       th:href="@{/comments(prodId=${prod.id})}" 
       th:unless="${#lists.isEmpty(prod.comments)}">view</a>
    ```

### th:if判断规则
* 值为非空时，为true的情况
    * 为true的boolean值
    * 非0数字
    * 非0字符
    * 非“false”, “off”, “no”的字符串
    * 非boolean, 数字, 字符, 字符串的对象
    
* 值为空时，判断为false

### th:switch
示例
```html
<div th:switch="${user.role}">
  <p th:case="'admin'">User is an administrator</p>
  <p th:case="#{roles.manager}">User is a manager</p>
  <!--/* 默认情况，可以不写默认情况 */-->
  <p th:case="*">User is some other thing</p>
</div>
```


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

把引用fragment表达式语句设置为一个变量
```html
<div th:with="frag=~{footer :: #main/text()}">
  <p th:insert="${frag}">
</div>
```


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
        <p th:text="${onevar}"></p>
        <p th:text="${twovar}"></p>
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

### Thymeleaf Layout Dialect
thymeleaf布局的方言，是thymeleaf布局功能上的扩展

[Thymeleaf Layout Dialect](https://github.com/ultraq/thymeleaf-layout-dialect)

## 局部变量
Local Variables 局部变量，只在当前标签块中可访问

* 声明局部变量语法
    ```html
    th:with="key1=val1,key2=val2"
    ```

这是我们前面见过的局部变量，只能在tr中可访问
```html
<tr th:each="prod : ${prods}">
    ...
</tr>
```

* 示例1
    ```html
    <div th:with="firstPer=${persons[0]}">
      <p>
        The name of the first person is <span th:text="${firstPer.name}">Julius Caesar</span>.
      </p>
    </div>
    ```

* 示例2
    ```html
    <div th:with="firstPer=${persons[0]},secondPer=${persons[1]}">
      <p>
        The name of the first person is <span th:text="${firstPer.name}">Julius Caesar</span>.
      </p>
      <p>
        But the name of the second person is 
        <span th:text="${secondPer.name}">Marcus Antonius</span>.
      </p>
    </div>
    ```

## Attribute属性优先级

<table >
    <thead>
    <tr class="header">
        <th >Order处理顺序</th>
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

在html原型中可以这么写，直接访问静态页面也不会报错，访问web服务器响应的页面则能把数据替换过来
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
### 关闭行内表达式
使用下面的属性关闭行此标签的行内表达式功能
```html
th:inline="none"
```

示例
```html
<p th:inline="none">A double array looks like this: [[1, 2, 3], [4, 5]]!</p>
```

渲染效果
```html
<p>A double array looks like this: [[1, 2, 3], [4, 5]]!</p>
```

### 行内text
```html
th:inline="text"
```

### 行内javaScript
```html
th:inline="javascript"
```

已有变量
```html
session.user.name = 'Sebastian \"Fruity\" Applejuice'
```

示例
```html
<script th:inline="javascript">
    ...
    var username = [[${session.user.name}]];
    ...
</script>
```

渲染效果
```html
<script th:inline="javascript">
    ...
    var username = "Sebastian \"Fruity\" Applejuice";
    ...
</script>
```

### 行内css
```html
th:inline="css"
```

有两个变量
```html
classname = 'main elems'
align = 'center'
```

行内css示例
```html
<style th:inline="css">
    .[[${classname}]] {
      text-align: [[${align}]];
    }
</style>
```

渲染效果
```html
<style th:inline="css">
    .main\ elems {
      text-align: center;
    }
</style>
```

## 文本模板模式
文本包括：TEXT, JAVASCRIPT, CSS

* 行内表达式
    
    text email模板
    ```html
      Dear [(${name})],
    
      Please find attached the results of the report you requested
      with name "[(${report.name})]".
    
      Sincerely,
        The Reporter.
    ```

* 迭代
    ```html
    [# th:each="item : ${items}"]
      - [(${item})]
    [/]
    ```
    
    等价于
    ```html
    [#th:block th:each="item : ${items}"]
      - [#th:block th:utext="${item}" /]
    [/th:block]
    ```

    * 示例
        ```html
        Dear [(${customer.name})],
        
        This is the list of our products:
        
        [# th:each="prod : ${products}"]
           - [(${prod.name})]. Price: [(${prod.price})] EUR/kg
        [/]
        
        Thanks,
          The Thymeleaf Shop
        ```
        
        渲染效果
        ```html
        Dear Mary Ann Blueberry,
        
        This is the list of our products:
        
           - Apricots. Price: 1.12 EUR/kg
           - Bananas. Price: 1.78 EUR/kg
           - Apples. Price: 0.85 EUR/kg
           - Watermelon. Price: 1.91 EUR/kg
        
        Thanks,
          The Thymeleaf Shop
        ```
        
## 配置详情
* 模板解析器

    Good Thymes Virtual Grocery使用ITemplateResolver实现了ServletContextTemplateResolver接口

## 模板缓存
application.properties
```properties
#关闭thymeleaf缓存
spring.thymeleaf.cache=false
#缓存时间，单位为秒
spring.resources.cache-period=604800
```

## SpringBoot静态资源加版本号
application.yml
```yaml
dglca:
  globals:
    #样式版本号
    version: 20210310001    
    websiteurl: https://www.dglca.com
    webhost: dglca.com
```

绑定数据的JavaBean
```java
package com.hongquan.web.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class Globals {

    @Value("${dglca.globals.version}")
    private String version;

    @Value("${dglca.globals.webhost}")
    private String webHost;

    @Value("${dglca.globals.websiteurl}")
    private String webSiteUrl;
}
```

给url加版本号
```html
    <link th:href="@{/css/common.css?v=}+${Globals.version}" rel="stylesheet" />
```

## 解耦模板逻辑
html文件与thymeleaf数据解耦

模板目录结构
```bash
./templates
├── home.html    // 纯html文件
├── home.th.xml    // thymeleaf绑定数据的逻辑文件
```

* home.html
    ```html
    <!DOCTYPE html>
    <html>
      <body>
        <table id="usersTable">
          <tr>
            <td class="username">Jeremy Grapefruit</td>
            <td class="usertype">Normal User</td>
          </tr>
          <tr>
            <td class="username">Alice Watermelon</td>
            <td class="usertype">Administrator</td>
          </tr>
        </table>
      </body>
    </html>
    ```

* home.th.xml
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <thlogic>
      <attr sel="#usersTable" th:remove="all-but-first">
        <attr sel="/tr[0]" th:each="user : ${users}">
          <attr sel="td.username" th:text="${user.name}" />
          <attr sel="td.usertype" th:text="#{|user.type.${user.type}|}" />
        </attr>
      </attr>
    </thlogic>
    ```
* home.html、home.th.xml合并效果与下面的相同
    ```html
    <!DOCTYPE html>
    <html>
      <body>
        <table id="usersTable" th:remove="all-but-first">
          <tr th:each="user : ${users}">
            <td class="username" th:text="${user.name}">Jeremy Grapefruit</td>
            <td class="usertype" th:text="#{|user.type.${user.type}|}">Normal User</td>
          </tr>
          <tr>
            <td class="username">Alice Watermelon</td>
            <td class="usertype">Administrator</td>
          </tr>
        </table>
      </body>
    </html>
    ```

## 附录A_表达式基本对象
[Base objects](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#base-objects)

## 附录B_表达式内置对象
[Expression Utility Objects](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#appendix-b-expression-utility-objects)
* Execution Info
* Messages
* URIs/URLs
* Conversions
* Dates
* Calendars
* Numbers
* Strings
* Objects
* Booleans
* Arrays
* Lists
* Sets
* Maps
* IDs

## 附录C_标记选择器语法
[Markup Selector Syntax](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#appendix-c-markup-selector-syntax)