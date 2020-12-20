package com.java.mp.bean;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

// MybatisPlus 默认使用实体类的类名到数据库找对应的表名，即不类名全部字母小写对应的表名
// 使用 @TableName(value = "表名") 可以指定对应的表
// @TableName(value = "tbl_employee")
// 当在 applicationContext.xml 配置文件中指定了MybatisPlus的全局策略配置，可以不在这里指定
public class Employee extends Model<Employee> {
    /*
    * @TableId
    *   value = "列名": 指定表中主键列的列名，如果实体属性名与列名一致，可以省略不指定.
    *   type: 指定主键策略
    *        IdType枚举类
    *           AUTO(0, "数据库ID自增"),
    *           INPUT(1, "用户输入ID"),
    *           ID_WORKER(2, "全局唯一ID"),
    *           UUID(3, "全局唯一ID"),
    *           NONE(4, "该类型为未设置主键类型"),
    *           ID_WORKER_STR(5, "字符串全局唯一ID");
    *
    * */
    // @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;
    private Integer age;
    // @TableField(exist = false)，表示非表的字段
    @TableField(exist = false)
    private Double salary;

    public Employee() {

    }

    public Employee(Integer id, String lastName, String email, Integer gender, Integer age) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // 指定当前实体类的 主键 属性
    @Override
    public Serializable pkVal() {
        return id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
