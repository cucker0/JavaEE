package test.com.java.mybatis;

import com.java.bean.Employee;
import com.java.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class TestMybatis {

    // 获取SqlSessionFactory
    public SqlSessionFactory getSqlSessionFactory() {
        // resource 使用相对于src类路径的资源引用
        String resource = "conf/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    @Test
    public void testSelectEmployee() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession();
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            System.out.println("mapper: " + mapper);  // mapper: org.apache.ibatis.binding.MapperProxy@92256
            Employee employee = mapper.getEmployeeById(2L);
            System.out.println(employee);
        }
    }

    @Test
    public void testAddEmployee() {
        try (
                /*
                * SqlSessionFactory.openSession() 默认情况下，autoCommit 为false，在执行sql操作后，需要手动提交
                * 如果传入一个 boolean 的参数
                *       true: autoCommit为true，自动提交sqlSession
                *       false: autoCommit 为false，不会自动提交sqlSession
                * */
                SqlSession sqlSession = getSqlSessionFactory().openSession();
        ) {
            Employee emp = new Employee(null, "沈从文", "1", "shencongwen@qq.com");
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            mapper.addEmployee(emp);
            // 如果SQL mapper里设置获取插入记录时的主键自增ID时，则会封装到Bean对象中
            System.out.println("emp: " + emp);
            // 手动提交session
            sqlSession.commit();
        }
    }


    public Employee getEmpById(Long id) {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession();
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            System.out.println("mapper: " + mapper);
            return mapper.getEmployeeById(id);
        }
    }

    @Test
    public void testUpdateEmployee() {
        try (
                //
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            Employee emp = getEmpById(2L);
            emp.setLastName("花花姐姐");
            System.out.println(emp);
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            int i = mapper.updateEmployee(emp);
            System.out.println(i);
        }
    }

    @Test
    public void testDeleteEmployeeById() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession();
                ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            boolean b = mapper.deleteEmployeeById(3L);
            System.out.println("option status: " + b);
            sqlSession.commit();
        }
    }


    /*
    * Error querying database.
    * Cause: org.apache.ibatis.binding.BindingException:
    *     Parameter 'id' not found. Available parameters are [arg1, arg0, param1, param2]
    *
    * */
    @Test
    public void testGetEmployeeByIdAndLastName() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee e = mapper.getEmployeeByIdAndLastName(4L, "沈从文");
            System.out.println(e);
        }
    }

    @Test
    public void testGetEmployeeByIdAndLastName2() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee e = mapper.getEmployeeByIdAndLastName2(5L, "沈从文");
            System.out.println(e);
        }
    }

    @Test
    public void getEmployeeMap() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("id", 1);
            map.put("lastName", "大山");
            // 指定表名
            map.put("table", "t_employee");
            Employee e = mapper.getEmployeeMap(map);
            System.out.println(e);
        }
    }
}
