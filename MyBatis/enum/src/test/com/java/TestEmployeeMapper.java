package test.com.java;

import com.java.bean.Department;
import com.java.bean.Employee;
import com.java.bean.EmployeeStatus;
import com.java.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import static com.java.common.MybatisUtils.getSqlSessionFactory;

public class TestEmployeeMapper {

    /*
    * 默认情况下，即未增加 EmployeeStatusTypeHandler时
    *   添加employee进会把enum类的对象名作为值插入数据中
    *
    * */
    @Test
    public void testAddEmployee() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee e = new Employee(null, "玛丽莎", "0", "malisha@wukelan.com", EmployeeStatus.LOGIN, new Department(1L, null));
            mapper.addEmployee(e);
        }
    }

    @Test
    public void testAddEmployee_2() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee e = new Employee(null, "sushan", "0", "sushan@google.com", EmployeeStatus.LOGIN, new Department(1L, null));
            mapper.addEmployee(e);
            System.out.println(e.getId());
        }
    }

    // 需要在mybatis-config.xml中 配置<typeHandler>
    @Test
    public void testGetEmployeeById() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
                ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee e = mapper.getEmployeeById(2L);
            System.out.println(e);
        }
    }

    // 不需要在mybatis-config.xml中 配置<typeHandler>
    @Test
    public void testGetEmployeeById2() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee e = mapper.getEmployeeById2(14L);
            System.out.println(e);
        }
    }

    @Test
    public void testAddEmployee2() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee e = new Employee(null, "jenny", "0", "jenny@google.com", EmployeeStatus.LOGIN, new Department(1L, null));
            mapper.addEmployee(e);
            System.out.println(e.getId());
        }
    }
}
