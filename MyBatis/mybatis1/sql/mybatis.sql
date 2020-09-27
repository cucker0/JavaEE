-- 创建数据库
CREATE DATABASE mybatis CHARSET 'utf8';

USE mybatis;

-- 创建Employee表
CREATE TABLE t_employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    last_name VARCHAR(32) NOT NULL,
    gender VARCHAR(1) DEFAULT '0' COMMENT '0: female, 1: male',
    email VARCHAR(32)
);

INSERT INTO t_employee (last_name, gender, email) VALUES
('大山', '0', 'da3@gmail.com'),
('花姐', '0', 'huajie@gmail.com')
;


SELECT * FROM t_employee;

-- create department table
CREATE TABLE t_department (
    id INT PRIMARY KEY AUTO_INCREMENT,
    dep_name VARCHAR(64)
);

INSERT INTO t_department (dep_name) VALUES
('IT部门'), 
('行政部'), 
('财务部'), 
('人事部')
;

-- 创建t_employee_x表
CREATE TABLE t_employee_x (
    id INT PRIMARY KEY AUTO_INCREMENT,
    last_name VARCHAR(32) NOT NULL,
    gender VARCHAR(1) DEFAULT '0' COMMENT '0: female, 1: male',
    email VARCHAR(32),
    dep_id INT NOT NULL COMMENT 'department_id'
);

INSERT INTO t_employee_x (last_name, gender, email, dep_id) VALUES
('佟大为', '1', 'tongdw@hotmail.com', '1'),
('关悦', '0', 'guany@gmail.com', '2'),
('kate', '1', 'kate@microsoft.com', '4'),
('amy', '0', 'amy@z.com', '3'),
('何润东', '1', 'herd@qq.com', '3'),
('段奕宏', '1', 'duanyh@163.com', '3')
;


-- 创建t_employee_z表
CREATE TABLE t_employee_z (
    id INT PRIMARY KEY AUTO_INCREMENT,
    last_name VARCHAR(32) NOT NULL,
    gender VARCHAR(1) DEFAULT '0' COMMENT '0: female, 1: male',
    email VARCHAR(32),
    `status` VARCHAR(32),
    dep_id INT NOT NULL COMMENT 'department_id'
);

INSERT INTO t_employee_z (last_name, gender, email, `status`, dep_id) VALUES
('钱学森', '1', 'qianxuesen@hotmail.com', '100', '1'),
('邓稼先', '1', 'degnjx@gmail.com', '100', '2'),
('华罗庚', '1', 'hualg@microsoft.com', '200', '4'),
('李四光', '1', 'lisg@z.com', '300', '3'),
('袁隆平', '1', 'yuanrp@qq.com', '100', '3'),
('茅以升', '1', 'maoys@163.com', '200', '3'),
('钱三强', '1', 'qiansq@163.com', '300', '3'),
('于敏', '1', 'yum@163.com', '100', '3')
;

-- 以下为查询操作，可以不执行。执行也不受影响

SELECT * FROM t_department;

SELECT * FROM t_employee_x;

SELECT e.id, e.last_name, e.gender, e.email, e.dep_id, d.dep_name
FROM t_employee_x e
LEFT OUTER JOIN t_department d
ON e.dep_id = d.id WHERE e.id = '2';

-- 分步查询
-- ①先查询出员工信息
SELECT id, last_name, gender, email, dep_id FROM t_employee_x WHERE id = '3';

-- ②再根据①中查询到的员工信息中的 dep_id 去查询department
SELECT id, dep_name FROM t_department WHERE id = 4;


-- 查询指定部门的部门信息以及该部门的所有员工信息
SELECT d.id, d.dep_name, e.id e_id, e.last_name, e.gender, e.email
FROM t_department d
LEFT OUTER JOIN t_employee_x e
ON d.id = e.dep_id
WHERE d.id = 3
;

-- 分步查询，查询指定部门的部门信息以及该部门的所有员工信息
SELECT id, dep_name FROM t_department WHERE id = 3;

SELECT
    id, last_name, gender, email
FROM
    t_employee_x
WHERE dep_id = 3;

INSERT INTO t_emp (ID, last_name, gender, email)
SELECT t_emp_id.NEXTVAL, lastName, gender, email
FROM 
    (SELECT 'last_name' lastName, gender, email FROM DUAL
    UNION
    SELECT 'last_name' lastName, gender, email FROM DUAL
    )
    
-- 
SELECT * FROM t_employee_z;

