package com.java.crudresful.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 定制错误消息，
 *
 */
@ControllerAdvice
public class MyExceptionHandler {
    // /**
    //  * 方法一
    //  *
    //  * 返回json格式数据
    //  *
    //  * @param e
    //  * @return
    //  */
    // @ResponseBody
    // @ExceptionHandler
    // public Map<String, Object> handlerException(Exception e) {
    //     Map<String, Object> map = new HashMap<>();
    //     map.put("msg", e.getMessage());
    //     map.put("code", "601 --user not exist");
    //     return map;
    // }

    /**
     * 方法2
     *  自适应浏览器或其他客户端
     * @param e
     * @return
     */
    @ExceptionHandler
    public String handlerException(Exception e, HttpServletRequest request) {
        // 指定状态码
        request.setAttribute("javax.servlet.error.status_code", 500);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", e.getMessage());
        map.put("code", "601 --user not exist");
        request.setAttribute("ext", map);
        // 转发到/error，让Spring Boot默认的ErrorView处理器处理，它能自适应浏览器或其他客户端
        return "forward:/error";
    }
}
