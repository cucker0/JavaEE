MyBatis
==

## MyBatis概述
```text
MyBatis使用介绍：https://mybatis.org/mybatis-3/

项目地址：https://github.com/mybatis/mybatis-3
```


## SQL映射文件
* 单个参数
    ```text
    mybatis不会做特殊处理，
    
    取出值用法：
    #{参数名/任意名}
    ```

* 多参数
    ```text
    多个参数会被封装成一个map对象
        key: param1, ..., paramN，或者参数的索引也可以
        value: 传入的参数值
        
        #{key}: 从map对象中获取指定的key值
      
        当未给参数指定key名时，可以使用如下的key名
        #{arg0}, #{arg1}, ..., #{argN-1}  // 指定index方式
        #{param1}, #{param2}, ..., #{paramN}  // 指定序号方式
    ```
    
    异常
    ```text
    Error querying database.
    Cause: org.apache.ibatis.binding.BindingException:
        Parameter 'id' not found. Available parameters are [arg1, arg0, param1, param2]
    ```
    
    * 为参数指定key名
        ```text
        在dao接口方法的参数前使用如下注解
        
        @Param("keyName")
            明确指定封装参数时map的key
        ```
        
## 注意
* sql映射文件注释说明
    ```text
    注意sql语句块内的
        -- sql注释
        # 注释 
    会引起其他歧义
    所以在sql语句块内，建议使用xml的注释
    
    ```


```text
public Employee getEmp(Integer id,@Param("e")Employee emp);
	取值：id==>#{param1}    lastName===>#{param2.lastName/e.lastName}

##特别注意：如果是Collection（List、Set）类型或者是数组，
		 也会特殊处理。也是把传入的list或者数组封装在map中。
			key：Collection（collection）,如果是List还可以使用这个key(list)
				数组(array)
public Employee getEmpById(List<Integer> ids);
	取值：取出第一个id的值：   #{list[0]}
	
```