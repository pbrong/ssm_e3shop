package com.iteason.show.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

	
	@Value("${CATAGORY_LUNBO_ID}")
	private String CATAGORY_LUNBO_ID;
	
	/**|
	 * 
	 * @author 阿荣
	 * @Description:由springmvc路由到商城首页
	 * @date: 2018年8月18日 下午4:17:01
	 * @return
	 */
	@RequestMapping(value="/index")
	public String toIndex(Model model){
		
		
		
		return "index";
	}
	
}
