DROP TABLE IF EXISTS t_employee;
CREATE TABLE t_employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    last_name VARCHAR(32) NOT NULL,
    gender VARCHAR(1) DEFAULT '0' COMMENT '0: female, 1: male',
    email VARCHAR(32),
    dep_id INT NOT NULL COMMENT 'department_id'
);
