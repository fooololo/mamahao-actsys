package com.mamahao.actsys.api.po;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;

/**
 * Created by huluohu on 2016/7/11.
 */
public class MongoDocument {
    @Id
    @GeneratedValue
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
