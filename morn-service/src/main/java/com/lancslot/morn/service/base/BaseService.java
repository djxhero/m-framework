package com.lancslot.morn.service.base;


import com.lancslot.morn.dao.base.BaseDao;
import com.lancslot.morn.service.base.impl.BaseInsertService;
import com.lancslot.morn.service.base.impl.BaseSelectService;
import com.lancslot.morn.service.base.impl.BaseUpdateService;
import com.lancslot.morn.service.base.impl.BaseDeleteService;

import java.io.Serializable;

public interface BaseService<D extends BaseDao<T, PK>, T, PK extends Serializable> extends
        BaseInsertService<D, T, PK>
        , BaseUpdateService<D, T, PK>
        , BaseSelectService<D, T, PK>
        , BaseDeleteService<D, T, PK> {

}
