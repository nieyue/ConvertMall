package com.nieyue.controller;

import java.util.ArrayList;
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

import com.nieyue.bean.MerCate;
import com.nieyue.service.MerCateService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;


/**
 * 商品类型控制类
 * @author yy
 *
 */
@RestController
@RequestMapping("/merCate")
public class MerCateController {
	@Resource
	private MerCateService merCateService;
	
	/**
	 * 商品类型分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingMerCate(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="mer_cate_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<MerCate> list = new ArrayList<MerCate>();
			list= merCateService.browsePagingMerCate(pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 商品类型修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateMerCate(@ModelAttribute MerCate merCate,HttpSession session)  {
		boolean um = merCateService.updateMerCate(merCate);
		return ResultUtil.getSR(um);
	}
	/**
	 * 商品类型增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addMerCate(@ModelAttribute MerCate merCate, HttpSession session) {
		boolean am = merCateService.addMerCate(merCate);
		return ResultUtil.getSR(am);
	}
	/**
	 * 商品类型删除
	 * @return
	 */
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delMerCate(@RequestParam("merCateId") Integer merCateId,HttpSession session)  {
		boolean dm = merCateService.delMerCate(merCateId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 商品类型浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = merCateService.countAll();
		return count;
	}
	/**
	 * 商品类型单个加载
	 * @return
	 */
	@RequestMapping(value = "/{merCateId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadMerCate(@PathVariable("merCateId") Integer merCateId,HttpSession session)  {
		List<MerCate> list = new ArrayList<MerCate>();
		MerCate merCate = merCateService.loadMerCate(merCateId);
			if(merCate!=null &&!merCate.equals("")){
				list.add(merCate);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
