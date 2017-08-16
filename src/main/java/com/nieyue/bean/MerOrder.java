package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 商品订单
 * @author yy
 *
 */
public class MerOrder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商品订单id
	 */
	private Integer merOrderId;
	
	/**
	 * 订单号
	 */
	private String orderNumber;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 更新时间
	 */
	private Date updateDate;
	/**
	 * 下单id,外键
	 */
	private Integer acountId;
	/**
	 * 收货信息id,外键
	 */
	private Integer receiptInfoId;
	/**
	 * 收货信息
	 */
	private ReceiptInfo receiptInfo;
	/**
	 * 多个订单商品
	 */
	private List<OrderMer> orderMerList;
	public Integer getMerOrderId() {
		return merOrderId;
	}
	public void setMerOrderId(Integer merOrderId) {
		this.merOrderId = merOrderId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
	public Integer getReceiptInfoId() {
		return receiptInfoId;
	}
	public void setReceiptInfoId(Integer receiptInfoId) {
		this.receiptInfoId = receiptInfoId;
	}
	public ReceiptInfo getReceiptInfo() {
		return receiptInfo;
	}
	public void setReceiptInfo(ReceiptInfo receiptInfo) {
		this.receiptInfo = receiptInfo;
	}
	public List<OrderMer> getOrderMerList() {
		return orderMerList;
	}
	public void setOrderMerList(List<OrderMer> orderMerList) {
		this.orderMerList = orderMerList;
	}
	public MerOrder(Integer merOrderId, String orderNumber,  Date createDate, Date updateDate,
			Integer acountId,Integer receiptInfoId,ReceiptInfo receiptInfo,List<OrderMer> orderMerList) {
		super();
		this.merOrderId = merOrderId;
		this.orderNumber = orderNumber;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.acountId = acountId;
		this.receiptInfoId=receiptInfoId;
		this.receiptInfo=receiptInfo;
		this.orderMerList=orderMerList;
	}
	public MerOrder() {
		super();
	}
	
}
