package com.iteason.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iteason.intef.ContentService;
import com.iteason.mapper.TbContentCategoryMapper;
import com.iteason.mapper.TbContentMapper;
import com.iteason.pojo.EsayUIZtreeNode;
import com.iteason.pojo.TbContent;
import com.iteason.pojo.TbContentCategory;
import com.iteason.pojo.TbContentCategoryExample;
import com.iteason.pojo.TbContentCategoryExample.Criteria;
import com.iteason.pojo.TbContentExample;
import com.iteason.utils.E3Result;
@Service
public class ContentServiceImp implements ContentService {

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	
	@Autowired
	private TbContentMapper tbContentMapper;
	
	@Override
	public List<EsayUIZtreeNode> findContentCatagory(Long parentId) {
		List nodeList  = new ArrayList<EsayUIZtreeNode>();
	
		
		
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		//添加条件
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> cataList = tbContentCategoryMapper.selectByExample(example);
		for (TbContentCategory cata : cataList) {
			//封装成EsayUIZtreeNode返回
			EsayUIZtreeNode node = new EsayUIZtreeNode();
			node.setId(cata.getId());
			node.setState(cata.getIsParent()?"closed":"open");//state:是父节点需要closed，叶子节点要open
			node.setText(cata.getName());
			nodeList.add(node);
		}
			return nodeList;
	}

	/**
	 * 添加商品目录
	 */
	@Override
	public E3Result createCatagory(Long parentId, String name) {
		TbContentCategory newCatagory = new TbContentCategory();
		//补全新目录的属性(id由mybatis生成)
		newCatagory.setParentId(parentId); 
		newCatagory.setCreated(new Date());
		newCatagory.setIsParent(false);
		newCatagory.setName(name);
		newCatagory.setStatus(1);
		newCatagory.setUpdated(new Date());
		//修改父目录的state为closed
		TbContentCategory parentCatagory = new TbContentCategory();
		parentCatagory.setIsParent(true);//是父节点
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		//锁定id
		criteria.andIdEqualTo(parentId);
		//执行更新操作，更新该节点为父节点
		tbContentCategoryMapper.updateByExampleSelective(parentCatagory, example);
		//执行插入新目录操作
		tbContentCategoryMapper.insertSelective(newCatagory);
		return E3Result.ok();
	}
	/**
	 * 保存内容
	 */
	@Override
	public E3Result saveContent(TbContent tbContent) {
		//补全content属性
		tbContent.setCreated(new Date());
		tbContent.setUpdated(new Date());
		tbContentMapper.insert(tbContent);
		return E3Result.ok();
	}

	@Override
	public List<TbContent> findContent(Long CATAGORY_LUNBO_ID) {
		TbContentExample example = new TbContentExample();
		com.iteason.pojo.TbContentExample.Criteria criteria = example.createCriteria();
		//设置条件
		criteria.andCategoryIdEqualTo(CATAGORY_LUNBO_ID);
		List<TbContent> list = tbContentMapper.selectByExample(example);
		return list;
	}

}
