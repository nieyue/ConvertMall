package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品类型
 * @author yy
 *
 */
public class MerCate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商品类型id
	 */
	private Integer merCateId;
	
	/**
	 * 商品类型名
	 */
	private String name;
	/**
	 * 更新时间
	 */
	private Date updateDate;

	public MerCate(Integer merCateId, String name, String duty, Date updateDate) {
		super();
		this.merCateId = merCateId;
		this.name = name;
		this.updateDate = updateDate;
	}
	public MerCate() {
		super();
	}
	public Integer getMerCateId() {
		return merCateId;
	}
	public void setMerCateId(Integer merCateId) {
		this.merCateId = merCateId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
