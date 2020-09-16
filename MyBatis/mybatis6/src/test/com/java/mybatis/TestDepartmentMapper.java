package test.com.java.mybatis;

import com.java.bean.Department;
import com.java.dao.DepartmentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static com.java.common.MybatisUtils.getSqlSessionFactory;

public class TestDepartmentMapper {
    @Test
    public void testGetDepartmentById() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
                ) {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department dep = mapper.getDepartmentById(2L);
            System.out.println(dep);
        }
    }

    @Test
    public void testLevel2Cache() {
        // 必须由同一个sqlSessionFactory打开的两SqlSession才能共享二级缓存
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
        SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
        DepartmentMapper mapper = sqlSession1.getMapper(DepartmentMapper.class);
        DepartmentMapper mapper2 = sqlSession2.getMapper(DepartmentMapper.class);

        Department m1 = mapper.getDepartmentById(1L);
        System.out.println("mapper读取数据: " + m1);
        sqlSession1.close();

        Department m2 = mapper2.getDepartmentById(1L);
        System.out.println("mapper2读取数据: " + m2);
        System.out.println(m1 == m2);  // true
    }
}
