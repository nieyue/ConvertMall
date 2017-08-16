package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.OrderMer;

/**
 * 订单商品逻辑层接口
 * @author yy
 *
 */
public interface OrderMerService {
	/** 新增订单商品 */	
	public boolean addOrderMer(OrderMer orderMer) ;	
	/** 删除订单商品 */	
	public boolean delOrderMer(Integer orderMerId) ;
	/** 更新订单商品*/	
	public boolean updateOrderMer(OrderMer orderMer);
	/** 装载订单商品 */	
	public OrderMer loadOrderMer(Integer orderMerId);	
	/** 订单商品总共数目 */	
	public int countAll(
			Integer merOrderId,
			Integer status,
			Date createDate,
			Date updateDate);
	/** 分页订单商品信息 */
	public List<OrderMer> browsePagingOrderMer(
			Integer merOrderId,
			Integer status,
			Date createDate,
			Date updateDate,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
