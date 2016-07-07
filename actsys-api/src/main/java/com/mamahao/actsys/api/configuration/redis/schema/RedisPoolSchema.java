package com.mamahao.actsys.api.configuration.redis.schema;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/6
 * Time           :   14:13
 * Description    :
 */
public class RedisPoolSchema {
    private int maxTotal = 1;
    private int maxIdle = 1;
    private int maxWaitMillis = 1;
    private int minIdle = 0;

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }
}
