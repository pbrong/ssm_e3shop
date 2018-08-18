package com.iteason.intef;

import java.util.List;

import com.iteason.pojo.EsayUIZtreeNode;

public interface ContentService {

	List<EsayUIZtreeNode> findContentCatagory(Long parentId);

}
