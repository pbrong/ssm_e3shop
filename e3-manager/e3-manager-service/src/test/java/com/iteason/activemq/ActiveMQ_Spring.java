/*package com.iteason.activemq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

*//**
 * 
 * @author 阿荣
 * @Description:测试activemq整合spring
 * @date: 2018年8月29日 上午11:47:07
 *//*
public class ActiveMQ_Spring {
	@Test
	public void testA(){
		
	
	//加载applicationContext-activemq.xml
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-activemq.xml");
	//获取jmsTemplate操作模板
	JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
	//从spring容器中取目标对象
	Destination destination = (Destination) context.getBean("queueDestination");
	//使用jmsTemplate发送消息
	jmsTemplate.send(destination, new MessageCreator() {
		
		@Override
		public Message createMessage(Session session) throws JMSException {
			// 发送消息
			TextMessage message = session.createTextMessage("activemq整合spring");
			return message;
		}
	});
	
	}
	
	@Test
	public void testQueueConsumer() throws Exception {
		//初始化spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext-activemq.xml");
		//等待
		System.in.read();
	}
}
*/