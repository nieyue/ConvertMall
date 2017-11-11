package com.nieyue.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.MerOrder;
import com.nieyue.bean.OrderMer;
import com.nieyue.rabbitmq.confirmcallback.Sender;
import com.nieyue.service.MerOrderService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;
import com.nieyue.view.MerOrderXlsView;


/**
 * 商品订单控制类
 * @author yy
 *
 */
@RestController
@RequestMapping("/merOrder")
public class MerOrderController {
	@Resource
	private MerOrderService merOrderService;
	@Resource
	private Sender sender;
	@Resource
	private MerOrderXlsView merOrderXlsView;
	
	/**
	 * 商品订单分页浏览
	 * @param orderName 商品订单排序数据库字段
	 * @param orderWay 商品订单排序方法 asc升序 desc降序
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingMerOrder(
			@RequestParam(value="acountId",required=false)Integer acountId,
			@RequestParam(value="orderNumber",required=false)String orderNumber,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="mer_order_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay) throws Exception  {
			List<MerOrder> list = new ArrayList<MerOrder>();
			list = merOrderService.browsePagingMerOrder(acountId,orderNumber,createDate,updateDate,pageNum, pageSize, orderName, orderWay);
			if( list.size()>0 ){
				
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 下载excel
	 */
	@RequestMapping(value = {"/downloadXls"}, method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	//public ModelAndView downloadXLS(
			public MerOrderXlsView downloadXLS(
			@RequestParam(value="acountId",required=false)Integer acountId,
			@RequestParam(value="orderNumber",required=false)String orderNumber,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="mer_order_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,
			HttpSession session,Model model) {
		List<MerOrder> molist=new ArrayList<MerOrder>();
		List<MerOrder> list = merOrderService.browsePagingMerOrder(acountId, orderNumber,createDate,updateDate, pageNum, pageSize, orderName, orderWay);
		for (int i = 0; i < list.size(); i++) {
			MerOrder mo = list.get(i);
			for (int j = 0; j < mo.getOrderMerList().size(); j++) {
				OrderMer om = mo.getOrderMerList().get(j);
				List<OrderMer> omlist=new ArrayList<OrderMer>();
				if(om.getStatus().equals(status)){
					omlist.add(om);
					mo.getOrderMerList().clear();
					mo.setOrderMerList(omlist);
					molist.add(mo);
				}
			}
		}
		model.addAttribute("merOrderList", molist);
		merOrderXlsView.setFileName("商品订单信息");
		return merOrderXlsView;
		/*HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("url","D:/");
		return new ModelAndView(merOrderXlsView,map);*/
		
	}
	/**
	 * 商品订单修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateMerOrder(@RequestBody MerOrder merOrder,HttpSession session)  {
		boolean um = merOrderService.updateMerOrder(merOrder);
		return ResultUtil.getSR(um);
	}
	/**
	 * 商品订单增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addMerOrder(@RequestBody MerOrder merOrder, HttpSession session) {
		//boolean am = merOrderService.addMerOrder(merOrder);
		sender.sendMerOrder(merOrder);
		return ResultUtil.getSR(true);
	}
	/**
	 * 商品订单删除
	 * @return
	 */
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delMerOrder(@RequestParam("merOrderId") Integer merOrderId,HttpSession session)  {
		boolean dm = merOrderService.delMerOrder(merOrderId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 商品订单浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="acountId",required=false)Integer acountId,
			@RequestParam(value="orderNumber",required=false)String orderNumber,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			HttpSession session)  {
		int count = merOrderService.countAll(acountId,orderNumber,createDate,updateDate);
		return count;
	}
	/**
	 * 商品订单单个加载
	 * @return
	 */
	@RequestMapping(value = "/{merOrderId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadMerOrder(@PathVariable("merOrderId") Integer merOrderId,HttpSession session)  {
		List<MerOrder> list = new ArrayList<MerOrder>();
		MerOrder merOrder = merOrderService.loadMerOrder(merOrderId);
			if(merOrder!=null &&!merOrder.equals("")){
				list.add(merOrder);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
