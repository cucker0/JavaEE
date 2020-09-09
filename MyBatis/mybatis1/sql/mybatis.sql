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

SELECT * FROM t_department;

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
('amy', '0', 'amy@z.com', '3')
;

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

