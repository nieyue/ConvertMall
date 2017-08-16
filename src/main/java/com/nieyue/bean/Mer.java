package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 商品
 * @author yy
 *
 */
public class Mer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商品id
	 */
	private Integer merId;
	/**
	 * 商品名称
	 */
	private String title;
	/**
	 * 原始价格
	 */
	private Double oldPrice;
	/**
	 * 价格
	 */
	private Double price;
	/**
	 *库存
	 */
	private Integer stock;
	/**
	 *销售数量
	 */
	private Integer saleNumber;
	/**
	 *销售额
	 */
	private Double saleMoney;
	/**
	 *折扣
	 */
	private Double discount;
	/**
	 *邮费默认0，包邮
	 */
	private Double postage;
	/**
	 *下架0,上架1，售完2
	 */
	private Integer status;
	/**
	 *商品详情
	 */
	private String detail;
	/**
	 *商品类型id,外键
	 */
	private Integer merCateId;
	/**
	 * 商品创建时间
	 */
	private Date createDate;
	/**
	 * 更新时间
	 */
	private Date updateDate;
	/**
	 * 商品图片
	 */
	private List<MerImg> merImgList;
	/**
	 * 商品类型
	 */
	private MerCate merCate;
	
	
	public Integer getMerId() {
		return merId;
	}
	public void setMerId(Integer merId) {
		this.merId = merId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(Double oldPrice) {
		this.oldPrice = oldPrice;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getSaleNumber() {
		return saleNumber;
	}
	public void setSaleNumber(Integer saleNumber) {
		this.saleNumber = saleNumber;
	}
	public Double getSaleMoney() {
		return saleMoney;
	}
	public void setSaleMoney(Double saleMoney) {
		this.saleMoney = saleMoney;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getPostage() {
		return postage;
	}
	public void setPostage(Double postage) {
		this.postage = postage;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Integer getMerCateId() {
		return merCateId;
	}
	public void setMerCateId(Integer merCateId) {
		this.merCateId = merCateId;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Mer(Integer merId, String title, Double oldPrice, Double price, Integer stock, Integer saleNumber,
			Double saleMoney, Double discount, Double postage, Integer status, String detail, Integer merCateId,
			 Date createDate, Date updateDate,List<MerImg> merImgList,MerCate merCate) {
		super();
		this.merId = merId;
		this.title = title;
		this.oldPrice = oldPrice;
		this.price = price;
		this.stock = stock;
		this.saleNumber = saleNumber;
		this.saleMoney = saleMoney;
		this.discount = discount;
		this.postage = postage;
		this.status = status;
		this.detail = detail;
		this.merCateId = merCateId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.merImgList=merImgList;
		this.merCate=merCate;
	}
	public Mer() {
		super();
	}
	public List<MerImg> getMerImgList() {
		return merImgList;
	}
	public void setMerImgList(List<MerImg> merImgList) {
		this.merImgList = merImgList;
	}
	public MerCate getMerCate() {
		return merCate;
	}
	public void setMerCate(MerCate merCate) {
		this.merCate = merCate;
	}
	
	
}
