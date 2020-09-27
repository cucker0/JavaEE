package test.com.java;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.bean.Department;
import com.java.bean.Employee;
import com.java.dao.EmployeeMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static com.java.common.MybatisUtils.getSqlSessionFactory;

public class TestEmployeeMapper {
    @Test
    public void testPage() {

        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession();
                )
        {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            // 调用PageHelper.startPage(pageNum, pageSize); 即可进行分页
            Page<Object> page = PageHelper.startPage(2, 3);
            List<Employee> employees = mapper.getAllEmployees();
            System.out.println(employees);
            System.out.println(employees.size());

            System.out.println("当前页码：" + page.getPageNum());
            System.out.println("总记录数：" + page.getTotal());
            System.out.println("总页码：" + page.getPages());
            System.out.println("每页显示记录数：" + page.getPageSize());
        }
    }

    // PageInfo包含了非常全面的分页属性
    @Test
    public void testPageInfo() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession();
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            PageHelper.startPage(4, 3);
            List<Employee> employees = mapper.getAllEmployees();
            // 用PageInfo对结集合再进行包装
            PageInfo pageInfo = new PageInfo(employees);
            System.out.println("当前页码: " + pageInfo.getPageNum());
            System.out.println("总记录数: " + pageInfo.getTotal());
            System.out.println("总页码: " + pageInfo.getPages());
            System.out.println("每页显示记录数: " + pageInfo.getPageSize());
            System.out.println("是否为最后一页: " + pageInfo.isIsLastPage());
            System.out.println("是否为第一页: " + pageInfo.isIsFirstPage());
            System.out.println("下一页：" + pageInfo.getNextPage());
        }
    }

    @Test
    public void testPageInfo2() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession();
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            PageHelper.startPage(4, 3);
            List<Employee> employees = mapper.getAllEmployees();
            // PageInfo(List<T> list, int navigatePages),  navigatePages: 连续显示多个页码
            PageInfo pageInfo = new PageInfo(employees, 3);
            System.out.println("当前页码: " + pageInfo.getPageNum());
            System.out.println("总记录数: " + pageInfo.getTotal());
            System.out.println("总页码: " + pageInfo.getPages());
            System.out.println("每页显示记录数: " + pageInfo.getPageSize());
            System.out.println("是否为最后一页: " + pageInfo.isIsLastPage());
            System.out.println("是否为第一页: " + pageInfo.isIsFirstPage());
            System.out.println("下一页：" + pageInfo.getNextPage());
            int[] nums = pageInfo.getNavigatepageNums();
            for (int num : nums) {
                if (num == pageInfo.getPageNum()) {
                    System.out.print("[" + num + "] ");
                    continue;
                }
                System.out.print(num + " ");
            }
        }
    }

    @Test
    public void testAddEmployee() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(true);
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Department department = new Department(1L, null);
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1000; ++i) {
                String s = UUID.randomUUID().toString().substring(1, 8);
                mapper.addEmployee(new Employee(null, "u" + s, Integer.toString(i & 1), s + "@qq.com", department));
            }
            sqlSession.commit();
            long end = System.currentTimeMillis();
            System.out.println("用时(ms)：" + (end - start));  // 57463ms
        }

    }

    /*
    * BATCH类型的SqlSession
    * 先预编译，拼接sql，最后再提交
    *
    * */
    @Test
    public void testAddEmployeeBatch() {
        try (
                SqlSession sqlSession = getSqlSessionFactory().openSession(ExecutorType.BATCH, true);
        ) {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Department department = new Department(1L, null);
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1000; ++i) {
                String s = UUID.randomUUID().toString().substring(1, 8);
                mapper.addEmployee(new Employee(null, "u" + s, Integer.toString(i & 1), s + "@qq.com", department));
            }
            sqlSession.commit();  // 这时需要手动提交，上面的openSession设置的自动提交貌似没有生效
            long end = System.currentTimeMillis();
            System.out.println("BATCH SqlSession用时(ms)：" + (end - start));  // 编译时间 3770ms， 总时长: 50098ms
        }

    }
}
