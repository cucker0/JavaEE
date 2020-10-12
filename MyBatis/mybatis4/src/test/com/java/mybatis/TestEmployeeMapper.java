package test.com.java.mybatis;

import com.java.bean.Employee;
import com.java.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.java.common.MybatisUtils.getSqlSessionFactory;

public class TestEmployeeMapper {
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
            // boolean b = mapper.deleteEmployeeById(3L);
            int b = mapper.deleteEmployeeById(3L);
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
            map.put("tableName", "t_employee");
            Employee e = mapper.getEmployeeMap(map);
            System.out.println(e);
        }
    }

    // 测试在 #{} 中指定类型
    // 在没有指定JdbcType时，mysql数据库可以通过，但oracle会报错，默认jdbcTypeForNull=JDBCType.OTHER，因为它无法解析
    @Test
    public void testAddEmployee2() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            Employee emp = new Employee(null, "马苏", "0", null);
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            mapper.addEmployee(emp);
        }

    }

    @Test
    public void testGetAllEmployees() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> employees = mapper.getAllEmployees();
            // for (Employee employee : employees) {
            //     System.out.println(employee);
            // }
            // System.out.println(employees);
            employees.forEach(System.out::println);
        }
    }

    @Test
    public void testGetEmployeeReturnMap() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<String, Object> map = mapper.getEmployeeReturnMap(2L);
            System.out.println(map);
        }
    }

    @Test
    public void testGetEmployeeByLastNameLikeReturnMap() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<Long, Employee> map = mapper.getEmployeeByLastNameLikeReturnMap("%文");
            System.out.println(map);
        }
    }


}
