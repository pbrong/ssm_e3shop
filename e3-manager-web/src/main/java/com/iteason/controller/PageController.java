package com.iteason.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	/**
	 * 
	 * @author 阿荣
	 * @Description:路由到index首页
	 * @date: 2018年8月10日 下午1:46:16
	 * @return
	 */
	@RequestMapping(value="/")
	public String index(){
		return "index";
	}
	
	/**
	 * 
	 * @author 阿荣
	 * @Description:根据输入的路径路由
	 * @date: 2018年8月10日 下午1:46:37
	 * @return
	 */
	@RequestMapping(value="/{page}")
	public String page(@PathVariable String page){
		return page;
	}
	
}
