package com.lancslot.morn.service.base.impl;


import com.lancslot.morn.dao.base.BaseDao;

import java.io.Serializable;

public interface BaseInsertService<D extends BaseDao<T, PK>, T, PK extends Serializable> {

    /**
     *  插入数据
     * @param t
     * @return
     */
    int insert(T t);

    /**
     *  插入数据
     * @param t
     * @return
     */
    int insertSelective(T t);

}
