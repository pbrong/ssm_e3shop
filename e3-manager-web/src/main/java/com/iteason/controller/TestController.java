package com.iteason.controller;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iteason.intef.TestService;


@Controller
public class TestController {
	@Autowired
	private TestService testService;
	
	@RequestMapping(value="/index.action")
	public String hello(Model model){
		System.out.println("controller");
		
		testService.test();
		
		model.addAttribute("hello","hello world!!");
		
		return "index";
	}
}
