package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.OrderMer;

/**
 * 订单商品数据库接口
 * @author yy
 *
 */
@Mapper
public interface OrderMerDao {
	/** 新增订单商品*/	
	public boolean addOrderMer(OrderMer orderMer) ;	
	/** 删除订单商品 */	
	public boolean delOrderMer(Integer orderMerId) ;
	/** 更新订单商品*/	
	public boolean updateOrderMer(OrderMer orderMer);
	/** 装载订单商品 */	
	public OrderMer loadOrderMer(Integer orderMerId);	
	/** 订单商品总共数目 */	
	public int countAll(
			@Param("merOrderId")Integer merOrderId,
			@Param("status")Integer status,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate
			);	
	/** 分页订单商品信息 */
	public List<OrderMer> browsePagingOrderMer(
			@Param("merOrderId")Integer merOrderId,
			@Param("status")Integer status,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
