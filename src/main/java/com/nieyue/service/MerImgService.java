package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.MerImg;

/**
 * 商品逻辑层接口
 * @author yy
 *
 */
public interface MerImgService {
	/** 新增商品 */	
	public boolean addMerImg(MerImg merImg) ;	
	/** 删除商品 */	
	public boolean delMerImg(Integer merImgId) ;
	/** 更新商品*/	
	public boolean updateMerImg(MerImg merImg);
	/** 装载商品 */	
	public MerImg loadMerImg(Integer merImgId);	
	/** 商品总共数目 */	
	public int countAll(
			Integer merId,
			Integer orderNum,
			Date updateDate
			);
	/** 分页商品信息 */
	public List<MerImg> browsePagingMerImg(
			Integer merId,
			Integer orderNum,
			Date updateDate,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
