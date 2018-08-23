package com.iteason.solrj;
//测试solr搜索引擎

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class solrjTest {

	@Test
	public void addDocument() throws SolrServerException, IOException{
		//获得SolrjServer对象
		SolrServer httpSolrServer = new HttpSolrServer("http://localhost:8088/solr/");
		//获得solrinputdocument对象
		SolrInputDocument document = new SolrInputDocument();
		//往document里写入索引域,所有的document都必须有id
		document.addField("id", "doc01");
		document.addField("item_title", "哇哈哈牛奶");
		document.addField("item_sell_point", "真他妈好喝");
		document.addField("item_price",100);
		//网solrserver中写入document
		httpSolrServer.add(document);
		//提交
		httpSolrServer.commit();
		
	}
	
	@Test
	public void deleteDocument() throws SolrServerException, IOException {
		// 测试删除document
		//获得solrserver对象
		SolrServer httpSolrServer = new HttpSolrServer("http://localhost:8088/solr/");
		//根据document的id删除索引文档
		httpSolrServer.deleteById("doc01");
		//提交
		httpSolrServer.commit();
	}
}