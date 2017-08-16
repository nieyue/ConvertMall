package com.nieyue.rabbitmq.confirmcallback;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.nieyue.bean.OrderRabbitmqDTO;
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
	@Value("${myPugin.projectName}")
	String projectName;
	
	    @RabbitListener(queues="${myPugin.rabbitmq.DIRECT_QUEUE}") 
	    public void process(Channel channel, OrderRabbitmqDTO orderRabbitmqDTO,Message message) throws IOException   {
	           try {
	        	  // LOGGER.info("消费端接收到消息: " + dataRabbitmqDTO.toString());
	        	  // LOGGER.info("message.getBody: " + new String (message.getBody()));
	        	  /**
	        	   * 判断是否存在
	        	   */
	        	
	        	   
	        	   //channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	        	   channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
