package com.mamahao.actsys.api.configuration.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/4
 * Time           :   14:23
 * Description    :   通用mapper接口，该接口不能被mybatis扫描到
 */
public interface IMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
