YAML书写规范
==

## 什么事YAML
YAML是一个类似 XML、JSON 的标记性语言。YAML强调以数据为中心，并不是以标识语言为重点，号称“一种人性化的数据格式语言”

* YAML与XML、JSON
    * YAML与XML
        >具有XML同样的优点，但比XML更加简单、敏捷
    * YAML与JSON
        ```text
        JSON可看做YAML的子集。
        YAML写法比JSON更简洁，键(key)、值(value)可以不用引号包裹
        YAML可描述更复杂的结构，例如：锚点引用数据
        ```

## YAML数据结构
YAML文件可以有一个或多个文档组成（即相对独立的组织结构组成）

**元素**
* \---  
    文档分隔符。  
    只有一个文档时，可以省略不写

* \***  
    文档结束符。  
    不需要为每个文档使用\*** 结束。但对于网络传输或者流来说，作为明确结束的符号，有利于软件处理。
* 标量（普通的数据类型。键值对）
* 序列（数组、List）
* Map

**示例**
```yaml

---
time: 20:03:20
player: Sammy Sosa
action: strike (miss)
...
---
time: 20:03:47
player: Sammy Sosa
action: grand slam
...
```

```yaml
# Ranking of 1998 home runs
---
- Mark McGwire
- Sammy Sosa
- Ken Griffey

# Team ranking
---
- Chicago Cubs
- St Louis Cardinals
```

## YAML书写规范
### 基本规则
* 大小写敏感
* YAML文件使用Unicode编码，例如UTF-8
* \#行注释符
    ```yaml
    # 客户订单
    date: 2015/02/01
    customer:
      - name: Jai
    items:
      - no: 1234  # 订单号
      - descript: cpu
    ```
* 使用空格缩进表示数据的层级关系，进制使用tab表缩进
    通常建议使用2个空格。只要元素对齐就表示这些元素属于一个层级
* key不用引号包裹
* 字符串可以不用引号包裹
* ''单引号包裹的值，会将字符串中的特殊字符进行转义
* ""双引号包裹的值，不会对字符串进行转义

### scalars标量
单个不可再分隔的值
```yaml
name: Lily
gender: female
```

### 对象
键值对的集合，即Map、Set、字典
```yaml
person:
  name: Lily
  gender: female

# 对应的JSON
{'person': {'name':'Lily', 'gender':'female'}}
```

* 对象作为key
    ```yaml
    ? [blue, red, green]: Color
    
    # 等价于
    ? - blue
      - reg
      - gree
     : Color
    ```

### Map
```yaml
# 客户订单
items: {no: 1234, descript: cpu, price: ￥800.00}
```

### sequence序列
一组按次序排列的值，也叫数组/List

* 单个列表项
    ```text
    
    - 值1
    - 值2
    ```
* []数组
    ```text
    [blue, red, green]
    ```
* 组合序列
    ```text
    - [blue, red, green]  # 列表项本身也是一个列表
    - [Age, Bag]
    - site: {osc: www.oschina.net, baidu: www.baidu.com}
    ```
### 组合表示
```yaml
Color:
  - blue
  - red
  - green

# 对应的JSON
{'Color': ['blue', 'red', 'green']}

div:
  - border: {color: red, width: 2px}
  - background: {color: green}
  - padding: [0, 10px, 0, 10px]

# 使用缩进表示的键值表与列表项
items:
  - item: cpu
    model: i3
    price: ￥800.00
  - item: HD
    model: WD
    price: ￥450.00

# 上面使用 “-” 前导与缩进来表示多个列表项，对应的JSON
{'items': [{'item':'cpu', 'model':'i3', 'price':'￥800.00'}, {'item':'HD', 'model':'WD', 'price':'￥450.00'}]}
```

## 文本块
* | 标注保留换行的文本块
    ```yaml
    mytex: |
      hello,
      hello!
    ```
    mytex值的输出效果：
    ```text
    hello,
    hello!
    ```
* |- 删除换行符
    ```yaml
    mytex: |-
      hello,
      hello!
    ```
    mytex输出效果
    ```text
    hello,hello!
    ```
* |+ 保留换行符
    ```yaml
      hello,
      hello!
    ```
    mytex输出效果
    ```text
    hello,\nhello!\n
    ```
* \> 将块中换行符替换为空格，最终连接成一行
    ```yaml
    msg: > hello
    world!
    ```
    msg值的输出效果
    ```text
    hello world!
    ```
## 锚点与引用
* &锚点名  
    定义数据锚点，即要被引用的数据
* *锚点名  
    引用那个锚点的数据  
    **注意** *引用部分不能再追加内容

示例
```yaml
name: &a yaml
book: *a
books:
  - java
  - *a
  - python
```

输出book值的效果
```text
yaml
```

输出books的效果
```text
[java, yaml, python]
```

## 数据类型的约定
### 字符串
使用’‘或""包裹，或不使用引号包裹

### 数字
```text
1234567890  # 整型
0o34  # 八进制数。第二个是字母'o'
0xF1  # 十六进制数
13.67  # 固定浮点数
1.23e+3  # 指数
.inf  # 无穷大
-.inf  # 负无穷
```

### 布尔类型
```text
true
false
```

### 空值
```text
null
~
```

### 日期
使用 iso-8601 标准表示日期
```text
date: 2021-03-03
datetime: 2021-03-03T02:02:00.1z
iso8601: 2021-03-03t21:59:43.10-05:00

# 在springboot中yaml文件的时间格式
date: yyyy/MM/dd HH:mm:ss
```

## 强制类型转换
使用!表示强制转换数据类型。

* ! 自定义的类型
* !! 内置的数据类型
    ```text
    !!int               # 整数类型
    !!float             # 浮点类型
    !!bool              # 布尔类型
    !!str               # 字符串类型
    !!binary            # 二进制，也是字符串类型
    !!timestamp         # 日期时间类型
    !!null              # 空值
    !!set               # 集合
    !!omap              # 键值列表
    !!pairs             # 对象列表
    !!seq               # 序列，也是列表
    !!map               # 键值表
    ```

* 示例
    ```yaml
    money: !!str 123
    date: !Boolean true
    # Base64图片
    picture: !!binary |
      R0lGODlhDAAMAIQAAP//9/X
      17unp5WZmZgAAAOfn515eXv
      Pz7Y6OjuDg4J+fn5OTk6enp
      56enmleECcgggoBADs=
    employee:
      name: zhang3
      age: !!int 20
      salary: !!float 9999.60
    ```


