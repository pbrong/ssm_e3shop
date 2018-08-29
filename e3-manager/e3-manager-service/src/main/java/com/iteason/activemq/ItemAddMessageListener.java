package com.iteason.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;

import com.iteason.mapper.TbItemSolrMapper;
import com.iteason.pojo.TbItemSolrResult;


/**
 * 
 * @author 阿荣
 * @Description:商品添加后，接受activemq中的商品id并查询出该商品相关信息，再添加到索引库
 * @date: 2018年8月29日 下午6:17:08
 */
public class ItemAddMessageListener implements MessageListener {
	@Autowired
	private HttpSolrServer httpSolrServer;
	
	
	@Autowired
	private TbItemSolrMapper tbItemSolrMapper;

	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		//获取商品id
		try {
			String ItemId = textMessage.getText();
			System.out.println(ItemId);
			//睡眠1s钟，等事务完全执行完后才能查询到商品
			Thread.sleep(1000);
			//从数据库中查询该商品并添加到索引库
			TbItemSolrResult result = tbItemSolrMapper.selectTbItemSolrResult(Long.parseLong(ItemId));
			
			 
			
			
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
