package com.lancslot.morn.service.base.impl;


import com.lancslot.morn.dao.base.BaseDao;

import java.io.Serializable;

public interface BaseDeleteService<D extends BaseDao<T, PK>, T, PK extends Serializable> {

    /**
     *  删除数据
     * @param t
     * @return
     */
    int delete(T t);
}
