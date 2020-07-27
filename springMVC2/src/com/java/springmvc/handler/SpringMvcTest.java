package com.java.springmvc.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *  当@RequestMapping 注解同时使用在类名和方法上，则需要具体的控制器方法的URL为 工程URL + 类URL + 方法URL
 *  当@RequestMapping 注解只使用在方法名上，则需要具体的控制器方法的URL为 工程URL + 方法URL
 *  以上两种情况，都会自动补齐/
 *
 *  @RequestMapping("path") path以不以/开头都可以，效果是一样的
 *
 */
@RequestMapping("springmvc")
@Controller
public class SpringMvcTest {
    private final String SUCCESS = "success";

    @RequestMapping("testRequestMapping")
    public String testRequestMapping() {
        System.out.println("testRequestMapping controller... ");
        // 这个返回的是一个视图资源文件的名称，将由view视图解析器，解析出具体的文件路径
        return SUCCESS;
    }

    // 限定URL、请求方法，如果语法方法不对，则报：HTTP Status 405 – 方法不允许
    @RequestMapping(value = "/testMethod", method = RequestMethod.POST)
    public String testMethod() {
        System.out.println("testMethod controller...");
        return SUCCESS;
    }

    // 表示，需要包含参数username, 参数age的值不能为10
    // HTTP Status 400 – 错误的请求
    // UnsatisfiedServletRequestParameterException: Parameter conditions "username, age!=10" not met for actual request
    @RequestMapping(value = "/testParams", params = {"username", "age!=10"})
    public String testParams() {
        System.out.println("testMethod controller...");
        return SUCCESS;
    }

    // 限定header，可以多个限制条件组合起来，它们之间是 and 关系
    @RequestMapping(value = "/testHeaders", headers = {"Accept-Language=zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7"})
    public String testHeaders() {
        System.out.println("testHeaders controller...");
        return SUCCESS;
    }

    // path支持ant风格的匹配符
    /*
        ? 任意字符
        * 任意多个字符
        ** 多层目录
     */
    @RequestMapping("/a/*/testAntPath")
    public String testAntPath() {
        System.out.println("testAntPath controller...");
        return SUCCESS;
    }

    // 路径变量的获取
    @RequestMapping("/testPathVariable/{id}")
    public String testPathVariable(@PathVariable("id") Integer id) {
        System.out.println("testPathVariable 占位参数：id=" + id);
        return SUCCESS;
    }


}
