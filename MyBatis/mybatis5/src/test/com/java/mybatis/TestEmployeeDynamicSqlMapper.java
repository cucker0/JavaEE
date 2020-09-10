package test.com.java.mybatis;

import com.java.bean.Employee;
import com.java.common.MybatisUtils;
import com.java.dao.EmployeeDynamicSqlMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestEmployeeDynamicSqlMapper {
    @Test
    public void testGetEmployeesByConditionIf() {
        try (
                SqlSession sqlSession = MybatisUtils.getSqlSessionFactory().openSession(true);
        ) {
            EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
            Employee employee = new Employee();
            // employee.setId(4L);  // 注释此行，即id为空时，将报语法错误
            employee.setGender("0");
            List<Employee> e = mapper.getEmployeesByConditionIf(employee);
            System.out.println(e);
        }
    }

    @Test
    public void testGetEmployeesByConditionIf2() {
        try (
                SqlSession sqlSession = MybatisUtils.getSqlSessionFactory().openSession(true);
        ) {
            EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
            Employee employee = new Employee();
            employee.setGender("0");
            // employee.setEmail("guany@gmail.com");
            List<Employee> e = mapper.getEmployeesByConditionIf2(employee);
            System.out.println(e);
        }
    }
}
