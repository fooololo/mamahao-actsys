package com.mamahao.actsys.api.dao.repository;

import com.mamahao.actsys.api.po.Demo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by huluohu on 2016/7/11.
 */
@Repository
public interface DemoRepository extends PagingAndSortingRepository<Demo,Long> {
}
