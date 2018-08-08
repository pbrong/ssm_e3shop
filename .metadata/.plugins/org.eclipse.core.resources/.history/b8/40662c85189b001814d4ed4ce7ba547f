package com.iteason.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iteason.mapper.TbItemCatMapper;
import com.iteason.pojo.TbItemCat;

@Service
public class TestService {
	@Autowired
	private TbItemCatMapper mapper;
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("service");
		TbItemCat result = mapper.selectByPrimaryKey(1l);
		System.out.println(result.toString());
		
	}

	
	
}
