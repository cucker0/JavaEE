package com.java.springboot.entity;

import javax.persistence.*;

/**
 * @Entity 声明这是一个JPA实体类（与数据表映射的类）
 */
@Entity
@Table(name = "tbl_user")
public class User {
    @Id  // 指定为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 自增主键
    private Integer id;

    @Column(name = "last_name", nullable = false, length = 64)
    private String lastName;

    @Column(name = "passwd", nullable = false, length = 64)
    private String passwd;

    @Column(length = 64)  // 缺省情况下，列名为 属性名
    private String email;

    public User() {}

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

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
