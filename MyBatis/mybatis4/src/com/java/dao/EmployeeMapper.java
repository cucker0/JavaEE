package com.java.dao;

import com.java.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * mybatis 允许 增删改 直接定义 以下类型的返回值
 * Integer(int)、
 * Long(long)、
 * Boolean(boolean)、
 * void
 */
public interface EmployeeMapper {
    Employee getEmployeeById(Long id);

    void addEmployee(Employee employee);

    int updateEmployee(Employee employee);

    // boolean deleteEmployeeById(Long id);
    int deleteEmployeeById(Long id);

    Employee getEmployeeByIdAndLastName(Long id, String lastName);

    // 多参数，会被mybatis封装成一个map
    // @Param("keyName") 指定该参数在map中的key名
    Employee getEmployeeByIdAndLastName2(@Param("id") Long id, @Param("lastName") String lastName);

    Employee getEmployeeMap(Map<String, Object> map);

    List<Employee> getAllEmployees();

    // 把返回的一条记录封装成map，key为列名，value为列值
    Map<String, Object> getEmployeeReturnMap(Long id);

    /*
    * 把多条记录封装成一个map，Map<Long, Employee>，key为该记录的主键值, value为记录封装后的javaBean对象
    * @MapKey("id") 指定用Bean对象的哪个属性作为map的key，
    *       注意key是不允许重复的，所以当key值出现重复时，则map的元素会被最后的记录所覆盖
    * */
    @MapKey("id")
    Map<Long, Employee> getEmployeeByLastNameLikeReturnMap(String lastNameKey);
}
