package com.mamahao.actsys.api.configuration.datasource.properties;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/7/5
 * Time           :   21:02
 * Description    :
 */
public class DataSourceConfig {
    @NotBlank(message = "数据源名称不能为空")
    private String name;
    private boolean master = false;
    @NotBlank(message = "数据源类型不能为空")
    private String type;
    @NotBlank(message = "数据源驱动不能为空")
    private String driverClassName;
    @NotBlank(message = "数据源地址不能为空")
    private String url;
    @NotBlank(message = "数据源账户名不能为空")
    private String username;
    @NotBlank(message = "数据源账户密码不能为空")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
