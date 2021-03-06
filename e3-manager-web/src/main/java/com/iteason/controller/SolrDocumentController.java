package com.iteason.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 
 * @author 阿荣
 * @Description:solr搜索引擎相关操作
 * @date: 2018年8月23日 下午9:00:42
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iteason.intef.SolrDocumentService;
import com.iteason.utils.E3Result;
@Controller
public class SolrDocumentController {

	@Autowired
	private SolrDocumentService solrDocumentService;
	
	
	/**
	 * 
	 * @author 阿荣
	 * @Description:添加商品信息索引文档到solr中
	 * @date: 2018年8月23日 下午9:02:00
	 * @return
	 */
	@RequestMapping(value="/index/item/import")
	@ResponseBody
	public E3Result addDocument(){
		try {
			solrDocumentService.addDocument();
		} catch (Exception e) {
			 return E3Result.build(500,"导入错误");
		}
		
		
			return E3Result.ok();
	}
	
	
}
