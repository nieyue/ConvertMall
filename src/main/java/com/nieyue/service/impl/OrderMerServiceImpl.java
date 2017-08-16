package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Mer;
import com.nieyue.bean.OrderMer;
import com.nieyue.dao.OrderMerDao;
import com.nieyue.service.MerService;
import com.nieyue.service.OrderMerService;
@Service
public class OrderMerServiceImpl implements OrderMerService{
	@Resource
	OrderMerDao orderMerDao;
	@Resource
	MerService merService;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addOrderMer(OrderMer orderMer) {
		orderMer.setCreateDate(new Date());
		orderMer.setUpdateDate(new Date());
		orderMer.setStatus(1);
		boolean b = orderMerDao.addOrderMer(orderMer);
		
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delOrderMer(Integer orderMerId) {
		boolean b = orderMerDao.delOrderMer(orderMerId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateOrderMer(OrderMer orderMer) {
		orderMer.setUpdateDate(new Date());
		boolean b = orderMerDao.updateOrderMer(orderMer);
		
		return b;
	}

	@Override
	public OrderMer loadOrderMer(Integer orderMerId) {
		OrderMer OrderMer = orderMerDao.loadOrderMer(orderMerId);
		return OrderMer;
	}

	@Override
	public int countAll(
			Integer merOrderId,
			Integer status,
			Date createDate,
			Date updateDate) {
		int c = orderMerDao.countAll(
				merOrderId,
				status,
				createDate,
				updateDate);
		return c;
	}

	@Override
	public List<OrderMer> browsePagingOrderMer(
			Integer merOrderId,
			Integer status,
			Date createDate,
			Date updateDate,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<OrderMer> l = orderMerDao.browsePagingOrderMer(
				merOrderId,
				status,
				createDate,
				updateDate,
				pageNum-1, pageSize, orderName, orderWay);
		for (OrderMer orderMer : l) {
			Mer mer = merService.loadSmallMer(orderMer.getMerId());
			orderMer.setMer(mer);
		}
		return l;
	}

	
}
