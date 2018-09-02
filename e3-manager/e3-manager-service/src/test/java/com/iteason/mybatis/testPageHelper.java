/*package com.iteason.mybatis;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iteason.mapper.TbItemMapper;
import com.iteason.pojo.TbItem;
import com.iteason.pojo.TbItemExample;

public class testPageHelper {

	@Test
	public void testName() throws Exception {
		//加载配置文件
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-dao.xml");
		//获得mapper
		TbItemMapper mapper = context.getBean(TbItemMapper.class);
		//获得分页插件设置分页信息
		PageHelper.startPage(1, 10);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = mapper.selectByExample(example);
		//包装在PageInfo中方便查询
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		System.out.println(pageInfo.getTotal());
		System.out.println(pageInfo.getPages());
		System.out.println(list.size());
	}
	
}
*/