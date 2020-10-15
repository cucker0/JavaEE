package test.com.java.mybatis;

import com.java.bean.Department;
import com.java.bean.DepartmentX;
import com.java.dao.DepartmentMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import static com.java.common.MybatisUtils.getSqlSessionFactory;

public class TestDepartmentMapper {
    @Test
    public void testGetDepartmentById() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department department = mapper.getDepartmentById(1L);
            System.out.println(department);
        }
    }

    @Test
    public void testGetDepartmentXById() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            DepartmentX d = mapper.getDepartmentXById(3L);
            System.out.println(d);
            System.out.println(d.getEmployeeList());
        }
    }

    // getDepartmentXStepById
    @Test
    public void testGetDepartmentXStepById() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
                ) {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            DepartmentX departmentX = mapper.getDepartmentXStepById(3L);
            System.out.println(departmentX.getEmployeeList());
        }
    }

    // 如何在xml mapper 文件中向方法中传递多个参数
    @Test
    public void testGetDepartmentXStepById2() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            DepartmentX departmentX = mapper.getDepartmentXStepById2(3L);
            System.out.println(departmentX.getDepName());
            System.out.println(departmentX.getEmployeeList());
        }
    }

    // <collection fetchType=""> 延迟方式控制
    @Test
    public void testGetDepartmentXStepById3() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            DepartmentX departmentX = mapper.getDepartmentXStepById3(3L);
            System.out.println(departmentX.getDepName());
        }
    }
}
