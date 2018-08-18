package com.iteason.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iteason.intef.ContentService;
import com.iteason.pojo.EsayUIZtreeNode;

@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;
	/**
	 * 
	 * @author 阿荣
	 * @Description:通过id查找商品类别
	 * @date: 2018年8月18日 下午9:42:21
	 * @return
	 */
	@RequestMapping(value="/content/category/list")
	@ResponseBody
	public List<EsayUIZtreeNode> findContentCatagory(@RequestParam(name="id",defaultValue="0")Long parentId){
		List<EsayUIZtreeNode> list = contentService.findContentCatagory(parentId);
		return list;
	}
}
