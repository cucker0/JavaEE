package test.com.java;

import com.java.bean.Employee;
import com.java.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import static com.java.common.MybatisUtils.getSqlSessionFactory;

public class TestEmployeeMapper {
    @Test
    public void testGetEmployeeById() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
                ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee e = mapper.getEmployeeById(1L);
            System.out.println(e);
        }
    }
}
