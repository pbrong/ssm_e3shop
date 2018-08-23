package com.iteason.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.iteason.intef.ContentService;
import com.iteason.jedis.JedisClient;
import com.iteason.mapper.TbContentCategoryMapper;
import com.iteason.mapper.TbContentMapper;
import com.iteason.pojo.EsayUIZtreeNode;
import com.iteason.pojo.TbContent;
import com.iteason.pojo.TbContentCategory;
import com.iteason.pojo.TbContentCategoryExample;
import com.iteason.pojo.TbContentCategoryExample.Criteria;
import com.iteason.pojo.TbContentExample;
import com.iteason.utils.E3Result;
import com.iteason.utils.JsonUtils;
@Service
public class ContentServiceImp implements ContentService {

	//缓存的key值
	private String CONTENT_LUNBO = "CONTENT_LUNBO";
		
		
	@Autowired
	private JedisClient jedisClient;
	
	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	
	@Autowired
	private TbContentMapper tbContentMapper;
	/**
	 * 查询内容分类
	 */
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
		
		//删除掉缓存服务器中的相关缓存，防止更新后仍然读取到旧缓存，达到缓存同步的目的
		try {
			String lunbo_string = jedisClient.hget(CONTENT_LUNBO, tbContent.getCategoryId().toString());
			if(StringUtils.isNotBlank(lunbo_string)){
				//删除掉缓存服务器中的相关缓存，防止更新后仍然读取到旧缓存，达到缓存同步的目的
				jedisClient.hdel(CONTENT_LUNBO, tbContent.getCategoryId().toString());
			}
		} catch (Exception e) {
		}
		
		return E3Result.ok();
	}
	
	/**
	 * 查询内容
	 */
	@Override
	public List<TbContent> findContent(Long CATAGORY_LUNBO_ID) {
		String cid = CATAGORY_LUNBO_ID.toString();
		
		//查询到缓存
		try {
			String content_lunbo_string = jedisClient.hget(CONTENT_LUNBO,cid);
			if(StringUtils.isNotBlank(content_lunbo_string)){
		 		//缓存存在
		 		//转化为相应的list
				List<TbContent> contentList = JsonUtils.jsonToList(content_lunbo_string, TbContent.class);
				return contentList;
		 	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 	
			//查询不到该缓存，查询数据库
			TbContentExample example = new TbContentExample();
			com.iteason.pojo.TbContentExample.Criteria criteria = example.createCriteria();
			//设置条件
			criteria.andCategoryIdEqualTo(CATAGORY_LUNBO_ID);
			List<TbContent> contentList = tbContentMapper.selectByExample(example);
			
			
			//添加list转化为json到缓存中
			String contentJson = JsonUtils.objectToJson(contentList);
			//添加hash缓存格式为：key filed value
			try {
				jedisClient.hset(CONTENT_LUNBO,cid, contentJson);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		 
			return contentList;
	
		
	}

}
