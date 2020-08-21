package com.java.ExceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    // 对全局的Exception生效
    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView handlerArithmeticException(Exception e) {
        System.out.println("[GlobalExceptionHandler]出异常了：" + e);
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", e);
        return mv;
    }

}
