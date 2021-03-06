package com.java.dao;

import com.java.bean.Department;
import com.java.bean.DepartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepartmentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_department
     *
     * @mbg.generated Tue Sep 22 15:11:33 CST 2020
     */
    long countByExample(DepartmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_department
     *
     * @mbg.generated Tue Sep 22 15:11:33 CST 2020
     */
    int deleteByExample(DepartmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_department
     *
     * @mbg.generated Tue Sep 22 15:11:33 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_department
     *
     * @mbg.generated Tue Sep 22 15:11:33 CST 2020
     */
    int insert(Department record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_department
     *
     * @mbg.generated Tue Sep 22 15:11:33 CST 2020
     */
    int insertSelective(Department record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_department
     *
     * @mbg.generated Tue Sep 22 15:11:33 CST 2020
     */
    List<Department> selectByExample(DepartmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_department
     *
     * @mbg.generated Tue Sep 22 15:11:33 CST 2020
     */
    Department selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_department
     *
     * @mbg.generated Tue Sep 22 15:11:33 CST 2020
     */
    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_department
     *
     * @mbg.generated Tue Sep 22 15:11:33 CST 2020
     */
    int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_department
     *
     * @mbg.generated Tue Sep 22 15:11:33 CST 2020
     */
    int updateByPrimaryKeySelective(Department record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_department
     *
     * @mbg.generated Tue Sep 22 15:11:33 CST 2020
     */
    int updateByPrimaryKey(Department record);
}