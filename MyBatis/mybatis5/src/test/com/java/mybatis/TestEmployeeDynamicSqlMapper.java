package test.com.java.mybatis;

import com.java.bean.Employee;
import com.java.dao.EmployeeDynamicSqlMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

import static com.java.common.MybatisUtils.getSqlSessionFactory;

public class TestEmployeeDynamicSqlMapper {
    @Test
    public void testGetEmployeesByConditionIf() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
            Employee employee = new Employee();
            employee.setId(4L);
            employee.setGender("0");
            List<Employee> eList = mapper.getEmployeesByConditionIf(employee);
            System.out.println(eList);
        }
    }

    @Test
    public void testGetEmployeesByConditionIf_2() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
            Employee employee = new Employee();
            // employee.setId(4L);  // 注释此行，即id为空时，将报语法错误
            employee.setGender("0");
            List<Employee> eList = mapper.getEmployeesByConditionIf(employee);
            System.out.println(eList);
        }
    }

    @Test
    public void testGetEmployeesByConditionWhere() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
            Employee employee = new Employee();
            employee.setGender("1");
            employee.setLastName("%何%");
            // employee.setEmail("guany@gmail.com");
            List<Employee> eList = mapper.getEmployeesByConditionWhere(employee);
            System.out.println(eList);
        }
    }

    // <where> 只能处理前面的AND、OR关键字，而无法处理后面的AND、OR关键字
    @Test
    public void testGetEmployeesByConditionWhere_2() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
            Employee employee = new Employee();
            employee.setGender("1");
            employee.setLastName("%何%");
            List<Employee> eList = mapper.getEmployeesByConditionWhere2(employee);
            System.out.println(eList);
        }
    }

    @Test
    public void testGetEpmloyeesByConditionTrim() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
                ) {
            EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
            Employee employee = new Employee();
            employee.setGender("1");
            employee.setLastName("%何%");
            List<Employee> eList = mapper.getEpmloyeesByConditionTrim(employee);
            System.out.println(eList);
        }
    }
}
