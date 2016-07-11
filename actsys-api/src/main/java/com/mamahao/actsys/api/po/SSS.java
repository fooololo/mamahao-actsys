package com.mamahao.actsys.api.po;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by huluohu on 2016/7/11.
 */
@Document(collection = "sss")
public class SSS extends MongoDocument{
    private Long shopId;
    private String shopName;
    private List<Double> gps;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<Double> getGps() {
        return gps;
    }

    public void setGps(List<Double> gps) {
        this.gps = gps;
    }
}
