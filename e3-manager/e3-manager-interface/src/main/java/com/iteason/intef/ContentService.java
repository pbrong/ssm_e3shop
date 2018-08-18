package com.iteason.intef;

import java.util.List;

import com.iteason.pojo.EsayUIZtreeNode;
import com.iteason.utils.E3Result;

public interface ContentService {

	List<EsayUIZtreeNode> findContentCatagory(Long parentId);

	E3Result createCatagory(Long parentId, String name);

}
