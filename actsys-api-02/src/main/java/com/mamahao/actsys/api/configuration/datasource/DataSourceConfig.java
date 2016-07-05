package com.mamahao.actsys.api.configuration.datasource;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/5
 * Time           :   21:02
 * Description    :
 */
public class DataSourceConfig {
    private String name;
    private String type;
    private String driverClassName;
    private String username;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
