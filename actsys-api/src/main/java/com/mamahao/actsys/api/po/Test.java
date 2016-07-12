package com.mamahao.actsys.api.po;

import javax.persistence.*;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/8
 * Time           :   9:24
 * Description    :
 */
@Table(name = "t_test")
public class Test extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    private String title;
    private String des;

    public Test() {
    }

    public Test(String title, String des) {
        this.title = title;
        this.des = des;
    }

    public Test(long id, String title, String des) {
        this.id = id;
        this.title = title;
        this.des = des;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
