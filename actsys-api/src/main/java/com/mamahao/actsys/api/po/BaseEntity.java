package com.mamahao.actsys.api.po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/4
 * Time           :   16:08
 * Description    :
 */

public class BaseEntity implements Serializable{
    @Transient
    protected Integer page;
    @Transient
    protected Integer pageSize;
    @Transient
    protected long version;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
