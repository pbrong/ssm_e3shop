package com.iteason.show.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iteason.intef.ContentService;
import com.iteason.pojo.TbContent;

@Controller
public class indexController {

	
	@Value("${CATAGORY_LUNBO_ID}")
	private Long CATAGORY_LUNBO_ID;
	
	
	
	@Autowired
	private ContentService contentService;
	
	
	/**|
	 * 
	 * @author 阿荣
	 * @Description:由springmvc路由到商城首页
	 * @date: 2018年8月18日 下午4:17:01
	 * @return
	 */
	@RequestMapping(value="/index")
	public String toIndex(Model model){
		List<TbContent> ad1List = contentService.findContent(CATAGORY_LUNBO_ID);
		model.addAttribute("ad1List", ad1List);
		return "index";
	}
	
}
