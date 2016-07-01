package com.mamahao.actsys.api.configuration.datasource;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/6/30
 * Time           :   10:59
 * Description    :
 */
public class LookupKey {
    /**
     * 表示数据库
     */
    private String targetDatabase;
    /**
     * 表示数据库的主从
     */
    private String targetDataSource;

    public LookupKey() {
    }

    public LookupKey(String targetDatabase, String targetDataSource) {
        this.targetDatabase = targetDatabase;
        this.targetDataSource = targetDataSource;
    }

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public String getTargetDataSource() {
        return targetDataSource;
    }

    public void setTargetDataSource(String targetDataSource) {
        this.targetDataSource = targetDataSource;
    }
}
