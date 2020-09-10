package test.com.java.mybatis;

import com.java.bean.Department;
import com.java.bean.DepartmentX;
import com.java.bean.Employee;
import com.java.bean.EmployeeX;
import com.java.dao.DepartmentMapper;
import com.java.dao.EmployeeMapper;
import com.java.dao.EmployeePlusMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
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

    // =========================== EmployeePlusMapper =============================
    @Test
    public void testGetEmployeeById() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeePlusMapper mapper = sqlSession.getMapper(EmployeePlusMapper.class);
            Employee emp = mapper.getEmployeeById(2L);
            System.out.println(emp);

        }
    }

    @Test
    public void testGetEmployeeXById() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
                ) {
            EmployeePlusMapper mapper = sqlSession.getMapper(EmployeePlusMapper.class);
            EmployeeX e = mapper.getEmployeeXById(2L);
            System.out.println(e);
        }
    }

    @Test
    public void testGetEmployeeXById2() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeePlusMapper mapper = sqlSession.getMapper(EmployeePlusMapper.class);
            EmployeeX e = mapper.getEmployeeXById2(2L);
            System.out.println(e);
        }
    }

    @Test
    public void testGetDepartmentById() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department department = mapper.getDepartmentById(1L);
            System.out.println(department);
        }
    }

    // 分步查询
    @Test
    public void testGetEmployeeXStepById() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeePlusMapper mapper = sqlSession.getMapper(EmployeePlusMapper.class);
            EmployeeX e = mapper.getEmployeeXStepById(3L);
            System.out.println(e);
        }
    }

    // 懒加载（按需加载），应用于分步查询
    @Test
    public void testLazyLoading() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeePlusMapper mapper = sqlSession.getMapper(EmployeePlusMapper.class);
            EmployeeX e = mapper.getEmployeeXStepById(3L);
            System.out.println(e.getLastName());
            /*
            * Preparing: SELECT id, last_name, gender, email, dep_id FROM t_employee_x WHERE id = ?
            * 只有一步，没有使用到部门信息，则不查询部门信息
            * */
        }
    }

    // 懒加载（按需加载），应用于分步查询
    @Test
    public void testLazyLoading2() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeePlusMapper mapper = sqlSession.getMapper(EmployeePlusMapper.class);
            EmployeeX e = mapper.getEmployeeXStepById(3L);
            System.out.println(e.getDepartment());

            /*
             * 两步，使用到了信息，先查询员工信息，在根据员工信息中的部门id查询部门信息
             * Preparing: SELECT id, last_name, gender, email, dep_id FROM t_employee_x WHERE id = ?
             * Preparing: SELECT id, dep_name depName FROM t_department WHERE id = ?
             * */
        }
    }

    // ================= DepartmentMapper =====================
    @Test
    public void testGetDepartmentXById() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
                ) {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            DepartmentX d = mapper.getDepartmentXById(3L);
            System.out.println(d);
            System.out.println(d.getEmployeeList());
        }
    }

    // 如何在xml mapper 文件中向方法中传递多个参数
    @Test
    public void testGetDepartmentXStepById2() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
                ) {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            DepartmentX departmentX = mapper.getDepartmentXStepById2(3L);
            System.out.println(departmentX.getDepName());
            System.out.println(departmentX.getEmployeeList());
        }
    }

    // <collection fetchType=""> 延迟方式控制
    @Test
    public void testGetDepartmentXStepById3() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            DepartmentX departmentX = mapper.getDepartmentXStepById3(3L);
            System.out.println(departmentX.getDepName());
        }
    }

    // <discriminator> 鉴别器
    @Test
    public void testGetEmployeeXDiscriminatorById() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeePlusMapper mapper = sqlSession.getMapper(EmployeePlusMapper.class);
            EmployeeX e = mapper.getEmployeeXDiscriminatorById(3L);
            System.out.println("3号员工：男性\n" + e);

            EmployeeX e2 = mapper.getEmployeeXDiscriminatorById(2L);
            System.out.println("2号员工：女性\n" + e2);

            /*
3号员工：男性
EmployeeX{id=3, lastName='kate', gender='1', email='kate', department=null

2号员工：女性
EmployeeX{id=2, lastName='关悦', gender='0', email='guany@gmail.com', department=Department{id=2, depName='行政部'}}
            * */
        }
    }
}
