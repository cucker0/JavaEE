package test;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.java.mp.bean.Employee;
import com.java.mp.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * UpdateWrapper是 AbstractWrapper的子类
 */
public class UpdateWrapperTest {
    private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
    private EmployeeMapper employeeMapper = ioc.getBean("employeeMapper", EmployeeMapper.class);

    /**
     * 条件构造器--UpdateWrapper 更新操作
     */
    @Test
    public void testUpdateWrapperUpdate() {
        int lines = employeeMapper.update(null,
                new UpdateWrapper<Employee>()
                        .eq("id", 43)
                        .set("last_name", "刘菲菲")
        );
        System.out.println("影响行数：" + lines);
    }
}
