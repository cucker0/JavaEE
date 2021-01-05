package com.java.mp.bean;

import com.baomidou.mybatisplus.annotation.KeySequence;

/**
 * 当共用一张数据表时的多个实体类，使用父类来指定 Sequence主键
 */
@KeySequence(value = "seq_tbl_user", clazz = Long.class)
public abstract class BaseUser {
}
