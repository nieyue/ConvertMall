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

import com.nieyue.bean.MerImg;
import com.nieyue.service.MerImgService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;


/**
 * 商品图片控制类
 * @author yy
 *
 */
@RestController
@RequestMapping("/merImg")
public class MerImgController {
	@Resource
	private MerImgService merImgService;
	
	/**
	 * 商品图片分页浏览
	 * @param orderName 商品图片排序数据库字段
	 * @param orderWay 商品图片排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingMerImg(
			@RequestParam(value="merId",required=false)Integer merId,
			@RequestParam(value="orderNum",required=false)Integer orderNum,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="mer_img_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<MerImg> list = new ArrayList<MerImg>();
			list= merImgService.browsePagingMerImg(merId,orderNum,updateDate,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 商品图片修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateMerImg(@ModelAttribute MerImg merImg,HttpSession session)  {
		boolean um = merImgService.updateMerImg(merImg);
		return ResultUtil.getSR(um);
	}
	/**
	 * 商品图片增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addMerImg(@ModelAttribute MerImg merImg, HttpSession session) {
		boolean am =merImgService.addMerImg(merImg);
		return ResultUtil.getSR(am);
	}
	/**
	 * 商品图片删除
	 * @return
	 */
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delMerImg(@RequestParam("merImgId") Integer merImgId,HttpSession session)  {
		boolean dm = merImgService.delMerImg(merImgId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 商品图片浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="merId",required=false)Integer merId,
			@RequestParam(value="orderNum",required=false)Integer orderNum,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			HttpSession session)  {
		int count = merImgService.countAll(merId,orderNum,updateDate);
		return count;
	}
	/**
	 * 商品图片单个加载
	 * @return
	 */
	@RequestMapping(value = "/{merImgId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadMerImg(@PathVariable("merImgId") Integer merImgId,HttpSession session)  {
		List<MerImg> list = new ArrayList<MerImg>();
		MerImg merImg = merImgService.loadMerImg(merImgId);
			if(merImg!=null &&!merImg.equals("")){
				list.add(merImg);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
