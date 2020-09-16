package test.com.java.mybatis;

import com.java.bean.Department;
import com.java.bean.Employee;
import com.java.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import static com.java.common.MybatisUtils.getSqlSessionFactory;

/**
 * MyBatis二级缓存
 * <p>
 * 二级缓存（全局缓存，同一个SqlSessionFactory打开的多个session有效）
 * namespace级别的缓存，默认一个namespace对应一个二级缓存
 * 工作机制：
 * 1. 一个会话，查询一条数据时，查询到的数据会存放到一级缓存中，并返回给调用方法。
 * 2. 当会话关闭或commit提交时：一级缓存中的数据就会写入到相应的二级缓存中，
 * 3. 新的会话查询时就可以到二级缓存中获取数据
 * <p>
 * 一级缓存（本地缓存，只对当前session有效）
 * session级别的缓存，一级缓存是一直开启的
 * 与数据库连接的同一个session会话，首次查询时，会把结果存到本地缓存。
 * 之后在获取再获取相同的数据时，直接从缓存中获取。
 * <p>
 * 一级缓存失效的情况：（即需要到数据库中进行查询）
 * * SqlSession不同时
 * * SqlSession相同，查询条件不同时
 * * SqlSession相同，查询条件也相同，在两次查询之间执行了增删改操作
 * * SqlSession相同，查询条件也相同，在两次查询之间执行了执行了sqlSession1.clearCache()
 * 和缓存有关的设置/属性
 * * mybatis全局配置中，<setting name="cacheEnabled" value="true"/>
 * true: 开启二级缓存
 * false: 关闭二级缓存
 * * mybatis全局配置中指定本地缓存(一级缓存)的作用域
 * <setting name="localCacheScope" value="SESSION"/>: 一级缓存为session级，同一session共享一个一级缓存
 * <setting name="localCacheScope" value="STATEMENT"/>: 一级缓存为statement级，相当于禁用一级缓存
 * <p>
 * * <select>标签都有默认 useCache="true"
 * <select useCache="true">: 查询时使用缓存
 * <select useCache="false">: 查询时不使用二级缓存，一级缓存仍然使用
 * * <insert>、<delete>、<update>标签默认都有 flushCache="true" 属性
 * 表示增删改操作都会清缓存(包括一级缓存、二级缓存)
 * 若指定 flushCache="false" 则不会清除缓存(包括一级、二级缓存)
 * * <select> 默认flushCache="false"，即查询操作默认不清除缓存
 * <select flushCache=“true”>: 该查询操作后，将删除缓存（一级、二级缓存）
 * * sqlSession.clearCache(); 只清除当前sqlSession的一级缓存
 */
public class TestEmployeeMapper {
    @Test
    public void testGetEmpById() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            // 只会发送一次sql查询请求
            Employee emp1 = mapper.getEmpById(3L);
            Employee emp2 = mapper.getEmpById(3L);
            Employee emp3 = mapper.getEmpById(2L);
            System.out.println("emp1 == emp2: " + (emp1 == emp2));  // true, 是同一个对象，即emp2是直接从缓存中获取到的
            System.out.println("emp1 == emp3: " + (emp1 == emp3));  // false, 查询条件不同，
        }
    }

    //
    @Test
    public void testLevele2Cache1() {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try (
                SqlSession sqlSession = sqlSessionFactory.openSession();
                SqlSession sqlSession2 = sqlSessionFactory.openSession();
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);
            Employee emp1 = mapper.getEmpById(3L);
            Employee emp2 = mapper2.getEmpById(3L);
            System.out.println("emp1 == emp2: " + (emp1 == emp2));  // false，原因，sqlSession未commit()或close()后，一级缓存的数据不会写入二级缓存中
        }
    }

    // insert, delete, update 操作默认情况下，会清除缓存(含一级、二级缓存)
    @Test
    public void testAddEmp() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee emp1 = mapper.getEmpById(3L);
            Employee e = new Employee(null, "张旺", "1", "zhangwang@qq.com", new Department(1L, null));
            mapper.addEmp(e);
            Employee emp2 = mapper.getEmpById(3L);
            System.out.println("emp1 == emp2: " + (emp1 == emp2));  // false
        }
    }

    @Test
    public void testClearCache1() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee emp1 = mapper.getEmpById(3L);
            // 清除缓存，清除当前SqlSession的全部一级缓存
            sqlSession.clearCache();
            Employee emp2 = mapper.getEmpById(3L);
            System.out.println(emp1 == emp2);  // false
        }
    }

    @Test
    public void testClearCache2() {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try (
                SqlSession sqlSession = sqlSessionFactory.openSession(true);
                SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);
            Employee emp1 = mapper.getEmpById(3L);
            // 只清除当前SqlSession的全部一级缓存
            sqlSession.clearCache();
            Employee emp2 = mapper.getEmpById(3L);
            System.out.println(emp1 == emp2);  // false
            sqlSession.commit();
            Employee emp3 = mapper2.getEmpById(3L);  // 使用二级缓存
            System.out.println(emp3 == emp1);  // false
            System.out.println(emp3 == emp2);  // true
        }
    }

    // 二级缓存必须是在同一个 SqlSessionFactory 下的SqlSession
    @Test
    public void testLevele2Cache() {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try (
                SqlSession sqlSession = sqlSessionFactory.openSession(true);
                SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            EmployeeMapper mapper1 = sqlSession1.getMapper(EmployeeMapper.class);
            Employee emp = mapper.getEmpById(1L);
            Employee emp1 = mapper1.getEmpById(1L);
            // 即使配置了 二级缓存，在sqlSession未commit()或close()前，因此此时查到的数据只缓存到了一组缓存中，都为 false
            System.out.println(emp == emp1);
        }
    }

    // 二级缓存必须是在同一个 SqlSessionFactory 下的SqlSession
    @Test
    public void testLevele2Cache2() {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        EmployeeMapper mapper1 = sqlSession1.getMapper(EmployeeMapper.class);
        Employee emp1 = mapper1.getEmpById(1L);
        sqlSession1.commit();
        sqlSession1.close();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);
        Employee emp2 = mapper2.getEmpById(1L);
        sqlSession2.close();
        System.out.println(emp1 == emp2);

    }

    // <select useCache="false"> 此方法关闭 二级缓存
    @Test
    public void testGetEmpById2() {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try (
                SqlSession sqlSession = sqlSessionFactory.openSession(true);
                SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);
            // 只会发送一次sql查询请求
            Employee emp1 = mapper.getEmpById2(3L);
            Employee emp2 = mapper.getEmpById2(3L);
            System.out.println(emp1 == emp2);  // true，关闭的是二级缓存，一级缓存仍然可以使用
            sqlSession.commit();
            Employee emp3 = mapper2.getEmpById2(3L);
            System.out.println(emp3 == emp1);  // false
        }
    }

    // <select flushCache="true"> 指定查询操作也清除缓存(含一级、二级缓存)
    @Test
    public void testGetEmpById3() {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try (
                SqlSession sqlSession = sqlSessionFactory.openSession(true);
                SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);
            // 只会发送一次sql查询请求
            Employee emp1 = mapper.getEmpById3(3L);
            Employee emp2 = mapper.getEmpById3(3L);
            System.out.println(emp1 == emp2);  // false, 一级缓存也被清除了
            sqlSession.commit();
            Employee emp3 = mapper2.getEmpById3(3L);
            System.out.println(emp3 == emp1);  // false, 二级缓存也被清除了
        }
    }

    // <insert id="addEmp2" flushCache="false">
    @Test
    public void testAddEmp2() {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try (
                SqlSession sqlSession = sqlSessionFactory.openSession(true);
                SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);
            Employee emp1 = mapper.getEmpById(3L);
            Employee e = new Employee(null, "张旺", "1", "zhangwang@qq.com", new Department(1L, null));
            mapper.addEmp2(e);
            Employee emp2 = mapper.getEmpById(3L);
            Employee emp3 = mapper2.getEmpById(3L);
            System.out.println(emp1 == emp2);  // false
            System.out.println(emp3 == emp1);  // false
        }
    }

    // <insert id="addEmp2" flushCache="false">
    @Test
    public void testAddEmp2_2() {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try (
                SqlSession sqlSession = sqlSessionFactory.openSession(true);
                SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);
            Employee emp1 = mapper.getEmpById(3L);
            sqlSession.commit();
            Employee e = new Employee(null, "张旺", "1", "zhangwang@qq.com", new Department(1L, null));
            mapper.addEmp2(e);
            Employee emp2 = mapper.getEmpById(3L);
            Employee emp3 = mapper2.getEmpById(3L);
            System.out.println(emp1 == emp2);  // true
            System.out.println(emp3 == emp1);  // true
        }
    }
}
