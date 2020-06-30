package com.java.aop.impl;

import com.java.aop.api.Calculator;

/**
 * 憋足日志版
 */
public class CalculatorLogImpl implements Calculator {
    @Override
    public int add(int i, int j) {
        System.out.println("\nThe method add begins with [" + i + ", " + j + "]");
        int ret = i + j;
        System.out.println("The method add ends with " + ret );
        return ret;
    }

    @Override
    public int sub(int i, int j) {
        System.out.println("\nThe method sub begins with [" + i + ", " + j + "]");
        int ret = i - j;
        System.out.println("The method sub ends with " + ret );
        return ret;
    }

    @Override
    public int mul(int i, int j) {
        System.out.println("\nThe method mul begins with [" + i + ", " + j + "]");
        int ret = i * j;
        System.out.println("The method mul ends with " + ret );
        return ret;
    }

    @Override
    public double div(int i, int j) {
        System.out.println("\nThe method div begins with [" + i + ", " + j + "]");
        double ret = i / j;
        System.out.println("The method div ends with " + ret );
        return ret;
    }
}
