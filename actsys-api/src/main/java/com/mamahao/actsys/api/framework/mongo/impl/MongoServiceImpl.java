package com.mamahao.actsys.api.framework.mongo.impl;

import com.mamahao.actsys.api.framework.mongo.MongoService;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.TaggableReadPreference;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/11
 * Time           :   18:45
 * Description    :
 */
@ConditionalOnBean(value = {MongoTemplate.class, MongoClient.class})
@Component
public class MongoServiceImpl implements MongoService {
    @Resource(name = "mongoTemplate")
    private MongoTemplate mongoTemplate;

    @Resource(name = "mongoClient")
    private MongoClient mongoClient;

    @Resource(name = "mongoTemplate4Read")
    private MongoTemplate mongoTemplate4Read;

    @Resource(name = "mongoClient4Read")
    private MongoClient mongoClient4Read;


    public void test(){
    }

    @Override
    public <T> T findById(Object id, Class<T> clazz) {
        return mongoTemplate.findById(id,clazz);
    }

    @Override
    public MongoTemplate getTemplate() {
        return mongoTemplate;
    }

    @Override
    public MongoTemplate getTemplate4Read() {
        return mongoTemplate4Read;
    }

    @Override
    public DBCollection getDefaultDBCollection(String collectionName) {
        DBCollection collection = mongoTemplate.getDb().getCollection(collectionName);
        return collection;
    }

    @Override
    public DBCollection getCollection(String database, String collectionName) {
        DB db = mongoClient.getDB(database);
        if(db != null){
            return db.getCollection(collectionName);
        }
        return null;
    }

    @Override
    public DBCollection getDefaultDBCollection4Read(String collectionName) {
        mongoClient4Read.setReadPreference(TaggableReadPreference.primaryPreferred());
        return null;
    }

    @Override
    public DBCollection getCollection4Read(String database, String collectionName) {
        DB db = mongoClient4Read.getDB(database);
        if(db != null){
            return db.getCollection(collectionName);
        }
        return null;
    }
}
