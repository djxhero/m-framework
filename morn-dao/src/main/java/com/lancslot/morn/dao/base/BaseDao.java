package com.lancslot.morn.dao.base;

import java.io.Serializable;

/**
 * 只创建对表的增删改操作
 */
public interface BaseDao<T, Pk extends Serializable> extends
        MornBaseDao<T, Pk> {
}
