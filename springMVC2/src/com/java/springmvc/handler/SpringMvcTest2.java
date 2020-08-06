package com.java.springmvc.handler;

import com.java.springmvc.bean.Address;
import com.java.springmvc.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

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
