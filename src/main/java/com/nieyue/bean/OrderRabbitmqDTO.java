package com.nieyue.bean;

import java.io.Serializable;

/**
 * 订单队列
 * @author 聂跃
 * @date 2017年8月9日
 */
public class OrderRabbitmqDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 账户ID
	 */
	private Integer acountId;
	public OrderRabbitmqDTO(Integer acountId) {
		super();
		this.acountId = acountId;
	}
	public OrderRabbitmqDTO() {
		super();
	}
	public Integer getAcountId() {
		return acountId;
	}
	public void setAcountId(Integer acountId) {
		this.acountId = acountId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
