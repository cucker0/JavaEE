package test.com.java.mp;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.java.mp.bean.Emp;
import com.java.mp.bean.Employee;
import com.java.mp.mapper.EmpMapper;
import com.java.mp.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class PluginTest {
    private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    private EmployeeMapper employeeMapper = ctx.getBean(EmployeeMapper.class);
    private EmpMapper empMapper = ctx.getBean(EmpMapper.class);

    /**
     * Mybatis-Plus分页插件，PaginationInnerInterceptor
     */
    @Test
    public void testPage() {
        Page<Employee> page = new Page<>(2, 3);
        // 查询所有员工，分页显示。 employeeMapper.selectPage(page, null) 返回的是page，所以 employeePage == page
        Page<Employee> employeePage = employeeMapper.selectPage(page, null);
        System.out.println("=== 分页信息  ===");
        System.out.println("总条数: " + page.getTotal());
        System.out.println("当前页码: " + page.getCurrent());
        System.out.println("总页码数: " + page.getPages());
        System.out.println("每页显示的条数: " + page.getSize());
        System.out.println("是否有下一页: " + page.hasNext());
        System.out.println("是否有上一页: " + page.hasPrevious());
        System.out.println("当前页的record: " + page.getRecords());
        System.out.println(employeePage == page);  // 结果为 true
    }

    /**
     * 防止全表更新与删除插件 BlockAttackInnerInterceptor
     */
    @Test
    public void testBlockAttackInnerInterceptor() {
        // 全表删除，利用SQL执行分析，阻止全表删除。会报异常：MybatisPlusException: Prohibition of full table deletion
        // employeeMapper.delete(null);


        Employee employee = new Employee();
        employee.setGender("1");
        // 更新全表，报异常：MybatisPlusException: Prohibition of table update operation
        int lines = employeeMapper.update(employee, new UpdateWrapper<Employee>());

    }

    /**
     * sql性能分析
     */
    @Test
    public void testPerformance() {
        Employee employee = new Employee();
        employee.setLastName("亮剑");
        employee.setGender("0");
        employee.setAge(16);
        employee.setEmail("liangj@qq.com");
        employeeMapper.insert(employee);
        /*
        // 分析的结果
        Consume Time：77 ms 2020-12-24 17:30:50
        Execute SQL：INSERT INTO tbl_employee ( last_name, email, gender, age ) VALUES ( '亮剑', 'liangj@qq.com', '0', 16 )

         */
    }

    /**
     * 乐观锁插件
     * 乐观锁实现方式：
     *      * 取出记录时，获取当前version
     *      * 更新时，带上这个version
     *      * 执行更新时， set version = newVersion where version = oldVersion
     *      * 如果version不对，就更新失败
     */
    @Test
    public void testOptimisticLocker() {
        Emp emp = new Emp();
        emp.setId(5);
        emp.setLastName("亮剑剑222");
        emp.setGender("0");
        emp.setAge(17);
        emp.setEmail("liangjj@qq.com");
        // 当version与表中该记录version字段值不相等时，将更新不到，Updates: 0
        emp.setVersion(1);
        empMapper.updateById(emp);
    }
}
