package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Mer;
import com.nieyue.bean.MerCate;
import com.nieyue.bean.MerImg;
import com.nieyue.dao.MerDao;
import com.nieyue.service.MerCateService;
import com.nieyue.service.MerImgService;
import com.nieyue.service.MerService;
@Service
public class MerServiceImpl implements MerService{
	@Resource
	MerDao merDao;
	@Resource
	MerImgService merImgService;
	@Resource
	MerCateService merCateService;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addMer(Mer mer) {
		mer.setCreateDate(new Date());
		mer.setUpdateDate(new Date());
		mer.setSaleNumber(0);
		mer.setSaleMoney(0.0);
		if(mer.getDiscount()==null){
			mer.setDiscount(1.0);
		}
		if(mer.getPostage()==null){
		mer.setPostage(0.0);
		}
		if(mer.getStatus()==null||mer.getStatus().equals("")){
			mer.setStatus(1);
		}
		boolean b = merDao.addMer(mer);
		List<MerImg> merImgList = mer.getMerImgList();
		for (int i = 0; i < merImgList.size(); i++) {
			MerImg img = merImgList.get(i);
			img.setMerId(mer.getMerId());
			b=merImgService.addMerImg(mer.getMerImgList().get(i));
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delMer(Integer merId) {
		boolean b = merDao.delMer(merId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateMer(Mer mer) {
		mer.setUpdateDate(new Date());
		boolean b = merDao.updateMer(mer);
		List<MerImg> delMerImgList = merImgService.browsePagingMerImg(mer.getMerId(), null, null,  1, Integer.MAX_VALUE, "mer_img_id","asc");
		for (MerImg merImg : delMerImgList) {
			b = merImgService.delMerImg(merImg.getMerImgId());
			}
		List<MerImg> merImgList = mer.getMerImgList();
		for (int i = 0; i < merImgList.size(); i++) {
			MerImg merImg = merImgList.get(i);
			merImg.setMerId(mer.getMerId());
			b=merImgService.addMerImg(merImg);
		}
		return b;
	}

	@Override
	public Mer loadMer(Integer merId) {
		Mer mer = merDao.loadMer(merId);
		List<MerImg> merImgList = merImgService.browsePagingMerImg(mer.getMerId(),null,null, 1, 8, "mer_img_id","asc");
		mer.setMerImgList(merImgList);
		MerCate merCate = merCateService.loadMerCate(mer.getMerCateId());
		mer.setMerCate(merCate);
		return mer;
	}

	@Override
	public int countAll(
			Double price,
			Integer saleNumber,
			Double saleMoney,
			Integer merCateId,
			Date createDate,
			Date updateDate,
			Integer status) {
		int c = merDao.countAll(
				price,
				saleNumber,
				saleMoney,
				merCateId,
				createDate,
				updateDate,
				status);
		return c;
	}

	@Override
	public List<Mer> browsePagingMer(
			Double price,
			Integer saleNumber,
			Double saleMoney,
			Integer merCateId,
			Date createDate,
			Date updateDate,
			Integer status,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Mer> l = merDao.browsePagingMer(
				price,
				saleNumber,
				saleMoney,
				merCateId,
				createDate,
				updateDate,
				status,
				pageNum-1, pageSize, orderName, orderWay);
		
		for (Mer mer : l) {
			List<MerImg> merImgList = merImgService.browsePagingMerImg(mer.getMerId(),null,null, 1, 8, "mer_img_id","asc");
			mer.setMerImgList(merImgList);
			MerCate merCate = merCateService.loadMerCate(mer.getMerCateId());
			mer.setMerCate(merCate);
		}
		return l;
	}
	@Override
	public Mer loadSmallMer(Integer merId) {
		Mer mer = merDao.loadSmallMer(merId);
		List<MerImg> merImgList = merImgService.browsePagingMerImg(mer.getMerId(),null,null, 1, 8, "mer_img_id","asc");
		mer.setMerImgList(merImgList);
		MerCate merCate = merCateService.loadMerCate(mer.getMerCateId());
		mer.setMerCate(merCate);
		return mer;
	}

	
}
