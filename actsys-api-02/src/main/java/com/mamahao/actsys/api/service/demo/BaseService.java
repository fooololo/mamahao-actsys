package com.mamahao.actsys.api.service.demo;

import java.io.Serializable;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/4
 * Time           :   21:09
 * Description    :
 */
public interface BaseService<T,ID extends Serializable> {
    T findByPrimaryKey(ID id);
    int deleteByPrimaryKey(ID id);
    int save(T record);

    /**
     * 仅插入不为null的字段
     * @param record
     * @return
     */
    int saveSelective(T record);
    int updateByPrimaryKey(T record);
    int updateByPrimaryKeySelective(T record);
}
