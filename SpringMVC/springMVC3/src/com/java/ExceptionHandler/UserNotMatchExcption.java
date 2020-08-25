package com.java.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "用户名或验证码不正确!!!")
public class UserNotMatchExcption extends RuntimeException {

}
