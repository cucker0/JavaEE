package com.java.springbootdatamybatis.mapper;

import com.java.springbootdatamybatis.bean.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 在主程序类中添加 @MapperScan(value = "com.java.springbootdatamybatis.mapper")
 * Mapper接口则可省去@Mapper注解
 */
// @Mapper
public interface DepartmentMapper {
    @Select("SELECT id, department_name FROM department WHERE id=#{id};")
    public Department getDepartmentById(Integer id);

    @Delete("DELETE FROM department WHERE id=#{id}")
    public int deleteDepartmentById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO department(department_name) VALUES(#{departmentName})")
    public int insertDepartment(Department department);

    @Update("UPDATE department SET department_name=#{departmentName} WHERE id=#{id}")
    public int updateDepartment(Department department);

    @Select("SELECT id, department_name FROM department LIMIT 0, 1000")
    public List<Department> getAllDepartments();
}
