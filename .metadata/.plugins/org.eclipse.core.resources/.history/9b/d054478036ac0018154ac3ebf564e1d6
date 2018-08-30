package com.iteason.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iteason.intef.ItemDetailService;
import com.iteason.pojo.TbItemDesc;
import com.iteason.pojo.TbItemSolrResult;

/**
 * 
 * @author 阿荣
 * @Description:show the item 
 * @date: 2018年8月30日 下午4:00:58
 */
@Controller
public class ItemDetailController {
	@Autowired
	private ItemDetailService itemDetailService;
	
	/**
	 * 商品详情
	 */
	@RequestMapping(value="/item/${itemId}")
	public String selectItemDetail(@PathVariable Long itemId,Model model){
		
		TbItemSolrResult item  =  itemDetailService.selectItemDetail(itemId);
		TbItemDesc desc = itemDetailService.selectItemDesc(itemId);
		
		//商品信息
		model.addAttribute("item", item);
		//商品详情
		model.addAttribute("itemDesc", desc);
		return "item";
	}
}
