package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单的商品
 * @author 聂跃
 * @date 2017年8月12日
 */
public class OrderMer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 订单的商品Id
	 */
	private Integer orderMerId;
	/**
	 * 商品单价
	 */
	private Double price;
	/**
	 * 商品数目
	 */
	private Integer number;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 商品总价
	 */
	private Double totalPrice;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 更新时间
	 */
	private Date updateDate;
	/**
	 * 状态，0已下单-未支付，1已支付-未发货，2已发货-未完成，3申请退款，4已退款，5拒绝退款,6已完成
	 */
	private Integer status;
	/**
	 * 快递公司,0暂无,1顺丰速运SF,2中通快递ZTO,3圆通速递YTO,4申通快递STO,5百世快递BestExpress,6韵达快递YUNDA,7中国邮政EMS,8宅急送ZJS,9FedEx联邦,10京东物流
	 * 11德邦物流,12安能物流,13优速快递,14天天快递,15汇通快递,16国通快递,17全峰快递,18菜鸟物流
	 */
	private Integer courierCompany;
	/**
	 * 快递单号
	 */
	private String courierNumber;
	
	/**
	 * 商品的订单Id
	 */
	private Integer merOrderId;
	/**
	 * 商品Id
	 */
	private Integer merId;
	/**
	 * 商品
	 */
	private Mer mer;
	public Integer getOrderMerId() {
		return orderMerId;
	}
	public void setOrderMerId(Integer orderMerId) {
		this.orderMerId = orderMerId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
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
	public Integer getMerOrderId() {
		return merOrderId;
	}
	public void setMerOrderId(Integer merOrderId) {
		this.merOrderId = merOrderId;
	}
	public Integer getMerId() {
		return merId;
	}
	public void setMerId(Integer merId) {
		this.merId = merId;
	}
	public Mer getMer() {
		return mer;
	}
	public void setMer(Mer mer) {
		this.mer = mer;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCourierNumber() {
		return courierNumber;
	}
	public void setCourierNumber(String courierNumber) {
		this.courierNumber = courierNumber;
	}
	public Integer getCourierCompany() {
		return courierCompany;
	}
	public void setCourierCompany(Integer courierCompany) {
		this.courierCompany = courierCompany;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public OrderMer() {
		super();
	}
	public OrderMer(Integer orderMerId, Double price, Integer number, String remark, Double totalPrice, Date createDate,
			Date updateDate, Integer status, Integer courierCompany, String courierNumber, Integer merOrderId,
			Integer merId, Mer mer) {
		super();
		this.orderMerId = orderMerId;
		this.price = price;
		this.number = number;
		this.remark = remark;
		this.totalPrice = totalPrice;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.status = status;
		this.courierCompany = courierCompany;
		this.courierNumber = courierNumber;
		this.merOrderId = merOrderId;
		this.merId = merId;
		this.mer = mer;
	}
	
}
