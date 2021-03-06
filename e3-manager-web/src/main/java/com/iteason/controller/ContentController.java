package com.iteason.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iteason.intef.ContentService;
import com.iteason.pojo.EsayUIZtreeNode;
import com.iteason.pojo.TbContent;
import com.iteason.utils.E3Result;

@Controller
public class ContentController {
	//图片地址前缀
	@Value("${IMAGE_ADDR}")
	private String IMAGE_ADDR;
	
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
	/**
	 * 
	 * @author 阿荣
	 * @Description:增加商品目录
	 * @date: 2018年8月19日 上午1:19:43
	 * @return
	 */
	@RequestMapping(value="/content/category/create")
	@ResponseBody
	public E3Result createCatagory(Long parentId,String name){
		E3Result e3Result = contentService.createCatagory(parentId,name);
		return e3Result;
	}
	
	/**
	 * 
	 * @author 阿荣
	 * @Description:保存内容
	 * @date: 2018年8月19日 下午1:26:43
	 * @param tbContent
	 * @return
	 */
	@RequestMapping(value="/content/save")
	@ResponseBody
	public E3Result saveContent(TbContent tbContent){
		//单图片上传地址缺少前缀（待解决）
		tbContent.setPic("http://localhost:8082"+tbContent.getPic());
		
		E3Result e3Result = contentService.saveContent(tbContent);
		return e3Result;
	}
}
