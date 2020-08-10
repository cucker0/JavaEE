package com.java.springmvc.handler;

import com.java.springmvc.bean.Address;
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

    /*
    * 有标记@ModelAttribute的方法，会在每个目标方法执行之前被SpringMVC调用
    *
    * */
    @ModelAttribute
    public void getUser(@RequestParam(value = "id", required = false) Integer id,
                        Map<String, Object> map) {
        System.out.println("getUser# ModelAttribute");
        if (id != null) {
            // 模拟从数据库查询数据并封装成对象
            User user = new User(2, "邝美云", "kmy123", 18, "kuangmy@gmail.com", new Address("河南", "郑州"));
            System.out.println("从数据库获取的user: " + user);
            // map.put("user", user);
            map.put("u", user);
        }
    }

    /*
    * 运行过程
    *   1. 执行@ModelAttribute注解的方法：从数据库中取出对象，把对象放入了map中，键为：user
    *   2. SpringMVC从map中取出User对象，并把表单的请求参赋给该User对象的对应属性。
    *   3. SpringMVC把上述对象传入目标方法的参数
    *   注意：在@ModelAttribute修饰的方法中，放入到Map时的键需要与目标方法入参类名的第一个字母小写的字条串一致
    *
    *
    * 源码分析
    *   1. 调用@ModelAttribute注解的方法，实际上把@ModelAttribute方法中Map中的数据放在了implicitModel 中
    *   2. 解析请求处理器的目标参数，实际上该目标参数来自于WebDataBinder对象的tagert属性
    *       1). 创建WebDataBinder对象
    *           ①确定objectName属性：若传入的attrName属性值为""，则 objectName 为类名第一个字母小写的字符串
    *           *注意: attrName. 若目标方法的 POJO 属性使用了 @ModelAttribute 来修饰, 则 attrName 值即为 @ModelAttribute的 value 属性值
     *          ②确定target属性
     *              在 implicitModel 中查找 attrName 对应的属性值. 若存在, ok
     *              *若不存在: 则验证当前 Handler 是否使用了 @SessionAttributes 进行修饰, 若使用了, 则尝试从 Session 中获取 attrName 所对应的属性值.
     *              若 session 中没有对应的属性值, 则抛出了异常.
     *
     *              若 Handler 没有使用 @SessionAttributes 进行修饰, 或 @SessionAttributes 中没有使用 value 值指定的 key
     *              和 attrName 相匹配, 则通过反射创建了 POJO 对象
     *      2). SpringMVC把表单的请求参数赋给了WebDataBinder的target对应的属性
     *      3). * SpringMVC会把WebDataBinder的attrName和target传给implicateModel，从而传到reqeust域对象中
     *      4). 把WebDataBinder的target作为参数传递给目标方法的入参
     *
     * SpringMVC 确定目标方法 POJO 类型入参的过程
     *      1. 确定一个key
     *          ①若目标方法的 POJO 类型的参数木有使用 @ModelAttribute 作为修饰, 则 key 为 POJO 类名第一个字母的小写
     *          ②若使用了  @ModelAttribute 来修饰, 则 key 为 @ModelAttribute 注解的 value 属性值.
     *          ③implicitModel 中查找 key 对应的对象, 若存在, 则作为入参传入
     *      2. 在 implicitModel 中查找 key 对应的对象, 若存在, 则作为入参传入
     *          ①若在 @ModelAttribute 标记的方法中在 Map 中保存过, 且 key 和 1 确定的 key 一致, 则会获取到.
     *      3. 若 implicitModel 中不存在 key 对应的对象, 则检查当前的 Handler 是否使用 @SessionAttributes 注解修饰,
     *          若使用了该注解, 且 @SessionAttributes 注解的 value 属性值中包含了 key, 则会从 HttpSession 中来获取 key 所
     *          对应的 value 值, 若存在则直接传入到目标方法的入参中. 若不存在则将抛出异常.
     *      4. 若Handler没有标识@SessionAttributes注解或@SessionAttribute 注解的value值中不包含key，
     *          则会通过反射来创建 POJO 类型的参数, 传入为目标方法的参数
     *      5. SpringMVC会把key和POJO类型的对象保存到implicateModel中，进而会保存到request域对象中
     * */
    @RequestMapping("/testModelAttribute")
    // public String testModelAttribute(User user) { // @ModelAttribute注解的方法的map键名需要为 User类名(第一个字母小写)，当当前类设置了@SessionAttributes时，会报500异常，因为会去Sessioin域对象中找user属性值
    public String testModelAttribute(@ModelAttribute(value = "u") User user) { // 若不指定@ModelAttribute(value = "u")，则@ModelAttribute注解的方法的map键名需要为 User类名(第一个字母小写)
        System.out.println("testModelAttribute# user 修改为：" + user);
        return SUCCESS;
    }


}
