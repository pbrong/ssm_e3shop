package com.iteason.show.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

	/**|
	 * 
	 * @author 阿荣
	 * @Description:由springmvc路由到商城首页
	 * @date: 2018年8月18日 下午4:17:01
	 * @return
	 */
	@RequestMapping(value="/index")
	public String toIndex(){
		return "index";
	}
	
}
