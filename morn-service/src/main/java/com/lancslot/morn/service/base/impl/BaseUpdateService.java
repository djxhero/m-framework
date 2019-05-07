package com.lancslot.morn.service.base.impl;


import com.lancslot.morn.dao.base.BaseDao;

import java.io.Serializable;

public interface BaseUpdateService<D extends BaseDao<T, PK>, T, PK extends Serializable> {

    int updateByPrimaryKey(T t);

    int updateByPrimaryKeySelective(T t);
}
