package test.com.java.mybatis;

import com.java.bean.Employee;
import com.java.bean.EmployeeX;
import com.java.dao.EmployeePlusMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import static com.java.common.MybatisUtils.getSqlSessionFactory;

public class TestEmployeePlusMapper {
    @Test
    public void testGetEmployeeById() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeePlusMapper mapper = sqlSession.getMapper(EmployeePlusMapper.class);
            Employee emp = mapper.getEmployeeById(2L);
            System.out.println(emp);

        }
    }

    @Test
    public void testGetEmployeeXById() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeePlusMapper mapper = sqlSession.getMapper(EmployeePlusMapper.class);
            EmployeeX e = mapper.getEmployeeXById(2L);
            System.out.println(e);
        }
    }

    @Test
    public void testGetEmployeeXById2() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeePlusMapper mapper = sqlSession.getMapper(EmployeePlusMapper.class);
            EmployeeX e = mapper.getEmployeeXById2(2L);
            System.out.println(e);
        }
    }


    // 分步查询
    @Test
    public void testGetEmployeeXStepById() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeePlusMapper mapper = sqlSession.getMapper(EmployeePlusMapper.class);
            EmployeeX e = mapper.getEmployeeXStepById(3L);
            System.out.println(e);
        }
    }

    // 懒加载（按需加载），应用于分步查询
    @Test
    public void testLazyLoading() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeePlusMapper mapper = sqlSession.getMapper(EmployeePlusMapper.class);
            EmployeeX e = mapper.getEmployeeXStepById(3L);
            System.out.println(e.getLastName());
            /*
             * Preparing: SELECT id, last_name, gender, email, dep_id FROM t_employee_x WHERE id = ?
             * 只有一步，没有使用到部门信息，则不查询部门信息
             * */
        }
    }

    // 懒加载（按需加载），应用于分步查询
    @Test
    public void testLazyLoading2() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeePlusMapper mapper = sqlSession.getMapper(EmployeePlusMapper.class);
            EmployeeX e = mapper.getEmployeeXStepById(3L);
            System.out.println(e.getDepartment());

            /*
             * 两步，使用到了信息，先查询员工信息，在根据员工信息中的部门id查询部门信息
             * Preparing: SELECT id, last_name, gender, email, dep_id FROM t_employee_x WHERE id = ?
             * Preparing: SELECT id, dep_name depName FROM t_department WHERE id = ?
             * */
        }
    }



    // <discriminator> 鉴别器
    @Test
    public void testGetEmployeeXDiscriminatorById() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeePlusMapper mapper = sqlSession.getMapper(EmployeePlusMapper.class);
            EmployeeX e = mapper.getEmployeeXDiscriminatorById(3L);
            System.out.println("3号员工：男性\n" + e);

            EmployeeX e2 = mapper.getEmployeeXDiscriminatorById(2L);
            System.out.println("2号员工：女性\n" + e2);

            /*
3号员工：男性
EmployeeX{id=3, lastName='kate', gender='1', email='kate', department=null

2号员工：女性
EmployeeX{id=2, lastName='关悦', gender='0', email='guany@gmail.com', department=Department{id=2, depName='行政部'}}
            * */
        }
    }
}
