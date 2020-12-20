package test.com.java.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.java.mp.bean.Employee;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ActiveRecordTest {
    // 需要初始 ioc，虽然这里地的测试方法没有直接用到ioc，少了这步，执行下面的测试方法将报 com.java.mp.bean.Employee Not Found TableInfoCache.
    private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

    /**
     * AR insert操作
     */
    @Test
    public void testInsert() {
        Employee employee = new Employee();
        employee.setLastName("刘安");
        employee.setAge(23);
        employee.setGender(1);
        employee.setEmail("liua@gmail.com");
        employee.setSalary(80000.0);
        // 执行插入，并返回插入操作的结果
        boolean ret = employee.insert();
        /*
        Preparing: INSERT INTO tbl_employee ( last_name, email, gender, age ) VALUES ( ?, ?, ?, ? )
        Parameters: 刘安(String), liua@gmail.com(String), 1(Integer), 23(Integer)
        Updates: 1
        */
        System.out.println("命令执行是否成功：" + ret);
    }

    /**
     * AR update操作
     */
    @Test
    public void testUpdate() {
        Employee employee = new Employee();
        employee.setId(18);
        employee.setLastName("刘安");
        employee.setAge(24);
        employee.setGender(1);
        employee.setEmail("liuan@qq.com");
        employee.setSalary(90000.0);

        boolean b = employee.updateById();
        System.out.println("update执行是否成功：" + b);
    }

    /**
     * AR select操作
     */
    @Test
    public void testSelect() {
        Employee employee = new Employee();
        Employee employee1 = employee.selectById(3);
        System.out.println(employee1);
    }
    // select 表中所有的记录
    @Test
    public void testSelect2() {
        Employee employee = new Employee();
        List<Employee> employees = employee.selectAll();
        System.out.println(employees);
    }
    // AR select 构造条件查询
    @Test
    public void testSelect3() {
        Employee employee = new Employee();
        List<Employee> emps = employee.selectList(
                new QueryWrapper<Employee>().
                        eq("gender", 0)
        );
        System.out.println(emps);

        List<Employee> emps2 = employee.selectList(
                new QueryWrapper<Employee>().
                        like("last_name", "b")
        );
        System.out.println(emps2);
    }


}
