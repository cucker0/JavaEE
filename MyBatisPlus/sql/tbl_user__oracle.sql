-- Oracle  key sequence测试

DROP TABLE tbl_user;
-- table tbl_user
CREATE TABLE tbl_user (
    id NUMBER(11) NOT NULL,
    username VARCHAR2(32),
    gender NUMBER,
    phone VARCHAR2(16)
);


-- 创建序列，用于自增主键
--     START WITH 1: 从1开始自增，包括1
--     NCREMENT by 1: 步长为1
CREATE SEQUENCE seq_tbl_user START WITH 1 INCREMENT by 1;

-- 生成下一个序列值，
SELECT seq_tbl_user.NEXTVAL FROM dual;

-- 获取当前序列值
SELECT seq_tbl_user.CURRVAL FROM dual;


-- 批量插入数据
INSERT INTO t_emp(id, username, gender, phone) VALUES(seq_tbl_user.NEXTVAL, '丘维声', 1, '13211335566');
INSERT INTO t_emp(id, last_name, gender, phone) VALUES(seq_tbl_user.NEXTVAL, '郭德刚', 1, '13211337788');

SELECT * FROM tbl_user;

SELECT * FROM V$VERSION;

