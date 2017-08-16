package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品图片
 * @author yy
 *
 */
public class MerImg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商品图片id
	 */
	private Integer merImgId;
	
	/**
	 * 商品图地址
	 */
	private String imgAddress;
	/**
	 * 排序数字
	 */
	private Integer orderNum;
	/**
	 * 更新时间
	 */
	private Date updateDate;
	/**
	 * 商品id,外键
	 */
	private Integer merId;
	public Integer getMerImgId() {
		return merImgId;
	}
	public void setMerImgId(Integer merImgId) {
		this.merImgId = merImgId;
	}
	public String getImgAddress() {
		return imgAddress;
	}
	public void setImgAddress(String imgAddress) {
		this.imgAddress = imgAddress;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getMerId() {
		return merId;
	}
	public void setMerId(Integer merId) {
		this.merId = merId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public MerImg(Integer merImgId, String imgAddress, Integer orderNum, Date updateDate, Integer merId) {
		super();
		this.merImgId = merImgId;
		this.imgAddress = imgAddress;
		this.orderNum = orderNum;
		this.updateDate = updateDate;
		this.merId = merId;
	}
	public MerImg() {
		super();
	}

	
}
