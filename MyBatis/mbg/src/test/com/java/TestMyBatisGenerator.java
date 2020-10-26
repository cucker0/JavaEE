package test.com.java;

import com.java.bean.Employee;
import com.java.bean.EmployeeExample;
import com.java.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import static com.java.common.MybatisUtils.getSqlSessionFactory;

public class TestMyBatisGenerator {
    // 根据mbg.xml配置文件和数据库表的结构，自动生成JavaBean类、Dao接口文件、xml Mapper文件
    // 每次重新生成时，把自动生成的文件删除
    @Test
    public void MyBatisGenerator() throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        // 指定mbg.xml路径
        File configFile = new File("mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        System.out.println("MyBatis Generator 执行成功");
    }

    // MyBatis3Simple
    // @Test
    // public void testMyBatis3Simple() {
    //     try (
    //             SqlSession sqlSession = getSqlSessionFactory().openSession(true);
    //     ) {
    //         EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
    //         // 查询所有员工
    //         List<Employee> employees = mapper.selectAll();
    //         for (Employee e : employees) {
    //             System.out.println(String.format("Emp{id: %s, lastName: %s, gender: %s, email: %s, department_id: %s}",
    //                     e.getId(), e.getLastName(), e.getGender(), e.getEmail(), e.getDepId()
    //                     )
    //             );
    //         }
    //     }
    // }

    // 查询所有员工
    @Test
    public void testMybatis() {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try (
                SqlSession sqlSession = sqlSessionFactory.openSession();
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> employees = mapper.selectByExample(null);
            for (Employee e : employees) {
                System.out.println(String.format("Emp{id: %s, lastName: %s, gender: %s, email: %s, department_id: %s}",
                        e.getId(), e.getLastName(), e.getGender(), e.getEmail(), e.getDepId()
                        )
                );
            }
        }
    }

    // 条件查询：查询员工名字中有e字母的，和员工性别是1的员工信息
    @Test
    public void testMybatis2() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession();
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            EmployeeExample example = new EmployeeExample();
            EmployeeExample.Criteria criteria = example.createCriteria();
            criteria.andLastNameLike("%e%");
            criteria.andGenderEqualTo("1");
            List<Employee> employees = mapper.selectByExample(example);
            for (Employee e : employees) {
                System.out.println(String.format("Emp{id: %s, lastName: %s, gender: %s, email: %s, department_id: %s}",
                        e.getId(), e.getLastName(), e.getGender(), e.getEmail(), e.getDepId()
                        )
                );
            }
        }
    }

    // 条件查询：查询员工名字中有e字母的，且员工性别是1的员工信息，或email为"guany@gmail.com"的员工信息
    // Criteria: 用于拼接条件
    @Test
    public void testMybatis3() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession();
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            EmployeeExample example = new EmployeeExample();
            EmployeeExample.Criteria criteria = example.createCriteria();
            criteria.andLastNameLike("%e%");
            criteria.andGenderEqualTo("1");

            EmployeeExample.Criteria criteria1 = example.createCriteria();
            criteria1.andEmailEqualTo("guany@gmail.com");

            example.or(criteria1);

            List<Employee> employees = mapper.selectByExample(example);
            for (Employee e : employees) {
                System.out.println(String.format("Emp{id: %s, lastName: %s, gender: %s, email: %s, department_id: %s}",
                        e.getId(), e.getLastName(), e.getGender(), e.getEmail(), e.getDepId()
                        )
                );
            }
        }
    }
}
