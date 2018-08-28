package com.iteason.service;

import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iteason.dao.SearchResultDao;
import com.iteason.intef.SearchResultService;
import com.iteason.pojo.SearchResult;
@Service
public class SearchResultServiceImp implements SearchResultService {
	@Autowired
	private SearchResultDao searchResultDao;
	
	/**
	 * 返回搜索结果
	 */
	@Override
	public SearchResult searchResult(String keyword) throws Exception{
		SearchResult result = searchResultDao.searchResult(keyword);
		int  a = 1/0;
		return result;
	}

}
