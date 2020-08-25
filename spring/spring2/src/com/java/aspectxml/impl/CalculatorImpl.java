package com.java.aspectxml.impl;

import com.java.aspectxml.api.Calculator;


public class CalculatorImpl implements Calculator {
    @Override
    public int add(int i, int j) {
        int ret = i + j;
        return ret;
    }

    @Override
    public int sub(int i, int j) {
        int ret = i - j;
        return ret;
    }

    @Override
    public int mul(int i, int j) {
        int ret = i * j;
        return ret;
    }

    @Override
    public double div(int i, int j) {
        double ret = i / j;
        return ret;
    }
}
