package com.iteason.intef;

import java.util.List;

import com.iteason.pojo.EasyUIDatagridResult;
import com.iteason.pojo.EsayUIZtreeNode;

public interface ItemService {

	EasyUIDatagridResult findItemList(int page, int rows);

	List<EsayUIZtreeNode> findCatZtree(Long parentId);

}
