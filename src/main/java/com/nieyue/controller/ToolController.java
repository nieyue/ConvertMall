package com.nieyue.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.MerOrder;
import com.nieyue.comments.RequestToMethdoItemUtils;
import com.nieyue.comments.RequestToMethodItem;
import com.nieyue.util.DateUtil;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResultList;

import net.sf.json.JSONObject;



/**
 * 控制类
 * @author yy
 *
 */
@RestController
@RequestMapping("/tool")
public class ToolController {
	
	@Resource
	StringRedisTemplate stringRedisTemplate;
	@Resource
	RequestToMethdoItemUtils requestToMethdoItemUtils;
	/**
	 * 获取API接口文档
	 * @return
	 */
	@RequestMapping(value={"/getAPI"}, method = {RequestMethod.GET,RequestMethod.POST})
	public StateResultList getAPI(
			HttpServletRequest request
			){
		List<RequestToMethodItem> requestToMethdoItemUtilsresult = requestToMethdoItemUtils.getRequestToMethodItemList(request);
		return ResultUtil.getSlefSRSuccessList(requestToMethdoItemUtilsresult);
	
	}
	/**
	 * 获取Session
	 * @return
	 */
	@RequestMapping(value={"/getSession"}, method = {RequestMethod.GET,RequestMethod.POST})
	public String getSession(
			HttpSession	 session
			){
		return session.getId();
		
	}
	/**
	 * addIncrement
	 * @return
	 */
	@RequestMapping(value={"/addIncrement"}, method = {RequestMethod.GET,RequestMethod.POST})
	public String addIncrement(
			HttpSession	 session
			){
		BoundValueOperations<String, String> bvo = stringRedisTemplate.boundValueOps(DateUtil.getImgDir()+"Increment");
		bvo.expire(DateUtil.currentToEndTime(), TimeUnit.SECONDS);
		bvo.increment(1);
		return bvo.get();
		
	}
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value={"/addOrder"}, method = {RequestMethod.GET,RequestMethod.POST})
	public String setTest(
			HttpServletRequest request
			){
		MerOrder mo=new MerOrder();
		mo.setAcountId(1000);
		mo.setOrderNumber(UUID.randomUUID().toString().replace("-", ""));
		mo.setMerOrderId(1001);
		mo.setCreateDate(new Date());
		mo.setUpdateDate(new Date());
		System.out.println(mo.toString());
		BoundValueOperations<String, String> order = stringRedisTemplate.boundValueOps("order");
		System.out.println(order.get());
		JSONObject json = JSONObject.fromObject(mo);
		if(order.size()<=0){
			order.set(json.toString()) ;
		}
		MerOrder merOrder = (MerOrder) JSONObject.toBean(JSONObject.fromObject(order.get()), MerOrder.class);
		System.out.println(merOrder.toString());
		System.out.println(merOrder.getCreateDate());
		System.out.println(merOrder.getUpdateDate().toLocaleString());
	return order.get();
	}
	
}
