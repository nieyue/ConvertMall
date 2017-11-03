package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.MerOrder;

/**
 * 商品订单逻辑层接口
 * @author yy
 *
 */
public interface MerOrderService {
	/** 新增商品订单 */	
	public boolean addMerOrder(MerOrder merOrder) ;	
	/** 删除商品订单 */	
	public boolean delMerOrder(Integer merOrderId) ;
	/** 更新商品订单*/	
	public boolean updateMerOrder(MerOrder merOrder);
	/** 装载商品订单 */	
	public MerOrder loadMerOrder(Integer merOrderId);	
	/** 商品订单总共数目 */	
	public int countAll(
			Integer acountId,
			String orderNumber,
			Date createDate,
			Date updateDate);
	/** 分页商品订单信息 */
	public List<MerOrder> browsePagingMerOrder(
			Integer acountId,
			String orderNumber,
			Date createDate,
			Date updateDate,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
