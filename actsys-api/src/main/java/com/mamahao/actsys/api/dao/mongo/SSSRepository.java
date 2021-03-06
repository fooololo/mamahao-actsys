package com.mamahao.actsys.api.dao.mongo;

import com.mamahao.actsys.api.po.SSS;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by huluohu on 2016/7/11.
 */
public interface SSSRepository extends PagingAndSortingRepository<SSS,String> {
    List<SSS> findByShopId(Long shopId, Pageable pageable);
}
