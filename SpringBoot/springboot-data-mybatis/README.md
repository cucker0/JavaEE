springboot-data-mybatis
==


## 常见问题
* @MapperScan(values = {"xx.xx.mapper"}) 和@Mapper在不同包中,@Mapper注解失效
    ```text
    启动项目报错
    
    Description:
    
    Field userTokenMapper in com.wyz.yangyang.member.service.impl.MemberServiceImpl required a bean of type ‘com.wyz.yangyang.member.mapper2.UserTokenMapper’ that could not be found.
    
    Action:
    
    Consider defining a bean of type ‘com.wyz.yangyang.member.mapper2.UserTokenMapper’ in your configuration.
    
    Disconnected from the target VM, address: ‘127.0.0.1:56527’, transport: ‘socket’
    
    Process finished with exit code 1
    ```
    
    **处理建议**
    ```text
    只是用 @MapperScan 注解，可添加多个mapper 所在的包路径
    ```