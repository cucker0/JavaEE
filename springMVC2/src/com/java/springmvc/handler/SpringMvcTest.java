package com.java.springmvc.handler;

import com.java.springmvc.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;


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

    /*
    * @RequestParam 映射请求参数
    *   value  请求的参数名
    *   required  该参数是否必传，默认为true
    *   defaultValue  该参数的默认值
    *
    * */
    @RequestMapping(value = "/testRequestParam")
    public String testRequestParam(@RequestParam(value = "name") String n,
                                   @RequestParam(value = "age", required = false, defaultValue = "1") int age) {
        System.out.println("testRequestParam # 姓名：" + n + " 年龄：" + age + "岁");
        return SUCCESS;
    }

    /**
     * @RequestHeader 映射请求头
     *      value  请求头名
     *      required  该参数是否必传，默认为true
     *      defaultValue  该参数的默认值
     * @param ua
     * @return
     */
    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "User-Agent") String ua) {
        System.out.println("testRequestHeader# User-Agent: " + ua);
        return SUCCESS;
    }
    /*
    * @CookieValue 映射cookie
    *      value  cookie名
    *      required  该参数是否必传，默认为true
    *      defaultValue  该参数的默认值
    * .jsp页面默认有一个cookie，名为：JSESSIONID
    * */
    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String sessionId) {
        System.out.println("testCookieValue# sessionId: " + sessionId);
        return SUCCESS;
    }
    /*
    * POJO(JavaBean对象)
    *   Spring MVC 会按请求参数名和POJO（Bean）属性进行自动匹配
    *   自动填充该对象的属性值。支持级联属性。
    *   如：address.province， address.city
    *
    * */
    @RequestMapping("/testPojo")
    public String testPojo(User user) {
        System.out.println("testPojo# user: " + user);
        return SUCCESS;
    }

    /*
    * 支持原生的Servlet Api
    *   支持以下类型
    *   HttpServletRequest
    *   HttpServletResponse
    *   HttpSession
    *   java.security.Principal
    *   Locale InputStream
    *   OutputStream
    *   Reader
    *   Writer
    * */
    @RequestMapping("testServletApi")
    public void testServletApi(HttpServletRequest request, HttpServletResponse response, Writer out) throws IOException {
        System.out.println("testServletApi# request: " + request + ", \n    response: " + response);
        out.write("hello ServletApi, ...");  // 响应内容
        // return SUCCESS;
    }

    /*
    * ModelAndView, 目标方法的返回值可以是 ModelAndView类型
    * 其中可以包含view和模型信息
    *
    * SpringMVC会把ModelAndView中的数据添加到request域对象中
    * */
    @RequestMapping("testModelAndView")
    public ModelAndView testModelAndView() {
        String viewName = "time";
        ModelAndView modelAndView = new ModelAndView(viewName);
        // 添加数据模型到 ModelAndView中
        modelAndView.addObject("time", new Date());
        return modelAndView;
    }

    /*
    * 目标方法可以添加Map类型、Model类型或ModelMap类型的参数
    * */
    @RequestMapping("testMap")
    public String testMap(Map<String, Object> map) {
        map.put("users", Arrays.asList("啦啦", "迪斯", "丁丁"));
        return "map";
    }
}
