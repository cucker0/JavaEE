package com.java.bean;

import java.util.List;

/**
 * 封装分页
 * 适用Oracle数据库
 */
public class OraclePage {
    // 开始行号
    private int start;
    // 结束行号
    private int end;
    // 记录总行数
    private int count;
    private List<Employee> emps;

    public OraclePage() {}

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }
}
