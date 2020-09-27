package test.com.java;

import com.java.bean.OraclePage;
import com.java.dao.EmployeeOracleMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import static com.java.common.MybatisUtils.getSqlSessionFactory;

public class TestEmployeeOracleMapper {

    /*
    * oracle分页
    *   借助行号rownum
    *       再子查询
    *   使用存储过程包装分页逻辑
    * */
    @Test
    public void TestGetPageByProcedure() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
                ) {
            EmployeeOracleMapper mapper = sqlSession.getMapper(EmployeeOracleMapper.class);
            OraclePage page = new OraclePage();
            page.setStart(5);
            page.setEnd(10);
            mapper.getPageByProcedure(page);
            System.out.println("总记录数：" + page.getCount());
            System.out.println("查出的数据：" + page.getEmps());
            System.out.println("查出的数据行数：" + page.getEmps().size());
        }

    }
}
