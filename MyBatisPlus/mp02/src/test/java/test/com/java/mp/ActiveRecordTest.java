package test.com.java.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    // AR select
    @Test
    public void testSelect4() {
        Employee employee = new Employee();
        // 查询整表所有记录条数
        Integer count = employee.selectCount(null);
        System.out.println("全部人数：" + count);

        Integer count1 = employee.selectCount(
                new QueryWrapper<Employee>().
                        eq("gender", 0)
        );
        System.out.println("所有女性员工人数：" + count1);
    }

    /**
     * AR delete操作
     */
    @Test
    public void testDelete() {
        Employee employee = new Employee();
        boolean b = employee.deleteById(15);
        System.out.println("delete status: " + b);

        boolean b1 = employee.deleteById(15);
        System.out.println("不存在记录删除 == delete status: " + b1);  // 结果：false，如果版本是是2.x的则是true
    }

    // AR delete
    @Test
    public void testDelete2() {
        Employee employee = new Employee();
        employee.setId(16);
        boolean b = employee.deleteById();
        System.out.println("delete status: " + b);
    }
    // AR delete带构造条件
    @Test
    public void testDelete3() {
        Employee employee = new Employee();
        boolean b = employee.delete(
                new QueryWrapper<Employee>().between("age", 18, 22)
        );
        System.out.println(b);
    }

    /**
     * AR 分页查询操作
     */
    @Test
    public void testSelectPage() {
        Employee employee = new Employee();
        Page<Employee> page = employee.selectPage(
                new Page<Employee>(1, 3),
                new QueryWrapper<Employee>().
                        like("last_name", "om")
        );
        System.out.println(page.getPages());
        /*
        [
            Employee{id=3, lastName='Tom', email='tom@baomidou.com', gender=1, age=28},
            Employee{id=12, lastName='Tom', email='tom@baomidou.com', gender=1, age=16},
            Employee{id=13, lastName='Tom', email='tom@baomidou.com', gender=1, age=16},
            Employee{id=17, lastName='Tom', email='tom@baomidou.com', gender=1, age=56},
            Employee{id=19, lastName='Tom', email='tom@baomidou.com', gender=1, age=16},
            Employee{id=20, lastName='Tom', email='tom@baomidou.com', gender=0, age=18},
            Employee{id=21, lastName='Tom', email='tom@baomidou.com', gender=1, age=22},
            Employee{id=22, lastName='Tom', email='tom@baomidou.com', gender=1, age=44},
            Employee{id=23, lastName='Tom', email='tom@baomidou.com', gender=1, age=56}]

         */
    }

}
