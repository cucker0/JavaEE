package test.com.java.mybatis;

import com.java.bean.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestMybatis {

    @Test
    public void testSqlSessionFactory() {
        // resource 使用相对于src类路径的资源引用
        String resource = "conf/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        /*
        * session.selectOne(String s, Object o):
        * s: sql语句的全局唯一标识符，即 命名空间.id
        * o: 参数
        *
        * */

        Employee employee = session.selectOne("com.java.EmployeeMapper.selectEmp", 1);
        System.out.println(employee);
        session.close();
    }

}
