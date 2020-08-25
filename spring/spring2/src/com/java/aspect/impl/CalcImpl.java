package com.java.aspect.impl;

import com.java.aspect.api2.Calc;
import org.springframework.stereotype.Component;

@Component("calc")
public class CalcImpl implements Calc {
    @Override
    public int mul(int a, int b) {
        int ret = a * b;
        System.out.println(a + " * " + b + " = " + ret);
        return ret;
    }

    @Override
    public int div(int a, int b) {
        int ret = a / b;
        System.out.println(a + " / " + b + " = " + ret);
        return ret;
    }
}
