package com.java.bean;


/**
 * 员工状态枚举类
 *
 * 希望数据库保存的员工状态值为：
 *  100，200，300等这些值，而不是默认的0，1， 3...或者枚举的名
 *
 */
public enum EmployeeStatus {
    LOGIN(100, "用户已登录"),
    LOGOUT(200, "用户已登录"),
    DISABLE(300, "用户已被禁用");

    private Integer code;
    private String msg;

    private EmployeeStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    // 根据code值返回对应的枚举对象
    public static EmployeeStatus getInstanceByCode(Integer code) {
        switch (code) {
            case 100:
                return LOGIN;
            case 200:
                return LOGOUT;
            case 300:
                return DISABLE;
            default:
                return LOGOUT;
        }
    }
}
