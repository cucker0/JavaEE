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


    /*
    * REST风格的CRUD
    *   动作  REST                传统
    *   新增  /order  POST        同左
    *   删除  /order/1  DELETE    delete?id=1
    *   修改  /order/1  PUT       update?id=1
    *   获取  /order/1  GET       get?id=1
    *
    * */
    // REST GET
    @RequestMapping(value = "testRest/{id}", method = RequestMethod.GET)
    public String testRest(@PathVariable("id") Integer id) {
        System.out.println("REST GET: id = " + id);
        return SUCCESS;
    }

    // REST POST
    @RequestMapping(value = "testRest", method = RequestMethod.POST)
    public String testRest() {
        System.out.println("REST POST");
        return SUCCESS;
    }

    /*
    * REST PUT
    * tomcat 8及以上，访问时报异常 HTTP Status 405 – 方法不允许，
    *   JSP 只允许 GET、POST 或 HEAD。Jasper 还允许 OPTIONS
    *
    * 处理方法：在转发的view文件里添加 isErrorPage="true" ，如：
    * <%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
    *
    * */
    @RequestMapping(value = "testRest/{id}", method = RequestMethod.PUT)
    public String testRestPut(@PathVariable Integer id) {
        System.out.println("REST PUT: id = " + id);
        return SUCCESS;
    }

    // REST DELETE
    @RequestMapping(value = "testRest/{id}", method = RequestMethod.DELETE)
    public String testRestDELETE(@PathVariable("id") Integer id) {
        System.out.println("REST DELETE: id = " + id);
        return SUCCESS;
    }
}
