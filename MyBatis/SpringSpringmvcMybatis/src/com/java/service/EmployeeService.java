package com.java.service;

import com.java.bean.Employee;
import com.java.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    // @Autowired
    // private SqlSession sqlSession;

    public List<Employee> getAllEmps() {
        // EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        // List<Employee> employees = mapper.getAllEmployees();
        return employeeMapper.getAllEmployees();
    }
}
