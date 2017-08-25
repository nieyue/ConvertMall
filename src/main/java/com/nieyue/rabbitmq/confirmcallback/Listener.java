package com.nieyue.rabbitmq.confirmcallback;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.nieyue.bean.FlowWater;
import com.nieyue.bean.MerOrder;
import com.nieyue.service.MerOrderService;
import com.rabbitmq.client.Channel;

/**
 * 消息监听者
 * @author 聂跃
 * @date 2017年5月31日
 */
@Configuration  
public class Listener {
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	@Resource
	private MerOrderService merOrderService;
	@Resource
	private Sender sender;
	@Value("${myPugin.projectName}")
	String projectName;
		/**
		 * 商品订单
		 * @param channel
		 * @param orderRabbitmqDTO
		 * @param message
		 * @throws IOException
		 */
	    @RabbitListener(queues="${myPugin.rabbitmq.MERORDER_DIRECT_QUEUE}") 
	    public void merOrder(Channel channel, MerOrder merOrder,Message message) throws IOException   {
	           try {
	        	  boolean b = merOrderService.addMerOrder(merOrder);
	        	  if(b){
    			//记录流水，新手任务收益
    			FlowWater flowWater = new FlowWater();
    			flowWater.setAcountId(merOrder.getAcountId());
    			flowWater.setCreateDate(new Date());
    			Double money=0.0;//总额
    			for (int i = 0; i < merOrder.getOrderMerList().size(); i++) {
					money+=merOrder.getOrderMerList().get(i).getTotalPrice();
				}
    			flowWater.setRealMoney(0.0);//商品真钱
    			flowWater.setMoney(-money);//商品为减
    			flowWater.setType(-1);//1-兑换商品
    			flowWater.setSubtype(1);
    			sender.sendMerOrderFlowWater(flowWater);
	        	  } 
	        	   channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			} catch (Exception e) {
				 try {
					channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
				} catch (IOException e1) {
					channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
					
					e1.printStackTrace();
				}
				//e.printStackTrace();
			} //确认消息成功消费 
	    }     
}
