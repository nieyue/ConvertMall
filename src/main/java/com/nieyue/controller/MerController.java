package com.nieyue.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.Mer;
import com.nieyue.service.MerService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;


/**
 * 商品控制类
 * @author yy
 *
 */
@RestController
@RequestMapping("/mer")
public class MerController {
	@Resource
	private MerService merService;
	
	/**
	 * 商品分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingMer(
			@RequestParam(value="price",required=false)Double price,
			@RequestParam(value="saleNumber",required=false)Integer saleNumber,
			@RequestParam(value="saleMoney",required=false)Double saleMoney,
			@RequestParam(value="merCateId",required=false)Integer merCateId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Mer> list = new ArrayList<Mer>();
			list= merService.browsePagingMer(price,saleNumber,saleMoney,merCateId,createDate,updateDate,status,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 商品修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateMer(@RequestBody Mer mer,HttpSession session)  {
		boolean um = merService.updateMer(mer);
		return ResultUtil.getSR(um);
	}
	/**
	 * 商品增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addMer(@RequestBody Mer mer, HttpSession session) {
		boolean am = merService.addMer(mer);
		return ResultUtil.getSR(am);
	}
	/**
	 * 商品删除
	 * @return
	 */
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delMer(@RequestParam("merId") Integer merId,HttpSession session)  {
		boolean dm = merService.delMer(merId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 商品浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="price",required=false)Double price,
			@RequestParam(value="saleNumber",required=false)Integer saleNumber,
			@RequestParam(value="saleMoney",required=false)Double saleMoney,
			@RequestParam(value="merCateId",required=false)Integer merCateId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			HttpSession session)  {
		int count = merService.countAll(price,saleNumber,saleMoney,merCateId,createDate,updateDate,status);
		return count;
	}
	/**
	 * 商品单个加载
	 * @return
	 */
	@RequestMapping(value = "/{merId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadMer(@PathVariable("merId") Integer merId,HttpSession session)  {
		List<Mer> list = new ArrayList<Mer>();
		Mer mer = merService.loadMer(merId);
			if(mer!=null &&!mer.equals("")){
				list.add(mer);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 商品单个加载
	 * @return
	 */
	@RequestMapping(value = "/loadSmall/{merId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadSmallMer(@PathVariable("merId") Integer merId,HttpSession session)  {
		List<Mer> list = new ArrayList<Mer>();
		Mer mer = merService.loadSmallMer(merId);
		if(mer!=null &&!mer.equals("")){
			list.add(mer);
			return ResultUtil.getSlefSRSuccessList(list);
		}else{
			return ResultUtil.getSlefSRFailList(list);
		}
	}
	
}
