package test.com.java.mybatis;

import com.java.bean.Department;
import com.java.bean.Employee;
import com.java.dao.EmployeeDynamicSqlMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    public void testGetEmployeesByConditionChoose() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
                ) {
            EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
            Employee e = new Employee();
            // e.setId(3L);
            // e.setEmail("amy@z.com");
            List<Employee> employeeList = mapper.getEmployeesByConditionChoose(e);
            System.out.println(employeeList);
        }
    }

    @Test
    public void testUpdateEmployeeById() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
                ) {
            EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
            Employee e = new Employee();
            e.setId(3L);
            e.setEmail("amy@qq.com");
            mapper.updateEmployeeById(e);
        }
    }

    @Test
    public void updateEmployeeById2() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
                ) {
            EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
            Employee e = new Employee();
            e.setId(3L);
            e.setEmail("anndy@google.com");
            mapper.updateEmployeeById2(e);
        }
    }

    @Test
    public void testGetEmployeesByIds() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true)
        ) {
            EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
            List<Long> ids = Arrays.asList(1L, 3L);
            List<Employee> employeeList = mapper.getEmployeesByIds(ids);
            System.out.println(employeeList);
        }
    }

    @Test
    public void testGetEmployeesByIds2() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true)
        ) {
            EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
            // List<Long> ids = Arrays.asList(1L, 3L);
            List<Long> ids = new ArrayList<>();
            List<Employee> employeeList = mapper.getEmployeesByIds2(ids);
            System.out.println(employeeList);
        }
    }

    @Test
    public void testBatchInsertEmployees() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true)
        ) {
            EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
            List<Employee> employeeList = new ArrayList<>();
            Employee e1 = new Employee(null, "亚历山大", "1", "yali3d@edu.com", new Department(1L, null));
            Employee e2 = new Employee(null, "秦王嬴政", "1", "yingzheng@zh.com", new Department(4L, null));
            Employee e3 = new Employee(null, "Elizabeth Tudor", "0", "elizabeth@uk.com", new Department(3L, null));
            employeeList.add(e1);
            employeeList.add(e2);
            employeeList.add(e3);
            Boolean b = mapper.batchInsertEmployees(employeeList);
            System.out.println(b);
        }
    }

    @Test
    public void testBatchInsertEmployees2() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true)
        ) {
            EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
            List<Employee> employeeList = new ArrayList<>();
            Employee e1 = new Employee(null, "亚历山大", "1", "yali3d@edu.com", new Department(1L, null));
            Employee e2 = new Employee(null, "秦王嬴政", "1", "yingzheng@zh.com", new Department(4L, null));
            Employee e3 = new Employee(null, "Elizabeth Tudor", "0", "elizabeth@uk.com", new Department(3L, null));
            employeeList.add(e1);
            employeeList.add(e2);
            employeeList.add(e3);
            Boolean b = mapper.batchInsertEmployees2(employeeList);
            System.out.println(b);
        }
    }

    // 切换为oracle，oracle批量插入方式1
    @Test
    public void testOracleBatchInsertEmployees() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true)
        ) {
            EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
            List<Employee> employeeList = new ArrayList<>();
            Employee e1 = new Employee(null, "亚历山大", "1", "yali3d@edu.com", new Department(1L, null));
            Employee e2 = new Employee(null, "秦王嬴政", "1", "yingzheng@zh.com", new Department(4L, null));
            Employee e3 = new Employee(null, "Elizabeth Tudor", "0", "elizabeth@uk.com", new Department(3L, null));
            employeeList.add(e1);
            employeeList.add(e2);
            employeeList.add(e3);
            mapper.oracleBatchInsertEmployees(employeeList);
        }
    }

    // 切换为oracle，oracle批量插入方式2
    @Test
    public void testOracleBatchInsertEmployees2() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true)
        ) {
            EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
            List<Employee> employeeList = new ArrayList<>();
            Employee e1 = new Employee(null, "亚历山大4", "1", "yali3d4@edu.com", new Department(1L, null));
            Employee e2 = new Employee(null, "秦王嬴政4", "1", "yingzheng4@zh.com", new Department(4L, null));
            Employee e3 = new Employee(null, "Elizabeth Tudor4", "0", "elizabeth4@uk.com", new Department(3L, null));
            employeeList.add(e1);
            employeeList.add(e2);
            employeeList.add(e3);
            mapper.oracleBatchInsertEmployees2(employeeList);
        }
    }
}
