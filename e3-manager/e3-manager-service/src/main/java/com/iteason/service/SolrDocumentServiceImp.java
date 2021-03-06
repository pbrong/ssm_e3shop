package com.iteason.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iteason.intef.SolrDocumentService;
import com.iteason.mapper.TbItemSolrMapper;
import com.iteason.pojo.TbItemSolrResult;
@Service
public class SolrDocumentServiceImp implements SolrDocumentService {
	
	@Autowired
	private TbItemSolrMapper tbItemSolrMapper;
	
	
	/**
	 * 添加商品索引文档
	 */
	@Override
	public void addDocument() throws Exception{
		// 查询出所有所需的商品信息
		List<TbItemSolrResult> list = tbItemSolrMapper.selectTbItemSolrResultList();
		//获得SolrServer对象
		SolrServer httpSolrServer = new HttpSolrServer("http://localhost:8088/solr/");
		
		//遍历出list，将其加入document的索引域中
		for (TbItemSolrResult result : list) {
			//创建sorl的输入索引文档
			SolrInputDocument document = new SolrInputDocument();
			document.addField("id",result.getId());
			document.addField("item_title", result.getTitle());
			document.addField("item_sell_point", result.getSell_point());
			document.addField("item_price", result.getPrice());
			document.addField("item_image", result.getImage());
			document.addField("item_category_name", result.getCategory_name());
			//提交每个document
			httpSolrServer.add(document);
			//提交索引文档
			httpSolrServer.commit();
		}
			
		
	}

}
