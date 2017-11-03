package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.MerOrder;

/**
 * 商品订单数据库接口
 * @author yy
 *
 */
@Mapper
public interface MerOrderDao {
	/** 新增商品订单*/	
	public boolean addMerOrder(MerOrder merOrder) ;	
	/** 删除商品订单 */	
	public boolean delMerOrder(Integer merOrderId) ;
	/** 更新商品订单*/	
	public boolean updateMerOrder(MerOrder merOrder);
	/** 装载商品订单 */	
	public MerOrder loadMerOrder(Integer merOrderId);	
	/** 商品订单总共数目 */	
	public int countAll(
			@Param("acountId")Integer acountId,
			@Param("orderNumber")String orderNumber,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate
			);	
	/** 分页商品订单信息 */
	public List<MerOrder> browsePagingMerOrder(
			@Param("acountId")Integer acountId,
			@Param("orderNumber")String orderNumber,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
