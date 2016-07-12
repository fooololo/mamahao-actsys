package com.mamahao.actsys.api.po;

import javax.persistence.*;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/5/31
 * Time           :   14:52
 * Description    :
 */
@Entity
@Table(name = "t_demo")
public class Demo extends BaseEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	private String name;

	public Demo() {
	}
	public Demo(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
