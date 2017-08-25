package com.nieyue.rabbitmq.confirmcallback;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
/**
 * 配置
 * @author 聂跃
 * @date 2017年5月31日
 */
@Configuration  
public class AmqpConfig {
	/**
	 * 商品订单
	 */
	@Value("${myPugin.rabbitmq.MERORDER_DIRECT_EXCHANGE}")
    public  String MERORDER_DIRECT_EXCHANGE ;  
	@Value("${myPugin.rabbitmq.MERORDER_DIRECT_ROUTINGKEY}")
    public String MERORDER_DIRECT_ROUTINGKEY;  
	@Value("${myPugin.rabbitmq.MERORDER_DIRECT_QUEUE}")
    public  String MERORDER_DIRECT_QUEUE; 
	/**
	 *商品订单流水
	 */
	@Value("${myPugin.rabbitmq.MERORDERFLOWWATER_DIRECT_EXCHANGE}")
	public  String MERORDERFLOWWATER_DIRECT_EXCHANGE ;  
	@Value("${myPugin.rabbitmq.MERORDERFLOWWATER_DIRECT_ROUTINGKEY}")
	public String MERORDERFLOWWATER_DIRECT_ROUTINGKEY;  
	@Value("${myPugin.rabbitmq.MERORDERFLOWWATER_DIRECT_QUEUE}")
	public  String MERORDERFLOWWATER_DIRECT_QUEUE; 
	
	
    @Autowired
    ConnectionFactory  connectionFactory ;
    /** 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置 */  
    @Bean  
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)  
    public RabbitTemplate rabbitTemplate() {  
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);  
        return rabbitTemplate;  
    } 
    /** 
     *商品订单
     */  
    /*
     * 设置交换机类型
     */   
    @Bean  
    public DirectExchange merOrderDirectExchange() {  
        /** 
         * DirectExchange:按照routingkey分发到指定队列 
         * TopicExchange:多关键字匹配 
         * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念 
         * HeadersExchange ：通过添加属性key-value匹配 
         */  
    	DirectExchange de = new DirectExchange(MERORDER_DIRECT_EXCHANGE);
        return de;
    }
    /*
     * 设置队列
     */
    @Bean  
    public Queue merOrderDirectQueue() {  
        return new Queue(MERORDER_DIRECT_QUEUE);  
    }
    /*
     * 设置绑定
     */
    @Bean  
    public Binding merOrderDirectBinding() {  
        /** 将队列绑定到交换机 */  
        return BindingBuilder.bind(merOrderDirectQueue()).to(merOrderDirectExchange()).with(MERORDER_DIRECT_ROUTINGKEY);  
    } 
    
    /** 
     *商品订单流水
     */  
    /*
     * 设置交换机类型
     */   
    @Bean  
    public DirectExchange merOrderFlowWaterDirectExchange() {  
    	DirectExchange de = new DirectExchange(MERORDERFLOWWATER_DIRECT_EXCHANGE);
    	return de;
    }
    /*
     * 设置队列
     */
    @Bean  
    public Queue merOrderFlowWaterDirectQueue() {  
    	return new Queue(MERORDERFLOWWATER_DIRECT_QUEUE);  
    }
    /*
     * 设置绑定
     */
    @Bean  
    public Binding merOrderFlowWaterDirectBinding() {  
    	/** 将队列绑定到交换机 */  
    	return BindingBuilder.bind(merOrderFlowWaterDirectQueue()).to(merOrderFlowWaterDirectExchange()).with(MERORDERFLOWWATER_DIRECT_ROUTINGKEY);  
    } 

}
