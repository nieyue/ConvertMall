package com.nieyue.business;

import org.springframework.context.annotation.Configuration;

/**
 * 商品订单业务
 * @author 聂跃
 * @date 2017年8月8日
 */
@Configuration
public class MerOrderBusiness {
  /**
   * 商品订单物流
   * @param 快递公司,0暂无,1顺丰速运SF,2中通快递ZTO,3圆通速递YTO,4申通快递STO,5百世快递BestExpress,6韵达快递YUNDA,7中国邮政EMS,8宅急送ZJS,9FedEx联邦,10京东物流
   * 11德邦物流,12安能物流,13优速快递,14天天快递,15汇通快递,16国通快递,17全峰快递,18菜鸟物流
   * @throws BookPayException 
   */
	public String getOrderMerCourierCompany(Integer courierCompany) {
		String cc="暂无";
		if(courierCompany==0){
			cc="暂无";
		}else if(courierCompany==1){
			cc="顺丰速运SF";
		}else if(courierCompany==2){
			cc="中通快递ZTO";
		}else if(courierCompany==3){
			cc="圆通速递YTO";
		}else if(courierCompany==4){
			cc="申通快递STO";
		}else if(courierCompany==5){
			cc="百世快递BestExpress";
		}else if(courierCompany==6){
			cc="韵达快递YUNDA";
		}else if(courierCompany==7){
			cc="中国邮政EMS";
		}else if(courierCompany==8){
			cc="宅急送ZJS";
		}else if(courierCompany==9){
			cc="FedEx联邦";
		}else if(courierCompany==10){
			cc="京东物流";
		}else if(courierCompany==11){
			cc="德邦物流";
		}else if(courierCompany==12){
			cc="安能物流";
		}else if(courierCompany==13){
			cc="优速快递";
		}else if(courierCompany==14){
			cc="天天快递";
		}else if(courierCompany==15){
			cc="汇通快递";
		}else if(courierCompany==16){
			cc="国通快递";
		}else if(courierCompany==17){
			cc="全峰快递";
		}else if(courierCompany==18){
			cc="菜鸟物流";
		}
		return cc;
	}
	/**
	 * 商品订单状态
	 * @param 状态，0已下单-未支付，1已支付-未发货，2已发货-未完成，3申请退款，4已退款，5拒绝退款,6已完成
	 * @throws BookPayException 
	 */
	public String getOrderMerStatus(Integer status) {
		String cc="已下单-未支付";
		if(status==0){
			cc="已下单-未支付";
		}else if(status==1){
			cc="已支付-未发货";
		}else if(status==2){
			cc="已发货-未完成";
		}else if(status==3){
			cc="申请退款";
		}else if(status==4){
			cc="已退款";
		}else if(status==5){
			cc="拒绝退款";
		}else if(status==6){
			cc="已完成";
		}
		return cc;
	}


public static void main(String[] args)  {
	
	 
}
}
