package com.mamahao.actsys.api.po;

import javax.persistence.Table;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/5/31
 * Time           :   14:52
 * Description    :
 */
@Table(name = "t_demo")
public class Demo extends Entity{
	public Demo() {
	}

	public Demo(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
