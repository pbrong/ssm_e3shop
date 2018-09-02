package com.iteason.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.iteason.intef.ItemDetailService;
import com.iteason.jedis.JedisClient;
import com.iteason.mapper.TbItemCatMapper;
import com.iteason.mapper.TbItemDescMapper;
import com.iteason.mapper.TbItemMapper;
import com.iteason.pojo.TbItem;
import com.iteason.pojo.TbItemCat;
import com.iteason.pojo.TbItemDesc;
import com.iteason.pojo.TbItemSolrResult;
import com.iteason.utils.JsonUtils;
@Service
public class ItemDetailServiceImp implements ItemDetailService {
	@Autowired
	private JedisClient jedisClient;
	
	
	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	@Override
	public TbItemSolrResult selectItemDetail(Long itemId) {
		TbItemSolrResult tbItemSolrResult = new TbItemSolrResult();
		//查询是否存在该商品信息缓存
		String itemJson = jedisClient.get("ITEM_INFO:"+itemId+":BASE");
		if(StringUtils.isNotEmpty(itemJson)){
			//不是空串
			TbItemSolrResult jsonToPojo = JsonUtils.jsonToPojo(itemJson, TbItemSolrResult.class);
			return jsonToPojo;
		}
		//不存在缓存
		
		
		// 查询商品详情
		TbItem item = tbItemMapper.selectByPrimaryKey(itemId);
		//补全tbItemSolrResult的属性
		tbItemSolrResult.setId(item.getId().toString());
		//通过cid获得目录名称
		TbItemCat cat = tbItemCatMapper.selectByPrimaryKey(item.getCid());
		String cataName = cat.getName();
		tbItemSolrResult.setCategory_name(cataName);
		
		tbItemSolrResult.setImage(item.getImage());
		tbItemSolrResult.setPrice(item.getPrice());
		tbItemSolrResult.setSell_point(item.getSellPoint());
		tbItemSolrResult.setTitle(item.getTitle());
		
		
		//添加到缓存中
		jedisClient.set("ITEM_INFO:"+itemId+":BASE", JsonUtils.objectToJson(tbItemSolrResult));
		//设置过期时间为1h
		jedisClient.expire("ITEM_INFO:"+itemId+":BASE", 3600);
		
		return tbItemSolrResult;
	}

	@Override
	public TbItemDesc selectItemDesc(Long itemId) {
		// 查询商品详情l    
		//判断缓存中是否有该详情
		String descJson = jedisClient.get("ITEM_INFO:"+itemId+":DESC");
		if(StringUtils.isNotEmpty(descJson)){
			//不是空串，通过json工具转成对象
			TbItemDesc jsonToPojo = JsonUtils.jsonToPojo(descJson, TbItemDesc.class);
			//返回
			return jsonToPojo;
		}
		
		//不存在缓存，添加缓存并设置过期时间为1h
		TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
		jedisClient.set("ITEM_INFO:"+itemId+":DESC", JsonUtils.objectToJson(tbItemDesc));
		jedisClient.expire("ITEM_INFO:"+itemId+":DESC", 3600);
		return tbItemDesc;
	}

	
}
