package com.mamahao.actsys.api.configuration.redis.schema;

import java.util.List;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/7
 * Time           :   9:33
 * Description    :
 */
public class RedisSentinelSchema {
    private String master;
    private List<RedisSentinelNode> nodes;

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public List<RedisSentinelNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<RedisSentinelNode> nodes) {
        this.nodes = nodes;
    }
}
