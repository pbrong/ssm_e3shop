package com.iteason.solrj;
//测试solr搜索引擎

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
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
	@Test
	public void queryTest() throws SolrServerException {
		// 查询测试
		HttpSolrServer httpSolrServer = new HttpSolrServer("http://localhost:8088/solr/");
		//获得SolrQuery对象封装查询条件
		SolrQuery query = new SolrQuery();
		//封装查询条件
		query.setQuery("三星");
		//设置默认搜索域
		query.set("df","item_title");
		//开始索引
		query.setStart(0);
		//容量
		query.setRows(20);
		//开启高亮
		query.setHighlight(true);
		//指定高亮域
		query.addHighlightField("item_title");
		//指定高亮前后缀
		query.setHighlightSimplePre("<span style='color:red'>");
		query.setHighlightSimplePost("<span>");
		//获得List结果
		QueryResponse response = httpSolrServer.query(query);
		//解析RESPONSE
		SolrDocumentList list = response.getResults();
		
		//获得高亮的Map,Map中先通过document的id查询
		Map<String, Map<String, List<String>>> map = response.getHighlighting();
		
		System.out.println("查询到的数量:"+list.getNumFound());
		for (SolrDocument doc : list) {
			System.out.println(doc.get("id"));
			//获得高亮域
			Map<String, List<String>> document = map.get(doc.get("id"));
			List<String> titles = document.get("item_title");
			System.out.println(titles.get(0));
		}
	}
}
