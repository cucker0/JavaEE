package test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.java.mp.bean.Employee;
import com.java.mp.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMP {
    private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
    private EmployeeMapper employeeMapper = ioc.getBean("employeeMapper", EmployeeMapper.class);

    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = ioc.getBean("dataSource", DataSource.class);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    /**
     * 通用insert
     */
    @Test
    public void testCommonInsert() {
        Employee e = new Employee();
        e.setLastName("光头强3");
        e.setAge(23);
        e.setEmail("guangguagn@gmail.com");
        e.setGender(1);
        // employeeMapper.insert(e) 返回的结果为插入影响的行数，即插入了多少行
        // 如果设置为 executorType 设置为 BATCH，则update影响的行数结果为-2147482646，
        // If the BATCH executor is in use, the update counts are being lost.

        Integer lines = employeeMapper.insert(e);
        /*
        * // 主键值不匹配
        * Caused by: org.apache.ibatis.reflection.ReflectionException:
        * Could not set property 'id' of 'class com.java.mp.bean.Employee' with value '1335839417142640642'
        * Cause: java.lang.IllegalArgumentException: argument type mismatch
        *
        * 解决方法：
        * 在JaveBean的实体类与表主键对应的属性名中添加注解 @TableId(value = "id", type = IdType.AUTO)
        *   指定主键名，和主键策略
        *
        * 也可以在 Spring配置文件中(applicationContext.xml)中 定义MybatisPlus的全局策略配置
        *
        * // 表名不存在
        * org.springframework.jdbc.BadSqlGrammarException:
        * com.java.mp.mapper.EmployeeMapper.insert (batch index #1) failed.
        * Cause: java.sql.BatchUpdateException: Table 'mp.employee' doesn't exist
        * MybatisPlus 默认使用实体类的类名到数据库找对应的表名，即不类名全部字母小写对应的表名
        *
        * 解决方法：
        * 在JaveBean的实体类定义时添加注解，@TableName(value = "tbl_employee")
        *   value = "tbl_employee": 表的名称
        *
        * */

        System.out.println("影响行数：" + lines);
    }

    // 非表中包含的字段
    @Test
    public void testInsert2() {
        // insert 会根据实体类的每个属性进行非空判断，只有非空的属性对应的字段才会出现到SQL语句中
        Employee e = new Employee();
        e.setLastName("刘亦菲");
        e.setAge(33);
        e.setGender(0);
        e.setEmail("liuyf@qq.com");

        e.setSalary(80000.0);
        Integer lines = employeeMapper.insert(e);
        /*
        * // salary非数据库的表中的字段
        *
        * ### SQL: INSERT INTO tbl_employee   ( last_name,  email,  gender,  age,  salary )  VALUES   ( ?,  ?,  ?,  ?,  ? )
        * ### Cause: java.sql.SQLSyntaxErrorException: Unknown column 'salary' in 'field list'
        * ; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column 'salary' in 'field list'
        *
        * // 解决方法：
        *   JavaBean实体的对应的属性中添加注解：指定该属性非表中的字段
        *   @TableField(exist = false)
        * */
        System.out.println(lines);
        System.out.println("获取插入的主键ID：" + e.getId());
    }

    /**
     * 通用update
     */
    @Test
    public void testCommonUpdate() {
        Employee employee = new Employee();
        employee.setId(36);
        employee.setAge(18);
        employee.setLastName("big刘亦菲");
        // 只更新非空字段
        Integer line = employeeMapper.updateById(employee);
        // Preparing: UPDATE tbl_employee SET last_name=?, age=? WHERE id=?
        // Parameters: big刘亦菲(String), 18(Integer), 36(Integer)
        System.out.println(line);
        /*
        * WARNING: Illegal reflective access by org.apache.ibatis.reflection.Reflector
        *
        * 这主要是因为MybatisPlus 2.3.2 使用的mybatis为3.4.1，
        * 因mybatis版本较低，解决方法，升级mybatis到3.5，当然MybatisPlus是自动关联mybatis，所以升级MybatisPlus到3.4.1就可以了
        * */

        // // updateAllColumnById(employee), 更新所有字段，已经废弃。
        // Integer line2 = employeeMapper.updateAllColumnById(employee);
        // /*
        // * Preparing: UPDATE tbl_employee SET last_name=?,email=?,gender=?,age=? WHERE id=?
        // * Parameters: big刘亦菲(String), null, null, 18(Integer), 36(Integer)
        // * */
    }

    /**
     * 通用select
     */
    // 通过ID查询
    @Test
    public void testCommonSelect() {
        Employee employee = employeeMapper.selectById(6);
        System.out.println(employee);
    }

    // selectOne 通过多个列进行查询，id + lastName
    @Test
    public void testCommonSelect2() {
        Employee e = new Employee();
        e.setId(18);
        e.setLastName("刘亦菲");
        Employee employee = employeeMapper.selectOne(new QueryWrapper<Employee>(e));
        System.out.println(employee);
    }

    // selectBatchIds 通多个id查询
    @Test
    public void testCommonSelect3() {
        List<Integer> idList = new ArrayList<>();
        idList.add(3);
        idList.add(5);
        idList.add(8);
        List<Employee> employees = employeeMapper.selectBatchIds(idList);
        employees.forEach(System.out::println);
    }

    // selectByMap 通过Map封装多个条件 进行查询
    @Test
    public void testCommonSelect4() {
        HashMap<String, Object> map = new HashMap<>();
        // key: 表中的列名
        map.put("last_name", "Tom");
        map.put("gender", 1);
        List<Employee> employees = employeeMapper.selectByMap(map);
        System.out.println(employees);
    }

    // 分页查询
    // 这里使用的是Mybatis的内存分页，与mysql的 limit offset size是有区别的
    @Test
    public void testCommonSelect5() {
        // new Page<>(current, size)
        //      current:页码
        //      size: 多少条记录分一页
        Page<Employee> employeePage = employeeMapper.selectPage(new Page<Employee>(1, 2), new QueryWrapper<Employee>());
        System.out.println("employeePage: " + employeePage.getRecords());
    }

    /**
     * 通用删除操作
     */
    // 根据id进行删除
    @Test
    public void testCommonDelete() {
        Integer line = employeeMapper.deleteById(11);
        System.out.println(line);
    }

    // deleteByMap，多条件删除
    @Test
    public void testCommonDelete2() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("last_name", "光头强2");
        columnMap.put("email", "guangguagn@gmail.com");
        Integer lines = employeeMapper.deleteByMap(columnMap);
        System.out.println("受影响的行数：" + lines);
    }

    // deleteBatchIds(Collection) 批量删除
    @Test
    public void testCommonDelete3() {
        List<Integer> idList = new ArrayList<>();
        idList.add(6);
        idList.add(7);
        idList.add(8);
        Integer lines = employeeMapper.deleteBatchIds(idList);
        System.out.println("受影响的行数：" + lines);
    }
}
