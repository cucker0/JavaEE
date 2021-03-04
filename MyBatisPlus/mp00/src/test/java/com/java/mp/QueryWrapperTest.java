package com.java.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.java.mp.bean.Employee;
import com.java.mp.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * QueryWrapper是 AbstractWrapper的子类
 */
public class QueryWrapperTest {
    private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
    private EmployeeMapper employeeMapper = ioc.getBean("employeeMapper", EmployeeMapper.class);

    /**
     * 条件构造器--select
     *
     */
    // 分页查询查询，年龄在18-50岁之间，且性别为男，且姓名为Tom的所有用户
    @Test
    public void testQueryWrapperSelect() {
        Page<Employee> employeePage = employeeMapper.selectPage(new Page<Employee>(1, 2),
                new QueryWrapper<Employee>()
                        .between("age", 18, 50)
                        .eq("gender", 1)
                        .eq("last_name", "Tom")
        );
        System.out.println("total: " + employeePage.getSize());
    }

    // 性别为女，并且名字中带有"头"，或者邮箱中带有"a"的记录
    @Test
    public void testQeuryWrapperSelect2() {
        List<Employee> employees = employeeMapper.selectList(new QueryWrapper<Employee>()
                .eq("gender", 0)
                .like("last_name", "头")
                .or()
                .like("email", "a")
        );
        System.out.println(employees);
    }

    // allEq(Map m)
    @Test
    public void testQueryWrapperAllEq() {
        Map<String, Object> map = new HashMap<>();
        map.put("last_name", "光头强");
        map.put("email", "guangguagn@gmail.com");
        List<Employee> employees = employeeMapper.selectList(
                new QueryWrapper<Employee>()
                        .allEq(map)
        );
        System.out.println(employees);
    }

    /**
     * 条件构造器--update
     */
    @Test
    public void testQueryWrapperUpdate() {
        Employee employee = new Employee();
        employee.setLastName("莫迪");
        employee.setAge(60);
        employee.setEmail("modi@gamil.com");
        employee.setGender(1);

        Integer lines = employeeMapper.update(employee,
                new QueryWrapper<Employee>()
                        .eq("last_name", "Tom")
                        .eq("id", 37)
        );
        System.out.println("影响行数：" + lines);
    }

    /**
     * 条件构造器--删除操作
     */
    @Test
    public void testQueryWrapperDelete() {
        Integer lines = employeeMapper.delete(
                new QueryWrapper<Employee>()
                        .eq("last_name", "张茜")
                        .eq("age", 18)
        );
        System.out.println("影响行数：" + lines);
    }

    /**
     * LambdaQueryWrapper
     *  通过lambda表达式快速指定指定只查询哪些字段
     */
    @Test
    public void testQueryLambdaQueryWrapper() {
        LambdaQueryWrapper<Employee> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.select(Employee::getLastName, Employee::getGender, Employee::getAge);
        List<Employee> employees = employeeMapper.selectList(queryWrapper);
        System.out.println(employees);
    }
}
