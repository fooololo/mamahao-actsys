package com.mamahao.actsys.api.framework.mongo;

import com.mongodb.DBCollection;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/11
 * Time           :   18:45
 * Description    :
 */
public interface MongoService {
    DBCollection getDefaultDBCollection(String collectionName);
    DBCollection getCollection(String database,String collectionName);
    DBCollection getDefaultDBCollection4Read(String collectionName);
    DBCollection getCollection4Read(String database,String collectionName);
    MongoTemplate getTemplate();
    MongoTemplate getTemplate4Read();
    <T> T findById(Object id,Class<T> clazz);
}
