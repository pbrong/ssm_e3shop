package com.iteason.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iteason.intef.ItemService;
import com.iteason.mapper.TbItemCatMapper;
import com.iteason.mapper.TbItemMapper;
import com.iteason.pojo.EasyUIDatagridResult;
import com.iteason.pojo.EsayUIZtreeNode;
import com.iteason.pojo.TbItem;
import com.iteason.pojo.TbItemCat;
import com.iteason.pojo.TbItemCatExample;
import com.iteason.pojo.TbItemExample;
import com.iteason.pojo.TbItemExample.Criteria;
@Service
public class ItemServiceImp implements ItemService {
	
	
	
	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	@Override
	public EasyUIDatagridResult findItemList(int page, int rows) {
		EasyUIDatagridResult result = new  EasyUIDatagridResult();
		// 使用mybatis分页插件，查询相应item数据
		PageHelper.startPage(page, rows);
		
		TbItemExample example = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(example);
		//封装成PageInfo对象
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		//封装总条数
		result.setTotal((int) pageInfo.getTotal());
		//封装数据
		result.setRows(list);
		return result;
	}
	
	@Override
	public List<EsayUIZtreeNode> findCatZtree(Long parentId) {
		 List<EsayUIZtreeNode> nodeList = new ArrayList<EsayUIZtreeNode>();
		TbItemCatExample example = new TbItemCatExample();
		//添加条件
		com.iteason.pojo.TbItemCatExample.Criteria c = example.createCriteria();
		c.andParentIdEqualTo(parentId);
		//  通过cid作为父ip，查找类别的tree
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		//封装成EsayUIZtreeNode对象
		for(TbItemCat tbItemCat:list){
			System.out.println(tbItemCat.getId()+"---------------------");
			EsayUIZtreeNode tree = new EsayUIZtreeNode();
			tree.setId(tbItemCat.getId());
			tree.setText(tbItemCat.getName());
			tree.setState(tbItemCat.getIsParent()?"closed":"open");
			nodeList.add(tree);
		}
		return nodeList;
	}
	
	

}
