package com.lancslot.morn.dao.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.base.BaseDeleteMapper;
import tk.mybatis.mapper.common.base.BaseInsertMapper;
import tk.mybatis.mapper.common.base.BaseSelectMapper;
import tk.mybatis.mapper.common.base.BaseUpdateMapper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 只创建对表的增删改操作
 */
public interface MornBaseDao<T, Pk extends Serializable> extends
        BaseInsertMapper<T>,
        BaseUpdateMapper<T>,
        BaseDeleteMapper<T>,
        BaseSelectMapper<T>,
        MySqlMapper<T>,
        Mapper<T>{

        List<T> selectParam(@SuppressWarnings("rawtypes") Map param);

        int deleteByParam(@SuppressWarnings("rawtypes") Map param);
}
