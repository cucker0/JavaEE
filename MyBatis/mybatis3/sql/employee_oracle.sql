-- 创建员工表
CREATE TABLE t_emp (
    id NUMBER PRIMARY KEY,
    last_name VARCHAR(32),
    gender VARCHAR(1),
    email VARCHAR(32)
);

INSERT INTO t_emp(id, last_name, gender, email) VALUES
(, '丘维声', 1, qiuweisheng@tsinghua.com);


-- 创建t_emp表的自增id计数器
CREATE SEQUENCE t_emp_id INCREMENT BY 1 START WITH 1;

-- 查询t_emp表的自增id计数器
SELECT t_emp_id.NEXTVAL FROM DUAL;

-- 批量插入数据
INSERT INTO t_emp(id, last_name, gender, email) VALUES(t_emp_id.NEXTVAL, '丘维声', '1', 'quiweisheng@tsinghua.com');
INSERT INTO t_emp(id, last_name, gender, email) VALUES(t_emp_id.NEXTVAL, '郭德刚', '1', 'quodg@qq.com');


-- 创建用于分页的存储过程
CREATE OR REPLACE PROCEDURE mypage(p_start IN INT, p_end IN INT, p_count OUT INT, p_emps OUT SYS_REFCURSOR)
AS
BEGIN
	SELECT COUNT(*) INTO p_count FROM T_EMP;
	OPEN p_emps FOR
	SELECT * FROM (SELECT ROWNUM rn, e.* FROM T_EMP e WHERE ROWNUM <= p_end)
		WHERE rn >= p_start;
END;


-- ------------------- 以下不要执行 ---------------
-- 查询存储过程
/*
SELECT * FROM user_source;

SELECT id, last_name, gender, email FROM t_emp ORDER BY id ASC;

BEGIN
    INSERT INTO t_emp (id, last_name, gender, email) VALUES
        (t_emp_id.NEXTVAL, '俞敏洪', '1', 'yumh@xindongf.com');
    INSERT INTO t_emp (id, last_name, gender, email) VALUES
        (t_emp_id.NEXTVAL, '刘胡兰', '0', 'liufl@china.cn');
    INSERT INTO t_emp (id, last_name, gender, email) VALUES
        (t_emp_id.NEXTVAL, '童第周', '1', 'tongdz@kx.com');
END;


INSERT INTO t_emp (id, last_name, gender, email)
SELECT t_emp_id.NEXTVAL, lastName, gender, email
FROM
	(
		SELECT 'suzhen' lastName, '0' gender, 'sz@kk.com' email FROM DUAL
		UNION
		SELECT 'dally' lastName, '0' gender, 'dally@kk.com' email FROM DUAL
	)
*/



