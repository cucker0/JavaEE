package com.java.dao;

import com.java.bean.OraclePage;

// 通过存储封装分页
public interface EmployeeOracleMapper {
    void getPageByProcedure(OraclePage oraclePage);
}
