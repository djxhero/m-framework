package com.lancslot.morn.service.base.impl;


import com.lancslot.morn.dao.base.BaseDao;
import com.lancslot.morn.utils.mybatis.PageEntity;
import com.lancslot.morn.utils.mybatis.PagingResult;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseSelectService<D extends BaseDao<T, PK>, T, PK extends Serializable> {

    T queryById(Integer id);

    List<T> queryAll();

    List<T> query(T t);

    List<T> queryParam(Map params);

    PagingResult<T> queryByPage(PageEntity pageEntity);

    int selectCount(T t);
}
