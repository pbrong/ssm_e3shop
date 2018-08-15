package com.iteason.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iteason.intef.ItemService;
import com.iteason.pojo.EasyUIDatagridResult;
import com.iteason.pojo.EsayUIZtreeNode;
import com.iteason.pojo.TbItem;
import com.iteason.utils.E3Result;

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
	
	/**
	 * 
	 * @author 阿荣
	 * @Description:通过cid作为父ip，查找类别的tree
	 * @date: 2018年8月11日 下午2:57:49
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value="/item/cat/list")
	@ResponseBody
	public List<EsayUIZtreeNode> findCatZtree(@RequestParam(name="id",defaultValue="0")
			Long parentId){
		List<EsayUIZtreeNode> list  = 	itemService.findCatZtree(parentId);
		
		return list;
	}
	
	/**
	 * 
	 * @author 阿荣
	 * @Description:保存商品到TbItem表和TbItemDesc表
	 * @date: 2018年8月15日 下午1:46:45
	 * @param item
	 * @param desc
	 * @return
	 */
	@RequestMapping(value="/item/save")
	@ResponseBody
	public E3Result saveItem(TbItem item,String desc){
		try{
			itemService.saveItem(item,desc);
			return E3Result.ok();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		
	}
}
