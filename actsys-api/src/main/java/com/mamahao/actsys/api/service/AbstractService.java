package com.mamahao.actsys.api.service;

import tk.mybatis.mapper.common.BaseMapper;

import java.io.Serializable;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/5
 * Time           :   9:18
 * Description    :
 */
public abstract class AbstractService<T,ID extends Serializable> implements BaseService<T,ID>  {
    public abstract BaseMapper getMapper();
    @Override
    public T findByPrimaryKey(ID id) {
        return (T) getMapper().selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(ID id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    @Override
    public int save(T record) {
        return getMapper().insert(record);
    }

    @Override
    public int saveSelective(T record) {
        return getMapper().insertSelective(record);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return getMapper().updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return getMapper().updateByPrimaryKeySelective(record);
    }
}
