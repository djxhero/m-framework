package com.lancslot.morn.service.base.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lancslot.morn.dao.base.BaseDao;
import com.lancslot.morn.utils.mybatis.PageEntity;
import com.lancslot.morn.utils.mybatis.PagingResult;
import com.lancslot.morn.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 *
 * @author
 * @create 2018-03-23 10:26
 **/
@SuppressWarnings("unchecked")
public class BaseServiceImpl<D extends BaseDao<T, PK>, T, PK extends Serializable> implements BaseService<D, T, PK> {
	static{
	}


    @Autowired
    protected D baseDao;

    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */

    @Override
    public T queryById(Integer id) {
        return baseDao.selectByPrimaryKey(id);
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    @Override
    public List<T> queryAll() {
        return baseDao.select(null);
    }

    /**
     * 根据参数查询
     * @param t
     * @return
     */
    @Override
    public List<T> query(T t) {
        return baseDao.select(t);
    }

    /**
     * 根据参数查询
     * @param params
     * @return
     */
    @Override
    public List<T> queryParam(Map params) {
        return baseDao.selectParam(params);
    }


    /**
     *  分页查询
     * @param pageEntity
     * @return
     */
    @Override
    public PagingResult<T> queryByPage(PageEntity pageEntity) {
        PageHelper.startPage(pageEntity.getPageNo(), pageEntity.getPageSize());

        List<T> resultList = queryParam(pageEntity.getParam());
        PageInfo<T> page = new PageInfo<>(resultList);

        return new PagingResult<>(page.getPageNum(), page.getTotal(), page.getPages(), resultList);
    }

    @Override
    public int selectCount(T t) {
        return baseDao.selectCount(t);
    }

    @Override
    public int insert(T t){
        return baseDao.insert(t);
    }

    @Override
    public int insertSelective(T t){
        return baseDao.insertSelective(t);
    }

    @Override

    public int delete(T t){
        return baseDao.delete(t);
    }

    @Override
    public int updateByPrimaryKey(T t) {
        return baseDao.updateByPrimaryKey(t);
    }

    @Override
    public int updateByPrimaryKeySelective(T t) {
        return baseDao.updateByPrimaryKeySelective(t);
    }
}
