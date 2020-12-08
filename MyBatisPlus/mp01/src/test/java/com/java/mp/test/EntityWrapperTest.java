package com.java.mp.test;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.java.mp.bean.Employee;
import com.java.mp.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;


/**
 * 条件构造器 EntityWrapper
 *      实体包装器，用于处理 sql 拼接，排序，实体参数查询等！
 *      工作原理，生成SQL片段
 *      补充说明：使用的是数据库字段，不是java属性
 * 实体包装器 EntityWrapper 继承 Wrapper
 */
public class EntityWrapperTest {
    private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
    private EmployeeMapper employeeMapper = ioc.getBean(EmployeeMapper.class);

    // 拼接 sql 方式 一
    @Test
    public void testSQL() {
        EntityWrapper<Employee> ew = new EntityWrapper<>();
        ew.setEntity(new Employee());
        ew.where("last_name={0}", "'Jone'").and("id=1")
                .orNew("gender=0")
                .or("email={0}", "'tom@baomidou.com'")
                .groupBy("gender");
        System.out.println(ew.getSqlSegment());
        /*
        // 打印生成的SQL片段
        AND (last_name=#{ew.paramNameValuePairs.MPGENVAL1} AND id=1)
        OR (gender=0 OR email=#{ew.paramNameValuePairs.MPGENVAL2})
        GROUP BY gende
         */
    }

    /**
     * 条件禁构造器--查询操作
     */
    // 分页查询查询，年龄在18-50岁之间，且性别为男，且姓名为Tom的所有用户
    @Test
    public void testEntityWrapperSelect() {
        List<Employee> employees = employeeMapper.selectPage(new Page<Employee>(1, 3),
                new EntityWrapper<Employee>()
                        .between("age", 18, 50)
                        .eq("gender", 1)
                        .eq("last_name", "Tom")
        );
        System.out.println(employees);
    }

    // 查询tbl_employee表中，性别为女，并且名字中带有"头"，或者邮箱中带有"a"的记录
    // or()与orNew()区别，orNew()新生成一个()SQL语句
    @Test
    public void testEntityWrapperSelect2() {
        EntityWrapper<Employee> ew = new EntityWrapper<>();
        ew.eq("gender", 0)
                .like("last_name", "头")
                .or()
                .like("email", "a");

        EntityWrapper<Employee> ew2 = new EntityWrapper<>();
        ew2.eq("gender", 0)
                .like("last_name", "头")
                .orNew()
                .like("email", "a");

        List<Employee> employees = employeeMapper.selectList(ew);
        // Preparing: SELECT id,last_name AS lastName,email,gender,age FROM tbl_employee WHERE (gender = ? AND last_name LIKE ? OR email LIKE ?)
        // Parameters: 0(Integer), %头%(String), %a%(String)
        System.out.println(employees);

        List<Employee> employees2 = employeeMapper.selectList(ew2);
        // Preparing: SELECT id,last_name AS lastName,email,gender,age FROM tbl_employee WHERE (gender = ? AND last_name LIKE ?) OR (email LIKE ?)
        // Parameters: 0(Integer), %头%(String), %a%(String)
        System.out.println(employees2);
    }

    // 查询性别为女的，根据age排序(asc/desc)，并使用分页
    @Test
    public void testEntityWrapperSelect3() {
        List<Employee> employees = employeeMapper.selectList(
                new EntityWrapper<Employee>()
                        .eq("gender", 0)
                        .orderBy("age")  // 默认为升序
                        .last("limit 1, 3")
        );
        System.out.println(employees);

        List<Employee> employees2 = employeeMapper.selectList(
                new EntityWrapper<Employee>()
                        .eq("gender", 0)
                        .orderDesc(Arrays.asList(new String[]{"age"}))  // orderAsc()的用法与此相同
                        .last("limit 1, 3")
        );
        System.out.println(employees2);

        List<Employee> employees3 = employeeMapper.selectList(
                new EntityWrapper<Employee>()
                        .eq("gender", 0)
                        .orderBy("age")
                        .last("desc limit 1, 3")
        );
        System.out.println(employees3);
    }

    /**
     * 条件构造器--修改操作
     *
     */
    @Test
    public void testEntityWrapperUpdate() {
        Employee employee = new Employee();
        employee.setLastName("川普");
        employee.setAge(74);
        employee.setEmail("trump@gamil.com");
        employee.setGender(1);

        Integer lines = employeeMapper.update(employee,
                new EntityWrapper<Employee>()
                        .eq("last_name", "光头强3")
                        .eq("id", 17)
        );
        System.out.println("影响行数：" + lines);
    }

    /**
     * 条件构造--删除操作
     */
    @Test
    public void testEntityWrapperDelete() {
        Integer lines = employeeMapper.delete(
                new EntityWrapper<Employee>()
                        .eq("last_name", "光头强3")
                        .eq("age", 23)
        );
        System.out.println("影响行数：" + lines);
    }
}
