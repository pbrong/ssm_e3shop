package com.iteason.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iteason.intef.ItemService;
import com.iteason.pojo.EasyUIDatagridResult;

@Controller
public class ItemController {

	
	@Autowired
	private ItemService itemService;
	/**
	 * 
	 * @author 阿荣
	 * @Description:管理员，item列表显示
	 * @date: 2018年8月10日 下午3:46:07
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/item/list")
	@ResponseBody
	public EasyUIDatagridResult findItemList(int page,int rows){
		
		EasyUIDatagridResult result = itemService.findItemList(page,rows);
		
		return result;
		
	}
}
