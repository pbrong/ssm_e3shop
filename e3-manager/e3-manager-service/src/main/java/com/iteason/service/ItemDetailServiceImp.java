package com.iteason.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iteason.intef.ItemDetailService;
import com.iteason.mapper.TbItemCatMapper;
import com.iteason.mapper.TbItemDescMapper;
import com.iteason.mapper.TbItemMapper;
import com.iteason.pojo.TbItem;
import com.iteason.pojo.TbItemCat;
import com.iteason.pojo.TbItemDesc;
import com.iteason.pojo.TbItemSolrResult;
@Service
public class ItemDetailServiceImp implements ItemDetailService {

	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	@Override
	public TbItemSolrResult selectItemDetail(Long itemId) {
		TbItemSolrResult tbItemSolrResult = new TbItemSolrResult();
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
		
		return tbItemSolrResult;
	}

	@Override
	public TbItemDesc selectItemDesc(Long itemId) {
		// 查询商品详情l                
		TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
		return tbItemDesc;
	}

}
