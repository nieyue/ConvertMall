package com.nieyue.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.OrderMer;
import com.nieyue.service.OrderMerService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;


/**
 * 订单商品控制类
 * @author yy
 *
 */
@RestController
@RequestMapping("/orderMer")
public class OrderMerController {
	@Resource
	private OrderMerService orderMerService;
	
	/**
	 * 订单商品分页浏览
	 * @param orderName 订单商品排序数据库字段
	 * @param orderWay 订单商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingOrderMer(
			@RequestParam(value="merOrderId",required=false)Integer merOrderId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="order_mer_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<OrderMer> list = new ArrayList<OrderMer>();
			list= orderMerService.browsePagingOrderMer(merOrderId,status,createDate,updateDate,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 订单商品修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateOrderMer(@ModelAttribute OrderMer orderMer,HttpSession session)  {
		boolean um = orderMerService.updateOrderMer(orderMer);
		return ResultUtil.getSR(um);
	}
	/**
	 * 订单商品增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addOrderMer(@ModelAttribute OrderMer orderMer, HttpSession session) {
		boolean am = orderMerService.addOrderMer(orderMer);
		return ResultUtil.getSR(am);
	}
	/**
	 * 订单商品删除
	 * @return
	 */
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delOrderMer(@RequestParam("orderMerId") Integer orderMerId,HttpSession session)  {
		boolean dm = orderMerService.delOrderMer(orderMerId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 订单商品浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="merOrderId",required=false)Integer merOrderId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			HttpSession session)  {
		int count = orderMerService.countAll(merOrderId,status,createDate,updateDate);
		return count;
	}
	/**
	 * 订单商品单个加载
	 * @return
	 */
	@RequestMapping(value = "/{orderMerId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadOrderMer(@PathVariable("orderMerId") Integer orderMerId,HttpSession session)  {
		List<OrderMer> list = new ArrayList<OrderMer>();
		OrderMer orderMer = orderMerService.loadOrderMer(orderMerId);
			if(orderMer!=null &&!orderMer.equals("")){
				list.add(orderMer);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
