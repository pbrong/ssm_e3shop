package com.iteason.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iteason.intef.ItemService;
import com.iteason.mapper.TbItemMapper;
import com.iteason.pojo.EasyUIDatagridResult;
import com.iteason.pojo.TbItem;
import com.iteason.pojo.TbItemExample;
@Service
public class ItemServiceImp implements ItemService {
	private EasyUIDatagridResult result = new  EasyUIDatagridResult();
	@Autowired
	private TbItemMapper mapper;
	
	@Override
	public EasyUIDatagridResult findItemList(int page, int rows) {
		// 使用mybatis分页插件，查询相应item数据
		PageHelper.startPage(page, rows);
		
		TbItemExample example = new TbItemExample();
		List<TbItem> list = mapper.selectByExample(example);
		//封装成PageInfo对象
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		//封装总条数
		result.setTotal((int) pageInfo.getTotal());
		//封装数据
		result.setRows(list);
		return result;
	}
	
	

}
