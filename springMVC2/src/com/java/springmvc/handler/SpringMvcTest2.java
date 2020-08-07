package com.java.springmvc.handler;

import com.java.springmvc.bean.Address;
import com.java.springmvc.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

/*
* @SessionAttributes
*   将Map、Model数据添加到session属性上
*   只能注解在类上，不能注解方法
*   * 通过指定属性名(键名)指定要添加到session域对象中，即value = {}，可以指定多个
*   * 通过指定模型属性的对象类型指定哪些模型属性需要添加到session域对象中，即types = {}，可以指定多个类型
*
* */
// @SessionAttributes(value = {"user"}) // 不指定在session添加模型数据的话，默认只在request域对象中添加模型数据，value 指定类专用
@SessionAttributes(value = {"user"}, types = {String.class}) // 不指定在session添加模型数据的话，默认只在request域对象中添加模型数据，value 指定类专用
@Controller
public class SpringMvcTest2 {
    @RequestMapping("testSessionAttributes")
    public String testSessionAttributes(Map<String, Object> map) {
        User u = new User(1, "蒋大为", "jj123", 63, "jiangdw@qq.com",
                new Address("浙江", "杭州"));
        map.put("user", u);
        map.put("phone", "138 6666 6666");
        return "sessionAttribute";
    }
}
