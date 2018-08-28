package com.iteason.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Repository;
/**
 * 
 * @author 阿荣
 * @Description:solr的dao
 * @date: 2018年8月24日 下午9:10:06
 */

import com.iteason.pojo.SearchResult;
import com.iteason.pojo.TbItemSolrResult;
@Repository
public class SearchResultDao {

	public SearchResult searchResult(String keyword) throws SolrServerException {
		List<TbItemSolrResult> itemList = new ArrayList<TbItemSolrResult>();
		SearchResult searchResult = new SearchResult();
		//创建solrServer对象连接solr
		SolrServer httpSolrServer = new HttpSolrServer("http://localhost:8088/solr/");
		//创建solrQuery封装查询条件
		SolrQuery query = new SolrQuery();
		//封装条件
		//默认查询域
		query.set("df","item_keywords");
		query.setQuery(keyword);
		//设置默认条数为24条
		query.setStart(0);
		query.setRows(24);
		//设置高亮
		query.setHighlight(true);
		//设置高亮域
		query.addHighlightField("item_title");
		query.addHighlightField("item_sell_point");
		query.addHighlightField("item_category_name");
		//获得查询结果
		QueryResponse response = httpSolrServer.query(query);
		//获取document结果集
		SolrDocumentList documents = response.getResults();
		//获得高亮结果集
		Map<String, Map<String, List<String>>> documentsH = response.getHighlighting();
		//获取查询到的数量
		long totalCount = documents.getNumFound();
		//遍历documents，将信息提取到TbItemSolrResult中
		for (SolrDocument document : documents) {
			TbItemSolrResult item = new TbItemSolrResult();
			item.setId((String) document.get("id"));
			item.setImage((String) document.get("item_image"));
			item.setPrice((long) document.get("item_price"));
			//获得高亮域
			Map<String, List<String>> d = documentsH.get(document.get("id"));
			if(d.get("item_category_name").size() > 0){
				item.setCategory_name(d.get("item_category_name").get(0));
			}
			if(d.get("item_sell_point").size() > 0){
				item.setSell_point(d.get("item_sell_point").get(0));
			}
			if(d.get("item_title").size() > 0){
				item.setTitle(d.get("item_title").get(0));
			}
			//封装好一个就往List中添加一个
			itemList.add(item);
		}
			
			//放入SearchResult中
			searchResult.setItemList(itemList);
			searchResult.setTotalCount(totalCount);
			searchResult.setCurrentPage((long) 0);
			searchResult.setCurrentRows((long) 24);
			
		return searchResult;
	}
	
	
	
}
