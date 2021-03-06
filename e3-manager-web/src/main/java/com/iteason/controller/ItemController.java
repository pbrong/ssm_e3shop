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
import com.iteason.pojo.TbItemDesc;
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
		//如果该节点为closed，那么点击后会传输该节点的标识，默认为id
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
	
	/**
	 * 
	 * @author 阿荣
	 * @Description:根据ids批量删除商品
	 * @date: 2018年8月18日 上午10:56:10
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/rest/item/delete")
	@ResponseBody
	public E3Result deleteItem(String ids){
		
		String[] idList = ids.split(",");
		
		try {
			itemService.delete(idList);
			return E3Result.ok();
		} catch (Exception e) {
			//删除失败
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	
	/**
	 * 
	 * @author 阿荣
	 * @Description:根据ids下架商品
	 * @date: 2018年8月18日 上午11:13:22
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/rest/item/instock")
	@ResponseBody
	public E3Result instock(String ids){
		
		try {
			itemService.instock(ids);
			return E3Result.ok();
		} catch (Exception e) {
			//下架失败
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @author 阿荣
	 * @Description:商品上架
	 * @date: 2018年8月18日 上午11:27:12
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/rest/item/reshelf")
	@ResponseBody
	public E3Result reshelf(String ids){
		try {
			itemService.reshelf(ids);
			return E3Result.ok();
		} catch (Exception e) {
			//上架失败
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 
	 * @author 阿荣
	 * @Description:商品描述的回显
	 * @date: 2018年8月18日 上午11:55:36
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/rest/item/query/item/desc")
	@ResponseBody
	public E3Result desc(String id){
		try {
			TbItemDesc data = itemService.desc(id);
			return E3Result.ok(data);
		} catch (Exception e) {
			e.printStackTrace();
			return E3Result.build(500, "error");
		}
		
	}
	
	/**
	 * 
	 * @author 阿荣
	 * @Description:update item
	 * @date: 2018年8月18日 下午12:21:52
	 * @param item
	 * @param desc
	 * @return
	 */
	@RequestMapping(value="/rest/item/update")
	@ResponseBody
	public E3Result update(TbItem item,String desc){
		
		try{
			itemService.updateItem(item,desc);
			return E3Result.ok();
		}catch(Exception e){
			e.printStackTrace();
			return E3Result.build(500, "error");
		}
		
		
	}
}
