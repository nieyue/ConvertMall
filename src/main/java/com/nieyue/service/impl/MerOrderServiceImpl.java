package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Finance;
import com.nieyue.bean.Mer;
import com.nieyue.bean.MerOrder;
import com.nieyue.bean.OrderMer;
import com.nieyue.bean.ReceiptInfo;
import com.nieyue.business.FinanceBusiness;
import com.nieyue.dao.MerOrderDao;
import com.nieyue.service.MerOrderService;
import com.nieyue.service.MerService;
import com.nieyue.service.OrderMerService;
import com.nieyue.service.ReceiptInfoService;
import com.nieyue.util.DateUtil;
@Service
public class MerOrderServiceImpl implements MerOrderService{
	@Resource
	MerOrderDao merOrderDao;
	@Resource
	StringRedisTemplate stringRedisTemplate;
	@Resource
	FinanceBusiness financeBusiness;
	@Resource
	OrderMerService orderMerService;
	@Resource
	MerService merService;
	@Resource
	ReceiptInfoService receiptInfoService;
	/**
	 * {acountId:1000,receiptInfoId:1000,orderMerList:[{merId:1010,number:4}]}
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addMerOrder(MerOrder merOrder) {
		boolean b=false;
		Double merOrderMoney=0.0;
		//判断库存和金钱
		List<OrderMer> orderMerList = merOrder.getOrderMerList();
		for (int i = 0; i < orderMerList.size(); i++) {
			OrderMer orderMer = orderMerList.get(i);
			Integer mid = orderMer.getMerId();
			//商品
			Mer mer = merService.loadSmallMer(mid);
			if(mer.getStock()-orderMer.getNumber()<=0){//没有库存
				return b;
			}
			merOrderMoney+=mer.getPrice();
		}
		//判断购买人的财务是否够
		try {
			Finance finance = financeBusiness.getFinanceByAcountId(merOrder.getAcountId());
			if(finance.getMoney()-merOrderMoney<0.0){
				//throw new MyInsufficientMoneyException();
				return b;//不够
			}
		} catch (Exception e) {
			return b;
		}//获取
		//增加商品订单
		merOrder.setCreateDate(new Date());
		merOrder.setUpdateDate(new Date());
		BoundValueOperations<String, String> orderBvo = stringRedisTemplate.boundValueOps(DateUtil.getImgDir()+"ConvertMallIncreament");
		orderBvo.expire(DateUtil.currentToEndTime(), TimeUnit.SECONDS);
		orderBvo.increment(1);
		//订单号（23位）=随机4位+14位时间+自增5位
		merOrder.setOrderNumber(((int) (Math.random()*9000)+1000)+DateUtil.getOrdersTime()+(Integer.valueOf(orderBvo.get())+10000));
		b = merOrderDao.addMerOrder(merOrder);
		//增加订单的商品
		for (int i = 0; i < orderMerList.size(); i++) {
			OrderMer orderMer = orderMerList.get(i);
			Integer mid = orderMer.getMerId();
			//修改商品
			Mer mer = merService.loadSmallMer(mid);
			mer.setStock(mer.getStock()-orderMer.getNumber());//减库存
			mer.setSaleNumber(mer.getSaleNumber()+orderMer.getNumber());//加销售数量
			mer.setSaleMoney(mer.getSaleMoney()+orderMer.getNumber()*mer.getPrice());//计算销售额=原有销售额+销售数目*单价
			mer.setUpdateDate(new Date());
			mer.setDetail(null);
			merService.updateMer(mer);//修改商品
			orderMer.setCreateDate(new Date());
			orderMer.setUpdateDate(new Date());
			orderMer.setMerOrderId(merOrder.getMerOrderId());
			orderMer.setStatus(1);
			orderMer.setMer(mer);
			orderMer.setMerId(mid);
			orderMer.setPrice(mer.getPrice());
			orderMer.setTotalPrice(orderMer.getPrice()*orderMer.getNumber());
			b=orderMerService.addOrderMer(orderMer);
		}
		
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delMerOrder(Integer merOrderId) {
		boolean b = merOrderDao.delMerOrder(merOrderId);
		List<OrderMer> orderMerList = orderMerService.browsePagingOrderMer(merOrderId,null, null, null, 1, Integer.MAX_VALUE, "order_mer_id", "desc");
		for (int i = 0; i < orderMerList.size(); i++) {
			OrderMer orderMer = orderMerList.get(i);
			b=orderMerService.delOrderMer(orderMer.getOrderMerId());
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateMerOrder(MerOrder merOrder) {
		merOrder.setUpdateDate(new Date());
		boolean b = merOrderDao.updateMerOrder(merOrder);
		return b;
	}

	@Override
	public MerOrder loadMerOrder(Integer merOrderId) {
		MerOrder merOrder = merOrderDao.loadMerOrder(merOrderId);
		List<OrderMer> orderMerList = orderMerService.browsePagingOrderMer(merOrder.getMerOrderId(),null, null, null, 1, Integer.MAX_VALUE, "order_mer_id", "desc");
		merOrder.setOrderMerList(orderMerList);
		ReceiptInfo receiptInfo = receiptInfoService.loadReceiptInfo(merOrder.getReceiptInfoId());
		merOrder.setReceiptInfo(receiptInfo);
		return merOrder;
	}

	@Override
	public int countAll(
			Integer acountId,
			Date createDate,
			Date updateDate) {
		int c = merOrderDao.countAll(
				acountId,
				createDate,
				updateDate);
		return c;
	}

	@Override
	public List<MerOrder> browsePagingMerOrder(
			Integer acountId,
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
		List<MerOrder> l = merOrderDao.browsePagingMerOrder(
				acountId,
				createDate,
				updateDate,
				pageNum-1, pageSize, orderName, orderWay);
		
		for (int i = 0; i < l.size(); i++) {
			MerOrder merOrder = l.get(i);
			List<OrderMer> orderMerList = orderMerService.browsePagingOrderMer(merOrder.getMerOrderId(),null, null, null, 1, Integer.MAX_VALUE, "order_mer_id", "desc");
			merOrder.setOrderMerList(orderMerList);
			ReceiptInfo receiptInfo = receiptInfoService.loadReceiptInfo(merOrder.getReceiptInfoId());
			merOrder.setReceiptInfo(receiptInfo);
		}
		return l;
	}

	
}
