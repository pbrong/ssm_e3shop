package com.iteason.service;

import java.io.IOException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iteason.mapper.TbItemMapper;
import com.iteason.pojo.TbItem;
import com.iteason.pojo.TbItemExample;

public class app {
	public static void main(String[] args) throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:*.xml");
		TbItemMapper mapper = context.getBean(TbItemMapper.class);
		//
		PageHelper.startPage(1,30);
		TbItemExample example = new TbItemExample();
		List<TbItem> list = mapper.selectByExample(example );
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		System.out.println(pageInfo.getTotal());
	}
	
}
