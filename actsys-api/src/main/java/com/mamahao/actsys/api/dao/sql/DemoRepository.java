package com.mamahao.actsys.api.dao.sql;

import com.mamahao.actsys.api.po.Demo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by huluohu on 2016/7/11.
 */
//@Repository
public interface DemoRepository extends PagingAndSortingRepository<Demo,Long> {
}
