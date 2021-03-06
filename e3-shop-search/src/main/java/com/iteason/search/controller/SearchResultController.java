package com.iteason.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
/**
 * 
 * @author 阿荣
 * @Description:搜索结果
 * @date: 2018年8月24日 下午7:33:47
 */
import org.springframework.web.bind.annotation.RequestMapping;

import com.iteason.intef.SearchResultService;
import com.iteason.pojo.SearchResult;
@Controller
public class SearchResultController {
	
	@Autowired
	private SearchResultService searchResultService;
/**
 * 
 * @author 阿荣
 * @Description:接受搜索框输入的keywords并返回商品
 * @date: 2018年8月24日 下午8:45:09
 * @param keyword
 * @return
 * @throws Exception 
 */
	@RequestMapping(value="/search.html")
	public String searchResult(String keyword,Model model) throws Exception{
		//浏览器输入字符集的转换
		keyword = new String(keyword.getBytes("iso-8859-1"),"utf-8");
		SearchResult result = searchResultService.searchResult(keyword);
		 model.addAttribute("itemList", result.getItemList());
		 model.addAttribute("totalCount", result.getTotalCount());
		 model.addAttribute("currentPage", result.getCurrentPage());
		 model.addAttribute("currentRows", result.getCurrentRows());
		 model.addAttribute("query", keyword);
		return "search";
	}
}
