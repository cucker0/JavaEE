CREATE DATABASE mydata CHARACTER SET utf8mb4;

DROP TABLE IF EXISTS department;
CREATE TABLE department (
    id INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS t_employee;
CREATE TABLE t_employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    last_name VARCHAR(32) NOT NULL,
    gender VARCHAR(1) DEFAULT '0' COMMENT '0: female, 1: male',
    email VARCHAR(32),
    dep_id INT NOT NULL COMMENT 'department_id'
);

SHOW TABLES;

INSERT INTO department (department_name) VALUES
('IT部门'), 
('行政部'), 
('财务部'), 
('人事部')
;

SELECT * FROM department;

SELECT id, department_name FROM department LIMIT 0, 1000;

INSERT INTO t_employee (last_name, gender, email, dep_id) VALUES
('佟大为', '1', 'tongdw@hotmail.com', '1'),
('关悦', '0', 'guany@gmail.com', '2'),
('kate', '1', 'kate@microsoft.com', '4'),
('amy', '0', 'amy@z.com', '3'),
('何润东', '1', 'herd@qq.com', '3'),
('段奕宏', '1', 'duanyh@163.com', '3')
;

SELECT * FROM t_employee;
SELECT 1 FROM DUAL

SELECT 1


SELECT id, last_name, gender, email, dep_id FROM t_employee LIMIT 0, 1000;
SELECT id, department_name FROM department LIMIT 0, 1000