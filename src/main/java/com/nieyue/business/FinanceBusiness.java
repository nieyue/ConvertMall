package com.nieyue.business;

import com.nieyue.bean.Finance;
import com.nieyue.util.HttpClientUtil;
import com.nieyue.util.MyDESutil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 财务业务
 * @author 聂跃
 * @date 2017年8月19日
 */
public class FinanceBusiness {
	public static String sevenSecondesUrl="http://localhost";
	
	/**
	 * 获取个人财务信息
	 * @throws Exception 
	 * @re
	 */
	public static Finance getFinanceByAcountId(Integer acountId) throws Exception{
		String financelist = HttpClientUtil.doGet(sevenSecondesUrl+"/finance/list?acountId="+acountId+"?auth="+MyDESutil.getMD5("1000"));
		JSONObject json=JSONObject.fromObject(financelist);
		JSONArray jsa = JSONArray.fromObject(json.get("list"));
		Finance finance = (Finance) JSONObject.toBean((JSONObject)jsa.get(0), Finance.class	);
		return finance;
	}
}
