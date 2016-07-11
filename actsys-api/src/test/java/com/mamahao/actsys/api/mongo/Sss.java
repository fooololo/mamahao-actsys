package com.mamahao.actsys.api.mongo;

import org.bson.types.ObjectId;

import java.util.List;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/11
 * Time           :   19:23
 * Description    :
 */
public class Sss {
    private ObjectId _id;
    private Long shopId;
    private String shopName;
    private List<Double> gps;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

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
