package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Mer;

/**
 * 商品数据库接口
 * @author yy
 *
 */
@Mapper
public interface MerDao {
	/** 新增商品*/	
	public boolean addMer(Mer mer) ;	
	/** 删除商品 */	
	public boolean delMer(Integer merId) ;
	/** 更新商品*/	
	public boolean updateMer(Mer mer);
	/** 装载商品 */	
	public Mer loadMer(Integer merId);	
	/** 装载small商品 */	
	public Mer loadSmallMer(Integer merId);	
	/** 商品总共数目 */	
	public int countAll(
			@Param("price")Double price,
			@Param("saleNumber")Integer saleNumber,
			@Param("saleMoney")Double saleMoney,
			@Param("merCateId")Integer merCateId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("status")Integer status
			);	
	/** 分页商品信息 */
	public List<Mer> browsePagingMer(
			@Param("price")Double price,
			@Param("saleNumber")Integer saleNumber,
			@Param("saleMoney")Double saleMoney,
			@Param("merCateId")Integer merCateId,
			@Param("createDate")Date createDate,
			@Param("updateDate")Date updateDate,
			@Param("status")Integer status,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
